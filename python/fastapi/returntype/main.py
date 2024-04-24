from typing import Any, Union

from fastapi import FastAPI, Response
from fastapi.responses import JSONResponse, RedirectResponse
from pydantic import BaseModel, EmailStr

app = FastAPI()


class Item(BaseModel):
    name: str
    description: Union[str, None] = None
    price: float
    tax: Union[float, None] = None
    tags: list[str] = []

#Define the return type, fastAPI will validate it using pydantic definition
@app.post("/items/")
async def create_item(item: Item) -> Item:
    return item


#In alternative can be used response_model annotation. Can define a filter interface on the returned result type
class UserIn(BaseModel):
    username: str
    password: str
    email: EmailStr
    full_name: Union[str, None] = None


class UserOut(BaseModel):
    username: str
    email: EmailStr
    full_name: Union[str, None] = None

#return the same object of the input removing fields not to be returned
#if in this case we had specified instead the reutrn tipe (-> UserOut) instead of response_model, there woludl have been an error on type checking (or we should have create a new instance of the correct type)
@app.post("/user/", response_model=UserOut, response_model_exclude_unset=True) #Exclude unset won't include not set fields in the response. Alternative : response_model_exclude_defaults or response_model_exclude_none
async def create_user(user: UserIn) -> Any:
    return user


#alternative to previous situation using subtype (I PREFER THIS)
class BaseUser(BaseModel):
    username: str
    email: EmailStr
    full_name: Union[str, None] = None


class UserInAlt(BaseUser):
    password: str


@app.post("/user-alt/")
async def create_user_alt(user: UserInAlt) -> BaseUser:
    return user


#Inheritance used also in base response example
#In this case RedirectResponse and JSONResponse are subclass of Response
@app.get("/portal")
async def get_portal(teleport: bool = False) -> Response:
    if teleport:
        return RedirectResponse(url="https://www.youtube.com/watch?v=dQw4w9WgXcQ")
    return JSONResponse(content={"message": "Here's your interdimensional portal."})


#example of union return type
class BaseItem(BaseModel):
    description: str
    type: str

class CarItem(BaseItem):
    type: str = "car"


class PlaneItem(BaseItem):
    type: str = "plane"
    size: int


items = {
    "item1": {"description": "All my friends drive a low rider", "type": "car"},
    "item2": {
        "description": "Music is my aeroplane, it's my aeroplane",
        "type": "plane",
        "size": 5,
    },
}

@app.get("/items/{item_id}", response_model=Union[PlaneItem, CarItem])
async def read_item(item_id: str):
    return items[item_id]
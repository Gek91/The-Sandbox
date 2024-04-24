from typing import Union

from typing_extensions import Annotated
from fastapi import FastAPI, Body
from pydantic import BaseModel, Field

# data model inherits from pydantic BaseModel. Attributes uses python types
#define basic type validation request body 
class Item(BaseModel):
    name: str
    description: Union[str, None] = None # model attributes can have default value (became not required)
    price: float
    tax: Union[float, None] = None

#it's possibile to define more specific validation on single field value
class User(BaseModel):
    username: str
    full_name: Union[str, None] = Field(default = None, title="full name", max_length=50, examples=["example full name"])
    age: int = Field(gt = 0, description="age")

app = FastAPI()


@app.post("/items/")
async def create_item(item: Item): #request body aspect to contain data with item structure
    item_dict = item.dict()
    if item.tax:
        price_with_tax = item.price + item.tax
        item_dict.update({"price_with_tax": price_with_tax})
    return item_dict

#multiple body parameters
#in this case fastapi expect a request body containing both of the object -> {"item": {...} , "user":{...}}
@app.put("/items/{item_id}")
async def update_item(item_id: int, item: Item, user: User):
    return {"item_id": item_id, "item": item, "user": user}


#single value as body parameter
#to define a single value we need to define it with Annotated and Body. without will be used as query parameter
@app.put("/items-annotated/{item_id}")
async def update_item_annotaded(item_id: int, value: Annotated[int, Body(gt=0)]):
    return {"item_id": item_id, "value": value}

#Embed
#permits to have the defined structure for the request inside a specified key in the request
#instead of { ...structure... } -> { key: {...structure...} }
@app.post("/items-embed/")
async def create_item_embed(item: Annotated[Item, Body(embed=True, examples = [{
        "name": "Foo",
        "description": "A very nice Item",
        "price": 35.4,
        "tax": 3.2,
    }])
]):
    return {"item": item}

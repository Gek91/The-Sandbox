from typing import Union

from fastapi import FastAPI
from pydantic import BaseModel



# Create a FastAPI app
app = FastAPI()


class Item(BaseModel):
    name: str
    price: float
    is_offer: Union[bool, None] = None

#Path operations are eveluated in order, make sure that the order is correct based on the path linked with the handler

#path operation decorator on the app variable of the FastApi application
#Define an handler for the path and HTTP method
@app.get("/")
def read_root():
    return {"Hello": "World"}


@app.put("/items/{item_id}")
def update_item(item_id: int, item: Item):
    return {"item_name": item.name, "item_id": item_id}
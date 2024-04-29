from typing import Annotated, Union
from fastapi import FastAPI, HTTPException, Header
from fastapi.testclient import TestClient
from pydantic import BaseModel

app = FastAPI()


@app.get("/")
async def read_main():
    return {"msg": "Hello World"}


#test client created passing FastAPI application
client = TestClient(app)

#create test function with test_ prefix
#normale assert statement to test the result
#test function are normal def fucntion that makes simple call (without await)
def test_read_main():
    response = client.get("/")
    assert response.status_code == 200
    assert response.json() == {"msg": "Hello World"}




fake_secret_token = "coneofsilence"

fake_db = {
    "foo": {"id": "foo", "title": "Foo", "description": "There goes my hero"},
    "bar": {"id": "bar", "title": "Bar", "description": "The bartenders"},
}

class Item(BaseModel):
    id: str
    title: str
    description: Union[str, None] = None


@app.get("/items/{item_id}", response_model=Item)
async def read_main(item_id: str, x_token: Annotated[str, Header()]):
    if x_token != fake_secret_token:
        raise HTTPException(status_code=400, detail="Invalid X-Token header")
    if item_id not in fake_db:
        raise HTTPException(status_code=404, detail="Item not found")
    return fake_db[item_id]


@app.post("/items/", response_model=Item)
async def create_item(item: Item, x_token: Annotated[str, Header()]):
    if x_token != fake_secret_token:
        raise HTTPException(status_code=400, detail="Invalid X-Token header")
    if item.id in fake_db:
        raise HTTPException(status_code=409, detail="Item already exists")
    fake_db[item.id] = item
    return item
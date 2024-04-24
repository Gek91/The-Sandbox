from enum import Enum
from fastapi import FastAPI

app = FastAPI()


#status code
@app.post("/items/", status_code=201)
async def create_item(name: str):
    return {"name": name}


#tags: useful to organize endpoint
class Tags(Enum):
    items = "items"
    users = "users"

@app.get("/users/", tags=[Tags.users])
async def read_users():
    return [{"username": "johndoe"}]

@app.post("/items-with-tags/", tags=[Tags.items])
async def create_item_tag(name: str):
    return {"name": name}


#summary and description
@app.post(
    "/items-summary/",
    summary="Summary",
    description="Description",
    response_description="Description of the response",
)
async def create_item_sum(name: str):
    return {"name": name}


#Deprecated
@app.get("/elements/", deprecated=True)
async def read_elements():
    return [{"item_id": "Foo"}]
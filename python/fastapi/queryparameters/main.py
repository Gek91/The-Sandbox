from typing import Union
from typing import Union


from typing_extensions import Annotated
from fastapi import FastAPI, Query


fake_items_db = [{"item_name": "Foo"}, {"item_name": "Bar"}, {"item_name": "Baz"}]

# Create a FastAPI app
app = FastAPI()

#handler parameters not in the path are automatically interpreted as query parameters
#items/?skip=0&limit=10
@app.get("/items/")
def read_item(skip: int = 0, limit: int = 10): #default value
    return fake_items_db[skip : skip + limit]

#q is a query parameter with default value None -> it's optional
#needy is a query parameeter without default -> it's required
@app.get("/items/{item_id}")
def read_item(item_id: str, needy: str, q: Union[str, None] = None):
    if q:
        return {"item_id": item_id, "q": q, "needy": needy}
    return {"item_id": item_id, "needy": needy}

#use Annotated to specify type and validation on q param
#first define the type in annotated, second use Query to apply validation
@app.get("/items-annotated/")
def read_items(
    q: Annotated[Union[str, None], 
                Query(
                    alias="item-query",
                    title="Query string",
                    description="Query string for the items to search in the database that have a good match",
                    min_length=3,
                    max_length=50,
                    pattern="^fixedquery$"
                )] = None):
    
    results = {"items": [{"item_id": "Foo"}, {"item_id": "Bar"}]}
    if q:
        results.update({"q": q})
    return results

from typing import Union

from fastapi import FastAPI, Path
from typing_extensions import Annotated


# Create a FastAPI app
app = FastAPI()



#path parameter example
#path param data validation performed under the hood by pydantic
@app.get("/items/{item_id}")
def read_item(item_id: int):
    return {"item_id": item_id}

#use Annotated to specify type and validation o path param
@app.get("/items-annotated/{item_id}")
def read_item_annotaded(item_id: Annotated[int, 
            Path(
                title="The ID of the item to get",
                ge=1,    # >= 1
                gt=0,    # >0
                lt=1000, # < 1000
                le= 999, # <= 999
                )]):
    
    return {"item_id": item_id}

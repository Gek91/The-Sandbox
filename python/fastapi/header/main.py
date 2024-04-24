from typing import Annotated, Union

from fastapi import FastAPI, Header

app = FastAPI()

#retrieve value of the header user-agent (automatic conversion of _ to -) and a list of headers x-token
@app.get("/items/")
async def read_items(
    user_agent: Annotated[Union[str, None], Header()] = None,
    x_token: Annotated[Union[list[str], None], Header()] = None
    ):
    return {"User-Agent": user_agent}
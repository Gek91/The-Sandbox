from fastapi import APIRouter, Depends, HTTPException

from ..dependencies import get_token_header

#common endpoint configuration
#single endpoint depenendecies are resolved in the following order: router dependencies, path decorator depenedices, parameter dependecies
router = APIRouter(
    prefix="/items", #same prefix
    tags=["items"], #same tag
    dependencies=[Depends(get_token_header)], #same dependencies
    responses={404: {"description": "Not found"}}, #same response
)


fake_items_db = {"plumbus": {"name": "Plumbus"}, "gun": {"name": "Portal Gun"}}


@router.get("/")
async def read_items():
    return fake_items_db


@router.get("/{item_id}")
async def read_item(item_id: str):
    if item_id not in fake_items_db:
        raise HTTPException(status_code=404, detail="Item not found")
    return {"name": fake_items_db[item_id]["name"], "item_id": item_id}


@router.put(
    "/{item_id}",
    tags=["custom"],#single operation tag, added to the commmon tags
    responses={403: {"description": "Operation forbidden"}},#single operation response
)
async def update_item(item_id: str):
    if item_id != "plumbus":
        raise HTTPException(
            status_code=403, detail="You can only update the item: plumbus"
        )
    return {"item_id": item_id, "name": "The great Plumbus"}
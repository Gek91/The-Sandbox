
#pydantic models

from typing import Union
from pydantic import BaseModel


class ItemBase(BaseModel):
    title: str
    description: Union[str, None] = None


class ItemCreate(ItemBase):
    pass


class Item(ItemBase):
    id: int
    owner_id: int

    #Pydantic configurations
    class Config:
        orm_mode = True




class UserBase(BaseModel):
    email: str


class UserCreate(UserBase):
    password: str


class User(UserBase):
    id: int
    is_active: bool
    items: list[Item] = []

    #Pydantic configurations
    class Config:
        #Tell the pydantic model to read the data even if it is not a dict  -> data.id instead of data["id"]
        #Useful to resolve SQLAlchemy lazy loading relationships, whey will be resolved when the model is popolated from the orm 
        orm_mode = True 
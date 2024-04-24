

from typing import Union

from pydantic import BaseModel


class Item(BaseModel):
    name: Union[str, None] = None
    description: Union[str, None] = None
    price: Union[float, None] = None
    tax: float = 10.5
    tags: list[str] = []


def main():
    item = Item(name="name", description="description", price=100.0)
    #to dict
    print(item.model_dump())
    print(item.model_dump(exclude_unset=True)) #also exclude_none and exclude_default

    #copy
    copy = item.model_copy()
    print(copy)
    
    update_data = item.model_dump(exclude_unset=True)
    updated_item = item.model_copy(update=update_data) #copy and override value with update_data dict

if __name__ == '__main__':
    main()
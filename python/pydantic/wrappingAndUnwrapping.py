from pydantic import BaseModel, EmailStr
from typing import Union


class UserIn(BaseModel):
    username: str
    password: str
    email: EmailStr
    full_name: Union[str, None] = None


class UserOut(BaseModel):
    username: str
    email: EmailStr
    full_name: Union[str, None] = None


def main():

    user_in = UserIn(username="john", password="secret", email="john.doe@example.com")
    #crate a dictionary of the model
    user_dict = user_in.dict()
    print(user_dict)
    #use the dictionary to populate the model
    user_out = UserOut(**user_dict)
    print(user_out)

if __name__ == '__main__':
    main()
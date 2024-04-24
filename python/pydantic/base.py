from datetime import datetime
from typing import Union

from pydantic import BaseModel


# declaration of the data class with attributes and their types
class User(BaseModel):
    id: int
    name: str = "John Doe"
    signup_ts: Union[datetime, None] = None
    friends: list[int] = []

#Creating a new istances of the class it will validate the values and convert them to appropriate type
def main():
    external_data = {
        "id": "123",
        "signup_ts": "2017-06-01 12:22",
        "friends": [1, "2", b"3"],
    }
    user = User(**external_data)

    print(user)

if __name__ == '__main__':
    main()

from typing import Annotated, Union
from fastapi import Depends, FastAPI, HTTPException, Path
from fastapi.params import Header
from pydantic import BaseModel, EmailStr


async def verify_token(x_token: Annotated[str, Header()]):
    if x_token != "fake-super-secret-token":
        raise HTTPException(status_code=400, detail="X-Token header invalid")


async def verify_key(x_key: Annotated[str, Header()]):
    if x_key != "fake-super-secret-key":
        raise HTTPException(status_code=400, detail="X-Key header invalid")
    return x_key #ignored if used in dependencies, used otherwise


#In some cases i't necessary to add dependencies to all the path operations in the application
app = FastAPI(dependencies=[Depends(verify_token), Depends(verify_key)])


class User(BaseModel):
    id: int
    username: str
    password: str

class UserRepository():
        
    fake_user_db = [{"id" : 1, "username": "us1", "password" : "pass1"}, 
                     {"id" : 2, "username": "us2", "password" : "pass2"},
                     {"id" : 3, "username": "us3", "password" : "pass3"}]
    

    def get_user(self, id: int) -> User:
        users = [user for user in UserRepository.fake_user_db if user["id"] == id] 
        if users.__len__() == 1:
            return users[0]
        else: 
            return None
        
        

def get_user_from_path(
        user_id: int = Annotated[int, Path()], 
        #Subdependencies don't have the annotated notation
        user_repository: UserRepository = Depends()) -> User : #example of DI with class (it can also be UserRepository = Depends(UserRepository))
    return user_repository.get_user(user_id)


#DI applied by Dependes keyword, parameter of dependes it's a callable entity (function or also a constructor)
#FastAPI will analyze the parameters of the callable dependencies and precess them in the same way as the parameters for a path operation function
@app.get("/users/{user_id}")
def get_user(user: Annotated[User, Depends(get_user_from_path)]): #Example of DI with function
    return user



#In case it's not needed a return value but only the execution of the dependency
#it will be used the field dependecie on the path operation decorator
@app.get("/items/", dependencies=[Depends(verify_token), Depends(verify_key)])
async def read_items():
    return [{"item": "Foo"}, {"item": "Bar"}]


from fastapi import FastAPI
from enum import Enum


# Create a FastAPI app
app = FastAPI()

#Enum class, inherit from str and enum. 
#Inheriting from str the api docs will be apble to know that the values must be of type string
class ModelName(str, Enum):
    alexnet = "alexnet"
    resnet = "resnet"
    lenet = "lenet"

@app.get("/models/{model_name}")
def get_model(model_name: ModelName):

    if model_name is ModelName.alexnet:
        return {"model_name": model_name, "message": "Deep Learning FTW!"}

    if model_name.value == "lenet":
        return {"model_name": model_name, "message": "LeCNN all the images"}

    return {"model_name": model_name, "message": "Have some residuals"}
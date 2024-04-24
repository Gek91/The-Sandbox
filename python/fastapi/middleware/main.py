import time

from fastapi import FastAPI, Request

#A middleware is a function that works with every reqyest before it is processd by any specific path operation 
#or with every response before returning it


#A middleware function receives a request, a taht that will receive the request as parameters and passes the request to the path operation
#finally can modify the response returned by by the path operation


app = FastAPI()

#middleware example
@app.middleware("http")
async def add_process_time_header(request: Request, call_next):
    start_time = time.time()
    response = await call_next(request) #pass the request on
    process_time = time.time() - start_time
    response.headers["X-Process-Time"] = str(process_time)
    return response

@app.get("/")
def read_root():
    return {"Hello": "World"}
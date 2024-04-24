
from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware

#CORS managed using CORSMiddleware
#Restricted by default, so it's needed to specifyu origins, methods or header in order to be permitted in a Cross-Domain context

app = FastAPI()

origins = [
    "http://localhost.tiangolo.com",
    "https://localhost.tiangolo.com",
    "http://localhost",
    "http://localhost:8080",
]

app.add_middleware(
    CORSMiddleware,
    allow_origins=origins,
    allow_credentials=True,#normally is False, no cookies supported
    allow_methods=["*"],
    allow_headers=["*"], #normally only Accept, Accpet-Language, Content-Language and Content-Type are allowed
)
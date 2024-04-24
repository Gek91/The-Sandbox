from typing import Annotated, Union
from fastapi import BackgroundTasks, Depends, FastAPI


#It's possibile to define background task to be run after returning a response

app = FastAPI()

#Function executed in the task
def write_notification(email: str, message=""):
    with open("log.txt", mode="w") as email_file:
        content = f"notification for {email}: {message}"
        email_file.write(content)


#Pass BackgroundTask object tp the path operation function    
@app.post("/send-notification/{email}")
async def send_notification(email: str, background_tasks: BackgroundTasks):
    #Pass the function and its parameter to the BackgroundTask object
    background_tasks.add_task(write_notification, email, message="some notification")
    return {"message": "Notification sent in the background"}


#Backgroundtask can be defined also in the DI system

def write_log(message: str):
    with open("log.txt", mode="a") as log:
        log.write(message)


def get_query(background_tasks: BackgroundTasks, q: Union[str, None] = None):
    if q:
        message = f"found query: {q}\n"
        background_tasks.add_task(write_log, message)
    return q


@app.post("/send-notification-log/{email}")
async def send_notification_write_log(
    email: str, background_tasks: BackgroundTasks, q: Annotated[str, Depends(get_query)]
):
    message = f"message to {email}\n"
    background_tasks.add_task(write_log, message)
    return {"message": "Message sent"}
from rich.progress import track
import time

def execute():
    for i in track(range(20), description="For example:"):
        time.sleep(0.10)


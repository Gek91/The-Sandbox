import os
import sys
import time
from rich.progress import track


print(os.path.expanduser("~"))

#to pass argument
#print(" ".join(sys.argv[1:]))

print("Hello world")

for i in track(range(20), description="For example:"):
    time.sleep(0.05)


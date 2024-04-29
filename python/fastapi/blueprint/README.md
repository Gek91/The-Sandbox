Bluepring of FastApI project


- app: upper folder
    - main.py
    - dependencies.py: contains dependencies used in several places of the application
    - routers: every file in this folder contains the paht opertions related to specific entity separated from the rest of the code to keep it organized
        - items.py
        - users.py
    - internal
        - admin.py

Every folder contains a __init__.py file that makes it a python module


run with
<code>python3 -m uvicorn app.main:app --reload</code>
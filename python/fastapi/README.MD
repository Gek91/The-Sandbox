
To install fastapi package e the web server uvicorn<br>
<code>pip install fastapi</code><br>
<code>pip install "uvicorn[standard]"</code><br>

to run the examples<br>
<code>uvicorn main:app --reload</code><br> or <code>python -m uvicorn main:app --reload</code><br>
the command refers to
- main: file main.py (python module)
- app: object created inside the main.py with <code>app = FastAPI()</code>
- <code>--reload</code> restart the server after code changes (to be used only in development)

To check on the browser, the base path of the webserver is: <code>http://127.0.0.1:8000/<code><br>
Api docs: <code> http://127.0.0.1:8000/docs</code> or <code>http://127.0.0.1:8000/openapi.json</code>
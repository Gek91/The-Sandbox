import contextlib

from starlette.applications import Starlette
from starlette.routing import Mount
from starlette.middleware.cors import CORSMiddleware
from mcp.server.fastmcp import FastMCP

echo_mcp = FastMCP(name="EchoServer", stateless_http=True)

@echo_mcp.tool()
def echo(message: str) -> str:
    """A simple echo tool"""
    return f"Echo: {message}"


math_mcp = FastMCP(name="MathServer", stateless_http=True)

@math_mcp.tool()
def add_two(n: int) -> int:
    """Tool to add two to the input"""
    return n + 2


# Create a combined lifespan to manage both session managers
@contextlib.asynccontextmanager
async def lifespan(app: Starlette):
    async with contextlib.AsyncExitStack() as stack:
        await stack.enter_async_context(echo_mcp.session_manager.run())
        await stack.enter_async_context(math_mcp.session_manager.run())
        yield

# Note: Clients connect to http://localhost:8000/echo/mcp and http://localhost:8000/math/mcp
# To mount at the root of each path (e.g., /echo instead of /echo/mcp):
# echo_mcp.settings.streamable_http_path = "/"
# math_mcp.settings.streamable_http_path = "/"
app = Starlette(
    routes=[
        Mount("/echo", echo_mcp.streamable_http_app()),
        Mount("/math", math_mcp.streamable_http_app()),
    ],
    lifespan=lifespan,
)

starlette_app = CORSMiddleware(
    app,
    allow_origins=["*"],  # Configure appropriately for production
    allow_methods=["GET", "POST", "DELETE"],  # MCP streamable HTTP methods
    expose_headers=["Mcp-Session-Id"],
)
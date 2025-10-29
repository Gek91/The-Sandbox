import asyncio
import os
from mcp.server.fastmcp import FastMCP
import logging

logger = logging.getLogger(__name__)
logging.basicConfig(format="[%(levelname)s]: %(message)s", level=logging.INFO)

port = int(os.getenv("PORT", 8080))
mcp = FastMCP(name="EchoServer", host="0.0.0.0", port=port)

@mcp.tool()
def echo(message: str) -> str:
    """A simple echo tool"""
    return f"Echo: {message}"

if __name__ == "__main__":
    logger.info(f"🚀 MCP server started on port {port}")
    asyncio.run(
        mcp.run(transport="streamable-http")
    )
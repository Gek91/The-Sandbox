
from google.adk.agents import LlmAgent
from google.adk.tools.mcp_tool.mcp_toolset import MCPToolset
from google.adk.tools.mcp_tool.mcp_session_manager import SseServerParams


def get_tools():
    return MCPToolset(
        connection_params=SseServerParams(url="http://localhost:8001/server/mcp")
    )

root_agent = LlmAgent(
    name="mcpAgent",
    model="gemini-2.0-flash",
    description=(
        "Agent that uses a mcp server"
    ),
    instruction=(
        #"Use the 'add' to add two numbers and the resource 'greeting://{name}' to get a personalized greeting."
        "greet people"
    ),
    tools=[get_tools()],
)
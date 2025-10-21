from google.adk.agents.remote_a2a_agent import AGENT_CARD_WELL_KNOWN_PATH
from google.adk.agents.remote_a2a_agent import RemoteA2aAgent

root_agent = RemoteA2aAgent(
    name="hello_world_agent",
    description=(
        "Helpful assistant"
    ),
    agent_card=f"http://localhost:9999{AGENT_CARD_WELL_KNOWN_PATH}",
)
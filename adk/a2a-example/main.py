from google.adk.a2a.utils.agent_to_a2a import to_a2a
from a2a.types import AgentCard
from simple_agent.agent import root_agent
import uvicorn

#Needed to load .env launching application from main.py instead of adk web
from dotenv import load_dotenv
load_dotenv()

def main():

    # Make your agent A2A-compatible
    #Autogenerate agent card
    a2a_app = to_a2a(root_agent, port=9999)

    #it's possible generate the agent card 
    #my_agent_card = AgentCard(
    #    "name": "file_agent",
    #    "url": "http://example.com",
    #    "description": "Test agent from file",
    #    "version": "1.0.0",
    #    "capabilities": {},
    #    "skills": [],
    #    "defaultInputModes": ["text/plain"],
    #    "defaultOutputModes": ["text/plain"],
    #    "supportsAuthenticatedExtendedCard": False
    #)
    #a2a_app = to_a2a(root_agent, port=8001, agent_card=my_agent_card)

    # Run the A2A server
    uvicorn.run(a2a_app, host='0.0.0.0', port=9999)


if __name__ == "__main__":
    main()
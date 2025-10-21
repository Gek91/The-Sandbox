from google.adk.sessions import InMemorySessionService
from google.adk.artifacts import InMemoryArtifactService 
from google.adk.runners import Runner
from google.genai import types
import json
from custom_agent.agent import root_agent as trip_agent
import asyncio
from dotenv import load_dotenv
from google.adk.plugins.base_plugin import BasePlugin
from google.adk.agents.base_agent import BaseAgent
from google.adk.agents.callback_context import CallbackContext
from google.adk.models.llm_request import LlmRequest

#Plugin
class CountInvocationPlugin(BasePlugin):

    def __init__(self, name):
        super().__init__(name="count_invocation")
        self.agent_count: int = 0
        self.tool_count: int = 0
        self.llm_request_count: int = 0

    async def before_agent_callback(self, *, agent: BaseAgent, callback_context: CallbackContext) -> None:
        self.agent_count += 1
        print(f"[Plugin] Agent run count: {self.agent_count}")

    async def before_model_callback(self, *, callback_context: CallbackContext, llm_request: LlmRequest) -> None:
        self.llm_request_count += 1
        print(f"[Plugin] LLM request count: {self.llm_request_count}")


#programmatic runner

async def main():
    session_service = InMemorySessionService()
    artifact_service = InMemoryArtifactService()
    session = await session_service.create_session(
        app_name="app", 
        user_id="user", 
        session_id="session",
          state={})
    

    runner = Runner(
        agent=trip_agent, # Pass the custom orchestrator agent
        app_name="app",
        session_service=session_service,
        artifact_service=artifact_service,
        plugins=[CountInvocationPlugin()],
    )

    ####

    current_session = await session_service.get_session(app_name="app", 
                                                  user_id="user", 
                                                  session_id="session")
    
    if not current_session:
        return
    
    print("Initial Session State:")
    print(json.dumps(current_session.state, indent=2))
    print("-------------------------------\n")

    content = types.Content(role='user', parts=[types.Part(text="Canada in september")])
    events = runner.run_async(user_id="user", session_id="session", new_message=content)

    final_response = "No final response captured."
    async for event in events:
        if event.is_final_response() and event.content and event.content.parts:
            final_response = event.content.parts[0].text

    print("\n--- Agent Interaction Result ---")
    print("Agent Final Response: ", final_response)

    final_session = await session_service.get_session(app_name="app", 
                                                  user_id="user", 
                                                  session_id="session")
    print("Final Session State:")
    print(json.dumps(final_session.state, indent=2))
    print("-------------------------------\n")

load_dotenv()
asyncio.run(main())

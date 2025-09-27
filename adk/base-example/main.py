from google.adk.sessions import InMemorySessionService
from google.adk.runners import Runner
from google.genai import types
import json
from custom_agent.agent import root_agent as trip_agent
import asyncio
from dotenv import load_dotenv


#programmatic runner

async def main():
    session_service = InMemorySessionService()
    session = await session_service.create_session(
        app_name="app", 
        user_id="user", 
        session_id="session",
          state={})

    runner = Runner(
        agent=trip_agent, # Pass the custom orchestrator agent
        app_name="app",
        session_service=session_service
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

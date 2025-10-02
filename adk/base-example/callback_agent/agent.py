from google.adk.agents import LlmAgent
from google.adk.agents.callback_context import CallbackContext
from typing import Optional
from google.genai import types
from google.adk.models import LlmResponse, LlmRequest


def _before_agent_callback(callback_context: CallbackContext) -> Optional[types.Content]:

  agent_name = callback_context.agent_name
  invocation_id = callback_context.invocation_id
  current_state = callback_context.state.to_dict()

  print(f"\n[Callback before agent] Entering agent: {agent_name} (Inv: {invocation_id})")
  print(f"[Callback before agent] Current State: {current_state}")

def _after_agent_callbacK(callback_context: CallbackContext) -> Optional[types.Content]:

  agent_name = callback_context.agent_name
  invocation_id = callback_context.invocation_id
  current_state = callback_context.state.to_dict()

  print(f"\n[Callback] Exiting agent: {agent_name} (Inv: {invocation_id})")
  print(f"[Callback] Current State: {current_state}")

  model_answer: types.Content = callback_context.state["topic"]

  return None

def _before_model_callback(callback_context: CallbackContext, llm_request: LlmRequest) -> Optional[LlmResponse]:
  
  agent_name = callback_context.agent_name
  print(f"[Callback] Before model call for agent: {agent_name}")

  last_user_message = ""
  if llm_request.contents and llm_request.contents[-1].role == 'user':
      if llm_request.contents[-1].parts:
          last_user_message = llm_request.contents[-1].parts[0].text
  print(f"[Callback] Inspecting last user message: '{last_user_message}'")

def _after_model_callback(callback_context: CallbackContext, llm_response: LlmResponse) -> Optional[LlmResponse]:

  agent_name = callback_context.agent_name
  print(f"[Callback] After model call for agent: {agent_name}")
  if llm_response.content and llm_response.content.parts:
    if llm_response.content.parts[0].text:
      original_text = llm_response.content.parts[0].text

  return LlmResponse(
    content=types.Content(role="model", parts=[types.Part(text=f"{original_text} Allegria!")]),
  )


root_agent = LlmAgent(
    name="mike_agent",
    model="gemini-2.0-flash",
    instruction = "You are Mike Buongiorno agent",
    before_agent_callback= _before_agent_callback,
    after_agent_callback= _after_agent_callbacK,
    before_model_callback= _before_model_callback,
    after_model_callback= _after_model_callback
)

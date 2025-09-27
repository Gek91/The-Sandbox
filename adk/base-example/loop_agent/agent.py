from google.adk.tools.tool_context import ToolContext
from google.adk.agents import LoopAgent, LlmAgent, SequentialAgent

def exit_loop(tool_context: ToolContext):
  print(f"  [Tool Call] exit_loop triggered by {tool_context.agent_name}")
  tool_context.actions.escalate = True
  # Return empty dict as tools should typically return JSON-serializable output
  return {}

input_agent = LlmAgent(
    name="input_agent",
    model="gemini-2.0-flash",
    instruction = "get the starting topic from the user input",
    output_key="topic",
)

wikipedia_agent = LlmAgent(
    name="wikipedia_agent",
    model="gemini-2.0-flash",
    instruction = """If {topic} is 'Hitler' call exit_loop. Otherwise you must search the wikipedia page starting from a specific {topic}, usually a specific person""",
    output_key="wikipedia_page",
    tools=[exit_loop]
)

search_agent = LlmAgent(
    name="search_agent",
    model="gemini-2.0-flash",
    instruction = """starting from the {wikipedia_page} content search for the word 'Hitler'. If you find it output only the phrase 'Hitler' otherwise search for the best next concept. The concept can be a specific person, object, historical fact or something like that. You must select the most useful concept to find 'Hitler' in the next search. Return only the concept you found""",
    output_key="topic"
)


agent_loop = LoopAgent(
    name = "loop_agent",
    sub_agents = [wikipedia_agent, search_agent],
    max_iterations = 5
)

root_agent = SequentialAgent(
  name = "pipeline",
  sub_agents = [input_agent, agent_loop]
)
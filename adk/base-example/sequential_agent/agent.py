import datetime
from zoneinfo import ZoneInfo
from google.adk.agents import LlmAgent,SequentialAgent

get_content_agent = LlmAgent(
    name="get_page_content_agent",
    model="gemini-2.0-flash",
    description=(
        "Agent that retrieve a web page content."
    ),
    instruction=(
        "You are an agent that retrieve the content of a webpage starting from the url provided."
    ),
    output_key="content"
)

summarize_agent = LlmAgent(
    name="summarize_agent",
    model="gemini-2.0-flash",
    description=(
        "Agent that summarize the content."
    ),
    instruction=(
        "You are an agent that summarize the content passed as input in {content}"
    ),
    output_key="summary"
)

pipeline_agent = SequentialAgent(
    name="pipeline_agent",
    sub_agents=[get_content_agent, summarize_agent],
    description="implement the get content and summarize pipeline"
)

root_agent = pipeline_agent

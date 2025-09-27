
from google.adk.agents import LlmAgent, ParallelAgent, SequentialAgent
from pydantic import BaseModel, Field


class Warrior(BaseModel):
    name: str = Field(description="warrior name")
    strength: int = Field(description="warrior strength")
    agilty: int = Field(description="warrior agilty")
    intelligence: int = Field(description="warrior intelligence")


def get_warrior_agent(output_key: str) :
    return LlmAgent(
        name="weather_time_agent",
        model="gemini-2.0-flash",
        description=(
            "Agent that generate a warrior"
        ),
        instruction=(
            "Generate a new warrior with a cool name and with strenght, agility and intelligence characteristics values, randomize that value having their sum equals to 100"
        ),
        output_schema=Warrior,
        output_key=output_key
    )

first_warrior = get_warrior_agent("first_warrior")
second_warrior = get_warrior_agent("second_warrior")
third_warrior = get_warrior_agent("third_warrior")

parallel_agent = ParallelAgent(
    name = "parallel_agent",
    sub_agents=[first_warrior, second_warrior, third_warrior]
)

battle_agent =  LlmAgent(
    name="weather_time_agent",
    model="gemini-2.0-flash",
    description=(
        "Agent that make warrior fight against each other"
    ),
    instruction=(
        "Given {first_warrior}, {second_warrior} and {third_warrior}, make them fight against each other and give me the winner of the battle"
    
    ),
    output_schema=Warrior,
)

root_agent = SequentialAgent(
    name="pipeline",
    sub_agents=[parallel_agent, battle_agent]
)


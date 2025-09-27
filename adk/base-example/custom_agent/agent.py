from google.adk.agents import LlmAgent, BaseAgent, LlmAgent
from google.adk.agents.invocation_context import InvocationContext
from typing_extensions import override
from google.adk.events import Event
from typing import AsyncGenerator

#Custom agent
class CustomAgent(BaseAgent):
    
    check_season: LlmAgent
    alternative_generator: LlmAgent
    trip_generator: LlmAgent

    #Agent initialization
    def __init__(self, name, check_season, alternative_generator, trip_generator):
        
      sub_agents_list = [
          check_season,
          alternative_generator,
          trip_generator,
      ]

      super().__init__(
          name = name,
          check_season = check_season,
          alternative_generator = alternative_generator,
          trip_generator = trip_generator,
          sub_agents = sub_agents_list)
      
    @override
    async def _run_async_impl(self, ctx: InvocationContext) -> AsyncGenerator[Event, None]:
       
      async for event in self.check_season.run_async(ctx):
        yield event

      if "season_check" not in ctx.session.state or not ctx.session.state["season_check"]:
        #fail
        return 
      
      if ctx.session.state["season_check"] == "true":
        async for event in self.trip_generator.run_async(ctx):
          yield event
      else :
        async for event in self.alternative_generator.run_async(ctx):
          yield event

        
check_season_agent = LlmAgent(
    name = "check_season_agent",
    model="gemini-2.0-flash",
    instruction = "check if the actual season is the right season to have a trip in the place indicated. Respond only with 'true' or 'false'",
    output_key = "season_check"
)    

alternative_generator_agent = LlmAgent(
    name = "alternative_generator_agent",
    model="gemini-2.0-flash",
    instruction = "Propose a different place to have a trip based on the actual season"
)  


trip_generator_agent = LlmAgent(
  name="trip_generator_agent",
  model="gemini-2.0-flash",
  instruction = "Generate a very simple trip for the place indicated, just a breif list of places"
)

root_agent = CustomAgent(
  name = "custom_agent",
  alternative_generator=alternative_generator_agent,
  check_season=check_season_agent,
  trip_generator=trip_generator_agent
)

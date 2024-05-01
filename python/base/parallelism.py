
#define that inside the function i has to be aware of wait expression, and it can pause the execution of the function to go something else 
async def get_burgers(number: int):
    # Do some asynchronous stuff to create the burgers
    return burgers

def get_sequential_burgers(number: int):
    # Do some sequential stuff to create the burgers
    return burgers

#Await can be used only inside a function declared as async
#async function return a coroutine, a function that it can start and et ends at some point, but can might be pused if there is an await inseide
async def main() :
#tells to wait for the function to finish before storing the result
    burgers = await get_burgers(2)


#Needed for generic argument if python version <3.9
#from typing import List

#type hinting of function parameters, not the same as declaring default values
def get_full_name(first_name: str, last_name: str):
    full_name = first_name.title() + " " + last_name.title()
    return full_name

#support for type parameter in arguments of collection
def process_items(items: list[str]):
    for item in items:
        print(item)

def main() : 
    print(get_full_name("gino", "gini"))
    process_items("gino", "gini", "ginetti")

if __name__ == '__main__':
    main()

from functools import partial

def sum(x,y):
    return x + y

#you can achive the same with closure!?
def main():
   
    partialsum3 = partial(sum, 3)

    print(partialsum3(2))
    print(partialsum3(4))


if __name__ == '__main__':
    main()
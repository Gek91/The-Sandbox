from functools import reduce


def main():

    #reduce(func, iterable, [initial])
    # reduce iteable at single value
    # return a value
    numbers = [3, 4, 6, 9, 34, 12]
    print(reduce(lambda x,y : x+y, numbers))
    #initial value
    print(reduce(lambda x,y : x+y, numbers, 10))

    

if __name__ == '__main__':
    main()
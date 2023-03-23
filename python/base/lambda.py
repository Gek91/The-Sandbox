


def main():

    #syntax -> name = inputs : function
    sum = lambda x,y : x + y

    print(sum(1,2))

    #useful with closure
    value = 5
    nplus5 = lambda n : value + n #can reference variable in enclosing scope (closure)

    print(nplus5(10))


    # map -> map(func, *iterable)
    # number of iterable elements base on func argument -> str.upper need only one argument -> only one iterable parameter passed
    # return an iterable
    pets = ['alfred', 'tabitha', 'william', 'arla']
    upperedpets = list(map(str.upper, pets))
    print(upperedpets)

    #map with more iterable argument
    circle_areas = [3.56773, 5.57668, 4.00914, 56.24241, 9.01344, 32.00013]
    #round need 2 parameters -> 2 iterable as argument of map
    result = list(map(round, circle_areas, range(1, 7)))
    print(result)

    #map with lambda
    print(list(map(lambda x : x + "!" , pets)))


    # filter -> filter(func, iterable) 
    # func must return a boolean, only one iterable
    # return an iterable filtered by the passed func result
    scores = [66, 90, 68, 59, 76, 60, 88, 74, 81, 65]
    print(list(filter(lambda x : x > 75, scores)))

if __name__ == '__main__':
    main()
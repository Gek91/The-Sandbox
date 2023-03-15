


def main():

    #syntax -> name = inputs : function
    sum = lambda x,y : x + y

    print(sum(1,2))

    #useful with closure
    value = 5
    nplus5 = lambda n : value + n #can reference variable in enclosing scope (closure)

    print(nplus5(10))


if __name__ == '__main__':
    main()
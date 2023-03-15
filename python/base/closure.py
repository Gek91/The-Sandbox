
def outerReadOnly(value):
    print("outer")
    print(value)

    def innerFun():
        print(value)
        print("inner")

    innerFun()

def outerFun(value):
    print("outer")
    print(value)

    def innerFun():
        nonlocal value #needed to increase
        value = value+1
        print(value)
        print("inner")

    innerFun()
    print(value)


def counter(start):

    print('starting with %s' % start)


    def increase():
        nonlocal start
        start = start +1
        print(start)
        return start
    
    return increase

def main():
   
    outerReadOnly(1)
    outerFun(1)
   
    counterfun = counter(3)

    counterfun()
    counterfun()

if __name__ == '__main__':
    main()
# Defines a "repeat" function that takes 2 arguments.
def repeat(s, exclaim):

    result = s + s + s # can also use "s * 3" which is faster (Why?)
    if exclaim:
        result = result + '!!!'
    return result

def varargsfunction(first, second, *others): 
    print("First: %s" %(first))
    print("Second: %s" %(second))
    print("And all the rest... %s" %(list(others)))

def varkwargsfunction(first, second, **others) :
    print("First: %s" %(first))
    print("Second: %s" %(second))
    print("And all the rest... %s" %(others))


def main():
    print(repeat('Yay', False))      ## YayYayYay
    print(repeat('Woo Hoo', True))   ## Woo HooWoo HooWoo Hoo!!!

    #unpacking operators
    #varagrs function
    varargsfunction(1,2)
    varargsfunction(1,2,3,4,5)

    #var kwargs -> varargs like a dictionary
    varkwargsfunction(1,2)
    varkwargsfunction(1,2, a=1, b=2, c=3)

    #unpaking
    mylist = [1,2,3]
    varargsfunction(*mylist)

    anotherlist = [4,5]
    varargsfunction(*mylist,*anotherlist)    

if __name__ == '__main__':
    main()
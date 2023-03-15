

def main():
   
    mystring = 'string'
    myfloat = 0.7
    myint = 1
    myNone = None

    print(isinstance(mystring, str))
    print(isinstance(myfloat, str))
    print(isinstance(myint, str))
    print(isinstance(myfloat, float))
    print(isinstance(myint, float))
    print(isinstance(myint, int))

    print(isinstance(myNone, str))
    print(isinstance(myNone, float))
    print(isinstance(myNone, int))

if __name__ == '__main__':
    main()
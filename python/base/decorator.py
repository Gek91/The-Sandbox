

def mysimpledecorator(inputfunc):
        def wrapper():
            print("before")
            inputfunc()
            print("after")
        return wrapper

def mydecoratorwithargs(inputfunc):
    def wrapper(*args, **kwargs): #args kwargs needed
        print("before")
        inputfunc(*args, **kwargs) #args kwargs needed
        print("after")
    return wrapper

def mydecoratorwithreturn(inputfunc):
    def wrapper():
        print("before")
        return inputfunc() #needed
    return wrapper


@mysimpledecorator #is like -> myfunc = mysimpledecorator(myfunc)
def myfunc() :
     print("do something)")

@mydecoratorwithargs
def myfunctwithargs(args) :
    print(args)

@mydecoratorwithreturn
def myfuncwithreturn() :
    return "hello"

def main():
   
  myfunc() 

  myfunctwithargs("hello")

  print(myfuncwithreturn())

if __name__ == '__main__':
    main()
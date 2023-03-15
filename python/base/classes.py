
   
class MyClass:

    #constructor
    def __init__(self, input):
        self.instancevariable = input #instance variable

    classvariable = "var" #class variable

    def function(self):
        print("function")



def main():

    myobject = MyClass(1)

    print(myobject.function())
    print(myobject.instancevariable)

    print(myobject.classvariable)
    print(MyClass.classvariable)

    myobject2 = MyClass(2)
    print(myobject2.instancevariable)
    print(myobject2.classvariable)

    #change value on instance
    myobject2.classvariable = "bar" #create a new instance variable named 'classvariable'
    print(myobject2.classvariable) #now instance variable
    print(myobject2.__class__.classvariable) # class variable!
    print(myobject.classvariable) #real class variable
    print(MyClass.classvariable)

    MyClass.classvariable = 5
    print(myobject.classvariable)
    print(MyClass.classvariable)
    print(myobject2.classvariable) #again instance variable
    print(myobject2.__class__.classvariable)

if __name__ == '__main__':
    main()
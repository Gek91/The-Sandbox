fun main(args: Array<String>) {

    var myObject: MyClass = MyClass(10,20);

    val newObject: myImplementation = myImplementation()

    newObject.functionWithDefault()

    println(newObject.value)
    println(newObject.valueWithGetter)


}

//custom accessor
class ClassWithCustomAccessor(inputVariable: Int) {
    val value: Int //can't have setter
        get() = 11 //custom getter
    var variable : Int = inputVariable //need to be populated
        private set(input: Int) { //custom setter with private visibility
            field = input * 2 //field is a special reference to the specific field
        }
        get() = field/2

}

interface myInterface {

    val value: String //abstract field
    val valueWithGetter: String //abstract field, no override needed
            get() = function()

    fun function(): String //abstract
    fun functionWithDefault() = println("default") //default implementation
}

class myImplementation : myInterface {

    override val value = "init" //abstract field implementation definition

    override fun function(): String {
        print("my implementation")
        super<myInterface>.functionWithDefault() //needed in multi interface implementation case
        return "ciao"
    }
}

//class that can be extended, otherwise final is default
open class MyClass (
    val field1: Int, //read only
    var field2: Int //modifiable
)

//subclass define same primary constructor and call superclass primary constructor in it
class MySubclass(field1: Int, field2: Int) : MyClass(field1, field2)

//only secondary constructor
open class ClassWithoutPrimaryConstructor {
    constructor(value: String) { print("primary")}
}

class SubclassWithoutPrimaryConstructor : ClassWithoutPrimaryConstructor {
    constructor(value : String) : this(value, "anotherValue") //same class secondary constructor call
    constructor(value1 : String, value2 : String) : super(value1) //superclass secondary constructor call
}


//final by default
open class Superclass { //class that permits subclasses
    fun finalFun() = print("final") //no override
    open fun openFun() = print("open") //permits override
}

//nested class
class outerClass {

    class staticInnerClass { //inner class static by default

    }

    inner class innerClass { //inner class with outerclass reference
        fun getOuterReference() : outerClass = this@outerClass; //outer class reference syntax
    }
}

//sealed class, no subclass outside the class itself
sealed class Expr {
    class Num(val Value: Int) : Expr()
    class Sum(val left: Expr, val right: Expr) : Expr()
}

fun eval(e: Expr) =
    when(e) {
        is Expr.Num -> "is Num"
        is Expr.Sum -> "is Sum"
        //else //not needed thanks to sealed
    }


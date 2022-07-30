fun main(args: Array<String>) {

    //java 8 static syntax, reference to the singleton object
    MyClassObject.value;

    //companion object method invocation
    A.function()

    //outerclass of implementing companion object became implementor of the interface
    callingInterface(A)

    //object expression, used to define anonymous class
    var anonymous = object {
        var a :Int = 10

        fun function() {
            print(a)
        }
    }
}

//object declaration, define a class and a singleton instance in the same time
// no constructor
object MyClassObject {

    val value = 10
}

interface MyInterface {
    fun function()
}

//companion object
class A (){
    companion object : MyInterface {

        override fun function() {
            print("override")
        }
    }
}

fun callingInterface (obj: MyInterface) {
    obj.function()
}
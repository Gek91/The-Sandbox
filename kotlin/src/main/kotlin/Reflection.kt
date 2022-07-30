import kotlin.reflect.KClass
import kotlin.reflect.KFunction

fun main(args: Array<String>) {

    var kclass : KClass<MyReflectionClass> = MyReflectionClass::class
    val value : MyReflectionClass = MyReflectionClass("ciao", 1)
    kclass = value.javaClass.kotlin

    println(kclass.simpleName)
    kclass.members.forEach( { println(it.name)})

    //Kcallable collection, call invoke function and properties getter
    kclass.members.forEach( { it.call() })

    //call a function with reflection
    val kFunction = ::foo
    //use Kcallable call method to invoke the function, pass argument with varargs list
    kFunction.call(42)

    //more specific type KFunctionN and use of invoke method of kFunction interface
    //KFunction2<first parameter, second parameter, type of return>
    val kFunction2 = ::mySum
    //invoke takes the exact number of parameters
    println(kFunction2.invoke(1,2))

    var counter = 0
    val kProperty = value::stringVal
    //method getter with Kcallable interace
    println(kProperty.call())
    kProperty.getter.call()
    //call setter, require varargs
    kProperty.setter.call("hello")

    //getter property with Kproperty interface
    kProperty.get()
    //setter property with kproperty interface
    kProperty.set("salut")

}

class MyReflectionClass(var stringVal : String, val intVal: Int) {

    fun hello() {
        println("hello")
    }
}

fun foo(x:Int) = println(x)

fun mySum(x : Int, y : Int) = x + y

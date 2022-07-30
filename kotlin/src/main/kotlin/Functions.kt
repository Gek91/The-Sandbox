import java.lang.RuntimeException

fun main(args: Array<String>) {

    println( sum(2, 3))

    //using default
    mySum2(1);
    //using named argument
    mySum2(first = 1, second = 2);

    //calling a extension function of String
    print("ciao".lastChar())

    //calling an extension Property
    print("ciao".lastCharProperty)

    varArgFunction(1,2,3)

    //infix call example
    println("ciao".append("bello"))//normal notation
    println("ciao" append "bello") //infix notation

    //local function example
    outerFunction()
}

//function
fun sum(a: Int, b: Int): Int {
    return a + b
}

//inline function declaration
fun inlineSum(a: Int, b: Int): Int = a + b

//named argument and default value
fun mySum2(first: Int, second: Int = 10) = first + second

//extension function of String, add new behaviour to existing class
fun String.lastChar(): Char = this.get(this.length -1)
//extension property, callable like a real class property, it isn't saved on memory
val String.lastCharProperty: Char
    get() = get(length -1)

//varargs example
fun varArgFunction(vararg values: Int) {
    println(values.last().toString());
}

//infix notation
infix fun String.append(value: String) = this + value

//local function example
fun outerFunction() {
    print("start outer")
    fun innerFunction() {
        print("inner")
    }

    innerFunction()
    innerFunction()
    innerFunction()

    print("end outer")
}

//Unit type, like java void
fun unitReturn() : Unit {

   print("unit function return")
    //implict unit return
}

//Nothing Type, function that never return correctly
fun nothingReturn() : Nothing {
    throw RuntimeException()
}
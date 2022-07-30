import java.lang.StringBuilder

fun main(args: Array<String>) {

    //function variable declaration
    val sum = {x: Int, y: Int -> x + y}
    val sumWithExplicitType: (Int, Int) -> Int = sum;
    val action : () -> Unit = {println(42)} //no input and no output
    val nullableFunction : ((Int, Int) -> Int)? = null

    //passing a function as argument
    println("Value".myFilter { it in 'a'..'z' })

    //return other function
    println( buildFunction(1)(2))
    println( buildFunction(-1)(2))
}

//function that take another function as argument
fun String.myFilter(predicate : (Char) -> Boolean) : String {
    val stringBuilder = StringBuilder()

    for(index in 0 until this.length) {
        val element = this.get(index)
        if(predicate(element)) {
            stringBuilder.append(element)
        }
    }
    return stringBuilder.toString()
}

//function tha return another function
fun buildFunction(value : Int) : (Int) -> Int {
    if(value > 0) {
        return {x -> x+1}
    } else {
        return { x -> x-1}
    }
}
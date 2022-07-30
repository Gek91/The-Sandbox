
fun main(args: Array<String>) {
    println("Hello world")

    val a: Int = 1; //read only variables
    val b = 2 //inferred tyope
    var c = 3 //modifiable variable
    c = c +1

    //type check, apply implict smart cast
    a is Int
    //explicit cast
    val d = a as Int

}







fun main(args: Array<String>) {

    strLenSafeCallOperator(null)
    strLenSafeCallOperator("value")

    strLenElvisOperator(null)
    strLenElvisOperator("value")

    //strLenNotNullAssertion(null) //throw excpetion
    strLenNotNullAssertion("value")

    //safe cast
    val string : Any = "string"
    var realString : String?
    realString = string as String //throw an exception if is not a String
    realString = string as? String //return null if is not a String

    //let function

    realString = "value"
    realString?.let { strLen(it) }
    realString = null
    realString?.let { strLen(it) } //no operation, labda is not executed
}

fun strLen(s : String) {
    println(s.length)
}

//nullability check
fun strLenSafe(s: String?) {
    if(s != null) //after you can use the nullable parameter as it's normal
        s.length
    else
        0
}

//safe call operator
fun strLenSafeCallOperator(s: String? ){
    var length : Int? = s?.length //return nullable value
    println(length)
}

//elvis operator
fun strLenElvisOperator(s : String?) {
    println(s?.length ?: 0) //if null return the second parameter
}

fun strLenNotNullAssertion(s : String?) {
    val notNull : String = s!! //throw exception if is it null, remove nullability
    println(notNull.length)
}

//late initialization
class MyTest {
    private lateinit var value : String //no init value allowed

    fun execute() {
        value = "ciao" //init value
        print(value)
    }

}
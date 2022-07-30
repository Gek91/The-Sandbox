import java.util.*

fun main(args: Array<String>) {
    //range, second value included
    val oneToTen = 1..10

    //for on ranges
    for(i in 1..100){
        print(i)
    }
    for(i in 100 downTo 1) { //inverse navigation
        print(i)
    }
    for(i in 1..10 step 2) { //define a navigation step
        print(i)
    }

    val map = TreeMap<String, String>();
    map["1"] = "one"
    map["2"] = "two"
    map["3"] = "three"
    map["4"] = "four"
    for((key, value) in map) {
        print("$key has value $value")
    }

    val list = arrayListOf("1","2","3")
    for ((index, value) in list.withIndex()) {
        print("$index : $value")
    }
}

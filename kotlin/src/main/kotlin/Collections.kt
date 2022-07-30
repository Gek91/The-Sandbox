fun main(args: Array<String>) {

    val list: List<Int> = listOf(1,2,3,4)
    val arrayList: ArrayList<Int> = arrayListOf(1,2,3,4)

    val set: Set<Int> = setOf(1,2,3,4)
    val hashSet: HashSet<Int> = hashSetOf(1,2,3,4)

    val map: HashMap<Int,String> = hashMapOf(1 to "1", 2 to "2", 3 to "3") //to -> tuple operator (infix notation)

    //kotlin collection methods, they are extension method
    list.last(); //last element
    set.maxOrNull() //max value

    //nullability
    var nullableList = ArrayList<Int?>()
    nullableList.add(null)
    nullableList.add(1)
    nullableList.add(2)
    nullableList.add(3)
    println(nullableList)
    println(nullableList.filterNotNull()) //remove not null value

    //mutable initialization
    val mutableList = mutableListOf<Int>(1,2,3,4)
    val mutableSet = mutableSetOf<Int>(1,2,3)
    val mutableMap = mutableMapOf<Int,String>(1 to "1", 2 to "2", 3 to "3")

    //read only interface initialization
    //not immutable!!
    val readOnlyList = listOf<Int>(1,2,3,4)
    val readOnlySet = setOf<Int>(1,2,3)
    val readOnlyMap = mapOf<Int,String>(1 to "1", 2 to "2", 3 to "3")

    //array
    arrayOf(1,2,3)
    arrayOfNulls<Int?>(3)
    Array<String>(3) {i -> ('a' + i).toString()} //size and lambda

    //arrays of primitive
    IntArray(5)
    intArrayOf(1,2,3,4)
    IntArray(5) { i -> i+1}

}

//read only and mutable collection
//Collection can be populated with read only collection
//mutable can be populated only with mutable only conllection
fun <T> copyElements(source: Collection<T>, target: MutableCollection<T>) {
    for(item in source) {
        target.add(item)
    }
}
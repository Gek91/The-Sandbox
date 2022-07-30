import java.lang.StringBuilder

fun main(args: Array<String>) {

    val lambda = { x:Int, y:Int -> x + y}
    println(lambda(1,2))

    val listOfInt = listOf(Person("gigi", 1), Person("mary", 2), Person("lamo", 3))
    //concise syntax
    listOfInt.maxByOrNull({ person:Person -> person.age})
    listOfInt.maxByOrNull() { person:Person -> person.age } //if is the last argument of function
    listOfInt.maxByOrNull { person:Person -> person.age } //if is the only argumento of the function
    listOfInt.maxByOrNull { person -> person.age } //type inference
    listOfInt.maxByOrNull { it.age } //default parameter value


    //member reference
    val ageLabda = Person::age
    println(ageLabda(Person("giani", 5)))

    //reference of top level function
    run(::myfunction) //run syntax used to execute lamdda

    //constructor reference
    val createPerson = ::Person //member reference to constructor
    val p: Person = createPerson("bilo", 6)

    //labda on collections
    val list = listOf(1,2,3,4)
    list.filter { it > 2 } //filter
    list.map { it * 2 } //map

    val map = mapOf(0 to "zero", 1 to "one")
    println(map.mapKeys { it.key * 2 })
    println(map.mapValues { it.value +"!" })

    println(list.all { it < 3 }) //predicate true for all
    println(list.any {it < 3 }) //predicate true for any
    println(list.count {it < 3 }) //count for how many is true
    println(list.find {it < 3 }) //find de first that is true

    val stringList : List<String> = listOf("a", "b", "ab", "ba")
    println(stringList.groupBy { it -> it.first() })

    val books = listOf(
        Book("title1", listOf("author1", "author2")),
        Book("title1", listOf("author2", "author3")),
        Book("title1", listOf("author1", "author4"))
    )

    println(books.flatMap { it.authors }.toSet())
    //first transform each element to collection then combines the collection into one
    val strings = listOf("abc", "def")
    println(strings.flatMap { it.toList() })


    //interact with functional interface
    useFunctionalInterface( { println("labda") })

    returnFunctionInterface()

    //with function, first argument became receiver of the defined lambda as second argument
    val stringBuilder = StringBuilder()
    val stringBuilderValue = with(stringBuilder, {
        this.append("value")
        this.toString() //return, last operation
    })

    //apply function, same logic of with but return the receiver object
    StringBuilder().apply {
        this.append("value")
    }.toString()
}

fun myfunction() {
    print("ciao")
}

class Person(val name: String, val age: Int)

class Book(val title:String, val authors: List<String>)

//functional interface example
fun useFunctionalInterface(obj: MyFunctionalInterface){

    obj.myFunctionalInterfaceMethod()
}

fun returnFunctionInterface() : MyFunctionalInterface {
    return MyFunctionalInterface { println("labda") }
}

@FunctionalInterface
fun interface MyFunctionalInterface {
    fun myFunctionalInterfaceMethod()
}
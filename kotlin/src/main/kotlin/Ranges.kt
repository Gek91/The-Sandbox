fun main() {

    var simpleRange: IntRange = 1..10
    print(simpleRange)
    var simpleRange2: CharRange = 'a'..'z'
    print(simpleRange2)

    val rangeWithStep: IntProgression = 1..10 step 2
    print(rangeWithStep)
    val invertedRange: IntProgression = 10 downTo 1
    print(invertedRange)
    val untilRange: IntRange = 1 until 10
    print(untilRange)

    //in operator
    print(1 in simpleRange)
    print(1 !in simpleRange)
}
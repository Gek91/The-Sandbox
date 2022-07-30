fun main(args: Array<String>) {
    listOf(1,2,3,4).asSequence()
        .map { print("map($it) "); it * it } //intermediate operation, until the final operation nothing is printed
        .toList() //now execute and print

    //generate sequence
    val naturalNumber = generateSequence(0, { it + 1 }) //sequence from 0, all natural number
    val numbersTo100 = naturalNumber.takeWhile {  it <= 100 } //sequence to 100
    //sequence elements not generated until terminal operation
    numbersTo100.sum()
}



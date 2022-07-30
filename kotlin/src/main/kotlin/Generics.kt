fun main(args: Array<String>) {

    //reifield

    //variance
    val sup = SuperType()
    val sub = SubType()

    val supProducer = Producer<SuperType>(sup)
    val supConsumer = Consumer<SuperType>()
    val subProducer = Producer<SubType>(sub)
    val subConsumer = Consumer<SubType>()

    executeProducer<SuperType>(supProducer) //normal
    executeProducer<SubType>(subProducer) //normal
    //executeProducer<SubType>(supProducer) //covariance KO
    executeProducer<SuperType>(subProducer) //covariance OK

    executeConsumer<SuperType>(supConsumer, sup) //normal
    executeConsumer<SubType>(subConsumer, sub) //normal
    executeConsumer<SubType>(supConsumer, sub)  //contravariance OK
    //executeConsumer<SuperType>(subConsumer, sup) //contravariance KO

    //type projection
    var stringValues : MutableCollection<String> = arrayListOf("1","2","3")
    var anyValues : MutableCollection<Any> = arrayListOf("1","2","3")

    covariantCopyData(stringValues,anyValues) //Accepted covariant
    contravariantCopyData(stringValues,anyValues) //Accepted, contravariant
    //copyDataWithoutVariance(stringValues,anyValues) //not accepted
}

//reifield permits to check value type. Remove runtime type erasure
inline fun <reified T> isA(value: Any) = value is T;

//not working, type erasure
//fun <T> isANotInline(value: Any) = value is T;

//variance
open class SuperType { }
class SubType : SuperType() {}

class Producer<out T>(val value: T) {
    fun produce() : T = value
}

class Consumer<in T> {
    fun consume(value: T) = println(value)
}

fun <T> executeProducer(producer : Producer<T>) : T {
    return producer.produce()
}

fun <T> executeConsumer(consumer : Consumer<T>, value : T) {
    consumer.consume(value)
}

//type projection
fun <T> covariantCopyData(source: MutableCollection<out T>, destination: MutableCollection<T>) {
    for(item in source) {
        destination.add(item)
    }
}

fun <T> contravariantCopyData(source: MutableCollection<T>, destination: MutableCollection<in T>) {
    for(item in source) {
        destination.add(item)
    }
}

fun <T> invariantCopyDataWithoutVariance(source: MutableCollection<T>, destination: MutableCollection<T>) {
    for(item in source) {
        destination.add(item)
    }
}


import java.lang.IndexOutOfBoundsException
import java.lang.reflect.Type
import java.math.BigDecimal
import java.time.LocalDate
import kotlin.reflect.KProperty

fun main(args: Array<String>) {

    val p1 = Point(1,2)
    val p2 = Point(2,3)
    println(p1 + p2)
    println(p1 - p2)

    println(p1*2.0)

    println(p1[1])
    println(p1[0])

    val list = arrayListOf(1,2)
    list+=3
    println(list)

    var bigDecimal = BigDecimal.ZERO
    println(++bigDecimal)

    val rect = Rectangle(Point(10, 10), Point(30,40))
    println(Point(20,20) in rect)

    val newYear = LocalDate.ofYearDay(2017,1)
    val daysOff = newYear.minusDays(1)..newYear
    for(dayOff in daysOff) {
        println(dayOff)
    }

    //destructuring example
    val (x,y) = Point(10,20)
    println(x)
    println(y)

    //delegate property example
    val foo = Foo()

    foo.p = 2
    println(foo.p)
    println(foo.p)

}

data class Point(val x: Int, val y: Int) {
    //add + operator to Point objects
    operator fun plus(other: Point) : Point {
        return Point(x + other.x, y + other.y)
    }
}

//define minus as extension function
operator fun Point.minus(other: Point) : Point {
    return Point(x - other.x, y - other.y)
}

//no need to have the same type as parameter
operator fun Point.times(scale: Double) : Point {
    return Point((x*scale).toInt(),(y*scale).toInt())
}

//collection get operator
operator fun Point.get(index: Int) : Int {
    return when(index) {
        0 -> x
        1 -> y
        else -> throw IndexOutOfBoundsException()
    }
}

//destructuring operation
operator fun Point.component1() : Int {
    return x
}
operator fun Point.component2() : Int {
    return y
}

//defined on collection
operator fun <T> MutableCollection<T>.plusAssign(element: T) {
    this.add(element)
}

//unary operation
operator fun BigDecimal.inc() = this + BigDecimal.ONE + BigDecimal.ONE

//contains (in) operator
data class Rectangle(val upperLeft:Point, val lowerRight: Point)

operator fun Rectangle.contains(p: Point) : Boolean {
    return p.x in upperLeft.x until lowerRight.x &&
            p.y in upperLeft.y until lowerRight.y //use until to create an open range
}

//Iterator operator
operator fun ClosedRange<LocalDate>.iterator() : Iterator<LocalDate> =
        object : Iterator<LocalDate> {

            var current : LocalDate = start //minimum value of the range, defined in closedRange

            override fun hasNext(): Boolean {
                return current <= endInclusive  //end value of the range, defined in closedRange
            }

            override fun next(): LocalDate {
               return current.apply { current = this.plusDays((1)) } //apply -> execute the logic and return "current" value
            }

        }

//delegate properties
//delegate class must define getValue and SetValue
//it contains eventually complicate logic in get and set delegated properties
class Delegate {

    var delegateValue : Int = 0

    operator fun getValue(obj: Foo, property: KProperty<*>) : Int {
        println("delegate getter")
        delegateValue -= 1
        return delegateValue;
    }

    operator fun setValue(obj: Foo, property: KProperty<*>, value: Int) {
        println("delegate setter")
        delegateValue = value * 3
    }
}

//delegate getter and setter function to delegate class
class Foo {
    var p : Int by Delegate()
}
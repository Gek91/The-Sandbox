import java.lang.RuntimeException

enum class Color(val r: Int, val g: Int, val b:Int) {
    RED(255,0,0),GREEN(0,255,0),BLUE(0,0,255);

    fun rgb() = (r * 256 + g) * 256 + b;
}

fun getMnemonic(color : Color) =
    when (color) {
        Color.RED -> "a"
        Color.GREEN, Color.BLUE -> "b"
        else -> "c"
    }

//Not only constants, object also
fun mix(c1: Color, c2: Color) =
    when(setOf(c1,c2)) {
        setOf(Color.RED, Color.GREEN) -> "a"
        setOf(Color.RED, Color.BLUE) -> "b"
        setOf(Color.BLUE, Color.GREEN) -> "c"
        else -> throw RuntimeException()
    }

//when without parameters -> options are boolean expression
fun mix2(c1: Color, c2: Color) =
    when {
        c1 == Color.RED && c2 == Color.GREEN || c1 == Color.GREEN && c2 == Color.RED -> "a"
        c1 == Color.RED && c2 == Color.BLUE || c1 == Color.BLUE && c2 == Color.RED -> "b"
        c1 == Color.BLUE && c2 == Color.GREEN || c1 == Color.GREEN && c2 == Color.BLUE -> "c"
        else -> throw RuntimeException()
    }
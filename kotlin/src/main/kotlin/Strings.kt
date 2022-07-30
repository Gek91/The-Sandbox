fun main(args: Array<String>) {

    //string templating
    val template = "world"
    println("Hello $template")

    //triple quoted
    val kotlinLog =
        """| //
       .|//
       .|/ \
    """.trimMargin(".")

    println(kotlinLog)
}

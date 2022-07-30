
fun main(args: Array<String>) {



}

//Substitute action code in parameter invocation for better performance
inline fun myFunc(action: () -> Unit) {

    action.invoke()
}

//noinline doesn't apply inline expansion of the code for secondaction
inline fun myFunctWithNoInlinePar(action: () -> Unit, noinline secondAction : () -> Unit) {

    action.invoke()
    secondAction.invoke()
}
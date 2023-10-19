package main

//standard library imported without any other operation
import (
	"fmt"
	//external module, need to be installed
	"rsc.io/quote"
	//user defined module
    "example/greetings"
)

func main() {
    fmt.Println("Hello, World!")
    fmt.Println(quote.Go())
    fmt.Println(greetings.Hello("Gladys"))
}
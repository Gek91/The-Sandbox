package main

import (
	"fmt"
	"os"
)

func main() {

	//all the same output
	fmt.Printf("Hello %d\n", 23)
	//first argument implements io.Writer interface.
	fmt.Fprint(os.Stdout, "Hello ", 23, "\n")
	fmt.Println("Hello", 23) //list of arguments

	//are the same
	fmt.Printf("%v\n", 23) //%v stay for generic value
	fmt.Println(23)

	//print te type
	fmt.Printf("%T\n", 23)
}
package main

import "fmt"

//return a function
func squares() func() int {
	//referenced variable in the closure
	var x int
	//closure function
	return func() int { //anonymous function
			x++ //literal function can access variable in the enviroment in witch is defined
			return x * x
		}
}

func main() {
	//closure is a function value that references variables from outside its body
	//the function may access and assign the referenced variables

	function := squares() //return the anonymous inner function (closure)

	fmt.Println(function()) //x=1
	fmt.Println(function()) //x=2
	fmt.Println(function()) //x=3
	fmt.Println(function()) //x=4

	function2 =: squares() //new function with new referenced variable
	fmt.Println(function2()) //x=1

	fmt.Println(squares()()) //1

}
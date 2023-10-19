package main

import "fmt"

//return value declared in the function signature, no need of explicit return
func myFunc1(x int) (result int) {
	
	result = x +1;

	return //return result
}

//multiple values return
func myFunc2(x int) (result int, odd bool) {

	odd = x%2 == 1
	result = x-1

	return result, odd
}



func main() {

	fmt.Println(myFunc1(1))

	fmt.Println(myFunc2(1))

	//first class value
	f := myFunc1
	//f = myFunc2 //error, no same type of func(int) int

	fmt.Printf("%T\n", f) // "func(int) int"
	fmt.Println(myFunc1(1)) //calling the function by its function variable

}
package main

import "fmt"

func main() {

	//allocates an array and return a slice that refers it. Array has element with zero type value
	var k = make([]int, 3)
	fmt.Println(len(k))
	fmt.Println(cap(k))
	fmt.Println(k)
	//it's possible specify a third argument to define a different value for the capacity of the slice
	k = make([]int, 3, 5)
    fmt.Println(len(k))
    fmt.Println(cap(k))

}
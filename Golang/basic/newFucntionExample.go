package main

import (
	"fmt"
)

func main() {

	//does not initialize the memory, only zero it. Allocate storage for a new item of the type defined and return its address
	p := new(int) //pointer to int variable
	p2 := new(int) //pointer to another variable


	fmt.Println(p == p2)


	fmt.Println(*p)

	*p = 2

	fmt.Println(*p)

}
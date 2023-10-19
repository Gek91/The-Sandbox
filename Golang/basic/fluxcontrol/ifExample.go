package main

import "fmt"

func main() {

	value := 10

	if value > 4 {
		fmt.Println("more than 4")
	}

	//short statement before condition
	if z := 7 ; value > 7 {
		fmt.Println("more than", z)
	} else {
		fmt.Println("less than", z)
	}
}
package main

import "fmt"

func main() {

	//append operation manage also the pointed array size, creating a new array if the size is not
	//enough for the new elements
	var x []int
	fmt.Println(len(x))
    fmt.Println(cap(x))

	x = append(x, 1) // is recommended to reassign to append operation to the same slice
	fmt.Println(len(x))
    fmt.Println(cap(x))

	x = append(x, 2, 3)
	fmt.Println(len(x))
    fmt.Println(cap(x))

	x = append(x, x...) // append the slice x
	fmt.Println(len(x))
    fmt.Println(cap(x))


	fmt.Println(x)
}

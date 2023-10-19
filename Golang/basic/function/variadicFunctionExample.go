package main

import "fmt"

//variadic function
func sum(vals ...int) int {
	total := 0
	for _, val := range vals {
		total += val
	}
	return total
}

func main() {

	fmt.Println(sum(1))
	fmt.Println(sum(1,2))
	fmt.Println(sum(1,2,3))
}
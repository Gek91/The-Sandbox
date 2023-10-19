package main

import "fmt"

//T must be a comparable type
func Index[T comparable](s []T, x T) int {
	for i, v := range s {
		// comparable include the operation ==
		if v == x {
			return i
		}
	}
	return -1
}

type List[T any] struct {
	next *List[T]
	val  T
}

func main() {
	//generic function
	si := []int{10, 20, 15, -10}
	fmt.Println(Index(si, 15))

	ss := []string{"foo", "bar", "baz"}
	fmt.Println(Index(ss, "hello"))


	//generics type
}

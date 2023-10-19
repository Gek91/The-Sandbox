package main

import "fmt"

func main() {

	//empty interface
	var i interface{} = "hello"

	//conversion of interface to string type
	s := i.(string)
	fmt.Println(s)

	//ok helps to check the conversion
	s, ok := i.(string)
	fmt.Println(s, ok)

	//no panic because of ok value
	f, ok := i.(float64)
	fmt.Println(f, ok)

	//f = i.(float64) // panic
	//fmt.Println(f)


	//type switch
	do(21)
	do("hello")
    do(true)
}

func do(i interface{}) {
	switch v := i.(type) {
	case int:
		fmt.Printf("Twice %v is %v\n", v, v*2)
	case string:
		fmt.Printf("%q is %v bytes long\n", v, len(v))
	default:
		fmt.Printf("I don't know about type %T!\n", v)
	}
}
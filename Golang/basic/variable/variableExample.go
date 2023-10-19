package main

import (
	"fmt"
	"math"
)
func main() {

	//var name type = value
	var a int = 5
	var b string = "ciao"
	//short variable declaration, only inside a function
	c := true
	//type inference with initializer value
	var d = 10
	//multiple initlialization
	var e, f bool = false, true

	//alla variables are initialized with 0 if not specified
	var g int
	var h string //empty string
	var i bool //false

	fmt.Println(a)
	fmt.Println(b)
	fmt.Println(c)
	fmt.Println(d)
	fmt.Println(e)
	fmt.Println(f)
    fmt.Println(g)
    fmt.Println(h)
    fmt.Println(i)

    //type conversion, must be explicit
    var x, y int = 3, 4
    var float float64 = math.Sqrt(float64(x*x + y*y))

    fmt.Println(float)
}
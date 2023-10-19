package main

import (
	"fmt"
)
//Struct define a type!
type Point struct {
	X, Y int
}

//constructor, language convention, is preferred to use struct literals
func NewPoint(X int, Y int) *Point {
	point := Point{ X, Y}
	return &point
}

//doesn't change struct values
func f(p Point) {
	p.X = 5
}
 
//changes struct values
func fp(p *Point) {
	p.X = 5
}


func main() {

	var center Point
	var centerPointer *Point

	//initialization of struct
	center = Point{ 1, 2}
	centerPointer = &center

	//equivalent
	center.X = 10
	centerPointer.Y = 15 //under the hood is (*centerPointer).X

	//struct literal (like constructor)
	center = Point { 2, 3} //X = 2, Y = 3
	center = Point{ X : 1, Y : 2} //X = 1, Y = 2
	center = Point{} // X = 0, Y = 0
	center = Point { X: 1} //X = 1, Y = 0

	//function agument
	f(center) // {1,0}, don't change
	fmt.Println(center)
	fp(&center) //{5, 0}, change
	fmt.Println(center)

	mypoint := Point{5, 0}

	//compare
	fmt.Println(center == mypoint)
	mypoint.Y = 1
	fmt.Println(center == mypoint)


	//nil struct is initalized using zero values of his field -> not nil
	var zeroStruct Point
	fmt.Println(zeroStruct)

}
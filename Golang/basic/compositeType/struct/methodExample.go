package main

import (
	"fmt"
	"math"
)

//struct
type Point struct{ 
	X, Y float64 
}

//methods, just a function that define a receiver argument
//the receiver define the the type that can use this method
func (p Point) Distance(q Point) float64 {
	return math.Hypot(q.X - p.X, q.Y - p.Y)
}

//without using the receiver of type pointer the operation on the structure don't change the structure values
func (p *Point) ScaleBy(factor float64) {
	p.X *= factor
	p.Y *= factor
}
//equivalment function , not a method
func equivalentFunctionScaleBy(p *Point, factor float64) {
	p.X *= factor
	p.Y *= factor
}

//define the default format for the specific type
func (p *Point) String() string {
    return fmt.Sprintf("%d/%d", p.X, p.Y)
}


//named type
type PointDistance float64

func (p PointDistance) Distance() float64 {
	return float64(p)
}


func main() {

	var p Point = Point{1.0,2.0}
	var p1 Point = Point{2.0,1.0}
	pointer := &p

	//equivalent notation, method require a non pointer
	fmt.Println(p.Distance(p1))
	fmt.Println((*pointer).Distance(p1))
	fmt.Println(pointer.Distance(p1)) //under the hood is (*pointer).Distance

	//equivalent notation, method require a pointer
	p.ScaleBy(1.0) //under the hood is (&p).scaleBy
    (*pointer).ScaleBy(1.0)
    pointer.ScaleBy(1.0)

	// receiver
	var nilPoint Point //in reality is initialized using zero values of fields
	fmt.Println(nilPoint)
	fmt.Println(nilPoint.Distance(p1)) //

	var nilPointPointer *Point
	fmt.Println(nilPointPointer) //nil
	//IMPORTANT MANAGE NIL RECEIVER IN METHOD LOGIC.
	//fmt.Println(nilPointPointer.Distance(p1)) //nil pointer reference error when calling struct field!!

// 	var d PointDistance = 0.2
// 	fmt.Println(d.Distance())
//
// 	distanceMethod := p.Distance //method value, variable containing reference to method distance being executed on the variable p
// 	fmt.Println(distanceMethod(p1))
//
// 	distance := Point.Distance //method expression, variable containing method reference
// 	fmt.Println(distance(p, p1))
}
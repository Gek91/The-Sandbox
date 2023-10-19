package main

import (
	"fmt"
	"math"
)

type Point struct {
	X, Y float64
}

func (p Point) Distance(q Point) float64 {
	return math.Hypot(q.X - p.X, q.Y - p.Y)
}

type Circle struct {
	*Point //anonymous field -> no name point
	Radius int
}

type Wheel struct {
	*Circle //anonymous field -> no name circle
	Spokes int
}

func main() {


	var w Wheel = Wheel{
		&Circle{
			&Point{2,2},
			10,
		},
		20,
	}
	//this assign it also work because p.x and *p.x under the hood make the same assignment
	//in this case w.Circle.Point.X became automatically -> w.*Circle.*Point.X
	//sintax that only works with struct.
	w.X = 8 //point x -> instead of w.circle.point.x
	w.Y = 8 //point y -> insted of w.circle.point.y
	w.Radius = 5 //circle radius -> instead of w.circle.radius
	w.Spokes = 20 //wheel spokes

	dist := w.Distance(Point{1,1}) //call distance of point with the point 1,1

	fmt.Println(dist)

	//it s possible to use the type of the anonymous field to refer to the field itself.
	fmt.Println(w.Circle.Point.X)
	//same
	fmt.Println(w.X)


}
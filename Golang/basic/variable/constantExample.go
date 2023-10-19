package main

import "fmt"

const PI = 3.14

const (
	Big = 1 << 100
)

func main() {
	fmt.Println(PI)
	fmt.Println(Big)
}
package main

import (
	"fmt"
)

//type declaration
type Celsius float64
type Fahrenheit float64

const (
	AbsoluteZeroC Celsius = -273.15
	FreezingC Celsius = 0
	BoilingC Celsius = 100
)

func CToF(c Celsius) Fahrenheit { return Fahrenheit(c*9/5 + 32) }
func FToC(f Fahrenheit) Celsius { return Celsius((f - 32) * 5 / 9) }

func main() {
	
	fmt.Println(CToF(10))
	fmt.Println(FToC(10))
	

	var f Fahrenheit = 10.4
	var c Celsius = 20.8

	//conversion -> the type have the underlining type
	fmt.Println(Celsius(f))
	fmt.Println(Fahrenheit(c))

}
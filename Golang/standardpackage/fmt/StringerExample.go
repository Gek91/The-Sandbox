package main

import "fmt"

type Person struct {
	Name string
	Age  int
}

//stringer defines this method. Fmt package look for this interface to print type values
func (p Person) String() string {
	return fmt.Sprintf("%v (%v years)", p.Name, p.Age)
}

func main() {
	a := Person{"Arthur Dent", 42}
	z := Person{"Zaphod Beeblebrox", 9001}
	fmt.Println(a, z)
}
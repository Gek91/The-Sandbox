package main

import (
	"fmt"
)

func main() {

	//create a map with make function
	var m map[string]int = make(map[string]int)
	m["uno"] = 1
	m["due"] = 2
	fmt.Println(m["uno"])
	fmt.Println(m["due"])


	//map literals
	m = map[string]int {
		"uno" : 1,
		"due" : 2,
	}
	fmt.Println(m["due"])

	//check value exists
	elem, ok := m["uno"]
	fmt.Println(ok)
	fmt.Println(elem)

	//remove
	delete(m, "uno")

	//maps return zero values in case the key not exists. !!!Is useful check the second returned parameter to know if the key not exists ora has zero value.!!!
	elem, ok = m["uno"]
	fmt.Println(ok)
	fmt.Println(elem)

	//to only check the existence of a key you use the pattern
	_, ok =  m["uno"]

	//safe operation, return zero value for the type
	fmt.Println(m["uno"])


	//loop on map
	m = map[string]int {
		"uno" : 1,
		"due" : 2,
		"tre" : 3,
    }
	for key, value := range m {
		fmt.Printf("%s\t%d\n", key, value)
	}


	//zero value
	var zeroMap map[string]int
	fmt.Println(zeroMap == nil)
	fmt.Println(len(zeroMap))

}
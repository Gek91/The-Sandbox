
package main

import "fmt"

func main() {

	s := "hello, world"
	fmt.Println(len(s)) //len function
	fmt.Println(s[0], s[7]) //indexing
	fmt.Println(s[0:5]) //substring slice
	fmt.Println(s[5:]) 

	s1 := "hello, world1"

	//compare
	fmt.Println(s == s1)
	fmt.Println(s < s1)

	//escaped string and raw string
	escaped := "ciao \n ciao"
	raw := `ciao \n ciao` //backquotes `
	fmt.Println(escaped)
	fmt.Println(raw)

	//string conversion in slice
	b := []byte(s) //string to slice
	s2 := string(b) //slice to string
	fmt.Println(s2)


	//range usage
	for pos, char := range s {
		fmt.Printf("character %#U starts at byte position %d\n", char, pos)
	}
}


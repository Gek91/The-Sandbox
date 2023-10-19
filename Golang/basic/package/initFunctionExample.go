package main

import "fmt"


var a int

//init function, called automatically at package startup
func init() {
    a = 10
}

func main() {
	
	fmt.Println(a);

}

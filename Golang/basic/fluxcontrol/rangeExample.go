package main

import "fmt"


func main() {

	slice := []int{1,2,3}

	//every iteration returns and index and the copy of the element at the index in the slice
	for index, element := range slice {
		fmt.Println(index, element)
	}

	//possible to ignore one of the two values
	for _, element := range slice {
    	fmt.Println(element)
    }


	//map
	m := map[string]int {
    		"uno" : 1,
    		"due" : 2,
    		"tre" : 3,
       	}

    //ordering changes between execution
    for index, value := range m {
    	fmt.Println(index, value)
    }
}
package main

import "fmt"

func main() {
	
	
	for index := 0; index < 10; index++ {
		
		fmt.Printf("%d \n", index)
	}

	//while style omitting start and increment
	i := 1
	for i < 10 {
		fmt.Println(i)
		i = i +1
	}

}

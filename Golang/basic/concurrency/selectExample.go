package main

import (
	"fmt"
	"time"
)

func fibonacci(c, quit chan int) {
	x, y := 0, 1
	for {
		//with select goroutine wait on multiple communication operations
		//the execution is blocked until one of its cases can run
		select {
		case c <- x:
			x, y = y, x+y
		case <-quit:
			fmt.Println("quit")
			return
		default: //executed if the other is not ready to execute
        	time.Sleep(50* time.Millisecond)
		}
	}
}

func main() {
	c := make(chan int)
	quit := make(chan int)
	go func() {
		//10 times run the first case then run the second
		for i := 0; i < 10; i++ {
			fmt.Println(<-c) //unlock the first select case
		}
		quit <- 0 //unlock the second select case
	}() //execution the anonymous function just defined

	//print the first 10 fibonacci numbers then print quit
	fibonacci(c, quit)
}

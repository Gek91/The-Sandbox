package main

import (
	"fmt"
	"time"
)

type Request int

//semaphore channel, used to limit the number of concurrent execution with the buffer size
var sem = make(chan int, 2)

func handle(r Request) {
		sem <- 1    // Wait for active queue to drain.
		process(r)  // May take a long time.
		<-sem       // Done; enable next request to run.
	}

func serve(queue chan Request) {
	for {
		req := <-queue
		go handle(req)  // Don't wait for handle to finish.
	}
}

func process(r Request) {
	fmt.Println("before", r)
	time.Sleep(time.Second)
	time.Sleep(time.Second)
	fmt.Println("after", r)
}

func main() {

	var queue = make(chan Request, 2)

	//start serve loop
	go serve(queue)

	queue <- Request(1)
	queue <- Request(2)
	queue <- Request(3)
	queue <- Request(4)

	time.Sleep(time.Second*10)

}

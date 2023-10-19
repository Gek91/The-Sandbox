package main 

import (
	"fmt";
	"time";
	"log")

//Mostly used to release resources. Using defer you can define the release logic next to the declaration

func myfunc() {
	defer fmt.Println("word") //Executed at the end of the function
	
	for i := 1 ; i < 10 ; i++ {
		fmt.Println(i)
	}
	
	fmt.Println("Hello")
}

func bigSlowOperation() {
	defer trace("bigSlowOperation")() // don't forget the extra parentheses, the execution of trace is at the end of the function
	// ...lots of work...
	time.Sleep(2 * time.Second) // simulate slow operation by sleeping
	}

func trace(msg string) func() {
	start := time.Now()
	log.Printf("enter %s", msg)
	return func() { log.Printf("exit %s (%s)", msg, time.Since(start)) } //anonymous function with closure!!
}

//deferred function executed in lifo order, last defined first.
func deferLifo() {

	defer fmt.Println("1") 
	defer fmt.Println("2") 
	defer fmt.Println("3") 

}

func main() {

	defer fmt.Println("end") //Executed after all the main
	fmt.Println("start")

	myfunc()

	bigSlowOperation()

	deferLifo()
}
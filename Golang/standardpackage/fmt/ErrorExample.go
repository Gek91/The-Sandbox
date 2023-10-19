package main

import (
	"fmt"
	"time"
)

type MyError struct {
	When time.Time
	What string
}

//Error define this method. fmt looks for this interface when printing values
func (e *MyError) Error() string {
	return fmt.Sprintf("at %v, %s",
		e.When, e.What)
}

//error value returned, client must check if it's null
func run() error {
	return &MyError{
		time.Now(),
		"it didn't work",
	}
}

func main() {
//if error is null it's success
	if err := run(); err != nil {
		fmt.Println(err)
	}
}

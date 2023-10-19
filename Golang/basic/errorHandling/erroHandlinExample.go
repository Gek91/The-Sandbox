package main

import "fmt"

//declare my error implementation
type MyError struct {
	message string
}

//implement error interface
func (e *MyError) Error() string {
	return fmt.Sprintf("I have an error: %s", e.message)
}

func doAnError() (value int, err error) {

	err = &MyError{ "ops" }

	return
}

func main() {

	if _, err := doAnError() ; err != nil {
		fmt.Println(err.Error())
	}

}
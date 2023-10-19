package main 

import "fmt"

func panicRecover() (result int, err error)  {

	//recover from panic
	defer func() {
		if p:= recover(); p != nil {
			err = fmt.Errorf("%v", p)
		}
		fmt.Println("do something else")
	}()

	panic(fmt.Sprintf("error"))

	return
}
	

func main() {

	_, err := panicRecover()
	fmt.Println(err)
}
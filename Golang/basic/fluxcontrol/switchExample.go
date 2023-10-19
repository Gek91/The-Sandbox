package main

import (
	"fmt"
	"runtime"
)

func main() {
	//switch has no automatic fall through

	//it stop executing on the first true occurrence, calling top to bottom
	fmt.Print("Go runs on ")
	switch os := runtime.GOOS; os {
	case "darwin":
		fmt.Println("OS X.")
	case "linux":
		fmt.Println("Linux.")
	default:
		fmt.Printf("%s.\n", os)
	}

	//switch without condition (in switch statement) is like "switch true"
	t := time.Now()
    switch { //like switch true
    	case t.Hour() < 12: //evaluates if true
    		fmt.Println("Good morning!")
    	case t.Hour() < 17:
    		fmt.Println("Good afternoon.")
    	default:
    		fmt.Println("Good evening.")
    }


    var value = "a"
    //multi case
    switch value
    	case 'a','b','c'
    		fmt.Println("true")
}
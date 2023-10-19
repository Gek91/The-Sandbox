package greetings

import "fmt"

//capital letter in the name of the function means exported name
func Hello(name string) string {
    message := fmt.Sprintf("Hi, %v. Welcome!", name)
    return message
}

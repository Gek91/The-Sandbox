package main

import (
	"fmt"
	//local package
	"example/hello/morestrings"

	//remote package
	"github.com/google/go-cmp/cmp"
)

func main() {
    fmt.Println("Hello, World!")
	fmt.Println(morestrings.ReverseRunes("!oG ,olleH"))
	fmt.Println(cmp.Diff("Hello World", "Hello Go"))
}
package main

import (
	"fmt"
)

//interface
type MyInterface interface {

	MyFuncInt() int

	MyFuncString() string
}

//concrete type
type MySimpleType int

func (t MySimpleType) MyFuncInt() int {
	return 1
}

func (t MySimpleType) MyFuncString() string {
	return "a"
}

//doesn't have all the method of the interface
type MySimpleType2 int 

func (t MySimpleType2) MyFuncInt() int {
	return 2
}

//using pointer receivers
type MySimpleType3 int 

func (t *MySimpleType3) MyFuncInt() int {
	return 1
}

func (t *MySimpleType3) MyFuncString() string {
	return "a"
}


//empty interface
type emptyInterface interface {}

//main
func main() {

	var interf MyInterface

	myType := MySimpleType(1)
	interf = myType; //implements interface
	fmt.Println(interf.MyFuncString())
	fmt.Printf("(%v, %T)\n", interf, interf) //prints underlying value and implementation type value

	myType2 := MySimpleType2(2)
	//interf = myType2 //error, doesn't implements all methods

	myType3 := MySimpleType3(3)
	interf = &myType3 //because uses pointer receivers
	fmt.Println(interf.MyFuncString())
	fmt.Printf("(%v, %T)\n", interf, interf) //prints underlying value and implementation type value

	//nil underlying value
	var nilPointer *MySimpleType3 //nil pointer receiver
    interf = nilPointer
    fmt.Printf("(%v, %T)\n", interf, interf) //nil, implementation type
    fmt.Println(interf.MyFuncString()) //works, should be better check for nil receiver

	//nil interface
	var nilInterface MyInterface
	fmt.Printf("(%v, %T)\n", nilInterface, nilInterface) //nil, nil
	//fmt.Println(emptyInterf.MyFuncString()) //error on caling method


	//empty interface
	var emptyInterf emptyInterface

	emptyInterf = myType3
	emptyInterf = myType2
	emptyInterf = myType
	fmt.Printf("(%v, %T)\n", emptyInterf, emptyInterf)
	emptyInterf = "hello"
	fmt.Printf("(%v, %T)\n", emptyInterf, emptyInterf)
	emptyInterf = 42
	fmt.Printf("(%v, %T)\n", emptyInterf, emptyInterf)
}
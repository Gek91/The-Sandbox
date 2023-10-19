package main

import (
	"fmt"
	"math"
)

type MyInterface interface {
	doSomething() (bool)
}

type MyInterfaceSecond interface {
	doSomethingElse() (int)
}

//this type can use the methods of the embedded types as is its (same sintax). Under the hood they are applied on the anonymous fields that type has the method
type MyCompleteInterface interface {
	//embedded -> anonymous field
	MyInterface
	MyInterfaceSecond

	AlsoDoThis()
}
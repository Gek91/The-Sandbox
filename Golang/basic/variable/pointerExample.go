package main 

import "fmt"

func incr(pointer *int) {
	*pointer++
}

func main() {

	x := 1
	p := &x //pointer

	//deferentiation
	fmt.Println(*p);

	//change value of the pointing memory
	*p = 2
	fmt.Println(x);

	//comparable
	p1 := p;
	fmt.Println(p1 == p);

	//passing pointer to function
	incr(&x)

	fmt.Println(x);

	//nil pointer
	val pointer *int
	fmt.Println(pointer);


}
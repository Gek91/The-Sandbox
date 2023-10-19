package main

import "fmt"

func main() {

	//array literals
	primes := [6]int{2, 3, 5, 7, 11, 13}
	//slice created specifying two indices of the array, low:upper
    //all the elements between the two indices are the slice content (excluded the upper)
   	//the slice operation creates a new slice from the previous slice, pointing the same array
	var slice []int = primes[1:4]
	fmt.Println(slice)
	fmt.Println(len(slice)) //slice size
    fmt.Println(cap(slice))	//array size from the first slice index (6 - 1 = 5)

	//modifying slice modify also the underlying array and other slice on the same array
	slice[0] = 20
	var slice2 []int = primes[1:5]
	fmt.Println(slice)
	fmt.Println(primes)
	fmt.Println(slice2)


	//slice literals, create an array with the size of the elements and a slice that reference it
	s := []int{2,3,5,7,11,13}
	fmt.Println(len(s)) //6
	fmt.Println(cap(s))	//6

	s = s[:4]
	fmt.Println(len(s))//4
	fmt.Println(cap(s))//6
	
	s = s[2:]
	fmt.Println(len(s)) //2
	fmt.Println(cap(s)) //4 because starting index moved

	//its possible to extends slice length until its capacity
	s = s[:4]
    fmt.Println(len(s)) //4
    fmt.Println(cap(s)) //4


	//zero slice, value is nil. No pointed array
	var zeroSlice []int
	fmt.Println(zeroSlice == nil)
	fmt.Println(len(zeroSlice)) //0
    fmt.Println(cap(zeroSlice)) //0

	var emptySlice []int = []int{}
	fmt.Println(emptySlice == nil)
	fmt.Println(len(emptySlice)) //0
    fmt.Println(cap(emptySlice))


    //slice of slice
    sliceOfSlice := [][]string{
    	[]string{"a","b","c"},
    	[]string{"d","e","f"},
    }
	fmt.Println(sliceOfSlice)



}

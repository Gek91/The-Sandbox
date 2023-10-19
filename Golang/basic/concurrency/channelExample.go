package main

import "fmt"

//function require a channel
func sum(s []int, c chan int) {
	sum := 0
	for _, v := range s {
		sum += v
	}
	c <- sum // send sum to channel
}

func main() {
	s := []int{7, 2, 8, -9, 4, 0}

	//create a channel of int type
	c := make(chan int)
	//start a goroutine passing the channel
	go sum(s[:len(s)/2], c)
	go sum(s[len(s)/2:], c)
	//get values from the channel
	x, y := <-c, <-c
	fmt.Println(x, y, x+y)

	//buffered channel
	ch := make(chan int, 2)
	ch <- 1
	ch <- 2
	//ch <- 3 //error, channel il full
	fmt.Println(<-ch)
	fmt.Println(<-ch)
	//fmt.Println(<-ch) //error channel is empty
    close(ch)

	//closed channel
	ch1 := make(chan int,5) //?? need to specify buffer length or error in the next command??
    ch1 <- 1
    ch1 <- 2
    ch1 <- 3
    close(ch1) //used to say that no more data will be sent on the channel

    val, ok := <- ch1
    fmt.Println(val, ok)
    val, ok = <- ch1
    fmt.Println(val, ok)
    val, ok = <- ch1
    fmt.Println(val, ok)
    val, ok = <- ch1
    fmt.Println(val, ok) //false, closed
    //can be wrote with ->  for i := range ch1

}

package main

import (
	"fmt"
	"sync"
	"time"
)

type SafeCounter struct {
	mu sync.Mutex //mutex construct
	v  map[string]int
}

func (c *SafeCounter) Inc(key string) {
	// Lock so only one goroutine at a time can access
	c.mu.Lock()
	// start mutual exclusive
	c.v[key]++
	// end mutual exclusive
	c.mu.Unlock()
}

func (c *SafeCounter) Value(key string) int {
	//unlock can be specified in defer
	defer c.mu.Unlock()
	c.mu.Lock()
	//mutual exclusive until defer execution
	return c.v[key]
}

func main() {
	c := SafeCounter{v: make(map[string]int)}
	for i := 0; i < 1000; i++ {
		go c.Inc("somekey")
		go c.Inc("anotherkey")
	}

	time.Sleep(time.Second)
	fmt.Println(c.Value("somekey"))
	fmt.Println(c.Value("anotherkey"))
}

package main

import (
	"fmt"
	"log"
	"net/http"
	"os"
)

func main() {

	//set handler for 
	http.HandleFunc("/", requestHandler)

	port := os.Getenv("PORT")
	if port == "" {
		port = "8080"
		log.Printf("Defaulting to port %s", port)
	}

	log.Printf("Listening on port %s", port)
	if err := http.ListenAndServe(":"+port, nil); err != nil {
		log.Fatal(err)
	}
}

//request handler
func requestHandler(w http.ResponseWriter, r *http.Request) {
	
	log.Printf("Received request on path %s", r.URL.Path)

	if r.URL.Path == "/" {
		fmt.Fprint(w, "Hello, World!")

	} else {

		http.NotFound(w, r)
		return
	}

}
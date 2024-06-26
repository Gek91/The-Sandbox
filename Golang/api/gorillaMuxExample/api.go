package main

import (
	"fmt"
	"log"
	"net/http"

	"github.com/gorilla/mux"
)


func homeLink(w http.ResponseWriter, r *http.Request) {
	fmt.Fprintf(w, "Welcome home!")
}

func alternativeLink(w http.ResponseWriter, r *http.Request) {
	fmt.Fprintf(w, "Alternative Welcome!")
}

func main() {

	router := mux.NewRouter().StrictSlash(true)
	router.HandleFunc("/", homeLink)
	router.HandleFunc("/", alternativeLink)
	
	log.Fatal(http.ListenAndServe(":8080", router))

}
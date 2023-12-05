package main

import (
	"os"
	"log"
)

func main() {

	//create the file
	file, err := os.Create("myFile")
	if err != nil {
		log.Fatal(err)
	}
	defer file.Close()

	//write to a file
	byteArray := []byte("i'm writing")
	_, err = file.Write(byteArray)
	if err != nil {
		log.Fatal(err)
	}
	file.Close()

	//more verbose alternative
	//open the file, only reading
// 	file, err = os.Open("myfile")
// 	if err != nil {
// 		log.Fatal(err)
// 	}
// 	byteArray = make([]byte, 1024)
//
// 	//read the file
// 	_, err = file.Read(byteArray)
// 	if err != nil {
// 		log.Fatal(err)
// 	}
// 	file.Close()

//	more concise alternative to read file in byteArray
	byteArray, err := os.ReadFile("myfile")
	if err != nil {
		log.Fatal(err)
	}

	log.Println(string(byteArray))

	//append data to the file
	file, err = os.OpenFile("myfile", os.O_APPEND|os.O_WRONLY, os.ModeAppend)
	file.Write([]byte(" appended some data\n"))
	if err != nil {
		log.Fatal(err)
	}
}
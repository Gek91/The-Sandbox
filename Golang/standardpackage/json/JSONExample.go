package main

import (
    "encoding/json"
    "fmt"
)

type Message struct {
    Name string
    Body string
    Time int64
    PreviousMessage *Message
}

func (m *Message) String() string {
    return fmt.Sprintf("name:%v body:%v time:%v previous message:{%v}", m.Name, m.Body, m.Time, m.PreviousMessage)
}

func main() {

	pm := Message{"Bob", "Hello!", 1294706395881544000, nil}
	m := Message{"Alice", "Hello", 1294706395881547000, &pm}

	//encode data to JSON
	b, _ := json.Marshal(m) //convert to []byte

	fmt.Println(b)

	um := &Message{}
	//decode from JSON
	json.Unmarshal(b, um)
	fmt.Println(um)


	//unmarshal struct with not perfect match
	um = &Message{}
	b = []byte(`{"Name":"Bob","Food":"Pickle"}`)
    json.Unmarshal(b, um)
    fmt.Println(um) //Unmarshal only what can match



    //decoding arbitrary data
    //json package uses map[string]interface{}	and []interface{} to store arbitrary JSON obect and array
    //it will unmarshal any valid JSON blob into a plain interface{} value
    b = []byte(`{"Name":"Wednesday","Age":6,"Parents":["Gomez","Morticia"]}`)

	//every Go type implements interface -> interface with zero methods
	var f interface{}
	json.Unmarshal(b, &f)
	fmt.Println(f) //print a map

	arbitraryMap := f.(map[string]interface{}) //type assertion to map[string]interface{}, so we can use it
	for key, value := range arbitraryMap {
		fmt.Println("key:", key, "value:", value)
	}

}


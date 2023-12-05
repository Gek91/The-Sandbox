package main

import (
	"log"
	"gopkg.in/yaml.v3"
	"os"
)

type Car struct {
	TopSpeed   int
	Name       string
	Cool       bool
	Passengers []string
}

func main() {
	c := Car{
		TopSpeed:   60,
		Name:       "Mirthmobile",
		Cool:       true,
		Passengers: []string{"garth", "wayne"},
	}

	log.Println(c)

	//marshall to yaml
	out, err := yaml.Marshal(c)
	if err != nil {
		log.Fatal(err)
	}

	log.Println(string(out))

	//write to file
	file, err := os.Create("myYaml.yml")
	if err != nil {
		log.Fatal(err)
	}
	defer file.Close()
	_, err = file.Write(out)
	if err != nil {
		log.Fatal(err)
	}
	file.Close()

	//unmarshal
	var result Car

	byteArray, err := os.ReadFile("myYaml.yml")
	if err != nil {
		log.Fatal(err)
	}

	err = yaml.Unmarshal(byteArray, &result)
	if err != nil {
		log.Fatal(err)
	}

	log.Println(result)
}
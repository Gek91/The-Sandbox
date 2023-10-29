package main

import (
	"database/sql"
	"fmt"
	"log"

	"github.com/go-sql-driver/mysql"
)

func main() {

	db := getDatabaseConnection()

	//check connection
	checkConnection(db)

	queryAll(db)

	queryByName(db,"ex2")

	addRow(db, Exercise{"10", "new name"})

	queryAll(db)
}

func getDatabaseConnection() (db *sql.DB) {

	//connection properties
	configuration := mysql.Config{
		User:   "",
        Passwd: "",
		Net:    "tcp",
		Addr:   "127.0.0.1:3306",
		DBName: "report",
	}

	//get database handle
	db, err := sql.Open("mysql", configuration.FormatDSN())
	if err != nil {
		log.Fatal(err)
	}

	return
}

func checkConnection(db *sql.DB) {
	if pingErr := db.Ping(); pingErr != nil {
		log.Fatal(pingErr)
	} else {
	    fmt.Println("Connected!")
	}
}

type Exercise struct {
	Id		string
	Name 	string
}

func queryAll(db *sql.DB) {

	rows, err := db.Query("SELECT * FROM exercise")
	defer rows.Close()

	if err != nil {
		fmt.Errorf("error query %v", err)
	} else {
		for _, element := range getExercisesFromRows(rows) {
			fmt.Println(element)
		}
	}
}

func getExercisesFromRows(rows *sql.Rows) (exercises []*Exercise) {

	for rows.Next() {
		var exercise *Exercise = new(Exercise)
		if err := rows.Scan(&exercise.Id, &exercise.Name); err != nil {
			fmt.Errorf("error scan %v", err)
		}
		exercises = append(exercises, exercise)
	}

	if err := rows.Err(); err != nil {
		fmt.Errorf("error rows %v", err)
	}

	return
}

func getExerciseFromRow(row *sql.Row) (*Exercise) {

	var exercise *Exercise = new(Exercise)
	if err := row.Scan(&exercise.Id, &exercise.Name); err != nil {
		 if err == sql.ErrNoRows {
		 	fmt.Println("no row", err)
		}
		fmt.Println("error %v", err)
		return nil
	}

	return exercise
}

func queryByName(db *sql.DB, name string) {

	row := db.QueryRow("SELECT * FROM exercise where name = ?", name)

	exercise := getExerciseFromRow(row)
	fmt.Println(exercise)
}

func addRow(db *sql.DB, exercise Exercise) {

	result, err := db.Exec("INSERT INTO exercise (id, name) VALUES (?, ?)", exercise.Id, exercise.Name)
	if err != nil {
		fmt.Errorf("error: %v", err)
	}
	_, err = result.LastInsertId()
	if err != nil {
		fmt.Errorf("error last id: %v", err)
	}
}


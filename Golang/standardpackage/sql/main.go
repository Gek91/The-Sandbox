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

	preparedStatementQuery(db)

	preparedStatementQuery(db, "10")
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

	//alternative, using a connection string
	//sql.Open("mysql", "username:password@tcp(127.0.0.1:3306)/report")

	//alternative, using a connector
	// mysql.NewConnector(&configuration)

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

	//query for multiple rows
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
		 //check if result is empty, QueryRow command doesn't have error in return to check
		 if err == sql.ErrNoRows {
		 	fmt.Println("no row", err)
		}
		fmt.Println("error %v", err)
		return nil
	}

	return exercise
}

func queryByName(db *sql.DB, name string) {

	//query for single row
	//IMPORTANT USE PLACEHOLDER (?) AND VARARGS TO BUILD A QUERY -> prevent sql injection
	row := db.QueryRow("SELECT * FROM exercise where name = ?", name)

	exercise := getExerciseFromRow(row)
	fmt.Println(exercise)
}

func addRow(db *sql.DB, exercise Exercise) {

	//use transaction
	transaction, err := db.Begin()
	if err != nil {
		fmt.Errorf("error creating transaction: %v", err)
	}
	defer tx.Rollback()

	//use exec method when for query that don't return data
	result, err := transaction.Exec("INSERT INTO exercise (id, name) VALUES (?, ?)", exercise.Id, exercise.Name)
	if err != nil {
		fmt.Errorf("error: %v", err)
	}
	_, err = result.LastInsertId()
	if err != nil {
		fmt.Errorf("error last id: %v", err)
	}

	if err = transaction.Commit(); err != nil {
		fmt.Errorf("error in commit: %v", err)
	}
}

func preparedStatementQuery(db *sql.DB, id string) {

	//IMPORTANT USE PLACEHOLDER (?) AND VARARGS TO BUILD A QUERY -> prevent sql injection
	statement := db.Prepare("SELECT * FROM exercise where id = ?")

	row := statement.QueryRow(id)

	exercise := getExerciseFromRow(row)
    fmt.Println(exercise)
}


package main

import (
	"context"
	"io"
	"os"
	"log"
	"net/http"
	"github.com/gin-gonic/gin"
	"strings"
 	"github.com/golang-jwt/jwt/v4"
 	"google.golang.org/api/idtoken"
 	"database/sql"
    _ "github.com/lib/pq"
)

func getHello(context *gin.Context) {
    context.IndentedJSON(http.StatusOK, "hello world")
}

func getHealthz(context *gin.Context) {
	context.Status(http.StatusOK)
}

func getCheckJWT(context *gin.Context) {
	authString := context.Request.Header["Authorization"]

	if len(authString) == 1 {
		stringSplit := strings.Split(authString[0], " ")
		if len(stringSplit) == 2 {

			token, _, err := new(jwt.Parser).ParseUnverified(stringSplit[1], jwt.MapClaims{})
			if err != nil {
				log.Println(err)
				return
			}

			if claims, ok := token.Claims.(jwt.MapClaims); ok {
				log.Println("exp", claims["exp"])
				log.Println("email", claims["email"])
				log.Println("iat", claims["iat"])
				log.Println("exp", claims["exp"])
				log.Println("aud", claims["aud"])
			} else {
				log.Println(err)
			}

		}
	}
}

func makeCall(ginContext *gin.Context) {

	url := os.Getenv("URL")

	resp, err := http.Get(url)

	if err != nil {
		log.Println("http.Get:", err)
		return
	}

	defer resp.Body.Close()

	buf := new(strings.Builder)
	_, err = io.Copy(buf, resp.Body)
	if err != nil {
		log.Println("io.Copy:", err)
		return
	}

	log.Println("response:", buf.String())
	ginContext.IndentedJSON(http.StatusOK, buf.String())
}

func makeCallOpenId(ginContext *gin.Context) {

	url := os.Getenv("URL")

	ctx := context.Background()

	//audience same as URL
	log.Println("Making call", url)
	client, err := idtoken.NewClient(ctx, url)
	if err != nil {
		log.Println("idtoken.NewClient:", err)
		return
	}

	resp, err := client.Get(url)
	if err != nil {
		log.Println("client.Get:", err)
		return
	}

	defer resp.Body.Close()

	buf := new(strings.Builder)
    _, err = io.Copy(buf, resp.Body)
	if err != nil {
		log.Println("io.Copy:", err)
		return
	}

	log.Println("response:", buf.String())
	ginContext.IndentedJSON(http.StatusOK, buf.String())
}

func receiveCall(ginContext *gin.Context) {

	ctx := context.Background()

	authString := ginContext.Request.Header["Authorization"]

	audience := os.Getenv("URL")
	log.Println("audience", audience)

	if len(authString) == 1 {
		stringSplit := strings.Split(authString[0], " ")
		if len(stringSplit) == 2 {

			payload, err := idtoken.Validate(ctx, stringSplit[1], audience)
			if err != nil {
				log.Println("idtoken.Validate", err)
				return
			}

			log.Println("exp", payload.Expires)
			log.Println("iat", payload.IssuedAt)
			log.Println("iss", payload.Issuer)
			log.Println("aud", payload.Audience)
		}

	    ginContext.IndentedJSON(http.StatusOK, "OK!")
	}}

func connectSQL(ginContext *gin.Context) {

	//LOCAL with cloud proxy: "postgres://prova:prova@localhost:3306/postgres?sslmode=disable".
	//Needed to create user prova with prova psw and launch cloud proxy pointing the instance on port 3306
	connectionStr := os.Getenv("DB-CONNECTION-STRING")

	conn, err := sql.Open("postgres", connectionStr)
	if err != nil {
		panic(err)
	}

	rows, err := conn.Query("SELECT version();")
	if err != nil {
		panic(err)
	}

	for rows.Next() {
		var version string
		rows.Scan(&version)
		log.Println(version)
	}

	rows.Close()
	conn.Close()

	ginContext.IndentedJSON(http.StatusOK, "Connected!")
}

func main() {
    router := gin.Default()

    router.GET("/hello", getHello)
    router.GET("/healthz", getHealthz)
    router.GET("/check-jwt", getCheckJWT)
    router.GET("/make-call", makeCall)
    router.GET("/make-call-open-id", makeCallOpenId)
    router.GET("/receive-call", receiveCall)
    router.GET("/connect-sql", connectSQL)
    router.Run(":8080")
}
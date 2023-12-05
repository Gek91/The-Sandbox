package main

import (
	"log"
	"net/http"
	"github.com/gin-gonic/gin"
	"strings"
 	"github.com/golang-jwt/jwt/v4"
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

func main() {
    router := gin.Default()

    router.GET("/hello", getHello)
    router.GET("/healthz", getHealthz)
    router.GET("/check-jwt", getCheckJWT)
    router.Run(":8080")
}
package main

import (
	"net/http"
	"github.com/gin-gonic/gin"
)

func getHello(context *gin.Context) {
    context.IndentedJSON(http.StatusOK, "hello world")
}

func getHealthz(context *gin.Context) {
	context.Status(http.StatusOK)
}


func main() {
    router := gin.Default()

    router.GET("/hello", getHello)
    router.GET("/healthz", getHealthz)
    router.Run(":8080")
}
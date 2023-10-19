package main

import (
    "net/http"

    "github.com/gin-gonic/gin"
)

func main() {
	//initialize gin router
	router := gin.Default()

	//associate the method GET and the path album to the handler
	router.GET("/albums", getAlbums)
	router.POST("albums", postAlbums)
	router.GET("/albums/:id", getAlbumByID)

	router.Run("localhost:8080")
}

//gin context carries request details, validates and serilize JSON and more.
func getAlbums(c *gin.Context) {
	//serilize the struct into JSON and add it ot the response
	c.IndentedJSON(http.StatusOK, Albums)
}

func postAlbums(c *gin.Context) {
	var newAlbum album
	if err := c.BindJSON(&newAlbum) ; err != nil {
		c.IndentedJSON(http.StatusBadRequest, gin.H{"message": "invalid value"})
	}

	Albums = append(Albums, newAlbum)
	c.IndentedJSON(http.StatusOK, Albums)
}

func getAlbumByID(c *gin.Context) {

    //get parameter from url
	id := c.Param("id")


    for _, a := range Albums {
         if a.ID == id {
             c.IndentedJSON(http.StatusOK, a)
             return
         }
    }
    c.IndentedJSON(http.StatusNotFound, gin.H{"message": "album not found"})
 }

package main

import (
	"net/http"
	"github.com/gin-gonic/gin"
)

//tags json specify what name a field should be when serialized into JSON, without it the name would be capitalized
type album struct {
    ID     string  `json:"id"`
    Title  string  `json:"title"`
    Artist string  `json:"artist"`
    Price  float64 `json:"price"`
}


var albums = []album{
    {ID: "1", Title: "Blue Train", Artist: "John Coltrane", Price: 56.99},
    {ID: "2", Title: "Jeru", Artist: "Gerry Mulligan", Price: 17.99},
    {ID: "3", Title: "Sarah Vaughan and Clifford Brown", Artist: "Sarah Vaughan", Price: 39.99},
}


//get handler
//gin context carries request details, validate and serialize JSON
func getAlbums(context *gin.Context) {
    //serialize into JSON
    context.IndentedJSON(http.StatusOK, albums)
}

//post handler
func postAlbum(context *gin.Context) {

	var newAlbum album

	//bind received JSON to struct newAlbum
	if err := context.BindJSON(&newAlbum); err != nil {
		return
	}

	albums = append(albums, newAlbum)
	context.IndentedJSON(http.StatusCreated, newAlbum)
}

func getAlbumById(context *gin.Context) {
	//Context.Param permits to retrieve path parameter from the URL
	id := context.Param("id")

	//find element whose id is value passed
	for _, album := range albums {
		 if album.ID == id {
			 context.IndentedJSON(http.StatusOK, album)
			 return
		 }
	 }
	 context.IndentedJSON(http.StatusNotFound, gin.H{"message": "album not found"})
}

func main() {
	//initialize gin router
    router := gin.Default()

    //associate GET HTTP method to path and handler function
    router.GET("/albums", getAlbums)
    router.GET("/albums/:id", getAlbumById)
    //associate POST HTTP method do path and handler function
    router.POST("/albums", postAlbum)

	//attache the router to an http server and start it
    router.Run("localhost:8080")
}
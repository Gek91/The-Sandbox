package main

import (
    "log"
    "net/http"
    "html/template"
    "regexp"
)

//template management, load template only on startup
//templates loaded ar identified by the last piece of their path -> template/view.html is identified by view.html
var templates = template.Must( //wrapper that panics wha a non-nil error is passed otherwise returns *template
	template.ParseFiles("templates/view.html", "templates/edit.html"), //load all templates
)


func viewHandler(response http.ResponseWriter, request *http.Request, title string) {

   	page, err := loadPage(title)
	if err != nil {
		//redirect to edit page
       	http.Redirect(response, request, "/edit/"+title, http.StatusFound)
    	return
    }

	renderTemplate(response, "view.html" ,page)
}

func editHandler(response http.ResponseWriter, request *http.Request, title string) {

    page, err := loadPage(title)
    if err != nil {
        page = &Page{Title: title}
    }

    renderTemplate(response, "edit.html" ,page)
}

func saveHandler(response http.ResponseWriter, request *http.Request, title string) {

    body := request.FormValue("body")
    page := &Page{Title: title, Body: []byte(body)}
    if err := page.save() ; err != nil {
    	http.Error(response, err.Error(), http.StatusInternalServerError)
        return
    }

    http.Redirect(response, request, "/view/"+title, http.StatusFound)
}



func renderTemplate(response http.ResponseWriter, templateName string, page *Page) {

	err := templates.ExecuteTemplate(response, templateName, page)
    if err != nil {
        http.Error(response, err.Error(), http.StatusInternalServerError)
    }
}

//path validation /view/name, edit/name o save/name
var validPath = regexp.MustCompile("^/(edit|save|view)/([a-zA-Z0-9]+)$")

//take a function with the signature of the handlers and return a clousure function of type http.HandlerFunc that can be used in http.handleFunc() method of the webserver
func makeHandler(fn func(http.ResponseWriter, *http.Request, string)) http.HandlerFunc {
   	//closure
    return func(response http.ResponseWriter, request *http.Request) {
        match := validPath.FindStringSubmatch(request.URL.Path)
        if match == nil {
            http.NotFound(response, request)
            return
        }
        fn(response, request, match[2]) // The title is the second subexpression.
    }
}

func main() {
	//tells to handle all requesto to "/view" with handler
	http.HandleFunc("/view/", makeHandler(viewHandler))
	http.HandleFunc("/edit/", makeHandler(editHandler))
    http.HandleFunc("/save/", makeHandler(saveHandler))
	//specify to listen on port 8080. This function blocks until program is terminated.
	endError := http.ListenAndServe(":8080", nil)
	log.Fatal(endError)
}
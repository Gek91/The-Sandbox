package main

import (
	"log"
	"net/http"

	socketio "github.com/googollee/go-socket.io"
)

func main() {

	server := socketio.NewServer(nil)

	server.OnConnect("/", func(socket socketio.Conn) error {
		socket.SetContext("")
		log.Println("connected:", socket.ID())
		return nil
	})

	server.OnEvent("/", "notice", func(socket socketio.Conn, msg string) {
		log.Println("notice:", msg)
		socket.Emit("reply", "have "+msg)
	})

	server.OnEvent("/chat", "msg", func(socket socketio.Conn, msg string) string {
		socket.SetContext(msg)
		return "recv " + msg
	})

	server.OnEvent("/", "bye", func(socket socketio.Conn) string {
		last := socket.Context().(string)
		socket.Emit("bye", last)
		socket.Close()
		return last
	})

	server.OnError("/", func(socket socketio.Conn, e error) {
		// server.Remove(s.ID())
		log.Println("meet error:", e)
	})

	server.OnDisconnect("/", func(socket socketio.Conn, reason string) {
		// Add the Remove session id. Fixed the connection & mem leak
		log.Println("closed", reason)
	})

	go func() {
		if err := server.Serve(); err != nil {
			log.Fatalf("socketio listen error: %s\n", err)
		}
	}()
	defer server.Close()

	http.Handle("/socket.io/", server)
	log.Println("Serving at localhost:8000...")
	log.Fatal(http.ListenAndServe(":8000", nil))
}

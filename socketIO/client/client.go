package main

import (
	"log"
	"time"

	socketio "github.com/googollee/go-socket.io"
)

func main() {

	uri := "http://127.0.0.1:8000"

	client, err := socketio.NewClient(uri, nil)
	if err != nil {
		panic(err)
	}

	// Handle an incoming event
	client.OnEvent("reply", func(s socketio.Conn, msg string) {
		log.Println("Receive Message /reply: ", "reply", msg)
	})
	client.OnConnect(func(socket socketio.Conn) error {
		log.Println("Connect")
		return nil
	})
	client.OnDisconnect(func(s socketio.Conn, msg string) {
		log.Println("Disconnect", msg)
	})
	client.OnError(func(socket socketio.Conn, e error) {
		log.Println("meet error:", e)
	})

	err = client.Connect()
	if err != nil {
		panic(err)
	}

	client.Conn.
		client.Emit("notice", "hello")
	time.Sleep(1 * time.Second)

	err = client.Close()
	if err != nil {
		panic(err)
	}
	time.Sleep(1 * time.Second)

}

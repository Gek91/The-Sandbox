
package main

import (
	"github.com/golang-jwt/jwt/v4"
	"fmt"
)

func main() {

	tokenString := "eyJhbGciOiJSUzI1NiIsImtpZCI6IjBlNzJkYTFkZjUwMWNhNmY3NTZiZjEwM2ZkN2M3MjAyOTQ3NzI1MDYiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20iLCJhenAiOiIzMjU1NTk0MDU1OS5hcHBzLmdvb2dsZXVzZXJjb250ZW50LmNvbSIsImF1ZCI6IjMyNTU1OTQwNTU5LmFwcHMuZ29vZ2xldXNlcmNvbnRlbnQuY29tIiwic3ViIjoiMTE3NDk0ODY0NjEwMDY1NzU0OTgzIiwiZW1haWwiOiJnZWsucGFuZGluaUBnbWFpbC5jb20iLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwiYXRfaGFzaCI6IjVwZWREMmNCVEcwcVUwdVI5b1Z2TEEiLCJpYXQiOjE3MDE0MjAwMzQsImV4cCI6MTcwMTQyMzYzNH0.DWpShEPUcDaXmcaI0k1eHImzej2R4p8qPAAVY07hJwZoh3-8Bn-fnWU0NbgM5A3oZAkTQG6p4DWrC7AuzjJMXs4zXD6kmKzxcV_bqWrab-Sis7YI8hmoeiTYZHPwXhysRZ7rpKnve3_98hlD2b913nCNIFYaNUXqUlhIYRyygyh0cAccaOU36pUBalq9C89kHopStp6vD3yM_JylUn07cU4GGZviNXcolFT0Bjc93NAdb1qwjI_nlnU8EjJotlqMmo-q-2mVaFkA0e8HtlOjY7FGa3YdDF4O7D5jjyYlhqTvefVzlM88LwxxsBweTnjh52EU393ss-rWbMMSIhfYjA"

    token, _, err := new(jwt.Parser).ParseUnverified(tokenString, jwt.MapClaims{})
	if err != nil {
		fmt.Println(err)
		return
	}

	if claims, ok := token.Claims.(jwt.MapClaims); ok {
		fmt.Println("exp", claims["exp"])
		fmt.Println("email", claims["email"])
		fmt.Println("iat", claims["iat"])
		fmt.Println("exp", claims["exp"])
		fmt.Println("aud", claims["aud"])
	} else {
		fmt.Println(err)
	}
}



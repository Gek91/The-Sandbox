FROM golang:1.21

WORKDIR /app

COPY go.mod go.sum ./

RUN go mod download

COPY *.go ./

RUN go build -o /go-app

EXPOSE 8080

ENTRYPOINT ["/go-app"]
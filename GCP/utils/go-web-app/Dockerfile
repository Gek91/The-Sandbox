FROM golang:alpine as builder
WORKDIR /app
COPY go.mod go.sum ./
RUN go mod download
COPY *.go ./
RUN go build -o /go-app

FROM alpine:latest
COPY --from=builder /go-app ./go-app
EXPOSE 8080

ENTRYPOINT ["/go-app"]
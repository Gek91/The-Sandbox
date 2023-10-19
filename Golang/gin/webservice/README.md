in the main folder <code>go get</code>  To get all the dependencies<br>
<code> go run . </code> to run the code

<code>curl http://localhost:8080/albums </code><br>
to get albums list

<code>curl http://localhost:8080/albums \
--include \
--header "Content-Type: application/json" \
--request "POST" \
--data '{"id": "4","title": "The Modern Sound of Betty Carter","artist": "Betty Carter","price": 49.99}'</code><br>
to add new album


<code>curl http://localhost:8080/albums/2 </code><br>
to get a specific album by id
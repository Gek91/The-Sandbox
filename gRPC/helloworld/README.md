[This code's documentation lives on the grpc.io site.](https://grpc.io/docs/languages/python/quickstart)


to generate grpc code (-I -> protos folder)
<code>python -m grpc_tools.protoc -Iprotos --python_out=. --pyi_out=. --grpc_python_out=. protos/helloworld.proto</code>

run server
<code> python greeter_server.py</code>

run client
<code> python greeter_client.py</code>>
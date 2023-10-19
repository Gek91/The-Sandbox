In this example we create a new go module and import local and third party modules

generate the module using <code>go mod init example/hello</code> with example/hello that is the module name

This will generate the file go.mod 

To use external module need to added as dependency requirement <br>
<code>go mod tidy</code>

<code>go run .</code> executes the main function of the method main of the module

---

Consume another personal module
Normally the second module greetings must be published ed imported like a third party module as done before.
To use local modules you can redirect the Go tools from the normal main module path to the local directory of the imported module<br>
<code>go mod edit -replace example/greetings=../greetings</code><br>
and then run <code>go mod tidy</code><br>

<code>go run .</code> executes the main function of the method main of the module


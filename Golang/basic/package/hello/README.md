In this example we create a package and import a local package and a remote one

generate the module using <code>go mod init example/hello</code> with example/hello that is the module name
<br>
in this folder it's defined the package main.

Under the folder morestrings it's defined the package morestring
<br>
Its import path is the module path + subfolder path -> <code>example/hello/morestrings</code>

For the local package we need build it using <br>
<code> go build</code> in the new package folder

For the remote package we need to import the external module that contains it.
<br>
<code>go mod tidy</code>

Now we can run the main 
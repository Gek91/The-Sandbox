
to build a package is needed to include the definition <code>build-system</code> in the pyproject.toml.
A build system determines how the project should be packaged and installed. uv uses the presence of a build system to determine if a project contains a pakcage that should be installed in the project virutal enviroment. If a build system is defined uv will build and install the project into the project enviroment.

In this case we will add the follow build configuration to the package project (imported) .toml
<code>
[build-system]
requires = ["hatchling"]
build-backend = "hatchling.build"
</code>

then we can run <code> uv build</code> and it will create in the dist/ folder the builded package



useful link
- https://sarahglasmacher.com/how-to-build-python-package-uv/
- https://packaging.python.org/en/latest/guides/tool-recommendations/#build-backends
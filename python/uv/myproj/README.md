
inizialize project <code>uv init myproj<code> or using <code>uv init</code> directly in the working directory.
It will create several files that compose the project structure. These parts work together to manage the project
- .toml: contains metadata about the project, it is used to specify dependencies and details about the project. It can be edited manually or using <code>uv add / uv remove </code>
- uv.lock: cross-platform lockfile that contains information about the project dependecies. Contains the exact resolved versions installatet in the project enviroment. Should be checkd in version control allowing consistent and reproducible installation across machines
- .vevn: contains project virutal environment isolatet from the rest of the system. uv install project's dependencies here
- .python-version: contains the project's default Python version


To manage dependencies is used the commands <code>uv add/remove 'requests==2.31.0'</code> with the possibility to specify version constraint
To upgrade a package use <code>uv lock --upgrade-package requests</code> and uv will attempt to update the specified package to tle latest compatible version

<code>uv run</code> can be used to run script or command in the project enviroment. uv will verify that the lockfile is up-to-date with the .toml and that the enviroment is up-to-date with the lockfile priori to execute.  This keep the project in sync without the need for manual intervention.
Alternatively it is possible to manullay update the enviroment using <code>uv sync</code> amd the acrivate it (source ...activate) before executing the command (without uv run)

<code>uv build</code> can be used to build source distribution and binary distribution (wheel) for the project. It will build the project in the current directory and place the build artifact in a dist/ subdirectory

Now is possible to import the package locally using its path from the executor uv project <code>python3 -m pip install ../imported/dist/imported-0.1.0-py3-none-any.whl</code>
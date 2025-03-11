### Managing single script ###
Standalone execution of a file. Normally using <code>uv run script.py</code> without creating uv project

it's possibile to pass argument <code> un run script.py hello</code>

Script can be passed directly from stdin <code>echo 'print("hello world!")' | uv run - </code>

To run script that requeres dependecies they must be istalled in the enviroment that the script runs in.
uv create a on-demand enviroment uing the <code>-- with</code> option.
In the example -> <code>uv run --with 'rich>12,<13' script.py</code> with the possibility to give specific version of the dependecy. Multiple ependencies can be requested repeating the option --with.
Using a project the dependencies can be passed directly in its configuration.

It's also possible creating a script that define with inline matadate its dependecies. uv support the creation of this inline metadata using <code>uv add --script metadata_script.py 'requests<3' 'rich'</code> and the use <code>uv run metadata_script.py</code> to execute

to declare an alternative package index can be used the option <code>--option https://example.com/simple"</code>

uv support also locking dependencies creating a uv.lock file. Using script this must be explicitly activated using <code>uv lock --script metadata_script.py</code>. Subsequent operation will reuse the locked dependecies updating the lockfile if necessary

uv allows arbitrary Python version to be used in script invocation using <code>uv run --python 3.10 python_version.py</code>
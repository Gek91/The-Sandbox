

import moduletoimport
import importalias as alias
from othermodule import *

def main():
    function() #from othermodule
    alias.function()
    moduletoimport.function()

if __name__ == '__main__':
    main()
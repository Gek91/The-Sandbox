import mypackage.module
from mypackage import othermodule


def main():
    mypackage.module.function()
    othermodule.anotherfunction() #no need of package name

if __name__ == '__main__':
    main()
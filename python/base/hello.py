#!/usr/bin/env python

# import modules
import sys

# main() function
def main():
    print('Hello there', sys.argv[1])
    # Command line args are in sys.argv[1], sys.argv[2] ...
    # sys.argv[0] is the script name

# Standard boilerplate to call the main()
if __name__ == '__main__':
    main()
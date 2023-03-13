def main():
   
    colors = ['red', 'blue', 'green']
    print(colors)

    print(sorted(colors))
    print(sorted(colors, key=len)) #built-in function
    print(sorted(colors, key=getLastChar)) #custom function

def getLastChar(s) :
    return s[-1]

if __name__ == '__main__':
    main()
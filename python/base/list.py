def main():
   
    colors = ['red', 'blue', 'green']
    print(colors[0])    
    print(len(colors))  

    #for syntax with list
    for color in colors:
        print(color)

    #in operator
    print('red' in colors)

    #list comprehension
    uppercolors = [s.upper() + '!' for s in colors]
    print(uppercolors)

    #combine with if
    nums = [2, 8, 1, 6]
    small = [ n for n in nums if n <= 2 ]
    print(small)

if __name__ == '__main__':
    main()
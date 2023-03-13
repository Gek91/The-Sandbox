def main():
   
    colors = ['red', 'blue', 'green']

    #append
    colors.append('yellow')

    #insert
    colors.insert(0, 'black')

    #extends, same effect of '+'
    colors.extend(['white', 'pink'])
    print(colors)

    #remove
    colors.remove('white')
    print(colors)

    #sort
    colors.sort()
    print(colors)

    #reverse
    colors.reverse()
    print(colors)

    #pop
    print(colors.pop()) #last element
    print(colors.pop(2))
    print(colors)

    #index
    print(colors.index('red'))

if __name__ == '__main__':
    main()
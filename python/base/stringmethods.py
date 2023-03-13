
def main():
    
    s = 'hello'

    #lower upper
    print(s.lower() + s.upper())

    #strip
    print(' is like trim '.strip())

    #isalpha isnum    
    print(s.isnumeric())
    print(s.isalpha())

    #starswith endswith
    print(s.startswith('lo'))
    print(s.endswith('lo'))

    #find
    print(s.find('lo'))

    #replace
    print(s.replace('he','me'))

    #split
    print(s.split('l'))

    #join
    print(s.join(['giorgio','gianni']))

if __name__ == '__main__':
    main()
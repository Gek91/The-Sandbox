

def main():
    s = 'hi'
    #element access
    print(s[1])
    #string lenght
    print(len(s))

    #concat
    print(s + ' there')
    #repeat
    print(s * 3)

    #multiline string
    print(''' 
        ciao
        hello
        salut
        ''')

    pi = 3.14
    print('pi is ' + str(pi)) #str convertion needed

    #raw string
    print('this\t\n and that') #normal
    print(r'this\t\n and that') #raw

    #byte string
    bytestring = s.encode('utf-8')
    print(bytestring)
    newstring = bytestring.decode('utf-8')
    print(newstring)

if __name__ == '__main__':
    main()
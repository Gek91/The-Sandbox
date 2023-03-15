

def main():
  
    value = 2.7894
    print(f'value : {value:.2f}')

    #dictionary
    car = {'tires': 4, 'doors': 3}
    print(f'{car}')
    print(f'tires:{car["tires"]} doors:{car["doors"]}')


    #operator %
    print("hello, %s" % "john")
    print("list: %s" % [1,2,3])
    #multiple value
    text = ( 
    "%d little pigs come out, "
    "or I'll %s, and I'll %s, "
    "and I'll blow your %s down."
    % (3, 'huff', 'puff', 'house'))
    print(text)

    #operation % with dictionary
    h = {} #dictionary
    h['word'] = 'garfield'
    h['count'] = 42
    s = "I want %(count)d copies of %(word)s" % h
    print(s)
    s = "I want {count:d} copies of {word}".format(h) #alternative notation
    print(s)

    
if __name__ == '__main__':
    main()
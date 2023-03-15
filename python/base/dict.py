def main():
   
    dict = {} #empty
    dict['a'] = 'alpha'
    dict['g'] = 'gamma'
    dict['o'] = 'omega'
    print(dict)
    print(dict['a'])
    print('a' in dict)
    print('alpha' in dict) #not a key
    
    print(dict.keys())
    print(dict.values())
    print(dict.items())

    for key in dict.keys() : print(key)
    for k,v in dict.items(): print(k, '>', v)

    #pop, get value and remove from dict
    print(dict.pop('g'))

    #del
    print(len(dict))
    print('a' in dict)
    del dict["a"]
    print('a' in dict)
    print(len(dict))

    #alternative initialization notation
    dict = {
        "a": "lpaha",
        "b": "beta",
        "c": "gamma"
    }
    
if __name__ == '__main__':
    main()
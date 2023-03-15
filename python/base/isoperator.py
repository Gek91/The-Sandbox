def main():
   
    x = [1,2,3]
    y = [1,2,3]
    z = x

    print(x == y)
    print(x is y) #false, pointer to different list
    print(x is z) #true, same list

    x = 4
    y = 4

    print(x == y)
    print(x is y) #true

    isFalse = False

    #is on boolean
    print(isFalse is True)
    print(isFalse is False)


if __name__ == '__main__':
    main()
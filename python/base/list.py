def main():
   
    colors = ['red', 'blue', 'green']
    print(colors[0])    
    print(len(colors))  

    #for syntax with list
    for color in colors:
        print(color)

    #in operator
    print('red' in colors)

    #list concatenation
    print([1,2,3] + [4,5,6])

    #list repeat
    print([1,2] * 3)


    #combine with if
    nums = [2, 8, 1, 6]
    small = [ n for n in nums if n <= 2 ]
    print(small)

    #del
    print(len(nums))
    print(2 in nums)
    del nums[0]
    print(2 in nums)
    print(len(nums))

if __name__ == '__main__':
    main()
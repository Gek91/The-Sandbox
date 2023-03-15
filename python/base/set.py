def main():
   
    colors = set(['red', 'blue', 'green'])
    print(colors)    
    print(len(colors))  

    othercolors = set(['red', 'white'])

    #intersection
    print(colors.intersection(othercolors))
    print(othercolors.intersection(colors))

    #simmetric difference
    print(colors.symmetric_difference(othercolors))
    print(othercolors.symmetric_difference(colors))

    #difference
    print(colors.difference(othercolors))
    print(othercolors.difference(colors))

    #union
    print(colors.union(othercolors))
if __name__ == '__main__':
    main()
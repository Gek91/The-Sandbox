

#yeld is used to return a generator
#has the same sintax of the return statement
#generator function stop the execution as it reches the yeld statement and return the generator
# when the caller iterate over the generator the execution continues until the next yeld statement
#yeld has better memory control over overhead allocation

def filter_odd(numbers):
   for number in range(numbers):
       if(number%2!=0):
           yield number 



def main() :
    odd_numbers = filter_odd(20)

    print(list(odd_numbers))

    #Alternative use next() on generator
    #generator can be used only once, then they are empty
    odd_numbers = filter_odd(20)

    print(next(odd_numbers))
    print(next(odd_numbers))
    print(next(odd_numbers))
    print(next(odd_numbers))
    print(next(odd_numbers))
    print(next(odd_numbers))



if __name__ == '__main__':
    main()
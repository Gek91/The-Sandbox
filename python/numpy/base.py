
import numpy as np

def main():
    matrix = np.arange(15).reshape(3,5)
    print(matrix)

    #array info
    print(matrix.ndim) #dimension number
    print(matrix.shape) #dimensions size
    print(matrix.dtype.name) #type
    print(matrix.itemsize)
    print(matrix.size) #elements

    #array creation
    print(np.array([2,3,5]))
    print(np.array([(1,2,3), (4,5,6)]))

    print(np.zeros((3,4))) # arrays of zero of dimension 3 5
    print(np.ones((2,3,2))) # arrays of ones of dimension 2 3 2
    print(np.empty((4,5))) # arrays with random content of dimension 4 5

    print(np.arange(2,4, 0.3)) #works like range (start, stop, step)
    print(np.linspace(0, 2, 9)) #like to arange (start, stop, number of element)

    rg = np.random.default_rng(1)
    print(rg.random((2,3))) #random generated matrix


    #Indexing
    a = np.arange(10)**3
    print(a)
    a[2] #normal list indexing and slicing 

    #generate from function
    b = np.fromfunction(lambda x,y: x * 10 + y, (5,4))
    print(b)
    print(b[2,3]) #like indexing, in matrix with more than 2 dimension is equivalent to b[2,3, ...] or b[2,3, : , : , : ] (with number of : to reach the number of dimensions)
    print(b[0:5,1]) #slicing
    print(b[1:3, :])

    #loops
    for row in b:
        print(row)

    for element in b.flat: #flat is an iterator over all element of the array
        print(element)

if __name__ == '__main__':
    main()



import numpy as np

def main():
   
    a = np.arange(12)**2
    print(a)

    indexarray = np.array([1,1,3,8,5])
    print(a[indexarray]) #indexing using array of index

    indexarray = np.array([[3,4],[9,7]]) #bidimensional array
    print(a[indexarray])

    #multidimensional
    a = np.array([
        [0,0,0],
        [255,0,0],
        [0,255,0],
        [0,0,255],
        [0,0,0]
    ])

    indexarray = np.array([[0,1,2,0],[0,3,4,0]]) #bidimensional array
    print(a[indexarray]) #for every array in the input generate a matrix of row at the index spcifiy in the element of the input 
    print(a[indexarray, 2]) #for every array in the input generate a value of the row at the index specify in the element and select the element of the row at index 2
    
    print(a[indexarray,  np.array([[1,2,0,1],[1,2,0,1]])])

    #using boolean
    a = np.arange(12).reshape(3, 4)
    print(a)
    b = a > 4
    print(b)
    print(a[b]) #return the element with true


    print(a[
        np.array([False, True, True]),
        np.array([True, False, True, False])
    ])

    #ix function, transform arrays in matrix to be used in arrays operation
    #example: calculate a+b*c for all triplet in three array a,b,c
    a = np.array([2, 3, 4, 5])
    b = np.array([8, 5, 4])
    c = np.array([5, 4, 6, 8, 3])

    ax,bx,cx = np.ix_(a,b,c)
    print(ax.shape, bx.shape, cx.shape)
    print(ax,bx,cx)

    result = ax +bx * cx
    print(result)

    print(result[3,2,4])

if __name__ == '__main__':
    main()



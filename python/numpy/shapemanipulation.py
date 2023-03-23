import numpy as np

def main():
   
    rg = np.random.default_rng(1)
    a = np.floor(10 * rg.random((3, 4)))
    print(a)
    print(a.shape)

    #the functions return a new array, they do not change the old one

    #flat the array
    print(a.ravel())
    #reshape, 
    print(a.reshape(6,2))
    print(a.reshape(4, -1))# the second dimension is calculated
    #trasposition
    print(a.T)
    print(a.T.shape)

    #like reshape but modify the same array 
    a.resize(2,6)
    print(a)
    print(a.shape)

    #stacking
    a = np.floor(10 * rg.random((2, 2)))
    b = np.floor(10 * rg.random((2, 2)))
    print(np.vstack((a,b))) #stack vertically
    print(np.hstack((a,b))) #stack horizzontaly
    
    #column stack
    np.column_stack((a, b)) #works like hstack on 2D array 
    a = np.array([4., 2.])
    b = np.array([3., 8.])
    c = np.array([7., 5.])
    print(np.column_stack((a, b, c))) #on D1 array every array is a column
    #row_stack is an alias of vstack
    np.row_stack((a,b))

    #splitting
    a = np.floor(10 * rg.random((2, 6)))
    print(a) #2x6
    print(np.hsplit(a,3)) #split on horizontal axis -> 2x2 2x2 2x2
    print(np.hsplit(a, (3,4))) #splig after the 3 and 4 column -> 3x2 ,1x2, 2x2
    print(np.vsplit(a,1)) # split on vertical axis -> 6x1, 6x1
if __name__ == '__main__':
    main()



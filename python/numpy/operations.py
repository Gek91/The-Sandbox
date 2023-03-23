import numpy as np

def main():
   
    a = np.array([20, 30, 40, 50])
    b = np.arange(4)
   
    print(a - b)

    print(b**2)

    print(b > 2)

    #matrix product
    A = np.array([
        [1, 1],
        [0, 1]]
        ) # matrix 2x2
    B = np.array([
        [2,0], 
        [3,4]]
        )
    
    print(A*B) #element product
    print(A @ B) #matrix product

    #reduce fumctions
    print(a.sum())
    print(a.max())
    print(a.min())
    print(a.cumsum())

    #reduce on axis
    c = np.arange(12).reshape(3, 4)
    print(c.sum(axis=0)) #column
    print(c.min(axis=1)) #row
    print(c.cumsum(axis=1)) #progression of the sum on elements of the matrix

if __name__ == '__main__':
    main()



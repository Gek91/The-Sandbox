
import numpy as np

def main():

    a = np.array([1.0, 2.0, 3.0])
    b = np.array([2.0, 2.0, 2.0])

    #no broadcasting
    print(a * b) #same dimesion

    print(a * 2.0) #broadcasting
    
    a.resize(1,2,3)
    b.resize(3,2,1)
    c = a * b
    print(c.shape) #get the greatest value for every dimension.
    # no error because the dimensions are equals or one of the two are 1 

    b.resize(2,3)
    #b.resize(1,2) #error, dimension values are not equals to the righmost a dimension values (2,3)
    c = a * b
    print(c.shape) #extends the second array starting with array alligned to the right

    #matrix example
    a = np.array([[ 0.0,  0.0,  0.0],
              [10.0, 10.0, 10.0],
              [20.0, 20.0, 20.0],
              [30.0, 30.0, 30.0]])
    b = np.array([1.0, 2.0, 3.0])
    print(a+b) #correct broadcasting

    

if __name__ == '__main__':
    main()



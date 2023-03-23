import numpy as np

def main():
   
    a = np.array([
                [ 0,  1,  2,  3],
                [ 4,  5,  6,  7],
                [ 8,  9, 10, 11]
                ])
    
    copy = a.copy()

    print(copy is a)
    print(copy is a.base)

    copy[1,0] = 1234 #change a value
    print(a[1])
    print(copy[1])

    

if __name__ == '__main__':
    main()

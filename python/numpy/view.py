import numpy as np

def main():
   
    a = np.array([
                [ 0,  1,  2,  3],
                [ 4,  5,  6,  7],
                [ 8,  9, 10, 11]
                ])
    
    view = a.view()

    print(view is a)
    print(view is a.base)

    view.resize(2,6) #a shape doesn't change
    print(view.shape)
    print(a.shape)

    view[0,4] = 1234 #change a value
    print(a[1])

    #slicing creates views
    view = a[1]
    view[0] = 123
    print(a[1])

    

if __name__ == '__main__':
    main()



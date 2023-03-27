import numpy as np

def main():
   
    x = np.array([('Rex', 9, 81.0), ('Fido', 3, 27.0)],
             dtype=[('name', 'U10'), ('age', 'i4'), ('weight', 'f4')]) #dtype define the struct
    
    print(x)
    print(x['age'])

if __name__ == '__main__':
    main()
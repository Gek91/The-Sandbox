
def mygenerator() :
    for i in range(3):
        yield i*2
    
    for i in range(3):
        yield i*3

def main():
   
   for elem in mygenerator():
       print(elem)
    
    
if __name__ == '__main__':
    main()
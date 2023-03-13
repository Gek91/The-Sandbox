def main():
   
   #last number not included
    myrange = range(5)  #return an object range iterable
    print(myrange) #print 'range(5)'
    print(list(myrange)) #end
    print(list(range(2,5))) #stard and end
    print(list(range(2,5,2))) #start end and step

    for i in myrange:
        print(i)

if __name__ == '__main__':
    main()
def main():
   
    x = 12

    if x > 0:
        print('>0')
    elif(x < 0):
        print('<0')
    else:
        print('=0')

    if x%2 == 0: print('even')
    else: print('odd')

    #loops
    i = 0
    while i < 5:
        print(i)
        i = i + 1

    for elem in [2,3,5,7]:
        print(elem)

    #else in loops
    count=0
    while(count<5):
        print(count)
        count +=1
    else:
        print("count value reached %d" %(count))

    for i in range(1, 10):
        if(i%5==0):
            break
        print(i)
    else:
        print("this is not printed because for loop is terminated because of break but not due to fail in condition")

if __name__ == '__main__':
    main()
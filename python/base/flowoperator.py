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

    i = 0
    while i < 5:
        print(i)
        i = i + 1

if __name__ == '__main__':
    main()
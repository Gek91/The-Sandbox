
def main():
   
   s = 'hello'

   print(s[:]) 
   print(s[1:])
   print(s[:2])
   print(s[1:3]) #start:end

   #negative notation
   print(s[:-1])
   print(s[-3:-1])

   #using step
   print(s[0:4:2]) #start:end:step
   print(s[0:4:1])

   print(s[::-1]) #reverse

if __name__ == '__main__':
    main()
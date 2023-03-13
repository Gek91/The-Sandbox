
def main():

    file = open('foo.txt', 'w');

    file.write("canc! some value\n")
    file.write("other value\n")

    #position in the file
    print(file.tell())

    #move writing cursor
    file.seek(0)
    file.write("begin\n")

    print(file.tell())

    file.close()


    file = open('foo.txt', 'r');

    for line in file:
        print(line)

    print(file.closed)
    file.close()
    print(file.closed)

if __name__ == '__main__':
    main()
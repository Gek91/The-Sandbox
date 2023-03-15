def main():

    colors = ['red', 'blue', 'green']
    uppercolors = [s.upper() + '!' for s in colors]
    print(uppercolors)


    sentence = "the quick brown fox jumps over the lazy dog"

    word_lengths = [len(word) for word in sentence.split() if word != "the"]
    print(word_lengths)

if __name__ == '__main__':
    main()
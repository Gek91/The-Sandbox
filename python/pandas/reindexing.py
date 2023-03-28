import numpy as np
import pandas as pd

def main():

    #series reindex
    s = pd.Series(np.random.randn(5), index=["a", "b", "c", "d", "e"])
    print(s)
    s = s.reindex(["e", "b", "f", "d"])
    print(s)

    #dataframe reindex
    df = pd.DataFrame(np.random.randn(4,3), columns=["A","B","C"])
    print(df)
    df = df.reindex(index=[1, 3, 2], columns=["A", "D", "C"])
    print(df)

    #align
    s = pd.Series(np.random.randn(5), index=["a", "b", "c", "d", "e"])
    print(s)
    s1 = s[:4]
    s2 = s[1:]
    print(s1)
    print(s2)
    print(s1.align(s2)) #outer
    print(s1.align(s2, join="inner"))
    print(s1.align(s2, join="left"))
    print(s1.align(s2, join="right"))



if __name__ == '__main__':
    main()
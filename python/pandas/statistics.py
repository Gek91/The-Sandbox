import numpy as np
import pandas as pd

def main():
   
    df = pd.DataFrame(np.random.randn(4,3), columns=["A","B","C"])
    print(df)
    #operations on dataset need the referenced axis (0,1)
    print(df.mean(0))
    print(df.mean(1))

    print(df.sum(skipna=True)) #skipping Nan numbers

    #standardization of matrix
    ts_stand = (df-df.mean()) /df.std() 
    print(ts_stand.std()) #column
    xs_stand = df.sub(df.mean(1), axis=0).div(df.std(1), axis=0)
    print(xs_stand.std(1)) #row

    #statistical summary
    print(df.describe())

    #min max
    print(df.max())
    print(df.idxmax())
    print(df.min())
    print(df.idxmin())

    #count
    s = pd.Series(np.random.randint(0, 7, size=50))
    print(s.value_counts())
    print(s.mode())

    #discretization
    arr = np.random.randn(20)
    print(pd.cut(arr, 4)) #categories
    print(pd.value_counts(pd.cut(arr, 4)))

    print(pd.qcut(arr, 4)) 
    print(pd.value_counts(pd.qcut(arr, 4)))


if __name__ == '__main__':
    main()


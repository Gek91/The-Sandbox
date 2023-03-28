import numpy as np
import pandas as pd

def dataframefunc1(df) : 
    #some stuff
    return df

def dataframefunc2(df, input=None) :
    #some stuff
    return df

def main():
   
    df = pd.DataFrame(np.random.randn(4,3), columns=["A","B","C"])
    
    #table wise
    #pipe sintax -> execute chain of function on dataframe
    df.pipe(dataframefunc1).pipe(dataframefunc2, input="input")

    #row and column wise
    #apply
    print(df.apply(np.mean))
    print(df.apply(np.mean, axis=1))

    #aggregation API
    print(df.agg(np.sum)) #similar to appy with single function
    print(df["A"].agg(np.sum))

    print(df.agg([np.sum, np.mean])) #more than 1 function
    print(df["A"].agg([np.sum, np.mean]))

    print(df.agg({"A" : [np.sum, np.mean], "B": np.sum})) #apply different function on the specified column

    #Transform API
    #allow to provider multiple operatione
    print(df.transform(np.abs))
    print(df["A"].transform(np.abs))

    print(df.transform([np.abs, lambda x: x + 1]))
    print(df["A"].transform([np.abs, lambda x: x + 1]))

    print(df.transform({ "A":np.abs, "B": [np.abs, lambda x: x + 1]}))

    #Element wise
    #applymap
    print(df.applymap(lambda x: x +1))
    print(df["A"].map(lambda x: x+1))


if __name__ == '__main__':
    main()


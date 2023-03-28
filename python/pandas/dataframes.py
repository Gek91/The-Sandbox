import numpy as np
import pandas as pd

def main():
    
    d = {
        "one": pd.Series([1.0, 2.0, 3.0], index=["a", "b", "c"]),
        "two": pd.Series([1.0, 2.0, 3.0, 4.0], index=["a", "b", "c", "d"]),
    }   

    dataframe = pd.DataFrame(d, index=["a", "b", "c", "d"], columns=["one", "two"])
    print(dataframe)

    #build from dict
    d = {"one": [1.0, 2.0, 3.0, 4.0], "two": [4.0, 3.0, 2.0, 1.0]} 
    dataframe = pd.DataFrame(d)
    print(dataframe)
    #build from list of dict
    d = [{"a": 1, "b": 2}, {"a": 5, "b": 10, "c": 20}]
    dataframe = pd.DataFrame(d)
    print(dataframe)

    #select by column
    print(dataframe["c"])

    dataframe["flag"] = dataframe["c"] > 10
    print(dataframe)

    dataframe["new"] = dataframe["a"][:1]
    print(dataframe)

    del dataframe["b"]

    #insert(position, column name, value (scalar))
    dataframe.insert(1,"column",1)
    print(dataframe)

    #assing -> return a copy of the data adding a calculated column, orginal dataframe unchanged
    #assign recive an **kwargsas input or a function with one argument to be called on the dataframe, return a copy of the dataframe
    dataframe = dataframe.assign(new_column= lambda x : x["a"] + x["c"])
    print(dataframe)



    d = {
        "one": pd.Series([1.0, 2.0, 3.0], index=["a", "b", "c"]),
        "two": pd.Series([1.0, 2.0, 3.0, 4.0], index=["a", "b", "c", "d"]),
    }   
    dataframe = pd.DataFrame(d, index=["a", "b", "c", "d"], columns=["one", "two"])
    print(dataframe)
    
    #indexing
    print(dataframe["one"]) #column by label
    print(dataframe.iloc[0]) #row by location (interger index)
    print(dataframe.loc["c"]) #row by label
    print(dataframe[0:2]) #slice on row

    #alignment on aritmetic operation
    df1 = pd.DataFrame(np.random.randn(10, 4), columns=["A", "B", "C", "D"])
    df2 = pd.DataFrame(np.random.randn(7, 3), columns=["A", "B", "C"])
    print(df1 + df2)
    #dataframe and serie -> allign on column and broadcasting on row
    print(df1- df1.iloc[1]) 
    #scalar operation
    print(df1 * 2)
    #boolean operation
    df1 = pd.DataFrame({"a": [1, 0, 1], "b": [0, 1, 1]}, dtype=bool)
    df2 = pd.DataFrame({"a": [0, 1, 1], "b": [1, 1, 0]}, dtype=bool)
    print(df1 & df2)
    print(df1 | df2)

    #operation add,sub,mul,div to specify the axis (index, columns)
    df = pd.DataFrame(
        {
            "one": pd.Series(np.random.randn(3), index=["a", "b", "c"]),
            "two": pd.Series(np.random.randn(4), index=["a", "b", "c", "d"]),
            "three": pd.Series(np.random.randn(3), index=["b", "c", "d"]),
        }
    )
    row = df.iloc[1]
    print(df.sub(row, axis="columns")) #axis=1
    column = df["two"]
    print(df.sub(column, axis="index")) #axis=0

    #transposition
    print(df1[:5])
    print(df1[:5].T)

    #numPy functions
    print(np.exp(dataframe))

    #dataframe info
    print(dataframe.info())

    #direct acces to column
    print(dataframe.one)
    print(dataframe.two)

    #get row data, often an ndarray
    dataframe.to_numpy()

    #combine first -> combine 2 dataframe getting the seconds one values only when in the first one there is Nan at specific position
    df1 = pd.DataFrame(
        {"A": [1.0, np.nan, 3.0, 5.0, np.nan], "B": [np.nan, 2.0, 3.0, np.nan, 6.0]}
    )
    df2 = pd.DataFrame(
        {
            "A": [5.0, 2.0, 4.0, np.nan, 3.0, 7.0],
            "B": [np.nan, np.nan, 3.0, 4.0, 6.0, 8.0],
        }
    )
    print(df1)
    print(df2)
    print(df1.combine_first(df2))

    #combine first call the more general combine
    #combine(dataframe, combinefunction)
    def combiner(x, y):
        return np.where(pd.isna(x), y, x)
    #same as combine_first
    print(df1.combine(df2, combiner))

if __name__ == '__main__':
    main()
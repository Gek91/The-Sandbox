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

    #indexing
    dataframe["one"] #column by label
    dataframe.iloc[0] #row by location (interger index)
    dataframe.loc["c"] #row by label
    dataframe[0:1] #slice on row

if __name__ == '__main__':
    main()
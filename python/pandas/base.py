import numpy as np
import pandas as pd

def main():
   
    index = pd.date_range("1/1/2000", periods=8)
    df = pd.DataFrame(np.random.randn(8, 3), index=index, columns=["A", "B", "C"])
    long_series = pd.Series(np.random.randn(1000))

    #head and tail
    print(long_series.head()) #first 5
    print(long_series.tail()) #last 5
    print(df.head(2)) #first 2
    print(df.tail(2)) #last 2

    #attributes
    print(df.shape)
    print(df.index)
    print(df.columns)

    #filling values
    s1 = pd.Series({"a":1, "b": 0, "c": None, "d": 3}, index=["a", "b", "c", "d"])
    s2 = pd.Series({"a":1, "b": 0, "c": None}, index=["a", "b", "c", "d"]) 
    print(s1+s2) #Nan where the value is missing in one of the operands
    print(s1.add(s2, fill_value=0)) #Nan where the value is missing in both operands

    #comparisons -> eq,ne,lt,gt,le,ge
    print(s1.eq(s2))
    print(s1.gt(s2))

    print((df + df).equals(df * 2)) #needed because Nan == Nan is false
    print(pd.Series(["foo", "bar", "baz"]) == "foo") #element wise comparison

    #boolean reduction empty,any,all,bool
    print((df > 0).all())
    print((df > 0).any())
    print((df > 0).any().any())


if __name__ == '__main__':
    main()


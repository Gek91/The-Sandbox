import numpy as np
import pandas as pd

def main():
   
    s = pd.Series(np.random.randn(5), index=["a", "b", "c", "d", "e"]) #build from ndarray
    print(s)
    print(s.dtype)
    print(s.array) #backing array

    dic = {"b":1, "a": 0, "c": 2}
    s = pd.Series(dic, index=["b", "c", "d", "a"]) #build with dictionary
    print(s)

    print(s["a"]) #using index label like a dictionary
    s["d"] = 3
    print("f" in s)
    print(s.get("f")) #series get method, return None

    print(s[s > 1]) #slicing like NumPy.
    print(s.to_numpy) #convert to numpy ndarray
    print(s * 2)

    #automatically allign index label
    s1 = pd.Series({"a":1, "b":1 , "c": 1})
    s2 = pd.Series({"b":1, "d":1})
    print(s1+ s2) #union of index of the series, value is Nan if the label is not presente in both series


if __name__ == '__main__':
    main()


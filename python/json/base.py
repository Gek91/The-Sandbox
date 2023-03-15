import json

#json structured as list and dictionaries nested inside each other
#it's possible to use standard methods to manipulate JSON strucutre

def main():

    JSONstring = '{"name":"gino","surname":"gini","age":3}'

    JSONobject = json.loads(JSONstring) #convert string to object (deserialization)
    print(JSONobject)
    print(JSONobject["name"])
    
    dir = { "values": [1,2,3], "string":"hello" }

    string = json.dumps(dir) #convert object to string (serialization)
    print(string)
    print(isinstance(string,str))

if __name__ == '__main__':
    main()
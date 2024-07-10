import json
from decimal import Decimal

#encoder for decimal values
class DecimalEncoder(json.JSONEncoder):
    def default(self,obj):
        if isinstance(obj, Decimal):
            return {'__Decimal__': str(obj)}
        return super().default(obj)
    
#decoder for decimal values    
def as_Decimal(dct):
    if '__Decimal__' in dct:
        return Decimal(dct['__Decimal__'])
    return dct

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


    #Decimal
    images= [
    {'type':'big', 'value': Decimal(1)},
    {'type':'big', 'value': Decimal(2)},
    {'type':'big', 'value': Decimal(3)},
    ]
    print (images)

    json_images = json.dumps(images, cls = DecimalEncoder)
    print(json_images)

    res = json.loads(json_images, object_hook=as_Decimal)
    print(res)

if __name__ == '__main__':
    main()
import redis
import os
import json
from decimal import Decimal

redis_host = os.environ.get("REDISHOST", "localhost")
redis_port = int(os.environ.get("REDISPORT", 6379))
redis_client = redis.StrictRedis(host=redis_host, port=redis_port, decode_responses=True)

class DecimalEncoder(json.JSONEncoder):
    def default(self,obj):
        if isinstance(obj, Decimal):
            return {'__Decimal__': str(obj)}
        return super().default(obj)

def as_Decimal(dct):
    if '__Decimal__' in dct:
        return Decimal(dct['__Decimal__'])
    return dct

if __name__ == "__main__":

    #counter increments
    value = redis_client.incr("counter", 1)
    print(f"Visitor number: {value}")


    redis_client.mset({"key1": "value1", "key2": "value2"})

    value = redis_client.get("key1")
    print(value)
    value = redis_client.get("key2")
    print(value)
    value = redis_client.get("key3")
    print(value)

    redis_client.hset('user-session:123', mapping={
        'name': 'John',
        "surname": 'Smith',
        "company": 'Redis',
        "age": 29
    })

    value = redis_client.hgetall('user-session:123')
    print(value)

    value = redis_client.hget('user-session:123', 'name')
    print(value)


    images= [
    {'type':'big', 'url':'....'},
    {'type':'big', 'url':'....'},
    {'type':'big', 'url':'....'},
    { "decimal": Decimal(1)}
    ]

    # Convert python dict to JSON str and save to Redis
    json_images = json.dumps(images, cls = DecimalEncoder)
    redis_client.set('images', json_images)

    # Read saved JSON str from Redis and unpack into python dict
    unpacked_images = json.loads(redis_client.get('images'), object_hook=as_Decimal)
    print(unpacked_images)
    for i in unpacked_images:
        print(i)
    
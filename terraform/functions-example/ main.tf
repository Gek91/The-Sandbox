
locals {
    //toType -> type conersione
    int_string = "1"
    int_value = tonumber(local.int_string)
    list_values = [1, 2, 3]
    se_values = toset(local.list_values)

    //Format -> string formatting
    format1 = format("this id %s", local.int_string)
    format2 = format("this is %d", local.int_value)

    //lenght -> lenght of strings or collections
    string_length = length(local.int_string)
    list_length = length(local.list_values)

    //join -> create a string from the collection's element separated by a specified separator
    join_string = join(",", local.list_values)

    //try -> allow do set a fallback value if the first one is unusable

    try1 = try(local.list_values[10], "fallback")
    try2 = try(local.list_values[1], "fallback")

    //flatten -> flatten a list of list in a single list
    unflatten_list = [[1, 2], [3, 4]]
    flatten_list = flatten(local.unflatten_list)

    //map functions
    map_value = {
        "key1" = "value1",
        "key2" = "value2"
    }

    key_list = keys(local.map_value)
    value_list = values(local.map_value)

    //slice -> create a sublist
    slice_list = slice(local.list_values, 1, 2)
    
    //range -> create a range of numbers
    range_one_arg = range(3)
    range_two_arg = range(1, 3)
    range_three_arg = range(1, 10, 2)

    //lookup -> fallback value for not existing key in a map
    look_up1 = lookup(local.map_value, "key3", "fallback") 
    look_up2 = lookup(local.map_value, "key1", "fallback")

    //concat -> combine two o more list into one
    concat_list = concat([1, 2], [3, 4])

    //merge -> combine two o more maps into one
    merge_map = merge({
        "key1" = "value1",
        "key2" = "value2"
    }, {
        "key3" = "value3",
        "key4" = "value4"
    })  

    //zipmap -> generate a map from a list of keys and a list of values
    zipmap = zipmap(["key1", "key2"], ["value1", "value2"])

    //jsondecode/jsonencode -> intepretes a string as json / encode a value to a string using json
    json_value = jsondecode("{\"hello\":\"world\"}")
    string_json_value = jsonencode({ "hello" = "world"})

    //yamlencode/yamldecode -> intepretes a string as yaml / encode a value to a string using yaml
    yaml_value = yamldecode("hello: world")
    string_yaml_value = yamlencode({ "hello" : "world"})
} 


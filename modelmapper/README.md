http://modelmapper.org/user-manual/

ModelMapper consist of two process:
- Matching: source and destination type properties are matched to each other. Uses convetion configurated in ModelMapper e TypeMap to determine the mapping
    - Eligibility: Based on field and method access level and naming convention. Only method with zero parameters and non void returm (getter) type are eligible in source. Only method with one parameter nad void return type (setter) are eligible in destination.
    - Transformation: transform property name to better matching between source and destination.
    - Tokenization: used for matching
    - Matching: Uses strategies (MatchingStrategies) to determine the matching of properties
- Mapping: matched property values are converted from source to destination.
    - If TypeMap exist for the source and destination type, mapping occur using mapping defined
    - Else If Converter exists that is capable of converting source to destination type, mapping occur using the converter
    - else new Type map is created fo source and destination types, and mapping occur according the implicit mapping created

When the map method is called, the source and destination types are analyzed to determine which properties implicitly match according to a matching strategy and other configuration. Data is then mapped according to these matches.

Even when the source and destination objects and their properties are different, ModelMapper will do its best to determine reasonable matches between properties according to the configured matching strategy.

- Property mapping: can be created to define explicit mapping between source and destination properties (defines mapping between properties)
- Converters: take place of any implicit or explicit mapping between to type. (define a custom logic in convertion)
- Providers: allow to provide instance of destination properties and types prior to mapping instead of having ModelMapper construct them via default constructor.
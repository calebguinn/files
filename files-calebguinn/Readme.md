# Maps

We are going to look at maps.

We have three classes that implement the interface for *Map*:
* HashMap (most popular)
* TreeMap (enties are sorted)
* LinkedHashMap (insertion order is kept)

We can envision this as follows:
|Key|Value|
|---|---|
|StudentId | Program.java|
|1234 | "public class Program{}"|

## Updadate methods
* put
* putAll
* remove
* clear

## Query methods
* containsKey
* containsValue
* isEmpty
* size

### Remember:
The keys of a map form a set.


## Let us work on some examples:

Create a map from entires in two lists:
```java
      List<String> list = Arrays.asList( "one", "two", "three");
        List<Integer> integers = Arrays.asList( 1, 2, 3);

        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < list.size(); ++i){
            map.put(list.get(i), integers.get(i));
        }
```

isEmpty() 

```java
        // Show isEmpty
        if(duplicate.isEmpty()){
            System.out.println("Duplicate is now officialy empty");
        }
        else {
            System.out.println("I must have missed something");
  ```

  # Writing Files

  Files can be classfied as:
  * binary
  * text 

  For now, we can think about text files as sequence of characters,
  and binary files as sequence of bytes.


  # ToDo
 - [ ] (before:) Read  about Enums
 - [x] (and later:) Read  about Enums 
 
 # Create an entry about files
 
 ## File
 
 ## Other classes

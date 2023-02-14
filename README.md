## Table of contents

1. [AGORA overview](#agora-overview)
1. [Generation of declaration file](#generation-of-declaration-file)
1. [Generation of data trace file](#generation-of-data-trace-file)

## AGORA overview
The image below shows an overview of AGORA, an approach for the automated generation of test oracles for REST APIs. At the core of the approach is Beet, a novel Daikon instrumenter. Beet receives two inputs:

1. The OAS specification of the API under test.
1. A set of API requests with their corresponding responses.

As a result, Beet returns an instrumentation of the API requests consisting on a declaration file (describing the format of the API operations inputs and outputs) and a data trace file (specifying the values assigned to each input parameter and response field in each API call). This instrumentation is then processed by our customized version of Daikon, resulting in a set of likely invariants that can be potentially used as test oracles, once confirmed by the developer.

![AGORA workflow](https://i.imgur.com/SdERJNS.png)

The following sections describe the functionality of Beet through a running example.

## Generation of declaration file
A declaration file specifies the structure of all the points of a program, including classes, objects, inputs and outputs of all the methods and functions that compose it. Since we are working in a black box testing context where we do not have access to the API source code, the only types of program points returned by Beet will be those that model the format of the inputs (ENTER) and outputs (EXIT).

To explain in detail the structure of a declaration file and exemplify the instrumentation process without showing redundant information, a simplified version of the "Get Album Tracks" operation of the Spotify API, shown in the Listing below, will be used as an example.

![OAS example](https://i.imgur.com/HZbivGu.png)

This operation receives parameters of different datatypes (`id` and `market` are strings, whereas `limit` is numeric) which are sent in different parts of the HTTP request (path and query). On the other hand, there are two possible types of response, each with its corresponding status code (lines 25 and 68).

The format of the response with code 200 illustrates the most common characteristics of the responses returned by an API, containing properties of different primitive datatypes (numeric, strings and  booleans), arrays of objects (property `items` on lines 34-67, and property `artists` on lines 39-47), arrays of primitive types (`available_markets`, lines 49-52, is an array of strings) and objects nested inside objects (each element of the object array `items` contains as a property an object `linked_from`, lines 61-67).

The Listing below displays a JSON that follows the response format of the 200-code responses.

![Example of JSON response](https://i.imgur.com/wAtftyf.png)

Beet would use the OAS from specification to generate a declaration file containing the input and output formats for each of the operations. This declaration file would follow the structure shown in the Listing below.

![Structure of declaration file](https://i.imgur.com/zHmmal0.png)

The declaration file starts by specifying the version of the file format (line 1), followed by all the program points of the API to be instrumented (in this case, it will be only ENTER and EXIT program points). There will be at least one ENTER and EXIT program point for each response code; each property of type array of objects will have its own ENTER and EXIT program points.

Using different program points depending on the response code allows, among other things, to detect invariants in the inputs that indicate the reasons why a certain type of response has been obtained. For example, if the Spotify API is well implemented, it should return a validation error (code 400) if the user enters invalid values for the numeric parameter `limit` (0 or a negative value) or for the string parameters `market` or `id` (for example, with an incorrect length).

Thus, for the program point that models inputs for which a valid output has been returned (`getAlbumTracks&200():::ENTER`), invariants that model this behavior will be obtained (`input. limit >= 1`,  `LENGTH(input.market)==2` and `LENGTH(input.id)==22`) that will not necessarily be present in the inputs of API requests that have returned a validation error (`getAlbumTracks&400()::::ENTER`).

Program points representing inputs will have exactly the same structure, differing only by their name (line 1), the Listing below shows an example of an ENTER program point.

![Example of ENTER program point](https://i.imgur.com/Z56NWgh.png)

Each program point begins with the specification of its name (line 1) followed by the type of program point (line 2). This is followed by the declarations of all variables involved in the program point. In this Listing, there is a variable of type object (`input`) that represents the whole input, with its properties being the different input parameters (`input.id`, `input.market` and `input.limit`). Each variable can contain the following fields:

* **var-kind \<kind\>:** Specifies the variable type. In our context, its possible values are:
    - **variable:** Used for input variables.
    - **return:** Used for output variables.
    - **field \<var-name\>:** Used for sub-properties of variables.
    - **array:** Used if the variable represents the elements of an array.

* **enclosing-var \<enclosing-var-name\>:** Used if the variable is a property of a variable of type object.

* **dec-type \<language-declaration\>:** Name of the variable datatype used in the original program. Primitive types must follow Java naming conventions (`int`, `boolean`, `double` or `java.lang.String`), whereas any value is accepted for objects.

* **rep-type \<daikon-type\>:** Specified the Daikon datatype used for representing the variable in the data trace file. The values of this field can be either `int`, `boolean`, `double` or `java.lang.String` for primitive variables, or `hashcode` for variables of type object.

* **array \<dim\>:** Number of dimensions of the array. Its value can be either 1 if the variable is an array, and 0 otherwise. The default value is 0.

![Example of EXIT program point (first nesting level)](https://i.imgur.com/2gComZ4.png)

The Listing above shows the program point that specifies the format of the API output when it returns a 200 code. The EXIT program points are numbered (line 1) and labeled as subexit (line 2). This is because, in other programming languages, a method may have several exits on different lines, which Daikon joins when reporting invariants.

When declaring an EXIT program point, it is necessary to include the input variables at the beginning again, which allows detecting invariants that compare variables of the inputs to variables of the outputs, such as `input.limit >= size(return.items[])`.

The output variables are found from line 5 of the Listing, where the object `return`, which contains all the fields of the response as properties, is found. Note that, in this case, its var-kind is return instead of variable.

As mentioned in describing the format of the response in the illustrative example, the `items` property of the response (`return.items` in the declaration file), is an array of object-like elements, each of which represents a song or track. When specifying an array property in a declaration, it is necessary to use two variables, one of type object represented by a hashcode that acts as a pointer (lines 19-23), and one of type array containing the array elements (lines 24-29).

The second variable, the one that defines the array elements, is declared as an array type (line 25), so it is necessary to specify that its dimensionality is not 0 (line 27), and its enclosing-var is the pointer (line 26). Since `return.items` is an array of objects, its representation will be an array of hashcodes (line 29).

Using only the hashcode of the elements of arrays of objects, no invariants related to these objects can be extracted, except those related to the size of the array, such as `input.limit >= size(return.items[])`. For this reason, a new program point is defined whose variables are the properties of the object type that compose it.

![Second EXIT nesting level](https://i.imgur.com/FxMbau4.png)

Note that the nesting hierarchy is represented using the “&” character as a separator to distinguish the different nesting levels (line 1). This program point contains two arrays, one of objects (lines 9-19) and one of strings (lines 20-30). Although it will be necessary to define a new nesting level (`200&items&artists`) for the array of objects, this will not occur with the array of strings, since the elements that compose it can be represented in a data trace file without resorting to a new nesting level.

In addition to these array-type properties, the Listing above also contains a nested object (`return.linked_from`, lines 39-53) with two primitive properties (`return.linked_from.id` and `return.linked_from.uri`), for which it is not necessary to create a new nesting level.

## Generation of data trace file
A data trace file contains the values assigned to each of the program variables (each of them belonging to a program point previously defined in the declaration file) throughout its execution. The Listing below contains a fragment of a data trace file corresponding to an input program point. As in the declaration file, the ENTER program points of the same test case will be identical, differing only by their name (line 1).

![ENTER data trace](https://i.imgur.com/oMACfrB.png)

Each representation of a program point in a data trace file has the following components:

* Program point name (line 1)
* For each variable:
    - Variable name (lines 2, 5, 8 and 11)
    - Variable value (lines 3, 6, 9 and 12):
        - If the variable is numeric, its value will be a sequence of digits that may be preceded by a minus sign. In the case of double type variables, a period may be used to indicate decimal digits.
        - If the variable is a string, its value must be enclosed in double quotes.
        - If the variable is an object, it will be represented by a hashcode, without quotation marks.
        - If the variable is an array, the array elements will be separated by spaces. The array elements will be between the characters “[” and “]”.
    - Modified bit (lines 4, 7, 10 and 13): Its value will be 1 if the variable has been assigned, and 0 otherwise.

The Listing below shows the structure that the complete data trace file will have for the running example, composed by a single test case. Note that each EXIT must be accompanied by its corresponding ENTER and that the same pair of inputs and outputs may be repeated in the same test if there is more than one element within an array of objects, as is the case for elements of type artist (lines 13-22).

![Structure of the data trace file](https://i.imgur.com/yNsaWvr.png)

The Listing below shows the value of the EXIT program point for the first nesting level of the response. As in the declaration file, when declaring an EXIT program point it is necessary to include the input variables at the beginning again.

![First data trace EXIT nesting level](https://i.imgur.com/Y8Uec0e.png)

In addition to two primitive variables (`return.total` and `return.href`), this EXIT contains a property of type array of objects (`return.items`), which is represented by two variables, an object type variable  acting as pointer represented by a hashcode (lines 13-15) and the variable containing the array elements, represented in this case by hashcodes (lines 16-18).

Next, a pair of program points (an ENTER and an EXIT) will be added for each element of the object array, as `return.items` contains only one element, only one ENTER and one EXIT will be generated, the latter represented in the Listing below. This program point contains the nested object `return.linked.linked_from` (lines 31-39), the array of objects `return.artists` (lines 7-12), and the array of strings `return.available.markets` (lines 13-18). In the case of `return.available.markets`, since it is an array with primitive elements, it is not necessary to define a new nesting level to detect invariants (such as `All the elements of return.markets have length 2`).

![Second data trace EXIT nesting level](https://i.imgur.com/lreisdM.png)

Finally, two pairs of ENTER and EXIT will be generated, one for each element of `return.artists`.

![Third data trace EXIT nesting level](https://i.imgur.com/z7rmJrn.png)








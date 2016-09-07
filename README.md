# Polyline Algorithm and Compound Interest

### Instructions
* This java application performs three tasks:
    - Implement the [Polyline Algorithm](https://developers.google.com/maps/documentation/utilities/polylinealgorithm) within the [PolylineEncoder](src/main/java/com/kartik/PolylineEncoder.java) class.
    - Implement the compound interest methods of [CompoundInterestCalculator](src/main/java/com/kartik/CompoundInterestCalculator.java) class.
    - Implement the command line [Program](src/main/java/com/kartik/Program.java) class to test the code.

### Program Instructions
* The [Program](src/main/java/com/kartik/Program.java) takes command line arguments and parse them according to the following rules
    - The first argument should either be `gps` or `interest`
    - If the first argument is `gps` then the remaining arguments are GPS lat,lng pairs separated by spaces. 
        - Parse these arguments and pass them into your [PolylineEncoder](src/main/java/com/kartik/PolylineEncoder.java) implementation and print the result
    - If the first argument is `interest` then the second argument is either `annual` or `continuous`
        - If the second argument is `annual` then the remaining arguments are parsed as `principal`, `rate`, `period` and `years`. Parse these values and pass them into your [CompoundInterestCalculator](src/main/java/com/kartik/CompoundInterestCalculator.java) `compoundAnnually` method implementation and print the result
        - If the second argument is `continuous` then the remaining arguments are parsed as `principal`, `rate` and `years`. Parse these values and pass them into your [CompoundInterestCalculator](src/main/java/com/kartik/CompoundInterestCalculator.java) `continuousCompound` method implementation and print the result
* If any of the arguments are invalid then print `Invalid argument` to the command line and quit
    
### Example Runs of Program
    $ java -cp "." com.kartik.Program foo
    $ Invalid argument
    $ java -cp "." com.kartik.Program gps 38.5,-120.2 40.7,-120.95 43.252,-126.453
    $ _p~iF~ps|U_ulLnnqC_mqNvxq`@
    $ java -cp "." com.kartik.Program interest foo
    $ Invalid argument
    $ java -cp "." com.kartik.Program interest annual
    $ Invalid argument
    $ java -cp "." com.kartik.Program interest annual 1500 0.043 4 6
    $ 438.8368221341036315119868759615438447390638938086647345973196039067038515540052407615743135238517425023019313812255859375000
    $ java -cp "." com.kartik.Program interest continuous 2340 0.031 3
    $ 228.0604605273118800

    

    
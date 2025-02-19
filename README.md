# CSC 207: Text Editor

**Author**: Owen Block

## Resources Used

+ Apache Netbeans
+ Starter code found on github in Professor Osera's repo
+ Instructions for the project
+ Java 17.0.14
+ Testing Frameworks Lab
+ Spoke with Finn Rowles on a high level about the project

## Analyzing SimpleStringBuffer

+ Part 1: Relavent inputs of insert
    + The only relavent input of insert is the char value ch, which is the char
        that will be input into the string by the method.
+ Part 2: The Critical Operations
    + There are 4 comparisons and under each comparison string s is being manipulated
        in some way, as well at the end 1 will always be added to the cursor.
    + String manipulations:
        + ch + ""
        + s + ch
        + ch + s
        + s.substring(0 , cursor) + ch + s.substring(cursor , s.length());
+ Part 3: A mathematical model of the runtime of insert 
            as a function of the inputs and operations you chose.
        + Best case f(n) = 3
        + Worst case f(n) = 6
        + Average case f(n) = 5
+ Part 4: Big-O Characterization of the Model:
        + insert is O(n) because it is linear.

## Changelog

+ Commit 1: Updated README and updated SimpleStringBuffer.java methods.
+ Commit 2: Created all test cases for SimpleStringBuffer.

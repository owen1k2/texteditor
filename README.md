# CSC 207: Text Editor

**Author**: Owen Block

## Changelog

+ Updated so that program now passes all auto grader tests
+ Updated so that arrow keys and cursor now work as intended
+ Updated so that the file is saved on deletion

## Resources Used

+ Apache Netbeans
+ Starter code found on github in Professor Osera's repo
+ Instructions for the project
+ Java 17.0.14
+ Testing Frameworks Lab
+ Spoke with Finn Rowles on a high level about the project
+ Starter code from the Project Instructions Part 4
+ Spoke with Professor Osera on a high level about the project
+ Spoke with Colton on a high level about the project

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
        + Worst case f(n) = n
        + Average case f(n) = n
+ Part 4: Big-O Characterization of the Model:
        + insert is O(n) because it is linear.

## Changelog

+ Commit 1: Updated README and updated SimpleStringBuffer.java methods.
+ Commit 2: Created all test cases for SimpleStringBuffer.
+ Commit 3: Completed Part Two which was the analyzing of insert in simple string buffer.
+ Commit 4: Completed Initial GapBuffer functions about to begin testing.
+ Commit 5: Completed everythin in GapBuffer besides doubling array length.
+ Commit 6: Completed Testing Suite for Gap Buffer.
+ Commit 7: Completed Testing Suite for Gap Buffer.
+ Commit 8: README Updated
+ Commit 9: JavaDoc Updated and completed for GapBuffer.
+ Commit 10: Main is now completed and the program is functional.
+ Commit 11: Style Updates of GapBuffer.
+ Commit 12: Style Updates of TextEditor.
+ Commit 13: Style Updates of SimpleStringBuffer.
+ Commit 14: Style Updates of GapBufferTests.
+ Commit 15: Style Updates of SimpleStringBufferTests.
+ Commit 16: Updated error in DoubleArray to change afterGapBeg.
+ Commit 17: Fixes to test String: empty, String: delete middle.
+ Commit 18: Test updated
+ Commit 19: Fixes to Gap: cursor movement
+ Commit 20: Fixes to String Empty, Cursor Movement tests.
+ Commit 21: Updated tests
+ Commit 22: Changes to cursor movement gap.
+ Commit 23: Updated Tests
+ Commit 24: Updated cursor movement for gap buffer.
+ Commit 25: Fixed to pass all auto grader tests.
+ Commit 27: Fixed all movement and cursor issues.


# This is ALec Lerner implementation of # Avility coding assignemnt
All details of assignment described in Availity Homework Backend.docx

#First coding exercise: 
Coding exercise: 
You are tasked to write a checker that validates the parentheses of a LISP code.
Write a program (in Java or JavaScript) which takes in a string as an input
and returns true if all the parentheses in the string are properly closed and nested.

Class that implements this exercise located in bracketschecker package
T

# Second coding exercise
Availity receives enrollment files from various benefits management and enrollment solutions (I.e. HR platforms, payroll platforms).  
Most of these files are typically in EDI format.  However, there are some files in CSV format.  
For the files in CSV format, write a program in a language that makes sense to you that will read the content of the fileand separate enrollees by insurance company in its own file.
Additionally, sort the contents of each file by last and first name (ascending).  
Lastly, if there are duplicate User Ids for the same Insurance Company, then only the record with the highest version should be included.
The following data points are included in the file:
* User Id (string)
* First Name (string)
* Last Name (string)
* Version (integer)
* Insurance Company (string)

Classes that implement this exercise located in enrollees directory
GroupEnrollees.main method read sample file enrolee.csv and generate files in output directory.


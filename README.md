# Intrigued-Machine
Simple programming language with a "unique" interpreter

Implemented in Java, the "Intrigued" programming language is in some ways similar to a BASIC dialect with a simple syntax and GOTO statements handling much of each program's logic. In fact, the language only has 9 commands: ADD, MOVE, BOOL, OPERATE, GOTO, SET, PRINT, CLEAR, and CLEARALL. These commands can be woven together in a number of ways to create a text-only output that will fill up a 50x50 character screen.

The Intrigued interpreter reads each line, one by one, and uses each command to alter the "Intrigued Machine;" which is a 6x6 matrix plus an "energy container," each of which can hold any positive integer value of "energy units." Starting with 200 energy units in the energy container, after each command the energy container is credited with one additional unit. The energy units can be moved between boxes with the ADD, MOVE, and OPERATE commands.  

Some commands will do different things depending on how much energy is currently in their "reference box" when the command is read by the interpreter. Three commands are assigned to a reference box: OPERATE and BOOL both reference box 1x1 (the "OPERATE box"), and SET references box 6x6 (the "PRINT box"). 

In it's first release, the Intrigued Machine will not be able to deal with input while running. Instead, programs will have to be coded that can handle different "initial inputs;" changeable values that you will set at the beginnings of programs by altering the code in simple ways. For instance, a program that finds the integer square root of a number may have code that loops through GOTO statements a number of times to set the ones, tens, and hundreds place in three boxes.  

~~Future implementations will likely include the ability to handle simple user input into a text box below the Intrigued Machine's screen. I also plan on implementing the ability to save and load .txt files so that programs can be stored easily from the editor. Eventually I would like to create a port to Android, so that Intrigued programs can be created and run on the go.~~

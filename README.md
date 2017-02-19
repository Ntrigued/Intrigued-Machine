# The Intrigued Machine and Language
Simple programming language with a "unique" interpreter

(Note: All code is being kept in dev branch until I consider V1 fully functional.)

Implemented in Java, the "Intrigued" programming language is basically a BASIC-looking assembly-like language with a simple syntax and JUMP statements handling much of each program's logic. In fact, the language only has 15 commands: ADD, MOVE, BOOL, OPERATE, LABEL, JUMP, ZER0, SET, GET, PRINT, CLEAR, CLEARALL, TIME, WAIT, INTERRUPT (not fully implemented) and MEMDUMP (buggy - ironic, I know) for debugging. These commands can be woven together in a number of ways to create a text-only output that will fill up a 100x100 character screen.

The Intrigued interpreter reads each line, one by one, and uses each command to alter the "Intrigued Machine;" which is a 6x6 matrix plus an "energy container," each of which can hold any positive integer value of "energy units." Aftering between introduced to the matrix through certain boxes, which can recieve units directly from the cotainer, the energy units can be moved between boxes with the ADD, MOVE, and OPERATE commands.  

Some commands will do different things depending on how much energy is currently in their "reference box" when the command is read by the interpreter. Three commands are assigned to reference boxes: OPERATE and BOOL both reference box 1x1 (the "OPERATE box"), and SET references box 6x6 (the "PRINT box"). 

In it's first release, the Intrigued Machine will not be able to deal with input while running. Instead, programs will have to be coded that can handle different "initial inputs;" changeable values that you will set at the beginnings of programs by altering the code in simple ways. For instance, a program that finds the integer square root of a number may have code that loops through ADD, OPERATE, and BOOL statements a number of times to set the ones, tens, and hundreds place in three boxes.  

Future implementations will likely include the ability to handle simple user input into a text box below the Intrigued Machine's screen. I also plan on implementing the ability to save and load .intr files so that programs can be stored easily from the editor.

(You can see what a .intr file looks like in the Sample Programs folder)

(Please note, I have just finished up a fairly major re-write on parts of how the machine works. There may be minor errors in this description, and sample programs may fail [I intend to fix this when I have time].)

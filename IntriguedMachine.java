/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* REMEMBERTODO IN THIS CLASS

*/
package theintriguedproject;

/*REMEMBERTODO
 * fix problem with CLEAR() function
 * check case 3 of OPERATE() function
 */

/*Intrigued Machine is the machine that will be simulated when Intrigued (the language to be created) is run
Class that will represent the Intrigued Machine and handle all functions of the machine
Create 6x6 matrix to hold energy values for each cell
Create function to handle all machine codes: IntriguedMachine.OPERATE( int requiredenergy, frombox, tobox, int index)
Leave commands not related to machine actions, such as GOTO and READ, to other class
*/

// fromBox: box that is treated first in an operation
//toBox: box that is treated second in an operation

public class IntriguedMachine { 
    
	java.util.Timer timer = new java.util.Timer();
	
    //Create the matrix
    int[][] boxMatrix = new int[6][6];
        
    //Create the energy container and set the beginning level;
    int energyContainer = 300;
    OutputWindow output;
 
    //Used to track whether the Intrigued Machine has crashed
    boolean crash = false;
    
//Create and set up the energy container.
// Create the 6x6 matrix of boxes from boxMatrix and set each value to zero
//Creates OutputWindow object
    public IntriguedMachine () {
        
        for (int[] x : boxMatrix) {
            for (int y : x) {
                y=0;
            }//End of inner for loop
        }//End of outer for loop
        
        //Initialize OutputWindow object and set visible
        output = new OutputWindow();
    }    
   
    //If the energy container has at least one energy remaining, take one energy and send it to borderBox
    public boolean ADD(int borderBox) {
        int borderBox_x = (borderBox - (borderBox%10))/10;
        int borderBox_y = borderBox%10;
        
        if(energyContainer>0) {
           if(borderBox_x == 1 || borderBox_x == 6) {
               energyContainer = energyContainer - 1;
               boxMatrix[borderBox_x-1][borderBox_y-1] = boxMatrix[borderBox_x-1][borderBox_y-1] + 1;
               return true;

           } else if(borderBox_y == 1 || borderBox_y == 6) {
               energyContainer = energyContainer - 1;
               boxMatrix[borderBox_x][borderBox_y] = boxMatrix[borderBox_x-1][borderBox_y-1] + 1;
               return true;

           } 
        }//End of if statements
        return false;
    } //end of ADD method
    
    public boolean BOOL(int fromBox, int toBox, int index) {
        int controlBoxEnergy = boxMatrix[0][0];
        int fromBox_x = (fromBox / 10) - (fromBox % 10);
        int fromBox_y = fromBox % 10;
        int toBox_x = (toBox/10) - (toBox % 10);
        int toBox_y = toBox % 10;
        
        
        switch (controlBoxEnergy) {
            case 11:
                if(boxMatrix[fromBox_x][fromBox_y] > 0) {
                    return true;
                } else {
                    return false;
                }// End of if statement
                
            case 13:
                if(index == 1) {
                    if(boxMatrix[fromBox_x][fromBox_y] > boxMatrix[toBox_x][toBox_y]) {
                        return true;
                    } else {
                        return false;
                    }// end of inner if statement
                } else if(index == 2) {
                    if(boxMatrix[fromBox_x][fromBox_y] == boxMatrix[toBox_x][toBox_y]) {
                        return true;
                    } else {
                        return false;
                    }//End of inner if statement
                }//End of outer if statement
                
            default:
                return false;
        }
    }
    
    //Clears every entry from the screen
    public void CLEAR() {
        for(int x = 0; x<50; x++){
           for(int y = 0; y<50; y++) {
               output.outputLines[x][y] = ' ';
           }
        }   
    }
    
    //Resets a specific coordinate on the screen to the space character 
    public void CLEARPLACE(int x_pos, int y_pos) {
        output.outputLines[x_pos][y_pos] = ' ';
    }
    
    //Moves 1 energy from fromBox to toBox, if they are touching and fromBox has 1 energy
    public boolean MOVE( int fromBox, int toBox) {
        //variables for altering values in boxMatrix
        int fromBox_x = (fromBox - (fromBox % 10))/10;
        int fromBox_y = fromBox % 10;
        int toBox_x = (toBox - (toBox % 10))/10;
        int toBox_y = toBox % 10;
        
        if(boxMatrix[fromBox_x - 1][fromBox_y - 1] > 0) {
            if(toBox-fromBox==1) {
                boxMatrix[fromBox_x-1][fromBox_y-1] = boxMatrix[fromBox_x-1][fromBox_y-1] - 1;
                boxMatrix[toBox_x-1][toBox_y-1] = boxMatrix[toBox_x-1][toBox_y-1] + 1;
                return true;
            
            } else if ((fromBox%10) - (toBox%10) == 0) {
                boxMatrix[fromBox_x-1][fromBox_y-1] = boxMatrix[fromBox_x-1][fromBox_y-1] - 1;
                boxMatrix[toBox_x-1][toBox_y-1] = boxMatrix[toBox_x-1][toBox_y-1] + 1;
                return true;
            
            } else {
                return false;
            }
        } else {
            return false;
        }     
    } //End of MOVE method
    
    public boolean OPERATE(int fromBox, int toBox, int x_pos, int y_pos, int index) {
        //variables for altering values in boxMatrix
        int fromBox_x = (fromBox - (fromBox % 10))/10;
        int fromBox_y = fromBox % 10;
        int toBox_x = (toBox - (toBox % 10))/10;
        int toBox_y = toBox % 10;
        int controlBoxEnergy = boxMatrix[0][0];
        
        switch(controlBoxEnergy) {
            
            case 3:
                int fromBox_Energy = boxMatrix[fromBox_x][fromBox_y];
                int toBox_Energy = boxMatrix[toBox_x][toBox_y];
                boxMatrix[toBox_x][toBox_y] = boxMatrix[toBox_x][toBox_y] + fromBox_Energy;
                boxMatrix[fromBox_x][fromBox_y] = 0;
                return true;
                
            //Has two indices
            case 5:
                
                //Prints the energy level of fromBox and then erases the number 
                //from outputLines so it is not printed during the next printing of the screen
                if(index==1) {
                    int fromBoxEnergy = boxMatrix[fromBox_x -1][fromBox_y -1];
                    int fromBoxEnergy_Hundreds = (fromBoxEnergy - (fromBoxEnergy % 100))/100;
                    int fromBoxEnergy_Tens = (fromBoxEnergy - (fromBoxEnergy%10))/10;
                    int fromBoxEnergy_Ones = fromBoxEnergy%10;
                    
                    if (fromBoxEnergy_Hundreds != 0) {
                        output.outputLines[x_pos-1][y_pos-1] = output.codeList(fromBoxEnergy_Hundreds);
                        output.outputLines[x_pos-2][y_pos-2] = output.codeList(fromBoxEnergy_Tens);
                        output.outputLines[x_pos-3][y_pos-3] = output.codeList(fromBoxEnergy_Ones);
                        
                        PRINT();
                        
                        output.outputLines[x_pos-1][y_pos-1] = ' ';
                        output.outputLines[x_pos-2][y_pos-2] = ' ';
                        output.outputLines[x_pos-3][y_pos-3] = ' ';
                        
                    } else if(fromBoxEnergy_Tens != 0) {
                        output.outputLines[x_pos-1][y_pos-1] = output.codeList(fromBoxEnergy_Tens);
                        output.outputLines[x_pos-2][y_pos-2] = output.codeList(fromBoxEnergy_Ones);
                        
                        PRINT();
                        
                        output.outputLines[x_pos-1][y_pos-1] = ' ';
                        output.outputLines[x_pos-2][y_pos-2] = ' ';
                    
                    } else {
                        output.outputLines[x_pos-1][y_pos-1] = output.codeList(fromBoxEnergy_Ones);
                     
                        PRINT();
                        
                        output.outputLines[x_pos-1][y_pos-1] = ' ';
                     
                    }//End of inner if statement
                    return true;
                } else if(index==2) {
                    int fromBoxEnergy = boxMatrix[fromBox_x][fromBox_y];
                    int toBoxEnergy = boxMatrix[toBox_x][toBox_y];
                    boxMatrix[fromBox_x][fromBox_y] = toBoxEnergy;
                    boxMatrix[toBox_x][toBox_y] = fromBoxEnergy;
                    
                    return true;
                } else{
                    return false;
                }//End of Case 5
                
            default: 
                return false;
        }
    }
    
    //STILL NEEDS TO BE IMPLEMENTED
    public boolean PRINT(){  
    	try {
    		timer.wait(10000000);
    	} catch(Exception adf) {}
    	String result = ""; //Used to hold the characters to be printed to the screen
        for(int x = 0; x<50; x++){
           for(int y = 0; y<50; y++) {
               result = result + String.valueOf(output.outputLines[x][y]); 
           }//End of inner IF statement

           result = result + "a\n";
           output.jTextArea1.setText(result);           
        }//End of outer IF statement
        
        return true;
    }
    
    //Sets the char represented by the # of energy in the print box to the screen position x_pos, y_pos
    //This char will be output to the x_pos, y_pos position during the next time the screen is printed
    public boolean SET(int x_pos, int y_pos) {
        //If x_pos and y_pos are within the screen parameters,
        //set the currently represented char value in the print box to be output
        //x_pos, y_pos during the next printing of the output screen.
        if(x_pos<50 && y_pos<50) {
        	System.out.println(boxMatrix[5][5]);//Debugging
            char charID = output.codeList(boxMatrix[5][5]);
            System.out.println("x: " + x_pos);
            System.out.println("y: " + y_pos);
            output.outputLines[x_pos][y_pos] = charID;
            System.out.println(output.outputLines[x_pos][y_pos]);
            return true;
        }//End of if statement
        return false;
    }//End of SET method
    
    //Runs when something causes the Intrigued Machine to crash
    //TODO: debugging features of crash() must be deleted from this and CodeCenter
    public void crash(int lineNum, String command) {
        crash = true;
        System.out.println("Type of command be parsed: " + command);
        System.out.println("ERASE THIS PRINTLN CALL FROM crash() FUNCTION AFTER TESTING\nError found in line " + lineNum );
    }
    
    public int getOperateBoxEnergyValue() {
        return boxMatrix[0][0];
    }
    
    public boolean isCrashed() {
        return crash;
    }
}

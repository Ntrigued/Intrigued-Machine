/* REMEMBERTODO IN THIS CLASS
*/
package theintriguedproject;

import java.util.HashMap;
import java.util.Map;

/*REMEMBERTODO
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
    
	java.util.Timer timer;
	Map<String, Integer> labels;
	
	//label pointing to start of interrupt code
	int interruptLabel;
	
    //Create the matrix
    int[][] boxMatrix;
        
    //Create the energy container
    int energyContainer;
    
    //Creates "reference" to OutputWindow
    OutputWindow output;
 
    //Used to track whether the Intrigued Machine has crashed
    boolean crash;
    
//Create and set up the energy container.
// Create the 6x6 matrix of boxes from boxMatrix and set each value to zero
//Creates OutputWindow object
    public IntriguedMachine () {
    	timer = new java.util.Timer();
    	labels = new HashMap<String, Integer>();
    	interruptLabel = 0;
    	boxMatrix = new int[6][6];
    	energyContainer = 300;
    	crash = false;
    	
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
        int borderBox_y = borderBox % 10;
        
        System.out.println("borderBox: " + borderBox);
        System.out.println("borderBox_x: " + borderBox_x);
        System.out.println("borderBox_y: " + borderBox_y);
        System.out.println("energyContainer: " + energyContainer);
        
        if(energyContainer>0) {
        	System.out.println("energyContainer barrier has been passed: " + energyContainer);
           if(borderBox_x == 1 || borderBox_x == 6) {
               energyContainer = energyContainer - 1;
               boxMatrix[borderBox_x-1][borderBox_y-1] = boxMatrix[borderBox_x-1][borderBox_y-1] + 1;
               return true;

           } else if(borderBox_y == 1 || borderBox_y == 6) {
        	   energyContainer = energyContainer - 1;
               boxMatrix[borderBox_x - 1][borderBox_y - 1] = boxMatrix[borderBox_x-1][borderBox_y-1] + 1;
               return true;

           } 
        }//End of if statements
        System.out.println("ADD failed");
        return false;
    } //end of ADD method
    
    public boolean BOOL(int fromBox, int toBox, int index) {
        int controlBoxEnergy = boxMatrix[0][0];
        int fromBox_x = (fromBox - (fromBox % 10))/10;
        int fromBox_y = fromBox % 10;
        int toBox_x = (toBox - (toBox % 10))/10;
        int toBox_y = toBox % 10;
        
        
        switch (controlBoxEnergy) {
            case 1:
                if(boxMatrix[fromBox_x - 1][fromBox_y - 1] > 0) {
                    return true;
                } else {
                    return false;
                }// End of if statement
                
            case 2:
                if(index == 1) {
                    if(boxMatrix[fromBox_x - 1][fromBox_y - 1] > boxMatrix[toBox_x - 1][toBox_y - 1]) {
                        return true;
                    } else {
                        return false;
                    }// end of inner if statement
                } else if(index == 2) {
                    if(boxMatrix[fromBox_x - 1][fromBox_y - 1] == boxMatrix[toBox_x - 1][toBox_y - 1]) {
                        return true;
                    } else {
                        return false;
                    }//End of inner if statement
                }//End of outer if statement
                
            case 3:
            	if(boxMatrix[fromBox_x - 1][fromBox_y - 1] > toBox) return true;
            	else return false;

            case 4:
            	if(boxMatrix[fromBox_x - 1][fromBox_y - 1] < toBox) return true;
            	else return false;
            	
            case 5:
            	if(boxMatrix[fromBox_x - 1][fromBox_y - 1] == toBox) return true;
            	else return false;
            	
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
    
    public boolean INTERRUPT(int place) {
    	labels.put("INTERRUPT", place);
    	return true;
    }
    
    public int JUMP(String label) {
    	if((labels.get(label) != null) && (labels.get(label) >= 0))
    	{
    		return labels.get(label);
    	}
    		return -1;
    }
    
    public boolean LABEL(String label, int place){
    	try {
    	labels.put(label, place);
    	return true;
    	} catch(Exception e) {
    		return false;
    	}
    }
    
    //TODO: re-write in more simulated way
    //Prints value of each box in boxMatrix to OutputWindow
    public void MEMDUMP()
    {
    	System.out.println("Boxmatrix.length: " + boxMatrix.length);
    	System.out.println("Boxmatrix.length[1]: " + boxMatrix[1].length);
    	
    	char x_pos, y_pos, x_char, y_char;
    	
    	for(int x = 0; x < boxMatrix.length; x++ ) {

    		System.out.println("x: " + x);
    		switch(x+1) {
			case 0:
				x_pos = '0';
				break;
				
			case 1:
				x_pos = '1';
				break;
				
			case 2:
				x_pos = '2';
				break;
				
			case 3:
				x_pos = '3';
				break;    				
			case 4:
				x_pos = '4';
				break;    				
			case 5:
				x_pos = '5';
				break;    				
			case 6:
				x_pos = '6';
				break;    				
			case 7:
				x_pos = '7';
				break;    				
			case 8:
				x_pos = '8';
				break;
				
			case 9:
				x_pos = '9';
				break;
				
			default:
				x_pos = 'E';
			}
    		System.out.println("x_pos: " + x_pos);
    		
    		
    		for(int y = 0; y < boxMatrix[x].length; y++) {

        	System.out.println("y: " + y);
    		switch(y+1) {
    			case 0:
    				y_pos = '0';
    				break;
    				
    			case 1:
    				y_pos = '1';
    				break;
    				
    			case 2:
    				y_pos = '2';
    				break;
    				
    			case 3:
    				y_pos = '3';
    				break;    				
    			case 4:
    				y_pos = '4';
    				break;    				
    			case 5:
    				y_pos = '5';
    				break;    				
    			case 6:
    				y_pos = '6';
    				break;    				
    			case 7:
    				y_pos = '7';
    				break;    				
    			case 8:
    				y_pos = '8';
    				break;
    				
    			case 9:
    				y_pos = '9';
    				break;
    				
    			default:
    				y_pos = 'E';
    			}
    			System.out.println("y_pos: " + y_pos);
    		
    			output.outputLines[x+3][10*y] = x_pos;    			
    			output.outputLines[x+3][10*y+1] = y_pos;
    			output.outputLines[x+3][10*y+2] = ':';
    		    int x_value= (boxMatrix[x][y] - (boxMatrix[x][y] % 10)) / 10;
    			int y_value = boxMatrix[x][y] % 10;
    			
    			switch(x_value) {
    			case 0:
    				x_char = '0';
    				break;
    				
    			case 1:
    				x_char = '1';
    				break;
    				
    			case 2:
    				x_char = '2';
    				break;
    				
    			case 3:
    				x_char = '3';
    				break;    				
    			case 4:
    				x_char = '4';
    				break;    				
    			case 5:
    				x_char = '5';
    				break;    				
    			case 6:
    				x_char = '6';
    				break;    				
    			case 7:
    				x_char = '7';
    				break;    				
    			case 8:
    				x_char = '8';
    				break;
    				
    			case 9:
    				x_char = '9';
    				break;
    				
    			default:
    				x_char = 'E';
    			}
    			
    		switch(y_value) {
    			case 0:
    				y_char = '0';
    				break;
    				
    			case 1:
    				y_char = '1';
    				break;
    				
    			case 2:
    				y_char = '2';
    				break;
    				
    			case 3:
    				y_char = '3';
    				break;    				
    			
    			case 4:
    				y_char = '4';
    				break;    				
    			
    			case 5:
    				y_char = '5';
    				break;
    				
    			case 6:
    				y_char = '6';
    				break;
    				
    			case 7:
    				y_char = '7';
    				break;    	
    				
    			case 8:
    				y_char = '8';
    				break;
    				
    			case 9:
    				y_char = '9';
    				break;
    				
    			default:
    				y_char = 'E';
    			}
    		
    		output.outputLines[x+3][10*y+5] = x_char;
    		output.outputLines[x+3][10*y+6] = y_char;
    	    }
    	}
    	
    	PRINT();
    	crash(0, "crash from MEMDUMP");
    }
    
    //Moves 1 energy from fromBox to toBox, if they are touching and fromBox has 1 energy
    public boolean MOVE(int fromBox, int toBox) {
        //variables for altering values in boxMatrix
        int fromBox_x = (fromBox - (fromBox % 10))/10;
        int fromBox_y = fromBox % 10;
        int toBox_x = (toBox - (toBox % 10))/10;
        int toBox_y = toBox % 10;
        
        if(boxMatrix[fromBox_x - 1][fromBox_y - 1] > 0) {
            if((toBox-fromBox)*(toBox-fromBox)==1) {
                boxMatrix[fromBox_x-1][fromBox_y-1] = boxMatrix[fromBox_x-1][fromBox_y-1] - 1;
                boxMatrix[toBox_x-1][toBox_y-1] += 1;
                
                return true;
            
            } else if ((fromBox%10) - (toBox%10) == 0) {
                boxMatrix[fromBox_x-1][fromBox_y-1] = boxMatrix[fromBox_x-1][fromBox_y-1] - 1;
                boxMatrix[toBox_x-1][toBox_y-1] = boxMatrix[toBox_x-1][toBox_y-1] + 1;
                return true;
            
            } else {
            	System.out.println("MOVE failed: boxes not adjacent");
                return false;
            }
        } else {
        	System.out.println("MOVE failed: energy in fromBOx less than 1");
            return false;
        }     
    } //End of MOVE method
    
    public boolean OPERATE(int fromBox, int toBox, int x_pos, int y_pos, int index) {
        //variables for altering values in boxMatrix
        int fromBox_x = (fromBox - (fromBox % 10))/10;
        int fromBox_y = fromBox % 10;
        int toBox_x = (toBox - (toBox % 10))/10;
        int toBox_y = toBox % 10;
        int controlBoxEnergy = getOperateBoxEnergyValue();
        
        switch(controlBoxEnergy) {

            case 3:
            //moves all of energy from fromBox to toBox
            	int fromBox_Energy = boxMatrix[fromBox_x - 1][fromBox_y - 1];
                int toBox_Energy = boxMatrix[toBox_x - 1][toBox_y - 1];
                boxMatrix[toBox_x - 1][toBox_y - 1] = boxMatrix[toBox_x - 1][toBox_y - 1] + fromBox_Energy;
                boxMatrix[fromBox_x - 1][fromBox_y - 1] = 0;
                return true;
                
            //Has two indices
            case 5:
                
                //Prints the energy level of fromBox and then erases the number 
                //from outputLines so it is not printed during the next printing of the screen
                if(index==1) {
                    int fromBoxEnergy = boxMatrix[fromBox_x -1][fromBox_y -1];
                    int fromBoxEnergy_Hundreds = (fromBoxEnergy - (fromBoxEnergy % 100))/100;
                    int fromBoxEnergy_Tens = (fromBoxEnergy - (fromBoxEnergy % 10))/10;
                    int fromBoxEnergy_Ones = fromBoxEnergy % 10;
                    
                    if (fromBoxEnergy_Hundreds != 0) {
                        output.outputLines[x_pos - 1][y_pos-1] = output.codeList(fromBoxEnergy_Hundreds);
                        output.outputLines[x_pos - 1][y_pos] = output.codeList(fromBoxEnergy_Tens);
                        output.outputLines[x_pos - 1][y_pos + 1] = output.codeList(fromBoxEnergy_Ones);
                        
                        PRINT();
                        
                        output.outputLines[x_pos - 1][y_pos - 1] = ' ';
                        output.outputLines[x_pos - 1][y_pos] = ' ';
                        output.outputLines[x_pos - 1][y_pos + 1] = ' ';
                        
                    } else if(fromBoxEnergy_Tens != 0) {
                        output.outputLines[x_pos - 1][y_pos - 1] = output.codeList(fromBoxEnergy_Tens);
                        output.outputLines[x_pos - 1][y_pos] = output.codeList(fromBoxEnergy_Ones);
                        
                        PRINT();
                        
                        output.outputLines[x_pos - 1][y_pos-1] = ' ';
                        output.outputLines[x_pos - 1][y_pos] = ' ';
                    
                    } else {
                        output.outputLines[x_pos-1][y_pos-1] = output.codeList(fromBoxEnergy_Ones);
                     
                        PRINT();
                        
                        output.outputLines[x_pos-1][y_pos-1] = ' ';
                     
                    }//End of inner if statement
                    return true;
                } else if(index==2) {
                    int fromBoxEnergy = boxMatrix[fromBox_x - 1][fromBox_y - 1];
                    int toBoxEnergy = boxMatrix[toBox_x - 1][toBox_y - 1];
                    boxMatrix[fromBox_x - 1][fromBox_y - 1] = toBoxEnergy;
                    boxMatrix[toBox_x - 1][toBox_y - 1] = fromBoxEnergy;
                    
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
    	String result = ""; //Used to hold the characters to be printed to the screen
        for(int x = 0; x<50; x++){
           for(int y = 0; y<50; y++) {
               result = result + String.valueOf(output.outputLines[x][y]); 
           }//End of inner IF statement

           result = result + "\n";         
        }//End of outer IF statement
        
        output.jTextArea1.setText(result);  
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
            output.outputLines[x_pos - 1][y_pos - 1] = charID;
            System.out.println(output.outputLines[x_pos][y_pos]);
            return true;
        }//End of if statement
        return false;
    }//End of SET method
    
  //If the energy container has at least one energy remaining, take one energy and send it to borderBox
    public boolean ZERO(int borderBox) {
        int borderBox_x = (borderBox - (borderBox%10))/10;
        int borderBox_y = borderBox % 10;
        
        System.out.println("borderBox: " + borderBox);
        System.out.println("borderBox_x: " + borderBox_x);
        System.out.println("borderBox_y: " + borderBox_y);
        
        if(borderBox_x == 1 || borderBox_x == 6) {
            boxMatrix[borderBox_x-1][borderBox_y-1] = 0;
            return true;

        } else if(borderBox_y == 1 || borderBox_y == 6) {
            boxMatrix[borderBox_x - 1][borderBox_y - 1] = 0;
            return true;

        } 
        System.out.println("ZERO failed");
        return false;
    } //end of ZERO method
    
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

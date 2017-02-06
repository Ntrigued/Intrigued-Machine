/* REMEMBERTODO IN THIS CLASS
call machine.crash() whenever a line is read that has a mistake in it
all machine.crash calls need to be moved to the respective functions after debugging to maintain authenticity
Implement machine.crash() for incorrect PRINT tributes[] array is created at the beginning of parsing each line
*/
package theintriguedproject;

import javax.swing.JFrame;
import java.lang.Integer; //Used to pull int values from code commands in JTextArea1
import java.lang.NumberFormatException; //Thrown for incorrect MOVE command

/**
 *
 * @author intri
 */
public class CodeCenter extends JFrame {

    /**
     * Creates new form CodeCenter
     */
    public CodeCenter() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jButton1.setText("Run");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 711, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(288, 288, 288)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>                        

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        //writes each line of user code to separate array place
        //userCodeArray can then be passed into an enhanced for loop to act on each line individually
        // May need to be moved into different class
        
        //Initialize IntriguedMachine object
        IntriguedMachine machine = new IntriguedMachine();
        String[] userCodeArray = getTextasArray();
        //Parse each line
        new Thread() {
        	    	public void run() {
        	    		int commandtime;
        	    		int extratime = 0;
		    
	    	    		for(int i = 0; i<userCodeArray.length; i++){
				            if(machine.isCrashed() == false ) {
				            	System.out.println("line to be parsed: " + userCodeArray[i]);
				            	//Split off command name;
				                String[] attributes = userCodeArray[i].trim().split(" ");
				                String command = attributes[0];
				                
				                //Sets simulated length of time for each command.
				                // Which is set to 0 for comments
				                commandtime = 5;
				                
				                //Handle each command
				                switch (command){
				                case "":
				                	//Fall-through
				                
				                    //"#" will serve as comment symbol
				                    case "#":
				                    	commandtime = 0;
				                        break;
				
				                    case "ADD":
				                        int num;
				                        try {
				                            num = Integer.parseInt(attributes[1]);
				                            boolean response = machine.ADD(num);
				                            if(!response) 
				                            {
				                            	machine.crash(i+1,userCodeArray[i]);
				                            }
				                            
				                        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
				                        	machine.crash(i+1, userCodeArray[i] + ": " + e);
				                        }
				                        break;
				
				                    case "BOOL":
				                       //Split parts of functions into seperate strings for easy handling
				                       String[] properties = userCodeArray[i].split(" ");
				                       //Necessary variables for calling BOOL() function 
				                       int fromBoxNum;
				                       int toBoxNum;
				                       int index;
				
				                       //Will hold the return value of BOOL to determine if next line should be run
				                       boolean BOOL_Answer;
				                       //Check for inappropriate input
				                       try {
				                           fromBoxNum = Integer.parseInt(properties[1]);
				                       } catch(NumberFormatException | ArrayIndexOutOfBoundsException e) {
				                           machine.crash(i+1, userCodeArray[i]);
				                           fromBoxNum = 0;
				                       }
				
				                       //Check for inappropriate input
				                       try {
				                           toBoxNum = Integer.parseInt(properties[2]);
				                       } catch(NumberFormatException | ArrayIndexOutOfBoundsException f) {
				                           machine.crash(i+1, userCodeArray[i]);
				                           toBoxNum = 0;
				                       }
				
				                       //Check for inappropriate input
				                       try {
				                           index = Integer.parseInt(properties[3]);
				                       } catch(ArrayIndexOutOfBoundsException e) {
				                           //No machine crash because index doesn't always need to be specified
				                           //crash() will be called in specific cases for energy values that require an index
				                           index = 0;
				                       } catch(NumberFormatException f) {
				                           machine.crash(i+1, userCodeArray[i]);
				                           break;
				                       }
				
				                       if(!machine.BOOL(fromBoxNum, toBoxNum, index)) {
				                           i++; //Skip the next line if BOOL() returns false
				                       }//End of if statement
				                       break;
				
				                    case "CLEAR":
				                        machine.CLEAR();
				                        break;
				                        
				                    case "CLEARPLACE":
				                        String[] propertiesArray = userCodeArray[i].split(" ");
				                         if(propertiesArray.length == 3) {
				                             int x_spot = Integer.parseInt(propertiesArray[1]); //These will represent x_pos and y_pos
				                             int y_spot = Integer.parseInt(propertiesArray[2]); //In the CLEARPLACE method
				                             
				                             machine.CLEARPLACE(x_spot, y_spot);
				                             break;
				                         } else {
				                             machine.crash(i+1, userCodeArray[i]);
				                             break;
				                         }//End of if statement
				                        
				                    case "GET":
					                    try {
					                    	int GETx = Integer.parseInt(attributes[1]);
					                    	int GETy = Integer.parseInt(attributes[2]);
					                    
					                    	machine.GET(GETx, GETy);
					                    } catch(Exception e) {
					                    	machine.crash(i+1, e.toString());
					                    }
					                    break;
					                    
				                    case "INTERRUPT":
				                    	machine.INTERRUPT(i);
				                    	break;
				                         
				                    case "JUMP":
				                    	String jumpLabel = attributes[1];
				                    	int jumpNum = machine.JUMP(jumpLabel);
				                    	if(jumpNum < 0) machine.crash(i+1, userCodeArray[i]);
				                    	else i = jumpNum;
				                    	break;
				                         
				                    case "LABEL":
				                    	String label = attributes[1];
				                    	if(!machine.LABEL(label, i)) machine.crash(i+1, userCodeArray[i]);
				                    	break;
				                    	
				                    //Causes Intrigued Machine to crash after function
				                    case "MEMDUMP":
				                    	machine.MEMDUMP();
				                    	break;
				                    	
				                    case "MOVE":
				                            
				                        int frmBoxNum;
				                        
				                        try {                        
				                              frmBoxNum = Integer.parseInt(attributes[1]);      
				
				                            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
				                            System.out.println("MOVE failed: parse error");
				                            machine.crash(i+1, userCodeArray[i]);
				                            frmBoxNum = 0;
				                            break;
				                        } //end of try-catch
				
				                            int tBoxNum;
				                            try {
				                                tBoxNum = Integer.parseInt(attributes[2]);
				                            } catch(NumberFormatException | ArrayIndexOutOfBoundsException f) {
				                                System.out.println("MOVE failed: number or array error");
				                            	machine.crash(i+1, userCodeArray[i]);
				                                
				                                break;
				                            }
				                            boolean status = machine.MOVE(frmBoxNum, tBoxNum);
				                            if(!status) machine.crash(i+1, userCodeArray[i]);
				                            break;
				
				                    case "OPERATE":
				                       //Necessary variables for calling BOOL() function 
				                       int fromBxNum;
				                       int toBxNum;
				                       int x; //These three will do different things depending
				                       int y; //on the energy level of the OPERATE box
				                       int z;
				
				                       //Check for inappropriate input
				                       try {
				                           fromBxNum = Integer.parseInt(attributes[1]);
				                       } catch(NumberFormatException | ArrayIndexOutOfBoundsException e) {
				                           machine.crash(i+1, userCodeArray[i]);
				                           fromBxNum = 0;
				                       }
				
				                       //Check for inappropriate input
				                       try {
				                           toBxNum = Integer.parseInt(attributes[2]);
				                       } catch(NumberFormatException | ArrayIndexOutOfBoundsException e) {
				                           machine.crash(i+1, userCodeArray[i]);
				                           toBxNum = 0;
				                       }
				
				
				                       //Check for inappropriate input
				                       try {
				                           x = Integer.parseInt(attributes[3]);
				                       } catch(NumberFormatException | ArrayIndexOutOfBoundsException e) {
				                           //No crash because some OPERATE functions require this property
				                           x = 0;
				                       }
				                       
				                       try {
				                           y = Integer.parseInt(attributes[4]);
				                       } catch(NumberFormatException | java.lang.ArrayIndexOutOfBoundsException f) {
				                           //No crash because some OPERATE functions require this property
				                           y = 0;
				                       }
				
				                      //Check for inappropriate input
				                       try {
				                           z = Integer.parseInt(attributes[5]);
				                       } catch(NumberFormatException | java.lang.ArrayIndexOutOfBoundsException g) {
				                           //No crash because some OPERATE functions require this property
				                           z = 0;
				                       }
				                       
				                       machine.OPERATE(fromBxNum, toBxNum, x, y, z);
				                       break;
				                       
				                    case "PRINT":
				                        if(attributes.length != 1) {
				                            machine.crash(i+1, userCodeArray[i]);
				                        } else {
				                            machine.PRINT();
				                            break;
				                        }
				                        
				                    case "SET":
				                         if(attributes.length == 3) {
				                             int x_; //Variables must be declared
				                             int y_;// outside of try-catch
				                        	 
				                        	 try {
				                            	 x_ = Integer.parseInt(attributes[1]); //These will represent x_pos and y_pos
				                            	 y_ = Integer.parseInt(attributes[2]); //In the CLEARPLACE method
				                             } catch(NumberFormatException l) {
				                            	 machine.crash(i+1, userCodeArray[i]);
				                            	 x_ = 0;// For compiler
				                            	 y_ = 0;
				                             }
				                            	 
				                             machine.SET(x_, y_);
				                             break;
				                         } else {
				                             machine.crash(i+1, userCodeArray[i]);
				                             break;
				                         }//End of if statement
				                        
				                    case "TIME":
				                        if(attributes.length != 2) {
				                        	System.out.println("attributes list too long in TIME");
				                            machine.crash(i+1, userCodeArray[i]);
				                        } else {
				                        	try{
				                        		extratime = Integer.parseInt(attributes[1]);
				                        	
				                        	} catch(NumberFormatException n)	{
				                        		machine.crash(i+1, userCodeArray[i]);
				                        	}
				                            break;
				                        }
				                        
				                    case "WAIT":
				                    	try{
				                    		int length = Integer.parseInt(attributes[1]);
				                    		sleep(length);
				                    		
				                    	} catch(NumberFormatException | java.lang.ArrayIndexOutOfBoundsException | InterruptedException e) {
				                    		machine.crash(i+1, userCodeArray[i]);
				                    	}
				                    	break;
				                         
				                    case "ZERO":
				                    	try {
				                    		int box = Integer.parseInt(attributes[1]);
				                    		if(!machine.ZERO(box)) machine.crash(i+1, userCodeArray[i]);
				                    	} catch(NumberFormatException | java.lang.ArrayIndexOutOfBoundsException e) {
				                    		machine.crash(i+1, userCodeArray[i]);
				                    	}
				                    	break;
				                    	
				                    default:
				                        System.out.println("Hit default!");
				                        System.out.println("Current String to be parsed: " + userCodeArray[i]);
				                        machine.crash(i+1, userCodeArray[i]);
				                }
				            } else {
				            	break;
				            }//End of if statement that encapsulates all command handling and checks for crashes
        	    		
	        	    		try {
	        	    			System.out.println("sleep starting");
								sleep(commandtime + extratime);
								System.out.println("Sleep finished");
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
        	    		}
        	    	} //end of run() method
        	    	}.start(); //end of inner Thread class
    }                          
    

    //Sets up Intrigued machine and the codecenter
    public static void main(String args[]) {
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CodeCenter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CodeCenter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CodeCenter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CodeCenter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
         */

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CodeCenter().setVisible(true);
            }
        });
    }

    //Takes the user code, splits it into a single String for each line, puts each String into userCodeArray, and returns userCodeArray
    public String[] getTextasArray() {
            
        String [] userCodeArray = jTextArea1.getText().split("\n");
        return userCodeArray;
    } //End of getTextasArray;
    
    // Variables declaration - do not modify                     
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration                   
}
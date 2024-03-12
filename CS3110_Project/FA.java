import java.util.*;
import java.util.Arrays;
import static java.lang.Math.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class FA
	
{
	public static void main(String[] args) {
	    
	Scanner scanner = new Scanner(System.in);
        String[] input1 = new String[20];
        int numStates = 0;
        int finalState[];
        String alphabet[]; //if alphabet, convert to int
        String transitions[][];        
        try{
            //input from the file
               
                Scanner keyboard = new Scanner(System.in);
                System.out.println("Enter the name or path of the file");
                String filename = keyboard.nextLine();
                File file = new File(filename);
                Scanner sc = new Scanner(file);
                numStates = sc.nextInt();
                

                sc.nextLine();
                
                String lineFinalState[] = sc.nextLine().split(" "); //read the final state to temp string array
                finalState = new int [lineFinalState.length];
                for(int i = 0; i < lineFinalState.length; i++)
                {
                    finalState[i] = Integer.parseInt(String.valueOf(lineFinalState[i])); //convert string type to int type
                    
                }

                //Do the same with the alphabet               
                String lineAlphabet[] = sc.nextLine().trim().split(" ");
                alphabet =  new String [lineAlphabet.length];
                for(int i = 0; i < lineAlphabet.length; i++)
                {
                    alphabet[i] = String.valueOf(lineAlphabet[i]); //convert char type to int type
                }              
                    
                  //num of rows of the transitions = (num of States)*(num of alphabet)
                  //num of cols of the transitions table  = 3
                        transitions = new String[numStates*(alphabet.length)][3];
                        for(int i = 0; i < (numStates*(alphabet.length)); i++ )
                        {

                            String [] line = sc.nextLine().trim().split(" ");                           
                            
                            for(int j = 0; j < 3; j++)
                         {
                              transitions[i][j] = line[j+1]; //to skip '(' and ')'

                         }
                                                                                  
                        }

                     
                
                int i = 0;            
                while(sc.hasNextLine() && (i<20))
                {                 
                        String str = sc.nextLine();
                        input1[i] = str;
                        i++;                        
                }

                //run FA function 
                FA(input1, transitions, numStates, finalState, alphabet);
               

                      
            }
       
            catch (Exception e)
             {
               System.out.println("File doesn't exist!");
                
            }
            
            }

public static void FA (String[] input, String[][] transitions, int numStates, int[] finalState, String[]alphabet)
{
    
    System.out.print("Finite State Automaton :\n" +
        "1) number of states: " + numStates+ "\n" +
        "2) final states: ");
        printArray1(finalState);
    System.out.print ("\n3) alphabet:");
        printArray(alphabet);   
    System.out.print ( "\n4) transitions: \n" );
         printMatrix(transitions);
        
      System.out.print("5) strings: \n");
       
      
    for(int j = 0; j < input.length; j++)
    {
        int count = 0;
        char a;
        int state = 0; //start at initial state
        
       if(validInput(input[j], alphabet)) 
       {
                while(count < input[j].length())
                {
                    a = input[j].charAt(count); //test each character in the string
                   // System.out.println("a" + a);
                    if(state == 0)
                    {
                        for(int i = 0; i < alphabet.length; i++) 
                        {
                            if(String.valueOf(a).equals(transitions[i][1])) //compare the value to transition table
                                {
                                    state = Integer.parseInt(transitions[i][2]);
                                  
                                    break;
                                }
                            
                        }
                    }
                    
                
                  else if(state != 0)
	       {
                for(int i = (alphabet.length*state); i < (alphabet.length*(state+1)); i++)
                {
                if(String.valueOf(a).equals(transitions[i][1]))
                    {
                        state = Integer.parseInt(transitions[i][2]);
                        break;
                    }
                
                }
            
	        }  
                    count ++;
                }

                if(appearInArray(finalState, state))
                {
                    System.out.println(input[j] +" Accept");
                }
                
        	
                else
                    System.out.println(input[j]+ " Reject");
                
                    
               
	    }
            
        	
        	else
        	   System.out.println(input[j]+ " Reject");
    }
    
}


private static boolean appearInArray(int line[], int a)
      {
          boolean appear = false;
          for(int i = 0; i < line.length; i++)
          {
            if(a == line[i])
            {
                appear = true;
                break;
            }
            
          }
          
          return appear;
      }
private static void printArray(String[] matrix)
{
    for(int i = 0; i < matrix.length; i++)
    {
        System.out.print(matrix[i]);
        if(i < ((matrix.length)-1))
        System.out.print(", ");
    }
}

private static void printArray1(int[] matrix)
{
    for(int i = 0; i < matrix.length; i++)
    {
        System.out.print(matrix[i]);
        if(i < ((matrix.length)-1))
        System.out.print(", ");
    }
}


private static void printMatrix (String[][] matrix)
    {  for(int j = 0; j < matrix.length; j++)
       {
          
           for(int u = 0; u < matrix[0].length; u++)
           {
               System.out.print(matrix[j][u]);
               System.out.print(" ");
           }
            System.out.println(" ");
       }
    }

    private static boolean appearInAlphabet(String alphabet[], char a)
    {
        boolean appear = false;
        for(int i = 0; i < alphabet.length; i++)
        {
          if(String.valueOf(a).equals(alphabet[i]))
          {
              appear = true;
              break;
          }
          
        }
        
        return appear;
    }
  
  public static boolean validInput(String str, String[] alphabet)
  {
      boolean valid = true;
      
      for(int i = 0; i < str.length(); i++)
      {
         
          if(appearInAlphabet(alphabet, str.charAt(i)) == false)
          {
              valid = false;
              break;
          }
         
      }
  
      if (str.length() == 0)
      {
          valid = false;
      }
      return valid;
  }

        }
    

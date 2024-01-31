// StoreController.java

package controller;

import java.util.Scanner;

// Reads user input from the console
public class StoreController {
    
    Scanner scan;

    public StoreController(){

        scan = new Scanner(System.in);
    }

    // Makes sure input is an int and valid then sends it
    public int getInputInt(){

        int num;

        while(true){

            if(scan.hasNextInt()){
                num = scan.nextInt();
    
                if(num >= 0){
                    return num;
                }
                else{
                    System.out.println("Invalid Input - Integer has to be 0 or more");
                }
            }
            else{
                System.out.println("Invalid Input - Input has to be an integer");
            }

        }
    }

    // Makes sure input is a double and valid then sends it
    public double getInputDouble(){

        double num;
        while(true){
            if(scan.hasNextDouble()){
                num = scan.nextDouble();

                if(num >= 0){
                    return num;
                }
                else{
                    System.out.println("Invalid Input - Integer has to be 0 or more");
                }
            }
            else{
                System.out.println("Invalid Input - Input has to be a number");
            }
        }
    }

    // Makes sure input is a String and valid then sends it
    public String getInputString(){

        String input;
        while(true){
            if(scan.hasNextLine()){
                input = scan.nextLine();

                return input;
            }
            else{
                System.out.println("Invalid Input");
            }
        }
    }
}
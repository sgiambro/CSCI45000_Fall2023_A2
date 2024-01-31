//StoreView.java

package view;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.io.*;
import common.StoreOp;
import controller.StoreController;

public class StoreView {
    
    StoreOp stub;
    StoreController input;
    int userInt, index, type;
    double userDouble;
    String userString;

    // Constructor that initilizes the variables
    // Sets up connection to remote server that the accounts use to access the store
    public StoreView(){
        try{
            stub = (StoreOp)Naming.lookup("//in-csci-rrpc01.cs.iupui.edu:1234/Store");
            System.out.println("---Client calling server---");
        }
        catch(Exception e){

            System.out.println("Client err:" + e.getMessage());
            e.printStackTrace();
        }

        input = new StoreController();
    }

    // Menu for when you first enter the online store
    public void loginMenu(){

        String n, un, p;

        System.out.println("Welcome to our online store!");
        System.out.println("What what whould you like to do?");
        
        try{
            while(true){

                System.out.println("Login---------------1");
                System.out.println("Register Account----2");
                System.out.println("QUIT----------------3");

                userInt = input.getInputInt();

                // Login menu for existing accounts
                if(userInt == 1){
                    
                    System.out.println("\n\n-----------LOGIN SCREEN-----------");

                    System.out.print("\nUsername:   ");
                    un = input.getInputString();

                    System.out.print("\nPassword:   ");
                    p = input.getInputString();

                    index = stub.login(un, p);

                    // tests if account is customer or admin
                    if(p != "779988"){
                        type = 1;
                    }
                    else{
                        type = 2;
                    }

                    // If account not found then break out of login menu
                    if(index != -1){
                        break;
                    }
                }

                // A new user can create an account
                // password 779988 is only for admins
                else if(userInt == 2){
                    
                    System.out.println("\n\n-----------SIGN UP SCREEN-----------");

                    System.out.print("\nYour Name:   ");
                    n = input.getInputString();

                    System.out.print("\nCreate Username:   ");
                    un = input.getInputString();

                    System.out.print("\nCreate Password:   ");
                    p = input.getInputString();

                    // tests if account is customer or admin
                    if(p != "779988"){
                        type = 1;
                    }
                    else{
                        type = 2;
                    }

                    index = stub.addAcount(n, un, p, type);

                }
                // Quits out of the program if quit is selected
                else if(userInt == 3){
                    System.exit(0);
                }

                else{
                    System.out.println("\n***INVALID INPUT***\n\n");
                }
            }
        }

        catch(Exception e){

            System.out.println("Client err:" + e.getMessage());
            e.printStackTrace();
        }
    }

    public void customerMenu(){

        int pn, f;
        double m;

        System.out.println("\n\n-----------Customer Menu-----------");
        try{
            while(true){

                System.out.println("Browse Products---------1");
                System.out.println("Add Item to Cart--------2");
                System.out.println("View Cart---------------3");
                System.out.println("Buy Cart----------------4");
                System.out.println("Add Money To Account----5");
                System.out.println("QUIT--------------------6");

                userInt = input.getInputInt();

                // Shows all items that the store is selling
                if(userInt == 1){
                    
                    System.out.println("\n\n-----------Product SCREEN-----------");

                    stub.showInventory();
                    
                }

                // Adds an item to the customers cart based on the given product number
                else if(userInt == 2){
                    
                    System.out.println("\n\n-----------Add Item SCREEN-----------");

                    System.out.print("\nProduct Number:   ");
                    pn = input.getInputInt();

                    if(pn >= 0 && pn <= stub.getItemNum()-1){

                        stub.addCustomerItem(index, pn);
                    }
                }

                // Shows the user whats in their cart
                else if(userInt == 3){
                    
                    System.out.println("\n\n-----------Cart SCREEN-----------");

                    stub.customerCart(index);
                    
                }

                // Users can buy the items in their cart if they have the money to do so
                else if(userInt == 4){
                    
                    System.out.println("\n\n-----------Buy Cart SCREEN-----------");

                    f = stub.customerBuy(index);

                    if(f == 1){

                        System.out.println("Transaction Complete.\nThanks For Shopping!");
                    }
                    else if(f == 2){

                        System.out.println("Transaction Incomplete.\nNot Enough Money In Account.");
                    }
                }

                // The customer can add money to their account
                else if(userInt == 5){

                    System.out.println("\n\n-----------Add Money SCREEN-----------");

                    System.out.print("\nAmount:   ");
                    m = input.getInputDouble();

                    stub.addCustomerMoney(index, m);

                }

                // Quits out of the program if quit is selected
                else if(userInt == 6){
                    System.exit(0);
                }

                else{
                    System.out.println("\n***INVALID INPUT***\n\n");
                }
            }
        }

        catch(Exception e){

            System.out.println("Client err:" + e.getMessage());
            e.printStackTrace();
        }
    }

    public void adminMenu(){

        String n, un, p, des;
        int pn, a;
        double c;

        System.out.println("\n\n-----------Admin Menu-----------");
        try{
            while(true){

                System.out.println("Show Inventory--------1");
                System.out.println("Add Item--------------2");
                System.out.println("Remove Item-----------3");
                System.out.println("Add Item Amount-------4");
                System.out.println("Remove Item Amount----5");
                System.out.println("Show Accounts---------6");
                System.out.println("Add Account-----------7");
                System.out.println("Remove Account--------8");
                System.out.println("QUIT------------------9");

                userInt = input.getInputInt();

                // Shows all items that the store is selling
                if(userInt == 1){
                    
                    System.out.println("\n\n-----------Product SCREEN-----------");

                    stub.showInventory();
                    
                }

                // Adds an item to the store's inventory
                else if(userInt == 2){
                    
                    System.out.println("\n\n-----------Add Item SCREEN-----------");

                    System.out.print("\nName:   ");
                    n = input.getInputString();

                    System.out.print("\nDescription:   ");
                    des = input.getInputString();

                    System.out.print("\nPrice:   ");
                    c = input.getInputDouble();

                    System.out.print("\nAmount:   ");
                    a = input.getInputInt();

                    stub.addItem(n, des, c, a);

                }

                // Removes an item from the store's inventory
                else if(userInt == 3){
                    
                    System.out.println("\n\n-----------Remove Item SCREEN-----------");

                    System.out.println("\nProduct Number:   ");
                    pn = input.getInputInt();

                    stub.removeItem(pn);
                    
                }

                // Adds more of an item to the store's inventory
                else if(userInt == 4){
                    
                    System.out.println("\n\n-----------Add Item Amount SCREEN-----------");

                    System.out.println("\nProduct Number:   ");
                    pn = input.getInputInt();

                    System.out.println("\nAmount To Add:   ");
                    a = input.getInputInt();

                    stub.addItemAmount(a, pn);
                    
                }

                // Removes some of an item to the store's inventory
                else if(userInt == 5){

                    System.out.println("\n\n-----------Reduce Item Amount SCREEN-----------");

                    System.out.println("\nProduct Number:   ");
                    pn = input.getInputInt();

                    System.out.println("\nAmount To Remove:   ");
                    a = input.getInputInt();

                    stub.reduceItemAmount(a, pn);

                }

                // Shows all the store's accounts
                else if(userInt == 6){
                    
                    System.out.println("\n\n-----------Account SCREEN-----------");

                    System.out.println("\nAdmin Accounts");
                    stub.getAdmins();

                    System.out.println("\nCustomer Accounts");
                    stub.getCustomer();
                }

                // Adds an account to the store
                else if(userInt == 7){
                    
                    System.out.println("\n\n-----------Add Account SCREEN-----------");

                    System.out.print("\nName:   ");
                    n = input.getInputString();

                    System.out.print("\nCreate Username:   ");
                    un = input.getInputString();

                    System.out.print("\nCreate Password:   ");
                    p = input.getInputString();

                    // tests if account is customer or admin
                    if(p != "779988"){

                        stub.addAcount(n, un, p, 1);
                    }
                    else{

                        stub.addAcount(n, un, p, 2);
                    }
                }

                // Removes an account from the store
                else if(userInt == 8){
                    
                    System.out.println("\n\n-----------Remove Account SCREEN-----------");

                    System.out.println("\nCustomer Account Number:   ");
                    userInt = input.getInputInt();

                    stub.removeAcount(userInt, 1);

                }

                // Quits out of the program if quit is selected
                else if(userInt == 9){
                    System.exit(0);
                }

                else{
                    System.out.println("\n***INVALID INPUT***\n\n");
                }
            }
        }

        catch(Exception e){

            System.out.println("Client err:" + e.getMessage());
            e.printStackTrace();
        }
    }

    // returns if account is customer or admin
    public int getType(){
        return type;
    }
    
    public static void main(String[] args){

        StoreView sv = new StoreView();

        sv.loginMenu();
        
        if(sv.getType() == 1){

            sv.customerMenu();
        }
        else if(sv.getType() == 2){

            sv.adminMenu();
        }
    }

}


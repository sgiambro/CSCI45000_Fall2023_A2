//Store.java

package model;

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import common.StoreOp;

public class Store extends UnicastRemoteObject implements StoreOp{

    Users customers[];
    Administrators admins[];
    Items inventory[];
    int customerNum, adminNum, itemNum;

    // The Store's constructor
    // Every store needs at least one administrators to run the store
    Store()throws RemoteException{
        super();

        customers = new Users[30];
        admins = new Administrators[30];
        inventory = new Items[30];

        customerNum = 0;
        adminNum = 1;
        itemNum = 0;

        Administrators firstAdmin = new Administrators("Admin Prime", "AdminPrime", "779988");
        admins[0] = firstAdmin;

    }

    // Adds a new customer if type is 1 and a new admin if type is 2
    // Returns the index of the new account for easy lookup
    public int addAcount(String n, String un, String p, int type)throws RemoteException{

        if(type == 1){

            Users newUser = new Users(n, un, p, 0.00);
            customers[customerNum] = newUser;

            customerNum++;

            return (customerNum - 1);
        }
        else if(type == 2){

            Administrators newAdmin = new Administrators(n, un, p);
            admins[adminNum] = newAdmin;

            adminNum++;

            return (adminNum - 1);
        }

        return -1;
    }

    // Removes a customer if type is 1 and an admin if type is 2
    public void removeAcount(int index, int type)throws RemoteException{

        if(type == 1){

            for(int i = index; i<customerNum-1; i++){
                customers[i] = customers[i+1];
            }

            customerNum--;
        }
        else if(type == 2){

            for(int i = index; i<adminNum-1; i++){
                admins[i] = admins[i+1];
            }

            adminNum--;
        }

    }
    
    // Matches target username and password with current store accounts
    // Password 779988 is reserved for only admins 
    // Returns the index of the account for easy lookup or -1 for no match
    public int login(String un, String p)throws RemoteException{

        if(p == "779988"){

            for(int i = 0; i<adminNum; i++){

                if(un == admins[i].getUsername()){
                    return i;
                }
            }
        }

        for(int i = 0; i<customerNum; i++){

            if(un == customers[i].getUsername() && p == customers[i].getPassword()){
                return i;
            }
        }

        return -1;
    }
    
    // Returns the list of store customer accounts
    public void getCustomer()throws RemoteException{

        for(int i = 0; i<customerNum; i++){

            System.out.println("Account Number:        " + i);
            System.out.println("Account Holder Name:   " + customers[i].getName());
            System.out.println("Account Username:      " + customers[i].getUsername());
            System.out.println("Account Password:      " + customers[i].getPassword());
            System.out.println("------------------------\n");

        }
    }

    // gives the total amount of customer accounts
    public int getCusNum()throws RemoteException{

        return customerNum;
    }

    
    // Returns the list of store admin accounts
    public void getAdmins()throws RemoteException{

        for(int i = 0; i<adminNum; i++){

            System.out.println("Account Number:        " + i);
            System.out.println("Account Holder Name:   " + admins[i].getName());
            System.out.println("Account Username:      " + admins[i].getUsername());
            System.out.println("Account Password:      " + admins[i].getPassword());
            System.out.println("------------------------\n");

        }
    }

    // gives the total amount of Adimn accounts
    public int getAdminNum()throws RemoteException{

        return adminNum;
    }
    
    // Adds a new item to the store's inventory
    public void addItem(String n, String des, double c, int a)throws RemoteException{

        Items newItem = new Items(n, des, c, a);
        inventory[itemNum] = newItem;

        itemNum++;
    }
    
    // Updates an item based on admins changes
    public void updateItem(String n, String des, double c, int a, int index)throws RemoteException{

        inventory[index].setName(n);
        inventory[index].setDescription(des);
        inventory[index].setCost(c);
        inventory[index].setAmount(a);
    }
    
    // Removes an item from the store's inventory
    public void removeItem(int index)throws RemoteException{

        for(int i = index; i<itemNum-1; i++){
            inventory[i] = inventory[i+1];
        }

        itemNum--;
    }

    // gives the total amount of items in the store's inventory
    public int getItemNum()throws RemoteException{

        return itemNum;
    }
    
    // Adds more of the item to the store
    public void addItemAmount(int addA, int index)throws RemoteException{

        inventory[index].addAmount(addA);
    }
    
    // Removes some of the item from the store
    public void reduceItemAmount(int subA, int index)throws RemoteException{

        inventory[index].subAmount(subA);
    }
    
    // Returns the store's inventory
    public void showInventory()throws RemoteException{

        for(int i = 0; i<itemNum; i++){

            System.out.println("---" + inventory[i].getName() + "---");
            System.out.println(inventory[i].getDescription());
            System.out.println("\nProduct Number:   " + i);
            System.out.println("------------------------\n");

        }
    }
    
    // Removes the specified amount of money from the customer's account
    public void removeCustomerMoney(int index, double subM)throws RemoteException{

        customers[index].removeMoney(subM);
    }
    
    // Adds the specified amount of money to the customer's account
    public void addCustomerMoney(int index, double addM)throws RemoteException{

        customers[index].removeMoney(addM);
    }
    
    // Adds the requested item to the customers cart
    // returns 1 if the action was succsesful
    // returns 2 if there werent enough items
    public int addCustomerItem(int index, int item)throws RemoteException{

        if(inventory[item].getAmount() > 0){

            customers[index].addItemtoCart(inventory[item]);
            inventory[item].subAmount(1);
            return 1;
        }
        else{

            return 2;
        }
        
    }

    // Get the items in the customer's cart
    public void customerCart(int index)throws RemoteException{

        customers[index].getCart();
    }
    
    // Customer buys all the items in their cart
    public int customerBuy(int index)throws RemoteException{

        return customers[index].buyCart();
    }

}
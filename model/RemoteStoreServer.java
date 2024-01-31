// RemoteStoreServer.java

package model;

import java.rmi.*;
import java.rmi.registry.*;
import common.StoreOp;

// Sets up the remote server that the store's accounts will use to access the store
public class RemoteStoreServer {
    
    public static void main (String args[]){
        try{
            
            Store remote_obj = new Store();
            Naming.rebind("//in-csci-rrpc01.cs.iupui.edu:1234/Store", remote_obj);
            System.out.println("---Server is ready---");

        }
        catch(Exception e){

            System.out.println("Server err:" + e.getMessage());
            e.printStackTrace();
        }

    }

}
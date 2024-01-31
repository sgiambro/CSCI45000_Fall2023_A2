//StoreOp.java

package common;

import java.rmi.*;

public interface StoreOp extends Remote{
    
    // Acount methods
    public int addAcount(String n, String un, String p, int type)throws RemoteException;
    public void removeAcount(int index, int type)throws RemoteException;
    public int login(String un, String p)throws RemoteException;
    public void getCustomer()throws RemoteException;
    public int getCusNum()throws RemoteException;
    public void getAdmins()throws RemoteException;
    public int getAdminNum()throws RemoteException;

    
    // Item methods
    public void addItem(String n, String des, double c, int a)throws RemoteException;
    public void updateItem(String n, String des, double c, int a, int index)throws RemoteException;
    public void removeItem(int index)throws RemoteException;
    public int getItemNum()throws RemoteException;
    public void addItemAmount(int addA, int index)throws RemoteException;
    public void reduceItemAmount(int subA, int index)throws RemoteException;
    public void showInventory()throws RemoteException;
    
    // Customer methods
    public void removeCustomerMoney(int index, double subM)throws RemoteException;
    public void addCustomerMoney(int index, double addM)throws RemoteException;
    public int addCustomerItem(int index, int item)throws RemoteException;
    public void customerCart(int index)throws RemoteException;
    public int customerBuy(int index)throws RemoteException;


}

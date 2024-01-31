// Account.java

package model;

// Parent class for admins and customers
public class Account {
    
    String name, username, password;

    public Account(String n, String un, String p){

        name = n;
        username = un; 
        password = p;

    }

    public String getName(){

        return name;
    }

    public String getUsername(){

        return username;
    }

    public String getPassword(){

        return password;
    }

    public boolean isAdmin(){

        if (password == "779988"){
            return true;
        }

        else{
            return false;
        }

    }
}

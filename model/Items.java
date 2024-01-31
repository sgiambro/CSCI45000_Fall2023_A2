// Items.java

package model;

// Class for all the store's goods
public class Items {
    
    String name, description;
    double cost;
    int amount;

    public Items(String n, String des, double c, int a){

        name = n;
        description = des; 
        cost = c;
        amount = a;
    }

    public String getName(){

        return name;
    }

    public void setName(String n){

        name = n;
    }

    public String getDescription(){

        return description;
    }

    public void setDescription(String des){

        description = des;
    }

    public double getCost(){

        return cost;
    }

    public void setCost(double c){

        cost = c;
    }

    public int getAmount(){

        return amount;
    }

    public void setAmount(int a){
        amount = a;
    }

    // Adds requested number of items to store
    public void addAmount(int a){

        amount = amount + a;
    }

    // Removes requested number of items to store
    public void subAmount(int a){

        amount = amount - a;
    }
}

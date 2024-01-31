// Users.java

package model;

// Child of Account
public class Users extends Account {
    
    Items[] cart;
    Items[] bought;
    int cartNum, boughtNum;
    double money;

    public Users(String n, String un, String p, double m){

        super(n, un, p);

        cart = new Items[30];
        bought = new Items[30];

        cartNum = 0;
        boughtNum = 0;
        money = m;
    }

    public void addItemtoCart(Items item){

        cart[cartNum] = item;

        cartNum++;
    }

    public void getCart(){

        System.out.println("Your Cart");

        for(int i = 0; i<cartNum; i++){

            System.out.println((i+1) + "--" + cart[i].getName());
        }
    }

    public int cartNum(){
        return cartNum;
    }

    public int getBoughtNum(){
        return boughtNum;
    }

    public int buyCart(){

        for( int i = 0; i<cartNum; i++){

            if(money >= cart[i].getCost()){

                this.removeMoney(cart[i].getCost());
                bought[boughtNum] = cart[i];
                boughtNum++;

            }
            else{
                return 2;
            }
        }

        return 1;
    }

    public void addMoney(double m){

        if(m >= 0){
            money = money + money;
            money = (Math.floor(money*100))/100;
        }
    }

    public void removeMoney(double m){

        if(m >= 0 && money >= m){
            m = (Math.floor(m*100))/100;
            money = money - m;
            money = (Math.floor(money*100))/100;
        }
    }

    public double getMoney(){

        return money;
    }


}

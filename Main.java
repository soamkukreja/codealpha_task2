import java.util.Scanner;

class Stock{
    String symbol;
    String name;
    double price;
    public Stock(String symbol,String name,double price){
        this.symbol=symbol;
        this.name=name;
        this.price=price;
    }
}
class Portfolio{
    String[] symbols=new String[20];
    int[] quantities =new int[20];
    int stockCount=0;
    double balance;
    public Portfolio(double initialBalance){
        this.balance=initialBalance;
    }
    public void buyStock(String symbol,int quantity,double price){
        double totalCost=quantity*price;
        if(totalCost<=balance){
            int index=findStock(symbol);
            if(index==-1){
                symbols[stockCount]=symbol;
                quantities[stockCount]=quantity;
                stockCount++;

            }
            else{
                quantities[index]+=quantity;
            }
            balance-=totalCost;
            System.out.println("Bought "+quantity+" shares of "+symbol);
        } else{
            System.out.println("Not enough balance.");
        }
    }
    public  void sellStock(String symbol,int quantity,double price){
        int index=findStock(symbol);
        if(index!=-1&&quantities[index]>=quantity){
            quantities[index]-=quantity;
            if(quantities[index]==0){
                removeStock(index);
            }
            balance+=quantity*price;
            System.out.println("Sold "+quantity+" shares of "+symbol);
        } else{
            System.out.println("Not enough shares to sell.");
        }
    }
public  void showPortfolio(){
    System.out.println("\nPortfolio");
    for(int i=0;i<stockCount;i++){
        System.out.println(symbols[i]+": "+quantities[i]+" shares");
    }
    System.out.println("Balance: Rs"+balance);
}
private int findStock(String symbol){
        for(int i=0;i<stockCount;i++){
            if(symbols[i].equals(symbol)){
                return i;
            }
        }
        return -1;
}
private  void removeStock(int index){
        for(int i=index;i<stockCount-1;i++){
            symbols[i]=symbols[i+1];
            quantities[i]=quantities[i+1];
        }
        stockCount--;
}

}

public class Main{
static Stock[] market=new Stock[5];
    static Scanner sc=new Scanner(System.in);

public static void main(String[] args) {

    market[0]=new Stock("A","Car",22222222);
    market[1]=new Stock("B","Mobile",22245522);
    market[2]=new Stock("C","Daraz",600000000);
    market[3]=new Stock("D","Laptop",50000);
    market[4]=new Stock("E","Gold",300000);
        Portfolio portfolio=new Portfolio(1000000);
        while(true){
            System.out.println("*******Stock Trading Platform*******");
            System.out.println("\n1. View Market\n2. Buy Stock\n3. Sell Stock\n4. View Portfolio\n5. Exit");
            System.out.print("Choose: ");
            int choice = sc.nextInt();
            if(choice==1){
                viewMarket();
            } else if (choice==2) {
                buyStock(portfolio);
            } else if (choice==3) {
                sellStock(portfolio);
                
            } else if (choice==4) {
                portfolio.showPortfolio();
            } else if (choice==5) {
                System.out.println("Goodbye!");break;
            } else{
                System.out.println("Invalid option.");
            }
        }
}
static void viewMarket(){
    System.out.println("\nMarket:");
    for(Stock stock:market){
        System.out.println(stock.symbol+" - "+stock.name+" - "+stock.price +" rs");
    }
}
static void buyStock(Portfolio portfolio){
    System.out.println("Enter stock symbol: ");
    String symbol=sc.next();
    Stock stock=findStock(symbol);
    if (stock == null) {
        System.out.println("Stock not found.");
        return;
    }
    System.out.print("Enter quantity: ");
    int quantity = sc.nextInt();
    portfolio.buyStock(symbol, quantity, stock.price);
}
static void sellStock(Portfolio portfolio){
    System.out.print("Enter stock symbol: ");
    String symbol= sc.next();
    Stock stock=findStock(symbol);
    if(stock==null){
        System.out.println("Stock not found.");
        return;
    }
    System.out.print("Enter quantity: ");
    int qunatity = sc.nextInt();
    portfolio.sellStock(symbol,qunatity,stock.price);
}
static Stock findStock(String symbol){
    for(Stock stock:market){
        if(stock.symbol.equals(symbol)){
            return stock;
        }
    }
    return null;
}



}
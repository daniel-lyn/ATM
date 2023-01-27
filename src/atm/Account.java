package atm;

import java.util.ArrayList;

public class Account {

    private String name;
    private String uuid;
    private User holder;
    private ArrayList<Transaction> transactions;

    public Account(String name, User holder, Bank theBank){
        this.name = name;
        this.holder = holder;
        this.uuid = theBank.getNewAccountUUID();
        this.transactions = new ArrayList<Transaction>();
    }

    public String getUUID() {
        return this.uuid;
    }

    public String getSummaryLine() {
        double balance = this.getBalance();
        if(balance >= 0) {
            return String.format("%s: $%.02f : %s", this.uuid, balance, this.name);
        }else{
            return String.format("%s: $(%.02f) : %s", this.uuid, balance, this.name);
        }
    }

    public double getBalance() {
        double balance = 0;
        for(Transaction tx : transactions) {
            balance += tx.getAmount();
        }
        return balance;
    }

    public void printTransactionHistory() {
        System.out.printf("\nTransaction history for account %s\n", this.uuid);
        for(int i= this.transactions.size()-1;i>=0;i--){
            System.out.printf(this.transactions.get(i).getSummaryLine());
        }
        System.out.println();
    }
}

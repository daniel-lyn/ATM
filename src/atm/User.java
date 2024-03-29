package atm;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.security.MessageDigest;
public class User {
    private  String firstName;
    private String lastName;
    private String uuid;
    private byte pinHash[]; //MD5 hash of the user;
    private ArrayList<Account> accounts;

    public User(String firstName, String lastName, String pin, Bank theBank) {
        this.firstName = firstName;
        this.lastName = lastName;

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            this.pinHash = md.digest(pin.getBytes());
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error: " + e.getMessage());
            throw new RuntimeException(e);
        }

        this.uuid = theBank.getNewUserUUID();
        this.accounts = new ArrayList<Account>();
        System.out.printf("New user %s, %s with ID %s created. \n", lastName, firstName, this.uuid);
    }

    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    public String getUUID() {
        return this.uuid;
    }

    public boolean validatePin(String pin) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return MessageDigest.isEqual(md.digest(pin.getBytes()),
                    this.pinHash);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        return false;
    }

    public String getfirstName() {
        return this.firstName;
    }

    public void printAccountsSummary() {
        System.out.printf("\n\n%s's accounts summary", this.firstName);
        for(int i = 0; i<this.accounts.size();i++){
            System.out.printf("%d. %s\n", i+1,
                    this.accounts.get(i).getSummaryLine());
        }
        System.out.println();
    }

    public int numAccounts() {
        return this.accounts.size();
    }

    public void printTransactionHistory(int acctIdx) {
        this.accounts.get(acctIdx).printTransactionHistory();
    }
}

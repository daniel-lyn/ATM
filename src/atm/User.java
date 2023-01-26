package atm;

import java.util.ArrayList;

public class User {
    private  String firstName;
    private String lastName;
    private String uuid;
    private byte pinHash[]; //MD5 hash of the user;
    private ArrayList<Account> accounts;

}

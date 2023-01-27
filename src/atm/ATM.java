package atm;

import java.util.Scanner;

public class ATM {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        Bank theBank = new Bank("Bank of East Asia");
        User aUser = theBank.addUser("Daniel","Lee","1357");
        Account newAccount = new Account("Checking",aUser,theBank);
        aUser.addAccount(newAccount);
        theBank.addAccount(newAccount);

        User currentUser;

        while(true){
            currentUser = ATM.mainMenuPrompt(theBank,scanner);
            ATM.printUserMenu(currentUser,scanner);
        }

    }

    public static void printUserMenu(User currentUser, Scanner scanner) {
        currentUser.printAccountsSummary();
        int choice;
        do{
            System.out.printf("Welcome %s, what would you like to do?",currentUser.getfirstName());
            System.out.println("1. Show account transaction history...");
            System.out.println("2. Withdrawal...");
            System.out.println("3. Deposit...");
            System.out.println("4. Transfer...");
            System.out.println("5. Exit...");
            System.out.println();
            System.out.println("Enter your choice...");
            choice = scanner.nextInt();
            if(choice < 1 || choice > 5){
                System.out.println("Please enter a number from 1-5...");
            }
        } while(choice < 1 || choice > 5);
        switch (choice){
            case 1:
                ATM.showTransactionHistory(currentUser,scanner);
                break;
            case 2:
                ATM.withdrawal(currentUser,scanner);
                break;
            case 3:
                ATM.deposit(currentUser,scanner);
                break;
            case 4:
                ATM.transfer(currentUser,scanner);
                break;
        }
        if(choice != 5){
            ATM.printUserMenu(currentUser,scanner);
        }
    }

    public static void showTransactionHistory(User currentUser, Scanner scanner) {
        int theAccount;
        do{
            System.out.printf("Enter the number (1-%d) of the account"+
                    "whose transactions you want to see: ",
                    currentUser.numAccounts());
            theAccount = scanner.nextInt()-1;
            if(theAccount < 0 || theAccount > currentUser.numAccounts()){
                System.out.println("Invalid account. Please try again...");
            }
        }while(theAccount < 0 || theAccount > currentUser.numAccounts());

        currentUser.printTransactionHistory(theAccount);
    }

    public static User mainMenuPrompt(Bank theBank, Scanner scanner) {
        String userID;
        String pin;
        User authUser;
        do{
            System.out.printf("\n\nWelcome to %s \n\n",theBank.getName());
            System.out.print("Enter user ID: ");
            userID = scanner.nextLine();
            System.out.print("Enter pin: ");
            pin = scanner.nextLine();
            authUser = theBank.userLogin(userID,pin);
            if(authUser==null){
                System.out.println("Incorrect user ID/pin. "+"Please Try again.");
            }
        } while(authUser == null);

        return authUser;
    }
}

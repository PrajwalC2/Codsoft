package ATMinterface;

import java.util.Scanner;

//Bank Account class
class BankAccount {
 private double balance;

 public BankAccount(double balance) {
     this.balance = balance;
 }

 public double getBalance() {
     return balance;
 }

 public void deposit(double amount) {
     balance += amount;
 }

 public boolean withdraw(double amount) {
     if (amount <= balance) {
         balance -= amount;
         return true;
     } else {
         return false;
     }
 }
}


class ATM {
 private BankAccount bankAccount;
 private Scanner scanner;

 public ATM(BankAccount bankAccount) {
     this.bankAccount = bankAccount;
     this.scanner = new Scanner(System.in);
 }

 public void displayMenu() {
     System.out.println("Welcome to the ATM!");
     System.out.println("1. Check Balance");
     System.out.println("2. Deposit");
     System.out.println("3. Withdraw");
     System.out.println("4. Exit");
 }

 public void run() {
     boolean exit = false;

     while (!exit) {
         displayMenu();
         System.out.print("Enter your choice: ");
         int choice = scanner.nextInt();

         switch (choice) {
             case 1:
                 checkBalance();
                 break;
             case 2:
                 deposit();
                 break;
             case 3:
                 withdraw();
                 break;
             case 4:
                 exit = true;
                 break;
             default:
                 System.out.println("Invalid choice. Please try again.");
         }

         System.out.println();
     }
 }

 public void checkBalance() {
     double balance = bankAccount.getBalance();
     System.out.println("Your current balance is: " + balance);
 }

 public void deposit() {
     System.out.print("Enter the amount to deposit: ");
     double amount = getValidAmount();

     bankAccount.deposit(amount);
     System.out.println("Deposit successful. Your new balance is: " + bankAccount.getBalance());
 }

 public void withdraw() {
     System.out.print("Enter the amount to withdraw: ");
     double amount = getValidAmount();

     boolean success = bankAccount.withdraw(amount);
     if (success) {
         System.out.println("Withdrawal successful. Your new balance is: " + bankAccount.getBalance());
     } else {
         System.out.println("Insufficient funds. Unable to withdraw.");
     }
 }

 private double getValidAmount() {
     double amount;
     do {
         amount = scanner.nextDouble();
         if (amount <= 0) {
             System.out.println("Invalid amount. Please enter a positive value.");
         }
     } while (amount <= 0);
     return amount;
 }
}


public class main {
 public static void main(String[] args) {
     BankAccount bankAccount = new BankAccount(1000.0);
     ATM atm = new ATM(bankAccount);
     atm.run();
 }
}


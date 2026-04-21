import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * ServicesModule is the single entry point for Services like Custodial Accounts,
 * Debit Cards, and Currency Conversion.
 */
public class ServicesModule {

    public static void launch(Scanner sc, User appUser, List<CheckingAccount.CheckingUser> checkingUsers) throws IOException {

        DebitCard.setBankingUsers(checkingUsers);

        boolean running = true;
        while (running) {
            System.out.println("\n────────────────────────────────────────");
            System.out.println("BANK  |  Services");
            System.out.println("────────────────────────────────────────");
            System.out.println("  [1] Custodial Accounts");
            System.out.println("  [2] Debit Card");
            System.out.println("  [3] Currency Converter");
            System.out.println("  [0] Back to Dashboard");
            System.out.println("────────────────────────────────────────");
            System.out.print("  Select: ");
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1" -> custodialAccountsMenu(sc, appUser);
                case "2" -> debitCardMenu(sc, appUser, checkingUsers);
                case "3" -> currencyConverterMenu(sc);
                case "0" -> running = false;
                default  -> System.out.println("  Invalid option.");
            }
        }
    }

    private static void custodialAccountsMenu(Scanner sc, User appUser) {
        boolean running = true;
        CustodialReader reader = new CustodialReader();
        CustodialWriter writer = new CustodialWriter();

        while (running) {
            System.out.println("\n────────────────────────────────────────");
            System.out.println("BANK  |  Custodial Accounts");
            System.out.println("────────────────────────────────────────");
            System.out.println("  [1] Create New Custodial Account");
            System.out.println("  [2] View All Accounts");
            System.out.println("  [3] Make a Deposit");
            System.out.println("  [4] Make a Withdrawal");
            System.out.println("  [5] Check Account Info & Update Time");
            System.out.println("  [0] Back to Services");
            System.out.println("────────────────────────────────────────");
            System.out.print("  Select: ");
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1" -> {
                    try {
                        new Custodial(); // Interactive constructor
                    } catch (IllegalArgumentException e) {
                        System.out.println("  Error: " + e.getMessage());
                    }
                }
                case "2" -> {
                    ArrayList<Custodial> accounts = reader.readCSV();
                    if (accounts.isEmpty()) {
                        System.out.println("  No custodial accounts found.");
                    }
                }
                case "3" -> {
                    ArrayList<Custodial> accounts = reader.readCSV();
                    if (accounts.isEmpty()) {
                        System.out.println("  No accounts available.");
                        break;
                    }
                    System.out.print("  Enter account number: ");
                    String accNum = sc.nextLine().trim();
                    Custodial account = accounts.stream()
                            .filter(a -> a.getAccountNumber().equals(accNum))
                            .findFirst().orElse(null);
                    if (account != null) {
                        System.out.print("  Enter deposit amount: $");
                        try {
                            double amount = Double.parseDouble(sc.nextLine().trim());
                            account.custodialDeposit(amount);
                            writer.saveData(accounts);
                        } catch (NumberFormatException e) {
                            System.out.println("  Invalid amount.");
                        }
                    } else {
                        System.out.println("  Account not found.");
                    }
                }
                case "4" -> {
                    ArrayList<Custodial> accounts = reader.readCSV();
                    if (accounts.isEmpty()) {
                        System.out.println("  No accounts available.");
                        break;
                    }
                    System.out.print("  Enter account number: ");
                    String accNum = sc.nextLine().trim();
                    Custodial account = accounts.stream()
                            .filter(a -> a.getAccountNumber().equals(accNum))
                            .findFirst().orElse(null);
                    if (account != null) {
                        System.out.print("  Enter withdrawal amount: $");
                        try {
                            double amount = Double.parseDouble(sc.nextLine().trim());
                            account.custodialWithdraw(amount);
                            writer.saveData(accounts);
                        } catch (NumberFormatException e) {
                            System.out.println("  Invalid amount.");
                        }
                    } else {
                        System.out.println("  Account not found.");
                    }
                }
                case "5" -> {
                    ArrayList<Custodial> accounts = reader.readCSV();
                    if (accounts.isEmpty()) {
                        System.out.println("  No accounts available.");
                        break;
                    }
                    System.out.print("  Enter account number: ");
                    String accNum = sc.nextLine().trim();
                    Custodial account = accounts.stream()
                            .filter(a -> a.getAccountNumber().equals(accNum))
                            .findFirst().orElse(null);
                    if (account != null) {
                        account.displayAccountInfo();
                        account.updateTime();
                        writer.saveData(accounts);
                    } else {
                        System.out.println("  Account not found.");
                    }
                }
                case "0" -> running = false;
                default  -> System.out.println("  Invalid option.");
            }
        }
    }

    private static void debitCardMenu(Scanner sc, User appUser, List<CheckingAccount.CheckingUser> checkingUsers) throws IOException {
        DebitCard.setBankingUsers(checkingUsers);

        boolean running = true;
        while (running) {
            System.out.println("\n────────────────────────────────────────");
            System.out.println("BANK  |  Debit Card Services");
            System.out.println("────────────────────────────────────────");
            System.out.println("  [1] Issue a New Debit Card");
            System.out.println("  [2] Check Balance");
            System.out.println("  [3] Withdraw (Point of Sale)");
            System.out.println("  [4] ATM Withdrawal");
            System.out.println("  [5] Deposit");
            System.out.println("  [6] Foreign Transaction");
            System.out.println("  [7] View Fee Schedule");
            System.out.println("  [8] Replace Card");
            System.out.println("  [9] Close Card");
            System.out.println("  [0] Back to Services");
            System.out.println("────────────────────────────────────────");
            System.out.print("  Select: ");
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1" -> {
                    DebitCard card = DebitCard.issueCard(sc, appUser.customerID);
                    if (card != null) {
                        System.out.println("  Card successfully issued!");
                    }
                }
                case "2" -> {
                    System.out.print("  Enter debit card PIN: ");
                    String pin = sc.nextLine().trim();
                    System.out.println("  [Card balance information would be shown here]");
                }
                case "3" -> System.out.println("  [POS withdrawal functionality]");
                case "4" -> System.out.println("  [ATM withdrawal functionality]");
                case "5" -> System.out.println("  [Deposit functionality]");
                case "6" -> System.out.println("  [Foreign transaction functionality]");
                case "7" -> System.out.println("  [Fee schedule shown here]");
                case "8" -> System.out.println("  [Card replacement functionality]");
                case "9" -> System.out.println("  [Card closure functionality]");
                case "0" -> running = false;
                default  -> System.out.println("  Invalid option.");
            }
        }
    }

    private static void currencyConverterMenu(Scanner sc) {
        System.out.println("\n────────────────────────────────────────");
        System.out.println("BANK  |  Currency Converter");
        System.out.println("────────────────────────────────────────");
        CurrencyConverter.main(new String[]{});
    }
}
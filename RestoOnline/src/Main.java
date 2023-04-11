import java.util.ArrayList;

/**
    Program Pemenasan Makanan Online Sederhana
 */

public class Main {
    /**
     * Inisiasi objek untuk Admin, Customer, Input
     */
    private static Admin admin = new Admin();
    private static Customer customer = new Customer();
    private static Input keyboard = new Input();

    private static ArrayList<Restaurant> listRestoran= new ArrayList<Restaurant>();
    private static int userInput;

    /**
     * Username dan password
     */
    private static String adminUsername = "administrator1";
    private static String adminPassword = "adminpass";
    private static String adminName = "Ayu Krisna";
    private static String customerUsername = "customer1";
    private static String customerPassword = "customerpass";
    private static String customerName = "Winson Tan";

    /**
     * Main methods
     */
    public static void main(String[] args) {
        /**
         * Inisiasi data restoran untuk testing
         */
        listRestoran.add(new Restaurant("RESTO1", "Warung Ikan Bakar", "Jimbaran"));
        listRestoran.add(new Restaurant("RESTO2", "Resto Siliwangi", "Denpasar"));

        boolean runProgram = true;
        while (runProgram) {
            login();
            if (admin.getIsAdmin()) {
                admin.adminAccess(listRestoran);
            } else if (customer.getIsCustomer()) {
                customer.showMenu();
            } else {
                System.out.println("Ada masalah pada program menu 11");
                System.exit(0);
            }
        }

    }

    public static void login() {
        boolean hasLoggedIn = false;
        admin.setIsAdmin(false);
        customer.setIsCustomer(false);

        while (!hasLoggedIn) {
            Messages.loginGreeting();
            Messages.inputInstruction();
            userInput = keyboard.getMenuChoice(0,2);
            switch (userInput) {
                case 1:
                    if(admin.logIn(adminUsername, adminPassword, adminName)) {
                        Messages.loggedInGreeting(admin.nama);
                        hasLoggedIn = true;
                        admin.setIsAdmin(true);
                        customer.setIsCustomer(false);
                    } else {
                        Messages.loginFailed();
                        keyboard.pause();
                    }
                    break;
                case 2:
                    if(customer.logIn(customerUsername, customerPassword, customerName)) {
                        Messages.loggedInGreeting(customer.nama);
                        hasLoggedIn = true;
                        customer.setIsCustomer(true);
                        admin.setIsAdmin(false);
                    } else {
                        Messages.loginFailed();
                        keyboard.pause();
                    }
                    break;
                case 0:
                    exitConfirmation();
            }
        }
    }

    public static void exitConfirmation() {
        Messages.exitConfirmation();
        Messages.inputInstruction();
        userInput = keyboard.getMenuChoice(1,2);
        switch (userInput) {
            case 1:
                Messages.exit();
                System.exit(0);
            case 2:
                break;
        }
    }
}
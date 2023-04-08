/**
    Program Pemenasan Makanan Online Sederhana
 */

public class Main {
    /**
     Inisiasi objek Admin, Customer, dll
     */
    private static Admin admin = new Admin("administrator1", "adminpass", "Ayu Krisna");
    private static Input keyboard = new Input();
    private static int userInput;
    public static void main(String[] args) {
        login();
    }

    public static void login() {
        boolean hasLoggedIn = false;
        while (!hasLoggedIn) {
            Messages.loginGreeting();
            Messages.inputInstruction();
            userInput = keyboard.getMenuChoice(0,2);
            switch (userInput) {
                case 1:
                    if(admin.logIn()) {
                        Messages.loggedInGreeting(admin.nama);
                        hasLoggedIn = true;
                        admin.setIsAdmin(true);
                        //customer.setIsCustomer(false);
                    } else {
                        Messages.loginFailed();
                        keyboard.pause();
                    }
                    break;
                case 2:
                    //customer.logIn();
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
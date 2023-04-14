import java.util.ArrayList;

/**
 * Program Pemesanan Makanan Online Sederhana: Tap and Eat
 *
 * Nama : I Gusti Ayu Krisna Kusuma Dewi
 * NIM  : 2205551072
 *
 * Class: Main
 *      Sebagai kelas utama untuk menjalankan program
 */

public class Main {
    /**
     * Inisiasi objek untuk Admin, Customer, Restaurant List, dan Input
     */
    private static Admin admin = new Admin();
    private static Customer customer = new Customer();
    private static Input keyboard = new Input();

    private static ArrayList<Restaurant> listRestoran= new ArrayList<Restaurant>();
    private static int userInput;

    /**
     * Username dan password untuk admin dan customer
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
//        listRestoran.add(new Restaurant("RESTO1", "Warung Ikan Bakar", "Jimbaran"));
//        Restaurant restoranTest1 = listRestoran.get(0);
//        restoranTest1.getListMakanan().add(new Dish("MAKAN1", "Ikan Gurame Bakar", 40000));
//        restoranTest1.getListMakanan().add(new Dish("MAKAN2", "Ikan Tongkol Bakar", 30000));
//        restoranTest1.getListMakanan().add(new Dish("MAKAN3", "Nasi Putih", 4000));
//        restoranTest1.getListMinuman().add(new Dish("MINUM1", "Es Teh Manis", 5000));
//        restoranTest1.getListMinuman().add(new Dish("MINUM2", "Es Jeruk Manis", 8000));
//        listRestoran.add(new Restaurant("RESTO2", "Resto Siliwangi", "Denpasar"));
//        Restaurant restoranTest2 = listRestoran.get(1);
//        restoranTest2.getListMakanan().add(new Dish("MAKAN1", "Nasi Goreng", 20000));
//        restoranTest2.getListMakanan().add(new Dish("MAKAN2", "Capcay", 12000));
//        restoranTest2.getListMinuman().add(new Dish("MINUM1", "Es Gula", 3000));

        /**
         * Kode utama untuk melakukan run pada program1
         */
        boolean runProgram = true;
        while (runProgram) {
            CLS.clearConsole();
            login(); //melakukan login dan mendapatkan isAdmin/isCustomer
            if (admin.getIsAdmin()) { //jika admin, maka buka menu untuk admin
                CLS.clearConsole();
                admin.adminAccess(listRestoran);
            } else if (customer.getIsCustomer()) { //jika customer, maka buka menu untuk customer
                CLS.clearConsole();
                customer.customerAccess(listRestoran);
            } else { //seharusnya bila tidak ada masalah, else tidak akan dicapai program
                System.out.println("Ada masalah pada program menu");
                System.exit(0);
            }
        }

    }

    /**
     * Method login() : untuk melakukan sesi login
     */
    public static void login() {
        boolean hasLoggedIn = false;
        admin.setIsAdmin(false);
        customer.setIsCustomer(false);

        while (!hasLoggedIn) {
            CLS.clearConsole();
            Messages.loginGreeting(); // pilih: 1. login sbg admin, 2. login sbg customer, 0. keluar
            Messages.inputInstruction();
            userInput = keyboard.getMenuChoice(0,2);
            CLS.clearConsole();
            switch (userInput) {
                case 1:
                    if(admin.logIn(adminUsername, adminPassword, adminName)) { //jika login sbg admin benar, set isAdmin = true
                        Messages.loggedInGreeting(admin.nama);
                        keyboard.nextLine();
                        hasLoggedIn = true;
                        admin.setIsAdmin(true);
                        customer.setIsCustomer(false);
                    } else { //jika salah, kembali ke menu sebelumnya
                        Messages.loginFailed();
                        keyboard.nextLine();
                    }
                    break;
                case 2:
                    if(customer.logIn(customerUsername, customerPassword, customerName)) { //jika login sbg customer benar, set isCustomer = true
                        Messages.loggedInGreeting(customer.nama);
                        keyboard.nextLine();
                        hasLoggedIn = true;
                        customer.setIsCustomer(true);
                        admin.setIsAdmin(false);
                    } else { //jika salah, kembali ke menu sebelumnya
                        Messages.loginFailed();
                        keyboard.nextLine();
                    }
                    break;
                case 0: //Jika user ingin keluar dari program
                    CLS.clearConsole();
                    exitConfirmation();
            }
        }
    }

    /**
     * Method exitConfirmation() : konfirmasi keluar/tidak dari program
     */
    public static void exitConfirmation() {
        Messages.exitConfirmation(); // 1. keluar dari program, 2. kembali
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
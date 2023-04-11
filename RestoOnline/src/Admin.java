import java.util.ArrayList;
import java.util.Scanner;

public class Admin extends User {
    // Variables
    private boolean isAdmin = false;
    Scanner keyboard = new Scanner(System.in);
    Input keyboard2 = new Input();

    // Constructor
    public Admin() {
        super();
    }
    public Admin(String theUsername, String thePassword, String theNama) {
        super(theUsername, thePassword, theNama);
    }

    // Methods
    public boolean logIn(String username, String password, String nama) {
        String inputUsername;
        String inputPassword;
        boolean hasLoggedIn = false;

        Messages.loginInstruction("ADMINISTRATOR");
        System.out.print("  Username: ");
        inputUsername = keyboard.next();
        System.out.print("  Password: ");
        inputPassword = keyboard.next();

        if (username.equals(inputUsername) && password.equals(inputPassword)) {
            this.username = inputUsername;
            this.password = inputPassword;
            this.nama = nama;
            hasLoggedIn = true;
        } else {
            hasLoggedIn = false;
        }
        return hasLoggedIn;
     }

    public void adminAccess(ArrayList<Restaurant> listRestaurant) {
        boolean runAdminAccess = true;
        while(runAdminAccess) {
            AdminMessages.showMenu(); //1. lihat, 2. tambah, 3. hapus, 0. kembali ke menu
            int userInput = keyboard2.getMenuChoice(0,3);

            if (userInput == 1) { // Lihat Restoran
                AdminMessages.lihatRestoranHeader();
                int index = 0;
                for (Restaurant element : listRestaurant) {
                    Restaurant restaurantObject = listRestaurant.get(index);
                    restaurantObject.toString();
                    index++;
                }
                AdminMessages.lihatRestoranFooter();
                keyboard.nextLine();
                String userInputResto = keyboard.nextLine();
                // masih perlu ditambahin lagi
            } else if (userInput == 2) {

            } else if (userInput == 3) {

            } else if (userInput == 0) {
                break;
            } else {
                System.out.println("Ada masalah pada program");
                System.exit(0);
            }
        }
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean admin) {
        isAdmin = admin;
    }

    // Nested Class for Messages Guide
    static class AdminMessages {
        public static void showMenu() {
            System.out.println(" + =============================================================== + ");
            System.out.println("||                         T A P  AND  E A T                       ||");
            System.out.println("||                            Admin Menu                           ||");
            System.out.println(" + --------------------------------------------------------------- + ");
            System.out.println("||                 Pilih opsi dengan memasukkan angka              ||");
            System.out.println("||                                                                 ||");
            System.out.println("||   [1] Lihat Restoran yang Ada                                   ||");
            System.out.println("||   [2] Tambahkan Restoran baru                                   ||");
            System.out.println("||   [3] Hapus Restoran yang Ada                                   ||");
            System.out.println("||   [0] Kembali ke menu log in                                    ||");
            System.out.println(" + =============================================================== + ");
        }

        public static void lihatRestoranHeader() {
            System.out.println(" + =============================================================== + ");
            System.out.println("||                          LIHAT RESTORAN                         ||");
            System.out.println("||                            Admin Menu                           ||");
            System.out.println(" + --------------------------------------------------------------- + ");
            System.out.println("|| ID RESTO |         NAMA RESTORAN         |    ALAMAT RESTORAN   ||");
            System.out.println(" + --------------------------------------------------------------- + ");
        }
        public static void lihatRestoranFooter() {
            System.out.println(" + --------------------------------------------------------------- + ");
            System.out.println("||            Pilih restoran dengan memasukkan ID restoran         ||");
            System.out.println("||                 Klik [0] untuk kembali ke menu admin            ||");
            System.out.println(" + =============================================================== + ");
        }
    }
}

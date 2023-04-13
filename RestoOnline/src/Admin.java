import java.util.ArrayList;
import java.util.Scanner;

public class Admin extends User {
    // Variables
    private boolean isAdmin = false; //apakah Admin sudah log in atau belum
    private Input keyboard = new Input();

    // Constructor
    public Admin() {
        super();
    }
    public Admin(String theUsername, String thePassword, String theNama) {
        super(theUsername, thePassword, theNama);
    }

    // Methods

    /**
     * Method logIn() : untuk melakukan log in pada Admin
     * @param username
     * @param password
     * @param nama
     * @return true kalau username dan password benar, false kalau salah
     */
    public boolean logIn(String username, String password, String nama) {
        String inputUsername;
        String inputPassword;
        boolean hasLoggedIn = false;

        Messages.loginInstruction("ADMINISTRATOR");
        System.out.print("  Username: ");
        inputUsername = keyboard.nextLine();
        System.out.print("  Password: ");
        inputPassword = keyboard.nextLine();

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

    /**
     * Method adminAccess() : berisi segala akses yang diberikan kepada Admin
     * @param listRestaurant
     */
    public void adminAccess(ArrayList<Restaurant> listRestaurant) {
        boolean runAdminAccess = true;
        while(runAdminAccess) {
            AdminMessages.showMenu(); //1. lihat, 2. tambah, 3. hapus, 0. kembali ke menu
            Messages.inputInstruction();
            int userInput = keyboard.getMenuChoice(0,3);

            if (userInput == 1) { // Lihat Restoran
                boolean runLihatRestoran = true;
                boolean restaurantFound = false;
                while (runLihatRestoran) {
                    AdminMessages.lihatRestoranHeader();
                    int index = 0;
                    for (Restaurant element : listRestaurant) {
                        Restaurant restaurantObject = listRestaurant.get(index);
                        restaurantObject.toString();
                        index++;
                    }
                    AdminMessages.lihatRestoranFooter(); // pilih resto dengan ID atau kembali dengan 0
                    Messages.inputInstruction();
                    String userInputResto = keyboard.nextLine();
                    if (userInputResto.equals("0")) break;
                    else {
                        index = 0;
                        for (Restaurant element : listRestaurant) {
                            Restaurant restaurantObject = listRestaurant.get(index);
                            if (restaurantObject.getIdResto().equals(userInputResto.toUpperCase())) {
                                restaurantFound = true;
                                restaurantObject.seeRestaurantMenu();
                                break;
                            } else index++;
                        }
                        if (!restaurantFound) {
                            System.out.println("    Maaf, ID Restoran yang Anda masukkan salah, nih.");
                            System.out.println("    Tekan ENTER untuk kembali.");
                            keyboard.nextLine();
                        }
                    }
                }
            } else if (userInput == 2) { //Tambah Restoran
                // variabel untuk memeriksa kevalidan
                boolean runAddRestaurant = true;
                boolean idNotValid = true;
                int userInputAddRestaurant;

                // Variabel untuk restoran
                String tempIdRestoran = null;
                String tempNamaRestoran = null;
                String tempAlamatRestoran = null;

                while (runAddRestaurant) {
                    AdminMessages.tambahRestoranHeader();
                    while (idNotValid) { //memeriksa apakah ID tidak valid (duplikat atau tidak memenuhi persyaratan)
                        System.out.print("    ID Restoran: ");
                        tempIdRestoran = keyboard.validateID();
                        idNotValid = keyboard.isIDRestoSame(listRestaurant, tempIdRestoran);
                        if (idNotValid) {
                            System.out.printf("    ID %s sudah digunakan.\n", tempIdRestoran);
                            System.out.println("    Mohon masukkan ID yang baru.");
                        }
                    }

                    System.out.print("    Nama Restoran: ");
                    tempNamaRestoran = keyboard.validateString("Nama restoran", 30);
                    System.out.print("    Alamat Restoran: ");
                    tempAlamatRestoran = keyboard.validateString("Alamat restoran", 21);
                    listRestaurant.add(new Restaurant(tempIdRestoran, tempNamaRestoran, tempAlamatRestoran));

                    Restaurant restaurant = listRestaurant.get(listRestaurant.size()-1);
                    restaurant.addMenu();
                    AdminMessages.tambahRestoranLagi();
                    Messages.inputInstruction();
                    userInputAddRestaurant = keyboard.getMenuChoice(1,2);
                    if (userInputAddRestaurant == 2) break;
                }

            } else if (userInput == 3) { //Hapus Restoran

            } else if (userInput == 0) { //Kembali ke menu login
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
            System.out.println("||          Lihat menu restoran dengan memasukkan ID restoran      ||");
            System.out.println("||                 Klik [0] untuk kembali ke menu admin            ||");
            System.out.println(" + =============================================================== + ");
        }

        public static void tambahRestoranHeader() {
            System.out.println(" + =============================================================== + ");
            System.out.println("||                          TAMBAH RESTORAN                        ||");
            System.out.println("||                            Admin Menu                           ||");
            System.out.println(" + --------------------------------------------------------------- + ");
            System.out.println("||  Mohon masukkan restoran baru dengan informasi sebagai berikut. ||");
            System.out.println("||        < ID Restoran, Nama Restoran, Alamat Restoran >          ||");
            System.out.println(" + =============================================================== + ");
        }

        public static void tambahRestoranLagi() {
            System.out.println(" + --------------------------------------------------------------- + ");
            System.out.println("||                   Restoran berhasil ditambahkan!                ||");
            System.out.println(" + --------------------------------------------------------------- + ");
            System.out.println("||                  Lanjutkan menambahkan restoran?                ||");
            System.out.println("||                                                                 ||");
            System.out.println("||                 [1] Ya                   [2] Tidak              ||");
            System.out.println(" + =============================================================== + ");
        }
    }
}

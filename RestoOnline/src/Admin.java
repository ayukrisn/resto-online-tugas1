/**
 * Class: Admin
 *
 * Berisi keperluan dan akses admin
 */

import java.util.ArrayList;

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
     * Method logIn() : untuk melakukan log in pada Admin, dicall pada Main class
     * @param username: menerima username yang benar
     * @param password: menerima password yang benar
     * @param nama    : menerima nama user
     * @return true kalau username dan password benar, false kalau salah
     */
    public boolean logIn(String username, String password, String nama) {
        String inputUsername;
        String inputPassword;
        boolean hasLoggedIn = false;

        Messages.loginInstruction("ADMINISTRATOR"); //instruksi login
        System.out.print("  Username: ");
        inputUsername = keyboard.nextLine();
        System.out.print("  Password: ");
        inputPassword = keyboard.nextLine();

        //memeriksa apakah username dan password benar
        if (username.equals(inputUsername) && password.equals(inputPassword)) {
            this.username = inputUsername;
            this.password = inputPassword;
            this.nama = nama;
            hasLoggedIn = true;
        } else {
            hasLoggedIn = false;
        }
        return hasLoggedIn; //status apakah login sukses atau tidak
     }

    /**
     * Method adminAccess() : berisi segala akses yang diberikan kepada Admin
     * @param listRestaurant: list restoran dari Restaurant class
     */
    public void adminAccess(ArrayList<Restaurant> listRestaurant) {
        boolean runAdminAccess = true;
        while(runAdminAccess) {
            CLS.clearConsole();
            showMenu(); //1. lihat resto, 2. tambah resto, 3. hapus resto, 0. kembali ke menu
            Messages.inputInstruction();
            int userInput = keyboard.getMenuChoice(0,3);

            if (userInput == 1) { // Lihat Restoran
                CLS.clearConsole();
                boolean runLihatRestoran = true;
                boolean restaurantFound = false;
                while (runLihatRestoran) {
                    CLS.clearConsole();
                    showLihatRestoranHeader();
                    int index = 0;
                    for (Restaurant element : listRestaurant) { //lihat semua restoran yang ada di list
                        Restaurant restaurantObject = listRestaurant.get(index);
                        restaurantObject.toString();
                        index++;
                    }
                    showLihatRestoranFooter(); // pilih resto dengan ID atau kembali dengan 0
                    Messages.inputInstruction();
                    String userInputIDResto = keyboard.nextLine(); //menerima ID yang diinput user
                    if (userInputIDResto.equals("0")) break;
                    else { //memeriksa apakah ID ada atau tidak dgn loop list yg ada
                        index = 0;
                        for (Restaurant element : listRestaurant) {
                            Restaurant restaurantObject = listRestaurant.get(index);
                            //jika ID ada, maka restoran ditemukan dan dapat lanjut ke melihat restoran
                            if (restaurantObject.getIdResto().equals(userInputIDResto.toUpperCase())) {
                                restaurantFound = true;
                                CLS.clearConsole();
                                restaurantObject.seeRestaurantMenu(); //memanggil method untuk melihat menu restoran yg dipilih
                                break;
                            } else index++;
                        }
                        if (!restaurantFound) { //jika ternyata ID tidak ditemukan
                            System.out.println("    Maaf, ID Restoran yang Anda masukkan salah, nih.");
                            System.out.println("    Tekan ENTER untuk kembali.");
                            keyboard.nextLine();
                        }
                    }
                }
            } else if (userInput == 2) { //Tambah Restoran
                CLS.clearConsole();
                // variabel untuk memeriksa kevalidan
                boolean runAddRestaurant = true;
                boolean idNotValid = true;
                int userInputAddRestaurant;

                // Variabel temporary untuk restoran sebelum dimasukkan ke objek
                String tempIdRestoran = null;
                String tempNamaRestoran = null;
                String tempAlamatRestoran = null;

                while (runAddRestaurant) {
                    CLS.clearConsole();
                    showTambahRestoranHeader();
                    idNotValid = true;
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
                    // Menambahkan elemen restoran baru ke list
                    listRestaurant.add(new Restaurant(tempIdRestoran, tempNamaRestoran, tempAlamatRestoran));

                    Restaurant restaurant = listRestaurant.get(listRestaurant.size()-1);
                    CLS.clearConsole();
                    boolean addMenuDone= restaurant.addMenu(); //masuk ke method untuk menambahkan menu pada restoran
                    if (!addMenuDone) { //jika ternyata user tidak menambahkan menu apapun, hapus restoran
                        listRestaurant.remove(listRestaurant.size()-1);
                        break;
                    }
                    showTambahRestoranLagi(); //1. tambah lagi, 2. berhenti
                    Messages.inputInstruction();
                    userInputAddRestaurant = keyboard.getMenuChoice(1,2);
                    if (userInputAddRestaurant == 2) break;
                }

            } else if (userInput == 3) { //Hapus Restoran
                CLS.clearConsole();
                boolean runHapusRestaurant = true;
                boolean restaurantFound = false;

                while (runHapusRestaurant) {
                    CLS.clearConsole();
                    showHapusRestoranHeader();
                    int index = 0;
                    for (Restaurant element : listRestaurant) { //memunculkan seluruh restoran yang ada di list
                        Restaurant restaurantObject = listRestaurant.get(index);
                        restaurantObject.toString();
                        index++;
                    }
                    showHapusRestoranFooter(); //memasukkan ID resto yang ingin dihapus
                    Messages.inputInstruction();
                    String userInputResto = keyboard.nextLine();
                    if (userInputResto.equals("0")) break;
                    else {
                        index = 0;
                        for (Restaurant element : listRestaurant) { //mencari ID resto yang diinput user
                            Restaurant restaurantObject = listRestaurant.get(index);
                            if (restaurantObject.getIdResto().equals(userInputResto.toUpperCase())) {
                                // kalau ketemu, hapusresto
                                restaurantFound = true;
                                listRestaurant.remove(index);
                                showHapusRestoranDone();
                                runHapusRestaurant = false;
                                keyboard.nextLine();
                                break;
                            } else index++;
                        }
                        if (!restaurantFound) { //kalau tidak, kembali
                            System.out.println("    Maaf, ID Restoran yang Anda masukkan salah, nih.");
                            System.out.println("    Tekan ENTER untuk kembali.");
                            keyboard.nextLine();
                        }
                    }

                }

            } else if (userInput == 0) { //Kembali ke menu login
                CLS.clearConsole();
                break;
            } else { //seharusnya program tidak menyentuh bagian ini
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


    /**
     * Methods to Show User's Instructions
     */
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

        public static void showLihatRestoranHeader() {
            System.out.println(" + =============================================================== + ");
            System.out.println("||                          LIHAT RESTORAN                         ||");
            System.out.println("||                            Admin Menu                           ||");
            System.out.println(" + --------------------------------------------------------------- + ");
            System.out.println("|| ID RESTO |         NAMA RESTORAN         |    ALAMAT RESTORAN   ||");
            System.out.println(" + --------------------------------------------------------------- + ");
        }
        public static void showLihatRestoranFooter() {
            System.out.println(" + --------------------------------------------------------------- + ");
            System.out.println("||          Lihat menu restoran dengan memasukkan ID restoran      ||");
            System.out.println("||                 Klik [0] untuk kembali ke menu admin            ||");
            System.out.println(" + =============================================================== + ");
        }

        public static void showTambahRestoranHeader() {
            System.out.println(" + =============================================================== + ");
            System.out.println("||                          TAMBAH RESTORAN                        ||");
            System.out.println("||                            Admin Menu                           ||");
            System.out.println(" + --------------------------------------------------------------- + ");
            System.out.println("||  Mohon masukkan restoran baru dengan informasi sebagai berikut. ||");
            System.out.println("||        < ID Restoran, Nama Restoran, Alamat Restoran >          ||");
            System.out.println(" + =============================================================== + ");
        }

        public static void showTambahRestoranLagi() {
            System.out.println(" + --------------------------------------------------------------- + ");
            System.out.println("||                   Restoran berhasil ditambahkan!                ||");
            System.out.println(" + --------------------------------------------------------------- + ");
            System.out.println("||                  Lanjutkan menambahkan restoran?                ||");
            System.out.println("||                                                                 ||");
            System.out.println("||                 [1] Ya                   [2] Tidak              ||");
            System.out.println(" + =============================================================== + ");
        }

        public static void showHapusRestoranHeader() {
            System.out.println(" + =============================================================== + ");
            System.out.println("||                          HAPUS RESTORAN                         ||");
            System.out.println("||                            Admin Menu                           ||");
            System.out.println(" + --------------------------------------------------------------- + ");
            System.out.println("|| ID RESTO |         NAMA RESTORAN         |    ALAMAT RESTORAN   ||");
            System.out.println(" + --------------------------------------------------------------- + ");
        }

        public static void showHapusRestoranFooter() {
            System.out.println(" + --------------------------------------------------------------- + ");
            System.out.println("||             Hapus restoran dengan memasukkan ID restoran        ||");
            System.out.println("||                 Klik [0] untuk kembali ke menu admin            ||");
            System.out.println(" + =============================================================== + ");
        }

        public static void showHapusRestoranDone() {
            System.out.println(" + =============================================================== + ");
            System.out.println("||                      Restoran berhasil dihapus!                 ||");
            System.out.println("||                Tekan ENTER untuk kembali ke menu admin          ||");
            System.out.println(" + =============================================================== + ");
        }

}

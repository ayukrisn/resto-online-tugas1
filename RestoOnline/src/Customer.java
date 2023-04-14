/**
 * Class: Customer
 *
 * Berisi keperluan dan akses customer
 */

import java.util.ArrayList;

public class Customer extends User {
    // Variables
    private boolean isCustomer = false;

    private Input keyboard = new Input();
    private ArrayList<Orders> listOrders = new ArrayList<Orders>();

    public Customer() {
        super();
    }

    public Customer(String theUsername, String thePassword, String theNama) {
        super(theUsername, thePassword, theNama);
    }

    // Methods

    /**
     * Method logIn() : untuk melakukan login customer
     * @param username: menerima username yang benar
     * @param password: menerima password yang benar
     * @param nama    : menerima nama user
     * @return true kalau username dan password benar, false kalau salah
     */
    public boolean logIn(String username, String password, String nama) {
        String inputUsername;
        String inputPassword;
        boolean hasLoggedIn = false;

        Messages.loginInstruction("CUSTOMER"); // instruksi login
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
     * Method customerAccess: berisi segala akses yang diberikan kepada Customer
     * @param listRestaurant list restoran dari Restaurant class
     */
    public void customerAccess(ArrayList<Restaurant> listRestaurant) {
        boolean runCustomerAccess = true;
        while(runCustomerAccess) {
            CLS.clearConsole();
            CustomerMessages.showMenu(); //1. lihat restoran, 2. buat pesanan, 3. lihat pesanan, 0. kembali ke menu
            Messages.inputInstruction();
            int userInput = keyboard.getMenuChoice(0,3);

            if (userInput == 1) { // Lihat Restoran
                CLS.clearConsole();
                boolean runLihatRestoran = true;
                boolean restaurantFound = false;
                while (runLihatRestoran) {
                    CLS.clearConsole();
                    CustomerMessages.lihatRestoranHeader();
                    int index = 0;
                    for (Restaurant element : listRestaurant) { //lihat semua restoran yang ada di list
                        Restaurant restaurantObject = listRestaurant.get(index);
                        restaurantObject.toString();
                        index++;
                    }
                    CustomerMessages.lihatRestoranFooter(); // pilih resto dengan ID atau kembali dengan 0
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
            } else if (userInput == 2) { //Buat pesanan
                boolean runBuatPesanan = true;
                boolean restaurantFound = false;

                //Variabel yang menyimpan pilihan user
                String userInputResto = null;

                while (runBuatPesanan) {
                    CLS.clearConsole();
                    restaurantFound = false;
                    userInputResto = null;
                    //Pilih restoran
                    CustomerMessages.buatPesananHeader();
                    int index = 0;
                    for (Restaurant element : listRestaurant) { //Nunjukkin restoran yang ada di list
                        Restaurant restaurantObject = listRestaurant.get(index);
                        restaurantObject.toString();
                        index++;
                    }
                    CustomerMessages.buatPesananFooter(); //memilih resto yang ingin dipesan
                    Messages.inputInstruction();
                    userInputResto = keyboard.nextLine();
                    // Periksa apakah ID yang diinput valid atau tidak
                    // Kalau valid, lanjut pilih menu yang ingin dipesan
                    if (userInputResto.equals("0")) break;
                    else {
                        index = 0;
                        for (Restaurant element : listRestaurant) { //memeriksa resto berdasarkan ID
                            Restaurant restaurantObject = listRestaurant.get(index);
                            if (restaurantObject.getIdResto().equals(userInputResto.toUpperCase())) {
                                // jika resto ditemukan, lanjutan pemesanan
                                restaurantFound = true;
                                // Menanyakan jarak
                                System.out.print("    Jarak Anda dengan restoran ini (dalam km): ");
                                double tempJarak = keyboard.getDouble();
                                // Akses restoran untuk mengakses menu yang ada dan memasukkannya ke order
                                String idOrder = "ORDER" + (listOrders.size()+1);
                                //inisiasi order baru + naruh id resto, nama, dan jarak ke resto
                                listOrders.add(new Orders(idOrder, userInputResto.toUpperCase(), restaurantObject.getNama(), tempJarak));
                                Orders orderObject = listOrders.get(listOrders.size()-1);
                                CLS.clearConsole();
                                boolean hasOrdered = restaurantObject.orderRestaurantMenu(orderObject); //memanggil method untuk memesan menu pada resto yang dipilih
                                if (!hasOrdered) { //jika ternyata user tidak menambahkan order makanan apapun, hapus objek order
                                    listOrders.remove(listOrders.size()-1);
                                    keyboard.nextLine();
                                    break;
                                } keyboard.nextLine();
                                break;
                            } else {
                                index++;
                            }
                        }
                        if (!restaurantFound) {
                            System.out.println("    Maaf, ID Restoran yang Anda masukkan salah, nih.");
                            System.out.println("    Tekan ENTER untuk kembali.");
                            keyboard.nextLine();
                        }
                    }
                }

            } else if (userInput == 3) { //Lihat pesanan
                boolean runLihatPesanan = true;
                int index = 0;

                CLS.clearConsole();
                Orders.showOrderLogsHeader();
                while(runLihatPesanan) { //memperlihatkan pesanan yang ada di list order
                    for (Orders element : listOrders) {
                        Orders orderObject = listOrders.get(index);
                        orderObject.showOrdersandPayment();
                        index++;
                    }
                    Orders.showOrdersFooter();
                    keyboard.nextLine();
                    break;
                }

            } else if (userInput == 0) { //Kembali ke menu login
                break;
            } else {
                System.out.println("Ada masalah pada program");
                System.exit(0);
            }
        }
    }

    public boolean getIsCustomer() {
        return isCustomer;
    }

    public void setIsCustomer(boolean customer) {
        isCustomer = customer;
    }

    /**
     * Nested Class for Messages Guide
     */
    static class CustomerMessages {
        public static void showMenu() {
            System.out.println(" + =============================================================== + ");
            System.out.println("||                         T A P  AND  E A T                       ||");
            System.out.println("||                           Customer Menu                         ||");
            System.out.println(" + --------------------------------------------------------------- + ");
            System.out.println("||                 Pilih opsi dengan memasukkan angka              ||");
            System.out.println("||                                                                 ||");
            System.out.println("||   [1] Lihat Restoran yang Ada                                   ||");
            System.out.println("||   [2] Buat Pesanan Baru                                         ||");
            System.out.println("||   [3] Lihat Riwayat Pemesanan                                   ||");
            System.out.println("||   [0] Kembali ke menu log in                                    ||");
            System.out.println(" + =============================================================== + ");
        }

        public static void lihatRestoranHeader() {
            System.out.println(" + =============================================================== + ");
            System.out.println("||                          LIHAT RESTORAN                         ||");
            System.out.println("||                          Customer Menu                          ||");
            System.out.println(" + --------------------------------------------------------------- + ");
            System.out.println("|| ID RESTO |         NAMA RESTORAN         |    ALAMAT RESTORAN   ||");
            System.out.println(" + --------------------------------------------------------------- + ");
        }
        public static void lihatRestoranFooter() {
            System.out.println(" + --------------------------------------------------------------- + ");
            System.out.println("||          Lihat menu restoran dengan memasukkan ID restoran      ||");
            System.out.println("||               Klik [0] untuk kembali ke menu customer           ||");
            System.out.println(" + =============================================================== + ");
        }
        public static void buatPesananHeader() {
            System.out.println(" + =============================================================== + ");
            System.out.println("||                          BUAT PESANAN                           ||");
            System.out.println("||                          Customer Menu                          ||");
            System.out.println(" + --------------------------------------------------------------- + ");
            System.out.println("|| ID RESTO |         NAMA RESTORAN         |    ALAMAT RESTORAN   ||");
            System.out.println(" + --------------------------------------------------------------- + ");
        }
        public static void buatPesananFooter() {
            System.out.println(" + --------------------------------------------------------------- + ");
            System.out.println("||          Lihat menu restoran dengan memasukkan ID restoran      ||");
            System.out.println("||               Klik [0] untuk kembali ke menu customer           ||");
            System.out.println(" + =============================================================== + ");
        }
    }
}

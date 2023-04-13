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
    public boolean logIn(String username, String password, String nama) {
        String inputUsername;
        String inputPassword;
        boolean hasLoggedIn = false;

        Messages.loginInstruction("CUSTOMER");
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

    public void customerAccess(ArrayList<Restaurant> listRestaurant) {
        boolean runCustomerAccess = true;
        while(runCustomerAccess) {
            CustomerMessages.showMenu(); //1. lihat restoran, 2. buat pesanan, 3. lihat pesanan, 0. kembali ke menu
            Messages.inputInstruction();
            int userInput = keyboard.getMenuChoice(0,3);

            if (userInput == 1) { // Lihat Restoran
                boolean runLihatRestoran = true;
                boolean restaurantFound = false;
                while (runLihatRestoran) {
                    CustomerMessages.lihatRestoranHeader();
                    int index = 0;
                    for (Restaurant element : listRestaurant) {
                        Restaurant restaurantObject = listRestaurant.get(index);
                        restaurantObject.toString();
                        index++;
                    }
                    CustomerMessages.lihatRestoranFooter(); // pilih resto dengan ID atau kembali dengan 0
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
            } else if (userInput == 2) { //Buat pesanan
                boolean runBuatPesanan = true;
                boolean restaurantFound = false;

                //Variabel yang menyimpan pilihan user
                String userInputResto = null;

                while (runBuatPesanan) {
                    restaurantFound = false;
                    userInputResto = null;
                    //Pilih restoran
                    CustomerMessages.buatPesananHeader();
                    //Nunjukkin restoran yang ada
                    int index = 0;
                    for (Restaurant element : listRestaurant) {
                        Restaurant restaurantObject = listRestaurant.get(index);
                        restaurantObject.toString();
                        index++;
                    }
                    CustomerMessages.buatPesananFooter();
                    Messages.inputInstruction();
                    userInputResto = keyboard.nextLine();
                    // Periksa apakah ID yang diinput valid atau tidak
                    // Kalau valid, lanjut pilih menu yang ingin dipesan
                    if (userInputResto.equals("0")) break;
                    else {
                        index = 0;
                        for (Restaurant element : listRestaurant) {
                            Restaurant restaurantObject = listRestaurant.get(index);
                            if (restaurantObject.getIdResto().equals(userInputResto.toUpperCase())) {
                                restaurantFound = true;
                                // Menanyakan jarak
                                System.out.print("    Jarak Anda dengan restoran ini (dalam km): ");
                                double tempJarak = keyboard.getDouble();
                                // Akses restoran untuk mengakses menu yang ada dan memasukkannya ke order
                                String idOrder = "ORDER" + (listOrders.size());
                                //inisiasi order baru + naruh id resto, nama, dan jarak ke resto
                                listOrders.add(new Orders(idOrder, userInputResto.toUpperCase(), restaurantObject.getNama(), tempJarak));
                                Orders orderObject = listOrders.get(listOrders.size()-1);
                                restaurantObject.orderRestaurantMenu(orderObject);
                                keyboard.nextLine();
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

                while(runLihatPesanan) {
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

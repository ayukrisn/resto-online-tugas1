/**
 * Class: Restaurant
 *
 * Berisi variabel dan method yang berkaitan dengan restoran
 */

import java.util.ArrayList;

public class Restaurant {
    // Variables
    private Input keyboard = new Input();
    private String idResto;
    private String nama;
    private String alamat;
    private ArrayList<Dish> listMakanan = new ArrayList<Dish>();
    private ArrayList<Dish> listMinuman = new ArrayList<Dish>();



    // Constractor
    public Restaurant() {

    }
    public Restaurant(String idResto, String nama, String alamat) {
        this.idResto = idResto;
        this.nama = nama;
        this.alamat = alamat;
    }

    // Methods
    public void addMakanan(String idMakanan, String nama, double harga) {
        listMakanan.add(new Dish(idMakanan, nama, harga));
    }

    public void addMinuman(String idMinuman, String nama, double harga) {
        listMinuman.add(new Dish(idMinuman, nama, harga));
    }

    /**
     * Method: addMenu()
     *
     * Digunakan untuk menambahkan menu pada restoran yang ditambahkan oleh admin
     * Dipanggil pada class Admin
     * @return addMenuDone: menu sudah dimasukkan atau belum
     */
    public boolean addMenu() {
        int userInput;

        // Validasi
        boolean runAddMenu = true;
        boolean idNotValid = true;
        boolean addMenuDone = false;

        // Variabel untuk makanan/minuman
        String tempIdDish = null;
        String tempNamaDish = null;
        double tempHargaDish = 0;

        while (runAddMenu) {
            CLS.clearConsole();
            RestaurantMessages.addMenu(); //1. tambah menu makanan 2. tambah menu minuman 0. selesai
            Messages.inputInstruction();
            userInput = keyboard.getMenuChoice(0,2);
            if (userInput == 0) { //memeriksa apakah user sudah menambahkan menu atau belum
                if(!addMenuDone) { //jika ternyata belum
                    RestaurantMessages.konfirmasiPembatalanTambahResto(); //1. batalkan, 2. lanjutkan
                    Messages.inputInstruction();
                    int userInput2 = keyboard.getMenuChoice(1,2);
                    if(userInput2 == 1) { //jika batal tambah resto, set addMenuDone ke false
                        CLS.clearConsole();
                        runAddMenu = false;
                        addMenuDone = false;
                    } else if (userInput2 == 2) { //jika lanjutkan, loop
                        CLS.clearConsole();
                    }
                } else { //kalau ternyata sudah menambahkan menu, lanjutkan
                    CLS.clearConsole();
                    runAddMenu = false;
                }
            } else if (userInput == 1) { //menambahkan menu makanan
                CLS.clearConsole();
                RestaurantMessages.addMakananOrMinuman("Makanan");
                idNotValid = true;
                while (idNotValid) { //memeriksa apakah ID tidak valid (duplikat atau tidak memenuhi persyaratan)
                    System.out.print("    ID Makanan: ");
                    tempIdDish = keyboard.validateID();
                    idNotValid = keyboard.isIDDishSame(listMakanan, tempIdDish);
                    if (idNotValid) {
                        System.out.printf("    ID %s sudah digunakan.\n", tempIdDish);
                        System.out.println("    Mohon masukkan ID yang baru.");
                    }
                }
                System.out.print("    Nama Makanan: ");
                tempNamaDish = keyboard.validateString("Nama makanan", 30);
                System.out.print("    Harga Makanan: Rp");
                tempHargaDish = keyboard.getDouble();
                keyboard.nextLine();

                addMakanan(tempIdDish, tempNamaDish, tempHargaDish); //menambahkan list makanan
                RestaurantMessages.addMenuBerhasil(); //memberi pesan penambahan menu berhasil
                addMenuDone = true;
                keyboard.nextLine();
            } else if (userInput == 2) { //menambahkan menu minuman
                CLS.clearConsole();
                RestaurantMessages.addMakananOrMinuman("Minuman");
                idNotValid = true;
                while (idNotValid) { //memeriksa apakah ID tidak valid (duplikat atau tidak memenuhi persyaratan)
                    System.out.print("    ID Minuman: ");
                    tempIdDish = keyboard.validateID();
                    idNotValid = keyboard.isIDDishSame(listMinuman, tempIdDish);
                    if (idNotValid) {
                        System.out.printf("    ID %s sudah digunakan.\n", tempIdDish);
                        System.out.println("    Mohon masukkan ID yang baru.");
                    }
                }
                System.out.print("    Nama Minuman: ");
                tempNamaDish = keyboard.validateString("Nama minuman", 30);
                System.out.print("    Harga Minuman: Rp");
                tempHargaDish = keyboard.getDouble();
                keyboard.nextLine();

                addMinuman(tempIdDish, tempNamaDish, tempHargaDish); //menambahkan list minuman
                RestaurantMessages.addMenuBerhasil(); //memberi pesan penambahan menu berhasil
                addMenuDone = true;
                keyboard.nextLine();
            }
            tempIdDish = null;
            tempNamaDish = null;
            tempHargaDish = 0;
        }
        return addMenuDone;
    }

    /**
     * Method: seeRestaurantMenu()
     *
     * Memunculkan menu yang ada pada restoran yang telah dipilih sebelumnya
     * Digunakan pada class Admin dan Customer
     */
    public void seeRestaurantMenu() {
        boolean runSeeRestaurantMenu = true;

        while (runSeeRestaurantMenu) {
            CLS.clearConsole();
            RestaurantMessages.pilihMenu(getNama(), getAlamat(),"Kembali"); //1. lihat makanan, 2. lihat minuman, 0. kembali
            Messages.inputInstruction();
            int userInput = keyboard.getMenuChoice(0,2);

            if (userInput == 0) {
                runSeeRestaurantMenu = false;
                break;
            } else if (userInput == 1) { //munculkan makanan sesuai list
                CLS.clearConsole();
                int index = 0;
                RestaurantMessages.lihatMakananHeader(getNama(), getAlamat());
                for (Dish element : listMakanan) {
                    Dish makananObject = listMakanan.get(index);
                    makananObject.toString();
                    index++;
                }
                RestaurantMessages.lihatMakananFooter();
                keyboard.nextLine();
            } else if (userInput == 2) { //munculkan minuman sesuai list
                CLS.clearConsole();
                int index = 0;
                RestaurantMessages.lihatMinumanHeader(getNama(), getAlamat());
                for (Dish element : listMinuman) {
                    Dish minumanObject = listMinuman.get(index);
                    minumanObject.toString();
                    index++;
                }
                RestaurantMessages.lihatMinumanFooter();
                keyboard.nextLine();
            }
        }

    }

    /**
     * Method: orderRestaurantMenu()
     * Digunakan untuk menambahkan order menu pada restoran yang dipilih
     * Dicall di class Customer
     * @param orderObject objek order yang ingin ditambahkan orderannya
     */

    public boolean orderRestaurantMenu(Orders orderObject) {
        boolean runOrderRestaurantMenu = true;
        boolean menuFound = false;
        boolean hasOrdered = false;

        //Variabel temporary untuk order yang dibuat
        String tempIdResto = this.idResto;
        String tempIdDish = null;
        String tempDishNama = null;
        double tempDishHarga = 0;
        int tempDishKuantitas = 0;
        double tempTotalHarga = 0;

        // Variabel yang menyimpan pilihan user
        String userMenuChoice = null;

        while (runOrderRestaurantMenu) {
            CLS.clearConsole();
            // Nunjukin pilihan makanan atau minuman
            RestaurantMessages.pilihMenu(getNama(), getAlamat(), "Selesai memesan"); // 1. makanan, 2. minuman, 0. selesai/batal memesan
            Messages.inputInstruction();
            int userInput = keyboard.getMenuChoice(0,2);

            if (userInput == 0) { //periksa apakah user sudah melakukan pemesanan/tidak
                if (!hasOrdered) { // Belum melakukan order apapun
                    RestaurantMessages.konfirmasiPembatalanOrder(); //1. batal, 2. tidak
                    Messages.inputInstruction();
                    int userInput2 = keyboard.getMenuChoice(1,2);
                    if(userInput2 == 1) {
                        runOrderRestaurantMenu = false;
                        break;
                    }
                } else if (hasOrdered) { // Sudah selesai melakukan order, lanjut ke pembayaran
                    runOrderRestaurantMenu = false;
                    CLS.clearConsole();
                    orderObject.payOrdersHeader();
                    orderObject.showOrders();
                    orderObject.payOrdersFooter();
                    orderObject.payment();
                    RestaurantMessages.pemesananDone();
                    keyboard.nextLine();
                    break;
                }
            } else if (userInput == 1) { // Mesan makanan
                int index = 0;
                // Nunjukin makanan yang ada
                CLS.clearConsole();
                RestaurantMessages.pesanMakananHeader(getNama(), getAlamat());
                for (Dish element : listMakanan) { //memunculkan list makanan yang ada
                    Dish makananObject = listMakanan.get(index);
                    makananObject.toString();
                    index++;
                }
                RestaurantMessages.pesanMakananFooter();
                Messages.inputInstruction(); //meminta user menginput id makanan yang ingin dipesan
                userMenuChoice = keyboard.nextLine();
                // Memeriksa apakah makanan ada atau tidak
                if (userMenuChoice.equals("0")) break;
                else {
                    index = 0;
                    for (Dish element : listMakanan) {
                        Dish makananObject = listMakanan.get(index);
                        // Kalau menu yg dicari ditemukan, masukkan ke order
                        if (makananObject.getIdDish().equals(userMenuChoice.toUpperCase())) {
                            menuFound = true;
                            // Menyimpan data temporary sebelum dimasukkan ke object
                            tempIdDish = makananObject.getIdDish();
                            tempDishNama = makananObject.getNama();
                            tempDishHarga = makananObject.getHarga();

                            System.out.print("    Banyak kuantitas menu yang dipesan: ");
                            tempDishKuantitas = keyboard.validationInteger();
                            tempTotalHarga = tempDishHarga*tempDishKuantitas; //menghitung total harga
                            // Memasukkan data ke objek order details
                            orderObject.addOrderDetails(tempIdDish, tempDishNama, tempDishHarga, tempDishKuantitas);
                            //Memunculkan pesanan untuk saat ini
                            CLS.clearConsole();
                            orderObject.showOrders();
                            orderObject.showOrdersFooter();
                            keyboard.nextLine();

                            hasOrdered = true;
                            break;
                        } else index++;
                    }
                    if (!menuFound) { //jika ternyata menu tidak ditemukan
                        System.out.println("    Maaf, ID Menu yang Anda masukkan salah, nih.");
                        System.out.println("    Tekan ENTER untuk kembali.");
                        keyboard.nextLine();
                    }
                }
            } else if (userInput == 2) { // Mesan minuman
                int index = 0;
                // Nunjukin minuman yang ada
                CLS.clearConsole();
                RestaurantMessages.pesanMinumanHeader(getNama(), getAlamat());
                for (Dish element : listMinuman) { //memunculkan list minuman yang ada
                    Dish minumanObject = listMinuman.get(index);
                    minumanObject.toString();
                    index++;
                }
                RestaurantMessages.pesanMinumanFooter();
                Messages.inputInstruction(); //meminta user memilih minuman dengan memasukkan id
                userMenuChoice = keyboard.nextLine();
                // Memeriksa apakah minuman ada atau tidak
                if (userMenuChoice.equals("0")) break;
                else {
                    index = 0;
                    for (Dish element : listMinuman) {
                        Dish minumanObject = listMinuman.get(index);
                        // Kalau menu yg dicari ditemukan
                        if (minumanObject.getIdDish().equals(userMenuChoice.toUpperCase())) {
                            menuFound = true;
                            // Menyimpan data temporary sebelum dimasukkan ke object
                            tempIdDish = minumanObject.getIdDish();
                            tempDishNama = minumanObject.getNama();
                            tempDishHarga = minumanObject.getHarga();

                            System.out.print("    Banyak kuantitas menu yang dipesan: ");
                            tempDishKuantitas = keyboard.validationInteger();
                            tempTotalHarga = tempDishHarga * tempDishKuantitas;
                            // Memasukkan data ke objek order details
                            orderObject.addOrderDetails(tempIdDish, tempDishNama, tempDishHarga, tempDishKuantitas);
                            //Memunculkan pesanan untuk saat ini
                            CLS.clearConsole();
                            orderObject.showOrders();
                            orderObject.showOrdersFooter();
                            keyboard.nextLine();

                            hasOrdered = true;
                            break;
                        } else index++;
                    }
                    if (!menuFound) { //jika ternyata menu tidak ditemukan
                        System.out.println("    Maaf, ID Menu yang Anda masukkan salah, nih.");
                        System.out.println("    Tekan ENTER untuk kembali.");
                        keyboard.nextLine();
                    }
                }
            }
        }
        return hasOrdered;

    }

    /**
     * Methods: equals
     * Memeriksa apakah ada duplikat atau tidak
     * @param otherObject
     * @return
     */

    public boolean equals(Object otherObject) {
        if (otherObject == null) {
            return false;
        } else if (getClass() != otherObject.getClass()) {
            return false;
        }
        return true;
    }

    /**
     * Method: toString
     * @return null, tapi memberikan printf toString sesuai format menu yang ada
     */
    public String toString() {
        System.out.printf("|| %-9s| %-30s| %-21s||\n", getIdResto(), getNama(), getAlamat());
        return null;
    }

    // Methods: Getter and setter
    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getIdResto() {
        return idResto;
    }

    public ArrayList<Dish> getListMakanan() {
        return listMakanan;
    }

    public ArrayList<Dish> getListMinuman() {
        return listMinuman;
    }

    static class RestaurantMessages {
        public static void pilihMenu(String namaResto, String alamatResto, String text) {
            System.out.println(" + =============================================================== + ");
            System.out.println("||                            Selamat Datang!                      ||");
            System.out.printf ("|| > Restoran: %-52s||\n", namaResto);
            System.out.printf ("|| > Alamat  : %-52s||\n", alamatResto);
            System.out.println(" + --------------------------------------------------------------- + ");
            System.out.println("||                  Yuk, pilih makanan dan minumanmu               ||");
            System.out.println("||                                                                 ||");
            System.out.println("||   [1] Lihat Makanan                                             ||");
            System.out.println("||   [2] Lihat Minuman                                             ||");
            System.out.printf("||   [0] %-58s||\n", text);
            System.out.println(" + =============================================================== + ");
        }
        public static void lihatMakananHeader(String namaResto, String alamatResto) {
            System.out.println(" + =============================================================== + ");
            System.out.println("||                        LIHAT MENU RESTORAN                      ||");
            System.out.printf ("|| > Restoran: %-52s||\n", namaResto);
            System.out.printf ("|| > Alamat  : %-52s||\n", alamatResto);
            System.out.println(" + --------------------------------------------------------------- + ");
            System.out.println("|| ID MENU  |             NAMA MAKANAN             |      HARGA    ||");
            System.out.println(" + --------------------------------------------------------------- + ");
        }
        public static void lihatMakananFooter() {
            System.out.println(" + --------------------------------------------------------------- + ");
            System.out.println("||                      Tekan Enter untuk kembali                  ||");
            System.out.println(" + =============================================================== + ");
        }

        public static void lihatMinumanHeader(String namaResto, String alamatResto) {
            System.out.println(" + =============================================================== + ");
            System.out.println("||                        LIHAT MENU RESTORAN                      ||");
            System.out.printf ("|| > Restoran: %-52s||\n", namaResto);
            System.out.printf ("|| > Alamat  : %-52s||\n", alamatResto);
            System.out.println(" + --------------------------------------------------------------- + ");
            System.out.println("|| ID MENU  |             NAMA MINUMAN             |      HARGA    ||");
            System.out.println(" + --------------------------------------------------------------- + ");
        }
        public static void lihatMinumanFooter() {
            System.out.println(" + --------------------------------------------------------------- + ");
            System.out.println("||                      Tekan Enter untuk kembali                  ||");
            System.out.println(" + =============================================================== + ");
        }

        public static void pesanMakananHeader(String namaResto, String alamatResto) {
            System.out.println(" + =============================================================== + ");
            System.out.println("||                        PESAN MENU RESTORAN                      ||");
            System.out.printf ("|| > Restoran: %-52s||\n", namaResto);
            System.out.printf ("|| > Alamat  : %-52s||\n", alamatResto);
            System.out.println(" + --------------------------------------------------------------- + ");
            System.out.println("|| ID MENU  |             NAMA MAKANAN             |      HARGA    ||");
            System.out.println(" + --------------------------------------------------------------- + ");
        }
        public static void pesanMakananFooter() {
            System.out.println(" + --------------------------------------------------------------- + ");
            System.out.println("||        Pilih menu yang diinginkan dengan menginput ID Menu      ||");
            System.out.println("||               Klik [0] untuk kembali ke menu customer           ||");
            System.out.println(" + =============================================================== + ");
        }

        public static void pesanMinumanHeader(String namaResto, String alamatResto) {
            System.out.println(" + =============================================================== + ");
            System.out.println("||                        LIHAT MENU RESTORAN                      ||");
            System.out.printf ("|| > Restoran: %-52s||\n", namaResto);
            System.out.printf ("|| > Alamat  : %-52s||\n", alamatResto);
            System.out.println(" + --------------------------------------------------------------- + ");
            System.out.println("|| ID MENU  |             NAMA MINUMAN             |      HARGA    ||");
            System.out.println(" + --------------------------------------------------------------- + ");
        }
        public static void pesanMinumanFooter() {
            System.out.println(" + --------------------------------------------------------------- + ");
            System.out.println("||        Pilih menu yang diinginkan dengan menginput ID Menu      ||");
            System.out.println("||               Klik [0] untuk kembali ke menu customer           ||");
            System.out.println(" + =============================================================== + ");
        }




        public static void addMenu() {
            System.out.println(" + =============================================================== + ");
            System.out.println("||                       TAMBAH RESTORAN: MENU                     ||");
            System.out.println("||                            Admin Menu                           ||");
            System.out.println(" + --------------------------------------------------------------- + ");
            System.out.println("||                 Pilih bagian yang ingin ditambah                ||");
            System.out.println("||                                                                 ||");
            System.out.println("||      [1] Makanan           [2] Minuman         [0] Selesai      ||");
            System.out.println(" + =============================================================== + ");
        }

        public static void addMakananOrMinuman(String menu) {
            System.out.println(" + =============================================================== + ");
            System.out.printf("||                        TAMBAH RESTORAN: %7s                 ||\n", menu.toUpperCase());
            System.out.println("||                            Admin Menu                           ||");
            System.out.println(" + --------------------------------------------------------------- + ");
            System.out.printf("||  Mohon masukkan %7s baru dengan informasi sebagai berikut. ||\n", menu);
            System.out.printf("||          < ID %7s, Nama %7s, Harga %7s >           ||\n", menu, menu, menu);
            System.out.println(" + =============================================================== + ");
        }

        public static void addMenuBerhasil() {
            System.out.println(" + --------------------------------------------------------------- + ");
            System.out.println("||                      Menu berhasil ditambahkan!                 ||");
            System.out.println(" + =============================================================== + ");
        }

        public static void konfirmasiPembatalanTambahResto() {
            System.out.println(" + =============================================================== + ");
            System.out.println("||                 Anda belum menambahkan menu apapun.             ||");
            System.out.println("||                Ingin membatalkan penambahan restoran?           ||");
            System.out.println("||                                                                 ||");
            System.out.println("||                 [1] Ya                   [2] Tidak              ||");
            System.out.println(" + =============================================================== + ");
        }

        public static void konfirmasiPembatalanOrder() {
            System.out.println(" + =============================================================== + ");
            System.out.println("||           Anda belum memesan apapun. Ingin membatalkan?         ||");
            System.out.println("||                                                                 ||");
            System.out.println("||                 [1] Ya                   [2] Tidak              ||");
            System.out.println(" + =============================================================== + ");
        }

        public static void pemesananDone() {
            System.out.println(" + =============================================================== + ");
            System.out.println("||                   Pesanan Anda sudah ditambahkan!               ||");
            System.out.println("||                      Tekan Enter untuk kembali                  ||");
            System.out.println(" + =============================================================== + ");
        }

    }
}

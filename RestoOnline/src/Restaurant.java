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

    public void addMenu() {
        int userInput;
        boolean runAddMenu = true;
        boolean idNotValid = true;

        // Variabel untuk makanan/minuman
        String tempIdDish = null;
        String tempNamaDish = null;
        double tempHargaDish = 0;

        while (runAddMenu) {
            RestaurantMessages.addMenu();
            Messages.inputInstruction();
            userInput = keyboard.getMenuChoice(0,2);
            if (userInput == 0) {
                runAddMenu = false;
            } else if (userInput == 1) {
                RestaurantMessages.addMakananOrMinuman("Makanan");
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
                System.out.print("    Harga Makanan: ");
                tempHargaDish = keyboard.getDouble();

                addMakanan(tempIdDish, tempNamaDish, tempHargaDish);
                RestaurantMessages.addMenuBerhasil();
                keyboard.nextLine();
            } else if (userInput == 2) {
                RestaurantMessages.addMakananOrMinuman("Minuman");
                System.out.print("   ID Minuman: ");
                tempIdDish = keyboard.validateID();
                System.out.print("   Nama Minuman: ");
                tempNamaDish = keyboard.validateString("Nama minuman", 30);
                System.out.print("   Harga Minuman: ");
                tempHargaDish = keyboard.getDouble();

                addMinuman(tempIdDish, tempNamaDish, tempHargaDish);
                RestaurantMessages.addMenuBerhasil();
                keyboard.nextLine();
            }
        }

    }

    public void seeRestaurantMenu() {
        boolean runSeeRestaurantMenu = true;

        while (runSeeRestaurantMenu) {
            RestaurantMessages.pilihMenu(getNama());
            Messages.inputInstruction();
            int userInput = keyboard.getMenuChoice(0,2);

            if (userInput == 0) {
                runSeeRestaurantMenu = false;
                break;
            } else if (userInput == 1) {
                int index = 0;
                RestaurantMessages.lihatMakananHeader(getNama());
                for (Dish element : listMakanan) {
                    Dish makananObject = listMakanan.get(index);
                    makananObject.toString();
                    index++;
                }
                RestaurantMessages.lihatMakananFooter();
                keyboard.nextLine();
            } else if (userInput == 2) {
                int index = 0;
                RestaurantMessages.lihatMinumanHeader(getNama());
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

    public void orderRestaurantMenu(Orders orderObject) {
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
            // Nunjukin pilihan makanan atau minuman
            RestaurantMessages.pilihMenu(getNama());
            Messages.inputInstruction();
            int userInput = keyboard.getMenuChoice(0,2);

            if (userInput == 0) {
                if (!hasOrdered) { // Belum melakukan order apapun
                    RestaurantMessages.konfirmasiPembatalan();
                    Messages.inputInstruction();
                    int userInput2 = keyboard.getMenuChoice(1,2);
                    if(userInput2 == 1) {
                        runOrderRestaurantMenu = false;
                        break;
                    }
                } else if (hasOrdered) { // Sudah selesai melakukan order
                    runOrderRestaurantMenu = false;
                    RestaurantMessages.pemesananDone();
                    keyboard.nextLine();
                    break;
                }
            } else if (userInput == 1) { // Mesan makanan
                int index = 0;
                // Nunjukin makanan yang ada
                RestaurantMessages.pesanMakananHeader(getNama());
                for (Dish element : listMakanan) {
                    Dish makananObject = listMakanan.get(index);
                    makananObject.toString();
                    index++;
                }
                RestaurantMessages.pesanMakananFooter();
                Messages.inputInstruction();
                userMenuChoice = keyboard.nextLine();
                // Memeriksa apakah makanan ada atau tidak
                if (userMenuChoice.equals("0")) break;
                else {
                    index = 0;
                    for (Dish element : listMakanan) {
                        Dish makananObject = listMakanan.get(index);
                        // Kalau menu yg dicari ditemukan
                        if (makananObject.getIdDish().equals(userMenuChoice.toUpperCase())) {
                            menuFound = true;
                            // Menyimpan data temporary sebelum dimasukkan ke object
                            tempIdDish = makananObject.getIdDish();
                            tempDishNama = makananObject.getNama();
                            tempDishHarga = makananObject.getHarga();

                            System.out.print("    Banyak kuantitas menu yang dipesan: ");
                            tempDishKuantitas = keyboard.validationInteger();
                            tempTotalHarga = tempDishHarga*tempDishKuantitas;
                            // Memasukkan data ke objek
                            orderObject.addOrderDetails(tempIdDish, tempDishNama, tempDishHarga, tempDishKuantitas);
                            //Memunculkan pesanan untuk saat ini
                            orderObject.showOrders();
                            orderObject.showOrdersFooter();
                            keyboard.nextLine();

                            hasOrdered = true;
                            break;
                        } else index++;
                    }
                    if (!menuFound) {
                        System.out.println("    Maaf, ID Menu yang Anda masukkan salah, nih.");
                        System.out.println("    Tekan ENTER untuk kembali.");
                        keyboard.nextLine();
                    }
                }
            } else if (userInput == 2) { // Mesan minuman
                int index = 0;
                // Nunjukin minuman yang ada
                RestaurantMessages.pesanMinumanHeader(getNama());
                for (Dish element : listMinuman) {
                    Dish minumanObject = listMinuman.get(index);
                    minumanObject.toString();
                    index++;
                }
                RestaurantMessages.pesanMinumanFooter();
                Messages.inputInstruction();
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
                            // Memasukkan data ke objek
                            orderObject.addOrderDetails(tempIdDish, tempDishNama, tempDishHarga, tempDishKuantitas);
                            //Memunculkan pesanan untuk saat ini
                            orderObject.showOrders();
                            orderObject.showOrdersFooter();
                            keyboard.nextLine();

                            hasOrdered = true;
                            break;
                        } else index++;
                    }
                    if (!menuFound) {
                        System.out.println("    Maaf, ID Menu yang Anda masukkan salah, nih.");
                        System.out.println("    Tekan ENTER untuk kembali.");
                        keyboard.nextLine();
                    }
                }
            }
        }

    }

    public boolean equals(Object otherObject) {
        if (otherObject == null) {
            return false;
        } else if (getClass() != otherObject.getClass()) {
            return false;
        }
        return true;
    }

    public String toString() {
        System.out.printf("|| %-9s| %-30s| %-21s||\n", getIdResto(), getNama(), getAlamat());
        return null;
    }

    // Methods: Getter and setter
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public ArrayList<Dish> getListMakanan() {
        return listMakanan;
    }

    public void setListMakanan(ArrayList<Dish> listMakanan) {
        this.listMakanan = listMakanan;
    }

    public ArrayList<Dish> getListMinuman() {
        return listMinuman;
    }

    public void setListMinuman(ArrayList<Dish> listMinuman) {
        this.listMinuman = listMinuman;
    }

    public String getIdResto() {
        return idResto;
    }

    public void setIdResto(String idResto) {
        this.idResto = idResto;
    }

    static class RestaurantMessages {
        public static void pilihMenu(String namaResto) {
            System.out.println(" + =============================================================== + ");
            System.out.println("||                            Selamat Datang!                      ||");
            System.out.printf ("|| > Restoran: %-52s||\n", namaResto);
            System.out.println(" + --------------------------------------------------------------- + ");
            System.out.println("||                  Yuk, pilih makanan dan minumanmu               ||");
            System.out.println("||                                                                 ||");
            System.out.println("||   [1] Lihat Makanan                                             ||");
            System.out.println("||   [2] Lihat Minuman                                             ||");
            System.out.println("||   [0] Selesai memesan                                           ||");
            System.out.println(" + =============================================================== + ");
        }
        public static void lihatMakananHeader(String namaResto) {
            System.out.println(" + =============================================================== + ");
            System.out.println("||                        LIHAT MENU RESTORAN                      ||");
            System.out.printf ("|| > Restoran: %-52s||\n", namaResto);
            System.out.println(" + --------------------------------------------------------------- + ");
            System.out.println("|| ID MENU  |             NAMA MAKANAN             |      HARGA    ||");
            System.out.println(" + --------------------------------------------------------------- + ");
        }
        public static void lihatMakananFooter() {
            System.out.println(" + --------------------------------------------------------------- + ");
            System.out.println("||                      Tekan Enter untuk kembali                  ||");
            System.out.println(" + =============================================================== + ");
        }

        public static void lihatMinumanHeader(String namaResto) {
            System.out.println(" + =============================================================== + ");
            System.out.println("||                        LIHAT MENU RESTORAN                      ||");
            System.out.printf ("|| > Restoran: %-52s||\n", namaResto);
            System.out.println(" + --------------------------------------------------------------- + ");
            System.out.println("|| ID MENU  |             NAMA MINUMAN             |      HARGA    ||");
            System.out.println(" + --------------------------------------------------------------- + ");
        }
        public static void lihatMinumanFooter() {
            System.out.println(" + --------------------------------------------------------------- + ");
            System.out.println("||                      Tekan Enter untuk kembali                  ||");
            System.out.println(" + =============================================================== + ");
        }

        public static void pesanMakananHeader(String namaResto) {
            System.out.println(" + =============================================================== + ");
            System.out.println("||                        PESAN MENU RESTORAN                      ||");
            System.out.printf ("|| > Restoran: %-52s||\n", namaResto);
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

        public static void pesanMinumanHeader(String namaResto) {
            System.out.println(" + =============================================================== + ");
            System.out.println("||                        LIHAT MENU RESTORAN                      ||");
            System.out.printf ("|| > Restoran: %-52s||\n", namaResto);
            System.out.println(" + --------------------------------------------------------------- + ");
            System.out.println("|| ID MENU  |             NAMA MINUMAN             |      HARGA    ||");
            System.out.println(" + --------------------------------------------------------------- + ");
        }
        public static void pesanMinumanFooter() {
            System.out.println(" + --------------------------------------------------------------- + ");
            System.out.println("||                      Tekan Enter untuk kembali                  ||");
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
            System.out.printf("|| Mohon masukkan %7s baru dengan informasi sebagai berikut. ||\n", menu);
            System.out.printf("||          < ID %7s, Nama %7s, Harga %7s >           ||\n", menu, menu, menu);
            System.out.println(" + =============================================================== + ");
        }

        public static void addMenuBerhasil() {
            System.out.println(" + --------------------------------------------------------------- + ");
            System.out.println("||                      Menu berhasil ditambahkan!                 ||");
            System.out.println(" + =============================================================== + ");
        }

        public static void konfirmasiPembatalan() {
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

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
                tempHargaDish = keyboard.getHarga();

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
                tempHargaDish = keyboard.getHarga();

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

    public void deleteDish() {

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
            System.out.println("||   [0] Kembali ke menu awal                                      ||");
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
    }
}

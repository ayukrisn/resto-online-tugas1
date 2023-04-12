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

    public void seeRestaurantMenu() {
        boolean runSeeRestaurantMenu = true;

        while (runSeeRestaurantMenu) {
            RestaurantMessages.pilihMenu(getNama());
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
                    Dish minumannObject = listMinuman.get(index);
                    minumannObject.toString();
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
            System.out.printf ("|| Restoran: %-54s||\n", namaResto);
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
            System.out.printf ("|| Restoran: %-54s||\n", namaResto);
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
            System.out.printf ("|| Restoran: %-54s||\n", namaResto);
            System.out.println(" + --------------------------------------------------------------- + ");
            System.out.println("|| ID MENU  |             NAMA MINUMAN             |      HARGA    ||");
            System.out.println(" + --------------------------------------------------------------- + ");
        }
        public static void lihatMinumanFooter() {
            System.out.println(" + --------------------------------------------------------------- + ");
            System.out.println("||                      Tekan Enter untuk kembali                  ||");
            System.out.println(" + =============================================================== + ");
        }
    }
}

import java.util.Scanner;

public class Admin {
    // Variables
    Scanner scanner = new Scanner(System.in);
    private String username = null;
    private String password = null;

    private static final String nama = "Elian";

    // Constructor
    public Admin(String username, String password) {
        System.out.printf("Selamat Datang, %s.", nama);
        this.username = username;
        this.password = password;
    }

    // Methods
    public void menu() {
        // Variabel Lokal
        int pilihanMenu = 0;
        while (pilihanMenu != 4){
            System.out.println();
            System.out.println("MENU ADMIN");
            System.out.println("1. Lihat Restoran");
            System.out.println("2. Tambah Restoran");
            System.out.println("3. Hapus Restoran");
            System.out.println("4. Kembali ke menu log in");
            pilihanMenu = scanner.nextInt();

            if(pilihanMenu == 1) {
                System.out.println();
                // lihatResto();
            } else if(pilihanMenu == 2) {
                System.out.println();
                // tambahResto();
            } else if(pilihanMenu == 3) {
                System.out.println();
                // hapusResto();
            } else if (pilihanMenu == 4) {
                System.out.println();
            } else {
                System.out.println("Maaf, input yang Anda berikan tidak valid.");
                System.out.println("Mohon coba lagi.");
            }
        }
    }
}

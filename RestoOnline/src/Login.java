import java.util.Scanner;

public class Login {

    // Variable
    private String username = null;
    private String password = null;
    private int pilihanMenu = 0;
    // Scanner
    Scanner scanner = new Scanner(System.in);

    // Constructor
    public Login() {

    }
    public void login() {
        while (true) {
            System.out.println("LOG IN");
            System.out.println("Selamat datang ke Makan Online!");
            System.out.println("Login sebagai:");
            System.out.println("1. Admin");
            System.out.println("2. Customer");
            System.out.println("3. Keluar dari program");
            System.out.print("Pilih:");
            this.pilihanMenu = scanner.nextInt();

            if(pilihanMenu == 1) {
                System.out.println();
                loginAdmin();
            } else if(pilihanMenu == 2) {
                loginCustomer();
            } else if(pilihanMenu == 3) {
                System.out.println("Terima kasih sudah menggunakan aplikasi ini!");
                System.exit(0);
            } else {
                System.out.println("Maaf, input yang Anda berikan tidak valid.");
                System.out.println("Mohon coba lagi.");
                login();
            }
        }
    }

    private void loginAdmin() {
        while (this.pilihanMenu != 2) {
            this.pilihanMenu = 0;
            // Mendapatkan username dan password
            System.out.println();
            System.out.println("LOG IN SEBAGAI ADMIN");
            System.out.print("Masukkan username Anda:");
            this.username = scanner.next();
            System.out.print("Masukkan password Anda:");
            this.password = scanner.next();

            // Periksa bila password dan username benar
            if(username.equals("Admin1") && password.equals("Admpass")) {
                System.out.println();
                Admin admin1 = new Admin("Admin1", "Admpass");
                admin1.menu();
                this.pilihanMenu = 2;
            } else {
                System.out.println();
                System.out.println("Username atau password Anda salah.");
                System.out.println("1. Log in kembali");
                System.out.println("2. Kembali ke menu awal");
                System.out.print("Pilih:");
                this.pilihanMenu = scanner.nextInt();

                if(pilihanMenu == 1) {
                    System.out.println();
                } else if(pilihanMenu == 2) {
                    System.out.println();
                } else {
                    System.out.println();
                    System.out.println("Maaf, input yang Anda berikan tidak valid.");
                    System.out.println("Anda akan diarahkan ke menu awal.");
                }
            }
        }
    }

    private void loginCustomer() {
        while (this.pilihanMenu != 2) {
            this.pilihanMenu = 0;
            // Mendapatkan username dan password
            System.out.println();
            System.out.println("LOG IN SEBAGAI CUSTOMER");
            System.out.print("Masukkan username Anda:");
            this.username = scanner.next();
            System.out.print("Masukkan password Anda:");
            this.password = scanner.next();

            // Periksa bila password dan username benar
            if(username.equals("Customer1") && password.equals("Custpass")) {
                System.out.println();
                Customer customer = new Customer("Custumer1", "Custpass");
                customer.menu();
                this.pilihanMenu = 2;
            } else {
                System.out.println();
                System.out.println("Username atau password Anda salah.");
                System.out.println("1. Log in kembali");
                System.out.println("2. Kembali ke menu awal");
                System.out.print("Pilih:");
                this.pilihanMenu = scanner.nextInt();

                if(pilihanMenu == 1) {
                    System.out.println();
                } else if(pilihanMenu == 2) {
                    System.out.println();
                } else {
                    System.out.println();
                    System.out.println("Maaf, input yang Anda berikan tidak valid.");
                    System.out.println("Anda akan diarahkan ke menu awal.");
                    this.pilihanMenu = 2;
                }
            }
        }
    }
}

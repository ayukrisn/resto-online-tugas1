import java.util.Scanner;

public class Customer extends User {
    // Variables
    private boolean isCustomer = false;

    private Input keyboard = new Input();

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

    public void showMenu() {
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

    public boolean getIsCustomer() {
        return isCustomer;
    }

    public void setIsCustomer(boolean customer) {
        isCustomer = customer;
    }
}

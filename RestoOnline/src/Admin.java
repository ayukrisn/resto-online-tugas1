import java.util.Scanner;

public class Admin extends User {
    // Variables
    private boolean isAdmin = false;
    Scanner keyboard = new Scanner(System.in);

    // Constructor
    public Admin() {
        super();
    }
    public Admin(String theUsername, String thePassword, String theNama) {
        super(theUsername, thePassword, theNama);
    }

    // Methods
    public boolean logIn(String username, String password, String nama) {
        String inputUsername;
        String inputPassword;
        boolean hasLoggedIn = false;

        Messages.loginInstruction("ADMINISTRATOR");
        System.out.print("  Username: ");
        inputUsername = keyboard.next();
        System.out.print("  Password: ");
        inputPassword = keyboard.next();

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

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean admin) {
        isAdmin = admin;
    }
}

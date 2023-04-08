import java.util.Scanner;

public class Admin extends User {
    // Variables
    private boolean isAdmin = true;
    Scanner keyboard = new Scanner(System.in);

    // Constructor
    public Admin() {
        super();
    }
    public Admin(String theUsername, String thePassword, String theNama) {
        super(theUsername, thePassword, theNama);
    }

    // Methods
    public boolean logIn() {
        String inputUsername;
        String inputPassword;
        boolean hasLoggedIn = false;

        Messages.loginInstruction("ADMINISTRATOR");
        System.out.print("  Username: ");
        inputUsername = keyboard.next();
        System.out.print("  Password: ");
        inputPassword = keyboard.next();

        if (username.equals(inputUsername) && password.equals(inputPassword)) {
            hasLoggedIn = true;
        } else {
            hasLoggedIn = false;
        }
        return hasLoggedIn;
     }


    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean admin) {
        isAdmin = admin;
    }
}

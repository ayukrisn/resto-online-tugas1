public abstract class User {

    // Variables
    protected String username;
    protected String password;
    protected String nama;

    // Constructor
    public User() {
        username = "Tidak ada username";
        password = "Tidak ada password";
        nama = "Tidak ada nama";
    }

    public User(String username, String password, String nama) {
        this.username = username;
        this.password = password;
        this.nama = nama;
    }
    // Methods
    public abstract boolean logIn();

    public abstract void showMenu();
}

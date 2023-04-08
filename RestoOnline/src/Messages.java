public class Messages {
    /**
     * MESSAGES UNTUK KEPERLUAN LOG IN
     */
    public static void loginGreeting () {
        System.out.println(" + =============================================================== + ");
        System.out.println("||                           Welcome Back!                         ||");
        System.out.println("||                         T A P  AND  E A T                       ||");
        System.out.println("||                            LOGIN PAGE                           ||");
        System.out.println(" + --------------------------------------------------------------- + ");
        System.out.println("||                           Login Sebagai                         ||");
        System.out.println("||        [1] Administrator                   [2] Customer         ||");
        System.out.println("||                     [0] Keluar dari Program                     ||");
        System.out.println(" + =============================================================== + ");
    }

    public static void loginInstruction(String user) {
        System.out.println(" + =============================================================== + ");
        System.out.printf("||                      LOG IN: %-35s||\n", user);
        System.out.println("||                                                                 ||");
        System.out.println("||      Mohon Masukkan Username dan Password untuk melanjutkan     ||");
        System.out.println(" + --------------------------------------------------------------- + ");
    }

    public static void loggedInGreeting(String userName) {
        System.out.println(" + =============================================================== + ");
        System.out.printf("||                      Welcome, %-34s||\n", userName);
        System.out.println(" + =============================================================== + ");
    }

    public static void loginFailed () {
        System.out.println(" + =============================================================== + ");
        System.out.println("||                            Log In Gagal                         ||");
        System.out.println("||                                                                 ||");
        System.out.println("|| Pastikan Anda sudah memasukkan Username dan Password yang benar || ");
        System.out.println(" + --------------------------------------------------------------- + ");
        System.out.println("||                  Tekan Enter untuk melanjutkan                  ||");
        System.out.println(" + =============================================================== + ");
    }
    /**
     * MESSAGES UNTUK EXIT PROGRAM
     */
    public static void exitConfirmation() {
        System.out.println(" + =============================================================== + ");
        System.out.println("||                Kamu yakin ingin keluar dari program?            ||");
        System.out.println("||                                                                 ||");
        System.out.println("||                 [1] Ya                   [2] Tidak              ||");
        System.out.println(" + =============================================================== + ");
    }
    public static void exit() {
        System.out.println(" + =============================================================== + ");
        System.out.println("||                           Terima kasih!                         ||");
        System.out.println("||              *     Sampai jumpa di lain waktu :>     *          ||");
        System.out.println(" + =============================================================== + ");
    }

    /**
     * MESSAGES PERINTAH UNTUK INPUT
     */
    public static void inputInstruction() {
        System.out.print("    Masukkan pilihan: ");
    }
}


/**
 * Input: kelas yang mengatur input dari User
 *
 * Fungsi:
 *  1. Menerima input User dalam bentuk integer untuk memilih menu dan input pembayaran
 *  2. Menerima input User untuk menghapus [9] atau selesai [0] memesan
 */

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Input {
    // Constants
    private static final int INPUT_HAPUS = 9;
    private static final int INPUT_SELESAI = 0;

    // Variable
    Scanner input = new Scanner(System.in);

    // Constructor
    public Input() { }

    // Methods

    /**
     * Digunakan untuk memastikan bahwa input yang diberikan
     * sudah sesuai dengan ketentuan: harus integer
     */
    private int validationInteger() {
        int inputInteger = -1;
        boolean inputValid = false;

        Scanner input = new Scanner(System.in);

        while (!inputValid) {
            while (true) {
                try {
                    inputInteger = input.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("    Mohon masukkan angka yang benar.");
                    input.next();
                    Messages.inputInstruction();
                }

            }
            inputValid = true;

        }
        return inputInteger;
    }

    /**
     * Memeriksa apakah User memberikan input sesuai dengan
     * menu yang diberikan
     */
    public int getMenuChoice(int range1, int range2) {
        int inputMenuChoice = 0;
        boolean inputValid = false;

        while (!inputValid) {
            inputMenuChoice = validationInteger();

            if (inputMenuChoice < range1 || inputMenuChoice > range2) {
                System.out.println("    Mohon masukkan pilihan sesuai menu yang disediakan.");
                Messages.inputInstruction();
            } else {
                inputValid = true;
                // System.out.println("Input benar.");
            }
        }

        return inputMenuChoice;
    }

    /**
     * Memeriksa apakah User memberikan input untuk menghapus pilihan
     */
    public boolean isDelete(int inputMenuChoice) {
        return inputMenuChoice == INPUT_HAPUS;
    }

    /**
     * Memeriksa apakah User memberikan input untuk selesai memesan
     */
    public boolean isDoneOrder(int inputMenuChoice) {
        return inputMenuChoice == INPUT_SELESAI;
    }


    /**
     * Digunakan untuk memvalidasi input double
     */
    private double validationDouble() {
        double inputDouble = 0;

        while (true) {
            try {
                inputDouble = input.nextDouble();
                break;
            } catch (InputMismatchException e) {
                System.out.println("    Mohon masukkan nominal yang benar.");
                input.next();
                Messages.inputInstruction();
            }
        }
        return inputDouble;
    }

    /**
    * Digunakan untuk mendapatkan dan memvalidasi input pembayaran
     * dari customer
    */
    public double getHarga() {
        double inputPayment = 0;
        boolean inputValid = false;

        while (!inputValid) {
            inputPayment = validationDouble();

            if (inputPayment < 0) {
                System.out.println("    Mohon masukkan nominal yang benar.");
                Messages.inputInstruction();
            } else {
                inputValid = true;
            }
        }

        return inputPayment;
    }

    /**
     * Digunakan untuk validasi input pada saat menambahkan ID
     */
    public String validateID() {
        boolean inputValid = false;
        String id = null;
        while (!inputValid) {
            inputValid = true;
            id = input.nextLine();
            if (id.isBlank()) {
                System.out.println("    ID tidak boleh kosong");
                inputValid = false;
            }
            if (id.length() > 9) {
                System.out.println("    Maksimal panjang ID adalah 9 huruf.");
                inputValid = false;
            }
            if (id.contains(" ")) {
                System.out.println("    ID tidak boleh berisi whitespace.");
                inputValid = false;
            }

            if (!inputValid) {
                System.out.println("    Mohon masukkan ID yang benar.");
                System.out.print("    ID:");
            } else {
                id = id.toUpperCase();
            }
        }
        return id;
    }

    /**
     * Digunakan untuk memeriksa apakah sudah ada ID restoran yang mirip atau tidak
     */

    public boolean isIDRestoSame(ArrayList<Restaurant> listRestaurant, String id) {
        boolean isIDSame = false;
        int index = 0;

        for(Restaurant element : listRestaurant) {
            if(element.getIdResto().equals(id)) isIDSame = true;
        }
        return isIDSame;
    }

    /**
     * Digunakan untuk memeriksa apakah sudah ada ID restoran yang mirip atau tidak
     */

    public boolean isIDDishSame(ArrayList<Dish> dish, String id) {
        boolean isIDSame = false;
        int index = 0;

        for(Dish element : dish) {
            if(element.getIdDish().equals(id)) isIDSame = true;
        }
        return isIDSame;
    }


    /**
     * Digunakan untuk validasi input pada saat menambahkan nama atau alamat
     * Nama restoran: length <= 30, nama dish: length <= 37, alamat: <= 21
     */
    public String validateString(String variable, int limit) {
        boolean inputValid = false;
        String string = null;
        while (!inputValid) {
            inputValid = true;
            string = input.nextLine();
            if (string.isBlank()) {
                System.out.printf("    %s tidak boleh kosong\n", variable);
                inputValid = false;
            }
            if (string.length() > limit) {
                System.out.printf("    Maksimal panjang %s adalah %d huruf.\n", variable, limit);
                inputValid = false;
            }

            if (!inputValid) {
                System.out.printf("    Mohon masukkan %s yang benar.\n", variable);
                System.out.printf("    %s:", variable);
            }
        }
        return string;
    }

    /**
     * Pause
     */
    public void pause() {
        input.nextLine();
    }

    /**
     * String
     */
    public String nextLine() {
        return input.nextLine();
    }

    public String next() {
        return input.next();
    }
}

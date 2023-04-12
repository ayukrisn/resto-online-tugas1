/**
 * Input: kelas yang mengatur input dari User
 *
 * Fungsi:
 *  1. Menerima input User dalam bentuk integer untuk memilih menu dan input pembayaran
 *  2. Menerima input User untuk menghapus [9] atau selesai [0] memesan
 */

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
    public double getPayment() {
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

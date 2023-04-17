/**
 * Input: kelas yang mengatur, menerima, dan memvalidasi input dari User
 */

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Input {

    // Variable
    Scanner input = new Scanner(System.in);

    // Constructor
    public Input() { }

    // Methods

    /**
     * Methods: validationInteger()
     * Digunakan untuk memastikan bahwa input yang diberikan
     * sudah sesuai dengan ketentuan: harus integer
     * @return received integer (valid)
     */
    public int validationInteger() {
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

    /**
     * Method: getMenuChoice
     * Menerima pilihan user sesuai dengan menu yang diberikan
     * @param range1 batas terkecil
     * @param range2 batas terbesar
     * @return received int (valid)
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
     * Method validationDouble()
     * Menerima dan memvalidasi apakah input benar double atau tidak
     * @return valid double
     */
    private double validationDouble() {
        double inputDouble = 0;

        while (true) {
            try {
                inputDouble = input.nextDouble();
                break;
            } catch (InputMismatchException e) {
                System.out.println("    Mohon masukkan angka yang benar.");
                input.next();
                Messages.inputInstruction();
            }
        }
        return inputDouble;
    }

    /**
     * Method: getDouble
     * Memeriksa apakah double yang diberikan negatif atau positif
     * @return valid double
     */
    public double getDouble() {
        double inputPayment = 0;
        boolean inputValid = false;

        while (!inputValid) {
            inputPayment = validationDouble();

            if (inputPayment < 0) {
                System.out.println("    Mohon masukkan angka yang benar.");
                Messages.inputInstruction();
            } else {
                inputValid = true;
            }
        }

        return inputPayment;
    }

    /**
     * Method: validateID
     * Digunakan untuk validasi input pada saat menambahkan ID
     * @return valid ID
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
     * Method: isIDRestoSame
     * @param listRestaurant
     * @param id
     * @return true jika sama, false jika tidak
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
     * Method isIDDishSame
     * Digunakan untuk memeriksa apakah sudah ada ID makanan yang mirip atau tidak
     * @param listDish
     * @param id
     * @return
     */
    public boolean isIDDishSame(ArrayList<Dish> listDish, String id) {
        boolean isIDSame = false;
        int index = 0;

        for(Dish element : listDish) {
            if(element.getIdDish().equals(id)) isIDSame = true;
        }
        return isIDSame;
    }


    /**
     * Method validateString()
     * Digunakan untuk validasi input pada saat menambahkan nama atau alamat
     * Nama restoran: length <= 30, nama dish: length <= 37, alamat: <= 21
     * @param variable
     * @param limit
     * @return valid string
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
     * String
     */
    public String nextLine() {
        return input.nextLine();
    }
}

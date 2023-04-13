public class Dish {
    // Variables
    private String idDish;
    private String nama;
    private double harga;

    // Constructor

    public Dish() {

    }

    public Dish (String idDish, String nama, double harga) {
        this.idDish = idDish;
        this.nama = nama;
        this.harga = harga;
    }

    // Methods

    /**
     * Method equals(): memeriksa apakah kedua objek sama atau tidak
     *                  biasanya dipakai untuk memeriksa duplikat
     * @param otherObject
     * @return
     */
    public boolean equals(Object otherObject) {
        if (otherObject == null) {
            return false;
        } else if (getClass() != otherObject.getClass()) {
            return false;
        }
        return true;
    }

    /**
     * Method: toString() : mengubah isi objek ke string
     * @return
     */
    public String toString() {
        System.out.printf("|| %-9s| %-37s| Rp%-12.2f||\n", getIdDish(), getNama(), getHarga());
        return null;
    }

    // Methods Getter and Setter
    public String getNama() {
        return nama;
    }

    public double getHarga() {
        return harga;
    }

    public String getIdDish() {
        return idDish;
    }
}

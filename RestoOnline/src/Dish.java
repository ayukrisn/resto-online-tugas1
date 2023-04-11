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
    public boolean equals(Object otherObject) {
        if (otherObject == null) {
            return false;
        } else if (getClass() != otherObject.getClass()) {
            return false;
        }
        return true;
    }

    // Methods Getter and Setter
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }


    public String getIdDish() {
        return idDish;
    }

    public void setIdDish(String idDish) {
        this.idDish = idDish;
    }
}

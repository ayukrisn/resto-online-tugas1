import java.util.ArrayList;

public class Restaurant {
    // Variables
    private String idResto;
    private String nama;
    private String alamat;
    private ArrayList<Dish> listMakanan = new ArrayList<Dish>();
    private ArrayList<Dish> listMinuman = new ArrayList<Dish>();

    // Constractor
    public Restaurant() {

    }
    public Restaurant(String idResto, String nama, String alamat) {
        this.idResto = idResto;
        this.nama = nama;
        this.alamat = alamat;
    }

    // Methods
    public void addMakanan(Dish makanan) {
        listMakanan.add(makanan);
    }

    private void checkDish() {

    }

    public void deleteDish() {

    }

    public boolean equals(Object otherObject) {
        if (otherObject == null) {
            return false;
        } else if (getClass() != otherObject.getClass()) {
            return false;
        }
        return true;
    }

    public String toString() {
        System.out.printf("|| %-9s| %-30s| %-21s||\n", getIdResto(), getNama(), getAlamat());
        return null;
    }

    // Methods: Getter and setter
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public ArrayList<Dish> getListMakanan() {
        return listMakanan;
    }

    public void setListMakanan(ArrayList<Dish> listMakanan) {
        this.listMakanan = listMakanan;
    }

    public ArrayList<Dish> getListMinuman() {
        return listMinuman;
    }

    public void setListMinuman(ArrayList<Dish> listMinuman) {
        this.listMinuman = listMinuman;
    }

    public String getIdResto() {
        return idResto;
    }

    public void setIdResto(String idResto) {
        this.idResto = idResto;
    }
}

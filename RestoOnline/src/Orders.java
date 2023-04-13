import java.util.ArrayList;

public class Orders {
    // Variables
    private String idOrders;
    private String idResto;
    private String namaResto;
    private double jarakResto;
    private ArrayList<Orders.OrderDetail> listOrderDetails = new ArrayList<>();
    private double totalBiaya;

    public Orders () {
    }

    public Orders(String idOrders, String idResto, String namaResto, double jarakResto) {
        this.idOrders = idOrders;
        this.idResto = idResto;
        this.namaResto = namaResto;
        this.jarakResto = jarakResto;
    }

    // Methods
    public void addOrderDetails(String idDish, String namaDish, double hargaDish, int totalPesan) {
        listOrderDetails.add(new OrderDetail(idDish, namaDish,hargaDish, totalPesan));
    }

    public double hitungTotalBiaya() {
        int index = 0;
        double totalBiaya = 0;
        for (OrderDetail element : listOrderDetails){
            OrderDetail orderDetails = listOrderDetails.get(index);
            totalBiaya += orderDetails.getTotalHarga();
            index++;
        }

        this.totalBiaya = totalBiaya;
        return totalBiaya;
    }
    public void showOrders() {
        System.out.println(" + ------------------------------------------------------------------------------------- + ");
        System.out.printf("|| ID Pesanan    : %-69s ||\n", idOrders);
        System.out.printf("|| ID Restoran   : %-69s ||\n", idResto);
        System.out.printf("|| Nama Restoran : %-69s ||\n", namaResto);
        System.out.printf("|| Jarak         : %-66.2f km ||\n", jarakResto);
        System.out.println(" + ------------------------------------------------------------------------------------- + ");
        showOrderDetails();
        System.out.printf("|| TOTAL SELURUH HARGA                                                  : Rp%-12.2f ||\n", hitungTotalBiaya());
    }

    public void showOrderDetails() {
        System.out.println(" + ------------------------------------------------------------------------------------- + ");
        System.out.println("|| ID MENU  |             NAMA PESANAN             | JML |     HARGA    |      TOTAL     ||");
        System.out.println(" + ------------------------------------------------------------------------------------- + ");
        int index = 0;
        for(OrderDetail element : listOrderDetails) {
            OrderDetail orderDetails = listOrderDetails.get(index);
            orderDetails.toString();
            index++;
        }
        System.out.println(" + ===================================================================================== + ");
    }

    public static void showOrdersFooter() {
        System.out.println(" + --------------------------------------------------------------- + ");
        System.out.println("||                  Tekan Enter untuk melanjutkan                  ||");
        System.out.println(" + =============================================================== + ");
    }

    // Order Details Static Nested Class
    static class OrderDetail extends Dish {
        // Variables
        private int totalPesan;
        private double totalHarga;

        //Constructor
        public OrderDetail() {
            super();
        }

        public OrderDetail(String idDish, String namaDish, double hargaDish, int totalPesan) {
            super(idDish, namaDish,hargaDish);
            this.totalPesan = totalPesan;
            totalHarga = totalPesan * hargaDish;
        }

        public String toString() {
            System.out.printf("|| %-9s| %-37s| %-4d| Rp%-11.2f| Rp%-13.2f||\n", getIdDish(), getNama(), getTotalPesan(), getHarga(), getTotalHarga());
            return null;
        }

        public int getTotalPesan() {
            return totalPesan;
        }

        public void setTotalPesan(int totalPesan) {
            this.totalPesan = totalPesan;
        }

        public double getTotalHarga() {
            return totalHarga;
        }

        public void setTotalHarga(double totalHarga) {
            this.totalHarga = totalHarga;
        }
    }

}

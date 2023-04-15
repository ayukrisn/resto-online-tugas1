import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Class Orders dan nested class OrderDetail
 *
 * Berisi variabel dan keperluan untuk order
 */
public class Orders {
    // Variables
    private String idOrders;
    private String idResto;
    private String namaResto;
    private String waktuOrder;
    private double jarakResto;
    private ArrayList<Orders.OrderDetail> listOrderDetails = new ArrayList<>();
    private Input keyboard = new Input();
    private double totalBiayaMakanan;
    private double biayaPengantaran;
    private double totalPembayaran;
    private double kembalian;

    public Orders () {
    }

    public Orders(String idOrders, String idResto, String namaResto, double jarakResto) {
        this.idOrders = idOrders;
        this.idResto = idResto;
        this.namaResto = namaResto;
        this.jarakResto = jarakResto;
        this.waktuOrder = getOrderTime();
    }

    // Methods

    /**
     * Method getOrderTime()
     * @return waktu pemesanan dalam bentuk string
     */
    public String getOrderTime() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss z");
        String orderTime = formatter.format(date);
        return orderTime;
    }

    /**
     * Method addOrderDetails() : menambahkan detail order ke dalam list
     *   Dipanggil di class Restaurant
     * @param idDish yang diterima
     * @param namaDish yang diterima
     * @param hargaDish yang diterima
     * @param totalPesan yang diterima
     */
    public void addOrderDetails(String idDish, String namaDish, double hargaDish, int totalPesan) {
        listOrderDetails.add(new OrderDetail(idDish, namaDish,hargaDish, totalPesan));
    }

    public void hitungBiayaPengantaran() {
        double biayaPengantaran = 0;
        if (jarakResto < 1) biayaPengantaran = 0;
        else if (jarakResto <= 1 && jarakResto >=3) biayaPengantaran = 5000;
        else if (jarakResto > 3) biayaPengantaran = (jarakResto / 3)*5000;

        this.biayaPengantaran = biayaPengantaran;
    }

    /**
     * Method hitungTotalBiaya() : menghitung total biaya pemesanan
     * @return total biaya
     */
    public double hitungTotalBiaya() {
        int index = 0;
        double totalBiaya = 0;
        for (OrderDetail element : listOrderDetails){ //menambahkan seluruh total biaya makanan
            OrderDetail orderDetails = listOrderDetails.get(index);
            totalBiaya += orderDetails.getTotalHarga();
            index++;
        }
        //menambahkan biaya pengantaran
        totalBiaya += biayaPengantaran;

        this.totalBiayaMakanan = totalBiaya;
        return totalBiaya;
    }

    /**
     * Method: showOrdersandPayment()
     * Menunjukkan order dan riwayat pembayaran
     */
    public void showOrdersandPayment() {
        showOrders();
        showPayment();
    }

    /**
     * Method: showOrders()
     * Menunjukkan order dengan showOrderDetails();
     */
    public void showOrders() {
        System.out.println(" + ------------------------------------------------------------------------------------- + ");
        System.out.printf("|| ID Pesanan      : %-67s ||\n", idOrders);
        System.out.printf("|| ID Restoran     : %-67s ||\n", idResto);
        System.out.printf("|| Nama Restoran   : %-67s ||\n", namaResto);
        System.out.printf("|| Jarak           : %-5.2f km                                                            ||\n", jarakResto);
        System.out.printf("|| Waktu Pemesanan : %-67s ||\n", waktuOrder);
        // System.out.println(" + ------------------------------------------------------------------------------------- + ");
        showOrderDetails();
        hitungBiayaPengantaran();
        System.out.printf("|| BIAYA PENGANTARAN                                                    : Rp%-12.2f ||\n", biayaPengantaran);
        System.out.printf("|| TOTAL SELURUH HARGA                                                  : Rp%-12.2f ||\n", hitungTotalBiaya());
    }

    /**
     * Method: showOrderDetails() : menunjukkan detail order
     */
    public void showOrderDetails() {
        System.out.println("|| ------------------------------------------------------------------------------------- || ");
        System.out.println("|| ID MENU  |             NAMA PESANAN             | JML |     HARGA    |      TOTAL     ||");
        System.out.println("|| ------------------------------------------------------------------------------------- ||");
        int index = 0;
        for(OrderDetail element : listOrderDetails) {
            OrderDetail orderDetails = listOrderDetails.get(index);
            orderDetails.toString();
            index++;
        }
        System.out.println(" + ===================================================================================== + ");
    }

    public static void payOrdersHeader() {
        System.out.println(" + ===================================================================================== + ");
        System.out.println("||                                    PEMBAYARAN PEMESANAN                                ||");
        System.out.println("||                                       Customer Menu                                   ||");
    }

    public static void payOrdersFooter() {
        System.out.println(" + ------------------------------------------------------------------------------------- + ");
        System.out.println("||                      Masukkan nominal pembayaran Anda di bawah ini                    ||");
        System.out.println(" + ===================================================================================== + ");
    }
    public static void showOrderLogsHeader() {
        System.out.println(" + ===================================================================================== + ");
        System.out.println("||                                      RIWAYAT PEMESANAN                                ||");
        System.out.println("||                                       Customer Menu                                   ||");
    }
    public static void showOrdersFooter() {
        System.out.println(" + ------------------------------------------------------------------------------------- + ");
        System.out.println("||                              Tekan Enter untuk melanjutkan                            ||");
        System.out.println(" + ===================================================================================== + ");
    }

    /**
     * Method: payment()
     * Melakukan pembayaran setelah pemesanan selesai dilakukan
     */
    public void payment() {
        boolean hasPaid = false;
        while (!hasPaid){
            totalPembayaran = 0;
            System.out.print("    Masukkan nominal pembayaran: Rp");
            totalPembayaran = keyboard.getDouble();
            if (totalPembayaran >= totalBiayaMakanan) {
                kembalian = totalPembayaran - totalBiayaMakanan;
                System.out.printf("    Kembalian: Rp%.2f\n", kembalian);
                hasPaid = true;
            } else if (totalPembayaran < totalBiayaMakanan) {
                System.out.println("    Maaf, nominal yang kamu masukkan kurang.");
                System.out.println("    Mohon lakukan pembayaran lagi.");
            }
        }

    }

    /**
     * Method: showPayment()
     * Menunjukkan total pembayaran dan kembalian
     */
    public void showPayment() {
        System.out.printf("|| TOTAL PEMBAYARAN                                                     : Rp%-12.2f ||\n", totalPembayaran);
        System.out.printf("|| TOTAL KEMBALIAN                                                      : Rp%-12.2f ||\n", kembalian);
    }

    /**
     * Order Details static nested class
     */
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

        public double getTotalHarga() {
            return totalHarga;
        }

    }

}

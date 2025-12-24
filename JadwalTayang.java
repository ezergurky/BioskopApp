import java.time.LocalDate;
import java.time.LocalTime;

public class JadwalTayang {
    // Tempat menyimpan tanggal dan jam film
    LocalDate tanggal;
    LocalTime jam;
    // Tempat penyimpanan Total kursi dan kursi yang masih tersedia
    int totalKursi = 30;
    int kursiTersedia = 30;

    public JadwalTayang(LocalDate tanggal, LocalTime jam) {
        this.tanggal = tanggal;
        this.jam = jam;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public LocalTime getJam() {
        return jam;
    }

    public int getKursiTersedia() {
        return kursiTersedia;
    }

    public boolean pesanKursi(int jumlah) {
        if(jumlah <= kursiTersedia) {
            kursiTersedia = kursiTersedia - jumlah;
            return true;
        } else return false;
    }

    public String toString() {
        return "Tanggal: " + tanggal + " | Jam: " + jam + " | Kursi Tersedia: " + kursiTersedia;
    }
}

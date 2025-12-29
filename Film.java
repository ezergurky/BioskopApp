import java.time.LocalDate;

public class Film {
    // Atribut film
    String judul;
    String genre;
    int durasi;
    double rating;
    double hargaTiket;

    // Array tempat menyimpan jadwal tayang film
    JadwalTayang[] jadwallist = new JadwalTayang[10];
    int jumlahJadwal = 0;

    // Diskon promo film
    double diskon = 0;
    LocalDate promoMulai;
    LocalDate promoSelesai;

    public Film(String judul, String genre, int durasi, double rating, double hargaTiket) {
        this.judul = judul;
        this.genre = genre;
        this.durasi = durasi;
        this.rating = rating;
        this.hargaTiket = hargaTiket;
    }

    public String getJudul() {
        return judul;
    }

    public String getGenre() {
        return genre;
    }

    public int getDurasi() {
        return durasi;
    }

    public double getRating() {
        return rating;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setDurasi(int durasi) {
        this.durasi = durasi;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public JadwalTayang[] getJadwalList() {
        return jadwallist;
    }

    public int getJumlahJadwal() {
        return jumlahJadwal;
    }
    
    public void tambahJadwal(JadwalTayang jadwal) {
        if (jumlahJadwal >= jadwallist.length) {
            System.out.println("Jadwal tayang penuh!");
            return;
        }

        jadwallist[jumlahJadwal] = jadwal;
        jumlahJadwal = jumlahJadwal + 1;
    }

    public void setPromo(LocalDate mulai, LocalDate selesai, double diskon) {
        this.promoMulai = mulai;
        this.promoSelesai = selesai;
        this.diskon = diskon;
    }

    public double getDiskon() {
        return diskon;
    }

    public double getHargaTiket() {
        return hargaTiket;
    }

    public void setHargaTiket(double hargaTiket) {
        this.hargaTiket = hargaTiket;
    }

    public boolean isPromoAktif() {
        if (promoMulai == null || promoSelesai == null) return false;
        LocalDate today = LocalDate.now();
        return !today.isBefore(promoMulai) && !today.isAfter(promoSelesai);
    }

    public String toString() {
        String promo = diskon > 0 ? " | Diskon: " + diskon + "%" : "";
        return "Judul: " + judul + " | Genre: " + genre + " | Durasi: " + durasi + " menit" + " | Rating: " + rating + " | Harga: Rp" + hargaTiket + promo;
    }
}
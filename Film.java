public class Film {
    String judul;
    String genre;
    int durasi;
    double rating;
    JadwalTayang[] jadwallist = new JadwalTayang[10];
    int jumlahJadwal = 0;
    double diskon = 0;

    public Film(String judul, String genre, int durasi, double rating) {
        this.judul = judul;
        this.genre = genre;
        this.durasi = durasi;
        this.rating = rating;
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

    public void setDiskon(double diskon) {
        this.diskon = diskon;
    }

    public double getDiskon() {
        return diskon;
    }

    public String toString() {
        return "Judul: " + judul + " | Genre: " + genre + " | Durasi: " + durasi + " menit | Rating: " + rating;
    }
}
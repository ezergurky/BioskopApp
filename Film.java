import java.util.ArrayList;

public class Film {
    String judul;
    String genre;
    int durasi;
    double rating;
    ArrayList<JadwalTayang> jadwallist = new ArrayList<>();

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

    public ArrayList<JadwalTayang> getJadwalList() {
        return jadwallist;
    }
    
    public void tambahJadwal(JadwalTayang jadwal) {
        jadwallist.add(jadwal);
    }

    public String toString() {
        return "Judul: " + judul + " | Genre: " + genre + " | Durasi: " + durasi + " menit | Rating: " + rating;
    }
}
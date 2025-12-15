import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class BioskopSystem {
    ArrayList<Film> daftarFilm = new ArrayList<>();
    Scanner in = new Scanner(System.in);

    public void menu() {
        int pilih;
        do {
            System.out.println("\n=== Aplikasi Bioskop ===");
            System.out.println("1. Tambah Film");
            System.out.println("2. Tampilkan Semua Film");
            System.out.println("3. Cari Film");
            System.out.println("4. Edit Film");
            System.out.println("5. Hapus Film");
            System.out.println("6. Tambah Promo");
            System.out.println("7. Pesan Kursi");
            System.out.println("0. Keluar");
            System.out.print("Pilih: ");
            pilih = in.nextInt();
            in.nextLine();

            switch(pilih) {
                case 1 -> tambahFilm();
                case 2 -> tampilFilm();
                case 3 -> cariFilm();
            }
        } while(pilih != 0);
    }

    public void tambahFilm() {
        System.out.print("Judul: ");
        String judul = in.nextLine();
        System.out.print("Genre: ");
        String genre = in.nextLine();
        System.out.print("Durasi (menit): ");
        int durasi = in.nextInt();
        System.out.print("Rating: ");
        double rating  = in.nextDouble();
        in.nextLine();

        Film film = new Film(judul, genre, durasi, rating);
        daftarFilm.add(film);
        System.out.println("Film berhasil ditambahkan!");
    }

    public void tampilFilm() {
        for(Film f : daftarFilm) {
            System.out.println(f);
            for(JadwalTayang j : f.getJadwalList()) {
                System.out.println(" " + j);
            }
        }
    }

    public void cariFilm() {
        System.out.print("Cari berdasarkan (judul/genre/tanggal): ");
        String kriteria = in.nextLine().toLowerCase();

        if (kriteria.equals("judul")) {
            System.out.print("Masukkan judul: ");
            String judul = in.nextLine().toLowerCase();
            daftarFilm.stream()
                .filter(f -> f.getJudul().toLowerCase().contains(judul))
                .forEach(System.out::println);

        } else if (kriteria.equals("genre")) {
            System.out.print("Masukkan genre: ");
            String genre = in.nextLine().toLowerCase();
            daftarFilm.stream()
                .filter(f -> f.getGenre().toLowerCase().contains(genre))
                .forEach(System.out::println);

        } else if (kriteria.equals("tanggal")) {
            System.out.print("Masukkan tanggal (YYYY-MM-DD): ");
            LocalDate tanggal = LocalDate.parse(in.nextLine());
            for (Film f : daftarFilm) {
                for (JadwalTayang j : f.getJadwalList()) {
                    if (j.getTanggal().equals(tanggal)) {
                        System.out.println(f + " | " + j);
                    }
                }
            }
        }
    }
}

import java.time.LocalDate;
import java.util.Scanner;

public class BioskopSystem {
    Film[] daftarFilm = new Film[100];
    int jumlahFilm = 0;
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
            System.out.println("========================");
            System.out.print("Pilih: ");
            pilih = in.nextInt();
            in.nextLine();

            switch(pilih) {
                case 1 -> tambahFilm();
                case 2 -> tampilFilm();
                case 3 -> cariFilm();
                case 4 -> editFilm();
                case 5 -> hapusFilm();
                case 6 -> tambahPromo();
                case 7 -> pesanKursi();
            }
        } while(pilih != 0);
    }

    public void tambahFilm() {
        if(jumlahFilm >= daftarFilm.length) {
            System.out.println("Data film penuh!");
            return;
        }

        System.out.print("Judul: ");
        String judul = in.nextLine();
        System.out.print("Genre: ");
        String genre = in.nextLine();
        System.out.print("Durasi (menit): ");
        int durasi = in.nextInt();
        System.out.print("Rating: ");
        double rating  = in.nextDouble();
        in.nextLine();

        daftarFilm[jumlahFilm] = new Film(judul, genre, durasi, rating);
        jumlahFilm = jumlahFilm + 1;

        System.out.println("Film berhasil ditambahkan!");
    }

    public void tampilFilm() {
        if (jumlahFilm == 0) {
            System.out.println("Belum ada data film.");
            return;
        }

        for (int i = 0; i < jumlahFilm; i++) {
            System.out.println("---------------------------------");
            System.out.println(daftarFilm[i]);
        }
        System.out.println("---------------------------------");
    }

    public void cariFilm() {
        System.out.print("Cari berdasarkan (judul/genre/tanggal): ");
        String kriteria = in.nextLine().toLowerCase();

        if (kriteria.equals("judul")) {
            System.out.print("Masukkan judul: ");
            String cari = in.nextLine().toLowerCase();

            for (int i = 0; i < jumlahFilm; i++) {
                if (daftarFilm[i].getJudul().toLowerCase().contains(cari)) {
                    System.out.println(daftarFilm[i]);
                }
            }

        } else if (kriteria.equals("genre")) {
            System.out.print("Masukkan genre: ");
            String cari = in.nextLine().toLowerCase();

            for (int i = 0; i < jumlahFilm; i++) {
                if (daftarFilm[i].getGenre().toLowerCase().contains(cari)) {
                    System.out.println(daftarFilm[i]);
                }
            }

        } else if (kriteria.equals("tanggal")) {
            System.out.print("Masukkan tanggal (YYYY-MM-DD): ");
            LocalDate tanggal = LocalDate.parse(in.nextLine());

            for (int i = 0; i < jumlahFilm; i++) {
                Film f = daftarFilm[i];

                for (int j = 0; j < f.getJumlahJadwal(); j++) {
                    JadwalTayang jt = f.getJadwalList()[j];

                    if (jt.getTanggal().equals(tanggal)) {
                        System.out.println(f);
                        System.out.println("  Jadwal: " + jt);
                    }
                }
            }
        }
    }

    public void editFilm() {
        System.out.print("Masukkan judul film yang ingin diedit: ");
        String cari = in.nextLine().toLowerCase();

        for (int i = 0; i < jumlahFilm; i++) {
            if (daftarFilm[i].getJudul().toLowerCase().equals(cari)) {

                System.out.print("Judul baru   : ");
                String judul = in.nextLine();
                System.out.print("Genre baru   : ");
                String genre = in.nextLine();
                System.out.print("Durasi baru  : ");
                int durasi = in.nextInt();
                System.out.print("Rating baru  : ");
                double rating = in.nextDouble();
                in.nextLine();

                daftarFilm[i] = new Film(judul, genre, durasi, rating);

                System.out.println("Film berhasil diperbarui!");
                return;
            }
        }
        System.out.println("Film tidak ditemukan.");
    }

    public void hapusFilm() {
        System.out.print("Masukkan judul film yang ingin dihapus: ");
        String cari = in.nextLine().toLowerCase();

        for (int i = 0; i < jumlahFilm; i++) {
            if (daftarFilm[i].getJudul().toLowerCase().equals(cari)) {

                for (int j = i; j < jumlahFilm - 1; j++) {
                    daftarFilm[j] = daftarFilm[j + 1];
                }

                daftarFilm[jumlahFilm - 1] = null;
                jumlahFilm = jumlahFilm - 1;

                System.out.println("Film berhasil dihapus.");
                return;
            }
        }
        System.out.println("Film tidak ditemukan.");
    }

    public void tambahPromo() {
        System.out.print("Judul film promo: ");
        String cari = in.nextLine().toLowerCase();

        for (int i = 0; i < jumlahFilm; i++) {
            if (daftarFilm[i].getJudul().toLowerCase().equals(cari)) {

                System.out.print("Diskon (%): ");
                double diskon = in.nextDouble();
                in.nextLine();

                daftarFilm[i].setDiskon(diskon);
                System.out.println("Promo berhasil ditambahkan!");
                return;
            }
        }
        System.out.println("Film tidak ditemukan.");
    }

    public void pesanKursi() {
        System.out.print("Judul film: ");
        String cari = in.nextLine().toLowerCase();

        for (int i = 0; i < jumlahFilm; i++) {
            Film f = daftarFilm[i];

            if (f.getJudul().toLowerCase().equals(cari)) {

                if (f.getJumlahJadwal() == 0) {
                    System.out.println("Belum ada jadwal tayang.");
                    return;
                }

                for (int j = 0; j < f.getJumlahJadwal(); j++) {
                    System.out.println((j + 1) + ". " + f.getJadwalList()[j]);
                }

                System.out.print("Pilih jadwal: ");
                int pilih = in.nextInt() - 1;
                System.out.print("Jumlah kursi: ");
                int jumlah = in.nextInt();
                in.nextLine();

                JadwalTayang jt = f.getJadwalList()[pilih];

                if (jt.pesanKursi(jumlah)) {
                    System.out.println("Pemesanan berhasil!");
                } else {
                    System.out.println("Kursi tidak mencukupi.");
                }
                return;
            }
        }
        System.out.println("Film tidak ditemukan.");
    }
}

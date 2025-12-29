import java.time.LocalDate;
import java.util.Scanner;
import java.time.LocalTime;

public class BioskopSystem {
    // Menyimpan daftar film maksimal 100
    Film[] daftarFilm = new Film[100];

    // Menyimpan jumlah film
    int jumlahFilm = 0;

    // Scanner untuk input dari pengguna
    Scanner in = new Scanner(System.in);

    public void menu() {
        int pilih;
        do {
            // Membersihkan layar setiap kali menu ditampilkan
            ClearConsole.clear();

            System.out.println("\n=== Aplikasi Bioskop ===");
            System.out.println("1. Tambah Film");
            System.out.println("2. Tampilkan Semua Film");
            System.out.println("3. Cari Film");
            System.out.println("4. Edit Film");
            System.out.println("5. Hapus Film");
            System.out.println("6. Tambah Promo");
            System.out.println("7. Pesan Kursi");
            System.out.println("8. Tambah Jadwal Tayang");
            System.out.println("9. Tampilkan Film Berdasarkan Genre");
            System.out.println("10. Tampilkan Jadwal Film Berdasarkan Tanggal");
            System.out.println("0. Keluar");
            System.out.println("========================");
            System.out.print("Pilih: ");
            pilih = in.nextInt();
            in.nextLine();

            // Mengarahkan pilihan menu ke method yang sudah ditentukan
            switch(pilih) {
                case 1 -> tambahFilm();
                case 2 -> tampilFilm();
                case 3 -> cariFilm();
                case 4 -> editFilm();
                case 5 -> hapusFilm();
                case 6 -> tambahPromo();
                case 7 -> pesanKursi();
                case 8 -> tambahJadwal();
                case 0 -> System.out.println("Terima kasih :)");
                default -> System.out.println("Pilihan tidak valid!");
            }

            // Pausa agar user bisa konfirmasi lanjut ketika selesai sebelum kembali ke menu
            if (pilih != 0) pause();
        } while(pilih != 0);
    }

    public void tambahFilm() {
        // Cek apakah array daftarFilm sudah penuh
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
        System.out.print("Harga tiket: ");
        double harga = in.nextDouble();
        in.nextLine();

        // Validasi rating sesuai standar dari 0 - 10
        if (rating < 0 || rating > 10) {
            System.out.println("Rating harus 0 - 10");
            return;
        }

        // Menambahkan objek film ke dalam array
        daftarFilm[jumlahFilm] = new Film(judul, genre, durasi, rating, harga);
        jumlahFilm = jumlahFilm + 1;

        System.out.println("Film berhasil ditambahkan!");
    }

    public void tampilFilm() {
        // Jika film tidak ada
        if(isFilmEmpty()) return;

        System.out.println("\n========= DAFTAR FILM =========");
        // Menampilan semua film yang tersedia
        for (int i = 0; i < jumlahFilm; i++) {
            Film f = daftarFilm[i];
            System.out.println((i + 1) + ") " + f.getJudul());
            System.out.println("   Genre     : " + f.getGenre());
            System.out.println("   Durasi    : " + f.getDurasi() + " menit");
            System.out.println("   Rating    : " + f.getRating() + "/10");
            System.out.println("   Harga     : Rp" + f.getHargaTiket());

            if (f.getDiskon() > 0) {
                System.out.println("Diskon    : " + f.getDiskon() + "%");
            }

            // Jika jadwal belum ada
            if (f.getJumlahJadwal() == 0) {
                System.out.println("   Jadwal    : - Belum ada jadwal -");
            } else {
                // Menampilkan jadwal dari film yang tersedia
                System.out.println("   Jadwal:");
                for (int j = 0; j < f.getJumlahJadwal(); j++) {
                    System.out.println("      * " + f.getJadwalList()[j].getTanggal() + " | " + f.getJadwalList()[j].getJam());
                }
            }

            System.out.println("---------------------------------------");
        }
    }

    public void cariFilm() {
        // Cek apakah film tidak ada
        if(isFilmEmpty()) return;

        // Menu pencarian berdasarkan kriteria tertentu
        System.out.print("Cari berdasarkan (judul/genre/tanggal): ");
        String kriteria = in.nextLine().toLowerCase();

        // Pencarian berdasarkan judul film
        if (kriteria.equals("judul")) {
            System.out.print("Masukkan judul: ");
            String cari = in.nextLine().toLowerCase();

            for (int i = 0; i < jumlahFilm; i++) {
                if (daftarFilm[i].getJudul().toLowerCase().contains(cari)) {
                    tampilkanFilmDenganJadwal(daftarFilm[i]);
                }
            } 

        // Pencarian berdasarkan genre film
        } else if (kriteria.equals("genre")) {
            System.out.print("Masukkan genre: ");
            String cari = in.nextLine().toLowerCase();

            for (int i = 0; i < jumlahFilm; i++) {
                if (daftarFilm[i].getGenre().toLowerCase().contains(cari)) {
                    tampilkanFilmDenganJadwal(daftarFilm[i]);
                }
            }
        // Pencarian berdasarkan tanggal film
        } else if (kriteria.equals("tanggal")) {
            System.out.print("Masukkan tanggal (YYYY-MM-DD): ");
            LocalDate tanggal = LocalDate.parse(in.nextLine());

            for (int i = 0; i < jumlahFilm; i++) {
                Film f = daftarFilm[i];

                for (int j = 0; j < f.getJumlahJadwal(); j++) {
                    JadwalTayang jt = f.getJadwalList()[j];

                    if (jt.getTanggal().equals(tanggal)) {
                        tampilkanFilmDenganJadwal(daftarFilm[i]);
                    }
                }
            }
        }
    }

    public void editFilm() {
        // Cek apakah film tidak ada
        if(isFilmEmpty()) return;

        System.out.print("Masukkan judul film yang ingin diedit: ");
        String cari = in.nextLine().toLowerCase();

        for (int i = 0; i < jumlahFilm; i++) {
            Film f = daftarFilm[i];

            if (daftarFilm[i].getJudul().toLowerCase().equals(cari)) {

                System.out.print("Judul baru   : ");
                f.setJudul(in.nextLine());

                System.out.print("Genre baru   : ");
                f.setGenre(in.nextLine());

                System.out.print("Durasi baru  : ");
                f.setDurasi(in.nextInt());

                System.out.print("Rating baru  : ");
                f.setRating(in.nextDouble());
                in.nextLine();

                System.out.println("Film berhasil diperbarui!");

                return;
            }
        }
        System.out.println("Film tidak ditemukan.");
    }

    public void hapusFilm() {
        // Cek apakah film tidak ada
        if(isFilmEmpty()) return;

        System.out.print("Masukkan judul film yang ingin dihapus: ");
        String cari = in.nextLine().toLowerCase();

        for (int i = 0; i < jumlahFilm; i++) {
            if (daftarFilm[i].getJudul().toLowerCase().equals(cari)) {
                System.out.println("\nFilm ditemukan:");
                tampilkanFilmDenganJadwal(daftarFilm[i]);

                System.out.print("Yakin ingin menghapus film ini? (y/n): ");
                String confirm = in.nextLine().toLowerCase();

                if (!confirm.equals("y")) {
                    System.out.println("Penghapusan dibatalkan.");
                    return;
                }

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
        // Cek apakah film tidak ada
        if(isFilmEmpty()) return;

        System.out.print("Judul film promo: ");
        String cari = in.nextLine().toLowerCase();

        for (int i = 0; i < jumlahFilm; i++) {
            Film f = daftarFilm[i];

            if (daftarFilm[i].getJudul().toLowerCase().equals(cari)) {

                System.out.print("Diskon (%): ");
                double diskon = in.nextDouble();
                in.nextLine();

                if (diskon < 0 || diskon > 100) {
                    System.out.println("Diskon harus 0 - 100%");
                    return;
                }

                System.out.print("Tanggal Mulai Promo (YYYY-MM-DD): ");
                LocalDate mulai = LocalDate.parse(in.nextLine());

                System.out.print("Tanggal Selesai Promo (YYYY-MM-DD): ");
                LocalDate selesai = LocalDate.parse(in.nextLine());

                f.setPromo(mulai, selesai, diskon);
                
                System.out.println("Promo berhasil ditambahkan!");
                System.out.println("Promo aktif dari " + mulai + " sampai " + selesai);
                return;
            }
        }
        System.out.println("Film tidak ditemukan.");
    }

    public void pesanKursi() {
        // Cek apakah film tidak ada
        if(isFilmEmpty()) return;

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
                    System.out.println("[" + (j + 1) + "] " + f.getJadwalList()[j]);
                }

                System.out.print("Pilih jadwal: ");
                int pilih = in.nextInt() - 1;
                System.out.print("Jumlah kursi: ");
                int jumlah = in.nextInt();
                in.nextLine();

                if (jumlah <= 0) {
                    System.out.println("Jumlah kursi tidak valid.");
                    return;
                }

                if (pilih < 0 || pilih >= f.getJumlahJadwal()) {
                    System.out.println("Pilihan jadwal tidak valid.");
                    return;
                }

                JadwalTayang jt = f.getJadwalList()[pilih];

                // Logika jika kursi mencukupi atau tidak
                if (jt.pesanKursi(jumlah)) {
                    // Detail pemesanan kursi bioskop
                    double hargaAwal = f.getHargaTiket() * jumlah;
                    double potongan = 0;

                    if (f.isPromoAktif()) {
                        potongan = hargaAwal * (f.getDiskon() / 100);
                        System.out.println("Promo berlaku! Diskon: " + f.getDiskon() + "%");
                    } else {
                        System.out.println("Promo tidak aktif untuk tanggal ini.");
                    }

                    double totalBayar = hargaAwal - potongan;

                    System.out.println("\n=== Detail Pemesanan ===");
                    System.out.println("Film       : " + f.getJudul());
                    System.out.println("Jadwal     : " + jt.getTanggal() + " | " + jt.getJam());
                    System.out.println("Jumlah     : " + jumlah + " kursi");
                    System.out.println("Harga      : Rp" + f.getHargaTiket());
                    System.out.println("Diskon     : " + f.getDiskon() + "%");
                    System.out.println("--------------------------");
                    System.out.println("Total Bayar: Rp" + totalBayar);
                    System.out.println("==========================");
                } else {
                    System.out.println("Kursi tidak mencukupi.");
                }
                return;
            }
        }
        System.out.println("Film tidak ditemukan.");
    }

    public void tambahJadwal() {
        if(isFilmEmpty()) return;

        System.out.print("Judul film: ");
        String cari = in.nextLine().toLowerCase();

        for (int i = 0; i < jumlahFilm; i++) {
            Film f = daftarFilm[i];

            if (f.getJudul().toLowerCase().equals(cari)) {

                System.out.print("Tanggal (YYYY-MM-DD): ");
                LocalDate tanggal = LocalDate.parse(in.nextLine());

                System.out.print("Jam (HH:MM): ");
                LocalTime jam = LocalTime.parse(in.nextLine());

                f.tambahJadwal(new JadwalTayang(tanggal, jam));
                System.out.println("Jadwal tayang berhasil ditambahkan!");

                tampilkanFilmDenganJadwal(daftarFilm[i]);
                return;
            }
        }
        System.out.println("Film tidak ditemukan.");
    }

    private void tampilkanFilmDenganJadwal(Film f) {
        System.out.println("\n========= DATA FILM =========");
        System.out.println("Judul     : " + f.getJudul());
        System.out.println("Genre     : " + f.getGenre());
        System.out.println("Durasi    : " + f.getDurasi() + " menit");
        System.out.println("Rating    : " + f.getRating() + "/10");
        System.out.println("Harga     : Rp" + f.getHargaTiket());

        // jika ada diskon
        if (f.getDiskon() > 0) {
            System.out.println("Diskon    : " + f.getDiskon() + "%");
        }

        // jadwal tayang
        if (f.getJumlahJadwal() == 0) {
            System.out.println("Jadwal    : - Belum ada jadwal tayang -");
        } else {
            System.out.println("Jadwal    :");
            for (int j = 0; j < f.getJumlahJadwal(); j++) {
                JadwalTayang jd = f.getJadwalList()[j];
                System.out.println("   * " + jd.getTanggal() + " | " + jd.getJam());
            }
        }

        System.out.println("---------------------------------------"); // pemisah film
    }

    private boolean isFilmEmpty() {
        if (jumlahFilm == 0) {
            System.out.println("Belum ada data film.");
            return true;
        }
        return false;
    }

    public void pause() {
        System.out.println("\nTekan ENTER untuk lanjut...");
        in.nextLine();
    }
}

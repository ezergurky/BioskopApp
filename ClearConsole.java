public class ClearConsole {
    // Membersihkan tampilan console dengan ANSI escape code
    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
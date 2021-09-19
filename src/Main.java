import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        // Minta baris dan kolom
        int b = sc.nextInt();
        int k = sc.nextInt();
        // Membuat objek matriks dengan nama m dengan baris b dan kolom k
        Matriks m = new Matriks(b, k);
        m.isiMatriks();
        m.displayMatriks();
    }
}

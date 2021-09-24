import java.util.*;
public class MenuInvers {
    static Scanner sc = new Scanner(System.in);
    public static void submenuInvers(){
        int pilihan;
        System.out.println("Pilih metode invers:");
        System.out.println("1. Metode OBE");
        System.out.println("2. Metode Adjoin");
        System.out.print("masukkan pilihan anda(1 atau 2) : ");
        pilihan = sc.nextInt();
        System.out.print("masukkan matriks, mau dari terminal(1) atau dari file(2)?\n->");
        int pilihanInputMatriks;
        pilihanInputMatriks = sc.nextInt();
        int b;
        Matriks m;
        // Kalo input dari console
        if (pilihanInputMatriks == 1){
            System.out.println("Input jumlah baris matriks (jumlah kolom = baris)");
            b = sc.nextInt();
            m = new Matriks(b, b);
            System.out.println("Input isi matriks");
            m.isiMatriks();
            if (pilihan == 1) {
                m.inversMatriksOBE();
            }
        }
    }
}

import java.util.*;
public class MenuInvers {
    static Scanner sc = new Scanner(System.in);
    public static void submenuInvers(){
        int pilihan, pilihanInputMatriks;
        System.out.println("Pilih metode invers:");
        System.out.println("1. Metode OBE");
        System.out.println("2. Metode Adjoin");
        // cek input pilihan
        pilihan = Main.RobustIntInput(1, 2);
        // input cara input
        System.out.println("Pilih cara input matriks:");
        System.out.println("1. Dari console");
        System.out.println("2. Dari file");
        pilihanInputMatriks = Main.RobustIntInput(1, 2);
        
        int b; // baris
        Matriks m;
        // Kalo input dari console
        if (pilihanInputMatriks == 1){
            System.out.println("Input jumlah baris matriks (jumlah kolom = baris):");
            System.out.print("->");
            b = sc.nextInt();
            m = new Matriks(b, b);
            System.out.println("Input isi matriks");
            m.isiMatriks();
            if (m.Baris() == m.Kolom()){
                if (pilihan == 1) {
                    m.inversMatriksOBE();
                } else if(pilihan == 2){
                    m.inversMatriksAdj(m);
                }
                m.konfirmOutputkeFile(3, 0, "dummy");
            } else {
                System.out.println("Matriks tidak berbentuk square sehingga tidak memiliki balikan.");
            }
            
        } else if (pilihanInputMatriks == 2) {
            System.out.println("Masukkan nama file yang akan dibaca: ");
            String namaFile = sc.nextLine();
            m = new Matriks(Matriks.FileRow(namaFile), Matriks.FileColumn(namaFile));
            m.ReadMatriksFromFile(namaFile);
            if (m.Baris() == m.Kolom()){
                if (pilihan == 1) {
                    m.inversMatriksOBE();
                } else if(pilihan == 2){
                    m.inversMatriksAdj(m);
                }
                m.konfirmOutputkeFile(3, 0, "dummy");
            } else {
                System.out.println("Matriks tidak berbentuk square sehingga tidak memiliki balikan.");
            }
        }
    }
}

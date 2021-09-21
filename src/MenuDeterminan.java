import java.util.*;
public class MenuDeterminan{

    static Scanner sc = new Scanner(System.in); 
    public static void SubmenuDeterminan(){
        int pilihan;
        System.out.println("Pilih submenu determinan");
        System.out.println("1. Metode reduksi");
        System.out.println("2. Metode kofaktor");
        System.out.print("masukkan pilihan anda(1 atau 2) : ");
        pilihan = sc.nextInt();
        // masukkan matriks, bukan matriks augmented
        System.out.print("masukkan matriks, mau dari terminal(1) atau dari file(2)?\n->");
        int pilihanInputMatriks;
        pilihanInputMatriks = sc.nextInt();
        int N;
        Matriks m;
        if(pilihanInputMatriks == 1){
            System.out.print("masukkan ukuran matriks (bukan augmented): ");
            N = sc.nextInt();
            m = new Matriks(N, N);
            m.isiMatriks();
        }
        else{//ini harusnya dari file, cuman blom siap aja fungsinya
            m = new Matriks(4, 4);
        }

        //pastikan N square dan bukan augmented!

        if (pilihan == 1){ //hitung determinan dengan OBE  
            System.out.println("matriks setelah di lakukan OBE:");
            m = FungsiDeterminan.detReduksiOBE(m);
            m.displayMatriks();
            System.out.print("Determinannya: ");
            double hasil  = 1;
            for(int b = 0;b< m.Baris();b++){
                System.out.print(m.Isi(b,b));
                if (b < m.Baris() - 1){
                    System.out.print(" * ");
                }
                else{
                    System.out.print(" = ");
                }
                hasil = hasil * m.Isi(b,b);
            }
            System.out.print(hasil);
        }
        if(pilihan == 2){ //hitung dengan cofactor
            Matriks mCof = new Matriks(m.Baris(), m.Kolom());
            mCof = FungsiDeterminan.cofactor(m);
            System.out.println("berikut matriks cofactornya:");
            mCof.displayMatriks();
            System.out.println("mau menggunakan cofactor baris(1) atau kolom(2)?");
            int pilihanbk = sc.nextInt();
            
        }
    }
}
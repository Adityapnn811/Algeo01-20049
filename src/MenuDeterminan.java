import java.util.*;
public class MenuDeterminan{

    static Scanner sc = new Scanner(System.in); 
    public static void SubmenuDeterminan(){
        int pilihan;
        System.out.println("Pilih submenu determinan");
        System.out.println("1. Metode reduksi");
        System.out.println("2. Metode kofaktor");
        System.out.println("masukkan pilihan anda(1 atau 2): ");
        pilihan = Main.RobustIntInput(1, 2);
        // masukkan matriks, bukan matriks augmented
        System.out.print("masukkan matriks, mau dari terminal(1) atau dari file(2)?\n");
        int pilihanInputMatriks = Main.RobustIntInput(1, 2);
        int N;
        Matriks m;
        if(pilihanInputMatriks == 1){
            System.out.print("masukkan ukuran matriks (bukan augmented): ");
            N = Main.RobustIntInput(1, 10000);
            m = new Matriks(N, N);
            m.isiMatriks();
        }
        else{//ini harusnya dari file, cuman blom siap aja fungsinya
            System.out.print("masukkan nama file\n->");
            String filename = sc.nextLine();
            int baris = Matriks.FileRow(filename);
            int kolom = Matriks.FileColumn(filename);
            m = new Matriks(baris, kolom);
            m = Matriks.ReadMatriksFromFile(filename, baris, kolom);
            m.ubahKolom(m.Kolom() - 1);
        }
        
        if(m.Kolom() != m.Baris()){
            System.out.println("matriks tidak square, tidak bisa hitung determinan");
        }
        else{
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
                if (pilihanbk == 1){ //pake baris start
                    System.out.println("mau baris ke berapa(mulai dari 0):");
                    int bariske = sc.nextInt();
                    System.out.print("determinan: = ");
                    double hasil = 0;
                    for(int k = 0;k < mCof.Kolom();k++){
                        System.out.print(mCof.Isi(bariske, k));
                        System.out.print("*");
                        System.out.print(m.Isi(bariske, k));
                        if (k < mCof.Kolom() -1 ){
                            System.out.print(" + ");
                        }
                        else{
                            System.out.print(" = ");
                        }
                        hasil = hasil + (mCof.Isi(bariske,k)*m.Isi(bariske, k));
                    }
                    System.out.println(hasil);
                }//pake baris end
                if (pilihanbk == 2){ //pake kolom start
                    System.out.println("mau kolom ke berapa(mulai dari 0):");
                    int kolomke = sc.nextInt();
                    System.out.print("determinan: = ");
                    double hasil = 0;
                    for(int b = 0;b < mCof.Baris();b++){
                        System.out.print(mCof.Isi(b, kolomke));
                        System.out.print("*");
                        System.out.print(m.Isi(b, kolomke));
                        if (b < mCof.Baris() -1 ){
                            System.out.print(" + ");
                        }
                        else{
                            System.out.print(" = ");
                        }
                        hasil = hasil + (mCof.Isi(b,kolomke)*m.Isi(b, kolomke));
                    }
                    System.out.println(hasil);
                }//pake kolom end
            }
        }
    }
}
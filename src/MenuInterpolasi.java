import java.util.Scanner;
import java.lang.Math;
public class MenuInterpolasi {
    static Scanner sc = new Scanner(System.in);
    public static void Interpolasi(){
        System.out.println("Input dari terminal(1) atau file(2)?");
        int pilihan = Main.RobustIntInput(1, 2);
        Matriks m;
        if (pilihan == 1){
            System.out.print("masukkan nilai N, yaitu jumlah titik:\n");
            int n = Main.RobustIntInput(1, 10000);
            m = new Matriks(n, n+1);
            System.out.format("masukkan nilai (x,y) sebanyak %dx:\n", n);
            for(int b = 0;b < n;b++){
                Double x = 0.0;
                Double y = 0.0;
                x = sc.nextDouble();
                y = sc.nextDouble();
                for(int k = 0;k < n;k++){
                    m.ubahIsi(b, k, Math.pow(x, k));
                }
                m.ubahIsi(b, n, y);
            }
        }//end of pilihan 1
        else{
            System.out.print("masukkan nama file\n->");
            String filename = sc.nextLine();
            int baris = Matriks.FileRow(filename);
            m = new Matriks(baris, baris + 1);
            Matriks mfile = new Matriks(baris, 2);
            mfile.ReadMatriksFromFile(filename);
            for(int b = 0;b< mfile.Baris();b++){
                Double x = mfile.Isi(b, 0); 
                Double y = mfile.Isi(b, 1); 
                for(int k = 0;k< mfile.Baris();k++){
                    m.ubahIsi(b, k, Math.pow(x, k));
                }
                m.ubahIsi(b, mfile.Baris(), y);
            }
        }//end of pilihan 2
        //waktunya gauss stuff

        m.OBEGaussJordan(m.Baris(), m.Kolom());

        String line = "";
        System.out.print("p(x) = ");
        line += "p(x) = ";
        double temp = 0;
        for(int b = 0;b< m.Baris();b++){
            temp = m.Isi(b, m.Kolom() - 1);
            if(temp != 0){
                System.out.format("%.2f",temp);
                line += temp;
                if( b != 0){
                    System.out.format("*x^(%d)", b);
                    line += "*x^" + b;
                }
                if(b< m.Baris()-1){
                    System.out.print(" + ");
                    line += " + ";
                }
            }
        }
        System.out.println("");
        line += "";
        m.konfirmOutputkeFile(4, 0, line);
    }
}
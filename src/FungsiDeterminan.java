public class FungsiDeterminan {
    private static Matriks pengubahOBE(Matriks m, int BarisDiubah, int BarisPengubah, double ratio){
        for(int k = 0;k < m.Kolom(); k++){
            double newValue = m.Isi(BarisDiubah, k) - (ratio*m.Isi(BarisPengubah,k));
            m.ubahIsi(BarisDiubah, k, newValue);
        }
        return m;
    }
    public static Matriks detReduksiOBE(Matriks m){
        // menerima matiks m, dan mereturn m yang telah mengalami OBE
        // prekondisi matriks square 
        for(int bk = 0;bk< m.Baris();bk++){
            for(int b = bk+1;b < m.Baris();b++){
                if (m.Isi(b, bk) != 0){
                    double ratio = m.Isi(b, bk)/m.Isi(bk,bk);
                    m = pengubahOBE(m, b, bk, ratio);
                }
            }
        }
        return m;
    }
    public static Matriks ditutupi(Matriks m, int btutup, int ktutup){
        // mmeberikan Matriks yang sudah ditutupi di baris b dan kolom k
        Matriks temp = new Matriks(m.Baris() -1 , m.Kolom() -1);
        int bIsi,kIsi;
        for(int b = 0; b < temp.Baris();b++){
            for(int k = 0 ;k < temp.Kolom();k++){
                if (b >= btutup){
                    bIsi = b+1;
                }
                else
                    bIsi = b;
                if(k >= ktutup){
                    kIsi = k+1;
                }
                else
                    kIsi = k;
                temp.ubahIsi(b, k, m.Isi(bIsi, kIsi));
            }
        }
        return temp;
    }
    public static Matriks cofactor(Matriks m){
        // prekondisi, matriks m bentuknya square(baris dan kolomnya sama)
        Matriks mCofactor = new Matriks(m.Baris(),m.Kolom());
        Matriks temp = new Matriks(m.Baris() - 1, m.Kolom() - 1); //untuk menyimpan array 'yang ditutupi'
        double nilai;
        for(int b = 0;b< m.Baris();b++){
            for(int k = 0;k < m.Kolom();k++){
                nilai = 1;
                // definisikan nilai sebagai determinan dari array yang sudah ditutupi, lengkap dengan plus minusnya
                temp = ditutupi(m,b,k);
                temp = detReduksiOBE(temp);
                for(int bk = 0;bk < temp.Baris();bk++){
                    nilai = nilai* temp.Isi(bk, bk);
                }
                if(b+k % 2 != 0){
                    nilai = nilai* -1;
                }

                mCofactor.ubahIsi(b, k, nilai);
            }
        }
        return mCofactor;
    }
}

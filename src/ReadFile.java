public class ReadFile{
    public static void main(String[] args){
        String filename = "filename.txt";
        int baris = Matriks.FileRow(filename);
        int kolom = Matriks.FileColumn(filename);
        System.out.format("baris = %d, kolom = %d\n", baris, kolom);
        Matriks mar = new Matriks(baris, kolom);
        mar = Matriks.ReadMatriksFromFile(filename, baris, kolom);
        System.out.println("aman");
        mar.displayMatriks();
    }
}

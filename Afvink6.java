import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Afvink6 {

    public static HashMap<String, String> fastahash = new HashMap<>();

    public static void main(String[] args) {

        JFileChooser fc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        System.out.println("Voer een fasta bestand in:");
        int returnValue = fc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fc.getSelectedFile();
            String bestandslocatie = selectedFile.getAbsolutePath();

            File bestand = new File(bestandslocatie);
            Scanner bestandlezer = null;
            try {
                bestandlezer = new Scanner(bestand);
            } catch (FileNotFoundException e) {
                System.out.println("Bestand is niet gevonden. Voer een nieuw bestand in.");
            }
            while (true) {
                assert bestandlezer != null;
                if (!bestandlezer.hasNextLine()) break;
                String header = bestandlezer.nextLine();
                String sequentie = bestandlezer.nextLine();
                fastahash.put(header, sequentie);
            }
        }

        for (String i : fastahash.keySet()) {
            String seq1 = fastahash.get(i);
            int lenseq1 = seq1.length();
            String sub1 = seq1.substring(lenseq1-3);

            for (String j : fastahash.keySet()) {
                if (!i.equals(j)) {
                    String seq2 = fastahash.get(j);
                    String sub2 = seq2.substring(0, 3);

                    if (sub1.equals(sub2)) {
                        System.out.println("Gevonden overlap: " + sub1);
                        System.out.println(i + " eind " + seq1 + " met " + j + " begin " + seq2 + ".\n");
                    }
                }
            }
        }
    }
}

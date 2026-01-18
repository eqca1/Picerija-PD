package picapica;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

public class DarbsArFailu {

    private static final String AKTIVIE = "pasutijumi/aktivie.txt";
    private static final String JAUNODOTIE = "pasutijumi/pabeigtie.txt";


    public static void saglabatFaila(String dati, boolean vaiAktivs) {
        String fails = vaiAktivs ? AKTIVIE : JAUNODOTIE;
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fails, true))) {
            bw.write(dati);
            bw.newLine();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Kļūda saglabājot failā: " + e.getMessage(), 
                    "Sistēmas kļūda", JOptionPane.ERROR_MESSAGE);
        }
    }



    public static String lasitNoFaila(boolean vaiAktivs) {
        String fails = vaiAktivs ? AKTIVIE : JAUNODOTIE;
        StringBuilder saturs = new StringBuilder();
        File f = new File(fails);

        if (!f.exists()) return "Saraksts ir tukšs.";

        try (BufferedReader br = new BufferedReader(new FileReader(fails))) {
            String rinda;
            while ((rinda = br.readLine()) != null) {
                saturs.append(rinda.replace(";", " | ")).append("\n");
            }
        } catch (IOException e) {
            return "Kļūda lasot failu!";
        }

        return saturs.length() > 0 ? saturs.toString() : "Saraksts ir tukšs.";
    }


    public static void notiritFailu(boolean vaiAktivs) {
        String fails = vaiAktivs ? AKTIVIE : JAUNODOTIE;
        try (FileWriter fw = new FileWriter(fails, false)) {
            fw.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
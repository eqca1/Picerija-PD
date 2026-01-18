package picapica;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Picerija {
    
    private static void paraditSarakstu(boolean vaiAktivie) {
        String saturs = DarbsArFailu.lasitNoFaila(vaiAktivie);
        JTextArea textArea = new JTextArea(saturs);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new java.awt.Dimension(500, 300));
        
        JOptionPane.showMessageDialog(null, scrollPane, 
                vaiAktivie ? "Aktīvie pasūtījumi" : "Pabeigto pasūtījumu vēsture", 
                JOptionPane.PLAIN_MESSAGE);
    }

    private static String lasitIevadi(String txt) {
        String ievade;
        do {
        	ievade = (String) JOptionPane.showInputDialog(null, txt, "Ievāde", JOptionPane.INFORMATION_MESSAGE, null, null, "+371 21231231");
            if (ievade == null) return null;
            if (ievade.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Lauks nevar būt tukšs!", "Kļūda", JOptionPane.ERROR_MESSAGE);
            }
        } while (ievade.trim().isEmpty());
        return ievade;
    }
    private static String lasitTalruni(String txt) {
        String ievade;
        do {
            ievade = JOptionPane.showInputDialog(null, txt, "Ievāde", JOptionPane.INFORMATION_MESSAGE);
            if (ievade == null) return null;

            if (!ievade.matches("\\+371 2\\d{7}")) {
                JOptionPane.showMessageDialog(null,"Nepareizs formāts!\nPareizi: +371 2XXXXXXX","Kļūda",JOptionPane.ERROR_MESSAGE);
                continue;
            }

            return ievade;

        } while (true);
    }    
    
    private static void izveidotPasutijumu() {

    	String vards = lasitIevadi("Ievadiet klienta vārdu:");
        if (vards == null) return;

        String talrunis = lasitTalruni("Ievadiet klienta tālruni: (pm +371 21111111");
        if (talrunis == null) return;

        String[] veidi = {"Uz vietas", "Piegāde"};
        int veidaIzvele = JOptionPane.showOptionDialog(null, "Saņemšanas veids:", "Veids",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, veidi, veidi[0]);
        
        boolean uzVietas = (veidaIzvele == 0);
        String adrese = "-";
        if (!uzVietas) {
            adrese = lasitIevadi("Ievadiet piegādes adresi:");
            if (adrese == null) return;
        }

        String[] izmeri = {"Maza (7 EUR)", "Vidēja (10 EUR)", "Liela (13 EUR)"};
        int izmeraID = JOptionPane.showOptionDialog(null, "Izvēlieties izmēru:", "Picas izmērs",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, izmeri, izmeri[0]);
        if (izmeraID== -1) return;

        String[] piedevuSaraksts = {"Siers", "Bekons", "Sēnes", "Paprika", "Sīpoli"};
        ArrayList<String> izveletasPiedevas = new ArrayList<>();
        
        int velPiedevas;
        do {
            String p = (String) JOptionPane.showInputDialog(null, "Izvēlieties piedevu:", "Piedevas",
                    JOptionPane.QUESTION_MESSAGE, null, piedevuSaraksts, piedevuSaraksts[0]);
            if (p != null) izveletasPiedevas.add(p);
            
            velPiedevas = JOptionPane.showConfirmDialog(null, "Vai pievienot vēl kādu piedevu?", "Piedevas", JOptionPane.YES_NO_OPTION);
        } while (velPiedevas == JOptionPane.YES_OPTION);


        String[] merсuSaraksts = {"Tomātu", "Ķiploku", "BBQ"};
        String merce = (String) JOptionPane.showInputDialog(null, "Izvēlieties mērci:", "Mērces",
                JOptionPane.QUESTION_MESSAGE, null, merсuSaraksts, merсuSaraksts[0]);

        Pasutijums jaunais = new Pasutijums(
            vards, talrunis, adrese, 
            new int[]{izmeraID}, 
            izveletasPiedevas.toArray(new String[0]), 
            new String[]{merce}, 
            uzVietas
        );
        DarbsArFailu.saglabatFaila(jaunais.izvadFailam(), true);
        
        jaunais.izvadit();
    }

    public static void main(String[] args) {

    	String[] darbibas = {"Jauns pasūtījums", "Apskatīt aktīvos","Pabeigt pasūtījumu", "Apskatīt vēsturi", "Apturēt"};
        
        int izvele;
        do {
            izvele = JOptionPane.showOptionDialog(null, "Izvēlieties darbību:", "Picerijas vadības sistēma", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, darbibas, darbibas[0]);

            switch (izvele) {
                case 0: 
                    izveidotPasutijumu();
                    break;
                case 1: 
                    paraditSarakstu(true);
                    break;
                case 2: 
                    pabeigtPasutijumu();
                    break;
                case 3: 
                    paraditSarakstu(false);
                    break;
            }
        } while (izvele != 4 && izvele != -1);
    }


}
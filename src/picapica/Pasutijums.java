package picapica;

import javax.swing.JOptionPane;


public class Pasutijums {

	private String vards;
    private String talrunis;
    private String adrese;
    

    private int[] picasLielums;
    private String[] piedevas;
    private String[] merces;
    private boolean uzVietas;


    public Pasutijums(String vards, String talrunis, String adrese, int[] picasLielums, 
                      String[] piedevas, String[] merces, boolean uzVietas) {
        this.vards = vards;
        this.talrunis = talrunis;
        this.adrese = adrese;
        this.picasLielums = picasLielums;
        this.piedevas = piedevas;
        this.merces = merces;
        this.uzVietas = uzVietas;
    }

    
    public String noteiktVards() { return vards; }
    public String noteiktTalrunis() { return talrunis; }
    public String noteiktAdrese() { return adrese; }
    public int noteiktPicasLielums() { return picasLielums[0]; }
    public int noteiktPiedevasSkaits() { return piedevas.length; }
    public int noteiktMercesSkaits() { return merces.length; }
    public boolean noteiktVaiUzVietas() { return uzVietas; }


    public void labotVards(String vards) { this.vards = vards; }
    public void labotTalrunis(String talrunis) { this.talrunis = talrunis; }
    public void labotAdrese(String adrese) { this.adrese = adrese; }
    public void labotUzVietas(boolean uzVietas) { this.uzVietas = uzVietas; }


    public void labotPiedevas(int index) {
        if (index >= 0 && index < piedevas.length) {
            String[] opcijas = {"Siers", "Bekons", "Sēnes", "Paprika", "Sīpoli", "Ananāsi"};
            String jauna = (String) JOptionPane.showInputDialog(null, 
                    "Izvēlieties jaunu piedevu esošās (" + piedevas[index] + ") vietā:", 
                    "Rediģēt piedevu", JOptionPane.QUESTION_MESSAGE, null, opcijas, piedevas[index]);
            if (jauna != null) this.piedevas[index] = jauna;
        }
    }


    public void labotMerces(int index) {
        if (index >= 0 && index < merces.length) {
            String[] opcijas = {"Tomātu", "Ķiploku", "BBQ", "Majonēze"};
            String jauna = (String) JOptionPane.showInputDialog(null, 
                    "Izvēlieties jaunu mērci esošās (" + merces[index] + ") vietā:", 
                    "Rediģēt mērci", JOptionPane.QUESTION_MESSAGE, null, opcijas, merces[index]);
            if (jauna != null) this.merces[index] = jauna;
        }
    }


    public double aprekinatCenu() {
        double summa = 0;
        
        switch(picasLielums[0]) {
            case 0: summa = 7.00; break;
            case 1: summa = 10.00; break;
            case 2: summa = 13.00; break;
        }
        
        summa += (piedevas.length * 1.50);
        summa += (merces.length * 0.50);
        
        if (!uzVietas) {
            summa += 4.99;
        }
        
        return summa;
    }


    public String izvadFailam() {
        return vards + ";" + talrunis + ";" + adrese + ";" + 
               picasLielums[0] + ";" + String.join(",", piedevas) + ";" + 
               String.join(",", merces) + ";" + uzVietas + ";" + aprekinatCenu();
    }


    public void izvadit() {
        String info =
                "~~ PASŪTĪJUMS ~~\n" +
                "Klients: " + vards + "\n" +
                "Tālrunis: " + talrunis + "\n" +
                "Saņemšana: " + (uzVietas ? "Uz vietas" : "Piegāde") + "\n" +
                "Adrese: " + (uzVietas ? "-" : adrese) + "\n" +
                "Piedevas: " + String.join(", ", piedevas) + "\n" +
                "Mērces: " + String.join(", ", merces) + "\n" +
                "------------------\n" +
                "KOPĀ: " + aprekinatCenu() + " EUR";
        JOptionPane.showMessageDialog(null, info, "Pasūtījuma apskate", JOptionPane.INFORMATION_MESSAGE);
    }
}
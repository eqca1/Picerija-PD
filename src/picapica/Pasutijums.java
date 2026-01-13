package picapica;

import javax.swing.JOptionPane;

public class Pasutijums {

	String vards;
    String talrunis;
    String adrese;
    int[] picasLielums;
    String[] piedevas;
    String[] merces;
    boolean uzVietas;


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


    
    public void labotVards(String vards) {
    	this.vards = vards; }
    public void labotTalrunis(String talrunis) {
    	this.talrunis = talrunis; }
    public void labotAdrese(String adrese) {
    	this.adrese = adrese; }
    public void labotPicasLielums(int id) {
    	this.picasLielums[0] = id; }
    public void labotPiedevas(int id) {
        if (id >= 0 && id < piedevas.length) {
            String[] visasPiedevas = {"Siers", "Bekons", "Sēnes", "Paprika", "Sīpoli", "Ananāsi"};
            
            String jaunaPiedeva = (String) JOptionPane.showInputDialog(null, 
                    "Izvēlieties jaunu piedevu esošās (" + piedevas[id] + ") vietā:", "Piedevu rediģēšana", JOptionPane.QUESTION_MESSAGE, null, 
                    	visasPiedevas, piedevas[id]);
            if (jaunaPiedeva != null) {
                this.piedevas[id] = jaunaPiedeva;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nepareizs piedevas indekss!", "Kļūda", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void labotMerces(int id) {
        if (id >= 0 && id < merces.length) {
            String[] visasMerces = {"Tomātu mērce", "Ķiploku mērce", "BBQ mērce", "Majonēze"};
            
            String jaunaMerce = (String) JOptionPane.showInputDialog(null, 
                    "Izvēlieties jaunu mērci esošās (" + merces[id] + ") vietā:", "Mērču rediģēšana", JOptionPane.QUESTION_MESSAGE, null, 
                    	visasMerces, merces[id]);

            if (jaunaMerce != null) {
                this.merces[id] = jaunaMerce;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nepareizs mērces indekss!", "Kļūda", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void labotUzVietas(boolean uzVietas) { this.uzVietas = uzVietas; }


    public String noteiktVards() {
    	return vards; }
    public String noteiktTalrunis() {
    	return talrunis; }
    public String noteiktAdrese() {
    	return adrese; }
    public int noteiktPicasLielums() {
    	return picasLielums[0]; }
    public int noteiktPiedevas() {
    	return piedevas.length; }
    public int noteiktMerces() {
    	return merces.length; }
    
}
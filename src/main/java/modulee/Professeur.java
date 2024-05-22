package modulee;

public class Professeur {
    private int id_prof;
    private Etablissement etablissement;
    private String nom_prof;
    private String prenom_prof;
    private String cin;
    private String numPRP;
    private String email;
    private String telephone;
    private String specialite;
    private String password;
    public Professeur() {
    }

    public Professeur(int id_prof,Etablissement etablissement , String nom_prof, String prenom_prof, String cin, String numPRP, String email, String telephone, String specialite, String password) {
        this.id_prof = id_prof;
        this.etablissement = etablissement;
        this.nom_prof = nom_prof;
        this.prenom_prof = prenom_prof;
        this.cin = cin;
        this.numPRP = numPRP;
        this.email = email;
        this.telephone = telephone;
        this.specialite = specialite;
        this.password = password;
    }
    public Professeur(Etablissement etablissement, String nom_prof, String prenom_prof, String cin, String numPRP, String email, String telephone, String specialite, String password) {
    	
         this.etablissement = etablissement;
         this.nom_prof = nom_prof;
         this.prenom_prof = prenom_prof;
         this.cin = cin;
         this.numPRP = numPRP;
         this.email = email;
         this.telephone = telephone;
         this.specialite = specialite;
         this.password = password;
    }


    // Getters et Setters
    public int getId_prof() {
        return id_prof;
    }

    public void setId_prof(int id_prof) {
        this.id_prof = id_prof;
    }

    public Etablissement getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(Etablissement etablissement) {
        this.etablissement = etablissement;
    }
    public String getNom_prof() {
        return nom_prof;
    }

    public void setNom_prof(String nom_prof) {
        this.nom_prof = nom_prof;
    }

    public String getPrenom_prof() {
        return prenom_prof;
    }

    public void setPrenom_prof(String prenom_prof) {
        this.prenom_prof = prenom_prof;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getNumPRP() {
        return numPRP;
    }

    public void setNumPRP(String numPRP) {
        this.numPRP = numPRP;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

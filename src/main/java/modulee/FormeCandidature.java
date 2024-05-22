package modulee;

public class FormeCandidature {
    private int id_forme;
    private Professeur professeur;
    private int nb_heure_permis;
    private int nb_heure_occupe;

    public FormeCandidature() {
    }

    public FormeCandidature(int id_forme, Professeur professeur, int nb_heure_permis, int nb_heure_occupe) {
        this.id_forme = id_forme;
        this.professeur = professeur;
        this.nb_heure_permis = nb_heure_permis;
        this.nb_heure_occupe = nb_heure_occupe;
    }

    public FormeCandidature(Professeur professeur, int nb_heure_permis, int nb_heure_occupe) {
        this.professeur = professeur;
        this.nb_heure_permis = nb_heure_permis;
        this.nb_heure_occupe = nb_heure_occupe;
    }

    // Getters and Setters
    public int getId_forme() {
        return id_forme;
    }

    public void setId_forme(int id_forme) {
        this.id_forme = id_forme;
    }

    public Professeur getProfesseur() {
        return professeur;
    }

    public void setProfesseur(Professeur professeur) {
        this.professeur = professeur;
    }

    public int getNb_heure_permis() {
        return nb_heure_permis;
    }

    public void setNb_heure_permis(int nb_heure_permis) {
        this.nb_heure_permis = nb_heure_permis;
    }

    public int getNb_heure_occupe() {
        return nb_heure_occupe;
    }

    public void setNb_heure_occupe(int nb_heure_occupe) {
        this.nb_heure_occupe = nb_heure_occupe;
    }
}

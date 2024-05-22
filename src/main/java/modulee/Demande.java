package modulee;

import java.util.Date;

public class Demande {
    private int id_demande;
    private Professeur professeur;
    private OffreVacation offreVacation;
    private Date date_demande;
    private String etat_demande;

    // Constructeur
    public Demande() {
    }
    public Demande(int id_demande, Professeur professeur, OffreVacation offreVacation, Date date_demande, String etat_demande) {
        this.id_demande = id_demande;
        this.professeur = professeur;
        this.offreVacation = offreVacation;
        this.date_demande = date_demande;
        this.etat_demande = etat_demande;
    }
    
    public Demande( Professeur professeur, OffreVacation offreVacation, Date date_demande, String etat_demande) {
      
        this.professeur = professeur;
        this.offreVacation = offreVacation;
        this.date_demande = date_demande;
        this.etat_demande = etat_demande;
    }

    // Getters et Setters

    public int getId_demande() {
        return id_demande;
    }

    public void setId_demande(int id_demande) {
        this.id_demande = id_demande;
    }

    public Professeur getProfesseur() {
        return professeur;
    }

    public void setProfesseur(Professeur professeur) {
        this.professeur = professeur;
    }

    public OffreVacation getOffreVacation() {
        return offreVacation;
    }

    public void setOffreVacation(OffreVacation offreVacation) {
        this.offreVacation = offreVacation;
    }

    public Date getDate_demande() {
        return date_demande;
    }

    public void setDate_demande(Date date_demande) {
        this.date_demande = date_demande;
    }

    public String getEtat_demande() {
        return etat_demande;
    }

    public void setEtat_demande(String etat_demande) {
        this.etat_demande = etat_demande;
    }
	public void setNom_professeur(String nomProfesseur) {
		// TODO Auto-generated method stub
		
	}
}

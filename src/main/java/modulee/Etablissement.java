package modulee;

public class Etablissement {
    private int id_etab;
    private String nom_etab;
    private String abreviation;
    private String ville_etab;

    // Constructeurs
    public Etablissement() {
    }

    public Etablissement(int id_etab, String nom_etab, String abreviation, String ville_etab) {
        this.id_etab = id_etab;
        this.nom_etab = nom_etab;
        this.abreviation = abreviation;
        this.ville_etab = ville_etab;
    }

    public Etablissement(String nom_etab, String abreviation, String ville_etab) {
        this.nom_etab = nom_etab;
        this.abreviation = abreviation;
        this.ville_etab = ville_etab;
    }

    // Getters et Setters
    public int getId_etab() {
        return id_etab;
    }

    public void setId_etab(int id_etab) {
        this.id_etab = id_etab;
    }

    public String getNom_etab() {
        return nom_etab;
    }

    public void setNom_etab(String nom_etab) {
        this.nom_etab = nom_etab;
    }

    public String getAbreviation() {
        return abreviation;
    }

    public void setAbreviation(String abreviation) {
        this.abreviation = abreviation;
    }

    public String getVille_etab() {
        return ville_etab;
    }

    public void setVille_etab(String ville_etab) {
        this.ville_etab = ville_etab;
    }
}

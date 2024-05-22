package modulee;

public class OffreVacation {
    private int id_offre;
    private Element element;
    private String description;
    private String poste;
    private String profil;

    // Constructeur
    
    public OffreVacation(int id_offre, Element element, String description, String poste, String profil) {
        this.id_offre = id_offre;
        this.element = element;
        this.description = description;
        this.poste = poste;
        this.profil = profil;
    }
    
    public OffreVacation(Element element, String description, String poste, String profil) {
        this.element = element;
        this.description = description;
        this.poste = poste;
        this.profil = profil;
    }

    // Getters et setters

   

	public OffreVacation() {
		// TODO Auto-generated constructor stub
	}
	public int getId_offre() {
        return id_offre;
    }

    public void setId_offre(int id_offre) {
        this.id_offre = id_offre;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public String getProfil() {
        return profil;
    }

    public void setProfil(String profil) {
        this.profil = profil;
    }

    // Autres méthodes si nécessaire
}

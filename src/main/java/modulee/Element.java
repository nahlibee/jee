package modulee;

public class Element {
    private int id_element;
    private Module module;
    private String nom_element;
    private int volumehoraire_element;

    // Constructeurs
    public Element() {
    }

    public Element(int id_element, Module module, String nom_element, int volumehoraire_element) {
        this.id_element = id_element;
        this.module = module;
        this.nom_element = nom_element;
        this.volumehoraire_element = volumehoraire_element;
    }

    public Element(Module module, String nom_element, int volumehoraire_element) {
        this.module = module;
        this.nom_element = nom_element;
        this.volumehoraire_element = volumehoraire_element;
    }

    // Getters et Setters
    public int getId_element() {
        return id_element;
    }

    public void setId_element(int id_element) {
        this.id_element = id_element;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public String getNom_element() {
        return nom_element;
    }

    public void setNom_element(String nom_element) {
        this.nom_element = nom_element;
    }

    public int getVolumehoraire_element() {
        return volumehoraire_element;
    }

    public void setVolumehoraire_element(int volumehoraire_element) {
        this.volumehoraire_element = volumehoraire_element;
    }

	
}

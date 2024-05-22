package modulee;

public class Module {
	private int id_module;
    private String nom_module;
    private String abrev_module;

    // Constructeur par défaut
    public Module() {
    }
    public Module(int id_module, String nom_module, String abrev_module) {
		super();
		this.id_module = id_module;
		this.nom_module = nom_module;
		this.abrev_module = abrev_module;
		
	}
	
	public Module(String nom_module, String abrev_module) {
		super();
		
		this.nom_module = nom_module;
		this.abrev_module = abrev_module;
	
	}

	public int getId_module() {
		return id_module;
	}

	public void setId_module(int id_module) {
		this.id_module = id_module;
	}

	public String getNom_module() {
		return nom_module;
	}

	public void setNom_module(String nom_module) {
		this.nom_module = nom_module;
	}

	public String getAbrev_module() {
		return abrev_module;
	}

	public void setAbrev_module(String abrev_module) {
		this.abrev_module = abrev_module;
	}
    


    // Constructors, getters, setters...
}

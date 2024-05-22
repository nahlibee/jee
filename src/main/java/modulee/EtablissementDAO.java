package modulee;


import java.util.List;

public interface EtablissementDAO {
    Etablissement getById(int id_etab);
    List<Etablissement> getAll();
    void insert(Etablissement etablissement);
    void update(Etablissement etablissement);
    void delete(int id_etab);
}

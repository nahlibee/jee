package modulee;

import java.util.List;

public interface ProfesseurDAO {
    Professeur getById(int id_prof);
    List<Professeur> getByEtablissementId(int id_prof);
    List<Professeur> getAll();
    void insert(Professeur professeur);
    void update(Professeur professeur);
    void delete(int id_prof);
}

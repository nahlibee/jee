package modulee;

import java.util.List;

public interface FormeCandidatureDAO {
	FormeCandidature getById(int id_forme);
    List<FormeCandidature> getByProfesseurtId(int id_forme);
    List<FormeCandidature> getAll();
    void insert(FormeCandidature formecandidature);
    void update(FormeCandidature formecandidature);
    void delete(int id_forme);
}

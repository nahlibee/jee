package modulee;

import java.sql.SQLException;
import java.util.List;

public interface OffreVacationDAO {
    OffreVacation getById(int id_offre) throws SQLException;

    List<OffreVacation> getByElementId(int idElement) throws SQLException;

    List<OffreVacation> getAll() throws SQLException;

    void insert(OffreVacation offreVacation) throws SQLException;

    void update(OffreVacation offreVacation) throws SQLException;

    void delete(int id_offre) throws SQLException;
}

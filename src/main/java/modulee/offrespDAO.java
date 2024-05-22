package modulee;

import java.sql.SQLException;
import java.util.List;

public interface offrespDAO {
	 public List<OffreVacation> getAll() throws SQLException;
}

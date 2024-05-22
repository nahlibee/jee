package modulee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class offrespDAOImpl implements offrespDAO {

    private final Connection connection;

    public offrespDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<OffreVacation> getAll() throws SQLException {
        List<OffreVacation> offresVacation = new ArrayList<>();
        String sql = "SELECT * FROM offrevacation o " +
                     "JOIN element e ON o.id_element = e.id_element " +
                     "JOIN module m ON e.id_module = m.id_module";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                OffreVacation offreVacation = extractOffreVacationFromResultSet(resultSet);
                offresVacation.add(offreVacation);
            }

        }

        return offresVacation;
    }

    private OffreVacation extractOffreVacationFromResultSet(ResultSet resultSet) throws SQLException {
        ModuleDAO moduleDAO = new ModuleDAOImpl(connection);

        Module module = moduleDAO.getById(resultSet.getInt("id_module"));
        Element element = new Element();
        element.setId_element(resultSet.getInt("id_element"));
        element.setNom_element(resultSet.getString("nom_element"));
        element.setModule(module);

        OffreVacation offreVacation = new OffreVacation();
        offreVacation.setId_offre(resultSet.getInt("id_offre"));
        offreVacation.setElement(element);
        offreVacation.setDescription(resultSet.getString("description"));
        offreVacation.setPoste(resultSet.getString("poste"));
        offreVacation.setProfil(resultSet.getString("profil"));

        return offreVacation;
    }
}

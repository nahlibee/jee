package modulee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OffreVacationDAOImpl implements OffreVacationDAO {

    private Connection connection;

    public OffreVacationDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public OffreVacation getById(int idOffre) throws SQLException {
        OffreVacation offreVacation = null;
        String sql = "SELECT * FROM offrevacation o " +
                     "JOIN element e ON o.id_element = e.id_element " +
                     "JOIN module m ON e.id_module = m.id_module " +
                     "WHERE o.id_offre = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idOffre);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                offreVacation = extractOffreVacationFromResultSet(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return offreVacation;
    }

    @Override
    public List<OffreVacation> getByElementId(int idElement) throws SQLException {
        List<OffreVacation> offresVacation = new ArrayList<>();
        String sql = "SELECT * FROM offrevacation o " +
                     "JOIN element e ON o.id_element = e.id_element " +
                     "JOIN module m ON e.id_module = m.id_module " +
                     "WHERE o.id_element = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idElement);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                OffreVacation offreVacation = extractOffreVacationFromResultSet(resultSet);
                offresVacation.add(offreVacation);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return offresVacation;
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

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return offresVacation;
    }

    @Override
    public void insert(OffreVacation offreVacation) throws SQLException {
        String sql = "INSERT INTO offrevacation (id_element, description, poste, profil) VALUES (?,?,?,?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, offreVacation.getElement().getId_element());
            statement.setString(2, offreVacation.getDescription());
            statement.setString(3, offreVacation.getPoste());
            statement.setString(4, offreVacation.getProfil());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

  
    @Override
    public void update(OffreVacation offreVacation) throws SQLException {
        String sql = "UPDATE offrevacation " +
                     "SET id_element = ?, description = ?, poste = ?, profil = ? " +
                     "WHERE id_offre = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, offreVacation.getElement().getId_element());
            statement.setString(2, offreVacation.getDescription());
            statement.setString(3, offreVacation.getPoste());
            statement.setString(4, offreVacation.getProfil());
            statement.setInt(5, offreVacation.getId_offre());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void delete(int idOffre) throws SQLException {
        String sql = "DELETE FROM offrevacation WHERE id_offre = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idOffre);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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


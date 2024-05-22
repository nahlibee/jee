package modulee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ElementDAOImpl implements ElementDAO {

    private Connection connection;

    public ElementDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Element getById(int id_element) {
        Element element = null;
        String sql = "SELECT * FROM element e JOIN module m ON e.id_module = m.id_module WHERE id_element = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id_element);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                element = extractElementFromResultSet(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return element;
    }

    @Override
    public List<Element> getByModuleId(int id_module) {
        List<Element> elements = new ArrayList<>();
        String sql = "SELECT * FROM element e JOIN module m ON e.id_module = m.id_module WHERE e.id_module = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id_module);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Element element = extractElementFromResultSet(resultSet);
                elements.add(element);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return elements;
    }

    @Override
    public List<Element> getAll() {
        List<Element> elements = new ArrayList<>();
        String sql = "SELECT e.*, m.nom_module, m.abrev_module FROM element e JOIN module m ON e.id_module = m.id_module";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Element element = extractElementFromResultSet(resultSet);
                elements.add(element);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return elements;
    }


    @Override
    public void insert(Element element) {
        String sql = "INSERT INTO element (id_module, nom_element, volumehoraire_element) VALUES (?,?,?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, element.getModule().getId_module());
            statement.setString(2, element.getNom_element());
            statement.setInt(3, element.getVolumehoraire_element());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Element element) {
        String sql = "UPDATE element SET id_module = ?, nom_element = ?, volumehoraire_element = ? WHERE id_element = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, element.getModule().getId_module());
            statement.setString(2, element.getNom_element());
            statement.setInt(3, element.getVolumehoraire_element());
            statement.setInt(4, element.getId_element());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id_element) {
        String sql = "DELETE FROM element WHERE id_element = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id_element);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Element extractElementFromResultSet(ResultSet resultSet) throws SQLException {
        Module module = new Module();
        module.setId_module(resultSet.getInt("id_module"));
        module.setNom_module(resultSet.getString("nom_module"));
        module.setAbrev_module(resultSet.getString("abrev_module"));

        Element element = new Element();
        element.setId_element(resultSet.getInt("id_element"));
        element.setModule(module);
        element.setNom_element(resultSet.getString("nom_element"));
        element.setVolumehoraire_element(resultSet.getInt("volumehoraire_element"));

        return element;
    }

}

package modulee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class ModuleDAOImpl implements ModuleDAO {
	

    private Connection connection;

    public ModuleDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Module getById(int id_module) {
        Module module = null;
        String sql = "SELECT * FROM module WHERE id_module = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id_module);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                module = new Module();
                module.setId_module(resultSet.getInt("id_module"));
                module.setNom_module(resultSet.getString("nom_module"));
                module.setAbrev_module(resultSet.getString("abrev_module"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return module;
    }

    public List<Module> getAll() {
        List<Module> modules = new ArrayList<>();
        String sql = "SELECT * FROM module";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Module module = new Module();
                module.setId_module(resultSet.getInt("id_module"));
                module.setNom_module(resultSet.getString("nom_module"));
                module.setAbrev_module(resultSet.getString("abrev_module"));

                modules.add(module);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return modules;
    }

    @Override
    public void insert(Module module) {
        String sql = "INSERT INTO module (nom_module, abrev_module) VALUES (?,?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, module.getNom_module());
            statement.setString(2, module.getAbrev_module());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Module module) {
        String sql = "UPDATE module SET nom_module = ?, abrev_module = ? WHERE id_module = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, module.getNom_module());
            statement.setString(2, module.getAbrev_module());
            statement.setInt(3, module.getId_module());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id_module) {
        String sql = "DELETE FROM module WHERE id_module = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id_module);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

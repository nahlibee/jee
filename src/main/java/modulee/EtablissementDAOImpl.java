package modulee;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class EtablissementDAOImpl implements EtablissementDAO {

    private Connection connection;

    public EtablissementDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Etablissement getById(int id_etab) {
        Etablissement etab = null;
        String sql = "SELECT * FROM etablissement WHERE id_etab = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id_etab);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                etab = new Etablissement();
                etab.setId_etab(resultSet.getInt("id_etab"));
                etab.setNom_etab(resultSet.getString("nom_etab"));
                etab.setAbreviation(resultSet.getString("abreviation"));
                etab.setVille_etab(resultSet.getString("ville_etab"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return etab;
    }

    @Override
    public List<Etablissement> getAll() {
        List<Etablissement> etablissements = new ArrayList<>();
        String sql = "SELECT * FROM etablissement";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Etablissement etab = new Etablissement();
                etab.setId_etab(resultSet.getInt("id_etab"));
                etab.setNom_etab(resultSet.getString("nom_etab"));
                etab.setAbreviation(resultSet.getString("abreviation"));
                etab.setVille_etab(resultSet.getString("ville_etab"));

                etablissements.add(etab);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return etablissements;
    }

    @Override
    public void insert(Etablissement etab) {
        String sql = "INSERT INTO etablissement (nom_etab, abreviation, ville_etab) VALUES (?,?,?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, etab.getNom_etab());
            statement.setString(2, etab.getAbreviation());
            statement.setString(3, etab.getVille_etab());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Etablissement etab) {
        String sql = "UPDATE etablissement SET nom_etab = ?, abreviation = ?, ville_etab = ? WHERE id_etab = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, etab.getNom_etab());
            statement.setString(2, etab.getAbreviation());
            statement.setString(3, etab.getVille_etab());
            statement.setInt(4, etab.getId_etab());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id_etab) {
        String sql = "DELETE FROM etablissement WHERE id_etab = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id_etab);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

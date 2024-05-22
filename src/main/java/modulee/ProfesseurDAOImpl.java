package modulee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfesseurDAOImpl implements ProfesseurDAO {

    private Connection connection;

    public ProfesseurDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Professeur getById(int id_prof) {
        Professeur professeur = null;
        String sql = "SELECT * FROM professeur p JOIN etablissement e ON p.id_etab = e.id_etab WHERE id_prof = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id_prof);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                professeur = extractProfesseurFromResultSet(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return professeur;
    }

    @Override
    public List<Professeur> getByEtablissementId(int id_etab) {
        List<Professeur> professeurs = new ArrayList<>();
        String sql = "SELECT * FROM professeur p JOIN etablissement e ON p.id_etab = e.id_etab WHERE p.id_etab = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id_etab);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Professeur professeur = extractProfesseurFromResultSet(resultSet);
                professeurs.add(professeur);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return professeurs;
    }

    @Override
    public List<Professeur> getAll() {
        List<Professeur> professeurs = new ArrayList<>();
        String sql = "SELECT p.*, e.nom_etab, e.ville_etab FROM professeur p JOIN etablissement e ON p.id_etab = e.id_etab";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Professeur professeur = extractProfesseurFromResultSet(resultSet);
                professeurs.add(professeur);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return professeurs;
    }

    @Override
    public void insert(Professeur professeur) {
        String sql = "INSERT INTO professeur (id_etab, nom_prof, prenom_prof, cin, numPRP, email, telephone, specialite, password) VALUES (?,?,?,?,?,?,?,?,?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, professeur.getEtablissement().getId_etab());
            statement.setString(2, professeur.getNom_prof());
            statement.setString(3, professeur.getPrenom_prof());
            statement.setString(4, professeur.getCin());
            statement.setString(5, professeur.getNumPRP());
            statement.setString(6, professeur.getEmail());
            statement.setString(7, professeur.getTelephone());
            statement.setString(8, professeur.getSpecialite());
            statement.setString(9, professeur.getPassword());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Professeur professeur) {
        String sql = "UPDATE professeur SET id_etab = ?, nom_prof = ?, prenom_prof = ?, cin = ?, numPRP = ?, email = ?, telephone = ?, specialite = ?, password = ? WHERE id_prof = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, professeur.getEtablissement().getId_etab());
            statement.setString(2, professeur.getNom_prof());
            statement.setString(3, professeur.getPrenom_prof());
            statement.setString(4, professeur.getCin());
            statement.setString(5, professeur.getNumPRP());
            statement.setString(6, professeur.getEmail());
            statement.setString(7, professeur.getTelephone());
            statement.setString(8, professeur.getSpecialite());
            statement.setString(9, professeur.getPassword());
            statement.setInt(10, professeur.getId_prof());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id_prof) {
        String sql = "DELETE FROM professeur WHERE id_prof = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id_prof);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Professeur extractProfesseurFromResultSet(ResultSet resultSet) throws SQLException {
        Etablissement etablissement = new Etablissement();
        etablissement.setId_etab(resultSet.getInt("id_etab"));
        etablissement.setNom_etab(resultSet.getString("nom_etab"));
        etablissement.setVille_etab(resultSet.getString("ville_etab"));

        Professeur professeur = new Professeur();
        professeur.setId_prof(resultSet.getInt("id_prof"));
        professeur.setEtablissement(etablissement);
        professeur.setNom_prof(resultSet.getString("nom_prof"));
        professeur.setPrenom_prof(resultSet.getString("prenom_prof"));
        professeur.setCin(resultSet.getString("cin"));
        professeur.setNumPRP(resultSet.getString("numPRP"));
        professeur.setEmail(resultSet.getString("email"));
        professeur.setTelephone(resultSet.getString("telephone"));
        professeur.setSpecialite(resultSet.getString("specialite"));
        professeur.setPassword(resultSet.getString("password"));

        return professeur;
    }
    
    
}

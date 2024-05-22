package modulee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FormeCandidatureDAOImpl implements FormeCandidatureDAO {

    private Connection connection;

    public FormeCandidatureDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public FormeCandidature getById(int id_forme) {
        FormeCandidature formeCandidature = null;
        String sql = "SELECT * FROM formecandidature fc JOIN professeur p ON fc.id_prof = p.id_prof WHERE id_forme = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id_forme);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                formeCandidature = extractFormeCandidatureFromResultSet(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return formeCandidature;
    }

    @Override
    public List<FormeCandidature> getByProfesseurtId(int id_prof) {
        List<FormeCandidature> formeCandidatures = new ArrayList<>();
        String sql = "SELECT * FROM formecandidature fc JOIN professeur p ON fc.id_prof = p.id_prof WHERE fc.id_prof = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id_prof);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                FormeCandidature formeCandidature = extractFormeCandidatureFromResultSet(resultSet);
                formeCandidatures.add(formeCandidature);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return formeCandidatures;
    }

    @Override
    public List<FormeCandidature> getAll() {
        List<FormeCandidature> formeCandidatures = new ArrayList<>();
        String sql = "SELECT * FROM formecandidature fc JOIN professeur p ON fc.id_prof = p.id_prof";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                FormeCandidature formeCandidature = extractFormeCandidatureFromResultSet(resultSet);
                formeCandidatures.add(formeCandidature);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return formeCandidatures;
    }

    @Override
    public void insert(FormeCandidature formeCandidature) {
        String sql = "INSERT INTO formecandidature (id_prof, nb_heure_permis, nb_heure_occupe) VALUES (?,?,?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, formeCandidature.getProfesseur().getId_prof());
            statement.setInt(2, formeCandidature.getNb_heure_permis());
            statement.setInt(3, formeCandidature.getNb_heure_occupe());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(FormeCandidature formeCandidature) {
        String sql = "UPDATE formecandidature SET id_prof = ?, nb_heure_permis = ?, nb_heure_occupe = ? WHERE id_forme = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, formeCandidature.getProfesseur().getId_prof());
            statement.setInt(2, formeCandidature.getNb_heure_permis());
            statement.setInt(3, formeCandidature.getNb_heure_occupe());
            statement.setInt(4, formeCandidature.getId_forme());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id_forme) {
        String sql = "DELETE FROM formecandidature WHERE id_forme = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id_forme);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private FormeCandidature extractFormeCandidatureFromResultSet(ResultSet resultSet) throws SQLException {
        Professeur professeur = new Professeur();
        professeur.setId_prof(resultSet.getInt("id_prof"));
        professeur.setNom_prof(resultSet.getString("nom_prof"));
        professeur.setPrenom_prof(resultSet.getString("prenom_prof"));
        
        FormeCandidature formeCandidature = new FormeCandidature();
        formeCandidature.setId_forme(resultSet.getInt("id_forme"));
        formeCandidature.setProfesseur(professeur);
        formeCandidature.setNb_heure_permis(resultSet.getInt("nb_heure_permis"));
        formeCandidature.setNb_heure_occupe(resultSet.getInt("nb_heure_occupe"));

        return formeCandidature;
    }
}

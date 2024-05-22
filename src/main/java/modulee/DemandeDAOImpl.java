package modulee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DemandeDAOImpl implements DemandeDAO {

    private Connection connexion; // Assurez-vous de l'initialiser correctement

    // Constructeur avec la connexion à la base de données
    public DemandeDAOImpl(Connection connexion) {
        this.connexion = connexion;
    }

    @Override
    public void ajouterDemande(Demande demande) {
        String requete = "INSERT INTO demande (id_prof, id_offre, date_demande, etat_demande) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = connexion.prepareStatement(requete)) {
            pstmt.setInt(1, demande.getProfesseur().getId_prof());
            pstmt.setInt(2, demande.getOffreVacation().getId_offre());
            pstmt.setDate(3, new java.sql.Date(demande.getDate_demande().getTime()));
            pstmt.setString(4, demande.getEtat_demande());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Demande obtenirDemandeParId(int id_demande) {
        String requete = "SELECT * FROM demande WHERE id_demande = ?";
        Demande demande = null;

        try (PreparedStatement pstmt = connexion.prepareStatement(requete)) {
            pstmt.setInt(1, id_demande);

            try (ResultSet resultSet = pstmt.executeQuery()) {
                if (resultSet.next()) {
                    demande = mapperResultSetVersDemande(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return demande;
    }

    @Override
    public List<Demande> obtenirToutesDemandes() {
        String requete = "SELECT * FROM demande";
        List<Demande> demandes = new ArrayList<>();

        try (PreparedStatement pstmt = connexion.prepareStatement(requete);
             ResultSet resultSet = pstmt.executeQuery()) {
            while (resultSet.next()) {
                Demande demande = mapperResultSetVersDemande(resultSet);
                demandes.add(demande);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return demandes;
    }

    // Ajoutez d'autres méthodes DAO nécessaires ici...

    // Méthode pour mapper les données d'une ligne ResultSet vers un objet Demande
    private Demande mapperResultSetVersDemande(ResultSet resultSet) throws SQLException {
        int id_demande = resultSet.getInt("id_demande");
        int id_prof = resultSet.getInt("id_prof");
        int id_offre = resultSet.getInt("id_offre");
        Date date_demande = resultSet.getDate("date_demande");
        String etat_demande = resultSet.getString("etat_demande");

        // Vous devrez obtenir les objets Professeur et OffreVacation à partir de leurs DAO respectifs
        ProfesseurDAO professeurDAO = new ProfesseurDAOImpl(connexion);
        OffreVacationDAO offreVacationDAO = new OffreVacationDAOImpl(connexion);

        Professeur professeur = professeurDAO.getById(id_prof);
        OffreVacation offreVacation = offreVacationDAO.getById(id_offre);

        return new Demande(id_demande, professeur, offreVacation, date_demande, etat_demande);
    }

	@Override
	public void mettreAJourDemande(Demande demande) {
		// TODO Auto-generated method stub
		
	}
}

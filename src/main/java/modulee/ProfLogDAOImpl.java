package modulee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfLogDAOImpl implements ProfLogDAO {

    private Connection connection;

    public ProfLogDAOImpl(Connection connection) {
        this.connection = connection;
    }
    
 


    @Override
    public boolean login(String email, String password) {
        String query = "SELECT * FROM professeur WHERE email = ? AND password = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void creerCompte(Professeur professeur) {
        String query = "INSERT INTO professeur (id_etab, nom_prof, prenom_prof, cin, numPRP, email, telephone, specialite, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, professeur.getEtablissement().getId_etab());
            pstmt.setString(2, professeur.getNom_prof());
            pstmt.setString(3, professeur.getPrenom_prof());
            pstmt.setString(4, professeur.getCin());
            pstmt.setString(5, professeur.getNumPRP());
            pstmt.setString(6, professeur.getEmail());
            pstmt.setString(7, professeur.getTelephone());
            pstmt.setString(8, professeur.getSpecialite());
            pstmt.setString(9, professeur.getPassword());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public Professeur getByEmail(String email) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Professeur professeur = null;

        try {
            conn = ModuleConnexion.getConnection();
            String query = "SELECT * FROM professeur WHERE email = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, email);
            rs = stmt.executeQuery();

            if (rs.next()) {
                professeur = extractProfesseurFromResultSet(rs);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return professeur;
    }

    // Ajoutez cette méthode pour extraire les données du ResultSet
    private Professeur extractProfesseurFromResultSet(ResultSet rs) throws SQLException {
        Professeur professeur = new Professeur();
        professeur.setId_prof(rs.getInt("id_prof"));
        professeur.setNom_prof(rs.getString("nom_prof"));
        professeur.setPrenom_prof(rs.getString("prenom_prof"));
        professeur.setCin(rs.getString("cin"));
        professeur.setNumPRP(rs.getString("numPRP"));
        professeur.setEmail(rs.getString("email"));
        professeur.setTelephone(rs.getString("telephone"));
        professeur.setSpecialite(rs.getString("specialite"));
        // Ajoutez d'autres champs au besoin

        return professeur;
    }
}

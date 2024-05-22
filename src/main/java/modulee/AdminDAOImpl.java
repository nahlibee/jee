package modulee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAOImpl implements AdminDAO {

    @Override
    public boolean authenticate(String login, String password) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ModuleConnexion.getConnection();
            String query = "SELECT * FROM admin WHERE login = ? AND password = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, login);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

            return rs.next();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
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
    }

    @Override
    public boolean registerAdmin(String login, String password, String nom, String prenom) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ModuleConnexion.getConnection();
            String query = "INSERT INTO admin (login, password, nom_ad, prenom_ad) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, login);
            stmt.setString(2, password);
            stmt.setString(3, nom);
            stmt.setString(4, prenom);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
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
    }

    @Override
    public Admin getAdminByLogin(String login) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Admin admin = null;

        try {
            conn = ModuleConnexion.getConnection();
            String query = "SELECT * FROM admin WHERE login = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, login);
            rs = stmt.executeQuery();

            if (rs.next()) {
                admin = new Admin();
                admin.setId_admin(rs.getInt("id_admin"));
                admin.setLogin(rs.getString("login"));
                admin.setNom_ad(rs.getString("nom_ad"));
                admin.setPrenom_ad(rs.getString("prenom_ad"));
                admin.setPassword(rs.getString("password"));
                // Add more fields as needed
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

        return admin;
    }

    @Override
    public boolean updateAdmin(Admin admin) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean success = false;

        try {
            conn = ModuleConnexion.getConnection();
            String query = "UPDATE admin SET nom_ad = ?, prenom_ad = ? WHERE id_admin = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, admin.getNom_ad());
            stmt.setString(2, admin.getPrenom_ad());
            stmt.setInt(3, admin.getId_admin());

            int rowsAffected = stmt.executeUpdate();
            success = rowsAffected > 0;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
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

        return success;
    }
}

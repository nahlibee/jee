package modulee;

public interface AdminDAO {
    boolean authenticate(String login, String password);
    boolean registerAdmin(String login, String password, String nom, String prenom);
    Admin getAdminByLogin(String login);
    boolean updateAdmin(Admin admin);
}

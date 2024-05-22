package modulee;

import java.sql.SQLException;

public interface ProfLogDAO {

    // Méthode pour l'authentification d'un professeur
    boolean login(String email, String password);

    // Méthode pour créer le compte d'un professeur
    void creerCompte(Professeur professeur);
    Professeur getByEmail(String email) throws SQLException;
}

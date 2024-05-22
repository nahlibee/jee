package modulee;

import java.sql.SQLException;

public interface ProfLogDAO {

    // M�thode pour l'authentification d'un professeur
    boolean login(String email, String password);

    // M�thode pour cr�er le compte d'un professeur
    void creerCompte(Professeur professeur);
    Professeur getByEmail(String email) throws SQLException;
}

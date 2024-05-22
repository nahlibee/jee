package modulee;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/authServlet")
public class AuthServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            if (action.equals("login")) {
                processLogin(request, response);
            } else if (action.equals("creerCompte")) {
                processCreerCompte(request, response);
            }
        }
    }

    private void processLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    Connection conn = null;
    HttpSession session = null; // Déclaration unique de la variable session

    try {
        conn = ModuleConnexion.getConnection();
        ProfLogDAO profLogDAO = new ProfLogDAOImpl(conn);

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email != null && password != null && !email.isEmpty() && !password.isEmpty()) {
            if (profLogDAO.login(email, password)) {
                // Authentification réussie, obtenir les informations du professeur
                Professeur professeur = profLogDAO.getByEmail(email);

                // Stocker les informations du professeur dans la session
                session = request.getSession();
                session.setAttribute("professeur", professeur);

                // Rediriger vers la page index2.jsp
                response.sendRedirect("index2.jsp");
            } else {
                // Échec de l'authentification, rediriger vers une page d'erreur ou de connexion
                response.sendRedirect("loginp.jsp?error=1");
            }
        } else {
            // Les champs email ou password sont vides, rediriger vers une page d'erreur
            response.sendRedirect("loginp.jsp?error=2");
        }
    } catch (SQLException | ClassNotFoundException e) {
        e.printStackTrace();
        response.sendRedirect("error.jsp"); // Rediriger vers une page d'erreur générale
    } finally {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}


    private void processCreerCompte(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = null;
        try {
            conn = ModuleConnexion.getConnection();
            ProfLogDAO profLogDAO = new ProfLogDAOImpl(conn);

            // Récupérer les données du formulaire
            int etabId = Integer.parseInt(request.getParameter("id_etab"));
            Etablissement etablissement = new Etablissement();
            etablissement.setId_etab(etabId);

            String nomProfesseur = request.getParameter("nom_prof");
            String prenomProfesseur = request.getParameter("prenom_prof");
            String cin = request.getParameter("cin");
            String numPRP = request.getParameter("numPRP");
            String email = request.getParameter("email");
            String telephone = request.getParameter("telephone");
            String specialite = request.getParameter("specialite");
            String password = request.getParameter("password");

            // Créer l'objet Professeur
            Professeur newProfesseur = new Professeur(etablissement, nomProfesseur, prenomProfesseur, cin, numPRP,
                    email, telephone, specialite, password);

            // Appeler la méthode pour créer le compte
            profLogDAO.creerCompte(newProfesseur);

            // Rediriger vers la page de connexion
            response.sendRedirect("loginp.jsp");
        } catch (SQLException | ClassNotFoundException | NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp"); // Rediriger vers une page d'erreur générale
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

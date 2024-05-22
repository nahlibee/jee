package modulee;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/professeurServlet")
public class ProfesseurServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ProfesseurServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = null;
        Professeur professeur = null;
        try {
            conn = ModuleConnexion.getConnection();
            ProfesseurDAO professeurDAO = new ProfesseurDAOImpl(conn);
            EtablissementDAO etablissementDAO = new EtablissementDAOImpl(conn);

            String action = request.getParameter("action");

            if (action == null || action.equals("list")) {
                List<Professeur> professeurs = professeurDAO.getAll();
                List<Etablissement> etablissements = etablissementDAO.getAll();
                System.out.println("Number of professors: " + professeurs.size());
                request.setAttribute("professeurs", professeurs);
                request.setAttribute("etablissements", etablissements);
                request.getRequestDispatcher("professeur.jsp").forward(request, response);
            } else if (action.equals("ajout")) {
                List<Etablissement> etablissements = etablissementDAO.getAll();
                request.setAttribute("etablissements", etablissements);
                request.getRequestDispatcher("AddProfesseur.jsp").forward(request, response);
            } else if (action.equals("edit")) {
                int professeurId = Integer.parseInt(request.getParameter("id_prof"));
                professeur = professeurDAO.getById(professeurId);
                List<Etablissement> etablissements = etablissementDAO.getAll();
                request.setAttribute("professeur", professeur);
                request.setAttribute("etablissements", etablissements);
                request.getRequestDispatcher("editProfesseur.jsp").forward(request, response);
            } else if (action.equals("supprimer")) {
                int professeurId = Integer.parseInt(request.getParameter("id_prof"));
                professeurDAO.delete(professeurId);
                response.sendRedirect("professeurServlet?action=list");
            } else if (action.equals("consulter")) {
                int professeurId = Integer.parseInt(request.getParameter("id_prof"));
                professeur = professeurDAO.getById(professeurId);
                request.setAttribute("professeur", professeur);
                request.getRequestDispatcher("viewProfesseur.jsp").forward(request, response);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        Connection conn = null;
        try {
            conn = ModuleConnexion.getConnection();
            ProfesseurDAO professeurDAO = new ProfesseurDAOImpl(conn);

            if (action.equals("ajouter")) {
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

                Professeur newProfesseur = new Professeur(etablissement, nomProfesseur, prenomProfesseur, cin, numPRP, email, telephone, specialite, password);
                professeurDAO.insert(newProfesseur);
                response.sendRedirect("professeurServlet?action=list");
            } else if (action.equals("editer")) {
                int professeurId = Integer.parseInt(request.getParameter("id_prof"));
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

                Professeur updatedProfesseur = new Professeur(professeurId, etablissement, nomProfesseur, prenomProfesseur, cin, numPRP, email, telephone, specialite, password);
                professeurDAO.update(updatedProfesseur);
                response.sendRedirect("professeurServlet?action=list");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
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

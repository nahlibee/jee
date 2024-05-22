package modulee;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/demandeServlet")
public class DemandeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DemandeServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = null;
        try {
            conn = ModuleConnexion.getConnection();
            DemandeDAO demandeDAO = new DemandeDAOImpl(conn);
            ProfesseurDAO professeurDAO = new ProfesseurDAOImpl(conn);
            OffreVacationDAO offreVacationDAO = new OffreVacationDAOImpl(conn);

            String action = request.getParameter("action");

            if (action == null || action.equals("list")) {
                // Afficher la liste des demandes
                List<Demande> demandes = demandeDAO.obtenirToutesDemandes();
                List<Professeur> professeurs = professeurDAO.getAll();
                List<OffreVacation> offresVacation = offreVacationDAO.getAll();

                request.setAttribute("demandes", demandes);
                request.setAttribute("professeurs", professeurs);
                request.setAttribute("offresVacation", offresVacation);

                request.getRequestDispatcher("demande-list.jsp").forward(request, response);
            } else if (action.equals("ajout")) {
                // Afficher le formulaire d'ajout
                List<Professeur> professeurs = professeurDAO.getAll();
                List<OffreVacation> offresVacation = offreVacationDAO.getAll();

                request.setAttribute("professeurs", professeurs);
                request.setAttribute("offresVacation", offresVacation);
                request.getRequestDispatcher("addDemande.jsp").forward(request, response);
            } else if (action.equals("edit")) {
                // Afficher le formulaire d'édition
                int demandeId = Integer.parseInt(request.getParameter("id_demande"));
                Demande demande = demandeDAO.obtenirDemandeParId(demandeId);
                List<Professeur> professeurs = professeurDAO.getAll();
                List<OffreVacation> offresVacation = offreVacationDAO.getAll();

                request.setAttribute("demande", demande);
                request.setAttribute("professeurs", professeurs);
                request.setAttribute("offresVacation", offresVacation);
                request.getRequestDispatcher("editDemande.jsp").forward(request, response);
            } else if (action.equals("consulter")) {
                // Afficher la page de consultation
                int demandeId = Integer.parseInt(request.getParameter("id_demande"));
                Demande demande = demandeDAO.obtenirDemandeParId(demandeId);
                request.setAttribute("demande", demande);
                request.getRequestDispatcher("viewDemande.jsp").forward(request, response);
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
            DemandeDAO demandeDAO = new DemandeDAOImpl(conn);

            if (action.equals("ajouter")) {
                // Ajouter une nouvelle demande
                int professeurId = Integer.parseInt(request.getParameter("id_prof"));
                int offreVacationId = Integer.parseInt(request.getParameter("id_offre"));
                Professeur professeur = new Professeur();
                professeur.setId_prof(professeurId);

                OffreVacation offreVacation = new OffreVacation();
                offreVacation.setId_offre(offreVacationId);

                String etatDemande = request.getParameter("etat_demande");

                Demande newDemande = new Demande(professeur, offreVacation, new Date(), etatDemande);
                demandeDAO.ajouterDemande(newDemande);
                response.sendRedirect("demandeServlet?action=list");
            } else if (action.equals("editer")) {
                // Mettre à jour une demande existante
                int demandeId = Integer.parseInt(request.getParameter("id_demande"));
                int professeurId = Integer.parseInt(request.getParameter("id_prof"));
                int offreVacationId = Integer.parseInt(request.getParameter("id_offre"));

                Professeur professeur = new Professeur();
                professeur.setId_prof(professeurId);

                OffreVacation offreVacation = new OffreVacation();
                offreVacation.setId_offre(offreVacationId);

                String etatDemande = request.getParameter("etat_demande");

                Demande updatedDemande = new Demande(demandeId, professeur, offreVacation, new Date(), etatDemande);
                demandeDAO.mettreAJourDemande(updatedDemande);
                response.sendRedirect("demandeServlet?action=list");
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

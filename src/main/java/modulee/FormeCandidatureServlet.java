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

@WebServlet("/formecandidatureServlet")
public class FormeCandidatureServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public FormeCandidatureServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = null;
        FormeCandidature formeCandidature = null;
        try {
            conn = ModuleConnexion.getConnection();
            FormeCandidatureDAO formeCandidatureDAO = new FormeCandidatureDAOImpl(conn);
            ProfesseurDAO professeurDAO = new ProfesseurDAOImpl(conn);

            String action = request.getParameter("action");

            if (action == null || action.equals("list")) {
                List<FormeCandidature> formeCandidatures = formeCandidatureDAO.getAll();
                List<Professeur> professeurs = professeurDAO.getAll();
                System.out.println("Number of candidature forms: " + formeCandidatures.size());
                request.setAttribute("formecandidatures", formeCandidatures);
                request.setAttribute("professeurs", professeurs);
                request.getRequestDispatcher("formecandidature.jsp").forward(request, response);
            } else if (action.equals("ajout")) {
                List<Professeur> professeurs = professeurDAO.getAll();
                request.setAttribute("professeurs", professeurs);
                request.getRequestDispatcher("AddFormeCandidature.jsp").forward(request, response);
            } else if (action.equals("edit")) {
                int formeCandidatureId = Integer.parseInt(request.getParameter("id_forme"));
                formeCandidature = formeCandidatureDAO.getById(formeCandidatureId);
                List<Professeur> professeurs = professeurDAO.getAll();
                request.setAttribute("formecandidature", formeCandidature);
                request.setAttribute("professeurs", professeurs);
                request.getRequestDispatcher("editFormeCandidature.jsp").forward(request, response);
            } else if (action.equals("supprimer")) {
                int formeCandidatureId = Integer.parseInt(request.getParameter("id_forme"));
                formeCandidatureDAO.delete(formeCandidatureId);
                response.sendRedirect("formecandidatureServlet?action=list");
            } else if (action.equals("consulter")) {
                int formeCandidatureId = Integer.parseInt(request.getParameter("id_forme"));
                formeCandidature = formeCandidatureDAO.getById(formeCandidatureId);
                request.setAttribute("formecandidature", formeCandidature);
                request.getRequestDispatcher("viewFormeCandidature.jsp").forward(request, response);
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
            FormeCandidatureDAO formeCandidatureDAO = new FormeCandidatureDAOImpl(conn);

            if (action.equals("ajouter")) {
                int profId = Integer.parseInt(request.getParameter("id_prof"));
                Professeur professeur = new Professeur();
                professeur.setId_prof(profId);

                int nbHeurePermis = Integer.parseInt(request.getParameter("nb_heure_permis"));
                int nbHeureOccupe = Integer.parseInt(request.getParameter("nb_heure_occupe"));

                FormeCandidature newFormeCandidature = new FormeCandidature(professeur, nbHeurePermis, nbHeureOccupe);
                formeCandidatureDAO.insert(newFormeCandidature);
                response.sendRedirect("formecandidatureServlet?action=list");
            } else if (action.equals("editer")) {
                int formeCandidatureId = Integer.parseInt(request.getParameter("id_forme"));
                int profId = Integer.parseInt(request.getParameter("id_prof"));
                Professeur professeur = new Professeur();
                professeur.setId_prof(profId);

                int nbHeurePermis = Integer.parseInt(request.getParameter("nb_heure_permis"));
                int nbHeureOccupe = Integer.parseInt(request.getParameter("nb_heure_occupe"));

                FormeCandidature updatedFormeCandidature = new FormeCandidature(formeCandidatureId, professeur, nbHeurePermis, nbHeureOccupe);
                formeCandidatureDAO.update(updatedFormeCandidature);
                response.sendRedirect("formecandidatureServlet?action=list");
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

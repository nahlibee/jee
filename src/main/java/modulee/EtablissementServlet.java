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

@WebServlet("/etablissementServlet")
public class EtablissementServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EtablissementServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = null;
        Etablissement etablissement = null;
        try {
            conn = ModuleConnexion.getConnection();
            EtablissementDAO etabDAO = new EtablissementDAOImpl(conn);
            String action = request.getParameter("action");

            if (action == null || action.equals("list")) {
                List<Etablissement> etablissements = etabDAO.getAll();
                System.out.println("Number of etablissements: " + etablissements.size());
                request.setAttribute("etablissements", etablissements);
                request.getRequestDispatcher("etablissement.jsp").forward(request, response);
            } else if (action.equals("ajout")) {
                request.getRequestDispatcher("AddEtablissement.jsp").forward(request, response);
            } else if (action.equals("edit")) {
                int etabId = Integer.parseInt(request.getParameter("id_etab"));
                etablissement = etabDAO.getById(etabId);
                request.setAttribute("etablissement", etablissement);
                request.getRequestDispatcher("editEtablissement.jsp").forward(request, response);
            } else if (action.equals("supprimer")) {
                int etabId = Integer.parseInt(request.getParameter("id_etab"));
                etabDAO.delete(etabId);
                response.sendRedirect("etablissementServlet?action=list");
            } else if (action.equals("consulter")) {
                int etabId = Integer.parseInt(request.getParameter("id_etab"));
                etablissement = etabDAO.getById(etabId);
                request.setAttribute("etablissement", etablissement);
                request.getRequestDispatcher("viewEtablissement.jsp").forward(request, response);
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
            EtablissementDAO etabDAO = new EtablissementDAOImpl(conn);

            if (action.equals("ajouter")) {
                String nom_etab = request.getParameter("nom_etab");
                String abreviation = request.getParameter("abreviation");
                String ville_etab = request.getParameter("ville_etab");
                Etablissement newEtablissement = new Etablissement(nom_etab, abreviation,ville_etab);
                etabDAO.insert(newEtablissement);
                response.sendRedirect("etablissementServlet?action=list");
            } else if (action.equals("editer")) {
                int etabId = Integer.parseInt(request.getParameter("id_etab"));
                String nom_etab = request.getParameter("nom_etab");
                String abreviation =request.getParameter("abreviation");
                String ville_etab = request.getParameter("ville_etab");
                Etablissement updatedEtablissement = new Etablissement(etabId, nom_etab, abreviation,ville_etab);
                etabDAO.update(updatedEtablissement);
                response.sendRedirect("etablissementServlet?action=list");
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

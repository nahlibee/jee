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

@WebServlet("/offreVacationServlet")
public class OffreVacationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public OffreVacationServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = null;
        OffreVacation offrevacation = null;
        try {
            conn = ModuleConnexion.getConnection();
            OffreVacationDAO offreVacationDAO = new OffreVacationDAOImpl(conn);
            ElementDAO elementDAO = new ElementDAOImpl(conn);

            String action = request.getParameter("action");

            if (action == null || action.equals("list")) {
                List<OffreVacation> offresVacation = offreVacationDAO.getAll();
                List<Element> elements = elementDAO.getAll();
                System.out.println("Number of vacation offers: " + offresVacation.size());
                request.setAttribute("offresVacation", offresVacation);
                request.setAttribute("elements", elements);
                request.getRequestDispatcher("offrevacation.jsp").forward(request, response);
            } else if (action.equals("ajout")) {
                List<Element> elements = elementDAO.getAll();
                request.setAttribute("elements", elements);
                request.getRequestDispatcher("AddOffreVacation.jsp").forward(request, response);
            } else if (action.equals("edit")) {
                int offreVacationId = Integer.parseInt(request.getParameter("id_offre"));
                offrevacation = offreVacationDAO.getById(offreVacationId);
                List<Element> elements = elementDAO.getAll();
                request.setAttribute("offrevacation", offrevacation);
                request.setAttribute("elements", elements);
                request.getRequestDispatcher("editOffreVacation.jsp").forward(request, response);
            } else if (action.equals("supprimer")) {
                int offreVacationId = Integer.parseInt(request.getParameter("id_offre"));
                offreVacationDAO.delete(offreVacationId);
                response.sendRedirect("offreVacationServlet?action=list");
            } else if (action.equals("consulter")) {
                int offreVacationId = Integer.parseInt(request.getParameter("id_offre"));
                offrevacation = offreVacationDAO.getById(offreVacationId);
                request.setAttribute("offrevacation", offrevacation);
                request.getRequestDispatcher("viewOffreVacation.jsp").forward(request, response);
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
            OffreVacationDAO offreVacationDAO = new OffreVacationDAOImpl(conn);

            if (action.equals("ajouter")) {
                int elementId = Integer.parseInt(request.getParameter("id_element"));
                Element element = new Element();
                element.setId_element(elementId);

                String description = request.getParameter("description");
                String poste = request.getParameter("poste");
                String profil = request.getParameter("profil");

                OffreVacation newOffreVacation = new OffreVacation(element, description, poste, profil);
                offreVacationDAO.insert(newOffreVacation);
                response.sendRedirect("offreVacationServlet?action=list");
            } else if (action.equals("editer")) {
                int offreVacationId = Integer.parseInt(request.getParameter("id_offre"));
                int elementId = Integer.parseInt(request.getParameter("id_element"));
                Element element = new Element();
                element.setId_element(elementId);

                String description = request.getParameter("description");
                String poste = request.getParameter("poste");
                String profil = request.getParameter("profil");

                OffreVacation updatedOffreVacation = new OffreVacation(offreVacationId, element, description, poste, profil);
                offreVacationDAO.update(updatedOffreVacation);
                response.sendRedirect("offreVacationServlet?action=list");
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

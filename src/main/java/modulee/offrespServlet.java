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

@WebServlet("/offrespServlet")
public class offrespServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public offrespServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = null;
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
                request.getRequestDispatcher("poffres.jsp").forward(request, response);
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
}

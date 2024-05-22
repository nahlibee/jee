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

@WebServlet("/moduleServlet")
public class ModuleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ModuleServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = null;
        Module module = null;
        try {
            conn = ModuleConnexion.getConnection();
            ModuleDAO moduleDAO = new ModuleDAOImpl(conn);
            String action = request.getParameter("action");

            if (action == null || action.equals("list")) {
                List<Module> modules = moduleDAO.getAll();
                System.out.println("Number of modules: " + modules.size());
                request.setAttribute("modules", modules);
                request.getRequestDispatcher("module.jsp").forward(request, response);
            } else if (action.equals("ajout")) {
                request.getRequestDispatcher("AddModule.jsp").forward(request, response);
            } else if (action.equals("edit")) {
                int moduleId = Integer.parseInt(request.getParameter("id_module"));
                module = moduleDAO.getById(moduleId);
                request.setAttribute("module", module);
                request.getRequestDispatcher("editModule.jsp").forward(request, response);
            } else if (action.equals("supprimer")) {
                int moduleId = Integer.parseInt(request.getParameter("id_module"));
                moduleDAO.delete(moduleId);
                response.sendRedirect("moduleServlet?action=list");
            } else if (action.equals("consulter")) {
                int moduleId = Integer.parseInt(request.getParameter("id_module"));
                module = moduleDAO.getById(moduleId);
                request.setAttribute("module", module);
                request.getRequestDispatcher("viewModule.jsp").forward(request, response);
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
            ModuleDAO moduleDAO = new ModuleDAOImpl(conn);

            if (action.equals("ajouter")) {
                String nom_module = request.getParameter("nom_module");
                String abrev_module = request.getParameter("abrev_module");
                Module newModule = new Module(nom_module, abrev_module);
                moduleDAO.insert(newModule);
                response.sendRedirect("moduleServlet?action=list");
            } else if (action.equals("editer")) {
                int moduleId = Integer.parseInt(request.getParameter("id_module"));
                String nom_module = request.getParameter("nom_module");
                String abrev_module = request.getParameter("abrev_module");
                Module updatedModule = new Module(moduleId, nom_module, abrev_module);
                moduleDAO.update(updatedModule);
                response.sendRedirect("moduleServlet?action=list");
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

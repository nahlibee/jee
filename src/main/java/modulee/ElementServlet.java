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

@WebServlet("/elementServlet")
public class ElementServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ElementServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = null;
        Element element = null;
        try {
            conn = ModuleConnexion.getConnection();
            ElementDAO elementDAO = new ElementDAOImpl(conn);
            ModuleDAO moduleDAO = new ModuleDAOImpl(conn);

            String action = request.getParameter("action");

            if (action == null || action.equals("list")) {
                List<Element> elements = elementDAO.getAll();
                List<Module> modules = moduleDAO.getAll();  // Obtenez la liste des modules ici
                System.out.println("Number of elements: " + elements.size());
                request.setAttribute("elements", elements);
                request.setAttribute("modules", modules);  // Ajoutez la liste des modules à la portée de la requête
                request.getRequestDispatcher("element.jsp").forward(request, response);
            } else if (action.equals("ajout")) {
                List<Module> modules = moduleDAO.getAll();  // Obtenez la liste des modules ici
                request.setAttribute("modules", modules);
                request.getRequestDispatcher("AddElement.jsp").forward(request, response);
            } else if (action.equals("edit")) {
                int elementId = Integer.parseInt(request.getParameter("id_element"));
                element = elementDAO.getById(elementId);
                List<Module> modules = moduleDAO.getAll();  // Obtenez la liste des modules ici
                request.setAttribute("element", element);
                request.setAttribute("modules", modules);
                request.getRequestDispatcher("editElement.jsp").forward(request, response);
            } else if (action.equals("supprimer")) {
                int elementId = Integer.parseInt(request.getParameter("id_element"));
                elementDAO.delete(elementId);
                response.sendRedirect("elementServlet?action=list");
            } else if (action.equals("consulter")) {
                int elementId = Integer.parseInt(request.getParameter("id_element"));
                element = elementDAO.getById(elementId);
                request.setAttribute("element", element);
                request.getRequestDispatcher("viewElement.jsp").forward(request, response);
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
            ElementDAO elementDAO = new ElementDAOImpl(conn);

            if (action.equals("ajouter")) {
                int moduleId = Integer.parseInt(request.getParameter("id_module"));
                Module module = new Module();
                module.setId_module(moduleId); // Initialisez le module comme nécessaire

                String nomElement = request.getParameter("nom_element");
                int volumehoraireElement = Integer.parseInt(request.getParameter("volumehoraire_element"));

                Element newElement = new Element(module, nomElement, volumehoraireElement);
                elementDAO.insert(newElement);
                response.sendRedirect("elementServlet?action=list");
            } else if (action.equals("editer")) {
                int elementId = Integer.parseInt(request.getParameter("id_element"));
                int moduleId = Integer.parseInt(request.getParameter("id_module"));
                Module module = new Module();
                module.setId_module(moduleId); // Initialisez le module comme nécessaire

                String nomElement = request.getParameter("nom_element");
                int volumehoraireElement = Integer.parseInt(request.getParameter("volumehoraire_element"));

                Element updatedElement = new Element(elementId, module, nomElement, volumehoraireElement);
                elementDAO.update(updatedElement);
                response.sendRedirect("elementServlet?action=list");
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

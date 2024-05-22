package modulee;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private AdminDAO adminDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        adminDAO = new AdminDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Inside doPost"); // Add this line for debugging

        String action = request.getParameter("action");

        if ("login".equals(action)) {
            performLogin(request, response);
        } else if ("register".equals(action)) {
            performRegister(request, response);
        } else {
            response.sendRedirect("login.jsp");
        }
    }


    private void performLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (adminDAO.authenticate(login, password)) {
            // Authentification réussie, enregistrez les informations en session
            HttpSession session = request.getSession();
            session.setAttribute("login", login);
            
            // Retrieve other admin details from the database
            Admin admin = adminDAO.getAdminByLogin(login);
            session.setAttribute("nom", admin.getNom_ad());
            session.setAttribute("prenom", admin.getPrenom_ad());
            session.setAttribute("login", login);
            session.setAttribute("id", admin.getId_admin()); // Ajoutez l'ID
            session.setAttribute("password", admin.getPassword()); // Ajoutez le mot de passe

            // Redirigez vers la page adminProfile.jsp
            response.sendRedirect("index.jsp");
        } else {
            // Authentification échouée, rediriger vers login.jsp avec un paramètre d'erreur
            response.sendRedirect("login.jsp?error=auth");
        }
    }
    private void performRegister(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");

        if (adminDAO.registerAdmin(login, password, nom, prenom)) {
            // Inscription réussie, rediriger vers login.jsp
            response.sendRedirect("login.jsp");
        } else {
            // Inscription échouée, rediriger vers signup.jsp avec un paramètre d'erreur
            response.sendRedirect("signup.jsp?error=register");
        }
    }
}

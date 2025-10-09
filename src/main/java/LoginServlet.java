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

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email == null || password == null || 
            email.trim().isEmpty() || password.trim().isEmpty()) {
            response.getWriter().println("<h3>Error: All fields are required.</h3>");
            response.getWriter().println("<a href='login.html'>Go Back</a>");
            return;
        }

        UserDAO dao = new UserDAO();
        boolean valid = dao.validateUser(email, password);

        if (valid) {
            HttpSession session = request.getSession();
            session.setAttribute("email", email);
            String role = dao.getUserRole(email);
            session.setAttribute("role", role);
            response.sendRedirect("dashboard.html");
        } else {
            response.getWriter().println("<h3>Invalid credentials. Please try again.</h3>");
            response.getWriter().println("<a href='login.html'>Go Back</a>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("login.html");
    }
}
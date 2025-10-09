import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("text/html");
        
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        
        // Validation
        if (name == null || email == null || password == null || role == null || 
            name.trim().isEmpty() || email.trim().isEmpty() || password.trim().isEmpty()) {
            response.getWriter().println("<h3>Error: All fields are required.</h3>");
            response.getWriter().println("<a href='register.html'>Go Back</a>");
            return;
        }
        
        // Email format validation
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            response.getWriter().println("<h3>Error: Invalid email format.</h3>");
            response.getWriter().println("<a href='register.html'>Go Back</a>");
            return;
        }
        
        UserDAO dao = new UserDAO();
        
        // Check if email already exists first
        if (dao.emailExists(email)) {
            response.getWriter().println("<h3>Error: Email already exists. Please use a different email.</h3>");
            response.getWriter().println("<a href='register.html'>Go Back</a>");
            return;
        }
        
        boolean success = dao.registerUser(name, email, password, role);
        
        if (success) {
            response.getWriter().println("<!DOCTYPE html>");
            response.getWriter().println("<html><head><title>Success</title></head><body>");
            response.getWriter().println("<h3>Registration successful!</h3>");
            response.getWriter().println("<p>You can now <a href='login.html'>login here</a></p>");
            response.getWriter().println("</body></html>");
        } else {
            response.getWriter().println("<h3>Error: Registration failed. Please check server logs for details.</h3>");
            response.getWriter().println("<a href='register.html'>Go Back</a>");
        }
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.sendRedirect("register.html");
    }
}
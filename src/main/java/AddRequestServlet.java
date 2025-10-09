import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/addRequest")
public class AddRequestServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("email") == null) {
            response.sendRedirect("login.html");
            return;
        }

        String requesterName = request.getParameter("requester_name");
        String typeOfDisaster = request.getParameter("type_of_disaster");
        String location = request.getParameter("location");

        if (requesterName == null || typeOfDisaster == null || location == null ||
            requesterName.trim().isEmpty() || typeOfDisaster.trim().isEmpty() || location.trim().isEmpty()) {
            response.getWriter().println("<h3>Error: All fields are required.</h3>");
            response.getWriter().println("<a href='addRequest.html'>Go Back</a>");
            return;
        }

        RequestDAO dao = new RequestDAO();
        boolean success = dao.addRequest(requesterName, typeOfDisaster, location);

        if (success) {
            response.sendRedirect("viewRequests");
        } else {
            response.getWriter().println("<h3>Error: Could not add request.</h3>");
            response.getWriter().println("<a href='addRequest.html'>Go Back</a>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("email") == null) {
            response.sendRedirect("login.html");
            return;
        }
        response.sendRedirect("addRequest.html");
    }
}
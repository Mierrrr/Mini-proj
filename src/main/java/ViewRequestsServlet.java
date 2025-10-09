import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/viewRequests")
public class ViewRequestsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("email") == null) {
            response.sendRedirect("login.html");
            return;
        }

        RequestDAO dao = new RequestDAO();
        List<Request> requests = dao.getAllRequests();

        response.getWriter().println("<!DOCTYPE html>");
        response.getWriter().println("<html>");
        response.getWriter().println("<head>");
        response.getWriter().println("<title>View Requests</title>");
        response.getWriter().println("<style>");
        response.getWriter().println("body { font-family: Arial, sans-serif; margin: 20px; }");
        response.getWriter().println("table { border-collapse: collapse; width: 100%; }");
        response.getWriter().println("th, td { border: 1px solid #ddd; padding: 12px; text-align: left; }");
        response.getWriter().println("th { background-color: #4CAF50; color: white; }");
        response.getWriter().println("tr:nth-child(even) { background-color: #f2f2f2; }");
        response.getWriter().println(".btn { padding: 8px 16px; margin: 5px; text-decoration: none; }");
        response.getWriter().println(".pending { color: orange; font-weight: bold; }");
        response.getWriter().println(".accepted { color: blue; font-weight: bold; }");
        response.getWriter().println(".completed { color: green; font-weight: bold; }");
        response.getWriter().println("</style>");
        response.getWriter().println("</head>");
        response.getWriter().println("<body>");
        response.getWriter().println("<h2>Disaster Relief Requests</h2>");
        
        if (requests.isEmpty()) {
            response.getWriter().println("<p>No requests found.</p>");
        } else {
            response.getWriter().println("<table>");
            response.getWriter().println("<tr><th>ID</th><th>Requester Name</th><th>Type of Disaster</th><th>Location</th><th>Status</th></tr>");
            for (Request req : requests) {
                response.getWriter().println("<tr>");
                response.getWriter().println("<td>" + req.getId() + "</td>");
                response.getWriter().println("<td>" + req.getRequesterName() + "</td>");
                response.getWriter().println("<td>" + req.getTypeOfDisaster() + "</td>");
                response.getWriter().println("<td>" + req.getLocation() + "</td>");
                response.getWriter().println("<td class='" + req.getStatus() + "'>" + req.getStatus() + "</td>");
                response.getWriter().println("</tr>");
            }
            response.getWriter().println("</table>");
        }
        
        response.getWriter().println("<br><a href='dashboard.html' class='btn'>Back to Dashboard</a>");
        response.getWriter().println("</body>");
        response.getWriter().println("</html>");
    }
}
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RequestDAO {

    public boolean addRequest(String requesterName, String typeOfDisaster, String location) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBConnection.getConnection();
            String query = "INSERT INTO requests(requester_name, type_of_disaster, location) VALUES (?, ?, ?)";
            ps = conn.prepareStatement(query);
            ps.setString(1, requesterName);
            ps.setString(2, typeOfDisaster);
            ps.setString(3, location);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<Request> getAllRequests() {
        List<Request> requests = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            String query = "SELECT id, requester_name, type_of_disaster, location, status FROM requests";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Request req = new Request();
                req.setId(rs.getInt("id"));
                req.setRequesterName(rs.getString("requester_name"));
                req.setTypeOfDisaster(rs.getString("type_of_disaster"));
                req.setLocation(rs.getString("location"));
                req.setStatus(rs.getString("status"));
                requests.add(req);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return requests;
    }

    public boolean updateRequestStatus(int id, String status) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBConnection.getConnection();
            String query = "UPDATE requests SET status = ? WHERE id = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, status);
            ps.setInt(2, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
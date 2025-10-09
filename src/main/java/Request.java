public class Request {
    private int id;
    private String requesterName;
    private String typeOfDisaster;
    private String location;
    private String status;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getRequesterName() { return requesterName; }
    public void setRequesterName(String requesterName) { this.requesterName = requesterName; }

    public String getTypeOfDisaster() { return typeOfDisaster; }
    public void setTypeOfDisaster(String typeOfDisaster) { this.typeOfDisaster = typeOfDisaster; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
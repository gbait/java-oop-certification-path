public class NetworkDevice {

    private String name;
    private String ipAddress;
    private boolean isOnline;

    public NetworkDevice(String name, String ipAddress, boolean isOnline) {
        this.name = name;
        this.ipAddress = ipAddress;
        this.isOnline = isOnline;
    }

    public String getName() { return name; }
    public String getIpAddress() { return ipAddress; }
    public boolean isOnline() { return isOnline; }
    public void setOnline(boolean isOnline) { this.isOnline = isOnline; }

    public String getStatus() {
        return isOnline ? "ONLINE" : "OFFLINE";
    }

    public void printReport() {
        System.out.println("--- DEVICE REPORT ---");
        System.out.println("Name:   " + name);
        System.out.println("IP:     " + ipAddress);
        System.out.println("Status: " + getStatus());
    }
}

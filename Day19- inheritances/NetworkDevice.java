public class NetworkDevice {
    
    // atributos comunes a todos los dispositivos
    private String name;
    private String ipAdress;
    private boolean isOnline;
    
    // constructor de la clase padre
    public NetworkDevice(String name, String ipAdress, boolean isOnline) {
        this.name = name;
        this.ipAdress = ipAdress;
        this.isOnline = isOnline;
    }
    
    // getters
    public String getName() { return name; }
    public String getIpAdress() { return ipAdress; }
    public boolean isOnline() { return isOnline; }
    
    // setters
    public void setName(String name) { this.name = name; }
    public void setOnline(boolean isOnline) { this.isOnline = isOnline; }
    
    // método común a todos los dispositivos 
    public String getStatus() {
        return isOnline ? "ONLINE" : "OFFLINE";
    }
    
    // método que las hijas sobreescriben
    public void printReport() {
        System.out.println("---DEVICE REPORT---");
        System.out.println("Name:   " + name);
        System.out.println("IP:     " + ipAdress);
        System.out.println("Status: " + getStatus());
    }
}

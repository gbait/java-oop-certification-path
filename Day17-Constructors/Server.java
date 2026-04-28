public class Server {
    
    // atributos
    String name;
    int totalBandwidth;
    int usedBandwidth;
    boolean isOnline;
    
    // CONSTRUCTOR - se ejecuta automaticamente al hacer new Serever()
    public Server(String name, int totalBandwidth, int usedBandwidth, boolean isOnline) {
        this.name = name;
        this.totalBandwidth = totalBandwidth;
        this.usedBandwidth = usedBandwidth;
        this.isOnline = isOnline;
    }
    
    // CONSTRUCTOR SOBRECARGADO - Crea un servidor offline sin datos de bandwidth 
    public Server(String name) {
        this.name = name;
        this.totalBandwidth = 0;
        this.usedBandwidth = 0;
        this.isOnline = false;
    }
    
    public double calculateUsage() {
        if (totalBandwidth == 0) return 0;
        return (double) usedBandwidth / totalBandwidth * 100;
    }
    
    public String getStatus() {
        if (!isOnline) return "OFFLINE";
        double usage = calculateUsage();
        if (usage >= 90) return "CRITICAL";
        else if (usage >= 70) return "WARNING";
        else return "ONLINE";
    }
    
    public void printReport() {
        System.out.println("--- SERVER REPORT ---");
        System.out.println("Name:      " + name);
        System.out.println("Bandwidth: " + usedBandwidth + "/" + totalBandwidth + " Mbps");
        System.out.println("Usage:     " + calculateUsage() + "%");
        System.out.println("Status:    " + getStatus());
    }
}

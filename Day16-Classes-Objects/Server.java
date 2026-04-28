public class Server { //esta es la clase 

    //ATRIBUTOS- datos que tiene cada servidor
    String name;
    int totalBandwidth;
    int usedBandwidth;
    boolean isOnline;
    
    //MÉTODO - el comportamiento que tiene cada servidor
    public double caluclateUsage() {
        return (double) usedBandwidth / totalBandwidth * 100;
    }
    
    public String getStatus() {
        if (!isOnline) {
            return "OFFLINE";
        }
        double usage = caluclateUsage();
        if (usage >= 90) return "CRITICAL";
        else if (usage >= 70) return "WARNING";
        else return "ONLINE";
    }
    
    public void printReport() {
        System.out.println("--- SERVER REPORT ---");
        System.out.println("Name:       " + name);
        System.out.println("Bandwidth:  " + usedBandwidth + "/" + totalBandwidth +  " Mbps");
        System.out.println("Usage:      " + caluclateUsage() + "%");
        System.out.println("Status:     " + getStatus());
    }
}

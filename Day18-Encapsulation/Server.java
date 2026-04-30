public class Server {

    private String name;
    private int totalBandwidth;
    private int usedBandwidth;
    private boolean isOnline;

    // constructor completo
    public Server(String name, int totalBandwidth, int usedBandwidth, boolean isOnline) {
        this.name = name;
        this.totalBandwidth = totalBandwidth;
        this.usedBandwidth = usedBandwidth;
        this.isOnline = isOnline;
    }

    // constructor sobrecargado
    public Server(String name) {
        this.name = name;
        this.totalBandwidth = 0;
        this.usedBandwidth = 0;
        this.isOnline = false;
    }

    // GETTERS
    public String getName() {
        return name;
    }

    public int getTotalBandwidth() {
        return totalBandwidth;
    }

    public int getUsedBandwidth() {
        return usedBandwidth;
    }

    public boolean isOnline() {
        return isOnline;
    }

    // SETTERS
    public void setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        } else {
            System.out.println("ERROR: Name cannot be null or empty");
        }
    }

    public void setTotalBandwidth(int totalBandwidth) {
        if (totalBandwidth >= 0) {
            this.totalBandwidth = totalBandwidth;
        } else {
            System.out.println("ERROR: Bandwidth cannot be negative");
            this.totalBandwidth = 0;
        }
    }

    public void setUsedBandwidth(int usedBandwidth) {
        if (usedBandwidth >= 0 && usedBandwidth <= totalBandwidth) {
            this.usedBandwidth = usedBandwidth;
        } else {
            System.out.println("ERROR: Used bandwidth out of range");
            this.usedBandwidth = 0;
        }
    }

    public void setOnline(boolean isOnline) {
        this.isOnline = isOnline;
    }

    // MÉTODOS DE LÓGICA
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
        System.out.println("Name:      " + getName());
        System.out.println("Bandwidth: " + getUsedBandwidth() + "/" + getTotalBandwidth() + " Mbps");
        System.out.println("Usage:     " + calculateUsage() + "%");
        System.out.println("Status:    " + getStatus());
    }
}

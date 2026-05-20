public class Server extends NetworkDevice {

    private int totalBandwidth;
    private int usedBandwidth;

    public Server(String name, String ipAddress, boolean isOnline,
                  int totalBandwidth, int usedBandwidth) {
        super(name, ipAddress, isOnline);
        this.totalBandwidth = totalBandwidth;
        this.usedBandwidth = usedBandwidth;
    }

    public double calculateUsage() {
        if (totalBandwidth == 0) return 0;
        return (double) usedBandwidth / totalBandwidth * 100;
    }

    @Override
    public void printReport() {
        super.printReport();
        System.out.println("Bandwidth: " + usedBandwidth + "/" + totalBandwidth + " Mbps");
        System.out.println("Usage:     " + calculateUsage() + "%");
    }
}

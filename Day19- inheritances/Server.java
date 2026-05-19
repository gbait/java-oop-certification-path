public class Server extends NetworkDevice { // extends = hereda de NetworkDevice

    // atributos propios del servidor - los comunes los hereda del padre
    private int totalBandwidth;
    private int usedBandwidth;
    
    
    // constructor del servidor
    public Server(String name, String ipAdress, boolean isOnline, int totalBandwidth, int usedBandwidth) {
        super(name, ipAdress, isOnline); // llama al constructor del padre
        this.totalBandwidth = totalBandwidth;
        this.usedBandwidth = usedBandwidth;
    } 
    
    // getters propios
    public int getTotalBandwidth() { return totalBandwidth; }
    public int getUsedBandwidth() { return usedBandwidth; }
    
    // método propio
    public double calculateUsage() {
        if (totalBandwidth == 0) return 0;
        return (double) usedBandwidth / totalBandwidth * 100;
    }
    
    // sobreescribimos printReport() del padre para añadir mas info
    @Override
    public void printReport() {
        super.printReport();  // llama al printReport() del poadre PRIMERO
        System.out.println("Bandwidth:  " + usedBandwidth + "/" + totalBandwidth + " Mbps ");
        System.out.println("Usage:      " +  calculateUsage() +  "%");
    }
    
}

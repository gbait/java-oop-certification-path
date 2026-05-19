public class Router extends NetworkDevice {
    
    
    // atributos propios del Router
    private int  numberOfPorts;
    private String routingProtocol;
    
    public Router(String name, String ipAdress, boolean isOnline, int numberOfPorts, String routingProtocol) {
        super(name, ipAdress, isOnline); // llama al constructor del padre 
        this.numberOfPorts = numberOfPorts;
        this.routingProtocol = routingProtocol;
    }
    
    // getter propios de la clase Router
    public int getNumberOfPorts() { return numberOfPorts; }
    public String getRoutingProtocol() { return routingProtocol; }
    
    @Override
    public void printReport() {
        super.printReport(); //llama al printReport() del padre PRIMERO
        System.out.println("Ports:      " + numberOfPorts);
        System.out.println("Protocol:   " + routingProtocol);
    }
}

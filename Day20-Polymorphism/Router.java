public class Router extends NetworkDevice {

    private int numberOfPorts;
    private String routingProtocol;

    public Router(String name, String ipAddress, boolean isOnline,
                  int numberOfPorts, String routingProtocol) {
        super(name, ipAddress, isOnline);
        this.numberOfPorts = numberOfPorts;
        this.routingProtocol = routingProtocol;
    }

    @Override
    public void printReport() {
        super.printReport();
        System.out.println("Ports:    " + numberOfPorts);
        System.out.println("Protocol: " + routingProtocol);
    }
}

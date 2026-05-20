public class Day20Polymorphism {
    
    public static void main (String[] args) {
        // POLIMORFISMO - variable tipo padre, objeto tipo hijo
        NetworkDevice device1 = new Server("WebServer-01", "192.168.1.10", true, 1000, 850);
        NetworkDevice device2 = new Router("CoreRouter-01", "192.168.1.1", true, 24, "OSPF");
        NetworkDevice device3 = new Server("DBServer-01", "192.168.1.20", true, 500, 120);
        
        // array de tipo padre que contiene objetos de distintos tipos hijos 
        NetworkDevice[] network = {device1, device2, device3};
        
        System.out.println("=== NETWORK REPORT ===");
        System.out.println("Total devices: " + network.length);
        System.out.println();
        
        // el bucle llama a printReport() de cada objeto 
        // Java ejecuta automáticamente la versión correcta de cada hijo 
        for (NetworkDevice device : network) {
            device.printReport(); // polimorfismo en acción
            System.out.println();
        }
        
        // instanceof - comprueba el tipo real del objeto
        System.out.println("==== DEVICE TYPE CHECK ===");
        for (NetworkDevice device : network) {
            if (device instanceof Server) {
                System.out.println(device.getName() + " is a Server");
            } else if (device instanceof Router) {
                System.out.println(device.getName() + " is a Router");
            }
        }
        
        // casting - recuperar el tipo de hijo desde la variable padre
        System.out.println("\n=== CASTING ===");
        NetworkDevice nd = new Server("FileServer-01", "192.168.1.30", true, 200, 150);
        Server s = (Server) nd; // cast de NetworkDevice a Server
        System.out.println("Usage:  " + s.calculateUsage() + "%");
    }
}

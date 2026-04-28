public class Day16ClassesAndObjects { // clase ppal con el main
    public static void main (String[] args) {
        // Creamos el primer objeto - instanciamos la clase Server
        Server server1 = new Server();
        server1.name = "WebServer-01";
        server1.totalBandwidth = 1000;
        server1.usedBandwidth = 850;
        server1.isOnline = true;
        
        // Creamos el segundo objeto - mismo plano, distintos datos 
        Server server2 = new Server();
        server2.name = "DBServer-01";
        server2.totalBandwidth = 500;
        server2.usedBandwidth = 120;
        server2.isOnline = true;
        
        // Creamos el tercer objeto - servidor caido 
        Server server3 = new Server();
        server3.name = "FileServer-01";
        server3.totalBandwidth = 200;
        server3.usedBandwidth = 0;
        server3.isOnline = false;
        
        // Llamamos a los métodos de cada objeto
        server1.printReport();
        System.out.println();
        server2.printReport();
        System.out.println();
        server3.printReport();
        System.out.println();
    }
    
}

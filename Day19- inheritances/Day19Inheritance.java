public class Day19Inheritance {
    
    public static void main (String[] args) {
        
        
        
        // creamos un servidor 
        Server server1 = new Server("WebServer-01", "192.168.1.10", true, 1000, 850);
        
        // creamos un router
        Router router1 = new Router("CoreRouter-01", "192.168.1.1", true, 24, "OSPF");
        
        // cada uno imprime su propio informe
        server1.printReport();
        System.out.println();
        router1.printReport();
        System.out.println();
        
        // los dos son NetworkDevice - pueden usar métodos del padre
        System.out.println("---- USING PARENT METHODS---");
        System.out.println(server1.getName() + " is " + server1.getStatus());
        System.out.println(router1.getName() + " is " + router1.getStatus());
        
        // ponemos el router offline usando el método del padre
        router1.setOnline(false);
        System.out.println(router1.getName() + "is now " + router1.getStatus());
    }
}

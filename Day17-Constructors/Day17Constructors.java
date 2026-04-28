public class Day17Constructors {
    
    public static void main (String[] args) {
        
        
        // usando el constructor completo - obligatorio pasar todos los datos 
        Server server1 = new Server("WebSerever-01", 1000, 850, true);
        Serever server2 = new Server("DBServer-01", 500, 120, true);
        
        //usando el constructor sobrecargado -  solo el nombre 
        Server server3 = new Server("FileServer-01");
        
        server1.printReport();
        System.out.println();
        serever2.prinReport();
        System.out.println();
        server3.printReport();
    
    }
}

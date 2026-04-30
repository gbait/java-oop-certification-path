public class Day18Encapsulation {

    public static void main(String[] args) {

        // creamos servidor con constructor
        Server server1 = new Server("WebServer-01", 1000, 850, true);
        server1.printReport();

        System.out.println();

        // intentamos valores inválidos — el setter los rechaza
        System.out.println("--- TRYING INVALID VALUES ---");
        server1.setTotalBandwidth(-500);
        server1.setUsedBandwidth(99999);
        server1.setName("");

        System.out.println();

        // modificamos con valores válidos
        System.out.println("--- UPDATING WITH VALID VALUES ---");
        server1.setTotalBandwidth(2000);
        server1.setUsedBandwidth(1500);
        server1.setName("WebServer-01-UPGRADED");

        server1.printReport();

        System.out.println();

        // leemos atributos con getters
        System.out.println("--- READING WITH GETTERS ---");
        System.out.println("Name:   " + server1.getName());
        System.out.println("Online: " + server1.isOnline());
    }
}

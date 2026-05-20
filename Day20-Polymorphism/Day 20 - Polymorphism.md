# Day 20 - Polymorphism
 
## What Did We Learn Today?
Polymorphism is the most tested OOP concept in the Oracle exam. The word comes from Greek — poly (many) + morphos (forms). In Java it means that **a variable of the parent type can contain an object of the child type**, and when you call an overridden method Java always executes the version of the real object, not the variable type.
 
> The type of the variable decides what methods you can call. The type of the object decides how they execute.
 
---
 
## Key Concepts
 
### Polymorphic variable
```java
// variable type = NetworkDevice (parent)
// object type   = Server (child)
NetworkDevice device1 = new Server("WebServer-01", "192.168.1.10", true, 1000, 850);
```
This is valid because Server IS a NetworkDevice — child extends parent.
 
---
 
### Polymorphic array
A single array of the parent type can hold objects of different child types:
```java
NetworkDevice[] network = {device1, device2, device3};
// device1 = Server, device2 = Router, device3 = Server
// all stored in one NetworkDevice array
```
 
---
 
### Dynamic dispatch — the heart of polymorphism
When you call an overridden method Java always executes the version of the real object:
```java
for (NetworkDevice device : network) {
    device.printReport(); // Java picks the right version automatically
    // device1 → Server.printReport()
    // device2 → Router.printReport()
    // device3 → Server.printReport()
}
```
 
---
 
### The for-each loop
A cleaner way to loop through arrays:
```java
for (NetworkDevice device : network) {
    // device takes each element of the array one by one
}
```
Read as: "for each device of type NetworkDevice in the network array".
 
---
 
### `instanceof` — checking the real type
Checks if an object is of a specific type. Returns `true` if the object is of that type or a child of that type:
```java
if (device instanceof Server) {
    System.out.println(device.getName() + " is a Server");
} else if (device instanceof Router) {
    System.out.println(device.getName() + " is a Router");
}
```
 
---
 
### Casting — accessing child methods from a parent variable
When you have a parent variable containing a child object you cannot call child-specific methods directly. You need to cast:
```java
NetworkDevice nd = new Server("FileServer-01", "192.168.1.30", true, 200, 150);
Server s = (Server) nd;          // cast from NetworkDevice to Server
System.out.println(s.calculateUsage() + "%"); // now we can call Server methods
```
If the real object is not a Server this throws ClassCastException at runtime.
 
---
 
## Full Programs
 
### NetworkDevice.java
```java
public class NetworkDevice {
 
    private String name;
    private String ipAddress;
    private boolean isOnline;
 
    public NetworkDevice(String name, String ipAddress, boolean isOnline) {
        this.name = name;
        this.ipAddress = ipAddress;
        this.isOnline = isOnline;
    }
 
    public String getName() { return name; }
    public String getIpAddress() { return ipAddress; }
    public boolean isOnline() { return isOnline; }
    public void setOnline(boolean isOnline) { this.isOnline = isOnline; }
 
    public String getStatus() {
        return isOnline ? "ONLINE" : "OFFLINE";
    }
 
    public void printReport() {
        System.out.println("--- DEVICE REPORT ---");
        System.out.println("Name:   " + name);
        System.out.println("IP:     " + ipAddress);
        System.out.println("Status: " + getStatus());
    }
}
```
 
### Server.java
```java
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
```
 
### Router.java
```java
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
```
 
### Day20Polymorphism.java
```java
public class Day20Polymorphism {
 
    public static void main(String[] args) {
 
        // polymorphism — parent type variable, child type object
        NetworkDevice device1 = new Server("WebServer-01", "192.168.1.10", true, 1000, 850);
        NetworkDevice device2 = new Router("CoreRouter-01", "192.168.1.1", true, 24, "OSPF");
        NetworkDevice device3 = new Server("DBServer-01", "192.168.1.20", true, 500, 120);
 
        // array of parent type holding different child objects
        NetworkDevice[] network = {device1, device2, device3};
 
        System.out.println("=== NETWORK REPORT ===");
        System.out.println("Total devices: " + network.length);
        System.out.println();
 
        // for-each loop — Java executes the correct version of each child automatically
        for (NetworkDevice device : network) {
            device.printReport();
            System.out.println();
        }
 
        // instanceof — checks the real type of the object
        System.out.println("=== DEVICE TYPE CHECK ===");
        for (NetworkDevice device : network) {
            if (device instanceof Server) {
                System.out.println(device.getName() + " is a Server");
            } else if (device instanceof Router) {
                System.out.println(device.getName() + " is a Router");
            }
        }
 
        // casting — access child methods from a parent variable
        System.out.println("\n=== CASTING ===");
        NetworkDevice nd = new Server("FileServer-01", "192.168.1.30", true, 200, 150);
        Server s = (Server) nd;
        System.out.println("Usage: " + s.calculateUsage() + "%");
    }
}
```
 
---
 
## Console Output
 
```
=== NETWORK REPORT ===
Total devices: 3
 
--- DEVICE REPORT ---
Name:   WebServer-01
IP:     192.168.1.10
Status: ONLINE
Bandwidth: 850/1000 Mbps
Usage:     85.0%
 
--- DEVICE REPORT ---
Name:   CoreRouter-01
IP:     192.168.1.1
Status: ONLINE
Ports:    24
Protocol: OSPF
 
--- DEVICE REPORT ---
Name:   DBServer-01
IP:     192.168.1.20
Status: ONLINE
Bandwidth: 120/500 Mbps
Usage:     24.0%
 
=== DEVICE TYPE CHECK ===
WebServer-01 is a Server
CoreRouter-01 is a Router
DBServer-01 is a Server
 
=== CASTING ===
Usage: 75.0%
```
 
---
 
## Mistakes Made Today and What They Taught Us
 
- **`for (NetworkDevice : network)`** — missing variable name in for-each loop. Always declare type AND name: `for (NetworkDevice device : network)`
---
 
## Oracle Exam Traps to Remember
 
- **Dynamic dispatch** — Java always executes the overridden method of the real object, not the variable type
- **`instanceof`** — returns `true` for parent types too. `server instanceof NetworkDevice` returns `true`
- **Wrong cast** — `(Server) router` throws `ClassCastException` at runtime, not at compilation
- **For-each syntax** — `for (Type variable : array)` — Oracle tests this directly
- **Parent variable cannot call child-specific methods** — need to cast first
- **Polymorphism only works with overridden methods** — if the child does not override the method the parent version executes

# Day 19 - Inheritance
 
## What Did We Learn Today?
Until now each class had its own complete code. If you have servers, routers and switches that share common characteristics you would have to repeat that code in every class.
 
**Inheritance** solves this — you define the common attributes and methods in a **parent** class and the **child** classes inherit them automatically without repeating code.
 
---
 
## The Analogy
 
From your experience as an electrical designer:
- **Electrical device** — parent class. Every device has voltage, consumption, status
- **Electric motor** — child class. Inherits everything above and adds RPM, power
- **Transformer** — child class. Inherits everything above and adds transformation ratio
In Java:
```
NetworkDevice (parent)
├── Server (child) — inherits name, IP, isOnline + adds bandwidth
└── Router (child) — inherits name, IP, isOnline + adds ports and protocol
```
 
---
 
## Key Concepts
 
### `extends` — establishes inheritance
```java
public class Server extends NetworkDevice {
```
`Server` inherits all public attributes and methods from `NetworkDevice`. In Java you can only inherit from **one class** — multiple inheritance does not exist.
 
---
 
### `super()` — calls the parent constructor
```java
public Server(String name, String ipAddress, boolean isOnline,
              int totalBandwidth, int usedBandwidth) {
    super(name, ipAddress, isOnline); // mandatory first line
    this.totalBandwidth = totalBandwidth;
    this.usedBandwidth = usedBandwidth;
}
```
`super()` must always be the **first line** of the child constructor. It is how you tell the parent to initialise itself with those values. If you do not include it Java throws an error.
 
---
 
### `@Override` — overriding a parent method
```java
@Override
public void printReport() {
    super.printReport(); // calls the parent method first
    System.out.println("Bandwidth: " + usedBandwidth + "/" + totalBandwidth + " Mbps");
    System.out.println("Usage:     " + calculateUsage() + "%");
}
```
`@Override` indicates we are overriding a parent method with our own version. `super.printReport()` calls the parent method before adding the child-specific information.
 
---
 
### Private attributes of the parent
The `private` attributes of the parent are **not directly accessible** in the child class — only through the parent's getters and setters:
 
```java
// Wrong — name is private in NetworkDevice
System.out.println(name);
 
// Correct — use the parent getter
System.out.println(getName());
```
 
---
 
### What the child class inherits
| | Inherited? |
|---|---|
| `public` attributes | Yes |
| `protected` attributes | Yes |
| `private` attributes | No — only via getters/setters |
| `public` methods | Yes |
| Parent constructor | No — must call it with `super()` |
 
---
 
## Full Programs
 
### NetworkDevice.java — parent class
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
 
    public void setName(String name) { this.name = name; }
    public void setOnline(boolean isOnline) { this.isOnline = isOnline; }
 
    public String getStatus() {
        return isOnline ? "ONLINE" : "OFFLINE";
    }
 
    public void printReport() {
        System.out.println("--- DEVICE REPORT ---");
        System.out.println("Name:    " + name);
        System.out.println("IP:      " + ipAddress);
        System.out.println("Status:  " + getStatus());
    }
}
```
 
### Server.java — child class
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
 
    public int getTotalBandwidth() { return totalBandwidth; }
    public int getUsedBandwidth() { return usedBandwidth; }
 
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
 
### Router.java — child class
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
 
    public int getNumberOfPorts() { return numberOfPorts; }
    public String getRoutingProtocol() { return routingProtocol; }
 
    @Override
    public void printReport() {
        super.printReport();
        System.out.println("Ports:    " + numberOfPorts);
        System.out.println("Protocol: " + routingProtocol);
    }
}
```
 
### Day19Inheritance.java — main
```java
public class Day19Inheritance {
 
    public static void main(String[] args) {
 
        Server server1 = new Server("WebServer-01", "192.168.1.10", true, 1000, 850);
        Router router1 = new Router("CoreRouter-01", "192.168.1.1", true, 24, "OSPF");
 
        server1.printReport();
        System.out.println();
        router1.printReport();
 
        System.out.println();
 
        System.out.println("--- USING PARENT METHODS ---");
        System.out.println(server1.getName() + " is " + server1.getStatus());
        System.out.println(router1.getName() + " is " + router1.getStatus());
 
        router1.setOnline(false);
        System.out.println(router1.getName() + " is now " + router1.getStatus());
    }
}
```
 
---
 
## Console Output
 
```
--- DEVICE REPORT ---
Name:    WebServer-01
IP:      192.168.1.10
Status:  ONLINE
Bandwidth: 850/1000 Mbps
Usage:     85.0%
 
--- DEVICE REPORT ---
Name:    CoreRouter-01
IP:      192.168.1.1
Status:  ONLINE
Ports:    24
Protocol: OSPF
 
--- USING PARENT METHODS ---
WebServer-01 is ONLINE
CoreRouter-01 is ONLINE
CoreRouter-01 is now OFFLINE
```
 
---
 
## Mistakes Made Today and What They Taught Us
 
- **`DEVIDE`** instead of `DEVICE` — typo in the println text
- **`WebSerever`** instead of `WebServer` — typo in the server name
- **`router`** instead of `router1` — variable name must match exactly everywhere
- **Missing spaces** around `"is now"` — without spaces text appears joined together
---
 
## Oracle Exam Traps to Remember
 
- **`extends`** — Java only allows inheriting from one class. `extends A, B` gives a compilation error
- **`super()`** must be the first line of the child constructor — if it is not first Java throws an error
- **`@Override`** — if the method does not exist in the parent with the same signature Java throws an error
- **`private` attributes of the parent** — not directly accessible in the child, only via getters and setters
- **The parent constructor is not inherited** — must be called explicitly with `super()`

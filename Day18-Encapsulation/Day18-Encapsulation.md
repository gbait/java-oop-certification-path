# Day 18 - Encapsulation, Getters and Setters
 
## What Did We Learn Today?
In Days 16 and 17 the attributes were public — anyone could modify them directly with invalid values:
 
```java
server1.totalBandwidth = -5000; // nothing stops this
server1.name = null;            // nothing stops this
```
 
**Encapsulation** solves this with three steps:
1. Make attributes **`private`** — nobody can touch them directly from outside
2. Create **getters** — methods to read the attributes
3. Create **setters** — methods to modify the attributes with validation
---
 
## Key Concepts
 
### `private` — hiding attributes
The `private` keyword makes the attribute invisible from outside the class. If you try `server1.name` directly from `main` the compiler throws an error immediately:
 
```java
private String name;
private int totalBandwidth;
private int usedBandwidth;
private boolean isOnline;
```
 
---
 
### Getters — reading attributes
Methods that allow reading private attributes from outside. The naming convention is `get` + attribute name with uppercase first letter:
 
```java
public String getName() { return name; }
public int getTotalBandwidth() { return totalBandwidth; }
public int getUsedBandwidth() { return usedBandwidth; }
```
 
Boolean attributes use `is` instead of `get`:
```java
public boolean isOnline() { return isOnline; } // isOnline() not getIsOnline()
```
 
Rules for a valid getter:
- No parameters
- Returns the same type as the attribute
- Name follows `get` + AttributeName convention
---
 
### Setters — modifying attributes with validation
Methods that allow modifying private attributes with validation. The naming convention is `set` + attribute name with uppercase first letter:
 
```java
public void setTotalBandwidth(int totalBandwidth) {
    if (totalBandwidth >= 0) {
        this.totalBandwidth = totalBandwidth;
    } else {
        System.out.println("ERROR: Bandwidth cannot be negative");
        this.totalBandwidth = 0;
    }
}
```
 
Rules for a valid setter:
- Receives the new value as a parameter
- Always returns `void`
- Name follows `set` + AttributeName convention
---
 
### Getter and Setter naming convention
 
| Attribute | Getter | Setter |
|---|---|---|
| `String name` | `getName()` | `setName()` |
| `int totalBandwidth` | `getTotalBandwidth()` | `setTotalBandwidth()` |
| `boolean isOnline` | `isOnline()` | `setOnline()` |
 
---
 
### Using setters inside the constructor
To apply validation even when creating the object we call the setters from inside the constructor:
 
```java
public Server(String name, int totalBandwidth, int usedBandwidth, boolean isOnline) {
    this.name = name;
    this.totalBandwidth = totalBandwidth;
    this.usedBandwidth = usedBandwidth;
    this.isOnline = isOnline;
}
```
 
---
 
## Full Programs
 
### Server.java
```java
public class Server {
 
    private String name;
    private int totalBandwidth;
    private int usedBandwidth;
    private boolean isOnline;
 
    public Server(String name, int totalBandwidth, int usedBandwidth, boolean isOnline) {
        this.name = name;
        this.totalBandwidth = totalBandwidth;
        this.usedBandwidth = usedBandwidth;
        this.isOnline = isOnline;
    }
 
    public Server(String name) {
        this.name = name;
        this.totalBandwidth = 0;
        this.usedBandwidth = 0;
        this.isOnline = false;
    }
 
    // GETTERS
    public String getName() { return name; }
    public int getTotalBandwidth() { return totalBandwidth; }
    public int getUsedBandwidth() { return usedBandwidth; }
    public boolean isOnline() { return isOnline; }
 
    // SETTERS
    public void setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        } else {
            System.out.println("ERROR: Name cannot be null or empty");
        }
    }
 
    public void setTotalBandwidth(int totalBandwidth) {
        if (totalBandwidth >= 0) {
            this.totalBandwidth = totalBandwidth;
        } else {
            System.out.println("ERROR: Bandwidth cannot be negative");
            this.totalBandwidth = 0;
        }
    }
 
    public void setUsedBandwidth(int usedBandwidth) {
        if (usedBandwidth >= 0 && usedBandwidth <= totalBandwidth) {
            this.usedBandwidth = usedBandwidth;
        } else {
            System.out.println("ERROR: Used bandwidth out of range");
            this.usedBandwidth = 0;
        }
    }
 
    public void setOnline(boolean isOnline) {
        this.isOnline = isOnline;
    }
 
    // LOGIC METHODS
    public double calculateUsage() {
        if (totalBandwidth == 0) return 0;
        return (double) usedBandwidth / totalBandwidth * 100;
    }
 
    public String getStatus() {
        if (!isOnline) return "OFFLINE";
        double usage = calculateUsage();
        if (usage >= 90) return "CRITICAL";
        else if (usage >= 70) return "WARNING";
        else return "ONLINE";
    }
 
    public void printReport() {
        System.out.println("--- SERVER REPORT ---");
        System.out.println("Name:      " + getName());
        System.out.println("Bandwidth: " + getUsedBandwidth() + "/" + getTotalBandwidth() + " Mbps");
        System.out.println("Usage:     " + calculateUsage() + "%");
        System.out.println("Status:    " + getStatus());
    }
}
```
 
### Day18Encapsulation.java
```java
public class Day18Encapsulation {
 
    public static void main(String[] args) {
 
        // create server with constructor
        Server server1 = new Server("WebServer-01", 1000, 850, true);
        server1.printReport();
 
        System.out.println();
 
        // try invalid values — setter rejects them
        System.out.println("--- TRYING INVALID VALUES ---");
        server1.setTotalBandwidth(-500);
        server1.setUsedBandwidth(99999);
        server1.setName("");
 
        System.out.println();
 
        // update with valid values
        System.out.println("--- UPDATING WITH VALID VALUES ---");
        server1.setTotalBandwidth(2000);
        server1.setUsedBandwidth(1500);
        server1.setName("WebServer-01-UPGRADED");
 
        server1.printReport();
 
        System.out.println();
 
        // read individual attributes with getters
        System.out.println("--- READING WITH GETTERS ---");
        System.out.println("Name:   " + server1.getName());
        System.out.println("Online: " + server1.isOnline());
    }
}
```
 
---
 
## Console Output
 
```
--- SERVER REPORT ---
Name:      WebServer-01
Bandwidth: 850/1000 Mbps
Usage:     85.0%
Status:    WARNING
 
--- TRYING INVALID VALUES ---
ERROR: Bandwidth cannot be negative
ERROR: Used bandwidth out of range
ERROR: Name cannot be null or empty
 
--- UPDATING WITH VALID VALUES ---
 
--- SERVER REPORT ---
Name:      WebServer-01-UPGRADED
Bandwidth: 1500/2000 Mbps
Usage:     75.0%
Status:    WARNING
 
--- READING WITH GETTERS ---
Name:   WebServer-01-UPGRADED
Online: true
```
 
---
 
## Mistakes Made Today and What They Taught Us
 
- **Missing `setTotalBandwidth()` method** — the constructor called a setter that did not exist yet
- **`setUsedBandwidth()` had wrong code** — was modifying `totalBandwidth` instead of `usedBandwidth`
- **`this.name` converted to link** — OnlineGDB sometimes converts dot notation to links, always write as plain text
---
 
## Oracle Exam Traps to Remember
 
- **`private`** — private attributes are not accessible from outside the class. Trying to access them gives a compilation error
- **Boolean getter convention** — `isOnline()` not `getIsOnline()`. Oracle puts both options and expects the correct one
- **Getter has no parameters** — a getter with parameters is not a valid getter
- **Setter returns `void`** — a setter that returns something is not a valid setter
- **Encapsulation is not just `private`** — the complete concept includes `private` attributes plus public getters and setters
- **Setter order matters** — always set `totalBandwidth` before `usedBandwidth` since the used bandwidth setter validates against total
 

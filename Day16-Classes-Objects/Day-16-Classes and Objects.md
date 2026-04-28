# Day 16 - Classes and Objects

## What Did We Learn Today?
The most important concept in all of Java — **Object Oriented Programming**. Until now we used loose variables to store data. Today we learn to group related data into a single entity called an **object**.

---

## The Blueprint Analogy

Think of an **electrical construction blueprint**:
- The **blueprint** is the **class** — it describes how to build something
- The **built structure** is the **object** — a real instance of the blueprint

With one blueprint you can build 100 different structures. With one class you can create 100 different objects.

```java
Class Server  →  object server1 (WebServer-01)
(blueprint)   →  object server2 (DBServer-01)
              →  object server3 (FileServer-01)
```

---

## Key Concepts

### The Class
Defines the structure — what data it has and what it can do. Goes in its own `.java` file and the filename must match the class name exactly:

```java
// file: Server.java
public class Server {
    // attributes and methods
}
```

---

### Attributes
Variables that belong to the class. Each object will have its own values:

```java
public class Server {
    String name;
    int totalBandwidth;
    int usedBandwidth;
    boolean isOnline;
}
```

---

### Instance Methods
Unlike the methods from Day 10, instance methods do **not have `static`**. They belong to the object and can access its attributes directly without needing parameters:

```java
public double calculateUsage() {
    return (double) usedBandwidth / totalBandwidth * 100;
}
```

---

### Creating an Object — Instantiating the Class
To create a real object from the blueprint we use `new`:

```java
Server server1 = new Server();
```

Three parts:
- `Server` — the type, which is our class
- `server1` — the name we give the object
- `new Server()` — creates the object in memory

---

### Accessing Attributes and Methods — the Dot `.`
We use the dot to access both attributes and methods of the object:

```java
server1.name = "WebServer-01";
server1.totalBandwidth = 1000;
server1.printReport();
```

---

### Each Object is Independent
Changing the data of one object does not affect the others:

```java
server1.name = "WebServer-01"; // does not affect server2
server2.name = "DBServer-01";  // does not affect server1
```

---

### Two Files — Important Rule
In Java each public class must live in its own file. The filename must match the class name exactly:
Server.java                 → public class Server
Day16ClassesAndObjects.java → public class Day16ClassesAndObjects

---

## Full Programs

### Server.java
```java
public class Server {

    String name;
    int totalBandwidth;
    int usedBandwidth;
    boolean isOnline;

    public double calculateUsage() {
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
        System.out.println("Name:      " + name);
        System.out.println("Bandwidth: " + usedBandwidth + "/" + totalBandwidth + " Mbps");
        System.out.println("Usage:     " + calculateUsage() + "%");
        System.out.println("Status:    " + getStatus());
    }
}
```

### Day16ClassesAndObjects.java
```java
public class Day16ClassesAndObjects {

    public static void main(String[] args) {

        Server server1 = new Server();
        server1.name = "WebServer-01";
        server1.totalBandwidth = 1000;
        server1.usedBandwidth = 850;
        server1.isOnline = true;

        Server server2 = new Server();
        server2.name = "DBServer-01";
        server2.totalBandwidth = 500;
        server2.usedBandwidth = 120;
        server2.isOnline = true;

        Server server3 = new Server();
        server3.name = "FileServer-01";
        server3.totalBandwidth = 200;
        server3.usedBandwidth = 0;
        server3.isOnline = false;

        server1.printReport();
        System.out.println();
        server2.printReport();
        System.out.println();
        server3.printReport();
    }
}
```

---

## Console Output
--- SERVER REPORT ---
Name:      WebServer-01
Bandwidth: 850/1000 Mbps
Usage:     85.0%
Status:    WARNING
--- SERVER REPORT ---
Name:      DBServer-01
Bandwidth: 120/500 Mbps
Usage:     24.0%
Status:    ONLINE
--- SERVER REPORT ---
Name:      FileServer-01
Bandwidth: 0/200 Mbps
Usage:     0.0%
Status:    OFFLINE

---

## Mistakes Made Today and What They Taught Us

- **`public class server`** → must be `public class Server` — class name must match filename exactly including uppercase
- **`caluclateUsage()`** → typo in method name — names must match exactly everywhere
- **`:` instead of `()`** → method calls always need parentheses

---

## Oracle Exam Traps to Remember

- **`static` vs no `static`** — instance methods do not have `static`. The `main` method does
- **`new` to create objects** — without `new` the object does not exist in memory
- **Filename must match class name** — uppercase included
- **Each object is independent** — changing `server1.name` does not affect `server2.name`
- **The dot `.`** — used to access both attributes and methods

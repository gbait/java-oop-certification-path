¡Aquí tienes todo!

Day17.md en español
markdown# Día 17 - Constructores

## ¿Qué hemos aprendido hoy?
Ayer creábamos objetos en dos pasos — primero `new Server()` y luego asignábamos los atributos uno a uno. El problema es que nada obligaba a rellenar todos los datos.

El **constructor** resuelve esto — es un método especial que se ejecuta automáticamente cuando creas un objeto con `new` y que obliga a pasar los datos necesarios desde el principio.

---

## Conceptos clave

### ¿Qué es un constructor?
Un método especial con tres características que lo distinguen de cualquier otro método:
- **El nombre es exactamente igual que la clase**
- **No tiene tipo de retorno** — ni `void`, ni `String`, ni nada
- **Se ejecuta automáticamente** cuando se hace `new`

```java
public Server(String name, int totalBandwidth, int usedBandwidth, boolean isOnline) {
    this.name = name;
    this.totalBandwidth = totalBandwidth;
    this.usedBandwidth = usedBandwidth;
    this.isOnline = isOnline;
}
```

---

### La palabra clave `this`
Cuando el parámetro y el atributo tienen el mismo nombre Java necesita distinguirlos:

```java
this.name = name;
//   ↑         ↑
// atributo  parámetro
```

- `this.name` → el atributo del objeto
- `name` → el parámetro que llegó al constructor

`this` significa "este objeto" — es la forma que tiene el objeto de referirse a sí mismo.

---

### Sobrecarga de constructores
Puedes tener varios constructores con distinto número o tipo de parámetros. Java elige cuál usar según los datos que le pases:

```java
// Constructor completo
public Server(String name, int totalBandwidth, int usedBandwidth, boolean isOnline) {
    this.name = name;
    this.totalBandwidth = totalBandwidth;
    this.usedBandwidth = usedBandwidth;
    this.isOnline = isOnline;
}

// Constructor sobrecargado — solo el nombre
public Server(String name) {
    this.name = name;
    this.totalBandwidth = 0;
    this.usedBandwidth = 0;
    this.isOnline = false;
}
```

---

### Constructor por defecto
Cuando no escribes ningún constructor Java crea uno automáticamente sin parámetros:

```java
// Java lo crea automáticamente si no escribes ningún constructor
public Server() { }
```

En cuanto escribes cualquier constructor propio el constructor por defecto desaparece. Si luego intentas hacer `new Server()` sin parámetros da error de compilación.

---

### Ventaja del constructor vs asignación manual

**Sin constructor — día 16:**
```java
Server server1 = new Server();
server1.name = "WebServer-01";      // puede olvidarse
server1.totalBandwidth = 1000;      // puede olvidarse
server1.usedBandwidth = 850;        // puede olvidarse
server1.isOnline = true;            // puede olvidarse
```

**Con constructor — día 17:**
```java
Server server1 = new Server("WebServer-01", 1000, 850, true); // todo obligatorio
```

---

## Los programas completos

### Server.java
```java
public class Server {

    String name;
    int totalBandwidth;
    int usedBandwidth;
    boolean isOnline;

    // constructor completo
    public Server(String name, int totalBandwidth, int usedBandwidth, boolean isOnline) {
        this.name = name;
        this.totalBandwidth = totalBandwidth;
        this.usedBandwidth = usedBandwidth;
        this.isOnline = isOnline;
    }

    // constructor sobrecargado
    public Server(String name) {
        this.name = name;
        this.totalBandwidth = 0;
        this.usedBandwidth = 0;
        this.isOnline = false;
    }

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
        System.out.println("Name:      " + name);
        System.out.println("Bandwidth: " + usedBandwidth + "/" + totalBandwidth + " Mbps");
        System.out.println("Usage:     " + calculateUsage() + "%");
        System.out.println("Status:    " + getStatus());
    }
}
```

### Day17Constructors.java
```java
public class Day17Constructors {

    public static void main(String[] args) {

        // constructor completo
        Server server1 = new Server("WebServer-01", 1000, 850, true);
        Server server2 = new Server("DBServer-01", 500, 120, true);

        // constructor sobrecargado
        Server server3 = new Server("FileServer-01");

        server1.printReport();
        System.out.println();
        server2.printReport();
        System.out.println();
        server3.printReport();
    }
}
```

---

## Lo que aparece en consola
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
Bandwidth: 0/0 Mbps
Usage:     0.0%
Status:    OFFLINE

---

## Trampas del examen Oracle

- **El constructor no tiene tipo de retorno** — si le pones `void` deja de ser constructor y se convierte en un método normal
- **El nombre del constructor debe ser exactamente igual que la clase** — mayúsculas incluidas
- **`this`** distingue atributos de parámetros cuando tienen el mismo nombre
- **Constructor por defecto desaparece** cuando defines cualquier constructor propio
- **Sobrecarga válida** — dos constructores con distinto número o tipo de parámetros. Dos constructores idénticos da error

Day17.md en inglés
markdown# Day 17 - Constructors

## What Did We Learn Today?
Yesterday we created objects in two steps — first `new Server()` then assigning attributes one by one. The problem is that nothing forced us to fill in all the data.

The **constructor** solves this — it is a special method that executes automatically when you create an object with `new` and forces you to pass the necessary data from the start.

---

## Key Concepts

### What is a Constructor?
A special method with three characteristics that distinguish it from any other method:
- **The name is exactly the same as the class**
- **No return type** — not `void`, not `String`, nothing
- **Executes automatically** when `new` is called

```java
public Server(String name, int totalBandwidth, int usedBandwidth, boolean isOnline) {
    this.name = name;
    this.totalBandwidth = totalBandwidth;
    this.usedBandwidth = usedBandwidth;
    this.isOnline = isOnline;
}
```

---

### The `this` Keyword
When the parameter and the attribute have the same name Java needs to tell them apart:

```java
this.name = name;
//   ↑         ↑
// attribute  parameter
```

- `this.name` → the object's attribute
- `name` → the parameter that arrived at the constructor

`this` means "this object" — it is how the object refers to itself.

---

### Constructor Overloading
You can have several constructors with different numbers or types of parameters. Java chooses which one to use based on the data you pass:

```java
// full constructor
public Server(String name, int totalBandwidth, int usedBandwidth, boolean isOnline) {
    this.name = name;
    this.totalBandwidth = totalBandwidth;
    this.usedBandwidth = usedBandwidth;
    this.isOnline = isOnline;
}

// overloaded constructor — name only
public Server(String name) {
    this.name = name;
    this.totalBandwidth = 0;
    this.usedBandwidth = 0;
    this.isOnline = false;
}
```

---

### Default Constructor
When you write no constructor Java automatically creates one with no parameters:

```java
// Java creates this automatically if you write no constructor
public Server() { }
```

As soon as you write any constructor of your own the default constructor disappears. If you then try `new Server()` with no parameters you get a compilation error.

---

### Constructor vs Manual Assignment

**Without constructor — Day 16:**
```java
Server server1 = new Server();
server1.name = "WebServer-01";   // easy to forget
server1.totalBandwidth = 1000;   // easy to forget
server1.usedBandwidth = 850;     // easy to forget
server1.isOnline = true;         // easy to forget
```

**With constructor — Day 17:**
```java
Server server1 = new Server("WebServer-01", 1000, 850, true); // all required
```

---

## Full Programs

### Server.java
```java
public class Server {

    String name;
    int totalBandwidth;
    int usedBandwidth;
    boolean isOnline;

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
        System.out.println("Name:      " + name);
        System.out.println("Bandwidth: " + usedBandwidth + "/" + totalBandwidth + " Mbps");
        System.out.println("Usage:     " + calculateUsage() + "%");
        System.out.println("Status:    " + getStatus());
    }
}
```

### Day17Constructors.java
```java
public class Day17Constructors {

    public static void main(String[] args) {

        Server server1 = new Server("WebServer-01", 1000, 850, true);
        Server server2 = new Server("DBServer-01", 500, 120, true);
        Server server3 = new Server("FileServer-01");

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
Bandwidth: 0/0 Mbps
Usage:     0.0%
Status:    OFFLINE

---

## Oracle Exam Traps to Remember

- **Constructor has no return type** — adding `void` turns it into a regular method
- **Constructor name must match class name exactly** — uppercase included
- **`this`** distinguishes attributes from parameters when they share the same name
- **Default constructor disappears** when you define any constructor of your own
- **Valid overloading** — two constructors with different number or type of parameters. Two identical constructors gives a compilation error

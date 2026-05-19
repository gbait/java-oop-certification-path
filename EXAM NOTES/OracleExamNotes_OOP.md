# Oracle Exam Key Notes — OOP Phase
> Phase 2 notes covering Object Oriented Programming concepts for the Oracle Java SE 17 Certification exam (1Z0-829).

---

## Classes and Objects

### Key vocabulary
| Term | Meaning |
|---|---|
| Class | The blueprint — defines structure and behaviour |
| Object | A real instance created from the class |
| Attribute | A variable that belongs to the class |
| Method | A behaviour that belongs to the class |
| Instantiation | Creating an object from a class using `new` |

### Creating a class
```java
public class Server {       // class name must match filename
    String name;            // attribute
    int totalBandwidth;     // attribute

    public void printReport() { // instance method — no static
        System.out.println(name);
    }
}
```

### Creating an object
```java
Server server1 = new Server(); // instantiate with new
server1.name = "WebServer-01"; // access attribute with dot
server1.printReport();         // call method with dot
```

### Instance methods vs static methods
| | Instance method | Static method |
|---|---|---|
| Has `static` | No | Yes |
| Belongs to | The object | The class |
| Can access attributes | Yes, directly | No, needs parameters |
| Called with | `object.method()` | `ClassName.method()` |

### Key OOP exam traps
- **Filename must match class name exactly** — uppercase included. `Server.java` → `public class Server`
- **`new` is required** — `Server s;` declares but does not create. `Server s = new Server();` creates
- **Each object is independent** — changing one object's attributes does not affect others
- **Instance methods have no `static`** — adding `static` to an instance method is a common exam trap
- **The dot `.`** accesses both attributes and methods — `object.attribute` and `object.method()`

---

## Constructors

### What makes a constructor
- Same name as the class
- No return type — not even `void`
- Executes automatically when `new` is called

```java
public Server(String name, int bandwidth) { // constructor
    this.name = name;
    this.totalBandwidth = bandwidth;
}

public void Server() { } // NOT a constructor — has void, becomes a regular method
```

### The `this` keyword
Distinguishes attributes from parameters when they share the same name:
```java
this.name = name; // this.name = attribute, name = parameter
```

### Constructor overloading
Multiple constructors with different parameters — Java picks the right one automatically:
```java
public Server(String name, int bandwidth) { } // full constructor
public Server(String name) { }               // overloaded constructor
```

### Default constructor rules
- Java provides a no-arg constructor automatically when NO constructor is written
- As soon as ONE constructor is written the default constructor disappears
- Calling `new Server()` after writing a constructor with parameters → compilation error

### Key constructor exam traps
- **No return type** — `void Server()` is a method, not a constructor
- **Name must match class** — `public server()` in class `Server` does not compile
- **Default constructor disappears** — Oracle tests this directly
- **`this`** is required when parameter and attribute share the same name
- **Overloading** — same name, different parameters. Same name, same parameters → compilation error

## Encapsulation
 
### The three steps of encapsulation
1. Make attributes `private`
2. Create getters to read them
3. Create setters to modify them with validation
 
### Getter rules
- No parameters
- Returns the same type as the attribute
- Naming: `get` + AttributeName (uppercase first letter)
- Boolean exception: `is` + AttributeName instead of `get`
 
```java
public String getName() { return name; }       // String getter
public boolean isOnline() { return isOnline; } // boolean getter — is not get
```
 
### Setter rules
- Receives the new value as parameter
- Always returns `void`
- Naming: `set` + AttributeName (uppercase first letter)
 
```java
public void setTotalBandwidth(int totalBandwidth) {
    if (totalBandwidth >= 0) {
        this.totalBandwidth = totalBandwidth;
    } else {
        System.out.println("ERROR: Bandwidth cannot be negative");
    }
}
```
 
### Getter and setter naming convention
| Attribute | Getter | Setter |
|---|---|---|
| `String name` | `getName()` | `setName()` |
| `int totalBandwidth` | `getTotalBandwidth()` | `setTotalBandwidth()` |
| `boolean isOnline` | `isOnline()` | `setOnline()` |
 
### Key encapsulation exam traps
- **`private`** — private attributes give compilation error if accessed directly from outside
- **Boolean getter** — `isOnline()` not `getIsOnline()`. Oracle puts both and expects the correct one
- **Getter with parameters** — not a valid getter
- **Setter that returns something** — not a valid setter
- **Encapsulation is not just `private`** — must include getters and setters to be complete
 
---
 
## Inheritance
 
### Key vocabulary
| Term | Meaning |
|---|---|
| Parent class | The class being inherited from |
| Child class | The class that inherits |
| `extends` | Keyword that establishes inheritance |
| `super()` | Calls the parent constructor |
| `@Override` | Marks a method that overrides a parent method |
 
### Establishing inheritance
```java
public class Server extends NetworkDevice { // Server inherits from NetworkDevice
```
Java only allows inheriting from **one class** — multiple inheritance does not exist.
 
### Calling the parent constructor
```java
public Server(String name, String ipAddress, boolean isOnline, int bandwidth) {
    super(name, ipAddress, isOnline); // MUST be the first line
    this.totalBandwidth = bandwidth;
}
```
 
### Overriding a parent method
```java
@Override
public void printReport() {
    super.printReport(); // calls parent method first
    System.out.println("Bandwidth: " + totalBandwidth); // adds child-specific info
}
```
 
### What the child inherits
| | Inherited? |
|---|---|
| `public` attributes | Yes |
| `protected` attributes | Yes |
| `private` attributes | No — only via getters/setters |
| `public` methods | Yes |
| Parent constructor | No — must call with `super()` |
 
### Key inheritance exam traps
- **`extends A, B`** — multiple inheritance does not exist in Java. Compilation error
- **`super()` must be first line** — if not first Java throws an error
- **`@Override` wrong signature** — if method does not exist in parent with same signature, compilation error
- **`private` attributes of parent** — not directly accessible in child, only via getters
- **Parent constructor not inherited** — must call explicitly with `super()`

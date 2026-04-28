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

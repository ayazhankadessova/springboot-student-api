1. Spring Initializer

https://start.spring.io

- Maven Project
- Java
- 2.4.1

* Dependencies:

- Spring Web
- Spring Data JPA
- PostgreSQL Driver

2. Simple API with SpringBoot

- RestController

```
@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@GetMapping
	public List<String> hello() {
		return List.of("Hello", "World");
	}
}
```

- Localhost:8080

```
["Hello","World"]
```

3. Student class

The constructor is created with the specified parameters. Type ctor , and then press TAB twice. For the full list of snippets (little bits of prefabricated code) press Ctrl + K and then Ctrl + X .

- Right click -> Source Action

- [x] Generate constructors
- [x] Generate getters
- [x] Generate setters
- [x] Generate toString()

- [x] Change DemoApplication.js

```
	@GetMapping
	public List<Student> hello() {
		Student Mariyam = new Student(1L, "Mariam", "mariam.jamal@gmail.com", LocalDate.of(2000, Month.JANUARY, 23),
				21);
		return List.of(Mariyam);
	}
```

```
[{"id":1,"name":"Mariam","email":"mariam.jamal@gmail.com","dob":"2000-01-23","age":21}]
```

> Our class was converted to json object

4. API Layer

- N-tier architecture: API Layer -> Service Layer -> Data Access Layer

  1.  Remove `GetMapping` from `DemoApplication.java`
  2.  Add `StudentController`

  ```
  @RestController
  @RequestMapping(path = "api/v1/student")
  public class StudentController {

  @GetMapping
  public List<Student> hello() {
  	Student Mariyam = new Student(1L, "Mariam", "mariam.jamal@gmail.com", LocalDate.of(2000, Month.JANUARY, 23),
  			21);
  	return List.of(Mariyam);
  	}
  }
  ```

  3. Test `http://localhost:8080/api/v1/student`

  ```
  [{"id":1,"name":"Mariam","email":"mariam.jamal@gmail.com","dob":"2000-01-23","age":21}]
  ```

5. Business Layer

   1. Add `StudentController` -> `StudentService`

   ```
   @RestController
   @RequestMapping(path = "api/v1/student")
   public class StudentController {

   	private final StudentService studentService;


   	public StudentController(StudentService studentService) {
   		this.studentService = studentService;
   	}

   	@GetMapping
   	public List<Student> getStudents() {
   		return studentService.getStudents();
   	}

   }
   ```

   2. Student Service

   ```
   public class StudentService {
    @GetMapping
    public List<Student> getStudents() {
        Student Mariyam = new Student(1L, "Mariam", "mariam.jamal@gmail.com", LocalDate.of(2000, Month.JANUARY, 23),
                21);
        return List.of(Mariyam);
   	}
   }
   ```

6. Annotation & Dependency injection within Spring & SpringBoot.

   1. In Controller, make `StudentService` to be auto generated -> `AutoWired`
   2. In `Service`, make it `Bean` -> Add `@Service`
   3. Test

   ```
   @Service
   public class StudentService {
    @GetMapping
    public List<Student> getStudents() {
        Student Mariyam = new Student(1L, "Mariam", "mariam.jamal@gmail.com", LocalDate.of(2000, Month.JANUARY, 23),
                21);
        return List.of(Mariyam);
   	}
   }
   ```

7. Properties Life (Data Access Layer)

   1. Add this to `application.properties`

   ```
   spring.datasource.url=jdbc:postgresql://localhost:5432/student
   spring.datasource.username=
   spring.datasource.password=
   # clean statue every time we fun the application
   spring.jpa.hibernate.ddl-auto=create-drop
   spring.jpa.show-sql=true
   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
   spring.jpa.properties.hibernate.format_sql=true
   ```

   2. Download `Postgres.app`

   - Best for MacOS: https://postgresapp.com/downloads.html

   3. Initialize & connect to database

   ```
   CREATE DATABASE student;

   GRANT ALL PRIVILEGES ON “Student" TO ayazhan;
   GRANT ALL PRIVILEGES ON DATABASE "student" TO postgres;

   \l
   \c student
   \d
   ```

8. JPA And Entity

```
@Entity
@Table
public class Student {
    @Id
    @SequenceGenerator(name = "student_sequence", sequenceName = "student_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sequence")
    private Long id;
    private String name;
    private String email;
    private LocalDate dob;
    private Integer age;

    public Student() {
    }
```

```
create sequence student_sequence start with 1 increment by 1
Hibernate:
    create table student (
        age integer,
        dob date,
        id bigint not null,
        email varchar(255),
        name varchar(255),
        primary key (id)
    )
```

9. Configure Database with 1 Table -> student

> Database -> Add connection -> PostgreSQL -> localhost@5342

10. JPA Repository

- Use interface inside of our service
- return things from the repository instead of static list

> JpaRepository gives us different methods

```

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    // annotate constructor with auto-wired
    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }
}
```

```
[]
```

11. JPA Repository

```
In Spring, the objects that form the backbone of your application and that are managed by the Spring IoC container are called beans. A bean is an object that is instantiated, assembled, and managed by a Spring IoC container. Otherwise, a bean is simply one of many objects in your application. Beans, and the dependencies among them, are reflected in the configuration metadata used by a container.
```

- Hibernate is running this SQL when we do `saveAll()`:

```
Hibernate:
    insert
    into
        student
        (age,dob,email,name,id)
    values
        (?,?,?,?,?)
```

```
[{"id":1,"name":"Mariam","email":"mariam.jamal@gmail.com","dob":"2000-01-23","age":21},{"id":2,"name":"Alex","email":"alex@gmail.com","dob":"2000-01-23","age":21}]
```

- Terminal

```
student=# SELECT * from student;
 age |    dob     | id |         email          |  name
-----+------------+----+------------------------+--------
  21 | 2000-01-23 |  1 | mariam.jamal@gmail.com | Mariam
  21 | 2000-01-23 |  2 | alex@gmail.com         | Alex
(2 rows)
```

12. Transient (Implement methods)

- Transient -> remove from DB

```
public Integer getAge() {
    return Period.between(this.dob, LocalDate.now()).getYears();
}
```

```

public Integer getAge() {
return Period.between(this.dob, LocalDate.now()).getYears();
}

```

13. Post GetMapping

> StudentController.java

- Get Payload & Store it in the system

```
ervlet        : Initializing Servlet 'dispatcherServlet'
2023-08-29T13:34:02.728+06:00  INFO 36398 --- [nio-8080-exec-2] o.s.web.servlet.DispatcherServlet        : Completed initialization in 7 ms
Student [id=null, name=null, email=bilal.ahmed@gmail.com, dob=null, age=null]

```

```
POST http://localhost:8080/api/v1/student

Content-type: application/json

{
    "firstname": "Bilal",
    "lastname": "Ahmed",
    "email": "bilal.ahmed@gmail.com",
    "password": "password"
}
```

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

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

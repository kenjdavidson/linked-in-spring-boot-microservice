#  Creating your First Microservice (Spring Boot)

The following project pertains to the Linked In Learning project [https://www.linkedin.com/learning/creating-your-first-spring-boot-microservice](https://www.linkedin.com/learning/creating-your-first-spring-boot-microservice).

## Chapter 1 - Creating a Spring Boot Microservice

Developmend started in **devcontainers** which was created:

1. In **VSCODE** `Palette` > `Add Dev Container`
2. Configure with Java 8 standard image
3. Update devcontainer extensions

```
	"extensions": [
		"vscjava.vscode-java-pack",
		"Pivotal.vscode-boot-dev-pack"
	],
```

4. `Palette` > `Spring Boot Initilizr` 
5. `mvn install`
6. Run the application

- `java -jar target/explorecali-0.0.1-SNAPSHOT.jar`
- `ExploreCaliApplication` > `java run`

```
14:30:08.016 [Thread-0] DEBUG org.springframework.boot.devtools.restart.classloader.RestartClassLoader - Created RestartClassLoader org.springframework.boot.devtools.restart.classloader.RestartClassLoader@2c913384

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v2.6.3)

2022-02-15 14:30:09.033  INFO 10128 --- [  restartedMain] k.l.explorecali.ExploreCaliApplication   : Starting ExploreCaliApplication using Java 1.8.0_312 on e8f06f43f1a6 with PID 10128 (/workspaces/linked-in-microservice-spring-boot/target/classes started by vscode in /workspaces/linked-in-microservice-spring-boot)
2022-02-15 14:30:09.035  INFO 10128 --- [  restartedMain] k.l.explorecali.ExploreCaliApplication   : No active profile set, falling back to default profiles: default
2022-02-15 14:30:09.209  INFO 10128 --- [  restartedMain] .e.DevToolsPropertyDefaultsPostProcessor : Devtools property defaults active! Set 'spring.devtools.add-properties' to 'false' to disable
2022-02-15 14:30:09.209  INFO 10128 --- [  restartedMain] .e.DevToolsPropertyDefaultsPostProcessor : For additional web related logging consider setting the 'logging.level.web' property to 'DEBUG'
2022-02-15 14:30:11.287  INFO 10128 --- [  restartedMain] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JPA repositories in DEFAULT mode.
2022-02-15 14:30:11.384  INFO 10128 --- [  restartedMain] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 57 ms. Found 0 JPA repository interfaces.
2022-02-15 14:30:13.467  INFO 10128 --- [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8080 (http)
2022-02-15 14:30:13.535  INFO 10128 --- [  restartedMain] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2022-02-15 14:30:13.536  INFO 10128 --- [  restartedMain] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.56]
2022-02-15 14:30:13.650  INFO 10128 --- [  restartedMain] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2022-02-15 14:30:13.651  INFO 10128 --- [  restartedMain] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 4440 ms
2022-02-15 14:30:13.780  INFO 10128 --- [  restartedMain] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
2022-02-15 14:30:14.333  INFO 10128 --- [  restartedMain] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
2022-02-15 14:30:14.465  INFO 10128 --- [  restartedMain] o.s.b.a.h2.H2ConsoleAutoConfiguration    : H2 console available at '/h2-console'. Database available at 'jdbc:h2:mem:cb13d24b-096c-49a2-9f1a-f41fc7f5dc45'
2022-02-15 14:30:15.220  INFO 10128 --- [  restartedMain] o.hibernate.jpa.internal.util.LogHelper  : HHH000204: Processing PersistenceUnitInfo [name: default]
2022-02-15 14:30:15.402  INFO 10128 --- [  restartedMain] org.hibernate.Version                    : HHH000412: Hibernate ORM core version 5.6.4.Final
2022-02-15 14:30:15.906  INFO 10128 --- [  restartedMain] o.hibernate.annotations.common.Version   : HCANN000001: Hibernate Commons Annotations {5.1.2.Final}
2022-02-15 14:30:16.336  INFO 10128 --- [  restartedMain] org.hibernate.dialect.Dialect            : HHH000400: Using dialect: org.hibernate.dialect.H2Dialect
2022-02-15 14:30:16.997  INFO 10128 --- [  restartedMain] o.h.e.t.j.p.i.JtaPlatformInitiator       : HHH000490: Using JtaPlatform implementation: [org.hibernate.engine.transaction.jta.platform.internal.NoJtaPlatform]
2022-02-15 14:30:17.027  INFO 10128 --- [  restartedMain] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
2022-02-15 14:30:17.260  WARN 10128 --- [  restartedMain] JpaBaseConfiguration$JpaWebConfiguration : spring.jpa.open-in-view is enabled by default. Therefore, database queries may be performed during view rendering. Explicitly configure spring.jpa.open-in-view to disable this warning
2022-02-15 14:30:20.399  INFO 10128 --- [  restartedMain] o.s.b.d.a.OptionalLiveReloadServer       : LiveReload server is running on port 35729
2022-02-15 14:30:20.477  INFO 10128 --- [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2022-02-15 14:30:20.495  INFO 10128 --- [  restartedMain] k.l.explorecali.ExploreCaliApplication   : Started ExploreCaliApplication in 12.431 seconds (JVM running for 13.37)
```

7. Connect to service `http://localhost:8080`

```
{
  "_links" : {
    "profile" : {
      "href" : "http://localhost:8080/profile"
    }
  }
}
```

## Chapter 2 - Leverage Spring Data JPA Repository Interfaces

### Domain Model

The primary models are:

- Tour
- TourPackage
- Region
- Difficulty

### JPA Repository

Extend from `CrudRepository<T,I>` where:
- `T` is the type
- `I` is the Id type

### JPA Repository Injection

Build out the `services` using the following annotations:

- `@Service` to make this service available
- `@Autowired` to inject other components

### Invoking JPA Repository

Pre-populate the database during application startup - required due to the H2 memory datasource.  

- Application must implement `ApplicationRunner` to provide the `run` method
- Create a `DataInitializer` to load the information

> Would be better to create interface `DataInitializer` and implementations `TourInitializer` to allow for adding more context.

### Intro to Data Query Methods

The `magical` world of creating interface methods using the appropriate naming structure.

- Return type
- `findBy`
- Entity attribute name in camel case
- Query parameters with type matching parameter type

More information at https://www.baeldung.com/spring-data-derived-queries and advanced query options including:

- `findBy...In`
- `findBy...LessThan`
- `findBy...And...`

and aggrigates:

- `countBy`

or for more advanced queries:

```
@Query("Select t from Tour t where ....")
List<Tour> lookupTour(...);
```

> Method names do not need to follow signature rules with `Query` annotation.

## Chapter 3 - Expose RESTful Apis with Spring Data REST

Explore libraries available for working with Hypermedia As the Engine of Application State (HATEOES).

- Expose Apis
- Navigation between resources

> Fancy way to say that it provides Links for different sub and related resources that are available within the response data.

Although this is pretty straight forward, the one thing that gets me is that the response for tour:

```
{
  "title" : "Big Sur Retreat",
  "description" : "The region know as Big Sur is like Yosemite's younger cousin, with all the redwood scaling, rock climbing and, best of all, hiking that the larger park has to offer. Robison Jeffers once said, \"Big Sur is the greatest meeting of land and sea in the world,\" but the highlights are only accessible on foot.\nOur 3-day tour allows you to choose from multiple hikes led by experienced guides during the day, while comfortably situated in the evenings at the historic Big Sur River Inn. Take a tranquil walk to the coastal waterfall at Julia Pfeiffer Burns State Par or hike to the Married Redwoods. If you're prepared for a more strenuous climb, try Ollason's Peak in Toro Park. An optional 4th day includes admission to the Henry Miller Library and the Point Reyes Lighthouse.",
  "blurb" : null,
  "price" : 750.00,
  "duration" : "3 days",
  "keywords" : "Hiking, National Parks, Big Sur",
  "difficulty" : "MEDIUM",
  "region" : "CENTRAL_COAST",
  "_links" : {
    "self" : {
      "href" : "http://localhost:8080/tours/1"
    },
    "tour" : {
      "href" : "http://localhost:8080/tours/1"
    },
    "tourPackage" : {
      "href" : "http://localhost:8080/tours/1/tourPackage"
    }
  }
}
```

has a link for the `tourPackage` but not the tour package itself:

```
{}
  "title" : "Big Sur Retreat",
  "tourPackage: {
    "code" : "BC",
    "name" : "Backpack Cal"
  }  
  "region" : "CENTRAL_COAST",
  "_links" : {
    "self" : {
      "href" : "http://localhost:8080/tours/1"
    },
    "tour" : {
      "href" : "http://localhost:8080/tours/1"
    },
    "tourPackage" : {
      "href" : "http://localhost:8080/tours/1/tourPackage"
    }
  }
}
```

which doesn't suffer from a number of issues and annoyances while attempting to work with references.

> Look into ways around this, they must exist.

### Creating the APIs with Spring Data REST

> Automagically create the endpoints with `spring-data-rest` library.

Creates endpoints for all standard methods:

- GET /entities
- GET /entities/:id
- POST /entities
- PUT /entities/:id
- DELETE /entities/:id

### Search resource

### Paging and Sorting

Providing the following parameters:

- `size` the number of elements in a page
- `page` the page in which to display
- `sort` the stort field providing `ASC|DESC` as applciable

This is done by converting the method signature with:

```
Page<Tour> findBy...(@Param("code") String code, Pageable pageable);
```

### Controlling API Exposure

Two annotations used to control:

- `@RepositoryRestResource(exported=false)` at the class level
- `@RestResource(exported = false)` at the method level

To do this we need to `@Override` the auto generated method signatures.

### HAL Browser

## Chapter 3 - Expose RESTful APIs with Spring MVC


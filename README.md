# fast-and-furious-cinema project

### Decisions

This project is using hexagonal architecture because it let us focus in the domain as the center of the application. In this particular use case the domain looks anemic but as the description says that is a cinema application where we'll deal with different things like ratings, movies and reviews, so it's better to have an architecture that let us extend the application in the future.

The domain model has as aggregate root the `Movie` class because the description focus in it. It contains a list of `shows` and `reviews`. It only holds the required fields because some data comes from IMDb external system

I created a `DomainException` class to represent all the domains rules and validations that I perform in the domain or use cases. I didn't create multiple Exception classes in order to avoid repetitive code between the different exceptions as I'm not doing something special for each exception, I'm only change the message.

The `MovieRepository` is an interface that represents a port of hexagonal architecture. The adapter class is called `MovieRepositoryH2` that it's present in the infrastructure layer. With this, we're decoupling the domain from any framework or library used to access to the Database.

In the application layer, we have all the uses cases (or application service) required to represent the business requirements. They are using the `command` design pattern. Also, I have a port to access to the `imdb` external service. These ports are present here and not in the domain layer because are not part of this specific domain (fast and furious cinema).

In the infrastructure layer we have the Movie rest controller that has a set of endpoints to meet business requirements. As Movie is the aggregate root, all the requirements should be point to this main resource. Also, we have the imdb external system adapter (implementing the port from application layer), this class is using the `webClient` from webflux in order to perform the HTTP requests. Finally, we have an `ExceptionHandler` that return a 400 Bad request when a domain exception is thrown.

As the problem specify that I need to perform requests to external systems (imdb) and database requests, it's valuable to use reactive programming in order to take care of threads. For that reason I'm using `Spring WebFlux` that let me create reactive applications e2e. It's based in Reactive Stream, so it's easy to understand if you have worked with another frameworks like vertx or quarkus, also it's easy to integrate because comes from Spring, so you can take advantage of Spring features. I'm returning `Mono` and `Flux` in the responses to have a reactive response from the rest controller to the repository. In repositories, I'm using `Spring data R2DBC` implementation for `H2`, that let me connect to H2 database in a reactive way.

For testing the domain, I have the `MovieTest` class that has all the test for the Movie aggregate. As the aggregates work as a cluster of objects, I can use a sociable unit test for check the behavior of all the objects using the aggregate root (Movie class). For application and infrastructure tests, I'm using `StepVerifier` from reactor and `mockk` to simulate response from external dependencies and test the Mono and Flux objects.

Finally, although I use Java in my daily work, I used `kotlin` as an opportunity to apply my knowledge in other JVM languages.

I'm using `WireMockStubs` to create the HTTP Stubs. There I'm defining the endpoints and responses, simulating a latency for each request between 2 and 5 seconds (random value).

### Future tasks

* Depending on the future business requirements, we could add more domain validations in the model.

* Create an integration test for imdb external system, using libraries like `WireMock` to simulate the response from the service.

* Create different execution contexts for the database access and external services call to avoid any block in the main threads that receives the requests.

* Create a docker image.

* Replace H2 with an actual database.

* Communicate the different components of the application using an Event Bus.

* Implementing Spring Security to validate the http requests (tokens, cors, etc.) and differentiate the `cinema owners` and `moviegoers` profiles.

* As I'm using Java 16, I could create my custom jdk image using `jlink` in order to have a light version of the jar.

### Running the application

You can run your application using the next gradle command in linux shell (replacing the corresponding imdb api key):

```shell script
IMDB_API_KEY={key} ./gradlew bootRun
```

When application is running, you can test the application acceding to this `swagger` path:

http://localhost:8090/swagger-ui.html

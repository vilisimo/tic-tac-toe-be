# Tic Tac Toe
This is the backend part of a classic tic-tac-toe game. It is responsible for
saving player moves the [frontend](https://github.com/vilisimo/tic-tac-toe-fe)
reports.

## Running Tic Tac Toe BE

### Prerequisites
The app uses MySQL as a database. Hence, you need to have a running MySQL
instance. How to launch it is discussed in the next section.

### Launching the App
There are a couple of ways to run the application.

With `docker-compose.yaml`:

~~~
gradle jibDockerBuild   # builds tictactoe:1.0.0 image
docker-compose up -d    # runs mysql + tictactoe
~~~

With `docker-compose.yaml` + local BE:

~~~
docker-compose up -d mysql
./gradlew run
~~~

Note, however, that with the latter method you will need to do either of the
steps:
 
1. Configure your machine to resolve `mysql` to `127.0.01`
2. Overwrite via `spring.datasource.url` environment variable.
3. Edit the [`application.yaml`](https://github.com/vilisimo/tic-tac-toe-be/blob/master/src/main/resources/application.yaml)
to have

    ~~~
    url: jdbc:mysql://localhost:3306/tictactoe?useUnicode=true&characterEncoding=utf8&createDatabaseIfNotExist=true
    ~~~
   
   instead of 

    ~~~
    url: jdbc:mysql://mysql:3306/tictactoe?useUnicode=true&characterEncoding=utf8&createDatabaseIfNotExist=true
    ~~~

## API
API can be explored through Swagger. Simply launch the application and go to:

- http://localhost:8080/swagger-ui.html#

There you will be able to see available endpoints and example requests/responses. 

In short, it exposes these endpoints:

* `/games -- POST`. Creates a game and returns its id.
* `/games/{id} -- GET`. Retrieve a game by id.
* `/games/{id}/moves -- POST`. Creates a move for a game specified by id.

Example Postman requests are provided in 
[`tic-tac-toe.postman_collection.json`](../../blob/master/tic-tac-toe.postman_collection.json) file.

## Assumptions
Backend was made with a few assumptions in mind. Mainly:

- Backend does not need to be aware of how the game works - winners, turns,
etc. That being said, it will not allow to mark the square that has already
been marked, or make more than 9 moves. Everything else is a fair game.
- There is no need for authentication, authorization, etc.
- There will always be a database that can be reached.

All of the drawbacks could be fixed in one way or the other, however, BE
was written with an idea that it should be as simple as possible.

## Technologies
- Java 8 is used for the main code
- Groovy 2.5.4 is used for tests
- Gradle 5.0 is used as a build tool

To install/upgrade Gradle:
- https://gradle.org/install/

To install/upgrade Groovy:
- http://groovy-lang.org/install.html

Both can be installed pretty easily via Homebrew/SDKMAN.
# ☕️ Java Kata Base

[![Java CI with Maven](https://github.com/rstraub/java-kata-base/actions/workflows/maven.yml/badge.svg)](https://github.com/rstraub/java-kata-base/actions/workflows/maven.yml)

Starter project for Code Katas in Java. Batteries included.

Included:

| Tool                                                  | Type                         |
|-------------------------------------------------------|------------------------------|
| [Maven](https://maven.apache.org)                     | Build Tool                   |
| [Junit 5](https://junit.org/junit5/)                  | Testing Library              |
| [Mockito](https://site.mockito.org)                   | Mocking Library              |
| [Checkstyle](https://checkstyle.sourceforge.io)       | Code Formatting              |
| [Spotbugs](https://spotbugs.github.io)                | Static Code Analysis         |
| [Github Actions](https://github.com/features/actions) | Continuous Integration       |
| [Github Dependabot](https://github.com/dependabot)    | Automatic Dependency Updates |

## Installation

This project requires some tooling on your machine, as described in the `.sdkmanrc`. If you use
sdkman `cd` into the root of this project and run:

```shell
sdk env install
```

to install the required sdks. You can also install them yourself.

## Compile

```shell
mvn compile
```

## Tests

Run unit tests:

```shell
mvn test
```

Run integration tests:

```shell
mvn verify
```

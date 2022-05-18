# 🧸 Breakable Toy Immutables

Playing around with the [Immutables](https://immutables.github.io) library which helps out with making immutable
objects.

# 🔭 Observations On The Immutables Library

Summary of what I liked, and disliked

| Like                                                                                   | Dislike                                                                         |
|----------------------------------------------------------------------------------------|---------------------------------------------------------------------------------|
| Highly [Configurable][config]                                                          | Requires an abstract value type (AVT) to define the model                       |
| No boilerplate methods                                                                 | Always results in a generated class + AVT                                       |
| Lots of "free" code ([Builders][builder], copy methods, factory methods etc)           | [Annotation processing][ap] results in a weird workflow (refactoring, cleaning) |
| Annotation processing produces source files, not bytecode                              | Redundancy due to language features?                                            |
| Container utilities ([Optionals][optional], [Arrays, Collections, Maps][collections])  | Requires [IDE][ide] configuration to offer a good experience                    |
| [Default arguments][defargs] (sort of)                                                 | Invariant protection is awkward due to inheritance (indecent exposure)          |
| Partial func application with [factory builders][facbuild]                             |                                                                                 |
| [Lazy][lazy] evaluation + memoization                                                  |                                                                                 |
| Usage [Patterns][patterns] & defining metatypes                                        |                                                                                 |
| Interop with Java & [Guava][guava] types                                               |                                                                                 |
| Java [records][records] offer zero-boilerplate dataholders, nothing more, nothing less |                                                                                 |
| Interoperability with `sealed` modifier                                                |                                                                                 |

# ☕️ Using Java Kata Base

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

[defargs]: https://immutables.github.io/immutable.html#default-attributes

[facbuild]: https://immutables.github.io/factory.html

[lazy]: https://immutables.github.io/immutable.html#lazy-attributes

[patterns]: https://immutables.github.io/immutable.html#patterns

[builder]: https://immutables.github.io/immutable.html#builder

[collections]: https://immutables.github.io/immutable.html#array-collection-and-map-attributes

[optional]: https://immutables.github.io/immutable.html#optional-attributes

[config]: https://immutables.github.io/style.html

[ide]: https://immutables.github.io/apt.html

[ap]: https://www.baeldung.com/java-annotation-processing-builder

[guava]: https://guava.dev/

[records]: https://docs.oracle.com/en/java/javase/14/language/records.html

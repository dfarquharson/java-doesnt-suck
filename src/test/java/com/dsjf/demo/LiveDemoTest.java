package com.dsjf.demo;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.gson.GsonBuilder;
import io.vavr.Function2;
import io.vavr.Lazy;
import io.vavr.collection.HashMap;
import io.vavr.collection.List;
import io.vavr.control.Either;
import io.vavr.control.Try;
import io.vavr.gson.VavrGson;
import lombok.Builder;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.Value;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.LogManager;
import java.util.stream.Collectors;


import static org.junit.jupiter.api.Assertions.*;

@Log4j2
class LiveDemoTest {

    /*
     * background ideas:
     * - Interactive Development
     * - "Fun"
     * - "Prototyping" (I no longer believe this is even a thing.
     *                  Any sufficiently viable "prototype" becomes a critical piece of a production system.
     *                  Therefore, we should always be writing "production-quality" code.)
     * - Development speed ("Time to Value") -- I'll race 'ya :)
     * - Polyparadigmatism > Polylingualism?
     * - Benefits of polyparadigmatic monolingualism?
     * - Referential Transparency
     * - Immutable OOP == FP?
     * - FP = immutable programming with referentially transparent expressions enabling equational reasoning?
     * - Usefulness of Static Types
     * - Incredible tooling
     */

    // make a quick logger (lombok sneak peek)
//    private static final Logger log = LogManager.getLogManager(LiveDemoTest.class);

    @Test
    void theJavaYouLoveToHate() {
        // old style stuff. no type inference at all. mutable list building with void setters. blargh.
        // "map", "filter" with c-style for loops. the horror.
        // even java8 stuff with its java-y boilerplate stream/collect
        log.info("hi data science");

        java.util.List<Integer> xs = new ArrayList<Integer>();
        xs.add(1);
        xs.add(2);
        log.info(xs);

        java.util.List<Integer> ys = new ArrayList<>();
        for (int i = 0; i < xs.size(); i++) {
            int j = xs.get(i);
            if (j % 2 == 0) {
                ys.add(++j);
            }
        }
        log.info(ys);

        log.info(xs.stream().filter(x -> x % 2 == 0).map(x -> x + 1).collect(Collectors.toList()));
    }

    @Test
    void vavrListsMapsSets() {
        // do fun stuff with lists/maps/sets
        log.info(List.range(1, 10));
        log.info(List.range(1, 10)
                .filter(x -> x % 2 == 0)
                .map(x -> x + 1));
        log.info(HashMap.of("k1", "v1"));
        log.info(VavrGson.registerAll(new GsonBuilder()).create().toJson(HashMap.of("k1", "v1")));
    }

    @Test
    void moreFunVavrTypes() {
        // Try -- recover/getOrElse
        // Either -- typeful heterogeneousness
        // Lazy -- memoize my things, and don't do it until I say so
        try {
            int i = 1/0;
        } catch (Exception e) {
            log.info(e.getMessage());
        }

//        List.range(1,1000).tojav

        log.info(Try.of(() -> 1/0).getOrElse(-1));

        List<Either<Integer, String>> xs = List.of(Either.left(1), Either.right("a"));
        xs.filter(Either::isRight).map(Either::get).forEach(log::info);

        log.info(Math.random());
        log.info(Math.random());

        Lazy<Double> l = Lazy.of(Math::random);
        log.info(l);
        log.info(l.get());
        log.info(l.get());
    }

    @Test
    void vavrFunctions() {
        // automagic currying. Mapping partially applied curried functions.
        // andThen/compose.
        Function2<Integer, Integer, Integer> add = (x, y) -> x + y;
        log.info(List.range(1, 10).map(x -> add.apply(x, 1)));
        log.info(List.range(1, 10).map(add.apply(1)));
        Function2<Integer, Integer, Integer> div = (x, y) -> x / y;
        log.info(div.reversed().apply(2).andThen(add.apply(1)).apply(2));
        log.info(add.apply(1).compose(div.reversed().apply(2)).apply(2));
    }

    @Test
    void patternMatchingInJavaExclamationPoint() {
        // wat?
    }

    @Test
//    @SneakyThrows
    void ioTheEasyWay() throws Exception {
        // Files.asCharSource/asCharSink
        // SneakyThrows?
        log.info(Files.asCharSource(new File(System.getenv("PWD") + "/pom.xml"), Charsets.UTF_8).read());
    }

    private static @Data @Builder
    class BeanPerson {
        String firtName;
        String lastName;
    }

    private @Value
    class Person {
        String firstName, lastName;
    }

    @Test
    void lombokImmutableValueClasses() {
        // bean-style data classes. kill me now.
        // mutable data class.
        // immutable with a mutable builder?
        // immutable value classes made trivial.
        // equals/hashcode/toString
        // new Gson().toJson() too!

//        BeanPerson person = new BeanPerson();
//        person.setFirtName("lamoine");
//        person.setLastName("z");
        BeanPerson person = BeanPerson.builder().firtName("lamoine").lastName("z").build();
        log.info(person);

        Person person2 = new Person("lamoine", "z");
        log.info(person2);

//        person2.firstName = "new things";
    }

    @Test
    void lombokVal() {
        // double wat
        val p = new Person("lamoine", "z");
        log.info(p);
    }

    /*
     * But wait, there's more!
     * - Super easy REST APIs with sparkjava (but seriously, Dropwizard/Spring Boot aren't that bad)
     * - SOOOO much more in guava
     * - jooq (fluent, type-safe sql)
     * - "reactive streams"
     * - Hadoop/Spark and the surrounding ecosystem
     * - deeplearning4j
     * - Processing (Easy to use graphics stuff)
     * - The Checker Framework (add strength to the type system)
     * - OkHttp as the "requests" of java
     * - infinite abyss of JVM libraries
     * - quasar (golang-style channels/green threads)
     * - akka (erlang-style distributed actors)
     * - annotation processors (compile-time metaprogramming)
     * - btw, IntelliJ IDEA is a java swing app. Remember your stereotype of those?
     * - javaEE 1.4 culture != modern java
     */

    /*
     * Yeah, but, can't I just do all this in python?
     * You could, but, for me, this all actually boils down to static types.
     * I want the computer (compiler, IDE, other static analysis tools) to be able to help me as much as possible.
     * I'm not smart enough to use dynamic types.
     * Also, don't make me bring up the GIL... :)
     */
}
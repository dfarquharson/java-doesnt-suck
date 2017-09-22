package com.dsjf.demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
     * - FP = immutable programming with expressions enabling equational reasoning?
     * - Usefulness of Static Types
     * - Incredible tooling
     */

    // make a quick logger (lombok sneak peek)

    @Test
    void theJavaYouLoveToHate() {
        // old style stuff. blargh.
        // "map", "filter" with c-style for loops. the horror.
        // even java8 stuff with its java-y boilerplate stream/collect
    }

    @Test
    void vavrListsMapsSets() {
        // do fun stuff with lists/maps/sets
    }

    @Test
    void moreFunVavrTypes() {
        // Try -- recover/getOrElse
        // Either -- typeful heterogeneousness
        // Lazy -- memoize my things, and don't do it until I say so
    }

    @Test
    void vavrFunctions() {
        // automagic currying. Mapping partially applied curried functions.
        // andThen/compose.
    }

    @Test
    void patternMatchingInJavaExclamationPoint() {
        // wat?
    }

    @Test
    void ioTheEasyWay() {
        // Files.asCharSource/asCharSink
        // SneakyThrows?
    }

    @Test
    void lombokImmutableValueClasses() {
        // bean-style data classes. kill me now.
        // mutable data class.
        // immutable with a mutable builder?
        // immutable value classes made trivial.
        // equals/hashcode/toString
        // new Gson().toJson() too!
    }

    @Test
    void lombokVal() {
        // double wat
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
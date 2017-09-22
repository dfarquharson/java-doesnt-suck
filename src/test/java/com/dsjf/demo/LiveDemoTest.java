package com.dsjf.demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LiveDemoTest {

    /*
     * background ideas:
     * - Interactive Development
     * - Polyparadigmatism > Polylingualism?
     * - Benefits of polyparadigmatic monolingualism?
     * - Referential Transparency
     * - Immutable OOP == FP?
     * - Usefulness of Static Types
     * - Incredible tooling
     */

    // make a quick logger (lombok sneak peek)

    @Test
    void theJavaYouLoveToHate() {
        // old style stuff. blargh.
        // even java8 stuff with its java-y boilerplate stream/collect
    }

    @Test
    void vavrListsMapsSets() {
        // do fun stuff with lists/maps/sets
    }

    @Test
    void moreFunVavrTypes() {
        // Try/Either/Lazy
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
        // immutable classes made trivial.
        // equals/hashcode/toString
        // new Gson().toJson() too!
    }

    @Test
    void lombokVal() {
        // double wat
    }

    /*
     * But wait, there's more!
     * - Super easy REST APIs with sparkjava
     * - SOOOO much more in guava
     * - jooq
     * - "reactive streams"
     * - Hadoop/Spark and the surrounding ecosystem
     * - deeplearning4j
     * - Processing (Graphics stuff)
     * - The Checker Framework
     * - OkHttp as the "requests" of java
     * - infinite abyss of JVM libraries
     */

    /*
     * Yeah, but, can't I just do all this in python?
     * You could, but, for me, this all actually boils down to static types.
     * I want the computer (compiler, IDE, other static analysis tools) to be able to help me as much as possible.
     * I'm not smart enough to use dynamic types.
     * Also, don't make me bring up the GIL... :)
     */
}
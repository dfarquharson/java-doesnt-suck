package com.dsjf.demo;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.vavr.Function0;
import io.vavr.Function1;
import io.vavr.Function2;
import io.vavr.Lazy;
import io.vavr.Tuple;
import io.vavr.collection.HashMap;
import io.vavr.collection.HashSet;
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
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;
import static org.junit.jupiter.api.Assertions.*;

@Log4j2
class DemoTest {

    /*
     * Remember when you had this boilerplate in every class?
     * Well, you're going to have to find something else to boil on that plate,
     * because lombok's @Log4j2 annotation handles that for us at compile-time.
     */
//    private static Logger log = LogManager.getLogger(DemoTest.class);


    /*
     * As a recovering Pythonista, one of the things I was adamant about was
     * the absolute necessity of "interactive development in the repl".
     * Turns out I was right about the former, but misguided about the latter.
     * In java-land, we do our interactive development in tests.
     * Turns out, this is actually MUCH better than the ephemeral repl because
     * your sessions are now "just another test".
     * This very buffer is just such an example.
     */

    @Test
    void theJavaYouKnew() {
        log.info("hello world");

        // Remember when there was no type inference? You hated it.
        java.util.List<String> xs1 = new ArrayList<String>();
        // and you had to mutate stuff all over the place just to do basic stuff...
        xs1.add("a");
        xs1.add("b");
        xs1.add("c");

        // sure, you had some fun tricks
        java.util.List<String> xs5 = new ArrayList<String>() {{
            add("a");
            add("b");
            add("c");
        }};

        java.util.List<String> xs2 = Arrays.asList("a", "b", "c");

        assertEquals(xs1, xs2);
        assertEquals(xs1, xs5);

        // but still, you wanted list literals.

        // remember when creating a new list from some existing one was horrible?
        java.util.List<String> xs3 = new ArrayList<>(); // at least we got some type inference to make life a little better...
        for (String s : xs1) {
            xs3.add(s.toUpperCase());
        }

        log.info(xs3);

        // things got better in java 8, but we still didn't like the .stream() .collect(Collectors.toList()) madness
        java.util.List<String> xs4 = xs1.stream().map(String::toUpperCase).collect(Collectors.toList());

        assertEquals(xs3, xs4);

        // java improved over the years, but it still sucked. It still wasn't fun...
    }

    @Test
    void javaConsideredSufficientlyFunctionalQuestionMark() {
        /* TL;DR: Functional Programming is about Referential Transparency
         * Also, don't bother to read the incoherent babbling below, it is fragmentary at best.
         *
         * What, exactly, is this "functional programming" that the hipsters rant and rave about?
         * The definition is hotly debated amongst functional programmers.
         * Most agree that at least one necessary feature is first class functions,
         * and this seems reasonable given that the entire paradigm has that word "function" embedded in it,
         * but I would contest the necessity of first class functions to functional programming.
         * I claim that some other equivalently powerful mechanism, objects for example,
         * can take the role that functions play in functional programming.
         *
         * So, if functional programming isn't really about "functions", what is it about?
         * Well, it's about functions.
         * Wait what? Didn't you just say it wasn't about functions?
         * Kinda. Before I was referring to functions in the context of programming languages,
         * which are often essentially just subroutines, poking and prodding the state of our machines at will.
         * What functional programming is really about is functions as we define them in mathematics.
         * A mathematical function takes some input and produces some output.
         * Given the same input, it will always produce the same output.
         * Always. No exceptions.
         * As such, a function call can always be replaced by its definition
         * without any effect on the result of the surrounding program.
         * This allows us to perform the "substitution model" of equational reasoning.
         * This is a property that we "functional programmers" call Referential Transparency.
         * Referential Transparency is what "functional programming" is all about.
         * This implies immutability. Functional programming is the enemy of mutable state.
         */

        /*
         * It is my contention that your bad memories of java are tainted by old styles of writing it
         * not by the current state of the language or its modern libraries
         */

        List<String> xs1 = List.of("a", "b", "c");
        log.info(xs1);

        // no more .stream() .collect(Collectors.toList()) madness!
        List<String> xs2 = List.of("A", "B", "C").map(String::toLowerCase);

        assertEquals(xs1, xs2);

        // but in your dynamic languages you liked to be a little fast and loose with your data sometimes,
        // maybe even doing something silly like mixing the types of the data in your list, essentially like this:
        List<Object> xs3 = List.of(1, "cow", 2.0, List.empty());

        // this is still something you can do, but it just becomes difficult to safely do anything to the elements of such a list.
        // doing so would involve a lot of junk like this:

        java.util.List<Integer> xs4 = new ArrayList<>();
        for (Object o : xs3) {
            try {
                xs4.add(((Integer) o) + 1);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
        log.info(xs4);

        // but maybe we can do better in vavr-land
        java.util.List<Integer> xs5 = xs3
                .map(o -> Try.of(() -> (((Integer) o) + 1)).getOrElse(-1))
                .filter(x -> x > 0)
                .toJavaList();

        assertEquals(xs4, xs5);

        // the strawman java that we remember: as verbose as possible
        java.util.List<Integer> xs6 = new ArrayList<Integer>();
        xs6.add(1);
        xs6.add(2);
        xs6.add(3);
        xs6.add(4);
        log.info(xs6);

        java.util.List<Integer> xs7 = new ArrayList<Integer>();
        for (int i = 0; i < xs6.size(); i++) {
            int j = xs6.get(i);
            if (j % 2 == 0) {
                xs7.add(++j);
            }
        }
        log.info(xs6);
        log.info(xs7);
        assertEquals(
                new ArrayList<Integer>() {{
                    add(3);
                    add(5);
                }},
                xs7
        );

        // vavr-land cometh
        assertEquals(
                List.range(1, 5).filter(x -> x % 2 == 0).map(x -> x + 1).toJavaList(),
                xs7
        );
    }

    @Test
    void vavrLists() {
        assertEquals(
                List.of(2, 3, 4),
                List.of(1, 2, 3).map(x -> x + 1)
        );
        assertEquals(
                List.of(2, 4),
                List.of(1, 2, 3).map(x -> x + 1).filter(x -> x % 2 == 0)
        );
        assertEquals(
                Arrays.asList(1, 2, 3),
                List.of(1, 2, 3).toJavaList()
        );
        assertEquals(
                List.of(1, 2, 3, 4, 5),
                List.range(1, 6)
        );
        assertEquals(
                List.of(2, 4, 6, 8),
                List.rangeBy(2, 9, 2)
        );
        log.info(List.range(1, 1000).shuffle().head());
        assertTrue(
                List.range(1, 1000).contains(List.range(1, 1000).shuffle().head())
        );
    }

    @Test
    void vavrSets() {
        assertEquals(
                HashSet.of(2),
                HashSet.of(1, 2, 3).intersect(HashSet.of(2, 4, 6))

        );
        assertEquals(
                HashSet.of(3),
                HashSet.of(2, 3, 4).diff(HashSet.of(2, 4, 6))
        );
    }

    @Test
    void vavrMaps() {
        assertEquals(
                HashMap.of(1, "a", 2, "b"),
                HashMap.of(2, "b", 1, "a")
        );
        assertEquals(
                List.of(Tuple.of(1, "a"), Tuple.of(2, "b")),
                HashMap.of(1, "a", 2, "b").toList()
        );
    }

    @Test
    void vavrMoreHelpfulTypes() {
        List<Either<String, Integer>> xs1 = List.of(Either.left("a"), Either.right(1));
        assertEquals(
                List.of(1),
                xs1.filter(Either::isRight).map(Either::get)
        );

        Lazy<Double> l = Lazy.of(Math::random);
        log.info(Math.random());
        log.info(Math.random());
        Function0<Double> r = Math::random;
        log.info(r.apply());
        log.info(r.apply());
        assertFalse(l.isEvaluated());
        log.info(l.get());
        log.info(l.get());
        assertEquals(l.get(), l.get());

        assertEquals(
                "whoops, tried to divide by zero.",
                Try.of(() -> Integer.toString(1 / 0))
                        .recover(ArithmeticException.class, x -> "whoops, tried to divide by zero.")
                        .recover(Exception.class, x -> "something really weird happened")
                        .getOrElse("getting this string is impossible")
        );
    }

    @Test
    void vavrFunctions() {
        Function1<Integer, Integer> add1 = x -> x + 1;
        assertTrue(add1.apply(1) == 2);

        Function2<Integer, Integer, Integer> add = (x, y) -> x + y;
        Function1<Integer, Integer> add2 = add.apply(2);
        assertTrue(add2.apply(1) == 3);

        assertEquals(
                List.of(2, 3, 4),
                List.of(1, 2, 3).map(add.apply(1))
        );

        Function2<Integer, Integer, Integer> div = (x, y) -> x / y;
        Function1<Integer, Integer> div2 = div.reversed().apply(2);

        assertTrue(add2.andThen(div2).apply(2) == 2);
        assertEquals(
                add2.andThen(div2).apply(2),
                div2.compose(add2).apply(2)
        );
    }

    @Test
    void patternMatching() {
        int i = 2;
        assertEquals(
                "two",
                Match(i).of(
                        Case($(1), "one"),
                        Case($(2), "two"),
                        Case($(), "?"))
        );
    }

    @Test
    @SneakyThrows
    void ioTheEasyWay() {
        Files.asCharSource(
                new File(System.getenv("PWD") + "/src/test/java/com/dsjf/demo/LiveDemoTest.java"),
                Charsets.UTF_8
        ).readLines().forEach(log::info);
        Files.asCharSink(new File("tmp.json"), Charsets.UTF_8)
                .write(VavrGson.registerAll(new GsonBuilder()).create().toJson(HashMap.of("key", "value")));
    }

    private @Data
    class MutablePerson {
        String firstName;
        String lastName;
        Integer ssn;
    }

    @Test
    void lombokMutableClasses() {
        MutablePerson p = new MutablePerson();
        p.setFirstName("horrible");
        p.setLastName("mutable-person");
        p.setSsn(123);
        assertEquals("horrible", p.getFirstName());
        p.setFirstName("even worse");
        assertNotEquals("horrible", p.getFirstName());
    }

    private static @Data
    @Builder
    class BuildablePerson {
        String firstName;
        String lastName;
        Integer ssn;
    }

    @Test
    void lombokBuilderClasses() {
        log.info(BuildablePerson.builder().firstName("a").lastName("b").ssn(1).build());
        assertEquals(
                BuildablePerson.builder().firstName("a").lastName("b").ssn(1).build(),
                BuildablePerson.builder().firstName("a").ssn(1).lastName("b").build()
        );
    }

    private @Value
    class Person {
        String firstName;
        String lastName;
        Integer ssn;
    }

    @Test
    void lombokGoodness() {
        assertEquals(
                "Philip",
                new Person("Philip", "Wadler", 123456789).firstName
        );
        assertEquals(
                new Person("Alonzo", "Church", 987654321),
                new Person("Alonzo", "Church", 987654321)
        );
        assertEquals(
                1,
                HashMap.of(
                        new Person("Haskell", "Curry", 1), 1,
                        new Person("Haskell", "Curry", 1), 2
                ).size()
        );
        log.info(new Person("Alan", "Turing", 2));
        log.info(new Gson().toJson(new Person("Rich", "Hickey", 3)));
    }

    @Test
    void lombokVal() {
        val xs = List.of(1, 2, 3);
        log.info(xs);
        assertEquals(List.of(1, 2, 3), xs);
    }

}
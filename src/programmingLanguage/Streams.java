package programmingLanguage;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static programmingLanguage.Utils4Stream.arrayToLine;
import static programmingLanguage.Utils4Stream.streamToLine;
import static programmingLanguage.Utils4Stream.console;

public class Streams {

    public static void main(String[] args) {
        Integer[] integers = {1, 2, 3, 4, 1, 2, 3, 4};
        List<Integer> integerList = Arrays.asList(integers);

        /* CREATING */
        Stream<Integer> streamFromCollection = integerList.stream();
        Stream<Integer> streamFromCollectionUseMultiCore = integerList.parallelStream();
        Stream<Integer> streamFromValues = Stream.of(1, 2, 3, 4, 5);
        Stream<Integer> streamFromArrays = Arrays.stream(integers);
//      Stream<String> streamFromFiles = Files.lines(Paths.get("file.txt"));
        IntStream streamFromString = "123".chars();
        Stream.builder().add("a1").add("a2").add("a3").build();
        Stream<Integer> streamFromIterate = Stream.iterate(1, n -> n + 1);        //--> infinity {1,2,3,4,5,6...}
        Stream<String> streamFromGenerate = Stream.generate(() -> "a1");          //--> infinity {"a1","a1","a1"...}

        /* END METHODS */
        List<String> stringCollection = Arrays.asList("a", "b", "c", "d", "e");
        console("Collection of Strings : " + arrayToLine(stringCollection));

        console("Find first : " + stringCollection.stream().findFirst().orElse("Nothing"));
        console("Find any : " + stringCollection.stream().findAny().orElse("Nothing"));

        // <collect>
        List<Integer> integerCollection = stringCollection.stream().collect(
                (Supplier<List<Integer>>) ArrayList::new,
                (BiConsumer<List<Integer>, String>) (List<Integer> list, String s) -> list.add((int) s.getBytes()[0]),
                (BiConsumer<List<Integer>, List<Integer>>) (left, right) -> left.addAll(right));
        console("Collection of Integers : " + arrayToLine(integerCollection));

        Double aDouble = stringCollection.stream().collect(Collectors.averagingInt(o -> o.hashCode()));

        String thirdElement = stringCollection.stream().collect(
                Collectors.collectingAndThen(Collectors.toList(), (Function<List<String>, String>) list -> list.get(2)));

        Long size = stringCollection.stream().collect(Collectors.counting());

        Map<String, List<String>> groupMap = stringCollection.stream().collect(Collectors.groupingBy(o -> o.equals("a") ? "group 1" : "group 2"));
        console("Grout 1 : " + arrayToLine(groupMap.get("group 1")));
        console("Grout 2 : " + arrayToLine(groupMap.get("group 2")));

        String line = stringCollection.stream().collect(Collectors.joining(" [after_every] ", "[Before_first] ", " [After_last]"));
        console(line);

        Map<String, String> map = stringCollection.stream().collect(Collectors.toMap(a -> a + "_key", a -> a + "_value"));
        // </collect>

        Predicate<String> hasSymbolA = o -> o.equals("a");
        boolean anyMatch = stringCollection.stream().anyMatch(hasSymbolA);
        boolean noneMatch = stringCollection.stream().noneMatch(hasSymbolA);
        boolean allMatch = stringCollection.stream().allMatch(hasSymbolA);

        String min = stringCollection.stream().min(String::compareTo).get();
        String max = stringCollection.stream().max(String::compareTo).get();
        console("min " + min + "; max " + max);

        stringCollection.stream().forEach(System.out::printf);
        stringCollection.stream().forEachOrdered(System.out::printf);

        String[] strings = stringCollection.stream().toArray(String[]::new);

        String reduce = stringCollection.stream().reduce((s1, s2) -> s1 + s2).orElse("Nothing");
        console("Reduce : " + reduce + "\n");


        /* EDIT METHODS */
        Stream<Integer> testStream = integerList.stream();
        console("Full array : " + streamToLine(testStream));

        testStream = integerList.stream().filter(o -> o % 2 == 1);
        console("Filter : " + streamToLine(testStream));

        testStream = integerList.stream().skip(2);
        console("Skip : " + streamToLine(testStream));

        testStream = integerList.stream().limit(2);
        console("Limit : " + streamToLine(testStream));

        testStream = integerList.stream().distinct();
        console("Distinct : " + streamToLine(testStream));

        Long collect = integerList.stream().peek(o -> System.out.print(o + "~")).collect(Collectors.counting());

        Comparator<Integer> goodComparator = (o1, o2) -> {
            Integer i1 = o1.byteValue() % 2;
            Integer i2 = o2.byteValue() % 2;
            return i1.compareTo(i2);
        };
        testStream = integerList.stream().sorted(goodComparator);
        console("Sort : " + streamToLine(testStream));

        Map<List<String>, List<Integer>> normalMap = new HashMap<>();
        normalMap.put(Arrays.asList("a", "b"), Arrays.asList(1, 2));
        normalMap.put(Arrays.asList("c", "d"), Arrays.asList(3, 4));
        normalMap.put(Arrays.asList("e", "f"), Arrays.asList(5, 6));

        Stream<String> stringStream = normalMap.entrySet().stream().flatMap(listListEntry -> listListEntry.getKey().stream());
        console("flatMap : " + streamToLine(stringStream));

        /////////////////////////////////////////////////////////////
        Supplier<Stream<String>> streamSupplier =
                () -> Stream.of("dd2", "aa2", "bb1", "bb3", "cc").filter(s -> s.startsWith("a"));
        Stream<String> stringOfSupplier = streamSupplier.get();
    }


}

class Utils4Stream {
    public static String arrayToLine(List<? extends Object> list) {
        return list.stream().map(Object::toString).collect(Collectors.joining(", "));
    }

    public static String streamToLine(Stream<? extends Object> stream) {
        return stream.map(Object::toString).collect(Collectors.joining(", "));
    }

    public static void console(String string) {
        System.out.print("\n" + string);
    }
}

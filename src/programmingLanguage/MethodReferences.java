package programmingLanguage;

import java.util.ArrayList;
import java.util.List;
import java.util.function.*;

public class MethodReferences {

    public static void main(String[] args) {
        Predicate<Integer> isEven = obj -> obj % 2 == 0;
        boolean firstCase = isEven.test(5);
        boolean secondCase = isEven.negate().test(5);
        boolean thirdCase = isEven.or(isEven.negate()).test(5);
        System.out.println(firstCase + " " + secondCase + " " + thirdCase);


        Supplier<NumberSeven> creator = NumberSeven::new;
        NumberSeven seven = creator.get();
        List list = MethodReferences.giveMeTwo(NumberSeven::new);
        System.out.println(list.get(0) + " " + list.get(1));

        Supplier<Integer> getInt = NumberSeven::getInt;
        System.out.printf("getInt : " + getInt);


        Function<String, String> addPlus = obj ->  obj = obj + "+";
        Function<String, String> bestFunction = addPlus.andThen(addPlus).andThen(obj -> obj = obj + "!");
        System.out.println(bestFunction.apply("C"));

        Consumer<String> greeter = (obj) -> System.out.println("Hello, " + obj);
        greeter.accept("Vasia");

        /* Bi */

        BiFunction<String, String, Object> stringComparator =
                (String first, String second) -> Integer.compare(first.length(), second.length());

        BiConsumer<String, Integer> introducion =
                (String name, Integer age) -> System.out.println("Hello I am " + name + ". I am " + age + "years old.");

    }

    public static List giveMeTwo(Supplier supplier){
        List list = new ArrayList<>();
        list.add(supplier.get());
        list.add(supplier.get());
        return list;
    }
}

class NumberSeven{
    @Override
    public String toString() {
        return "NumberSeven";
    }

    static public int getInt(){
        return 7;
    }
}
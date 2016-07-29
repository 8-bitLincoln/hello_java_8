package programmingLanguage;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

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


        Function<String, String> addPlus = obj -> obj = obj + "+";
        Function<String, String> bestFunction = addPlus.andThen(addPlus).andThen(obj -> obj = obj + "!");
        System.out.println(bestFunction.apply("C"));


        Consumer<String> greeter = (obj) -> System.out.println("Hello, " + obj);
        greeter.accept("Vasia");
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
}
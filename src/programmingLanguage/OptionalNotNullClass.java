package programmingLanguage;

import java.util.Optional;

public class OptionalNotNullClass {
    public static void main(String[] args) throws Exception {
        Optional<String> fullName = Optional.ofNullable(null);
        System.out.println("Full Name is set? " + fullName.isPresent());
        System.out.println(fullName);
        fullName = Optional.of("HasValue");
        System.out.println(fullName.get());
    }
}

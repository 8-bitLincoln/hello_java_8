package programmingLanguage;

public class DefaultMethods {
    public static void main(String[] args) {
        new classOne().methodInInterface();
    }
}
interface interfaceOne {
    default void methodInInterface(){
        System.out.println("method");
    }
}

class classOne implements interfaceOne {}



package programmingLanguage;

import java.math.BigInteger;
import java.time.Clock;
import java.util.function.Function;

public class Lambda {

    /*
    *
    * Why the name? Many years ago, before there were any computers, the logician Alonzo Church wanted to formalize
    * what it means for a mathematical function to be effectively computable. He used the Greek letter lambda (λ)
    * to mark parameters. Had he known about the Java API, he would have written:
    *
    * λfirst.λsecond.Integer.compare(first.length(), second.length())
    *
    * */

    public static void main(String[] args) {
        int i = 0;
        Function<Integer, Integer> plusOneFunction =  o -> o + 1;
        System.out.println("i = " + i + " , after plusOneFunction i = " + plusOneFunction.apply(i) );

        Function<String, String> getAnswerFunction =  (String question) -> {
            if (BigInteger.valueOf(Clock.systemUTC().millis()).mod(BigInteger.valueOf(2)).intValue() == 1) {
                return "Yes";
            } else {
                return "No";
            }
        };
        System.out.println("What is the meaning of life? " + getAnswerFunction.apply(""));

    }
}

package programmingLanguage;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class ArraysParallel {
    public static void main(String[] args) {

	//comment to make changes on git

        long[] arrayOfLong = new long [ 20000 ];

        Arrays.parallelSetAll( arrayOfLong,
                index -> ThreadLocalRandom.current().nextInt( 1000000 ) );
        for (long i : arrayOfLong){
            System.out.print( i + " " );
        }
        Arrays.parallelSort( arrayOfLong );
        System.out.print("\n ParallelSort! \n");
        for (long i : arrayOfLong){
            System.out.print( i + " " );
        }

        Arrays.parallelPrefix(arrayOfLong, ( x, y ) -> x + y);
        System.out.print("\n ParallelPrefix! \n");
        for (long i : arrayOfLong){
            System.out.print( i + " " );
        }
    }
}

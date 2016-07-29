package programmingLanguage;

import java.time.*;
import java.util.Random;

public class DateAndTime {
    public static void main(String[] args) throws Exception {
        Clock clock = Clock.systemUTC();
        System.out.println( clock.instant() );
        System.out.println( clock.millis() );
        long startTime = clock.millis();
        for(int i = 0; i < 10; i++){
            String string = "";
            for(int j = 0; j<10000; j++){
                string += new Random().nextInt();
            }
            System.out.printf("*");
        }
        System.out.println("\nWorking time: " + (Clock.systemUTC().millis() - startTime)/1000.0 + "s \n");

        LocalDate date = LocalDate.now();
        LocalDate dataPlusDay = LocalDate.now().plusDays(1);
        System.out.println( date + ", " + dataPlusDay );
        System.out.println( "Is date after dataPlusDay: " + date.isAfter(dataPlusDay) );
        System.out.println( "Until: " + date.until(dataPlusDay) + "\n");

        LocalTime time = LocalTime.now();
        LocalTime timeFromClock = LocalTime.now( clock );
        System.out.println( time );
        System.out.println( timeFromClock );
        System.out.println( "Compare: " + timeFromClock.compareTo(time));
        System.out.println( "Compare: " + time.compareTo(time));
        System.out.println( "Compare: " + time.compareTo(timeFromClock) + "\n");

        LocalDateTime datetime = LocalDateTime.now();
        LocalDateTime datetimeFromClock = LocalDateTime.now( clock );
        System.out.println( datetime );
        System.out.println( datetimeFromClock  + "\n");

        ZonedDateTime zonedDatetime = ZonedDateTime.now();
        ZonedDateTime zonedDatetimeFromClock = ZonedDateTime.now( clock );
        ZonedDateTime zonedDatetimeFromZone = ZonedDateTime.now( ZoneId.of( "America/Los_Angeles" ) );
        System.out.println( zonedDatetime );
        System.out.println( zonedDatetimeFromClock );
        System.out.println( zonedDatetimeFromZone );

    }
}
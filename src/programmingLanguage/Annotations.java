package programmingLanguage;

import java.lang.annotation.*;
import java.util.ArrayList;
import java.util.Collection;

public class Annotations {

    /*Repeatable Annotations*/

    @Target( ElementType.TYPE )
    @Retention( RetentionPolicy.RUNTIME )
    public @interface Filters {
        Filter[] value();
    }

    @Target( ElementType.TYPE )
    @Retention( RetentionPolicy.RUNTIME )
    @Repeatable( Filters.class )
    public @interface Filter {
        String value();
    };

    @Filter( "filter1" )
    @Filter( "filter2" )
    public interface Filterable {
    }

    /*Annotations everywhere*/

//  Type Annotations provide the ability to apply an annotation anywhere a type is used, not just on a declaration.

    @Retention( RetentionPolicy.RUNTIME )
    @Target( { ElementType.TYPE_USE, ElementType.TYPE_PARAMETER } )
    public @interface NonEmpty {
    }

    public static class Holder< @NonEmpty T > extends @NonEmpty Object {
        public void method() throws @NonEmpty Exception {
        }
    }

    public static void main(String[] args) {
        @NonEmpty Collection< @NonEmpty String > strings = new ArrayList<>();
    }
}


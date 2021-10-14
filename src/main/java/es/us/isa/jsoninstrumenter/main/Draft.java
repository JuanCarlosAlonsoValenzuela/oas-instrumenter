package es.us.isa.jsoninstrumenter.main;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class Draft {

        public static String getMonthName(int month) {
            return LocalDate.of(2020,month, 1).getMonth().getDisplayName(TextStyle.FULL, Locale.US);
        }

}

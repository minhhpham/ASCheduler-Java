
package ShiftPackage;
import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Create an object for shifts including WeekDay and Hour
 * ShiftClass(WeekDay, Hour)
 *      WeekDay: string Monday through Sunday
 *      Hour: int 0 to 23
 * Attributes:
 *      .WeekDay: string
 *      .Hour: int
 *      .concat: string, print out Day & Time
 */
public class ShiftClass {

    public String WeekDay;
    public int Hour;
    public String concat;
    
    /**
    * @param WeekDay: string Monday through Sunday
    * @param Hour: int 0 to 23
    */
    public ShiftClass(String WeekDay, int Hour){
        // validate data
        String[] ValidDays = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        IntStream ValidHours = IntStream.range(0, 23);
        
        if (!Arrays.asList(ValidDays).contains(WeekDay)){
            throw new RuntimeException("WeekDay is not valid!");
        }
        if (!ValidHours.anyMatch(x -> x == Hour)){
            throw new RuntimeException("Hour is not valid!");
        }
                
        this.WeekDay = WeekDay; //WeekDay
        this.Hour = Hour; // Hour
        this.concat = WeekDay + " " + Hour + ":00:00"; // print out all attributes
    }
}

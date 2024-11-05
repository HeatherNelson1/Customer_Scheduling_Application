package Utilities;

import java.time.ZoneId;
import java.util.TimeZone;

public abstract class  Zone {

    TimeZone tz = TimeZone.getTimeZone(ZoneId.systemDefault());

}

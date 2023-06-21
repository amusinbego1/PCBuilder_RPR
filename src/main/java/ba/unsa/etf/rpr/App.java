package ba.unsa.etf.rpr;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */

/*TODO
    1. Change test methods names
 */
public class App 
{
    public static void main( String[] args )
    {
        try {
            AppFX.main(args);
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}

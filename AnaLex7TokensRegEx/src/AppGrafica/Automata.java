package AppGrafica;

import java.util.regex.Pattern;

/**
 *
 * @author Misael Adame
 */
public class Automata {
    Pattern[] afd = new Pattern[7];
    
    public Automata() {
        afd[0] = Pattern.compile("\\s+"); // delim
        afd[1] = Pattern.compile("([A-Za-z]|_)([A-Za-z]|_|[0-9])*"); // id
        afd[2] = Pattern.compile("[\\+\\-\\*/]=|="); // opasig
        afd[3] = Pattern.compile("[\\+\\-\\*/]"); // oparit
        afd[4] = Pattern.compile("[0-9]+"); // num
        afd[5] = Pattern.compile("\\(|\\)"); // sep
        afd[6] = Pattern.compile("\\;"); // terminstr
    }
}

package AppGrafica;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Misael Adame
 */
public class Automata {
    Pattern[] afd = new Pattern[9];
    
    public Automata() {
        afd[0] = Pattern.compile("\\s+"); // delim
        afd[1] = Pattern.compile("([A-Za-z]|_)([A-Za-z]|_|[0-9])*"); // id
        afd[2] = Pattern.compile("[\\+\\-\\*/]=|="); // opasig
        afd[3] = Pattern.compile("[\\+\\-\\*/]"); // oparit
        afd[8] = Pattern.compile("[0-9]+"); // num    100
        afd[4] = Pattern.compile("[0-9]+\\.[0-9]+");  // num    234.89
        afd[7] = Pattern.compile("[0-9]+\\."); // num    911.
        afd[5] = Pattern.compile("\\(|\\)"); // sep
        afd[6] = Pattern.compile("\\;"); // terminstr
    }
    
    public boolean Reconoce (String texto, int iniToken, int[] i, int noAuto ) {
        Matcher m = afd[noAuto].matcher(texto);
        if (m.find(iniToken))
            if (m.start()==iniToken) {
                i[0] = m.end();
                return true;
            }
            else 
                return false;
        else
            return false;
    }
}

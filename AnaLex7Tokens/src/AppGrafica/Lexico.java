package AppGrafica;

/**
 *
 * @author Misael Adame
 */
public class Lexico {
    
    final int TOKREC = 7;
    final int MAXTOKENS = 500;
    String[] _lexemas;
    String[] _tokens;
    String _lexema;
    int _noTokens;
    int[] _i = {0};
    int _iniToken;
    Automata oAFD;
    
    public int NoTokens() {
        return _noTokens;
    }
    
    public String[] Tokens() {
        return _tokens;
    }
    
    public String[] Lexemas() {
        return _lexemas;
    }
}

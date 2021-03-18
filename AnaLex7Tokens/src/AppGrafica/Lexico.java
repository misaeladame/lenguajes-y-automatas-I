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
    
    // Constructor por defecto
    public Lexico() {
        _lexemas = new String[MAXTOKENS];
        _tokens = new String[MAXTOKENS];
        oAFD = new Automata();
        _i[0] = 0;
        _iniToken = 0;
        _noTokens = 0;
    }
    
    public void Inicia() {
        _i[0] = 0;
        _iniToken = 0;
        _noTokens = 0;
    }
    
    public void Analiza(String texto) {
        boolean recAuto;
        int noAuto;
        while(_i[0] < texto.length()) {
            recAuto = false;
            noAuto = 0;
            for(;noAuto<TOKREC && !recAuto;)
                if(oAFD.Reconoce(texto, _iniToken, _i, noAuto))
                    recAuto = true;
                else 
                    noAuto++;
        }
    }
}

package AppGrafica;

/**
 *
 * @author Misael Adame
 */
public class Automata {
    String _textoIma;
    int _edoAct;
    int[] _i;
    
    public boolean Reconoce(String texto, int iniToken, int[] i, int noAuto) {
        char c;
        _textoIma = texto;
        _i = i;
        
        // BLOQUE 1
        switch (noAuto) {
            case 0: _edoAct = 0;
                break;
            case 1: _edoAct = 3;
                break;
            case 2: _edoAct = 6;
                break;
            case 3: _edoAct = 9;
                break;
            case 4: _edoAct = 11;
                break;
            case 5: _edoAct = 14;
                break;
            case 6: _edoAct = 16;
                break;
        }  // FIN DEL BLOQUE 1
        return false;
    }
}

package AppGrafica;

/**
 *
 * @author Misael Adame
 */
public class Automata {
    String _textoIma;
    int _edoAct;
    int[] _i;
    
    char SigCar() {
        if (_i[0] == _textoIma.length()) {
            _i[0]++;
            return '₴';
        }
        else
            return _textoIma.charAt(_i[0]++);
    }
    
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
        // BLOQUE 2
        while(i[0]<=texto.length()) 
            switch(_edoAct) {
                // delim ---------------------------------------
                case 0:
                    c = SigCar();
                    if (c == ' ' || c == '\t' || c == '\n' 
                            || c == '\r')
                        _edoAct = 1;
                    else {
                        i[0] = iniToken;
                        return false;
                    }
                    break;
                case 1:
                    c = SigCar();
                    if (c != ' ' && c != '\t' && c == '\n' 
                            && c != '\r')
                        _edoAct = 2;
                    break;
                case 2:
                    i[0]--;
                    return true;
                // id -------------------------------------------
                case 3:
                    c = SigCar();
                    if (Character.isLetter(c) || c == '_')
                        _edoAct = 4;
                    else {
                        i[0] = iniToken;
                        return false;
                    }
                    break;
                case 4:
                    c = SigCar();
                    if (! Character.isLetter(c) && !Character.isDigit(c) && c != '_')
                        _edoAct = 5;
                    break;
                case 5:
                    i[0]--;
                    return true;
                    
                    
            }
        return false;
    }
}

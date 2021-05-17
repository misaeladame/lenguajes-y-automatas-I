package AppGrafica;

public class SintDescNRP {
    
    private Pila _pila;
    private String[] _vts = { "", "id", "=", ";", "+", "-", "*", "/", "num", "(", ")", "$" };
    private String[] _vns = { "", "A", "E", "E'", "T", "T'", "F" };
    private int _noVts;
    
    public SintDescNRP () {
        _pila = new Pila();
        _noVts = _vts.length;
    }
    
    public int Analiza(Lexico oAnaLex) {
        SimbGram x;
        String a;
        _pila.Inicia();
        _pila.Push(new SimbGram("$"));
        _pila.Push(new SimbGram(_vns[1]));
        oAnaLex.Anade ( "$", "$" );
        int ae = 0;
        do {
            x = _pila.Tope();
            a = oAnaLex.Tokens()[ae];
            if ( EsTerminal(x.Elem()))
        } while (!x.Elem().equals("$"));
    }
    
    public boolean EsTerminal(String x) {
        for (int i = 1; i < _noVts; i++)
            if (_vts[i].equals(x))
                return true;
        return false;           
    }
    
    
}

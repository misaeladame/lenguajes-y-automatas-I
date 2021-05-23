package AppGrafica;

public class SintDescNRP {
    
    private Pila _pila;
    private String[] _vts = { "", "id", "=", ";", "+", "-", "*", "/", "num", "(", ")", "$" };
    private String[] _vns = { "", "A", "E", "E'", "T", "T'", "F" };
    private int [][] _prod = {{1,4,-1,-2,2,-3},        // A  -> id = E ;
                              {2,2,4,3,0,0},           // E  -> T E'
                              {3,3,-4,4,3,0},          // E' -> + T E'
                              {3,3,-5,4,3,0},          // E' -> - T E'
                              {3,0,0,0,0,0},           // E' -> empty
                              {4,2,6,5,0,0},           // T  -> F T'
                              {5,3,-6,6,5,0},          // T' -> * F T'
                              {5,3,-7,6,5,0},          // T' -> / F T'
                              {5,0,0,0,0,0},           // T' -> empty
                              {6,1,-1,0,0,0},          // F  -> id
                              {6,1,-8,0,0,0},          // F  -> num
                              {6,3,-9,2,-10,0}         // F  -> ( E )
                             };
    private int [][]_m = {{1,1,0},
                          {2,1,1},
                          {2,8,1},
                          {2,9,1},
                          {3,4,2},
                          {3,5,3},
                          {3,3,4},
                          {3,10,4},
                          {4,1,5},
                          {4,8,5},
                          {4,9,5},
                          {5,6,6},
                          {5,7,7},
                          {5,4,8},
                          {5,5,8},
                          {5,3,8},
                          {5,10,8},
                          {6,1,9},
                          {6,8,10},
                          {6,9,11}
                         };
    private int _noVts;
    private int _noVns;
    private int _noProd;
    private int _noEnt;
    
    public SintDescNRP () {
        _pila = new Pila();
        _noVts = _vts.length;
        _noVns = _vns.length;
        _noProd = 12;
        _noEnt = 20;
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
            if ( EsTerminal(x.Elem()))   // es token
                if ( x.Elem().equals(a)) {
                    _pila.Pop();
                    ae++;
                }
                else
                    return 1;
            else      // variable sintáctica o simbolo no terminal
                
                
        } while (!x.Elem().equals("$"));
    }
    
    public boolean EsTerminal(String x) {
        for (int i = 1; i < _noVts; i++)
            if (_vts[i].equals(x))
                return true;
        return false;           
    }
    
    
}

package AppGrafica;

public class SintDescNRP {
    
    final int NODIS = 5000;
    
    private Pila _pila;
    private String[] _vts = { "", "id", "=", ";", "+", "-", "*", "/", "num", "(", ")", "$" };
    private String[] _vns = { "", "A", "A'", "E", "E'", "T", "T'", "F" };
    private int [][] _prod = {{1,5,-1,-2,3,-3,2},      // A  -> id = E ; A'
                              {2,5,-1,-2,3,-3,2},      // A' -> id = E ; A'
                              {2,0,0,0,0,0,0},         // A' -> empty        
                              {3,2,5,4,0,0,0},         // E  -> T E'
                              {4,3,-4,5,4,0,0},        // E' -> + T E'
                              {4,3,-5,5,4,0,0},        // E' -> - T E'
                              {4,0,0,0,0,0,0},         // E' -> empty
                              {5,2,7,6,0,0,0},         // T  -> F T'
                              {6,3,-6,7,6,0,0},        // T' -> * F T'
                              {6,3,-7,7,6,0,0},        // T' -> / F T'
                              {6,0,0,0,0,0,0},         // T' -> empty
                              {7,1,-1,0,0,0,0},        // F  -> id
                              {7,1,-8,0,0,0,0},        // F  -> num
                              {7,3,-9,3,-10,0,0}       // F  -> ( E )
                             };
    private int [][]_m = {{1,1,0},
                          {2,1,1},
                          {2,11,2},
                          {3,1,3},
                          {3,8,3},
                          {3,9,3},
                          {4,4,4},
                          {4,5,5},
                          {4,3,6},
                          {4,10,6},
                          {5,1,7},
                          {5,8,7},
                          {5,9,7},
                          {6,6,8},
                          {6,7,9},
                          {6,4,10},
                          {6,5,10},
                          {6,3,10},
                          {6,10,10},
                          {7,1,11},
                          {7,8,12},
                          {7,9,13}
                         };
    private int _noVts;
    private int _noVns;
    private int _noProd;
    private int _noEnt;
    private int[] _di;
    private int _noDis;
    
    public SintDescNRP () {
        _pila = new Pila();
        _noVts = _vts.length;
        _noVns = _vns.length;
        _noProd = 14;
        _noEnt = 22;
        _di = new int [NODIS];
        _noDis = 0;
    }
    
    public void Inicia () {
        _pila.Inicia();
        _noDis = 0;
    }
    
    public int Analiza(Lexico oAnaLex) {
        SimbGram x;
        String a;
        int noProd;
        _pila.Inicia();
        _pila.Push(new SimbGram("$"));
        _pila.Push(new SimbGram(_vns[1]));
        oAnaLex.Anade ( "$", "$" );
        int ae = 0;
        do {
            x = _pila.Tope();
            a = oAnaLex.Tokens()[ae];
            if ( EsTerminal(x.Elem()))   // x es token
                if ( x.Elem().equals(a)) {
                    _pila.Pop();
                    ae++;
                }
                else
                    return 1;
            else      // x es variable sintáctica o simbolo no terminal
                if ((noProd = BusqProd(x.Elem(), a)) >= 0) {
                    _pila.Pop();
                    MeterYes (noProd);
                    _di[_noDis++] = noProd;
                } 
                else
                    return 2;  
        } while (!x.Elem().equals("$"));
        return 0;   // EXITO EN EL RECONOCIMIENTO DE LA SENTENCIA
    }
    
    public boolean EsTerminal(String x) {
        for (int i = 1; i < _noVts; i++)
            if (_vts[i].equals(x))
                return true;
        return false;           
    }
    
    public int BusqProd ( String x, String a ) {
        int indiceX = 0;
        for ( int i = 1; i < _noVns; i++ )
            if ( _vns[i].equals(x)) {
                indiceX = i;
                break;
            }
        int indiceA = 0;
        for ( int i = 0; i < _noVts; i++ )
            if ( _vts[i].equals(a)) {
                indiceA = i;
                break;
            }
        for ( int i = 0; i < _noEnt; i++)
            if ( indiceX == _m[i][0] && indiceA == _m[i][1])
                return _m[i][2];
        return -1;
    }
    
    public void MeterYes ( int noProd ) {
        int noYes = _prod[noProd][1];
        for ( int i = 1; i <= noYes; i++ )
            if ( _prod[noProd][noYes + 2 - i] < 0)
                _pila.Push(new SimbGram(_vts[-_prod[noProd][noYes + 2 - i]]));
            else
                _pila.Push(new SimbGram(_vns[_prod[noProd][noYes + 2 - i]]));
                
    }
    
    public String[] Vts() {
        return _vts;
    }
    
    public String[] Vns() {
        return _vns;
    }
     
    public int[][] M() {
        return _m;
    }
    
    public int NoDis () {
        return _noDis;
    }
    
    public int [] Di() {
        return _di;
    }
    
    public int IndiceVn (String vn) {
        for ( int i = 1; i < _noVns; i++)
            if ( _vns[i].equals(vn))
                return i;
        return 0;
    }
    
    public int IndiceVt(String vt) {
        for ( int i = 1; i < _noVts; i++)
            if ( _vts[i].equals(vt))
                return i;
        return 0;
    }
    
    public int NoProd() {
        return _noProd;
    }
}

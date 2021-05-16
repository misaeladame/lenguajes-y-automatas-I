package AppGrafica;

public class SintDescNRP {
    
    private Pila _pila;
    private String[] _vns = {"", "A", "E", "E'", "T", "T'", "F"};
    
    public SintDescNRP () {
        _pila = new Pila();
    }
    
    public int Analiza(Lexico anaLex) {
        _pila.Inicia();
        _pila.Push(new SimbGram("$"));
        _pila.Push(new SimbGram(_vns[1]));
    }
}

package AppGrafica;

public class Pila {
    final int MAX = 5000;
    SimbGram [] _elems;
    int _tope;
    
    public Pila () {
        _elems = new SimbGram [ MAX ];
        for (int i = 0; i < _elems.length; i++)
            _elems[i] = new SimbGram("");
        _tope = 0;
    }
    
    public boolean Empty () {
        return _tope == 0;
    }
    
    public boolean Full () {
        return _tope == _elems.length;
    }
    
    public void Push ( SimbGram oElem ) {
        _elems[_tope++] = oElem;
    }
    
    public int Length() {
        return _tope;
    }
    
    public SimbGram Pop () {
        return _elems[--_tope];
    }
    
    public void Inicia () {
        _tope = 0;
    }
    
    public SimbGram Tope () {
        return _elems[_tope - 1];
    }
}

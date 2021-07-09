/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppGrafica;

/**
 *
 * @author Misael Adame
 */
public class SintAscSLR {

    public static final int NOPROD = 12;
    public final int NODDS = 1000;
    public final int NOACTIONS = 1000;
    public final int NOGOTOS = 1000;

    String[] _vts = {"", "id", "=", ";", "+", "-", "*", "/", "num", "(", ")", "$"};
    String[] _vns = {"", "B", "A", "E", "T", "F"};
    int[][] _prod = {{1, 1, 2, 0, 0, 0, 0}, // B->A 
                    {2, 5, 2, -1, -2, 3, -3}, // A->A id = E ; 
                    {2, 4, -1, -2, 3, -3, 0}, // A->id = E ; 
                    {3, 3, 3, -4, 4, 0, 0}, // E->E + T 
                    {3, 3, 3, -5, 4, 0, 0}, // E->E - T 
                    {3, 1, 4, 0, 0, 0, 0}, // E->T 
                    {4, 3, 4, -6, 5, 0, 0}, // T->T * F 
                    {4, 3, 4, -7, 5, 0, 0}, // T->T / F 
                    {4, 1, 5, 0, 0, 0, 0}, // T->F 
                    {5, 1, -1, 0, 0, 0, 0}, // F->id 
                    {5, 1, -8, 0, 0, 0, 0}, // F->num 
                    {5, 3, -9, 3, -10, 0, 0} // F->( E ) 
                };
    int[][] _sig = {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // Renglon que no se usa
                    {1, 11, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // SIG(B)={ $  }
                    {2, 1, 11, 0, 0, 0, 0, 0, 0, 0, 0}, // SIG(A)={ id $  }
                    {4, 3, 4, 5, 10, 0, 0, 0, 0, 0, 0}, // SIG(E)={ ; + - )  }
                    {6, 6, 7, 3, 4, 5, 10, 0, 0, 0, 0}, // SIG(T)={ * / ; + - )  }
                    {6, 6, 7, 3, 4, 5, 10, 0, 0, 0, 0} // SIG(F)={ * / ; + - )  }
                };

    Pila _pila;
    int[][] _action;
    int _noActions;
    int _noGoTos;
    int[][] _goTo;
    int[] _dd;
    int _noDds;
    Item[] _c;
    int _noItems;

    // Metodos 
    public SintAscSLR() // Constructor------------------------------
    {
        _pila = new Pila();
        _dd = new int[NODDS];
        _noDds = 0;
        _action = new int[1000 * (_vts.length - 1)][4];
        _goTo = new int[1000 * (_vns.length - 1)][3];
        _noActions = 0;
        _noGoTos = 0;
        _noItems = 0;
    }  // Fin del constructor ---------------------------------------------

    public void Inicia() //---------------------------------------------
    {
        _pila.Inicia();
        _noDds = 0;
        _noActions = 0;
        _noGoTos = 0;
        _c = new Item[1000];
        _noItems = 0;
        for (int i = 0; i < _c.length; i++) {
            _c[i] = new Item();
        }

        //crea item 0 y calcula la cerradura del mismo---------------
        int[][] arre = {{-1, 0}};
        _c[_noItems++] = Cerradura(new Item(arre, 1));

        //crea item 1 y lo asigna ----------------------------------
        int[][] arreItem1 = {{-1, 1}};
        _c[_noItems++] = new Item(arreItem1, 1);

        //calcula la coleccion canonica de la gramatica-------------
        for (int i = 0; i < _noItems; i++) {
            if (i != 1) {
                AgregarConjItems(i);
            }
        }

        //crear los goTos del item  S'->.S gramatica aumentada------------
        _goTo[_noGoTos][0] = 0;
        _goTo[_noGoTos][1] = 1;
        _goTo[_noGoTos++][2] = 1;

        //genera cambios y reducciones de la tabla M----------------------
        for (int i = 0; i < 1000; i++) {
            GeneraCambios(i);
            GeneraReducciones(i);
        }

    }  // fin de Inicia() -------------------------------------------------------------------

    public Item Cerradura(Item oItem) // Cerradura de un item-------------------------------------
    {
        boolean cambios = true;
        while (cambios) {
            for (int i = 0; i < oItem.NoItems(); i++) {
                int noItemsAgregado = AgregarItems(i, oItem);
                if (noItemsAgregado > 0) {
                    cambios = true;
                    break;
                } else {
                    cambios = false;
                }
            }
        }
        return oItem;
    }  // Fin de Cerradura() ----------------------------------------------------------------------

    public void AgregarConjItems(int i) //-------------------------------------------------------
    {
        boolean[] marcaItems = new boolean[NOPROD + 1];
        for (int j = 0; j < NOPROD + 1; j++) {
            marcaItems[j] = false;
        }
        marcaItems[0] = i == 0;
        for (int j = 0; j < _c[i].NoItems(); j++) {
            if (!marcaItems[j]) {
                int noProd = _c[i].NoProd(j);
                int posPto = _c[i].PosPto(j);
                if (posPto != _prod[noProd][1]) {
                    Item oNuevoItem = new Item();
                    int indSimGoTo = _prod[noProd][posPto + 2];
                    for (int k = 0; k < _c[i].NoItems(); k++) {
                        if (!marcaItems[k]) {
                            int nP = _c[i].NoProd(k);
                            int pP = _c[i].PosPto(k);
                            try {
                                if (indSimGoTo == _prod[nP][pP + 2]) {
                                    oNuevoItem.Agregar(nP, pP + 1);
                                    marcaItems[k] = true;
                                }
                            } catch (Exception e) {
                                continue;
                            }
                        }
                    }
                    int[] edoYaExiste = {-1};
                    _goTo[_noGoTos][0] = i;
                    _goTo[_noGoTos][1] = indSimGoTo;
                    oNuevoItem = Cerradura(oNuevoItem);
                    if (!EstaNuevoItem(oNuevoItem, edoYaExiste))//verifica si el item no existe
                    {
                        _goTo[_noGoTos++][2] = _noItems;
                        _c[_noItems++] = oNuevoItem;
                    } else {
                        _goTo[_noGoTos++][2] = edoYaExiste[0];//calcular el goTo cuando el item no existe
                    }
                }
            }
        }
    }  // Fin de AgregarConjItems()--------------------------------------------------------------------

    public int AgregarItems(int i, Item oItem) //--------------------------------------------------
    {
        int noItemsAgregado = 0;
        int posPto = oItem.PosPto(i);
        int noProd = oItem.NoProd(i);
        int indVns = noProd == -1 ? 1 : (posPto == _prod[noProd][1] ? 0 : (_prod[noProd][posPto + 2] < 0 ? 0 : _prod[noProd][posPto + 2]));
        if (indVns > 0) {
            for (int j = 0; j < NOPROD; j++) {
                if (indVns == _prod[j][0] && !oItem.ExisteItem(j, 0)) //busca si existe una produccion con 
                {                                                    //ese indice y que no exista el item
                    oItem.Agregar(j, 0);
                    noItemsAgregado++;
                }
            }
        }
        return noItemsAgregado;
    }  // Fin de AgregarItems() -------------------------------------------------------------------------

    public boolean EstaNuevoItem(Item oNuevoItem, int[] edoYaExiste) //-----------------------------------
    {
        edoYaExiste[0] = -1;
        for (int i = 0; i < _noItems; i++) {
            if (_c[i].NoItems() == oNuevoItem.NoItems()) {
                int aciertos = 0;
                for (int j = 0; j < _c[i].NoItems(); j++) {
                    for (int k = 0; k < oNuevoItem.NoItems(); k++) {
                        if (_c[i].NoProd(j) == oNuevoItem.NoProd(k) && _c[i].PosPto(j) == oNuevoItem.PosPto(k)) {
                            aciertos++;
                            break;
                        }
                    }
                }
                if (aciertos == _c[i].NoItems()) //si numero de items son iguales a los aciertos, entonces ya existe
                {
                    edoYaExiste[0] = i;
                    return true;
                }

            }
        }
        return false;
    }  // Fin de EstaNuevoItem()  ------------------------------------------------------------------

    public void GeneraReducciones(int i) // reducciones del Item _c[i] ----------------------------
    {
        for (int j = 0; j < _c[i].NoItems(); j++) {
            int noProd = _c[i].NoProd(j);
            int posPto = _c[i].PosPto(j);
            if (i == 1) //cuando el item es 1 se realiza lo siguiente
            {
                _action[_noActions][0] = i;
                _action[_noActions][1] = _vts.length - 1;
                _action[_noActions][2] = 2;
                _action[_noActions++][3] = -1;
            } else if (noProd != -1 && posPto == _prod[noProd][1]) {
                int indVns = _prod[noProd][0];
                for (int k = 1; k <= _sig[indVns][0]; k++) {
                    _action[_noActions][0] = i;
                    _action[_noActions][1] = _sig[indVns][k];
                    _action[_noActions][2] = 1;
                    _action[_noActions++][3] = noProd;
                }
            }
        }
    }  // Fin de GeneraReducciones()----------------------------------------

    public void GeneraCambios(int i) // cambios del Item _c[i]-------------------------
    {
        for (int j = 0; j < _c[i].NoItems(); j++) {
            int noProd = _c[i].NoProd(j);
            int posPto = _c[i].PosPto(j);
            if (noProd != -1) {
                if (posPto != _prod[noProd][1]) {
                    int indSim = _prod[noProd] [posPto + 2];
                        if (indSim < 0) {
                        int edoTrans = -1;
                        for (int k = 0; k < _noGoTos; k++) {
                            if (_goTo[k][0] == i && _goTo[k][1] == indSim) {
                                edoTrans = _goTo[k][2];
                                break;
                            }
                        }
                        _action[_noActions][0] = i;
                        _action[_noActions][1] = -indSim;
                        _action[_noActions][2] = 0;
                        _action[_noActions++][3] = edoTrans;
                    }
                }
            }
        }
    }  // Fin de GeneraCambios() --------------------------------------------------------------------

    public int Analiza(Lexico oAnalex) {
        int ae = 0;
        oAnalex.Anade("$", "$");
        _pila.Push(new SimbGram("0"));
        while (true) {
            String s = _pila.Tope().Elem();
            String a = oAnalex.Tokens()[ae];
            String accion = Accion(s, a);
            switch (accion.charAt(0)) {
                case 's':
                    _pila.Push(new SimbGram(a));
                    _pila.Push(new SimbGram(accion.substring(1)));  // caso en que la accion es un cambio
                    ae++;
                    break;
                case 'r':
                    SacarDosBeta(accion);//sacar dos veces Beta simbolos de la pila
                    MeterAGoTo(accion);  //meter Vns y goTos a la pila
                    _dd[_noDds++] = Integer.parseInt(accion.substring(1));  // caso en que la accion es una 
                    break;                                               // reduccion
                case 'a':
                    return 0;  // aceptacion
                case 'e':
                    return 1;  // error
                }
        }
    }  // Fin de Analiza() ----------------------------------------------------------------------------------

    public String Accion(String s, String a) // ------------------------------------------------------------  
    {
        //metodo que determina que accion se realizara 
        int tipo = -1, no = -1;
        int edo = Integer.parseInt(s);
        int inda = 0;
        boolean enc = false;
        for (int i = 1; i < _vts.length; i++) {
            if (_vts[i].equals(a)) {
                inda = i;
                break;
            }
        }
        for (int i = 0; i < _noActions; i++) {
            if (_action[i][0] == edo && _action[i][1] == inda) {
                tipo = _action[i][2];
                no = _action[i][3];
                enc = true;
            }
        }
        if (!enc) {
            return "error";
        } else {
            switch (tipo) {
                case 0:
                    return "s" + Integer.toString(no);
                case 1:
                    return "r" + Integer.toString(no);
                case 2:
                    return "acc";
                default:
                    return "error";
            }
        }

    }  // Fin de Accion() ------------------------------------------------------------------

    public void SacarDosBeta(String accion) //--------------------------------------------
    {
        int noProd = Integer.parseInt(accion.substring(1));
        int noVeces = _prod[noProd][1] * 2;
        for (int i = 1; i <= noVeces; i++) {
            _pila.Pop();
        }
    }  // Fin de SacarDosBeta() ------------------------------------------------------------

    public void MeterAGoTo(String accion) //-----------------------------------------------
    {
        int sPrima = Integer.parseInt(_pila.Tope().Elem());
        int noProd = Integer.parseInt(accion.substring(1));
        _pila.Push(new SimbGram(_vns[_prod[noProd][0]]));
        for (int i = 0; i < _noGoTos; i++) {
            if (sPrima == _goTo[i][0] && _prod[noProd][0] == _goTo[i][1]) {
                _pila.Push(new SimbGram(Integer.toString(_goTo[i][2])));
                break;
            }
        }
    }  // Fin de MeterAGoTo() --------------------------------------------------------------

} // fin de la clase SintAscSLR

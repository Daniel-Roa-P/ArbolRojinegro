
package arbolrojinegro;

public class Arbol {
    
    private Nodo raiz;
    private final Nodo nodoSentinel;
    String cadena="";
    String cadena2="";
    String cadena3="";
    private int nivel = 0;
    private int max = 0;

    public Arbol() {
        
        nodoSentinel = new Nodo();
        nodoSentinel.color = 0;
        nodoSentinel.izquierda = null;
        nodoSentinel.derecha = null;
        raiz = nodoSentinel;
    
    }
    
    public void insertar(int valor) {

        Nodo nodo = new Nodo();
        nodo.padre = null;
        nodo.llave = valor;
        nodo.izquierda = nodoSentinel;
        nodo.derecha = nodoSentinel;
        nodo.color = 1;

        Nodo y = null;
        Nodo x = this.raiz;

        while (x != nodoSentinel) {
            
            y = x;
            
            if (nodo.llave < x.llave) {
            
                x = x.izquierda;
            
            } else {
            
                x = x.derecha;
            
            }
        }

        nodo.padre = y;
        
        if (y == null) {
            
            raiz = nodo;
        } else if (nodo.llave < y.llave) {
         
            y.izquierda = nodo;
        
        } else {
        
            y.derecha = nodo;
        
        }

        if (nodo.padre == null){
        
            nodo.color = 0;
            
            return;
        
        }

        if (nodo.padre.padre == null) {
        
            return;
        
        }

        arreglarInsecion(nodo);
        
    }
    
    private Nodo buscar(Nodo node, int key) {
        
        if (node == nodoSentinel || key == node.llave) {
        
            return node;

        }

        
        if (key < node.llave) {
    
            return buscar(node.izquierda, key);
        
        } 
        
        return buscar(node.derecha, key);
    
    }

    private void arreglarRetiro(Nodo x) {
            
        Nodo s;
        
        while (x != raiz && x.color == 0) {
        
            if (x == x.padre.izquierda) {

                s = x.padre.derecha;
                
                if (s.color == 1) {
                    
                                   
                    s.color = 0;                    
                    x.padre.color = 1;                    
                    rotarDerecha(x.padre);                    
                    s = x.padre.derecha;
                    
                }

                
                if (s.izquierda.color == 0 && s.derecha.color == 0) {
                                                
                    s.color = 1;
                    x = x.padre;
                                                   
                } else {
                
                    if (s.derecha.color == 0) {

                        s.izquierda.color = 0;                       
                        s.color = 1;
                        
                        rotarIzquierda(s);
                        
                        s = x.padre.derecha;
                        
                    } 
                    
                    s.color = x.padre.color;                    
                    x.padre.color = 0;                    
                    s.derecha.color = 0;  
                    
                    rotarDerecha(x.padre);
                    
                    x = raiz;
                    
                }
                
            } else {
            
                s = x.padre.izquierda;

                if (s.color == 1) {

                    s.color = 0;                    
                    x.padre.color = 1;
                    
                    rotarIzquierda(x.padre);
                    
                    s = x.padre.izquierda;
                    
                }

                
                if (s.derecha.color == 0 && s.derecha.color == 0) {                     
                
                    s.color = 1;
                    x = x.padre;
                    
                } else {
                
                    if (s.izquierda.color == 0) {

                        s.derecha.color = 0;                        
                        s.color = 1;
                        
                        rotarDerecha(s);
                        
                        s = x.padre.izquierda;
                        
                    } 
                    
                    s.color = x.padre.color;                    
                    x.padre.color = 0;                    
                    s.izquierda.color = 0;
                    
                    rotarIzquierda(x.padre);
                    
                    x = raiz;
                    
                }
                
            } 
        
        }
            
        x.color = 0;
            
    }

    private void intercambiar(Nodo u, Nodo v){
        
        if (u.padre == null) {
        
            raiz = v;

        } else if (u == u.padre.izquierda){
        
            u.padre.izquierda = v;

        } else {
        
            u.padre.derecha = v;

        }
        
        v.padre = u.padre;
            
    }

    private void borrarNodo(Nodo node, int key) {
        
        Nodo z = nodoSentinel;
        Nodo x;        
        Nodo y;   
           
        while (node != nodoSentinel){
               
            if (node.llave == key) {
            
                z = node;

            }

            
            if (node.llave <= key) {
            
                node = node.derecha;

            } else {
            
                node = node.izquierda;

            }
            
        }

        
        if (z == nodoSentinel) {
        
            System.out.println("Este nodo no tiene valor");

            return;
            
        } 

        y = z;        
        int yOriginalColor = y.color;
            
        if (z.izquierda == nodoSentinel) {
        
            x = z.derecha;

            intercambiar(z, z.derecha);
            
        } else if (z.derecha == nodoSentinel) {
        
            x = z.izquierda;

            intercambiar(z, z.izquierda);
            
        } else {
        
            y = minimo(z.derecha);
            yOriginalColor = y.color;        
            x = y.derecha;
            
            
            if (y.padre == z) {
            
                x.padre = y;

            } else {
            
                intercambiar(y, y.derecha);
                y.derecha = z.derecha;               
                y.derecha.padre = y;
                    
            }
            
            intercambiar(z, y);
             
            y.izquierda = z.izquierda;            
            y.izquierda.padre = y;            
            y.color = z.color;
            
        }
            
            
        if (yOriginalColor == 0){
        
            arreglarRetiro(x);

        }
            
    }

    private void arreglarInsecion(Nodo k){
        
        Nodo tio;
            
        
        while (k.padre.color == 1) {
                
        
            if (k.padre == k.padre.padre.derecha) {
                        

                tio = k.padre.padre.izquierda; 

                
                if (tio.color == 1) {

                
                    tio.color = 0;
                    
                    k.padre.color = 0;
                    
                    k.padre.padre.color = 1;
                    
                    k = k.padre.padre;

                    
                } else {

                
                    if (k == k.padre.izquierda) {

                    
                        k = k.padre;
                        
                        rotarIzquierda(k);
                        
                    }

                    
                    k.padre.color = 0;
                    
                    k.padre.padre.color = 1;
                    
                    rotarDerecha(k.padre.padre);

                    
                }
                            
                
            } else {
                        
            
                tio = k.padre.padre.derecha; 

                
                if (tio.color == 1) {

                
                    tio.color = 0;
                            
                    k.padre.color = 0;
                    
                    
                    k.padre.padre.color = 1;
                    
                    k = k.padre.padre;
                                
                    
                } else {
                            
                
                    if (k == k.padre.derecha) {

                    
                        k = k.padre;
                        
                        rotarDerecha(k);
                        
                    }

                    
                    k.padre.color = 0;
                    
                    k.padre.padre.color = 1;
                    
                    rotarIzquierda(k.padre.padre);
                                
                    
                }
                
            }
                    
            
            if (k == raiz) {
            
                break;

            }
            
        }
            
        
        raiz.color = 0;
            
    }

    public String preorden( Nodo nodo ) {

        if (nodo == null || nodo.llave == 0){ 
            return ""; 
        }

        cadena3 = cadena3 + nodo.llave +", ";

        preorden(nodo.izquierda); 

        preorden(nodo.derecha); 

        return cadena3;

    }

    public String inorden(Nodo nodo) {

        if (nodo == null || nodo.llave == 0){ 
            return ""; 
        }

        nivel++;
        inorden(nodo.izquierda); 
        nivel--;

        cadena = cadena + nodo.llave+", ";

        if(nivel > max){

            max = nivel;

        }

        nivel++;
        inorden(nodo.derecha); 
        nivel--;

        return cadena;

    }

    public String postorden(Nodo nodo) {

        if (nodo == null || nodo.llave == 0){ 
        
            return ""; 
        
        }

        postorden(nodo.izquierda); 

        postorden(nodo.derecha); 

        cadena2 = cadena2 + nodo.llave +", ";

        return cadena2;
    
    }

    public Nodo encontrar(int k) {
    
        return buscar(this.raiz, k);

    }

    public Nodo minimo(Nodo node) {
    
        while (node.izquierda != nodoSentinel) {

            node = node.izquierda;
            
        }
        
        return node;
    
    }

    public Nodo maximo(Nodo node) {
    
        while (node.derecha != nodoSentinel) {

            node = node.derecha;
            
        }
        
        return node;
    
    }

    public Nodo sucesor(Nodo x) {
    
        if (x.derecha != nodoSentinel) {

            return minimo(x.derecha);
            
        }

        Nodo y = x.padre;

        while (y != nodoSentinel && x == y.derecha) {
        
            x = y;

            y = y.padre;
            
        }
        
        return y;
    }

    public Nodo predecesor(Nodo x) {
       
        if (x.izquierda != nodoSentinel) {
                
            return maximo(x.izquierda);
                   
        }
        
        Nodo y = x.padre;
        
        while (y != nodoSentinel && x == y.izquierda) {
        
            x = y;

            y = y.padre;
            
        }
        
        return y;
    
    }

    public void rotarDerecha(Nodo x) {
        
        Nodo y = x.derecha;
        x.derecha = y.izquierda;
        
        if (y.izquierda != nodoSentinel) {
    
            y.izquierda.padre = x;
        
        }
        
        y.padre = x.padre;
        
        if (x.padre == null) {
        
            this.raiz = y;
        
        } else if (x == x.padre.izquierda) {
        
            x.padre.izquierda = y;
        
        } else {
        
            x.padre.derecha = y;
        
        }
        
        y.izquierda = x;
        x.padre = y;
        
    }

    public void rotarIzquierda(Nodo x) {
            
        Nodo y = x.izquierda;
        x.izquierda = y.derecha;
        
        if (y.derecha != nodoSentinel) {
        
            y.derecha.padre = x;
        
        }
        
        y.padre = x.padre;
        
        if (x.padre == null) {
        
            this.raiz = y;
        
        } else if (x == x.padre.derecha) {
        
            x.padre.derecha = y;
        
        } else {
        
            x.padre.izquierda = y;
        
        }
        
        y.derecha = x;
        x.padre = y;
        
    }

    public Nodo getRoot(){
    
        return this.raiz;
        
    }

    public void recibirNodoBorrar(int data) {
        
        borrarNodo(this.raiz, data);
            
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    
    public String getCadena() {
        return cadena;
    }

    public void setCadena(String cadena) {
        this.cadena = cadena;
    }

    public String getCadena2() {
        return cadena2;
    }

    public void setCadena2(String cadena2) {
        this.cadena2 = cadena2;
    }

    public String getCadena3() {
        return cadena3;
    }

    public void setCadena3(String cadena3) {
        this.cadena3 = cadena3;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
    
}

package arbolrojinegro;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class ArbolRojiNegro extends JFrame implements ActionListener{

    public JButton botonCrear  = new JButton("Crear arbol");
    public JButton botonInsertar = new JButton("Insertar en el arbol");
    public JButton botonRetirar  = new JButton("Retirar del arbol");
    private List <Nodo> listaArbol = new ArrayList();
    
    public JLabel label1 = new JLabel("Ingresar numero(s) y cadenas que desea añadir");
    public JLabel label2 = new JLabel("Recorrido Preorden: ");
    public JLabel label3 = new JLabel("Recorrido Inorden: ");
    public JLabel label4 = new JLabel("Recorrido Posorden: ");
    public JLabel label5 = new JLabel("");
    public JLabel label6 = new JLabel("Daniel Alejandro Roa Palacios");
    public JLabel label7 = new JLabel("Universidad Distrital Francisco Jose de Caldas");
    public JLabel label8 = new JLabel("20171020077");
    
    public JTextField tfIngreso = new JTextField("14,26,35,74,10,2,78,3,97");
    public JTextField tfCadenas = new JTextField("Juan,Manuel,Daniel,Carlos,Maria,Laura,Natalia,Oscar,Julian");
    public JTextField tfRetiro = new JTextField("");
    public JTextField preOrden = new JTextField();
    public JTextField inOrden = new JTextField();
    public JTextField posOrden = new JTextField();
    
    private Arbol arbol = null; 
    private JScrollPane scrollPane = new JScrollPane();
    private JScrollPane scrollPane1 = new JScrollPane();
    private String entrada, entrada2;
    
    public static void main(String[] args) {

        ArbolRojiNegro arbolesRN = new ArbolRojiNegro();
        arbolesRN.setSize(1300, 650);
        arbolesRN.setTitle("Arboles AVL");
        arbolesRN.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        arbolesRN.setVisible(true);
        
    }

    ArbolRojiNegro(){
        
        Container c = getContentPane();
        c.setLayout(null);
        this.getContentPane().setBackground(Color.LIGHT_GRAY);
        
        c.add(label1);
        c.add(label2);
        c.add(label3);
        c.add(label4);
        c.add(label5);
        c.add(label6);
        c.add(label7);
        c.add(label8);
        
        c.add(tfIngreso);
        c.add(tfRetiro);
        c.add(tfCadenas);
        c.add(preOrden);
        c.add(inOrden);
        c.add(posOrden);
        
        c.add(botonInsertar);
        c.add(botonRetirar);
        c.add(botonCrear);
        
        c.add(scrollPane1);  
        
        botonInsertar.addActionListener(this);
        botonRetirar.addActionListener(this);
        botonCrear.addActionListener(this);
        
        label1.setBounds(900, 5, 300, 20);
        label2.setBounds(350, 25, 200, 20);
        label3.setBounds(350, 50, 200, 20);
        label4.setBounds(350, 75, 200, 20);
        label5.setBounds(900,100,370,20);
        label6.setBounds(25, 25, 200, 20);
        label7.setBounds(25, 50, 500, 20);
        label8.setBounds(25, 75, 370,20);
        
        tfIngreso.setBounds(900, 25, 210, 20);
        tfCadenas.setBounds(900, 50, 210, 20);
        tfRetiro.setBounds(900, 75, 210, 20);
        preOrden.setBounds(500, 25, 350, 20);
        inOrden.setBounds(500, 50, 350, 20);
        posOrden.setBounds(500, 75, 350, 20);
        
        botonInsertar.setBounds(1125, 50, 145, 20);
        botonInsertar.setBackground(Color.green);
        botonRetirar.setBounds(1125, 75, 145, 20);
        botonRetirar.setBackground(Color.red);
        botonCrear.setBounds(1125, 25, 145, 20);
        botonCrear.setBackground(Color.blue);
        
        scrollPane.setBounds(0, 200, 2500, 2500);
        scrollPane.setPreferredSize(new Dimension(2500, 2500));  
        scrollPane.setBackground(Color.LIGHT_GRAY);
        
        scrollPane1.setBounds(2, 125, 1280, 450);
        scrollPane1.setPreferredSize(new Dimension(1280, 450));
        scrollPane1.setBackground(Color.BLUE);
        
    }
    
    private boolean todoNull(List list) {

        for (Object object : list) {
            
            if (object != null){
                
                return false;
            
            }
        }

        return true;
    
    }
    
    public void listarArbol(List<Nodo> nodos, int nivel, int profundidad){
        
        if (nodos.isEmpty() || todoNull(nodos)){
        
            return;

        }
       
        int ubicacion = profundidad - nivel;
        List<Nodo> newNodes = new ArrayList<>();
        
        for (Nodo node : nodos) {
            if (node != null) {
                
                newNodes.add(node.izquierda);
                newNodes.add(node.derecha);
                listaArbol.add(node.izquierda);
                listaArbol.add(node.derecha);
                
                
            } else {
                
                newNodes.add(null);
                newNodes.add(null);
                listaArbol.add(null);
                listaArbol.add(null);
            
            }
        }
            
            listarArbol(newNodes, nivel + 1, profundidad);
            
    
    }
    
    void pintarArbol(){
        
        int exponente = 0;
        int i=0;
        int j=0;
        int coorX = 630;
        int coorY = 30;
        
        JLabel numeros[]=new JLabel[listaArbol.size()];
        
        while(i<listaArbol.size()){
        
            if(j >= Math.pow(2, exponente)){           
                
                exponente=exponente+1;
                j=0;
                coorY = coorY + 50;
                coorX = (int) ((1260/(Math.pow(2, (exponente + 1)))));
                
            }
            
            if(j != 0){
                coorX = (int) ( coorX + (1260/(Math.pow(2, (exponente) ))) );
            }
        
            
            
            if(listaArbol.get(i) != null){
                
                numeros[i]= new JLabel(Integer.toString(listaArbol.get(i).llave) + ", " + listaArbol.get(i).info);
                numeros[i].setBounds(coorX, coorY, 300, 30);
            
                JLabel img1 = new JLabel();
                
                int escala = (int) (280/((Math.pow(2, (exponente)))));
                
                if(listaArbol.get(i).derecha != null && listaArbol.get(i).derecha.llave != 0){
                
                    
                    ImageIcon imgIcon = new ImageIcon(getClass().getResource("flecha.png"));
                    
                    Image imgEscalada = imgIcon.getImage().getScaledInstance(escala,30, Image.SCALE_SMOOTH);
                    Icon iconoEscalado = new ImageIcon(imgEscalada);
                    img1.setBounds(coorX+20 , coorY + 30, escala, 30);
                    img1.setIcon(iconoEscalado);
                    
                    scrollPane.add(img1);
            
                    
                } 
                
                img1 = new JLabel();
                
                if(listaArbol.get(i).izquierda != null && listaArbol.get(i).izquierda.llave != 0){
                
                    ImageIcon imgIcon = new ImageIcon(getClass().getResource("fder.png"));
            
                    Image imgEscalada = imgIcon.getImage().getScaledInstance(escala,30, Image.SCALE_SMOOTH);
                    Icon iconoEscalado = new ImageIcon(imgEscalada);
                    img1.setBounds(coorX-escala , coorY + 30, escala, 30);
                    img1.setIcon(iconoEscalado);
                    
                    scrollPane.add(img1);
                    
                }

                if(listaArbol.get(i).color == 0){

                    numeros[i].setForeground(Color.black);

                } else {

                    numeros[i].setForeground(Color.red);

                } 

                if(listaArbol.get(i).info != null){
                
                    scrollPane.add(numeros[i]);
                    
                }
                
                
            
            
            } else {

                numeros[i]= new JLabel("");
                scrollPane.add(numeros[i]);

            }
            
            i++;
            j++;
        }
            
        scrollPane.repaint();
        scrollPane1.setViewportView(scrollPane);
        
    }
    
    void dibujar(){
        
        scrollPane.removeAll();
        
        arbol.setCadena("");
        inOrden.removeAll();
        inOrden.setText(arbol.inorden(arbol.getRoot()));
        
        arbol.setCadena2("");
        posOrden.removeAll();
        posOrden.setText(arbol.postorden(arbol.getRoot()));
        
        arbol.setCadena3("");
        preOrden.removeAll();
        preOrden.setText(arbol.preorden(arbol.getRoot()));
        
        List <Nodo> lista = new ArrayList();
        lista.add(arbol.getRoot());
        listaArbol = new ArrayList();
        listaArbol.add(arbol.getRoot());
        listarArbol(lista, 0, arbol.getNivel());

        pintarArbol();
    
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
            
        if(e.getSource() == botonCrear){
        
            arbol =  new Arbol(); 

            label5.setText("Arbol creado");
            
            
        } else if(e.getSource() == botonInsertar){

            entrada = tfIngreso.getText().replace(" ", "");
            entrada2 = tfCadenas.getText().replace(" ", "");
            
            String[] listaNumeros = entrada.split(",");
            String[] listaCadenas = entrada2.split(",");
            
            if(!entrada.equals(" ") && (listaNumeros.length == listaCadenas.length)){
                
                for(int i=0; i<listaNumeros.length; i++){
                        
                        arbol.insertar(Integer.parseInt(listaNumeros[i]), listaCadenas[i]);

                }
                
                arbol.inorden(arbol.getRoot());
                
                label5.setText("Datos insertados");
                
                dibujar();
            
            } else {
                
                label5.setText("Inserte bien los datos");
                
            }
            
        } else if(e.getSource() == botonRetirar){
            
            entrada = tfRetiro.getText() + " ";
            
            if(!entrada.equals(" ")){
            
                String temp="";

                for(int i=0;i<entrada.length();i++){

                    if(entrada.substring(i,i+1).equals(",")|| entrada.substring(i,i+1).equals(" ")){

                        arbol.recibirNodoBorrar(Integer.parseInt(temp));
                        temp = "";

                    } else {

                        temp = temp + entrada.substring(i,i+1);

                    }

                }

                dibujar();
                
            }
        }
                  
    }
    
}

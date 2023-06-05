/*
    LEONARDO JAVIER CARRILLO MARTINEZ
    ARTURO TREJO JUAREZ

*/
package kmeans3d;

import java.awt.Color;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Ventana extends javax.swing.JFrame{
    
    //datos que ingresa el usuario
    private String opcion;
    private int k,items; //x y y son las coordenadas del mouse 
    private double x,y,z; //coordenadas del nuevo punto
    
    //funcionalidad
    private KMeans3D algoritmo; // manda a llamar la clase KMeans
    private ArrayList<Item> nuevos; //los que el usuario agrega
    private Random r;
    
    
    
    public Ventana() {
        initComponents();
        initComponents2();
    }
    public void limpiaPanel(){
        plot3DPanel1.plotCanvas.plots.clear();
        algoritmo.puntosDivididos.clear();
    
    }
    public void limpia(){
        limpiaPanel();
        plot3DPanel1.plotCanvas.updateUI();
        algoritmo.itemsArray.clear();
        nuevos.clear();
        algoritmo.cuentaPuntos=0;
        btnAgrega.setEnabled(true);
        comboOpciones.setEnabled(true);
    }

    public void initComponents2(){
        setLocationRelativeTo(null);
        /*
            1)inicializo als variables que no se modificaron en el initcompoonents()
            2) genero el algoritmo de KMeans3D
            3) me pongo a la escucha del plano con el mouse 
        
        */
        nuevos = new ArrayList();
        r = new Random();
        
        this.inputItems.setText(""+20);
        this.inputX.setText(""+r.nextInt(1000));
        this.inputY.setText(""+r.nextInt(1000));
        this.inputZ.setText(""+r.nextInt(1000));
        
        
        
        this.k = Integer.parseInt(inputK.getText());
        this.items = Integer.parseInt(inputItems.getText());
        this.x = Double.parseDouble(inputX.getText());
        this.y = Double.parseDouble(inputY.getText());
        this.z = Double.parseDouble(inputZ.getText());
        this.opcion = comboOpciones.getSelectedItem().toString();
        
        
        generaAlgoritmo();
        algoritmo.generaPuntos();
        paintPuntos();
        
    }
    
    
    public void generaAlgoritmo(){
        //KMeans3D algoritmo = new KMeans3D(k,items,this.x,this.y,this.z,plano.x,plano.y,plano.z,opcion);
        algoritmo = new KMeans3D(k,items,this.x,this.y,this.z,opcion);

    }
    public void pintaNuevoPunto(){
        
        double auxX []= {x};
        double auxY []= {y};
        double auxZ []= {z};

        
        plot3DPanel1.addScatterPlot("nuevos ",Color.RED, auxX,auxY,auxZ);
        
    }
    
    public void paintPuntos(){
        System.out.println("en paint Puntos");
        plot3DPanel1.addScatterPlot("mis puntos ",Color.BLACK, algoritmo.getVector(algoritmo.itemsArray,0),algoritmo.getVector(algoritmo.itemsArray,1), algoritmo.getVector(algoritmo.itemsArray,2));

    }
    public void repaintPuntos(){
        
        System.out.println("cambiando colores");
        
        
        for (int i = 0; i < algoritmo.cuentaPuntos; i++) {
            
            plot3DPanel1.addScatterPlot("nuevos ",algoritmo.puntosDivididos.get(i).get(0).color,
                    algoritmo.getVector(algoritmo.puntosDivididos.get(i),0),
                    algoritmo.getVector(algoritmo.puntosDivididos.get(i),1),
                    algoritmo.getVector(algoritmo.puntosDivididos.get(i),2));
            
        }
        

    }
    public void reubicaClusters(){
        System.out.println("en funcion reubica Clusters ");
        

        plot3DPanel1.addScatterPlot("nuevos ",Color.RED,
                algoritmo.getVector(algoritmo.nuevosPuntos,0),
                algoritmo.getVector(algoritmo.nuevosPuntos,1),
                algoritmo.getVector(algoritmo.nuevosPuntos,2));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        plot3DPanel1 = new org.math.plot.Plot3DPanel();
        inputItems = new javax.swing.JTextField();
        jLabelItems = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        inputK = new javax.swing.JTextField();
        btnGo = new javax.swing.JButton();
        btnRemake = new javax.swing.JButton();
        comboOpciones = new javax.swing.JComboBox<>();
        inputX = new javax.swing.JTextField();
        inputY = new javax.swing.JTextField();
        inputZ = new javax.swing.JTextField();
        btnAgrega = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        inputExponente = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Algoritmo KMeans en 3D");

        inputItems.setText("100");

        jLabelItems.setText("items");

        jLabel1.setText("k");

        inputK.setText("5");

        btnGo.setText("go");
        btnGo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGoActionPerformed(evt);
            }
        });

        btnRemake.setText("remake");
        btnRemake.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemakeActionPerformed(evt);
            }
        });

        comboOpciones.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "euclideana", "manhatan", "fraccionaria", "cuadratica","inventada" }));

        inputX.setText("0");

        inputY.setText("0");

        inputZ.setText("0");

        btnAgrega.setText("agrega");
        btnAgrega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregaActionPerformed(evt);
            }
        });

        jLabel2.setText("Agrega una coordenada");

        jLabel3.setText("x");

        jLabel4.setText("y");

        jLabel5.setText("z");

        jLabel6.setText("Selecciona un calculo de distancias");

        jLabel9.setText("Plano 3D");

        inputExponente.setText("2");

        jLabel7.setText("exponente");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(242, 242, 242)
                        .addComponent(jLabel9))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(plot3DPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE)))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(inputZ, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(7, 7, 7))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabelItems)
                                    .addGap(55, 55, 55))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(inputItems, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(inputX, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(inputY, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGap(25, 25, 25)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(69, 69, 69))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(inputK, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(34, 34, 34)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(btnAgrega)
                                    .addGap(22, 22, 22))))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(17, 17, 17)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btnRemake)
                                    .addGap(29, 29, 29)
                                    .addComponent(btnGo))
                                .addComponent(jLabel6))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(43, 43, 43))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(comboOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(inputExponente, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(78, 78, 78))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelItems, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel9)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(inputItems, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inputK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(inputX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(inputY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(btnAgrega))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(inputZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(25, 25, 25)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(inputExponente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnRemake)
                            .addComponent(btnGo)))
                    .addComponent(plot3DPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE))
                .addGap(47, 47, 47))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRemakeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemakeActionPerformed
        // TODO add your handling code here:
        limpia();
        
        this.items = Integer.parseInt(inputItems.getText());       
        this.k = Integer.parseInt(inputK.getText());
        
        
        
        super.update(this.getGraphics());

        
        generaAlgoritmo();
        algoritmo.generaPuntos();
        paintPuntos();
        
    }//GEN-LAST:event_btnRemakeActionPerformed

    private void btnAgregaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregaActionPerformed
        // TODO add your handling code here:
        this.x = Double.parseDouble(inputX.getText());
        this.y = Double.parseDouble(inputY.getText());
        this.z = Double.parseDouble(inputZ.getText());
        
        if(this.x==0 || this.y==0 || this.z==0)
            JOptionPane.showMessageDialog(this, "Que pongas  una cantidad mas grande que  0");
        
        Item auxiliar = new Item("",this.x,this.y,this.z,new Color(r.nextInt(180),r.nextInt(180),r.nextInt(180)));
        
        if(nuevos.size() < this.k){ //solo si los puntos qye llevo son menores a la cantidad de K  puedo generar mas 
               this.nuevos.add(auxiliar);
               algoritmo.coordenadasDeCentroides[nuevos.size()-1][0] = this.x;
               algoritmo.coordenadasDeCentroides[nuevos.size()-1][1] = this.y;
               algoritmo.coordenadasDeCentroides[nuevos.size()-1][2] = this.z;
               algoritmo.cuentaPuntos++;
           }
        pintaNuevoPunto();
        
        this.inputX.setText(""+r.nextInt(1000));
        this.inputY.setText(""+r.nextInt(1000));
        this.inputZ.setText(""+r.nextInt(1000));
        
        if(algoritmo.cuentaPuntos>=this.k){
            btnAgrega.setEnabled(false);
        }
    }//GEN-LAST:event_btnAgregaActionPerformed
    
    
    private void btnGoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoActionPerformed
        // TODO add your handling code here:
        
        if(algoritmo.cuentaPuntos<1){
            JOptionPane.showMessageDialog(this,"Olvidaste agregar un punto");
//      }else if( Double.parseDouble(inputExponente.getText())%2!=0){
//            JOptionPane.showMessageDialog(this,"Si el exponente es impar\n  los puntos se borran");
        }else{
            comboOpciones.setEnabled(false);
            btnAgrega.setEnabled(false);
            algoritmo.nuevosPuntos = this.nuevos;
            algoritmo.opcion = comboOpciones.getSelectedItem().toString();
            if(comboOpciones.getSelectedItem().toString().equals("inventada"))
                algoritmo.miExponente=Double.parseDouble(this.inputExponente.getText());
            do{
                algoritmo.puntosDivididos.clear();


                //sospechoso
                //algoritmo.nuevosPuntos = this.nuevos;


                System.out.println("llamando a recalculaPuntos");
                algoritmo.recalculaPuntos();
                limpiaPanel();
                reubicaClusters();///////////////////////

                algoritmo.divideItems();
                repaintPuntos();
                System.out.println("ya deberia verse lo que se pintó");
                super.update(this.getGraphics());
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }while(algoritmo.hayCentrados()<algoritmo.cuentaPuntos);
            JOptionPane.showMessageDialog(this,"Has clasificado todo");
        }
    }//GEN-LAST:event_btnGoActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgrega;
    private javax.swing.JButton btnGo;
    private javax.swing.JButton btnRemake;
    private javax.swing.JComboBox<String> comboOpciones;
    private javax.swing.JTextField inputExponente;
    private javax.swing.JTextField inputItems;
    private javax.swing.JTextField inputK;
    private javax.swing.JTextField inputX;
    private javax.swing.JTextField inputY;
    private javax.swing.JTextField inputZ;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelItems;
    private org.math.plot.Plot3DPanel plot3DPanel1;
    // End of variables declaration//GEN-END:variables
    
}

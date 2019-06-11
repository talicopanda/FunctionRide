/*
 * Sukhraj, Tales, Sergio
 * June 7 2019
 * the user manual 
 */
package functionride;

import java.awt.Color;

/**
 *
 * @author nm
 */
public class UserManual extends javax.swing.JFrame {

    /**
     * Creates new form UserManual
     */
    public UserManual() {
        initComponents();
                this.getContentPane().setBackground(new Color(198,168,103));

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        exit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Engravers MT", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("User Manual");

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("Introduction:\nWelcome to Function Ride, the game that will help you on your quest of understanding graphics. We hope you have an amazing time crunching down numbers.\n\n\nTable of contents:\n\nI. Set-Up\t\nII. Main-Menu\t\nA. Play\t\nB. Help\t\nC. Quit\t\nD. Leaderboard\t\nIII. Level Screen\nIV. Objective of the Game\t\nV. Function Writing\t\nVI. Completed Screen\t\n                                                                                                                                                                                                                              \nI. Set-Up \nThe program can be started by running the “FunctionRide.jar” provided with the user manual. Upon starting the program the main menu will appear.\nII. Main-Menu\nOnce the program starts up, the game will ask you for your name, or the name you want to be recognized with. The name you input will be registered for the leaderboard.\nAfterward, there are four options that you can choose from. Play, Help, Quit and Leaderboard.\n\nA. Play\nThis option takes you to the level-choosing screen.\n\nB. Help\nThis option opens a JFrame with the user manual.\n\nC. Quit\nThis option closes the program.\n\nD. Leaderboard\nThis option opens a panel which shows the players that have played the game in descending order, from the most levels completed to the least. This is displayed under the “Rankings” text.\nThe panel also allows you to search for the desired player and shows its name and the levels completed by the player.\n\nIII. Level Screen\nIn the level screen, the square boxes with numbers represent the levels. Once you press a box, it will take you to one of the levels of the game. The X button on the top right, once pressed, will take you to the main menu.\n\nIV. Objective of the Game\nOnce you enter the game you will be presented with this screen. The objective is to create a function that will go through the starting and end points without touching the obstacles(blue rectangles) in between. The starting point is labeled in green and has a sprite which represents you (the player), and the end point is labeled in red. \nTo write a function, press the “Run function button”, refer to Function Writing (V), for more instructions of writing a function.\n\nV. Function Writing\nTo write a function, you must use “x” as the variable. Different symbols mean different mathematical operations.\n ^=exponents (ex. x^2)\nsqrt= square root (ex. sqrt(x))\n/= division (ex. x/2)\n*=multiplication (ex. 2x or 2*x)\n+=addition (ex. x+4)\n()=parentheses (grouping)(ex. (x^2)+4, (x+5)/(x+6))\nAfterward, press the ”test” button to display the function.\nThe Menu and the Level-Select buttons will take you to their respective screens. \n\nVI. Completed Screen\nOnce you complete a level by passing the starting point and end point, as shown in this screen, the completed screen will appear. \n\nThe completed screen will show three buttons for you to choose from. \nThe “Menu” button will take you to the main menu, the “Levels” screen will take you to the level select. \nThe button “next”, will take you the next level in the sequence.\n\nPS: To exit the game, you must go to Main Menu and press \"quit\". Otherwise your data will not be saved");
        jScrollPane1.setViewportView(jTextArea1);

        exit.setText("Exit");
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 663, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(231, 231, 231)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(290, 290, 290)
                        .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 631, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
    this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_exitActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(UserManual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserManual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserManual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserManual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserManual().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton exit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}

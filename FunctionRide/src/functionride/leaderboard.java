/*
 * Sukhraj, Tales, Sergio
 * June 9 2019
 * the class that displays the leaderboard
 */

package functionride;

import java.awt.Color;
import java.net.URL; 
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class leaderboard extends javax.swing.JFrame {
    
    ArrayList<CompletedLevels> highScores = FunctionRide.highScores;

    int[] scores = new int[highScores.size()];
    String[] names = new String[highScores.size()];
    //sort the leaderboard datafile in terms of levels completed
    public static void dquiksort(int[] a, int left, int right) {
        if (left >= right) {
            return;
        }
        int i = left;
        int j = right;
        int pivot = a[(left + right) / 2];
        while (i < j) {

            while (a[i] > pivot) {
                i++;
            }
            while (a[j] < pivot) {
                j--;
            }
            if (i <= j) {
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
                i++;
                j--;
            }

        }
        dquiksort(a, left, j);
        dquiksort(a, i, right);

    }
    //search for a specific name in the leaderboards
    public static int binarySearch(String[] b, String x) {
        int l = 0,
                r = b.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;

            int res = x.compareTo(b[m]);

            // Check if x is present at mid 
            if (res == 0) {
                return m;
            }

            // If x greater, ignore left half 
            if (res > 0) {
                l = m + 1;
            } // If x is smaller, ignore right half 
            else {
                r = m - 1;
            }
        }

        return -1;
    }

    /**
     * Creates new form leaderboard
     */
    public leaderboard() {
        initComponents();
        this.getContentPane().setBackground(new Color(198, 168, 103));
        int i = 0;
        for (CompletedLevels cl : highScores) {
            scores[i] = cl.getLevels();
            i++;
        }
        dquiksort(scores, 0, scores.length - 1);
        int k = 0;
        for (int score : scores) {
            for (CompletedLevels cl : highScores) {
                if (cl.getLevels() == score) {
                    names[k] = cl.getName();
                    
                }
            } 
            k++;
        }
        String output = "";
        for (int j = 0; j < scores.length; j++) {
            output += (j+1)+". Name: " + names[j] + "\nLevels Completed: " + scores[j] + "\n";
        }
        players.setText(output);

    }
 
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jtext = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        players = new javax.swing.JTextArea();
        exit = new javax.swing.JButton();
        search = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        namesearch = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        searchArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jtext.setEditable(false);
        jtext.setFont(new java.awt.Font("Engravers MT", 0, 24)); // NOI18N
        jtext.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtext.setText("LEADERBOARD");
        jtext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtextActionPerformed(evt);
            }
        });

        players.setEditable(false);
        players.setColumns(20);
        players.setRows(5);
        jScrollPane1.setViewportView(players);

        exit.setText("Exit");
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });

        search.setText("Search");
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Engravers MT", 0, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Rankings");

        namesearch.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        searchArea.setEditable(false);
        searchArea.setColumns(20);
        searchArea.setRows(5);
        jScrollPane2.setViewportView(searchArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                            .addComponent(exit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtext, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(namesearch, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(search)))))
                .addContainerGap(85, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jtext, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(17, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(namesearch, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(search))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtextActionPerformed

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
        this.dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_exitActionPerformed

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed

        String p = namesearch.getText();
        int x = binarySearch(names, p);
        if (x != -1) {
            searchArea.setText("Name: " + names[x] + "\nLevels completed: " + scores[x]);
        } else {
            searchArea.setText("Not found");
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_searchActionPerformed

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
            java.util.logging.Logger.getLogger(leaderboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(leaderboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(leaderboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(leaderboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        URL url = FunctionRide.class.getResource("res\\rollercoaster.jpg");
        ImageIcon imageIcon = new ImageIcon(url);
        JLabel label = new JLabel(imageIcon);

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new leaderboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton exit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jtext;
    private javax.swing.JTextField namesearch;
    private javax.swing.JTextArea players;
    private javax.swing.JButton search;
    private javax.swing.JTextArea searchArea;
    // End of variables declaration//GEN-END:variables
}

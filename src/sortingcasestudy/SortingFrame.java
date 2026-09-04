/*
 * =====================================================================
 *  DATASTRU - MIDTERM CASE STUDY (SORTING ALGORITHMS)
 *  GROUP 4
 *
 *  MEMBERS:
 *  - BACALLO, Khen Isiah R.      - LEONEN, Clark Kirby M.
 *  - BALINGIT, Luigi D.          - NASOL, Aeron Francis L.
 *  - DUYANEN, Kryzthelle C.      - PABILANI, Kizziah Aherica J.
 *  - ESCARTIN, Angelica Maze Z.  - SORCOSO, Lean Marr M.
 *  - JIMENEZ, Carl Kian B.       - STAGEN, Stanley Fox P.
 *
 *  PROGRAM FLOW:
 *  1. Input array size.
 *  2. Input elements (separated by space).
 *  3. Choose algorithm (A/B/C/D).
 *  4. Display step-by-step sorting process.
 * =====================================================================
 */
package sortingcasestudy;

/**
 * SortingFrame
 * Ito ang main GUI window ng program. Dito nag-i-interact ang user.
 * 
 * @author Luigi Balingit (Group 4)
 */
public class SortingFrame extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(SortingFrame.class.getName());

    /**
     * Constructor: Tumatawag kapag binubuksan ang application.
     */
    public SortingFrame() {
        initComponents(); // I-setup ang UI components (buttons, labels, etc.)
        
        // UI Tweaks para mas maganda ang dating:
        jTextAreaOutput.setEditable(false); // Bawal i-type ng user ang output area
        jTextAreaOutput.setText("");        // Linisin ang default text
    }

    /* =================================================================
     * HELPER METHODS (Mga Kasangkapan)
     * ================================================================= */

    /**
     * append() - Sumusulat ng text sa Output Area.
     * OPTIMIZATION: May auto-scroll pababa para makita agad ang latest pass.
     */
    private void append(String text) {
        jTextAreaOutput.append(text + "\n");
        // Auto-scroll: Pinupunta ang cursor sa dulo ng text para laging nakikita ang pinakabagong line
        jTextAreaOutput.setCaretPosition(jTextAreaOutput.getDocument().getLength());
    }

    /**
     * formatArray() - Gina-format ang array para magmukhang table.
     * Example: [9, 5, 1]  =>  "| 9 | 5 | 1 |"
     */
    private String formatArray(int[] arr) {
        StringBuilder sb = new StringBuilder("|");
        for (int value : arr) {
            sb.append(" ").append(value).append(" |");
        }
        return sb.toString();
    }

    /* =================================================================
     * A. BUBBLE SORT
     * Concept: Pinaghahambing ang magkatabing numero. Pag mas malaki yung 
     * nasa kaliwa, i-swap. Parang bubbles na lumulutang sa tubig.
     * ================================================================= */
    private void bubbleSort(int[] arr) {
        append("=== BUBBLE SORT ===");
        append("Original: " + formatArray(arr));

        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false; // Flag para i-check kung may nag-swap

            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    // SWAP Logic
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            append("Pass " + (i + 1) + ":    " + formatArray(arr));
            
            // Optimization: Kung walang nag-swap, sorted na. Hinto na!
            if (!swapped) break; 
        }
        append("Sorted:   " + formatArray(arr) + "\n");
    }

    /* =================================================================
     * B. SELECTION SORT
     * Concept: Hanapin ang PINAKAMALIIT na numero sa natitirang array, 
     * tapos ilagay sa pinaka-unahan.
     * ================================================================= */
    private void selectionSort(int[] arr) {
        append("=== SELECTION SORT ===");
        append("Original: " + formatArray(arr));

        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i; // Assume muna na yung current position ang pinakamaliit

            // Hanapin ang totoong pinakamaliit sa natitirang bahagi
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j; // Update kung may nakitang mas maliit
                }
            }

            // Swap kung yung pinakamaliit ay hindi yung nasa current position
            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
            append("Pass " + (i + 1) + ":    " + formatArray(arr));
        }
        append("Sorted:   " + formatArray(arr) + "\n");
    }

    /* =================================================================
     * C. INSERTION SORT
     * Concept: Parang nag-aayos ng playing cards. Kinukuha ang isang card 
     * tapos isinisingit (insert) sa tamang pwesto sa mga naka-sort na.
     * ================================================================= */
    private void insertionSort(int[] arr) {
        append("=== INSERTION SORT ===");
        append("Original: " + formatArray(arr));

        for (int i = 1; i < arr.length; i++) {
            int key = arr[i]; // Ang card na hawak natin ngayon
            int j = i - 1;

            // I-shift pakanan ang mga card na mas malaki sa key
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            // Isingit ang key sa tamang butas
            arr[j + 1] = key; 
            append("Pass " + i + ":      " + formatArray(arr));
        }
        append("Sorted:   " + formatArray(arr) + "\n");
    }

    /* =================================================================
     * D. MERGE SORT (Divide and Conquer)
     * Concept: Hatiin ang array sa maliliit na piraso hanggang sa mag-isa 
     * na lang, tapos pagsamahin (merge) nang naka-sort.
     * ================================================================= */
    private void mergeSortWrapper(int[] arr) {
        append("=== MERGE SORT ===");
        append("Original: " + formatArray(arr));

        if (arr.length <= 1) {
            append("Sorted:   " + formatArray(arr) + "\n");
            return;
        }

        int[] temp = new int[arr.length];
        mergeSortRecursive(arr, temp, 0, arr.length - 1);
        append("Sorted:   " + formatArray(arr) + "\n");
    }

    private void mergeSortRecursive(int[] arr, int[] temp, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            // Divide: Hatiin ang kaliwa at kanan
            mergeSortRecursive(arr, temp, left, mid);
            mergeSortRecursive(arr, temp, mid + 1, right);

            // Conquer: Pagsamahin ang dalawang hati
            mergeArrays(arr, temp, left, mid, right);
            append("Merge:    " + formatArray(arr));
        }
    }

    private void mergeArrays(int[] arr, int[] temp, int left, int mid, int right) {
        // I-copy sa temporary array para safe ang data habang nagha-halo
        for (int i = left; i <= right; i++) {
            temp[i] = arr[i];
        }

        int i = left;    // Pointer ng kaliwang bahagi
        int j = mid + 1; // Pointer ng kanang bahagi
        int k = left;    // Pointer ng main array

        // Paghambingin at isulat ang mas maliit
        while (i <= mid && j <= right) {
            if (temp[i] <= temp[j]) {
                arr[k] = temp[i];
                i++;
            } else {
                arr[k] = temp[j];
                j++;
            }
            k++;
        }

        // Ilipat ang mga natirang nasa kaliwa (kung mayroon man)
        while (i <= mid) {
            arr[k] = temp[i];
            i++;
            k++;
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        logoLabel2 = new CustomControl.LogoLabel();
        logoLabel1 = new CustomControl.LogoLabel();
        glassPanel1 = new CustomControl.GlassPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaOutput = new javax.swing.JTextArea();
        j_jButtonSort = new javax.swing.JButton();
        jComboBoxAlgorithm = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldSize = new javax.swing.JTextField();
        jTextFieldElements = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        modernLabel1 = new CustomControl.ModernLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SORTING ALGORITHMS VISUALIZER");
        setSize(new java.awt.Dimension(900, 700));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Poppins Black", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(241, 245, 249));
        jLabel5.setText("COLLEGE OF COMPUTING AND INFORMATION SCIENCES");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 30, 430, 70));

        jLabel6.setFont(new java.awt.Font("Poppins Black", 0, 26)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(241, 245, 249));
        jLabel6.setText("UNIVERSITY OF MAKATI ");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, 340, 70));

        logoLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assests/CCIS-Logo-Official-1-1-705x705.png"))); // NOI18N
        logoLabel2.setText("logoLabel1");
        getContentPane().add(logoLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 90, 60));

        logoLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assests/UMak-Logo-Registered-Favicon.png"))); // NOI18N
        logoLabel1.setText("logoLabel1");
        getContentPane().add(logoLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 90, 60));

        glassPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextAreaOutput.setEditable(false);
        jTextAreaOutput.setBackground(new java.awt.Color(102, 102, 102));
        jTextAreaOutput.setColumns(20);
        jTextAreaOutput.setFont(new java.awt.Font("Poppins Black", 0, 12)); // NOI18N
        jTextAreaOutput.setForeground(new java.awt.Color(0, 204, 0));
        jTextAreaOutput.setRows(5);
        jTextAreaOutput.setText("Output display:\n");
        jTextAreaOutput.setBorder(null);
        jScrollPane1.setViewportView(jTextAreaOutput);

        glassPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, 570, 130));

        j_jButtonSort.setBackground(new java.awt.Color(0, 102, 0));
        j_jButtonSort.setFont(new java.awt.Font("Poppins Black", 0, 12)); // NOI18N
        j_jButtonSort.setForeground(new java.awt.Color(255, 255, 255));
        j_jButtonSort.setText("SUBMIT");
        j_jButtonSort.addActionListener(this::j_jButtonSortActionPerformed);
        glassPanel1.add(j_jButtonSort, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 210, -1, -1));

        jComboBoxAlgorithm.setBackground(new java.awt.Color(102, 102, 102));
        jComboBoxAlgorithm.setFont(new java.awt.Font("Poppins Black", 0, 12)); // NOI18N
        jComboBoxAlgorithm.setForeground(new java.awt.Color(255, 255, 255));
        jComboBoxAlgorithm.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A. Bubble Sort", "B. Selection Sort", "C. Insertion Sort", "D. Merge Sort" }));
        glassPanel1.add(jComboBoxAlgorithm, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, -1, -1));

        jLabel3.setFont(new java.awt.Font("Poppins Black", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(241, 245, 249));
        jLabel3.setText("Choose Sorting Algorithm:");
        glassPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, -1, -1));

        jLabel2.setFont(new java.awt.Font("Poppins Black", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(241, 245, 249));
        jLabel2.setText("Enter array elements:");
        glassPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 110, -1, -1));

        jLabel1.setFont(new java.awt.Font("Poppins Black", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(241, 245, 249));
        jLabel1.setText("Enter array size:");
        glassPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 140, 20));

        jTextFieldSize.setBackground(new java.awt.Color(102, 102, 102));
        jTextFieldSize.setFont(new java.awt.Font("Poppins Black", 0, 12)); // NOI18N
        jTextFieldSize.setForeground(new java.awt.Color(255, 255, 255));
        jTextFieldSize.addActionListener(this::jTextFieldSizeActionPerformed);
        glassPanel1.add(jTextFieldSize, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 250, 30));

        jTextFieldElements.setBackground(new java.awt.Color(102, 102, 102));
        jTextFieldElements.setFont(new java.awt.Font("Poppins Black", 0, 12)); // NOI18N
        jTextFieldElements.setForeground(new java.awt.Color(255, 255, 255));
        glassPanel1.add(jTextFieldElements, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 140, 250, 30));

        jLabel7.setFont(new java.awt.Font("Poppins Black", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(241, 245, 249));
        jLabel7.setText("SORTING ALGORITHMS VISUALIZER");
        glassPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 460, 70));

        jLabel8.setFont(new java.awt.Font("Poppins Black", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(241, 245, 249));
        jLabel8.setText("Data Structures & Algorithms Midterm Case Study | Group 4");
        glassPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 480, 70));

        getContentPane().add(glassPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, 660, 400));

        modernLabel1.setForeground(new java.awt.Color(241, 245, 249));
        modernLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assests/visax-r9DV-EdDmWM-unsplash.jpg"))); // NOI18N
        getContentPane().add(modernLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 700));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSizeActionPerformed
        
    }//GEN-LAST:event_jTextFieldSizeActionPerformed

    private void j_jButtonSortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_j_jButtonSortActionPerformed
                                                        
        jTextAreaOutput.setText(""); // Linisin ang lumang output

        try {
            // 1. KUNIN ANG SIZE
            int size = Integer.parseInt(jTextFieldSize.getText().trim());
            if (size <= 0) {
                append("⚠️ Error: Array size must be greater than 0.");
                return;
            }

            // 2. KUNIN ANG ELEMENTS
            String elementsText = jTextFieldElements.getText().trim();
            if (elementsText.isEmpty()) {
                append("⚠️ Error: Please enter array elements separated by spaces.");
                return;
            }

            // 3. I-PARSE ANG MGA ELEMENTS
            String[] tokens = elementsText.split("\\s+"); // Hatiin base sa space
            if (tokens.length < size) {
                append("⚠️ Error: Need " + size + " elements, but only " + tokens.length + " entered.");
                return;
            }

            int[] arr = new int[size];
            for (int i = 0; i < size; i++) {
                arr[i] = Integer.parseInt(tokens[i]);
            }

            // 4. ALAMIN ANG PINILING ALGORITHM
            String choice = jComboBoxAlgorithm.getSelectedItem().toString().toUpperCase();

            // 5. I-EXECUTE ANG TAMANG SORTING ALGORITHM
            switch (choice) {
                case "A. BUBBLE SORT":
                    bubbleSort(arr.clone());
                    break;
                case "B. SELECTION SORT":
                    selectionSort(arr.clone());
                    break;
                case "C. INSERTION SORT":
                    insertionSort(arr.clone());
                    break;
                case "D. MERGE SORT":
                    mergeSortWrapper(arr.clone());
                    break;
                default:
                    append("⚠️ Error: Please choose a valid algorithm.");
                    break;
            }

        } catch (NumberFormatException ex) {
            // Dito dadapo kapag nag-type ng letters (halimbawa: "abc") sa number fields
            append("⚠️ Invalid Input: Please use whole numbers only.");
        }
    
     
    }//GEN-LAST:event_j_jButtonSortActionPerformed

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new SortingFrame().setVisible(true));
    
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private CustomControl.GlassPanel glassPanel1;
    private javax.swing.JComboBox<String> jComboBoxAlgorithm;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaOutput;
    private javax.swing.JTextField jTextFieldElements;
    private javax.swing.JTextField jTextFieldSize;
    private javax.swing.JButton j_jButtonSort;
    private CustomControl.LogoLabel logoLabel1;
    private CustomControl.LogoLabel logoLabel2;
    private CustomControl.ModernLabel modernLabel1;
    // End of variables declaration//GEN-END:variables
}
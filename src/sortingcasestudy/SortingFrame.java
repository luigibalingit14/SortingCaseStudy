/*
 * =====================================================================
 *  DATASTRU - MIDTERM CASE STUDY (SORTING ALGORITHMS)
 *  GROUP 4
 *
 *  MEMBERS:
 *  - BACALLO, Khen Isiah R.
 *  - BALINGIT, Luigi D.
 *  - DUYANEN, Kryzthelle C.
 *  - ESCARTIN, Angelica Maze Z.
 *  - JIMENEZ, Carl Kian B.
 *  - LEONEN, Clark Kirby M.
 *  - NASOL, Aeron Francis L.
 *  - PABILANI, Kizziah Aherica J.
 *  - SORCOSO, Lean Marr M.
 *  - STAGEN, Stanley Fox P.
 *
 *  PAANO GUMAGANA ANG PROGRAM NA ITO?
 *  1. Mag-i-input ang user ng array size.
 *  2. Mag-i-input ang user ng mga elements ng array (separated by spaces).
 *  3. Pipili ang user ng sorting algorithm (A/B/C/D) sa combo box.
 *  4. Ipapakita sa output area ang original array, bawat iteration/pass,
 *     hanggang sa maging sorted na ang array.
 * =====================================================================
 */
package sortingcasestudy;

/**
 * SortingFrame = ito ang main window (GUI) ng program natin.
 *
 * @author Luigi Balingit
 */
public class SortingFrame extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(SortingFrame.class.getName());

    /**
     * Constructor - tinatawag ito kapag binuksan ang window.
     */
    public SortingFrame() {
        initComponents(); // Auto-generated ng NetBeans; itinatatag nito ang design (labels, buttons, etc.)
        jTextAreaOutput.setEditable(false); // Para HINDI ma-type/ma-edit ng user ang output area
        jTextAreaOutput.setText("");        // Burahin ang default na "Output display" para malinis magsimula
    }

    /* =================================================================
     * HELPER METHODS - mga kasangkapan na ginagamit ng buong program
     * ================================================================= */

    /**
     * append() - sumusulat ng isang linya sa output area (JTextArea).
     * Ang "\n" ay para lumipat sa bagong linya.
     */
    private void append(String text) {
        jTextAreaOutput.append(text + "\n");
    }

    /**
     * formatArray() - ginagawang table-style na format ang array.
     * Example: {9, 5, 1}  ->  "| 9 | 5 | 1 |"
     */
    private String formatArray(int[] arr) {
        StringBuilder sb = new StringBuilder("|");     // Simulan sa "|"
        for (int value : arr) {                        // Ikutan ang bawat element
            sb.append(" ").append(value).append(" |"); // Ilagay ang value sa gitna ng "|"
        }
        return sb.toString();
    }

    /* =================================================================
     * A. BUBBLE SORT
     * - Pinaghahambing ang MAGKATABING elements, ina-swap kung mali ang ayos.
     * - Bawat "pass", ang pinakamalaking numero ay napupunta sa dulo.
     * ================================================================= */
    private void bubbleSort(int[] arr) {
        append("BUBBLE SORT");
        append("Original: " + formatArray(arr)); // Ipakita muna ang original na array

        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {         // Outer loop = bilang ng passes
            boolean swapped = false;              // Flag: may na-swap ba sa pass na ito?

            for (int j = 0; j < n - 1 - i; j++) { // Inner loop = paghahambing ng magkatabi
                if (arr[j] > arr[j + 1]) {        // Kung mali ang ayos...
                    int temp = arr[j];            // ...i-SWAP ang dalawa
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;               // Markahan na may nangyaring swap
                }
            }

            append("Pass " + (i + 1) + ":  " + formatArray(arr)); // Ipakita ang array pagkatapos ng pass

            if (!swapped) { // Kung walang na-swap, sorted na = hinto na
                break;
            }
        }

        append("Sorted:  " + formatArray(arr)); // Final na sorted array
        append("");                             // Blank line (pampaganda ng spacing)
    }

    /* =================================================================
     * B. SELECTION SORT
     * - Hinahanap ang PINAKAMALIIT na element sa natitirang bahagi,
     *   tapos inilalagay ito sa tamang posisyon sa unahan.
     * ================================================================= */
    private void selectionSort(int[] arr) {
        append("SELECTION SORT");
        append("Original: " + formatArray(arr));

        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {     // Ikutan ang bawat posisyon ng array
            int minIndex = i;                 // Akala muna: current ang pinakamaliit

            for (int j = i + 1; j < n; j++) { // Hanapin ang totoong pinakamaliit sa kanan
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;             // Natagpuan ang mas maliit, itala ang index
                }
            }

            if (minIndex != i) {              // Kung iba ang pinakamaliit, i-swap
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }

            append("Pass " + (i + 1) + ":  " + formatArray(arr)); // Ipakita ang array pagkatapos ng pass
        }

        append("Sorted:  " + formatArray(arr));
        append("");
    }

    /* =================================================================
     * C. INSERTION SORT
     * - Kinukuha ang bawat element (key), tapos isinisingit ito
     *   sa tamang lugar sa bahaging sorted na sa kaliwa.
     * - Parang nag-aayos ng cards sa kamay.
     * ================================================================= */
    private void insertionSort(int[] arr) {
        append("INSERTION SORT");
        append("Original: " + formatArray(arr));

        for (int i = 1; i < arr.length; i++) { // Simulan sa ikalawang element
            int key = arr[i];                  // Ang element na isisingit (insert)
            int j = i - 1;                     // Simulan ang paghahambing sa kaliwa ng key

            // I-shift pakanan ang mga element na mas malaki sa key
            // hanggang sa mahanap ang tamang puwesto ng key
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j]; // I-shift ang element pakanan
                j--;                 // Lumipat sa susunod na kaliwa
            }

            arr[j + 1] = key; // Isingit ang key sa tamang posisyon

            append("Pass " + i + ":  " + formatArray(arr)); // Ipakita ang array pagkatapos ng insertion
        }

        append("Sorted:  " + formatArray(arr));
        append("");
    }

    /* =================================================================
     * D. MERGE SORT (Divide and Conquer)
     * - Hatiin ang array nang paulit-ulit (divide),
     *   tapos pagsamahin nang nakaayos (merge).
     * ================================================================= */

    /**
     * mergeSortWrapper() - panimulang method para sa Merge Sort.
     * Dito nagsisimula ang proseso at dito rin ipinapakita ang final output.
     */
    private void mergeSortWrapper(int[] arr) {
        append("MERGE SORT");
        append("Original: " + formatArray(arr));

        if (arr.length <= 1) { // Kung 1 element lang o wala, sorted na agad
            append("Sorted:  " + formatArray(arr));
            append("");
            return;
        }

        int[] temp = new int[arr.length];        // Temporary array na gagamitin sa pag-merge
        mergeSort(arr, temp, 0, arr.length - 1); // Simulan ang recursive na paghahati

        append("Sorted:  " + formatArray(arr));
        append("");
    }

    /**
     * mergeSort() - RECURSIVE method: hahatiin ang array hanggang sa
     * mag-isang element na lang, tapos i-merge pabalik nang nakaayos.
     */
    private void mergeSort(int[] arr, int[] temp, int left, int right) {
        if (left < right) { // Kung may mahahati pa (hindi pa single element)
            int mid = (left + right) / 2; // Gitnang punto ng hati

            mergeSort(arr, temp, left, mid);      // Hatiin at ayusin ang KALIWA
            mergeSort(arr, temp, mid + 1, right); // Hatiin at ayusin ang KANAN

            merge(arr, temp, left, mid, right); // Pagsamahin ang dalawang bahagi nang sorted

            append("Merge:  " + formatArray(arr)); // Ipakita ang array pagkatapos ng bawat merge
        }
    }

    /**
     * merge() - pinagsasama ang dalawang sorted na bahagi
     * (left..mid at mid+1..right) para maging isang sorted na bahagi.
     */
    private void merge(int[] arr, int[] temp, int left, int mid, int right) {
        // Kopyahin muna lahat sa temporary array (backup habang naghahambing)
        for (int i = left; i <= right; i++) {
            temp[i] = arr[i];
        }

        int i = left;    // Pointer sa simula ng KALIWANG bahagi
        int j = mid + 1; // Pointer sa simula ng KANANG bahagi
        int k = left;    // Pointer sa posisyon sa orihinal na array

        // Paghahambingin ang dalawang bahagi, ilalagay muna ang mas maliit
        while (i <= mid && j <= right) {
            if (temp[i] <= temp[j]) {
                arr[k] = temp[i]; // Mas maliit ang kaliwa, kunin ito
                i++;
            } else {
                arr[k] = temp[j]; // Mas maliit ang kanan, kunin ito
                j++;
            }
            k++;
        }

        // Kung may natira pa sa KALIWANG bahagi, ilipat lahat
        while (i <= mid) {
            arr[k] = temp[i];
            i++;
            k++;
        }

        // Kung may natira pa sa KANANG bahagi, ilipat lahat
        while (j <= right) {
            arr[k] = temp[j];
            j++;
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

        jLabel1 = new javax.swing.JLabel();
        jTextFieldSize = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldElements = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jComboBoxAlgorithm = new javax.swing.JComboBox<>();
        j_jButtonSort = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaOutput = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(500, 500));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Poppins Black", 0, 12)); // NOI18N
        jLabel1.setText("Enter array size:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 140, 20));

        jTextFieldSize.setFont(new java.awt.Font("Poppins Black", 0, 12)); // NOI18N
        jTextFieldSize.addActionListener(this::jTextFieldSizeActionPerformed);
        getContentPane().add(jTextFieldSize, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, 250, 30));

        jLabel2.setFont(new java.awt.Font("Poppins Black", 0, 12)); // NOI18N
        jLabel2.setText("Enter array elements:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, -1, -1));

        jTextFieldElements.setFont(new java.awt.Font("Poppins Black", 0, 12)); // NOI18N
        getContentPane().add(jTextFieldElements, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 150, 250, 30));

        jLabel3.setFont(new java.awt.Font("Poppins Black", 0, 12)); // NOI18N
        jLabel3.setText("Choose Sorting Algorithm:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, -1, -1));

        jComboBoxAlgorithm.setFont(new java.awt.Font("Poppins Black", 0, 12)); // NOI18N
        jComboBoxAlgorithm.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A. Bubble Sort", "B. Selection Sort", "C. Insertion Sort", "D. Merge Sort" }));
        getContentPane().add(jComboBoxAlgorithm, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, -1, -1));

        j_jButtonSort.setFont(new java.awt.Font("Poppins Black", 0, 12)); // NOI18N
        j_jButtonSort.setText("SUBMIT");
        j_jButtonSort.addActionListener(this::j_jButtonSortActionPerformed);
        getContentPane().add(j_jButtonSort, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 240, -1, -1));

        jTextAreaOutput.setColumns(20);
        jTextAreaOutput.setFont(new java.awt.Font("Poppins Black", 0, 12)); // NOI18N
        jTextAreaOutput.setRows(5);
        jTextAreaOutput.setText("Output display:\n");
        jScrollPane1.setViewportView(jTextAreaOutput);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, 240, 110));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSizeActionPerformed
        
    }//GEN-LAST:event_jTextFieldSizeActionPerformed

    private void j_jButtonSortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_j_jButtonSortActionPerformed
        
        jTextAreaOutput.setText(""); // Linisin muna ang dating output

        try {
            // STEP 1: Kunin ang array size mula sa text field at gawing numero
            int size = Integer.parseInt(jTextFieldSize.getText().trim());

            // Validation: dapat positibo ang size
            if (size <= 0) {
                append("Please enter an array size greater than 0.");
                return;
            }

            // STEP 2: Kunin ang mga elements na tinype ng user
            String elementsText = jTextFieldElements.getText().trim();

            // Validation: dapat may nilagay na elements
            if (elementsText.isEmpty()) {
                append("Please enter array elements separated by spaces.");
                return;
            }

            // Hatiin ang input base sa spaces.
            // Example: "9 5 1 4 3" -> {"9","5","1","4","3"}
            String[] tokens = elementsText.split("\\s+");

            // Validation: dapat sapat ang bilang ng elements sa size
            if (tokens.length < size) {
                append("Need " + size + " elements, but only " + tokens.length + " entered.");
                return;
            }

            // STEP 3: Ilagay ang mga elements sa int array
            int[] arr = new int[size];
            for (int i = 0; i < size; i++) {
                arr[i] = Integer.parseInt(tokens[i]); // I-convert ang string -> int
            }

            // STEP 4: Alamin kung anong algorithm ang pinili sa combo box
            String choice = jComboBoxAlgorithm.getSelectedItem() == null
                    ? ""
                    : jComboBoxAlgorithm.getSelectedItem().toString().toUpperCase();

            // STEP 5: Tumawag ng tamang sorting method base sa pinili
            // (Gumamit ng clone() para may backup ng original na input)
            if (choice.contains("BUBBLE") || choice.startsWith("A")) {
                bubbleSort(arr.clone());
            } else if (choice.contains("SELECTION") || choice.startsWith("B")) {
                selectionSort(arr.clone());
            } else if (choice.contains("INSERTION") || choice.startsWith("C")) {
                insertionSort(arr.clone());
            } else if (choice.contains("MERGE") || choice.startsWith("D")) {
                mergeSortWrapper(arr.clone());
            } else {
                append("Please choose A, B, C, or D.");
            }

        } catch (NumberFormatException ex) {
            // Kapag may na-type na hindi numero, dito tayo dadapo
            append("Invalid input. Please use whole numbers only.");
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
    private javax.swing.JComboBox<String> jComboBoxAlgorithm;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaOutput;
    private javax.swing.JTextField jTextFieldElements;
    private javax.swing.JTextField jTextFieldSize;
    private javax.swing.JButton j_jButtonSort;
    // End of variables declaration//GEN-END:variables
}
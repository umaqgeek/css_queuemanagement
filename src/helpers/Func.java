/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import javax.swing.JComboBox;

/**
 *
 * @author umarmukhtar
 */
public class Func {
    
    public static void cmbSelectInput(JComboBox combo, String input, boolean isContain) {
        for (int i = 0; i < combo.getItemCount(); i++) {
            if (String.valueOf(combo.getItemAt(i)).equals(input)) {
                combo.setSelectedIndex(i);
                break;
            }
            if (isContain) {
                if (String.valueOf(combo.getItemAt(i)).contains(input)) {
                    combo.setSelectedIndex(i);
                    break;
                }
            }
        }
    }
}

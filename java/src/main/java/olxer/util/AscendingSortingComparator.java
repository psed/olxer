/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package olxer.util;

import java.util.Comparator;

/**
 *
 * @author user
 */
public class AscendingSortingComparator implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        return Integer.parseInt(o2) - Integer.parseInt(o1);
    }

}

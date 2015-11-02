/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package olxer.web;

/**
 *
 * @author user
 */
public enum RegularExpression {

    TO_REMOVE_WHITESPACES("\\s+"),
    TO_REMOVE_NON_NUMERIC_CHARACTERS("[^\\d]");

    private final String text;

    private RegularExpression(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

}

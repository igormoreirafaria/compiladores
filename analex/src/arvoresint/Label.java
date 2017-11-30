/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arvoresint;

/**
 *
 * @author brunos1212
 */
public class Label extends Operand{
    private static int n;
    private String name;
    
    public Label() {
        name = "L" + n;
        n++;
    }
    public String getName() {
        return name;
    }
}

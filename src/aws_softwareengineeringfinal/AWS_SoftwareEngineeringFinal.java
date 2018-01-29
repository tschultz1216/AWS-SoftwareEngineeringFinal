/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aws_softwareengineeringfinal;

/**
 *
 * @author Todd
 */
public class AWS_SoftwareEngineeringFinal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String test = "9+4";
        DeductionTree tester = new DeductionTree(test);
        System.out.println(test);
        System.out.println(tester.evaluate());

    }
    
}

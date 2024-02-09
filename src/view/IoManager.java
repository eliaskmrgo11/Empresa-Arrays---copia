package view;

import java.util.Scanner;

import javax.swing.JOptionPane;

public class IoManager {
    private Scanner sc;

    public IoManager() {
        sc = new Scanner(System.in);
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showErrorMessage(String message){
        JOptionPane.showMessageDialog(null,message,"Error",JOptionPane.ERROR_MESSAGE);
    }

    public short readShort(String message) {
        this.showMessage(message);
        return sc.nextShort();
    }

    public String readString(String message) {
        this.showMessage(message);
        return sc.next();
    }

    public double readDouble(String message) {
        this.showMessage(message);
        return sc.nextDouble();
    }

    public int readInt(String message) {
        this.showMessage(message);
        return sc.nextInt();
    }

    public Float readFloat(String message) {
        this.showMessage(message);
        return sc.nextFloat();
    }

    public short readMenu() {
        String menuText = 
                "---------------MENU------------\n"+
                "1. Imprimir datos de la empresa\n" +
                "2. Adicionar un empleado\n" +
                "3. Borrar un empleado\n" +
                "4. Modificar un Empleado\n" +
                "5. Mostrar datos de un empleado\n" +
                "6. Mostrar todos los empleados\n" +
                "7. Liquidar\n" +
                "8. Ordenar lista de empleados\n"+
                "9. Salir ";
        return readShort(menuText);
    }

}

package controller;

import model.Date;
import model.Bussiness;
import model.Employee;
import view.IoManager;

public class Control {
    public IoManager io;
    Bussiness bussinessObj;

    public Control() {
        io = new IoManager();
        bussinessObj = new Bussiness();
    }

    public void init() {
        bussinessObj.setName(null);
        bussinessObj.setCity(null);
        bussinessObj.readData();
        if (bussinessObj.getName() == null || bussinessObj.getCity() == null) {

            bussinessObj.setName(io.readString("Nombre de la empresa"));
            bussinessObj.setCity(io.readString("Ciudad de la empresa"));
        }
        this.menu();
    }

    public void menu() {
        int op = 0;
        do {
            try {
                op = io.readMenu();
                switch (op) {
                    case 1:
                        // Imprimir datos de la empresa (nombre y ciudad)
                        io.showMessage(bussinessObj.datosEmpresaString());
                        break;
                    case 2:
                        // Agregar un empleado
                        bussinessObj.addEmployee(createEmployee("A continueacion, ingrese los datos del nuevo empleado"));
                        break;
                    case 3:
                        // Borrar Empleado
                        bussinessObj.deleteEmployee(io.readInt("Ingrese el ID del empleado a despedir:"));
                        break;
                    case 4:
                        // Cambiar un Empleado
                        bussinessObj.changeEmployee(io.readInt("Ingrese el ID del empleado a cambiar:"),io.readShort("Ingrese el numero de hijos actual:"));
                        break;
                    case 5:
                        // Mostrar datos de un empleado
                        io.showMessage(this.bussinessObj.showEmployee(io.readInt("Ingrese el ID del empleado para ver sus datos")));
                        break;
                    case 6:
                        // Mostrar todos los empleados
                        io.showMessage(this.bussinessObj.mostrarEmpleados());

                        break;
                    case 7:
                        // Liquidar
                        io.showMessage(bussinessObj.liquidarEmpleado(io.readInt("Ingrsese el id del empleado a liquidar"))+"\n");
                        break;
                    case 8:
                        bussinessObj.ordedarEmpleados();
                        io.showMessage("Lista de empleados actualizada\n");
                        break;    
                    case 9:
                        // Salir
                        io.showMessage("Saliendo");
                        bussinessObj.writeData();
                        break;
                    default:
                        io.showMessage("Opcion incorrecta");
                }
            } catch (NumberFormatException e) {
                io.showErrorMessage("Opcion no valida");
            }

        } while (op != 9);
    }

    public Date createDate(String message) {
        io.showMessage(message);
        Short day = io.readShort("Dia:");
        Short month = io.readShort("Mes:");
        Short year = io.readShort("Año:");
        return new Date(day, month, year);
    }

    // creado un empleado con todos sus atributos
    public Employee createEmployee(String message) {
        io.showMessage(message);
        short id = io.readShort("Id:");
        String firstName = io.readString("Nombre:");
        String lastName = io.readString("Apellido:");
        double salary = io.readDouble("Salario:");
        Short childrenNumber = io.readShort("Número de hijos:");
        float comission = io.readFloat("Comisión;");
        Date birthDate = this.createDate("Ingrese fecha de cumpleaños");
        Date hirDate = this.createDate("Ingrese fecha de contratación");
        return new Employee(id, firstName, lastName, salary, childrenNumber, comission, birthDate, hirDate);
    }
}

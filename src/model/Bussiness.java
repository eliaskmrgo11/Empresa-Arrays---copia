package model;

import java.util.Iterator;
import Persistence.MyFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Bussiness {
    private String name;
    private String city;
    private ArrayList<Employee> empArray;
    private MyFile arch = new MyFile(
            // "C:\\Users\\SALAS\\Downloads\\Empresa Arrays/datos.txt");
            "D:\\Desktop\\UPTC\\2doSemestre\\2do Cincuenta\\Programacion I\\Empresa Arrays/datosEmpresa.txt");

    public Bussiness() {
        this.empArray = new ArrayList<Employee>();
    }

    public String datosEmpresaString() {
        return "------Datos de la empresa:-----\n Nombre: " + this.name + "\n Ciudad: " + this.city
                + "\n Numero de empleados: " + (empArray.size()) + "\n";
    }

    private int posicionEmpleado(int idEmployeSearch) {
        int posicionEmpleado = -1;
        Employee emp;
        Iterator<Employee> itrEmpArray = empArray.iterator();
        while (itrEmpArray.hasNext() && posicionEmpleado == -1) {
            emp = itrEmpArray.next();
            if (emp.getId() == idEmployeSearch) {
                posicionEmpleado = empArray.indexOf(emp);
            }
        }
        // for (Employee employee : empArray) {
        // if (employee.getId() == idEmployeSearch) {
        // posicionEmpleado = empArray.indexOf(employee);
        // }
        // }
        return posicionEmpleado;
    }

    public void deleteEmployee(int idEmployeDelete) {
        if (this.posicionEmpleado(idEmployeDelete) != -1) {
            empArray.remove(this.posicionEmpleado(idEmployeDelete));
        }
    }

    public void addEmployee(Employee emp) {
        empArray.add(emp);
    }

    public void changeEmployee(int idEmployeChange, short newChildrenNumber) {
        if (this.posicionEmpleado(idEmployeChange) != -1) {
            Employee emp = empArray.get(this.posicionEmpleado(idEmployeChange));
            emp.setChildrenNumber(newChildrenNumber);
            empArray.set(this.posicionEmpleado(idEmployeChange), emp);
        }
    }

    public String showEmployee(int idEmployeSearch) {

        if (this.posicionEmpleado(idEmployeSearch) != -1) {
            return "------Datos del empleado:-----\n" + empArray.get(this.posicionEmpleado(idEmployeSearch)).toString();
        } else {
            return "Empleado inexistente";
        }
    }

    public String mostrarEmpleados() {
        if (empArray.size() == 0) {
            return "No hay empleados";
        } else {
            String listaEmpleados = "La lista de empleados es:\n";
            Iterator<Employee> itrEmpArray = empArray.iterator();
            int i = 1;
            while (itrEmpArray.hasNext()) {
                listaEmpleados += "------Empleado numero " + i + ":------\n";
                listaEmpleados += itrEmpArray.next().toString();
                i++;
            }
            return listaEmpleados;
        }
    }

    public String liquidarEmpleado(int idEmploye) {
        int index = this.posicionEmpleado(idEmploye);
        if (index != -1) {
            double anioTrabajo = ((empArray.get(index).getSalary()) * 0.4) * this.aniosTrabajados(index);
            double bonificacion = ((empArray.get(index)).getSalary() / 2) * empArray.get(index).getChildrenNumber();
            double comision = empArray.get(index).getSalary() / empArray.get(index).getComission();
            double liquidacion = anioTrabajo + bonificacion + comision;
            String strLiquidacion = "--------La liquidacion del empleado " + empArray.get(index).getFisrtname()
                    + ", es de $" + liquidacion + "-------"
                    + "\n Dada por los siguientes calculos:\n  Por el numero de " + this.aniosTrabajados(index)
                    + " años trabajados, suma $" + anioTrabajo
                    + "\n  Por cada hijo del trabajador(" + empArray.get(index).getChildrenNumber()
                    + ") obtiene un bono, que suma $" + bonificacion
                    + "\n  Por su sueldo respecto a una comision del " + empArray.get(index).getComission()
                    + " %, suma $" + comision;
            return strLiquidacion;
        } else {
            return "Empleado no encontrado";
        }
    }

    private int aniosTrabajados(int index) {
        int hireYear = (int) empArray.get(index).getHirDate().getYear();
        int yearsWork = 23 - hireYear;
        if (yearsWork > 0) {
            return yearsWork;
        } else {
            return 0;
        }
    }

    public void ordedarEmpleados(){
        Collections.sort(empArray, Comparator.comparingInt(Employee::getId));    
    }
    // public void readData(){
    // arch.open('r');
    // String datosImportados;
    // String datosCompania = "";
    // while ((datosImportados = arch.read()) != null) {
    // datosCompania += datosImportados + ";;";
    // }
    // ArrayList<String> arrayDatosCompania = new
    // ArrayList<>(Arrays.asList(datosCompania.split(";;")));

    // ArrayList<String> arrayDatosEmpresa = new
    // ArrayList<>(Arrays.asList(arrayDatosCompania.get(0).split(";")));
    // setName(arrayDatosEmpresa.get(0));
    // setCity(arrayDatosEmpresa.get(1));

    // Iterator<String> itrVecDatosCompania = arrayDatosCompania.iterator();
    // // if (itrVecDatosCompania.hasNext()) {
    // // itrVecDatosCompania.next();
    // // }
    // while (itrVecDatosCompania.hasNext()) {
    // addEmployee(stringToEmployee(itrVecDatosCompania.next()));
    // }
    // arch.close();
    // }

    public void readData() {

        arch.open('r');

        String datosImportados;
        String datosCompania = "";
        while ((datosImportados = arch.read()) != null) {
            datosCompania += datosImportados + ";;";
        }
        String[] vecDatosCompania = datosCompania.split(";;");

        String[] vecDatosEmpresa = vecDatosCompania[0].split(";");
        setName(vecDatosEmpresa[0]);
        setCity(vecDatosEmpresa[1]);

        for (int i = 1; i < vecDatosCompania.length; i++) {
            addEmployee(stringToEmployee(vecDatosCompania[i]));
        }
        arch.close();
    }

    private Employee stringToEmployee(String cadEmployee) {

        String[] vecDatosEmpleado = cadEmployee.split(";");
        Employee employee = new Employee();
        employee.setId(Short.parseShort(vecDatosEmpleado[0]));
        employee.setFisrtname(vecDatosEmpleado[1]);
        employee.setLastName(vecDatosEmpleado[2]);
        employee.setSalary(Double.parseDouble(vecDatosEmpleado[3]));
        employee.setChildrenNumber(Short.parseShort(vecDatosEmpleado[4]));
        employee.setComission(Float.parseFloat(vecDatosEmpleado[5]));
        employee.setBirthDate(this.stringToDate(vecDatosEmpleado[6]));
        employee.setHirDate(this.stringToDate(vecDatosEmpleado[7]));
        return employee;
    }

    private Date stringToDate(String stringDate) {
        String[] dates = stringDate.split("/");
        Short dia = Short.parseShort(dates[0]);
        Short mes = Short.parseShort(dates[1]);
        Short anio = Short.parseShort(dates[2]);

        Date date = new Date(dia, mes, anio);
        return date;
    }

    public void writeData() {

        arch.open('w');
        arch.record(this.name + ";" + this.city);
        for (int i = 0; i < empArray.size(); i++) {
            arch.record(this.employeesData(i));
        }
        arch.close();
    }

    private String employeesData(int i) {
        return empArray.get(i).getId() + ";" + empArray.get(i).getFisrtname() + ";"
                + empArray.get(i).getLastName() + ";"
                + empArray.get(i).getSalary() + ";" + empArray.get(i).getChildrenNumber() + ";"
                + empArray.get(i).getComission()
                + ";" + empArray.get(i).getBirthDate() + ";" + empArray.get(i).getHirDate();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public ArrayList<Employee> getEmpArray() {
        return empArray;
    }

    public void setEmpArray(ArrayList<Employee> empArray) {
        this.empArray = empArray;
    }

    public MyFile getArch() {
        return arch;
    }

    public void setArch(MyFile arch) {
        this.arch = arch;
    }

}

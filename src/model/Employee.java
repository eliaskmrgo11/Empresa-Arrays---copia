package model;

import java.time.LocalDateTime;

public class Employee {
    private short id;
    private String fisrtname;
    private String lastName;
    private double salary;
    private short childrenNumber;
    private float comission;
    private Date birthDate;
    private Date hirDate;

    public Employee() {

    }

    public Employee(short id, String fisrtname, String lastName, double salary, short childrenNumber, float comission,
            Date birthDate, Date hirDate) {
        this.id = id;
        this.fisrtname = fisrtname;
        this.lastName = lastName;
        this.salary = salary;
        this.birthDate = birthDate;
        this.hirDate = hirDate;
        this.childrenNumber = childrenNumber;
        this.comission = comission;
    }

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public String getFisrtname() {
        return fisrtname;
    }

    public void setFisrtname(String fisrtname) {
        this.fisrtname = fisrtname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public short getChildrenNumber() {
        return childrenNumber;
    }

    public void setChildrenNumber(short childrenNumber) {
        this.childrenNumber = childrenNumber;
    }

    public float getComission() {
        return comission;
    }

    public void setComission(float comission) {
        this.comission = comission;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getHirDate() {
        return hirDate;
    }

    public void setHirDate(Date hirDate) {
        this.hirDate = hirDate;
    }

    private Date getCurrentDate() {
        LocalDateTime currentLocalDateTime = LocalDateTime.now();
        Date currrentDay = new Date((short) currentLocalDateTime.getDayOfMonth(),
                (short) currentLocalDateTime.getMonthValue(), (short) currentLocalDateTime.getYear());
        return currrentDay;
    }

    private int distancebetweenDates(Date date) {
        return this.getCurrentDate().getYear() - date.getYear();
    }

    public int getAge() {
        return this.distancebetweenDates(birthDate);
    }

    public int getAntiquity() {
        return this.distancebetweenDates(hirDate);
    }

    public String toString() {
        return " ID: " + this.id + " \n Nombre(s): " + this.fisrtname + "\n Apellido(s): " + this.lastName
                + "\n Numero de hijos: " + this.childrenNumber + " \n Salario: $" + this.salary + "\n Comision: "
                + this.comission + "%\n Fecha de nacimiento: " + this.birthDate + "\n Fecha de contratacion: "
                + this.hirDate + "\n";
    }
}

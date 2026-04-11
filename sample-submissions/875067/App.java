
public class App {
    public static void main(String[] args) throws Exception{       
        Employee employee1= new Employee("Susan Meyers", "47899","Accounting","Vice President");
        Employee employee2= new Employee("Mark Jones", "39119", "IT", "Programmer");
        Employee employee3= new Employee("Joy Rogers", "81774", "Manufacturing", "Engineer");

        System.out.printf("%-15s %-10s %-15s %-15s\n", "Name", "ID Number", "Department", "Position");
        System.out.printf("%-15s %-10s %-15s %-15s\n", employee1.getName(), employee1.getIDNumber(), employee1.getDepartment(), employee1.getPosition());
        System.out.printf("%-15s %-10s %-15s %-15s\n", employee2.getName(), employee2.getIDNumber(), employee2.getDepartment(), employee2.getPosition());
        System.out.printf("%-15s %-10s %-15s %-15s\n", employee3.getName(), employee3.getIDNumber(), employee3.getDepartment(), employee3.getPosition());
    }
}
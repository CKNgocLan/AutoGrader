package problem0;

public class ProblemApplication_0 {
    public static void main(String[] args) {
        Employee emp_1 = new Employee("Susan Meyers", 47899, "Accounting", "Vice President");
        System.out.println(emp_1.getName() + " "+emp_1.getIdNumber() + " "+emp_1.getDepartment() + " "+emp_1.getPosition() + " ");


        Employee emp_2 = new Employee("Mark Jones ", 39119, "IT", "Programmer");
        System.out.println(emp_2.getName() + " "+emp_2.getIdNumber() + " "+emp_2.getDepartment() + " "+emp_2.getPosition() + " ");


        Employee emp_3 = new Employee("Joy Rogers ", 81774, "Manufacturing", "Engineer");
        System.out.println(emp_3.getName() + " "+emp_3.getIdNumber() + " "+emp_3.getDepartment() + " "+emp_3.getPosition() + " ");


    }

}

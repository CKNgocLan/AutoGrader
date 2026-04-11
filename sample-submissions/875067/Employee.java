

public class Employee {
    private String name;
    private String idnumber;
    private String department;
    private String position;
    public Employee() {
    }
    public Employee(String name, String idnumber, String department, String position){
        this.name=name;
        this.idnumber=idnumber;
        this.department=department;
        this.position=position;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return this.name;
    }
    public void setIDNumber(String idnumber){
        this.idnumber=idnumber;
    }
    public String getIDNumber(){
        return this.idnumber;
    }
    public void setDepartment(String department){
        this.department=department;
    }
    public String getDepartment(){
        return this.department;
    }
    public void setPosition(String position){
        this.position=position;
    }
    public String getPosition(){
        return this.position;
    }
}
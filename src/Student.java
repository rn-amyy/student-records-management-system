public class Student {
    String fullName,dept,email,gender,matricNo,regNo;
    double cgpa;
    int level;

    public Student(String fullName,String dept,String email,
                   String gender,String matricNo,
                   String regNo,double cgpa,int level){
        this.fullName=fullName;
        this.dept=dept;
        this.email=email;
        this.gender=gender;
        this.matricNo=matricNo;
        this.regNo=regNo;
        this.cgpa=cgpa;
        this.level=level;
    }

}

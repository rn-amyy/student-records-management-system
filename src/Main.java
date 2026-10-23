import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner input=new Scanner(System.in);

        boolean running =true;
        while(running){
            System.out.println("\n\nSTUDENT RECORDS MANAGEMENT SYSTEM\n\n" +
                    "What operation would you like to perform:\n" +
                    "1. ADD A STUDENT\n" +
                    "2. VIEW ALL STUDENTS\n" +
                    "3. SEARCH FOR A STUDENT\n" +
                    "4. UPDATE STUDENT RECORD\n" +
                    "5. DELETE STUDENT\n" +
                    "6. EXIT\n");
            int choice;
            try {
                choice = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number from 1 to 6.");
                continue;
            }
            String matNo;

            switch (choice) {
                case 6:
                    System.out.println("You have exited the system");
                    running=false;
                    break;

                case 1:

                    String fullName = getRequiredInput(input, "Full Name: ");
                    String dept = getRequiredInput(input, "Department: ");
                    String email = getRequiredInput(input, "Email: ");
                    String gender = getRequiredInput(input, "Gender: ");
                    String matricNo = getRequiredInput(input, "Matriculation Number: ");
                    String regNo = getRequiredInput(input, "Registration Number: ");
                    double cgpa=getValidCGPA(input);
                    int level=getValidLevel(input);

                    Student student1=new Student(fullName,dept,email,
                            gender,matricNo,regNo,cgpa,level);
                    DatabaseConnection.saveStudent(student1);

                    System.out.println("Full Name:"+student1.fullName);
                    System.out.println("Department: "+student1.dept);
                    System.out.println("Email:"+student1.email);
                    System.out.println("Gender:"+student1.gender);
                    System.out.println("Matriculation Number: "+student1.matricNo);
                    System.out.println("Registration Number:"+student1.regNo);
                    System.out.println("CGPA:"+student1.cgpa);
                    System.out.println("Level:"+student1.level);
                    break;

                case 2:
                    DatabaseConnection.viewAllStudents();
                    break;

                case 3:
                    System.out.println("Enter student's matric number: ");
                    matNo=input.nextLine();

                    DatabaseConnection.searchStudent(matNo);
                    break;

                case 4:
                    System.out.println("Enter student's matric number:");
                    matNo=input.nextLine();

                    System.out.println("Enter new email address: ");
                    String newEmail=input.nextLine();

                    DatabaseConnection.updateStudent(matNo,newEmail);
                    break;

                case 5:
                    System.out.println("Enter student's matric number:");
                    matNo=input.nextLine();

                    System.out.println("Are you sure you want to delete this student?(yes/no");
                    String confirmation=input.nextLine();

                    if(confirmation.equalsIgnoreCase("yes")){
                        DatabaseConnection.deleteStudent(matNo);
                    }
                    else{
                        System.out.println("Deletion Cancelled.");
                    }
                    break;

                default:
                    System.out.println("You didnt pick an option");

            }

        }
    }
    public static double getValidCGPA(Scanner input){
        while(true){
            System.out.println("CGPA(0.00-5.00: ");
            try{
                double cgpa=Double.parseDouble(input.nextLine());

                if (cgpa >= 0 && cgpa <= 5) {
                    return cgpa;
                }

                System.out.println("CGPA must be between 0.00 and 5.00.");

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
            }
        }


    public static int getValidLevel(Scanner input) {
        while (true) {
            System.out.println("Level (100, 200, 300, 400, or 500): ");

            try {
                int level = Integer.parseInt(input.nextLine());

                if (level == 100 || level == 200 ||
                        level == 300 || level == 400 ||
                        level == 500) {
                    return level;
                }

                System.out.println("Please enter a valid academic level.");

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }


    public static String getRequiredInput(Scanner input, String prompt) {
        while (true) {
            System.out.println(prompt);
            String value = input.nextLine().trim();

            if (!value.isEmpty()) {
                return value;
            }

            System.out.println("This field cannot be empty. Please try again.");
        }
    }




}



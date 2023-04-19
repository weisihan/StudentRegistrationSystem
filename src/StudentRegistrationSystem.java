import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class StudentRegistrationSystem {
    public static void main(String[] args) {
        Registrar r = new Registrar();
        r.start();
    }
}

class Registrar {
    static ArrayList<Student> allStudents = new ArrayList<>();
    static ArrayList<Course> allCourses = new ArrayList<>();
    static JFrame homepage = new JFrame("Student Registration System");
    static ArrayList<JFrame> allJF = new ArrayList<>();
    static JPanel jp = new JPanel(new GridLayout(4,2));
    static JButton importStudent = new JButton("Import Student");
    static JButton importCourse = new JButton("Import Course");
    static JButton enrollStudent = new JButton("Enroll Student");
    static JButton cancelCourse = new JButton("Cancel Course");
    static JButton exemptStudent = new JButton("Exempt Student");
    static JButton removeStudent = new JButton("Remove Student from Course");
    static JButton displayStudent = new JButton("Display Student");
    static JButton displayCourse = new JButton("Display Course");
    static JButton returnHome = new JButton("Return to Homepage");
    public Registrar(){}
    public void start(){
        homepage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homepage.setVisible(true);
        homepage.setSize(1000,1000);
        importStudent.addActionListener(new Press());
        importCourse.addActionListener(new Press());
        enrollStudent.addActionListener(new Press());
        returnHome.addActionListener(new Press());
        cancelCourse.addActionListener(new Press());
        exemptStudent.addActionListener(new Press());
        removeStudent.addActionListener(new Press());
        displayStudent.addActionListener(new Press());
        displayCourse.addActionListener(new Press());
        jp.add(importStudent);
        jp.add(importCourse);
        jp.add(enrollStudent);
        jp.add(cancelCourse);
        jp.add(exemptStudent);
        jp.add(removeStudent);
        jp.add(displayStudent);
        jp.add(displayCourse);
        homepage.add(jp);
    }
    public static void returnToHome(){
        for (JFrame jf : allJF){
            jf.setVisible(false);
        }
        homepage.setVisible(true);
    }
    public static void importStudents(String fileName) throws FileNotFoundException {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Student newStudent = new Student(values);
                allStudents.add(newStudent);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void importCourses(String fileName){
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Course newCourse = new Course(values);
                allCourses.add(newCourse);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void enrollStudent(){
        homepage.setVisible(false);
        searchFrame newJF = new searchFrame("Enroll", "Student");
        newJF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newJF.setVisible(true);
        JPanel newJP = new JPanel(new GridLayout(1,1));
        newJP.add(returnHome);
        newJF.add(newJP);
        allJF.add(newJF);
    }
    public static void cancelCourse(){
        homepage.setVisible(false);
        searchFrame newJF = new searchFrame("Cancel", "Course");
        newJF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newJF.setVisible(true);
        JPanel newJP = new JPanel(new GridLayout(1,1));
        newJP.add(returnHome);
        newJF.add(newJP);
        allJF.add(newJF);
    }
    public static void displayStudentPage(String netID, String purpose) {
        for (Student student : allStudents) {
            if (student.netID.equals(netID)) {
                homepage.setVisible(false);
                if (purpose.equals("Enroll")) {
                    searchFrame newJF = new searchFrame("Enroll", "Course");
                    newJF.setStudent(student);
                    newJF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    newJF.setVisible(true);
                    newJF.setSize(1000, 1000);
                    newJF.setTitle("Homepage of " + student.name);
                    JPanel newJP = new JPanel(new GridLayout(1, 1));
                    newJP.add(returnHome);
                    newJF.add(newJP);
                } else if (purpose.equals("Exempt")) {
                    searchFrame newJF = new searchFrame("Exempt", "Course");
                    newJF.setStudent(student);
                    newJF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    newJF.setVisible(true);
                    newJF.setSize(1000, 1000);
                    newJF.setTitle("Homepage of " + student.name);
                    JPanel newJP = new JPanel(new GridLayout(1, 1));
                    newJP.add(returnHome);
                    newJF.add(newJP);
                } else if (purpose.equals("Remove")) {
                    searchFrame newJF = new searchFrame("Remove", "Course");
                    newJF.setStudent(student);
                    newJF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    newJF.setVisible(true);
                    newJF.setSize(1000, 1000);
                    newJF.setTitle("Homepage of " + student.name);
                    JPanel newJP = new JPanel(new GridLayout(1, 1));
                    newJP.add(returnHome);
                    newJF.add(newJP);
                }
            }
        }
    }
    public static void exemptStudent() {
        homepage.setVisible(false);
        searchFrame newJF = new searchFrame("Exempt", "Student");
        newJF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newJF.setVisible(true);
        JPanel newJP = new JPanel(new GridLayout(1,1));
        newJP.add(returnHome);
        newJF.add(newJP);
        allJF.add(newJF);
    }

    public static void removeStudent() {
        homepage.setVisible(false);
        searchFrame newJF = new searchFrame("Remove", "Student");
        newJF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newJF.setVisible(true);
        JPanel newJP = new JPanel(new GridLayout(1,1));
        newJP.add(returnHome);
        newJF.add(newJP);
        allJF.add(newJF);
    }

    public static void displayStudent() {
        homepage.setVisible(false);
        JFrame f = new JFrame();
        JButton jb = new JButton("Export to file");
        jb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    exportStudent();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        JPanel jp = new JPanel(new GridLayout(1,1));
        jp.add(jb);
        jp.add(returnHome);
        String[][] data = new String[allStudents.size()][5];
        int i = 0;
        for (Student student : allStudents) {
            String allCourses = "";
            for (String course : student.coursesTaken) {
                allCourses += course;
                allCourses += " ";
            }
            data[i] = new String[]{student.name, student.netID, String.valueOf(student.gradYear), String.valueOf(student.credit), allCourses};
            i++;
        }
        String column[]={"Name","NetID","Grad Year","Credit","Courses Taken"};

        JTable jt = new JTable(data,column);
        jt.setBounds(30,40,200,300);
        JScrollPane sp = new JScrollPane(jt);
        jp.add(sp);
        f.add(jp);
        f.setSize(1000,1000);
        f.setVisible(true);
    }

    public static void exportStudent() throws FileNotFoundException {
        PrintStream out = new PrintStream("allStudentOut.txt");
        String header = "Name,NetID,Grad Year,Credit,Courses Taken";
        out.println(header);
        for (Student student : allStudents) {
            String each = student.name + "," + student.netID + "," + String.valueOf(student.gradYear) + "," + String.valueOf(student.credit) + "," ;
            for (String s : student.coursesTaken) {
                each += s;
                each += " ";
            }
            out.println(each);
        }
    }

    public static void displayCourse() {
        homepage.setVisible(false);
        JFrame f = new JFrame();
        JButton jb = new JButton("Export to file");
        jb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    exportCourse();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        JPanel jp = new JPanel(new GridLayout(1,1));
        jp.add(jb);
        jp.add(returnHome);
        String[][] data = new String[allCourses.size()][8];
        int i = 0;
        for (Course course : allCourses) {
            String prereqs = "";
            for (String s : course.prerequisites) {
                prereqs += s;
                prereqs += " ";
            }
            String allStudents = "";
            for (Student student : course.studentsEnrolled) {
                allStudents += student.name + "(" + student.netID + ")";
                allStudents += " ";
            }
            data[i] = new String[]{course.name + "," + course.courseID + "," + course.status + "," + course.time
                    + "," + course.location + "," + String.valueOf(course.capacity) + "," + prereqs + "," + allStudents};
            i++;
        }
        String column[]={"Name,CourseID,Status,Time,Location,Capacity,Prerequisites,Students"};

        JTable jt = new JTable(data,column);
        jt.setBounds(30,40,200,300);
        JScrollPane sp = new JScrollPane(jt);
        jp.add(sp);
        f.add(jp);
        f.setSize(1000,1000);
        f.setVisible(true);
    }

    public static void exportCourse() throws FileNotFoundException {
        PrintStream out = new PrintStream("allCourseOut.txt");
        String header = "Name,CourseID,Status,Time,Location,Capacity,Prerequisites,Students";
        out.println(header);
        for (Course course : allCourses) {
            String each = course.name + "," + course.courseID + "," + course.status + "," + course.time
                    + "," + course.location + "," + String.valueOf(course.capacity) + ",";
            for (String s : course.prerequisites) {
                each += s;
                each += " ";
            }
            each += ",";
            for (Student student : course.studentsEnrolled) {
                each += student.name + "(" + student.netID + ")";
                each += " ";
            }
            out.println(each);
        }
    }
}

class Student {
    String name;
    String netID;
    int gradYear;
    int credit;
    ArrayList<String> coursesTaken = new ArrayList<>();
    ArrayList<Course> currentCourses = new ArrayList<>();
    ArrayList<Course> exempted = new ArrayList<>();
    public Student(String[] allInfo) {
        name = allInfo[0];
        netID = allInfo[1];
        gradYear = Integer.parseInt(allInfo[2]);
        credit = Integer.parseInt(allInfo[3]);
        if (allInfo.length > 4) {
            String[] values = allInfo[4].split(" ");
            for (int i = 0; i < values.length; i++) {
                coursesTaken.add(values[i]);
            }
        }
    }
    public void enrollInCourse(String courseID) {
        for (Course course : Registrar.allCourses) {
            if (course.courseID.equals(courseID)) {
                if (course.status.equals("open") && !course.findStudent(this)) {
                    if (course.checkPrereq(this) || checkExempted(course)){
                        if (course.capacity >= course.number) {
                            course.number += 1;
                            if (course.capacity >= course.number) {
                                course.status = "closed";
                            }
                            course.studentsEnrolled.add(this);
                            currentCourses.add(course);
                            Alert newAlert = new Alert("Student " + this.name + " is now enrolled in " + course.name);
                        } else {
                            Alert newAlert = new Alert("The course " + course.name + " is full!");
                        }
                    } else {
                        Alert newAlert = new Alert("Student " + this.name + " does not meet the prereqs");
                    }
                } else if (course.status.equals("open") && course.findStudent(this)) {
                    Alert newAlert = new Alert("Student " + this.name + " is already enrolled in " + course.name);
                }
            }
        }
    }
    public void removeFromCourse(Course course) {
        if (course.findStudent(this)) {
            course.studentsEnrolled.remove(this);
            currentCourses.remove(course);
            Alert newAlert = new Alert("Student " + this.name + " is now removed from " + course.name);
        } else {
            Alert newAlert = new Alert("Student " + this.name + " is not enrolled in " + course.name);
        }
    }
    public boolean checkExempted(Course course) {
        for (Course ecourse : exempted) {
            if (ecourse == course) { return true; }
        }
        return false;
    }
    public void getExempt(Course course) {
        this.exempted.add(course);
        Alert newAlert = new Alert("Student " + this.name + " is exempted from " + course.name);
    }
}

class Course {
    String name;
    String courseID;
    String status;
    String time;
    String location;
    int capacity;
    int number = 0;
    ArrayList<String> prerequisites = new ArrayList<>();
    ArrayList<Student> studentsEnrolled = new ArrayList<>();
    public Course(String[] allInfo) {
        name = allInfo[0];
        courseID = allInfo[1];
        status = allInfo[2];
        time = allInfo[3];
        location = allInfo[4];
        capacity = Integer.parseInt(allInfo[5]);
        if (allInfo.length > 6) {
            String[] values = allInfo[4].split(" ");
            for (int i = 0; i < values.length; i++) {
                prerequisites.add(values[i]);
            }
        }
    }
    public boolean findStudent(Student student) {
        for (Student s : studentsEnrolled) {
            if (s == student) { return true; }
        }
        return false;
    }
    public boolean checkPrereq(Student student) {
        for (String prereq : prerequisites) {
            boolean each = false;
            for (String course : student.coursesTaken) {
                if (course == prereq) { each = true; }
            }
            if (!each) { return false; }
        }
        return true;
    }
    public void cancelCourse() {
        if (status != "closed") {
            for (Student student : studentsEnrolled) {
                student.removeFromCourse(this);
            }
            status = "closed";
            Alert newAlert = new Alert("The course " + this.name + " is now cancelled");
        }
    }
}

class Press implements ActionListener {
    @Override
    public void actionPerformed (ActionEvent e) {
        JButton source = (JButton) e.getSource();
        if (source == Registrar.importStudent) {
            try {
                Registrar.importStudents("allStudents.csv");
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        } else if (source == Registrar.importCourse) {
            Registrar.importCourses("allCourses.csv");
        } else if (source == Registrar.enrollStudent) {
            Registrar.enrollStudent();
        } else if (source == Registrar.returnHome) {
            Registrar.returnToHome();
        } else if (source == Registrar.cancelCourse) {
            Registrar.cancelCourse();
        } else if (source == Registrar.exemptStudent) {
            Registrar.exemptStudent();
        } else if (source == Registrar.removeStudent) {
            Registrar.removeStudent();
        } else if (source == Registrar.displayStudent) {
            Registrar.displayStudent();
        } else if (source == Registrar.displayCourse) {
            Registrar.displayCourse();
        }
    }
}

class searchFrame extends JFrame {
    String purpose = "";
    String result = "";
    Student student;
    Course course;
    protected JButton buttonSearch;
    public searchFrame(String searchPurpose, String searchResult) {
        purpose = searchPurpose;
        result = searchResult;
        buttonSearch = new JButton("Search for " + result);
        initComponents();
        setSize(1000, 1000);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setStudent(Student theStudent) { student = theStudent; }
    public void setCourse(Course theCourse) { course = theCourse; }
    protected void initComponents() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JPanel panelButton = new JPanel();
        panelButton.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (result.equals("Student")) {
                    searchStudent();
                } else if (purpose.equals("Enroll")) {
                    searchCourse();
                } else if (purpose.equals("Exempt")) {
                    exemptCourse();
                } else if (purpose.equals("Cancel")) {
                    cancelCourse();
                } else if (purpose.equals("Remove")) {
                    removeStudent();
                }
            }
        });
        panelButton.add(buttonSearch);
        add(panelButton);
    }

    private void searchStudent() {
        String studentNetID = JOptionPane.showInputDialog(this, "Enter student net ID to search for:");
        for (Student student : Registrar.allStudents) {
            if (student.netID.equals(studentNetID)) {
                Registrar.displayStudentPage(studentNetID, purpose);
            }
        }
    }

    private void searchCourse() {
        String courseID = JOptionPane.showInputDialog(this, "Enter course ID to search for:");
        for (Course course : Registrar.allCourses) {
            if (course.courseID.equals(courseID)) {
                student.enrollInCourse(courseID);
            }
        }
    }

    private void exemptCourse() {
        String courseID = JOptionPane.showInputDialog(this, "Enter course ID to search for:");
        for (Course course : Registrar.allCourses) {
            if (course.courseID.equals(courseID)) {
                student.getExempt(course);

            }
        }
    }

    private void cancelCourse() {
        String courseID = JOptionPane.showInputDialog(this, "Enter course ID to search for:");
        for (Course course : Registrar.allCourses) {
            if (course.courseID.equals(courseID)) {
                course.cancelCourse();
            }
        }
    }

    private void removeStudent() {
        String courseID = JOptionPane.showInputDialog(this, "Enter course ID to search for:");
        for (Course course : Registrar.allCourses) {
            if (course.courseID.equals(courseID)) {
                student.removeFromCourse(course);
            }
        }
    }
}

class Alert {
    JFrame f;
    public Alert(String message){
        f = new JFrame();
        JOptionPane.showMessageDialog(f,message,"Alert",JOptionPane.WARNING_MESSAGE);
    }
}

import java.sql.*;

public class UniversityJava {
    public static void main(String[] args) {
        String url;
        url = "jdbc:db2://192.86.32.76:5030/DALLASA?autoReconnect=true&useSSL=false";
        String userName = "C434024";
        String password = "buffs";

        try {
            // load the JDBC driver
            // this command will register the driver with the driver manager and make it available to the program

            Class.forName("com.ibm.db2.jcc.DB2Driver");
            // setup the connection to the db
            System.out.println("Here");
            Connection con = DriverManager.getConnection(url, userName, password);
            Statement st = con.createStatement();  // Creates a Statement object for sending SQL statements to the database.
            // A straight forward query to find name, salary and address of all employees
            String sql = "select name from STUDENT, TAKES where STUDENT.id = TAKES.id AND TAKES.course_id in (select COURSE.course_id from COURSE where COURSE.dept_name = 'Comp. Sci.') group by name";
            ResultSet rs = st.executeQuery(sql); // run the query from a Java program
            //ResultSet rs contains the query result. Each string attribute vale is
            //extracted using a getString method, getDouble method and so on. See print statement in the while loop
            System.out.println("Name "); // This will display the table headings

            while (rs.next()) {
                System.out.print(String.format("%-20s", rs.getString(1)));
            }

            st = con.createStatement();
            sql = "select name, dept_name, salary from INSTRUCTOR where salary in (select max(salary) from INSTRUCTOR group by dept_name";
            rs = st.executeQuery(sql);
            System.out.println("Name                Salary      Address");

            while (rs.next()) {
                System.out.print(String.format("%-20s", rs.getString(1) + " " + rs.getString(2)));
                System.out.print(String.format("$%,.2f", rs.getDouble(3)) + "\t");
                System.out.println(rs.getString(4));
            }

            st = con.createStatement();
            sql = "select name, INSTRUCTOR.dept_name, building from INSTRUCTOR, DEPARTMENT where INSTRUCTOR.dept_name = DEPARTMENT.dept_name group by name,INSTRUCTOR.dept_name";
            rs = st.executeQuery(sql);
            System.out.println("Name                Salary      Address");

            while (rs.next()) {
                System.out.print(String.format("%-20s", rs.getString(1) + " " + rs.getString(2)));
                System.out.print(String.format("$%,.2f", rs.getDouble(3)) + "\t");
                System.out.println(rs.getString(4));
            }
        }
        catch(ClassNotFoundException cnfe){
            cnfe.printStackTrace();
            System.err.println(cnfe);
        }
        catch (SQLException e){
            System.err.println(e);
        }
    }
}


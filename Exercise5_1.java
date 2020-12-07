import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.*;

public class Exercise5_1 {
    static Connection con = null;
    static PreparedStatement ps=null;
    public static void main(String[] args) throws SQLException {

        //initialize mysql
       mysqlIni();
        UI ui=new UI();
        //addStudent("sunyu");
        //addCourse("java introducation", "Tuesday", "12:00 AM");
        //enroll(2,4);
        //queryCourses(1);
        //queryStudents(1);
        //querySchedule(2,"Tuesday");
        ui.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    con.close();
                    System.out.println("closed");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
        //con.close();
    }
    public static void mysqlIni(){
        String driver="com.mysql.cj.jdbc.Driver";

        String url="jdbc:mysql://localhost:3306/exercise5_1?&useSSL=false&serverTimezone=UTC";
        String user="root";
        String password="as2188988";
        try {
            //注册JDBC驱动程序
            Class.forName(driver);
            //建立连接
            con = DriverManager.getConnection(url, user, password);
            if (!con.isClosed()) {
                System.out.println("success");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("not install");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("fail");
        }
    }
    public static void addStudent(String name) throws SQLException {
        String sql="insert into students values(default, ?)";
        ps=con.prepareStatement(sql);
        ps.setString(1,name);
        ps.executeUpdate();
    }
    public static void addCourse(String name,String day, String time) throws SQLException {
        String sql="insert into courses values(default, ?,?,?)";
        ps=con.prepareStatement(sql);
        ps.setString(1,name);
        ps.setString(2,day);
        ps.setString(3,time);
        ps.executeUpdate();
    }
    public static void enroll(int studentId, int courseId) throws SQLException {
        String sql="insert into courses_has_students values(?,?)";
        ps= con.prepareStatement(sql);
        ps.setInt(1,courseId);
        ps.setInt(2,studentId);
        ps.executeUpdate();
    }
    public static void queryStudents(int courseId) throws SQLException {
        String sql="select students_id,s.name from courses_has_students cs join students s on cs.students_id=s.id where courses_id=?";
        ps= con.prepareStatement(sql);
        ps.setInt(1,courseId);
        ResultSet ret=ps.executeQuery();


        String res="<html><body>";
        while(ret.next()){
            String id=ret.getString("students_id");
            String name=ret.getString("name");
            res=res+"<br>"+name;
            //System.out.println("student's id="+id);
            System.out.println("student's Name="+name);
        }
        res=res+"</body></html>";
        System.out.println(res);
        QueryResult UI=new QueryResult(res);

    }
    public static void queryCourses(int studentId) throws SQLException {
        String sql="select  courses_id, c.name from courses_has_students cs join courses c on cs.courses_id=c.id  where students_id=?";
        ps= con.prepareStatement(sql);
        ps.setInt(1,studentId);
        ResultSet ret=ps.executeQuery();
        String res="<html><body>";
        while(ret.next()){
            String id=ret.getString("courses_id");
            String name=ret.getString("name");
            res=res+"<br>"+name;
            //System.out.println("Course's id="+id);
            System.out.println("Course's Name="+name);
        }
        res=res+"</body></html>";
        QueryResult UI=new QueryResult(res);
    }
    public static void querySchedule(int studentId, String day) throws SQLException {
        String sql="select courses_id, c.name, c.start_time from courses_has_students cs join courses c on cs.courses_id=c.id where students_id=? and c.day=?";
        ps= con.prepareStatement(sql);
        ps.setInt(1,studentId);
        ps.setString(2,day);
        ResultSet rst=ps.executeQuery();
        String res="<html><body>";
        while(rst.next()){
            String id=rst.getString("courses_id");
            String name=rst.getString("name");
            String startTime=rst.getString("start_time");
            //System.out.println("Course's id="+id);
            res=res+"<br>"+name+"<br>"+startTime;
            System.out.println("Course's Name="+name);
            System.out.println("Course's startTime="+startTime);


        }
        res=res+"</body></html>";
        QueryResult UI=new QueryResult(res);
    }
}

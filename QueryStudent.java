import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class QueryStudent extends JFrame {
    JPanel jp1, jp2;
    JButton jb1;
    JTextField jtf1;

    public QueryStudent() {

        jp1 = new JPanel();
        jp2 = new JPanel();
        jtf1 = new JTextField(20);
        jtf1.setText("Enter Courses'Id here ");

        jb1 = new JButton("OK");
        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String courseId=jtf1.getText();
                try {
                    Exercise5_1.queryStudents(Integer.valueOf(courseId));

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        this.setLayout(new GridLayout(3, 1));

        // 加入各个组件
        jp1.add(jtf1);
        //jp1.add(jtf1);

        jp2.add(jb1);
        //jp2.add(jpf1);

        // 加入到JFrame
        this.add(jp1);
        this.add(jp2);


        this.setSize(450, 150);
        this.setTitle("Query student ");
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}

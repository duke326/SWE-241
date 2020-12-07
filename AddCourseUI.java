import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AddCourseUI extends JFrame {
    JPanel jp1, jp2,jp3,jp4;
    JButton jb1;
    JTextField jtf1,jtf2,jtf3;

    public AddCourseUI() {

        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        jp4 = new JPanel();
        jtf1 = new JTextField(20);
        jtf1.setText("Enter Course's name here ");
        jtf2 = new JTextField(20);
        jtf2.setText("Enter Course's day here ");
        jtf3 = new JTextField(20);
        jtf3.setText("Enter Course's time here ");

        jb1 = new JButton("OK");
        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name=jtf1.getText();
                String day=jtf2.getText();
                String time=jtf3.getText();
                try {
                    Exercise5_1.addCourse(name,day,time);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        this.setLayout(new GridLayout(3, 1));

        // 加入各个组件
        jp1.add(jtf1);
        //jp1.add(jtf1);
        jp3.add(jtf2);
        jp4.add(jtf3);
        jp2.add(jb1);
        //jp2.add(jpf1);

        // 加入到JFrame
        this.add(jp1);
        this.add(jp3);
        this.add(jp4);
        this.add(jp2);



        this.setSize(450, 150);
        this.setTitle("Add course");
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}

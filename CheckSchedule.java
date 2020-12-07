import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class CheckSchedule extends JFrame {
    JPanel jp1, jp2,jp3;
    JButton jb1;
    JTextField jtf1,jtf2;

    public CheckSchedule() {

        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3=new JPanel();
        jtf1 = new JTextField(20);
        jtf1.setText("Enter student's id here ");
        jtf2 = new JTextField(20);
        jtf2.setText("Enter the day you want check here ");

        jb1 = new JButton("OK");
        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentId=jtf1.getText();
                String day=jtf2.getText();
                try {
                    Exercise5_1.querySchedule(Integer.valueOf(studentId),day);
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
        jp2.add(jb1);
        //jp2.add(jpf1);

        // 加入到JFrame
        this.add(jp1);
        this.add(jp3);
        this.add(jp2);


        this.setSize(450, 150);
        this.setTitle("Check schedule");
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}

import javax.swing.*;
import java.awt.*;

public class QueryResult extends JFrame {
    JPanel jp1, jp2;
    JButton jb1;
    JLabel jlb1;

    public QueryResult(String message) {

        jp2 = new JPanel();

        jlb1=new JLabel(message);


        this.setLayout(new GridLayout(1, 1));


        jp2.add(jlb1);
        //jp2.add(jpf1);

        // 加入到JFrame
        this.add(jp2);


        this.setSize(450, 700);
        this.setTitle("Result");
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}

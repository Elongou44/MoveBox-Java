import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterFrame extends JFrame implements ActionListener {
    public static void main(String[] args) {
        RegisterFrame r = new RegisterFrame();
    }

    JLabel lbAccount = new JLabel("请您输入账号");
    JTextField tfAccount = new JTextField(18);
    JLabel lbPassworld1 = new JLabel("请您输入密码");
    JPasswordField pfPassworld1 = new JPasswordField(18);
    JLabel lbPassworld2 = new JLabel("输入确认密码");
    JPasswordField pfPassworld2 = new JPasswordField(18);
    JButton button4 = new JButton("注册");
    JButton button5 = new JButton("退出");
    JPanel jP = new JPanel();

    public RegisterFrame() {
        super("注册");
        jP.add(lbAccount);
        jP.add(tfAccount);
        jP.add(lbPassworld1);
        jP.add(pfPassworld1);
        jP.add(lbPassworld2);
        jP.add(pfPassworld2);
        jP.add(button4);
        jP.add(button5);
        this.setLocation(600, 200);
        this.setSize(250, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        this.add(jP);
        button4.addActionListener(this::actionPerformed);
        button5.addActionListener(this::actionPerformed);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == button4) {
            String password1 = new String(pfPassworld1.getPassword());
            String password2 = new String(pfPassworld2.getPassword());
            //密码二次判断
            if (!password1.equals(password2)) {
                JOptionPane.showMessageDialog(this, "两个密码不相同");
                return;
            }
            String account = tfAccount.getText();//获取账号
            try {

                Class.forName("com.mysql.cj.jdbc.Driver");
                //建立数据库连接
                // pushbox为对应的数据库，user:mysql的账号，，password:MySQL的密码
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/youxi", "root", "13553713631");

                //设置参数值
                //MySQL插入数据语句：INSERT INTO 表名 (字段1, 字段2) VALUES (?, ?)
                PreparedStatement pstmt = conn.prepareStatement("INSERT INTO pushbox (username, usernumber) VALUES (?, ?)");
                pstmt.setString(1, account);
                pstmt.setString(2, password1);//这里使用password1做为密码

                //执行sql
                int rowsInserted = pstmt.executeUpdate();

                //判断登录是否成功
                if (rowsInserted > 0) {
                    JOptionPane.showMessageDialog(this, "用户" + account + "注册成功！");
                } else {
                    JOptionPane.showMessageDialog(this, "注册失败！");
                }

            } catch (SQLException se) {

                se.printStackTrace();
                JOptionPane.showMessageDialog(this, "数据库连接错误");
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }

        }else if (e.getSource() == button5){
            this.dispose();
            new Test1();
        }

    }

}
class Conf{
    public static String account;
    public static String password;

}



import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import javax.swing.*;

public class Test1 extends JFrame {
    public static void main(String[] args) {
        Test1 t = new Test1();
    }

    //创建文本标签和文本框
    JLabel username = new JLabel("用户名");
    JLabel usernumber = new JLabel("密码  ");
    JTextField username1 = new JTextField(18);
    JTextField usernumber1 = new JTextField(18);

    //创建一个容器用来储存
    JPanel jp = new JPanel();

    //注册和登录的按钮
    JButton button1 = new JButton("注册");
    JButton button2 = new JButton("登录");
    JButton button3 = new JButton("退出");

    public Test1() {

        Toolkit t = Toolkit.getDefaultToolkit();//工具类
        Dimension d = t.getScreenSize();

        int height = (int) d.getHeight();//得到显示屏的高度
        int width = (int) d.getWidth();//得到显示屏的宽度
        this.setBounds((width - 300) / 2, (height - 400) / 2, 250, 150);//设置一个宽为250，高为150的窗口，并且让窗口居中

        this.setDefaultCloseOperation(3);//关闭窗口的同时，结束运行
        this.setTitle("登录系统");//窗口标题
        init();
        this.setVisible(true);//让窗口显示


    }

    public void init() {
        //将内容添加到容器中
        jp.add(username);
        jp.add(username1);
        jp.add(usernumber);
        jp.add(usernumber1);
        jp.add(button1);
        jp.add(button2);
        jp.add(button3);

        button1.addActionListener(new ActionListener() {
            //添加监听器
            //将用户名和密码写入文件中的操作
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterFrame registerFrame = new RegisterFrame();
//                    try {
//                        BufferedWriter w = new BufferedWriter(new FileWriter("D:/登录.txt", true));
//                        String sum = username1.getText() + " " + usernumber1.getText();//中间加了空格是为了确保后续登录与文件数据匹配的稳定性
//                        BufferedReader r = new BufferedReader(new FileReader("D:/登录.txt"));
//                        boolean cot = true;
//                        String s;
//                        while ((s = r.readLine()) != null) {
//                            if (sum.equals(s)) {
//                                cot = false;//如果符合其中一条数据，说明该数据就已经存在了，就不能在注册
//                            }
//                        }
//                        if (cot) {
//                            w.write(sum);
//                            w.newLine();
//                            w.flush();
//                            w.close();
//                            JOptionPane.showMessageDialog(null, "注册成功！");//对按了注册按钮做出的回应
//                        } else {
//                            JOptionPane.showMessageDialog(null, "已经存在了，请更换用户名和密码！");//对按了注册按钮做出的回应
//                        }
//                    } catch (IOException e1) {
//                        e1.printStackTrace();
//                    }

            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //String sum = username1.getText() + " " + usernumber1.getText();//中间加了空格是为了确保与文件数据匹配的稳定性
                String username = username1.getText(); //用户名
                String password = usernumber1.getText(); //密码

                //检查用户名和密码是否为空
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(Test1.this, "用户名和密码不能为空,无法登录！", "错误", JOptionPane.ERROR_MESSAGE);
                    return; // 如果为空，则不执行后续代码
                }

                Connection conn = null;
                PreparedStatement pstmt = null;
                ResultSet rs = null;


                try {
                    // 加载JDBC驱动
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    // 建立数据库连接
                    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/youxi", "root", "13553713631");

                    //SQL查询语句：定义sql
                    String sql = "SELECT * FROM pushbox WHERE username = ? AND usernumber = ?";

                    // 使用PreparedStatement来防止SQL注入
                    //设置值
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, username);
                    pstmt.setString(2, password);

                    // 执行sql查询语句
                    rs = pstmt.executeQuery();

                    // 进行数据库匹配
                    if (rs.next()) {

                        JOptionPane.showMessageDialog(null, "登录成功！");// 登录成功
                        dispose();
                        GameLauncher gameLauncher=new GameLauncher();
                    } else {

                        JOptionPane.showMessageDialog(null, "用户名或者密码错误，登录失败！");// 登录失败
                    }

                } catch (ClassNotFoundException | SQLException ex) {
                    // 处理异常
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "登录时发生错误：" + ex.getMessage());
                }

            }

        });
        this.add(jp);
    }
}

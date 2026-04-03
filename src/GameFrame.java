import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame {
    public GameFrame(){

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension d = toolkit.getScreenSize();
        int x =(int)d.getWidth();
        int y =(int)d.getHeight();
        this.setBounds((x-600)/2,(y-600)/2,600,400);
        this.setTitle("推箱子");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel jPanel=new JPanel();
        jPanel.setBackground(Color.gray);
        jPanel.setLayout(new GridLayout(1,3,1,2));
        GameData gameData=new GameData();
        JButton jButton1=new JButton("关卡1");
        JButton jButton2=new JButton("关卡2");
        JButton jButton3=new JButton("关卡3");
        jPanel.add(jButton1);
        jPanel.add(jButton2);
        jPanel.add(jButton3);
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                GameData.currentLevel=0;
                za1 za=new za1();
            }
        });jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                GameData.currentLevel=1;
                za1 za=new za1();
            }
        });jButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                GameData.currentLevel=2;
                za1 za=new za1();
            }
        });
        setContentPane(jPanel);

    }
}
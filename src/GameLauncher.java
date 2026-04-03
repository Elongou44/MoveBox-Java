import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameLauncher extends JFrame{
    public GameLauncher(){
        setTitle("推箱子");
        setLayout(new GridLayout(4,4,0,0));
        for (int i = 0; i < 1; i++) {
            JPanel jPanel=new JPanel();
            jPanel.setBackground(Color.GRAY);
            this.add(jPanel);
        }
        JLabel titleLabel = new JLabel("推箱子", JLabel.CENTER);
        titleLabel.setOpaque(true);
        titleLabel.setFont(new Font("推箱子", Font.BOLD, 36)); // 设置字体样式和大小
        titleLabel.setBackground(Color.GRAY);
        this.add(titleLabel);
        for (int i = 0; i < 5; i++) {
            JPanel jPanel=new JPanel();
            jPanel.setBackground(Color.GRAY);
            this.add(jPanel);
        }
        JButton startButton = new JButton("开始游戏");
        startButton.setOpaque(true);
        startButton.setBackground(Color.GRAY);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new GameFrame().setVisible(true);
            }
        });
        getContentPane().add(startButton);
        this.add(startButton);
        for (int i = 0; i < 4; i++) {
            JPanel jPanel=new JPanel();
            jPanel.setBackground(Color.GRAY);
            this.add(jPanel);
        }
        setSize(388, 288);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

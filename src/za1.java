import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class za1 extends JFrame implements KeyListener {
    static String turn;//定义一个变量判断朝向,用于人物朝向图片更换
    public za1() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu editMenu = new JMenu("编辑");
        JMenu contentMenu = new JMenu("菜单");
        menuBar.add(editMenu);
        menuBar.add(contentMenu);
        JMenuItem quitMenu = new JMenuItem("退出");
        JMenuItem returnMenu = new JMenuItem("返回主菜单");
        returnMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                GameLauncher gameLauncher=new GameLauncher();
            }
        });
        JMenuItem restartMenu = new JMenuItem("重新开始");
        JMenu selectMenu = new JMenu("选择关卡");
        editMenu.add(quitMenu);
        restartMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameRules.resetCurrentLevel();
                repaint();
            }
        });
        quitMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        editMenu.add(returnMenu);
        contentMenu.add(restartMenu);
        contentMenu.add(selectMenu);
        JMenuItem mi1 = new JMenuItem("关卡1");
        mi1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                GameData.currentLevel=0;
                new za1();
                GameRules.resetCurrentLevel();
            }
        });
        JMenuItem mi2 = new JMenuItem("关卡2");
        mi2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                GameData.currentLevel=1;
                new za1();
                GameRules.resetCurrentLevel();
            }
        });
        JMenuItem mi3 = new JMenuItem("关卡3");
        mi3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                GameData.currentLevel=2;
                new za1();
                GameRules.resetCurrentLevel();
            }
        });
        selectMenu.add(mi1);
        selectMenu.add(mi2);
        selectMenu.add(mi3);
        setTitle("推箱子游戏");
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension d = toolkit.getScreenSize();
        int x = (int) d.getWidth();
        int y = (int) d.getHeight();
        setBounds((x-GameData.levels[GameData.currentLevel].length*50)/2, (y-GameData.levels[GameData.currentLevel][GameData.levels[GameData.currentLevel].length-1].length*50)/2,GameData.levels[GameData.currentLevel].length*60, GameData.levels[GameData.currentLevel][GameData.levels[GameData.currentLevel].length-1].length*60);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(this);
        setVisible(true);
        setResizable(false);
        GameData.initPlayerPosition();
    }

    public void paint(Graphics g) {//搞到内存内改变再显示出来
        Image offScreenImage = createImage(getWidth(), getHeight());
        Graphics gOffScreen = offScreenImage.getGraphics();

        boolean allBoxesOnGoals = true;
        for (int i = 0; i < GameData.levels[GameData.currentLevel].length; i++) {
            for (int j = 0; j < GameData.levels[GameData.currentLevel][i].length; j++) {
                Image img = null;
                switch (GameData.levels[GameData.currentLevel][i][j]) {
                    case 1:
                        img = new ImageIcon("MoveBox/Image/Wall.png").getImage();
                        break;
                    case 2:
                        img = new ImageIcon("MoveBox/Image/Box.png").getImage();
                        break;
                    case 3:
                        img = new ImageIcon("MoveBox/Image/Goal.png").getImage();
                        break;
                    case 4://人物朝向判断
                        img = new ImageIcon("MoveBox/Image/Under.png").getImage();//初始化人物位置朝向
                        if(turn=="UP"){
                            img = new ImageIcon("MoveBox/Image/Up.png").getImage();
                        }
                        if(turn=="DOWN"){
                            img = new ImageIcon("MoveBox/Image/Under.png").getImage();
                        }
                        if(turn=="LEFT"){
                            img = new ImageIcon("MoveBox/Image/Left.png").getImage();
                        }
                        if(turn=="RIGHT"){
                            img = new ImageIcon("MoveBox/Image/Right.png").getImage();
                        }
                        break;
                    case 5:
                        img = new ImageIcon("MoveBox/Image/Arrive.png").getImage();
                        break;
                }
                if (img != null) {
                    gOffScreen.drawImage(img, 50 * j, 50 * i, 50, 50, this);
                }
            }
        }

        for (int i = 0; i < GameData.levels[GameData.currentLevel].length; i++) {
            for (int j = 0; j < GameData.levels[GameData.currentLevel][i].length; j++) {
                if (GameData.levels[GameData.currentLevel][i][j] == 2) {
                    allBoxesOnGoals = false;
                    break;
                }
            }
        }

        g.drawImage(offScreenImage, 0, 0, this);
        if (allBoxesOnGoals) {
            JOptionPane.showMessageDialog(this, "你赢了", "胜利", JOptionPane.INFORMATION_MESSAGE);
            advanceToNextLevel();
        }
    }

    public void advanceToNextLevel() {
        GameData.currentLevel = (GameData.currentLevel + 1) % GameData.levels.length;
        GameData.initPlayerPosition();
        GameRules.resetCurrentLevel();
        repaint();
    }
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP) {
            GameRules.move(0, -1);
            turn="UP";
        } else if (key == KeyEvent.VK_DOWN) {
            GameRules.move(0, 1);
            turn="DOWN";
        } else if (key == KeyEvent.VK_LEFT) {
            GameRules.move(-1, 0);
            turn="LEFT";
        } else if (key == KeyEvent.VK_RIGHT) {
            GameRules.move(1, 0);
            turn="RIGHT";
        } else if (key==KeyEvent.VK_SPACE) {
            GameRules.resetCurrentLevel();
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {}


}
class GameRules {

    public static void resetCurrentLevel() {
        GameData.levels[GameData.currentLevel] = deepCopy(GameData.levels2[GameData.currentLevel]);
        GameData.initPlayerPosition();
    }

    public static int[][] deepCopy(int[][] original) {
        if (original == null) {
            return null;
        }
        int[][] result = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            result[i] = original[i].clone();
        }
        return result;
    }
    public static boolean move(int dx, int dy) {
        int newX = GameData.playerPosition.x + dx;
        int newY = GameData.playerPosition.y + dy;

        // 检查边界
        if (newX < 0 || newY < 0 || newX >= GameData.levels[GameData.currentLevel][0].length || newY >= GameData.levels[GameData.currentLevel].length) {
            return false;
        }

        // 获取目标位置的内容
        int targetCell = GameData.levels[GameData.currentLevel][newY][newX];

        // 处理墙壁和箱子
        if (targetCell == 1) {
            return false; // 墙壁
        } else if (targetCell == 2||targetCell == 5) {
            // 箱子后面的位置
            int nextX = newX + dx;
            int nextY = newY + dy;
            if (nextX < 0 || nextY < 0 || nextX >= GameData.levels[GameData.currentLevel][0].length || nextY >= GameData.levels[GameData.currentLevel].length) {
                return false;
            }
            int nextCell = GameData.levels[GameData.currentLevel][nextY][nextX];
            if (nextCell == 0 ) {
                GameData.levels[GameData.currentLevel][newY][newX] = 0;
                GameData.levels[GameData.currentLevel][nextY][nextX] = 2;
            } else if (nextCell==3) {
                GameData.levels[GameData.currentLevel][newY][newX] = 0;
                GameData.levels[GameData.currentLevel][nextY][nextX] = 5;
            } else {
                return false;
            }
        }
        if (GameData.levels2[GameData.currentLevel][GameData.playerPosition.y][GameData.playerPosition.x]==3)//逻辑
            GameData.levels[GameData.currentLevel][GameData.playerPosition.y][GameData.playerPosition.x]=3;
        else
            GameData.levels[GameData.currentLevel][GameData.playerPosition.y][GameData.playerPosition.x]=0;
        GameData.Behide=GameData.levels[GameData.currentLevel][newX][newY];
        GameData.playerPosition.setLocation(newX, newY);
        GameData.levels[GameData.currentLevel][newY][newX] = 4;

        return true;

    }
}


class GameData  {
    public static int Behide=0;
    static int currentLevel = 0;
    public static Point playerPosition;


    public static int[][][] levels = {//支持自定义地图
            {
                    {0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 1, 1, 1, 0, 0, 0},
                    {0, 0, 0, 1, 3, 1, 0, 0, 0},
                    {0, 0, 0, 1, 0, 1, 1, 1, 1},
                    {0, 1, 1, 1, 2, 0, 2, 3, 1},
                    {0, 1, 3, 0, 2, 4, 1, 1, 1},
                    {0, 1, 1, 1, 1, 2, 1, 0, 0},
                    {0, 0, 0, 0, 1, 3, 1, 0, 0},
                    {0, 0, 0, 0, 1, 1, 1, 0, 0}
            },
            {
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 1, 1, 1, 1, 1, 0, 0, 0, 0},
                    {0, 1, 4, 0, 0, 1, 0, 0, 0, 0},
                    {0, 1, 0, 2, 2, 1, 0, 1, 1, 1},
                    {0, 1, 0, 2, 0, 1, 0, 1, 3, 1},
                    {0, 1, 1, 1, 0, 1, 1, 1, 3, 1},
                    {0, 0, 1, 1, 0, 0, 0, 0, 3, 1},
                    {0, 0, 1, 0, 0, 0, 1, 0, 0, 1},
                    {0, 0, 1, 0, 0, 0, 1, 1, 1, 1},
                    {0, 0, 1, 1, 1, 1, 1, 0, 0, 0}
            },
            {
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 1, 1, 1, 1, 1, 0, 0, 0, 0},
                    {0, 1, 0, 0, 0, 1, 1, 1, 1, 1},
                    {0, 1, 0, 0, 3, 2, 0, 0, 0, 1},
                    {0, 1, 1, 0, 3, 1, 0, 2, 0, 1},
                    {0, 0, 1, 0, 3, 1, 0, 2, 4, 1},
                    {0, 0, 1, 1, 3, 0, 0, 2, 0, 1},
                    {0, 0, 0, 1, 0, 0, 1, 1, 1, 1},
                    {0, 0, 0, 1, 1, 1, 1, 0, 0, 0}
            }
    };
    public static int[][][] levels2 = {//不直接写==levels是因为无法完成功能，而且支持自定义地图更改
            {
                    {0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 1, 1, 1, 0, 0, 0},
                    {0, 0, 0, 1, 3, 1, 0, 0, 0},
                    {0, 0, 0, 1, 0, 1, 1, 1, 1},
                    {0, 1, 1, 1, 2, 0, 2, 3, 1},
                    {0, 1, 3, 0, 2, 4, 1, 1, 1},
                    {0, 1, 1, 1, 1, 2, 1, 0, 0},
                    {0, 0, 0, 0, 1, 3, 1, 0, 0},
                    {0, 0, 0, 0, 1, 1, 1, 0, 0}
            },
            {
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 1, 1, 1, 1, 1, 0, 0, 0, 0},
                    {0, 1, 4, 0, 0, 1, 0, 0, 0, 0},
                    {0, 1, 0, 2, 2, 1, 0, 1, 1, 1},
                    {0, 1, 0, 2, 0, 1, 0, 1, 3, 1},
                    {0, 1, 1, 1, 0, 1, 1, 1, 3, 1},
                    {0, 0, 1, 1, 0, 0, 0, 0, 3, 1},
                    {0, 0, 1, 0, 0, 0, 1, 0, 0, 1},
                    {0, 0, 1, 0, 0, 0, 1, 1, 1, 1},
                    {0, 0, 1, 1, 1, 1, 1, 0, 0, 0}
            },
            {
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 1, 1, 1, 1, 1, 0, 0, 0, 0},
                    {0, 1, 0, 0, 0, 1, 1, 1, 1, 1},
                    {0, 1, 0, 0, 3, 2, 0, 0, 0, 1},
                    {0, 1, 1, 0, 3, 1, 0, 2, 0, 1},
                    {0, 0, 1, 0, 3, 1, 0, 2, 4, 1},
                    {0, 0, 1, 1, 3, 0, 0, 2, 0, 1},
                    {0, 0, 0, 1, 0, 0, 1, 1, 1, 1},
                    {0, 0, 0, 1, 1, 1, 1, 0, 0, 0}
            }
    };

    public static void initPlayerPosition() {
        for (int i = 0; i < levels[currentLevel].length; i++) {
            for (int j = 0; j < levels[currentLevel][i].length; j++) {
                if (levels[currentLevel][i][j] == 4) {
                    playerPosition = new Point(j, i);
                }
            }
        }
    }
}

class T {
    public static void main(String[] args) {
        GameLauncher gameLauncher=new GameLauncher();
    }
}

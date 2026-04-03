# MoveBox 推箱子游戏

这是一个使用Java Swing开发的推箱子游戏项目，包含登录注册系统和多关卡设计。

## 项目简介

MoveBox是一个经典的推箱子游戏，玩家需要通过方向键控制人物移动，将箱子推到指定的目标位置。此外，项目还实现了用户登录和注册功能，使用MySQL数据库存储用户信息。第一次做的一个项目，没有添加登录验证，可以直接启动za1.java启动

## 技术栈

- **Java SE**：核心编程语言
- **Swing**：图形界面库，用于实现游戏界面
- **MySQL**：关系型数据库，用于存储用户信息
- **JDBC**：Java数据库连接，用于与MySQL交互

## 项目结构

```
MoveBox/
├── Image/                 # 游戏图片资源
│   ├── Arrive.png         # 箱子到达目标位置的图片
│   ├── Box.png            # 箱子图片
│   ├── Goal.png           # 目标位置图片
│   ├── Left.png           # 人物向左图片
│   ├── Right.png          # 人物向右图片
│   ├── Road.png           # 道路图片
│   ├── Under.png          # 人物向下图片
│   ├── Up.png             # 人物向上图片
│   └── Wall.png           # 墙壁图片
├── src/                   # 源代码
│   ├── GameFrame.java     # 关卡选择界面
│   ├── GameLauncher.java  # 游戏启动界面
│   ├── za1.java           # 游戏主逻辑和界面
│   ├── RegisterFrame.java # 注册界面
│   ├── Test1.java         # 登录界面
│   ├── jdbc.properties    # 数据库配置文件
│   ├── jdbcUtiles.java    # 数据库工具类
│   ├── mysql-connector-java-8.0.18_.jar # MySQL驱动
│   └── MoveBox.sql        # 数据库脚本
└── README.md              # 项目说明文档
```

## 核心功能

### 1. 登录注册系统
- 用户注册：创建新用户账号和密码
- 用户登录：验证用户身份
- 数据存储：使用MySQL数据库存储用户信息

### 2. 游戏功能
- 多关卡设计：包含3个不同难度的关卡
- 人物移动：通过方向键控制人物上下左右移动
- 箱子推动：人物可以推动箱子到指定位置
- 胜利判定：当所有箱子都到达目标位置时游戏胜利
- 关卡切换：胜利后自动进入下一关
- 游戏重置：可以重新开始当前关卡

### 3. 图形界面
- 启动界面：游戏标题和开始按钮
- 关卡选择界面：选择不同关卡
- 游戏界面：显示游戏元素和人物
- 菜单系统：包含退出、返回主菜单、重新开始等选项

## 游戏操作

- **方向键**：控制人物移动
- **空格键**：重置当前关卡
- **菜单选项**：通过顶部菜单进行操作

## 技术实现

### 1. 游戏引擎
- 使用二维数组存储游戏地图
- 实现碰撞检测和移动逻辑
- 采用双缓冲技术优化图形绘制

### 2. 数据库设计
- 数据库表：`pushbox`，存储用户账号和密码
- 使用PreparedStatement防止SQL注入
- 实现基本的异常处理

### 3. 图形界面
- 使用Swing组件构建界面
- 实现事件监听器处理用户输入
- 采用布局管理器优化界面布局

## 安装与运行

### 环境要求
- Java JDK 8或以上
- MySQL数据库
- IDE（如Eclipse、IntelliJ IDEA等）

### 安装步骤
1. **克隆项目**：将项目克隆到本地
2. **创建数据库**：
   - 打开MySQL，创建名为`youxi`的数据库
   - 运行`MoveBox.sql`脚本创建表结构
3. **配置数据库连接**：
   - 修改`Test1.java`和`RegisterFrame.java`中的数据库连接信息
   - 确保用户名和密码正确
4. **导入项目**：在IDE中导入项目
5. **添加依赖**：添加`mysql-connector-java-8.0.18_.jar`到项目依赖
6. **运行游戏**：运行`Test1.java`的main方法启动游戏

### 数据库配置

```sql
-- 创建数据库
CREATE DATABASE youxi;

-- 使用数据库
USE youxi;

-- 创建用户表
CREATE TABLE pushbox (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    usernumber VARCHAR(50) NOT NULL
);
```

## 游戏截图

### 登录界面
![登录界面](Image/login.png)

### 注册界面
![注册界面](Image/register.png)

### 游戏启动界面
![启动界面](Image/launcher.png)

### 关卡选择界面
![关卡选择](Image/level-select.png)

### 游戏界面
![游戏界面](Image/game.png)

## 学习收获

通过开发这个项目，我掌握了以下技能：

- Java Swing图形界面开发
- 事件处理和用户输入响应
- 二维数组的使用和游戏地图设计
- MySQL数据库连接和操作
- JDBC API的使用
- 异常处理和错误调试
- 项目结构设计和代码组织

## 未来

- 此项目为第一次学java所做，比较简陋，不再更新


*此项目仅用于学习和展示目的，作为个人技能的证明。*
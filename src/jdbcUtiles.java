import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;


public class jdbcUtiles {
    //工具类，连接mysql的连接和关闭

    private static String user;
    private static String password;
    private static String url;//端口号
    private static String driver;//需要连接的属性

    static {
        Properties properties =new Properties();
        try {
            properties.load(new FileInputStream("MoveBox/src/jdbc.properties"));

            //读取相关属性
            user=properties.getProperty("user");
            password =properties.getProperty("password");
            url=properties.getProperty("url");
            driver = properties.getProperty("driver");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    //连接数据库

    public static Connection getConnection(){//连接方法

        try {
            System.out.println(user+","+password+","+url+","+driver);
            return DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    //关闭相关资源
    public static void close(ResultSet set, PreparedStatement preparedStatement,Connection connection){

        try {
            if(set!=null){
                set.close();
            }
            if(preparedStatement!=null){
                preparedStatement.close();
            }
            if(connection!=null){
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

class qwe{
    public static void main(String[] args) {

        jdbcUtiles.getConnection();
    }
}

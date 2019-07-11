package sqlservertest;

import entity.Emp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcUtil {
    static Connection conn = null;

    // 获取数据库连?
    public Connection getConn() {
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver"); // 加载数据库驱?
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:jtds:sqlserver://localhost:1433;DatabaseName=db_database19"; // 连接数据库URL
        String userName = "sa"; // 连接数据库的用户?
        String passWord = ""; // 连接数据库密?

        try {
            conn = DriverManager.getConnection(url, userName, passWord); // 获取数据库连?
            if (conn != null) {
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn; // 返回Connection对象
    }

    public void insertEmp(Emp emp) {
        conn = getConn(); // 获取数据库连?
        try {
            PreparedStatement statement = conn.prepareStatement("insert into tb_emp values(?,?,?,?,?,?)"); // 定义插入数据库的预处理语?
            statement.setString(1, emp.getName()); // 设置预处理语句的参数?
            statement.setString(2, emp.getSex());
            statement.setInt(3, emp.getAge());
            statement.setString(4, emp.getDept());
            statement.setString(5, emp.getPhone());
            statement.setString(6, emp.getRemark());
            statement.executeUpdate(); // 执行预处理语?
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

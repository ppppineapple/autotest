package MysqlOperation;


import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectSqlHelper {

    public static Connection connect130() {
        Connection connect1 = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序
            //Class.forName("org.gjt.mm.mysql.Driver");
            System.out.println("Success loading Mysql Driver!");
        } catch (Exception e) {
            System.out.print("Error loading Mysql Driver!");
            e.printStackTrace();
        }
        try {
            connect1 = DriverManager.getConnection("jdbc:mysql://121.40.173.130:3306/joyowyb?characterEncoding=utf8", "joyosbtest", "socmc86dfg");//jdbc:mysql://121.40.173.130:3306/joyowyb","chenzong","chenzong
            System.out.println("Success connect 121.40.173.130!");
        } catch (Exception e) {
            System.out.print("get data error!");
            e.printStackTrace();
        }
        return connect1;
    }

    public static Connection connect84() {
        Connection connect2 = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序
            //Class.forName("org.gjt.mm.mysql.Driver");
            System.out.println("Success loading Mysql Driver!");
        } catch (Exception e) {
            System.out.print("Error loading Mysql Driver!");
            e.printStackTrace();
        }
        try {
            connect2 = DriverManager.getConnection("jdbc:mysql://121.43.147.84:3306/joyowyb?characterEncoding=utf8", "devsb02", "su3ZhISCpvOV");
            System.out.println("Success connect 121.43.147.84!");

        } catch (Exception e) {
            System.out.print("get data error!");
            e.printStackTrace();
        }
        return connect2;

    }

    public static Connection connectJoyosbtest() {
        Connection connect1 = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序
            //Class.forName("org.gjt.mm.mysql.Driver");
            System.out.println("Success loading Mysql Driver!");
        } catch (Exception e) {
            System.out.print("Error loading Mysql Driver!");
            e.printStackTrace();
        }
        try {
            connect1 = DriverManager.getConnection("jdbc:mysql://121.40.173.130:3306/joyosbtest?characterEncoding=utf8", "joyosbtest", "socmc86dfg");//jdbc:mysql://121.40.173.130:3306/joyosbtest","chenzong","chenzong
            System.out.println("Success connect 121.40.173.130!");
        } catch (Exception e) {
            System.out.print("get data error!");
            e.printStackTrace();
        }
        return connect1;
    }

    public static Connection connectShopnctest() {
        Connection connect1 = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序
            //Class.forName("org.gjt.mm.mysql.Driver");
            System.out.println("Success loading Mysql Driver!");
        } catch (Exception e) {
            System.out.print("Error loading Mysql Driver!");
            e.printStackTrace();
        }
        try {
            connect1 = DriverManager.getConnection("jdbc:mysql://121.40.173.130:3306/shopnctest?characterEncoding=utf8", "joyosbtest", "socmc86dfg");//jdbc:mysql://121.40.173.130:3306/joyosbtest","chenzong","chenzong
            System.out.println("Success connect 121.40.173.130!");
        } catch (Exception e) {
            System.out.print("get data error!");
            e.printStackTrace();
        }
        return connect1;
    }
}

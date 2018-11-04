package MysqlOperation;
import java.sql.SQLException;
import java.sql.*;
import java.lang.*;

public class SqlOperation {
    public static void update(String sql){
        try {
            Connection connection  = ConnectSqlHelper.connect130();

            PreparedStatement state = connection.prepareStatement(sql);
            state.executeUpdate(sql);
            state.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void insert(String sql){
        try {
            Connection connection  = ConnectSqlHelper.connect130();
            PreparedStatement state = connection.prepareStatement(sql);
            state.executeUpdate(sql);
            state.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void delete(String sql){
        try {
            Connection connection  = ConnectSqlHelper.connect130();
            PreparedStatement state = connection.prepareStatement(sql);
            state.executeUpdate(sql);
            state.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void query(String sql){
        try {
            Connection connection  = ConnectSqlHelper.connect130();
            PreparedStatement state = connection.prepareStatement(sql);

            ResultSet rs = state.executeQuery();
            int col = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= col; i++) {
                    System.out.print(rs.getString(i) + "\t");
                    if ((i == 2) && (rs.getString(i).length() < 8)) {
                        System.out.print("\t");
                    }
                }
                System.out.println("");
            }
            state.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



}

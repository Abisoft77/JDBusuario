package datos;

import java.sql.*;

public class Conexion {
    private static final String URL = "jdbc:mysql://localhost:3306/test?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "admin";

    public static Connection establecerConexion() throws SQLException {
        return DriverManager.getConnection(URL,USUARIO,PASSWORD);
    }
    //metodos para cerrar la conexion
    public static void close(Connection conn) throws SQLException{
        conn.close();
    }
    public static void close(PreparedStatement pstm) throws SQLException{
        pstm.close();
    }
    public static void close(ResultSet rstm) throws SQLException{
        rstm.close();
    }


}

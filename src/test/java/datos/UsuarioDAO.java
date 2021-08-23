package datos;
import static datos.Conexion.*;
import domain.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private static final String SQL_SELECT = "SELECT * FROM usuario";
    private static final String SQL_INSERT = "INSERT INTO usuario(usuario, password) VALUES(?,?)";
    private static final String SQL_UPDATE = "UPDATE usuario SET usuario=?, password=? WHERE id_usuario=?";
    private static final String SQL_DELETE = "DELETE FROM usuario WHERE id_usuario=?";
    private Connection connectionTransactional;
    //constructor vacio
    public UsuarioDAO(){

    }
    //constructor que recibe la conexion desde afuera
    public UsuarioDAO(Connection conectionTransactional){
        this.connectionTransactional = conectionTransactional;
    }

    //recuperar registro
    public List<Usuario> obtenerDatos() throws SQLException{
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rst = null;
        Usuario user = null;
        List<Usuario> users = new ArrayList<>();
        try {
            con = this.connectionTransactional!=null ? this.connectionTransactional : establecerConexion();
            pstm = con.prepareStatement(SQL_SELECT);
            rst = pstm.executeQuery();
            while (rst.next()) {
                var idUsuario = rst.getInt("id_usuario");
                var usuario = rst.getString("usuario");
                var password = rst.getString("password");
                user = new Usuario(idUsuario,usuario,password);
                users.add(user);
            }
        } finally {
            try {
                close(rst);
                close(pstm);
                if (connectionTransactional == null){
                    close(con);
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }

        return users;
    }

    //insertar registros
    public int insertar(Usuario user) throws SQLException{
        int registro = 0;
        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            conn = this.connectionTransactional != null ? this.connectionTransactional : establecerConexion();
            pstm = conn.prepareStatement(SQL_INSERT);
            pstm.setString(1, user.getUsuario());
            pstm.setString(2, user.getPassword());
            registro = pstm.executeUpdate();
        } finally {
            try {
                close(pstm);
                if (connectionTransactional == null) close(conn);
            } catch (SQLException er){
                er.printStackTrace();
            }
        }

        return registro;
    }

    //Actualizar
    public int actualizar(Usuario user) throws SQLException{
        int registros = 0;
        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            conn = this.connectionTransactional != null ? this.connectionTransactional : establecerConexion();
            pstm = conn.prepareStatement(SQL_UPDATE);
            pstm.setString(1, user.getUsuario());
            pstm.setString(2, user.getPassword());
            pstm.setInt(3, user.getIdUsuario());
            registros = pstm.executeUpdate();
        } finally {
            try {
                close(pstm);
                if (connectionTransactional == null)close(conn);
            } catch ( SQLException er) {
                er.printStackTrace();
            }
        }
        return  registros;
    }

    //Eliminar
    public int eliminar(Usuario user) throws SQLException{
        int registros = 0;
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = this.connectionTransactional!=null ? this.connectionTransactional : establecerConexion();
            pstm = conn.prepareStatement(SQL_DELETE);
            pstm.setInt(1, user.getIdUsuario());
            registros = pstm.executeUpdate();
        } finally {
                close(pstm);
                if (connectionTransactional == null) close(conn);
        }
        return registros;
    }




}

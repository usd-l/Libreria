
package bd;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author USRVI-LC2
 */
public class ConexionBD {
    
    public Connection obtenerConexion()
    {
        Connection conexion = null;
        //trycatch + tab
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/libreria", "root", "duoc.2024");
            System.out.println("Conexion exitosa");
        } catch (Exception e) {
            System.out.println("Fallo conexión con Servidor");
        }
        
        return conexion;
    }
    
//    public static void main(String[] args) {
//        ConexionBD con = new ConexionBD();
//        con.obtenerConexion();
//    }
//    
}

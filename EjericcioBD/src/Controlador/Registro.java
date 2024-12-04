
package Controlador;

import Modelo.Libro;
import bd.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.sql.ResultSet;

/**
 *
 * @author USRVI-LC2
 */
public class Registro { //vamos hacer el CRUD
    
    public boolean agregar(Libro libro)
    {
        try {
            Date date;
            
            ConexionBD con = new ConexionBD();
            Connection cnx = con.obtenerConexion();
            
            date = libro.getPublicacion();
            //SQL
            String query = "INSERT INTO libro(titulo,autor,publicacion,precio,disponible) VALUES(?,?,?,?,?)";
            PreparedStatement stmt = cnx.prepareStatement(query);
            
            //ahora le diremos los ?,?,?,?,?
            stmt.setString(1, libro.getTitulo());
            stmt.setString(2, libro.getAutor());
            stmt.setDate(3, new java.sql.Date(date.getTime()));
            stmt.setInt(4, libro.getPrecio());
            stmt.setBoolean(5,libro.isDisponible());
            
            stmt.executeUpdate();
            stmt.close();
            cnx.close();
            
            return true;
        } catch (SQLException e) {
            System.out.println("Error en SQL al agregar libro " + e.getMessage());
            return false;
        }
        catch(Exception e){
            System.out.println("Error en el método agregar Libro " + e.getMessage());
            return false;
        }
    }
    
    public boolean actualizar(Libro libro)
    {
        try {
            Date date;
            
            ConexionBD con = new ConexionBD();
            Connection cnx = con.obtenerConexion();
            
            date = libro.getPublicacion();
            //SQL
            String query = "UPDATE libro set titulo = ?,autor=?,publicacion=?,precio=?,disponible=? WHERE idlibro=?" ;
            PreparedStatement stmt = cnx.prepareStatement(query);
            
            //ahora le diremos los ?,?,?,?,?
            stmt.setString(1, libro.getTitulo());
            stmt.setString(2, libro.getAutor());
            stmt.setDate(3, new java.sql.Date(date.getTime()));
            stmt.setInt(4, libro.getPrecio());
            stmt.setBoolean(5,libro.isDisponible());
            stmt.setInt(6, libro.getIdLibro());
            
            stmt.executeUpdate();
            stmt.close();
            cnx.close();
            
            return true;
        } catch (SQLException e) {
            System.out.println("Error en SQL al actualizar libro " + e.getMessage());
            return false;
        }
        catch(Exception e){
            System.out.println("Error en el método actualizar Libro " + e.getMessage());
            return false;
        }
    }
    
    public boolean eliminar(int idLibro)
    {
        try {
            
            ConexionBD con = new ConexionBD();
            Connection cnx = con.obtenerConexion();
            
            //SQL
            String query = "DELETE FROM libro WHERE idLibro=?" ;
            PreparedStatement stmt = cnx.prepareStatement(query);
            
            //ahora le diremos los ?,?,?,?,?
            stmt.setInt(1, idLibro);
            
            
            stmt.executeUpdate();
            stmt.close();
            cnx.close();
            
            return true;
        } catch (SQLException e) {
            System.out.println("Error en SQL al elimina libro " + e.getMessage());
            return false;
        }
        catch(Exception e){
            System.out.println("Error en el método eliminar Libro " + e.getMessage());
            return false;
        }
    }
   
    
    public ArrayList<Libro> buscarTodos()
    {
        ArrayList<Libro> lista = new ArrayList<>();
        
        try {
                   
            ConexionBD con = new ConexionBD();
            Connection cnx = con.obtenerConexion();
            
            //SQL
            String query = "SELECT * FROM libro ORDER BY titulo" ;
            PreparedStatement stmt = cnx.prepareStatement(query);
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Libro libro = new Libro();
                libro.setIdLibro(rs.getInt("idLibro"));
                libro.setTitulo(rs.getString("titulo"));
                libro.setAutor(rs.getString("autor"));
                libro.setPublicacion(rs.getDate("publicacion"));
                libro.setPrecio(rs.getInt("precio"));
                libro.setDisponible(rs.getBoolean("disponible"));
                
                lista.add(libro);
            }
            rs.close();
            stmt.close();
            cnx.close();
            
        } catch (SQLException e) {
            System.out.println("Error en SQL al listar libros " + e.getMessage());
        }
       return lista;
    }
    
    public Libro buscarPorId(int idLibro)
    {
        Libro libro = new Libro();        
        try {
                   
            ConexionBD con = new ConexionBD();
            Connection cnx = con.obtenerConexion();
            
            //SQL
            String query = "SELECT * FROM libro WHERE idLibro = ?" ;
            PreparedStatement stmt = cnx.prepareStatement(query);
            stmt.setInt(1, idLibro);
            
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                
                libro.setIdLibro(rs.getInt("idLibro"));
                libro.setTitulo(rs.getString("titulo"));
                libro.setAutor(rs.getString("autor"));
                libro.setPublicacion(rs.getDate("publicacion"));
                libro.setPrecio(rs.getInt("precio"));
                libro.setDisponible(rs.getBoolean("disponible"));
                
            }
            rs.close();
            stmt.close();
            cnx.close();
            
        } catch (SQLException e) {
            System.out.println("Error en SQL al listar libros " + e.getMessage());
        }
       return libro;
    }
    
    
    
}

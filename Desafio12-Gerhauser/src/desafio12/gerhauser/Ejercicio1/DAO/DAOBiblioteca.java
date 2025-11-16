/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package desafio12.gerhauser.Ejercicio1.DAO;

import desafio12.gerhauser.Ejercicio1.Conexion.conexionBiblioteca;
import desafio12.gerhauser.Ejercicio1.Modelo.modeloLibro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Benja
 */
public class DAOBiblioteca {

    // ---------------------------
    // INSERTAR
    // ---------------------------
    public int agregarLibro(modeloLibro libro) {
        String sql = "INSERT INTO libros (titulo, autor, anio_publicacion, isbn, disponible) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = conexionBiblioteca.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, libro.getTitulo());
            stmt.setString(2, libro.getAutor());
            stmt.setInt(3, libro.getAnioPublicacion());
            stmt.setInt(4, libro.getIsbn());
            stmt.setBoolean(5, libro.isDisponible());

            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                return keys.getInt(1);   // <-- ID AUTOGENERADO
            }

        } catch (SQLException e) {
            System.err.println("Error al insertar libro: " + e.getMessage());
        }

        return -1; // error
    }

    // ---------------------------
    // LISTAR TODOS
    // ---------------------------
    public List<modeloLibro> listarLibros() {
        List<modeloLibro> lista = new ArrayList<>();
        String sql = "SELECT * FROM libros";

        try (Connection conn = conexionBiblioteca.obtenerConexion(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                modeloLibro libro = new modeloLibro();
                libro.setId(rs.getInt("id_libro"));
                libro.setTitulo(rs.getString("titulo"));
                libro.setAutor(rs.getString("autor"));
                libro.setAnioPublicacion(rs.getInt("anio_publicacion"));
                libro.setIsbn(rs.getInt("isbn"));
                libro.setDisponible(rs.getBoolean("disponible"));

                lista.add(libro);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar libros: " + e.getMessage());
        }

        return lista;
    }

    // ---------------------------
    // OBTENER POR ID
    // ---------------------------
    public modeloLibro obtenerPorId(int id) {
        String sql = "SELECT * FROM libros WHERE id_libro = ?";
        modeloLibro libro = null;

        try (Connection conn = conexionBiblioteca.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                libro = new modeloLibro();
                libro.setId(rs.getInt("id"));
                libro.setTitulo(rs.getString("titulo"));
                libro.setAutor(rs.getString("autor"));
                libro.setAnioPublicacion(rs.getInt("anio_publicacion"));
                libro.setIsbn(rs.getInt("isbn"));
                libro.setDisponible(rs.getBoolean("disponible"));
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener libro: " + e.getMessage());
        }

        return libro;
    }

    // ---------------------------
    // ACTUALIZAR
    // ---------------------------
    public int actualizarLibro(modeloLibro libro) {
        String sql = "UPDATE libros SET titulo=?, autor=?, anio_publicacion=?, isbn=?, disponible=? WHERE id_libro=?";

        try (Connection conn = conexionBiblioteca.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, libro.getTitulo());
            stmt.setString(2, libro.getAutor());
            stmt.setInt(3, libro.getAnioPublicacion());
            stmt.setInt(4, libro.getIsbn());
            stmt.setBoolean(5, libro.isDisponible());
            stmt.setInt(6, libro.getId());

            int filas = stmt.executeUpdate();

            if (filas > 0) {
                return libro.getId(); // <-- confirma que se editó y devuelve el mismo ID
            }

        } catch (SQLException e) {
            System.err.println("Error al actualizar libro: " + e.getMessage());
        }

        return -1; // error
    }

    // ---------------------------
    // ELIMINAR
    // ---------------------------
    public boolean eliminarLibro(int id) {
        String sql = "DELETE FROM libros WHERE id_libro=?";

        try (Connection conn = conexionBiblioteca.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Error al eliminar libro: " + e.getMessage());
            return false;
        }
    }

    // ---------------------------
    // BUSCAR POR AUTOR
    // ---------------------------
    public List<modeloLibro> buscarPorAutor(String autor) {
        List<modeloLibro> lista = new ArrayList<>();
        String sql = "SELECT * FROM libros WHERE autor LIKE ?";

        try (Connection conn = conexionBiblioteca.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + autor + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                modeloLibro libro = new modeloLibro();
                libro.setId(rs.getInt("id_libro"));
                libro.setTitulo(rs.getString("titulo"));
                libro.setAutor(rs.getString("autor"));
                libro.setAnioPublicacion(rs.getInt("anio_publicacion"));
                libro.setIsbn(rs.getInt("isbn"));
                libro.setDisponible(rs.getBoolean("disponible"));

                lista.add(libro);
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar por autor: " + e.getMessage());
        }

        return lista;
    }

    // ---------------------------
    // LISTAR SOLO DISPONIBLES
    // ---------------------------
    public List<modeloLibro> listarDisponibles() {
        List<modeloLibro> lista = new ArrayList<>();
        String sql = "SELECT * FROM libros WHERE disponible = TRUE";

        try (Connection conn = conexionBiblioteca.obtenerConexion(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                modeloLibro libro = new modeloLibro();
                libro.setId(rs.getInt("id_libro"));
                libro.setTitulo(rs.getString("titulo"));
                libro.setAutor(rs.getString("autor"));
                libro.setAnioPublicacion(rs.getInt("anio_publicacion"));
                libro.setIsbn(rs.getInt("isbn"));
                libro.setDisponible(rs.getBoolean("disponible"));

                lista.add(libro);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar disponibles: " + e.getMessage());
        }

        return lista;
    }
}

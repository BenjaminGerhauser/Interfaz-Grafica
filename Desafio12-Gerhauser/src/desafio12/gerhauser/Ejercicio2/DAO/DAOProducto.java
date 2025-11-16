/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package desafio12.gerhauser.Ejercicio2.DAO;

import desafio12.gerhauser.Ejercicio2.Conexion.conexionGestor;
import desafio12.gerhauser.Ejercicio2.Modelo.modeloProducto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Benja
 */
public class DAOProducto {

    public int agregarProducto(modeloProducto p) {
        String sql = "INSERT INTO productos (nombre, precio, stock, categoria_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = conexionGestor.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, p.getNombre());
            stmt.setDouble(2, p.getPrecio());
            stmt.setInt(3, p.getStock());
            stmt.setInt(4, p.getCategoriaId());

            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                return keys.getInt(1);
            }

        } catch (SQLException e) {
            System.err.println("Error al insertar producto: " + e.getMessage());
        }

        return -1; // error
    }

    public List<modeloProducto> listarProductos() {
        List<modeloProducto> lista = new ArrayList<>();
        String sql = "SELECT * FROM productos";

        try (Connection conn = conexionGestor.obtenerConexion(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                modeloProducto p = new modeloProducto();
                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                p.setPrecio(rs.getDouble("precio"));
                p.setStock(rs.getInt("stock"));
                p.setCategoriaId(rs.getInt("categoria_id"));
                lista.add(p);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar productos: " + e.getMessage());
        }

        return lista;
    }

    public boolean actualizarProducto(modeloProducto p) {
        String sql = "UPDATE productos SET nombre=?, precio=?, stock=?, categoria_id=? WHERE id=?";

        try (Connection conn = conexionGestor.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getNombre());
            stmt.setDouble(2, p.getPrecio());
            stmt.setInt(3, p.getStock());
            stmt.setInt(4, p.getCategoriaId());
            stmt.setInt(5, p.getId());

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Error al actualizar producto: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarProducto(int id) {
        String sql = "DELETE FROM productos WHERE id=?";

        try (Connection conn = conexionGestor.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Error al eliminar producto: " + e.getMessage());
            return false;
        }
    }

    // --------------------------------------------
    // MÉTODO ESPECIAL: JOIN para listar productos con nombre de categoría
    // --------------------------------------------
    public List<String> listarProductosConCategoria() {
        List<String> lista = new ArrayList<>();

        String sql
                = "SELECT p.id, p.nombre AS producto, p.precio, p.stock, "
                + "c.nombre AS categoria, p.categoria_id "
                + "FROM productos p "
                + "JOIN categorias c ON p.categoria_id = c.id";

        try (Connection conn = conexionGestor.obtenerConexion(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String prod = rs.getString("producto");
                double precio = rs.getDouble("precio");
                int stock = rs.getInt("stock");
                String categoria = rs.getString("categoria");
                int idCategoria = rs.getInt("categoria_id");
                lista.add(id + "," + prod + "," + precio + "," + stock + "," + categoria + "," + idCategoria);
            }

        } catch (SQLException e) {
            System.err.println("Error en JOIN: " + e.getMessage());
        }

        return lista;
    }
}

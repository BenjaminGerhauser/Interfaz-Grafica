/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package desafio12.gerhauser.Ejercicio2.DAO;

import desafio12.gerhauser.Ejercicio2.Conexion.conexionGestor;
import desafio12.gerhauser.Ejercicio2.Modelo.modeloCategoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Benja
 */
public class DAOCategoria {

    public int agregarCategoria(modeloCategoria cat) {
        String sql = "INSERT INTO categorias (nombre) VALUES (?)";

        try (Connection conn = conexionGestor.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, cat.getNombre());
            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                return keys.getInt(1);
            }

        } catch (SQLException e) {
            System.err.println("Error al insertar categoría: " + e.getMessage());
        }

        return -1; // error
    }

    public List<modeloCategoria> listarCategorias() {
        List<modeloCategoria> lista = new ArrayList<>();
        String sql = "SELECT * FROM categorias";

        try (Connection conn = conexionGestor.obtenerConexion(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                modeloCategoria cat = new modeloCategoria();
                cat.setId(rs.getInt("id"));
                cat.setNombre(rs.getString("nombre"));
                lista.add(cat);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar categorías: " + e.getMessage());
        }

        return lista;
    }

    public boolean actualizarCategoria(modeloCategoria cat) {
        String sql = "UPDATE categorias SET nombre=? WHERE id=?";

        try (Connection conn = conexionGestor.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cat.getNombre());
            stmt.setInt(2, cat.getId());
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Error al actualizar categoría: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarCategoria(int id) {
        String sql = "DELETE FROM categorias WHERE id=?";

        try (Connection conn = conexionGestor.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Error al eliminar categoría: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Hay productos con esta categoria, eliminelos para poder continuar");
            return false;
        }
    }
}

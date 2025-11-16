/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package desafio12.gerhauser.Ejercicio4.DAO;

import desafio12.gerhauser.Ejercicio4.Modelo.modeloUsuario;
import desafio12.gerhauser.Ejercicio4.conexion.conexionLogin;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Benja
 */
public class DAOusuario {
    // ----------------------------------------
    // REGISTRAR NUEVO USUARIO
    // ----------------------------------------

    public int registrarUsuario(String username, String password, String email) {

        if (existeUsername(username)) {
            System.err.println("El usuario ya existe");
            return -1;
        }

        String sql = "INSERT INTO usuarios (username, password_hash, email) VALUES (?, ?, ?)";

        try (Connection conn = conexionLogin.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, username);
            stmt.setString(2, modeloUsuario.hashPassword(password));
            stmt.setString(3, email);

            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                return keys.getInt(1);
            }

        } catch (SQLException e) {

            if (e.getMessage().contains("Duplicate entry")) {
                System.err.println("Error: usuario o email ya existe");
            } else {
                System.err.println("Error registrando usuario: " + e.getMessage());
            }
        }

        return -1;
    }

    public boolean existeUsername(String username) {
        String sql = "SELECT id FROM usuarios WHERE username = ? LIMIT 1";

        try (Connection conn = conexionLogin.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // si hay un resultado → ya existe

        } catch (SQLException e) {
            System.err.println("Error comprobando username: " + e.getMessage());
        }

        return false;
    }

    // ----------------------------------------
    // VALIDAR LOGIN
    // ----------------------------------------
    public modeloUsuario validarLogin(String username, String password) {

        String sql = "SELECT * FROM usuarios WHERE username = ?";

        try (Connection conn = conexionLogin.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                String hash = rs.getString("password_hash");

                if (modeloUsuario.verificarPassword(password, hash)) {

                    modeloUsuario user = mapearUsuario(rs);

                    // Actualizar último acceso
                    actualizarUltimoAcceso(user.getId());

                    return user;
                }
            }

        } catch (SQLException e) {
            System.err.println("Error validando login: " + e.getMessage());
        }

        return null; // credenciales inválidas
    }

    // ----------------------------------------
    // ACTUALIZAR ÚLTIMO ACCESO
    // ----------------------------------------
    public void actualizarUltimoAcceso(int idUsuario) {

        String sql = "UPDATE usuarios SET ultimo_acceso = NOW() WHERE id = ?";

        try (Connection conn = conexionLogin.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error actualizando último acceso: " + e.getMessage());
        }
    }

    // ----------------------------------------
    // CAMBIAR CONTRASEÑA
    // ----------------------------------------
    public boolean cambiarPassword(int idUsuario, String nuevaPassword) {

        String sql = "UPDATE usuarios SET password_hash = ? WHERE id = ?";

        try (Connection conn = conexionLogin.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, modeloUsuario.hashPassword(nuevaPassword));
            stmt.setInt(2, idUsuario);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error cambiando contraseña: " + e.getMessage());
        }

        return false;
    }

    // ----------------------------------------
    // BUSCAR POR USUARIO
    // ----------------------------------------
    public modeloUsuario obtenerPorUsuario(String username) {

        String sql = "SELECT * FROM usuarios WHERE username = ? LIMIT 1";

    try (Connection conn = conexionLogin.obtenerConexion();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return mapearUsuario(rs);
        }

    } catch (SQLException e) {
        System.err.println("Error obteniendo usuario por username: " + e.getMessage());
    }

    return null;
    }

    // ----------------------------------------
    // CONVERTIR DE RESULTSET → MODELOUSUARIO
    // ----------------------------------------
    private modeloUsuario mapearUsuario(ResultSet rs) throws SQLException {

        LocalDateTime fechaRegistro = rs.getTimestamp("fecha_registro")
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        Timestamp ts = rs.getTimestamp("ultimo_acceso");
        LocalDateTime ultimoAcceso = ts != null
                ? ts.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
                : null;

        return new modeloUsuario(
                rs.getInt("id"),
                rs.getString("username"),
                rs.getString("password_hash"),
                rs.getString("email"),
                fechaRegistro,
                ultimoAcceso
        );
    }

    public List<modeloUsuario> listarUsuarios() {

        List<modeloUsuario> lista = new ArrayList<>();

        String sql = "SELECT * FROM usuarios";

        try (Connection conn = conexionLogin.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                modeloUsuario user = mapearUsuario(rs);
                lista.add(user);
            }

        } catch (SQLException e) {
            System.err.println("Error listando usuarios: " + e.getMessage());
        }

        return lista;
    }

}

package co.softcafe.dao;

import co.softcafe.config.DatabaseConnection;
import co.softcafe.model.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/** Acceso a datos: operaciones CRUD para productos. */
public class ProductoDao {
    public Producto crear(Producto producto) throws SQLException {
        String sql = "INSERT INTO productos (codigo, nombre, precio, stock) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, producto.getCodigo());
            statement.setString(2, producto.getNombre());
            statement.setBigDecimal(3, producto.getPrecio());
            statement.setInt(4, producto.getStock());
            statement.executeUpdate();
            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) producto.setId(keys.getInt(1));
            }
            return producto;
        }
    }

    public List<Producto> listar() throws SQLException {
        String sql = "SELECT id, codigo, nombre, precio, stock FROM productos ORDER BY id";
        List<Producto> productos = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet results = statement.executeQuery()) {
            while (results.next()) productos.add(mapearProducto(results));
        }
        return productos;
    }

    public Optional<Producto> buscarPorId(int id) throws SQLException {
        String sql = "SELECT id, codigo, nombre, precio, stock FROM productos WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet results = statement.executeQuery()) {
                return results.next() ? Optional.of(mapearProducto(results)) : Optional.empty();
            }
        }
    }

    public boolean actualizar(Producto producto) throws SQLException {
        String sql = "UPDATE productos SET codigo = ?, nombre = ?, precio = ?, stock = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, producto.getCodigo());
            statement.setString(2, producto.getNombre());
            statement.setBigDecimal(3, producto.getPrecio());
            statement.setInt(4, producto.getStock());
            statement.setInt(5, producto.getId());
            return statement.executeUpdate() > 0;
        }
    }

    public boolean eliminar(int id) throws SQLException {
        String sql = "DELETE FROM productos WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        }
    }

    private Producto mapearProducto(ResultSet results) throws SQLException {
        return new Producto(results.getInt("id"), results.getString("codigo"),
                results.getString("nombre"), results.getBigDecimal("precio"), results.getInt("stock"));
    }
}

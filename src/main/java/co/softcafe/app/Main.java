package co.softcafe.app;

import co.softcafe.dao.ProductoDao;
import co.softcafe.model.Producto;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Scanner;

/** Menú de consola para demostrar el CRUD del módulo de productos. */
public class Main {
    private static final Scanner INPUT = new Scanner(System.in);
    private static final ProductoDao PRODUCTO_DAO = new ProductoDao();

    public static void main(String[] args) {
        boolean continuar = true;
        while (continuar) {
            mostrarMenu();
            String opcion = INPUT.nextLine().trim();
            try {
                switch (opcion) {
                    case "1" -> crearProducto();
                    case "2" -> listarProductos();
                    case "3" -> buscarProducto();
                    case "4" -> actualizarProducto();
                    case "5" -> eliminarProducto();
                    case "0" -> continuar = false;
                    default -> System.out.println("Opción no válida.");
                }
            } catch (SQLException | IllegalArgumentException exception) {
                System.out.println("No fue posible completar la operación: " + exception.getMessage());
            }
        }
        System.out.println("Aplicación SoftCafé finalizada.");
    }

    private static void mostrarMenu() {
        System.out.println("\n=== SOFTCAFÉ | MÓDULO DE PRODUCTOS ===");
        System.out.println("1. Registrar producto");
        System.out.println("2. Consultar todos los productos");
        System.out.println("3. Buscar producto por ID");
        System.out.println("4. Actualizar producto");
        System.out.println("5. Eliminar producto");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void crearProducto() throws SQLException {
        Producto producto = leerDatosProducto(false);
        System.out.println("Producto registrado: " + PRODUCTO_DAO.crear(producto));
    }

    private static void listarProductos() throws SQLException {
        var productos = PRODUCTO_DAO.listar();
        if (productos.isEmpty()) System.out.println("No hay productos registrados.");
        else productos.forEach(System.out::println);
    }

    private static void buscarProducto() throws SQLException {
        int id = leerEntero("ID del producto: ");
        System.out.println(PRODUCTO_DAO.buscarPorId(id).map(Object::toString)
                .orElse("No se encontró un producto con ese ID."));
    }

    private static void actualizarProducto() throws SQLException {
        int id = leerEntero("ID del producto que desea actualizar: ");
        if (PRODUCTO_DAO.buscarPorId(id).isEmpty()) {
            System.out.println("No se encontró el producto.");
            return;
        }
        Producto producto = leerDatosProducto(true);
        producto.setId(id);
        System.out.println(PRODUCTO_DAO.actualizar(producto)
                ? "Producto actualizado correctamente." : "No se realizaron cambios.");
    }

    private static void eliminarProducto() throws SQLException {
        int id = leerEntero("ID del producto que desea eliminar: ");
        System.out.println(PRODUCTO_DAO.eliminar(id)
                ? "Producto eliminado correctamente." : "No se encontró el producto.");
    }

    private static Producto leerDatosProducto(boolean actualizacion) {
        System.out.print("Código: ");
        String codigo = INPUT.nextLine().trim();
        System.out.print("Nombre: ");
        String nombre = INPUT.nextLine().trim();
        if (codigo.isBlank() || nombre.isBlank()) {
            throw new IllegalArgumentException("El código y el nombre son obligatorios.");
        }
        System.out.print("Precio: ");
        BigDecimal precio = new BigDecimal(INPUT.nextLine().trim());
        int stock = leerEntero("Stock: ");
        if (precio.signum() < 0 || stock < 0) {
            throw new IllegalArgumentException("El precio y el stock no pueden ser negativos.");
        }
        return new Producto(codigo, nombre, precio, stock);
    }

    private static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        int value = Integer.parseInt(INPUT.nextLine().trim());
        if (value < 0) throw new IllegalArgumentException("Ingrese un número igual o mayor que cero.");
        return value;
    }
}

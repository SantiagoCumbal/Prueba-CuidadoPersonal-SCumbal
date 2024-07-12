package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class RegistroProductos {
    public JPanel MainPanel;
    private JTextField codigoP;
    private JTextField nombreP;
    private JTextField descripcionP;
    private JTextField precioP;
    private JTextField cantidadP;
    private JTextField categoriaP;
    private JButton registrarButton;
    private JButton irABusquedaButton;
    String url="jdbc:mysql://localhost:3306/productos_cp";
    String usuario = "root";
    String contraseña= "123456";
    Productos newproducto = new Productos();
    String sql="INSERT INTO PRODUCTO (codigo_producto, nombre, descripcion, precio, cantidad, categoria) VALUES (?,?,?,?,?,?)";

    public RegistroProductos() {
        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try(Connection connection= DriverManager.getConnection(url,usuario,contraseña)){
                    System.out.println("CONEXION EXITOSA");
                    newproducto.setCodigoProducto(codigoP.getText());
                    newproducto.setNombreProducto(nombreP.getText());
                    newproducto.setDescripcion(descripcionP.getText());
                    newproducto.setPrecio(Double.parseDouble(precioP.getText()));
                    newproducto.setCantidad(Integer.parseInt(cantidadP.getText()));
                    newproducto.setCategoria(categoriaP.getText());

                    PreparedStatement ps=connection.prepareStatement(sql);
                    ps.setString(1, newproducto.getCodigoProducto());
                    ps.setString(2, newproducto.getNombreProducto());
                    ps.setString(3, newproducto.getDescripcion());
                    ps.setDouble(4, newproducto.getPrecio());
                    ps.setInt(5, newproducto.getCantidad());
                    ps.setString(6, newproducto.getCategoria());
                    ps.executeUpdate();

                }catch(SQLException e1){
                    System.out.println(e1.getMessage());
                }
            }
        });
        irABusquedaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setContentPane(new BusquedaProductos().MainPanel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(300,300);
                frame.setVisible(true);
                ((JFrame)SwingUtilities.getWindowAncestor(irABusquedaButton)).dispose();
            }
        });
    }
}

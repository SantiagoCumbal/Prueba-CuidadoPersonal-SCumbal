package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class BusquedaProductos {
    public JPanel MainPanel;
    private JTextField buscarP;
    private JButton buscarButton;
    private JTextField codigoP;
    private JTextField nombreP;
    private JTextField descripcionP;
    private JTextField precioP;
    private JTextField cantidadP;
    private JTextField categoriaP;
    private JButton irARegistroButton;
    private JButton irALoginButton;
    String url="jdbc:mysql://localhost:3306/productos_cp";
    String usuario = "root";
    String contraseña= "123456";



    public BusquedaProductos() {
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try(Connection connection= DriverManager.getConnection(url,usuario,contraseña)){
                    System.out.println("CONEXION EXITOSA");
                    String query="select * from PRODUCTO where codigo_producto= '"+buscarP.getText()+"'";
                    Statement statement=connection.createStatement();
                    ResultSet resultSet=statement.executeQuery(query);
                    while(resultSet.next()){
                        codigoP.setText(resultSet.getString("codigo_producto"));
                        nombreP.setText(resultSet.getString("nombre"));
                        descripcionP.setText(resultSet.getString("descripcion"));
                        precioP.setText(resultSet.getString("precio"));
                        cantidadP.setText(resultSet.getString("cantidad"));
                        categoriaP.setText(resultSet.getString("categoria"));

                    }
                }catch(SQLException e1){
                    System.out.println(e1.getMessage());
                }
            }
        });
        irARegistroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setContentPane(new RegistroProductos().MainPanel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(300,300);
                frame.setVisible(true);
                ((JFrame)SwingUtilities.getWindowAncestor(irARegistroButton)).dispose();
            }
        });
        irALoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setContentPane(new Login().MainPanel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(300,300);
                frame.setVisible(true);
            }
        });
    }
}

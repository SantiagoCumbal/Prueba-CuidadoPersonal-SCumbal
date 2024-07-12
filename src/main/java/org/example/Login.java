package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login {
    public JPanel MainPanel;
    private JTextField usuarioT;
    private JTextField contraseñaT;
    private JButton aceptarButton;
    private JLabel Error;
    String url="jdbc:mysql://localhost:3306/productos_cp";
    String usuario = "root";
    String contraseña= "123456";
    Usuarios usenew = new Usuarios();



    public Login() {
        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try(Connection connection= DriverManager.getConnection(url,usuario,contraseña)){
                    System.out.println("CONEXION EXITOSA");
                    String query="select * from USUARIO";
                    Statement statement=connection.createStatement();
                    ResultSet resultSet=statement.executeQuery(query);
                    usenew.setUser(usuarioT.getText());
                    usenew.setPassword(contraseñaT.getText());

                    while(resultSet.next()){
                        if(usenew.getUser().equals(resultSet.getString("username")) && usenew.getPassword().equals(resultSet.getString("password"))){
                            System.out.println("Ingreso exitoso");
                            JFrame frame = new JFrame();
                            frame.setContentPane(new RegistroProductos().MainPanel);
                            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            frame.setSize(300,300);
                            frame.setVisible(true);
                            ((JFrame)SwingUtilities.getWindowAncestor(aceptarButton)).dispose();
                        }else{
                            Error.setText("ERROR DE INGRESO");
                            usuarioT.setText("");
                            contraseñaT.setText("");
                        }
                    }


                }catch(SQLException e1){
                    System.out.println(e1.getMessage());
                }
            }
        });
    }
}

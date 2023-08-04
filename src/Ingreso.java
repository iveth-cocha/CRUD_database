import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Ingreso {
    //ventana
    private JTextField user;
    private JPasswordField psw;
    private JButton ingresarButton;
    private JButton registrarButton;
    private JButton eliminarButton;
    public JPanel rootPanel;
    private JTextField codigo;
    private JTextField edad;
    private JTextField ciudad;
    private JTextField cedula;
    private JButton actualizarButton;
    //database
    static final String DB_URL="jdbc:mysql://localhost/Principal";
    static final String USER="root";
    static final String PASS="poo123";
    static final String QUERY="Select * From Estudiantes1";
    //var de conexion
    public static String nombrein;
    public static String clavein;
    public static String idx;
    public static String edax;
    public static String ciux;
    public static String cedx;
    public static String usuariox;
    public static String clavex;

    public Ingreso() {
        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {



                //variables del jpanel
                usuariox =user.getText().trim();
                clavex = new String(psw.getPassword()).trim();
                comprobar();
            }
        });
        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idx=codigo.getText().trim();
                edax=edad.getText().trim();
                ciux=ciudad.getText().trim();
                cedx=cedula.getText().trim();
                usuariox =user.getText().trim();
                clavex = new String(psw.getPassword()).trim();
                registrar(idx,usuariox,edax,ciux,cedx,clavex);
            }
        });
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idx=codigo.getText().trim();
                usuariox =user.getText().trim();
                clavex = new String(psw.getPassword()).trim();
                eliminar(idx);
            }
        });
        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usuariox =user.getText().trim();
                clavex = new String(psw.getPassword()).trim();
                actualizar(edax);
            }
        });
    }

    public static void comprobar(){
        try(
                //Esencial para la conección
                Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
                Statement stmt= conn.createStatement();
                ResultSet rs= stmt.executeQuery(QUERY);
        ){
            //valido credenciales
            boolean userFound = false;
            while (rs.next()){
                //ingreso credenciales
                nombrein = rs.getString("nombre");
                clavein = rs.getString("contra");
                //verificar
                if(nombrein.equals(usuariox) && clavein.equals(clavex)){
                    userFound = true;
                    // System.out.println("----------------------------------------------");
                    System.out.println("Bienvenido");
                    System.out.println("Nombre: "+rs.getString("nombre"));
                    System.out.println("Edad: "+rs.getString("edad"));
                    System.out.println("Ciudad: "+rs.getString("ciudad"));
                    System.out.println("Cedula: "+rs.getString("cedula"));
                    System.out.println("Clave: "+rs.getString("contra"));
                    System.out.println("----------------------------------------------");
                }}
            if (!userFound){
                System.out.println("----------------------------------------------");
                System.out.println("!!ERRORR¡¡ Usuario o clave incorrectos.");
            }
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    public static void registrar(String id,String usu, String age,String ciu,String ced,String clav){
        //asi mi tabla este configurada necesito llenar todos los campos
        String query3 = " insert into Estudiantes1 values('"+id+"','"+usu+"','"+age+"','"+ciu+"','"+ced+"','"+clav+"')";
        //System.out.println(query3);
        try(
                Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
                Statement stmt= conn.createStatement();
        ){
            stmt.executeUpdate(query3);
            System.out.println("Usuario registardo exitosamente");
            System.out.println("----------------------------------------------");
        }catch (Exception el){
            throw new RuntimeException(el);
        }
    }

    public static void eliminar(String id){
        String query2 = "DELETE FROM Estudiantes1 where id = '"+ id +"'";
        //System.out.println(query2);
        try(
                Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
                Statement stmt= conn.createStatement();
        ){
            stmt.executeUpdate(query2);
            System.out.println("Usuario eliminado xd");
            System.out.println("----------------------------------------------");
        }catch (Exception el){
            throw new RuntimeException(el);
        }
    }

    public static void actualizar(String edad){
        String query2 = "UPDATE Estudiantes1 set edad = '"+25+"'"+ "where id="+'"'+ "122" +'"' ;
        //System.out.println(query2);
        try(
                Connection conn = DriverManager.getConnection(DB_URL,USER,PASS); //Esencial para la conección
                Statement stmt= conn.createStatement();
        ){
            stmt.executeUpdate(query2);
            System.out.println("edad Actualizada ");
            System.out.println("----------------------------------------------");
        }catch (Exception el){
            throw new RuntimeException(el);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ingreso");
        frame.setContentPane(new Ingreso().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(500,300));
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }

}

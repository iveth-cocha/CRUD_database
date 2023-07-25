import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class query {
    private JButton queryButton;
    private JPanel query;
    private JTextArea Datos1;
    static final String DB_URL="jdbc:mysql://localhost/Universidad";
    static final String USER="root";
    static final String PASS="root_bas3";
    static final String QUERY="Select * From Estudiantes";


    public query(){
        queryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try(
                        Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
                        Statement stmt= conn.createStatement();
                        ResultSet rs= stmt.executeQuery(QUERY);
                ){
                    while (rs.next()){
                        System.out.println("\n----------------------------------------------");
                        System.out.println("Nombre: "+rs.getString("Nombre"));
                        System.out.println("id: "+rs.getInt("id"));
                        System.out.println("Ciudad: "+rs.getString("Ciudad"));
                        System.out.println("Edad: "+rs.getInt("Edad"));
                        System.out.println("Cédula: "+rs.getInt("Cedula"));

                        Datos1.append("Nombre: "+rs.getString("Nombre")+"\n"+" id: "+rs.getInt("id")+"\n"
                                +" Ciudad: "+rs.getString("Ciudad")+"\n"+" Edad: "+rs.getInt("Edad")+"\n"+
                                " Cédula: "+rs.getInt("Cedula")+"\n"+ "---------------------------------\n");


                    }
                }catch (Exception ex){
                    throw new RuntimeException(ex);
                }

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("query");
        frame.setContentPane(new query().query);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

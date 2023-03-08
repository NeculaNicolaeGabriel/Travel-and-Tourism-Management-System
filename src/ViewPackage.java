import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class ViewPackage extends JFrame implements ActionListener {

    JButton back;

    ViewPackage(String username){
        setBounds(480,200,900,450);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel text = new JLabel("VIEW PACKAGE DETAILS");
        text.setFont(new Font("Tahoma",Font.BOLD,20));
        text.setBounds(60,0,300,30);
        add(text);


        JLabel lblusername = new JLabel("Username");
        lblusername.setBounds(30,50,150,25);
        add(lblusername);

        JLabel labelusername = new JLabel();
        labelusername.setBounds(220,50,150,25);
        add(labelusername);


        JLabel blid = new JLabel("Package");
        blid.setBounds(30,90,150,25);
        add(blid);

        JLabel labelpackage = new JLabel();
        labelpackage.setBounds(220,90,150,25);
        add(labelpackage);


        JLabel lblperson = new JLabel("Total Persons");
        lblperson.setBounds(30,130,150,25);
        add(lblperson);

        JLabel labelperson= new JLabel();
        labelperson.setBounds(220,130,150,25);
        add(labelperson);


        JLabel lblid = new JLabel("Id");
        lblid.setBounds(30,170,150,25);
        add(lblid);

        JLabel labelid= new JLabel();
        labelid.setBounds(220,170,150,25);
        add(labelid);


        JLabel lblnumber = new JLabel("Number");
        lblnumber.setBounds(30,210,150,25);
        add(lblnumber);

        JLabel labelnumber= new JLabel();
        labelnumber.setBounds(220,210,150,25);
        add(labelnumber);


        JLabel lblphone= new JLabel("Phone");
        lblphone.setBounds(30,250,150,25);
        add(lblphone);

        JLabel labelphone= new JLabel();
        labelphone.setBounds(220,250,150,25);
        add(labelphone);



        JLabel lblprice = new JLabel("Price");
        lblprice.setBounds(30,290,150,25);
        add(lblprice);

        JLabel labelprice = new JLabel();
        labelprice.setBounds(220,290,150,25);
        add(labelprice);



        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(130,360,100,25);
        back.addActionListener(this);
        add(back);


        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/bookedDetails.jpg"));
        Image i2 = i1.getImage().getScaledInstance(500,400,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image1 = new JLabel(i3);
        image1.setBounds(450,20,500,400);
        add(image1);





        try {
            Conn conn =new Conn();
            String query = "select * from bookpackage where username = '"+username+"'";
            ResultSet rs = conn.s.executeQuery(query);

            while (rs.next()){
                labelusername.setText(rs.getString("username"));
                labelid.setText(rs.getString("id"));
                labelnumber.setText(rs.getString("number"));
                labelpackage.setText(rs.getString("package"));
                labelprice.setText(rs.getString("price"));
                labelphone.setText(rs.getString("phone"));
                labelperson.setText(rs.getString("persons"));


            }

        }catch (Exception e){

        }



        setVisible(true);


    }


    @Override
    public void actionPerformed(ActionEvent e) {
    }





        public static void main(String[] args) {
        new ViewPackage("Gabi");
    }



}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class BookPackage extends JFrame implements ActionListener {

    Choice cpackage;
    JTextField tfperson;
    String username;
    JLabel labelusername,labelid,labelnumber,labelphone,labeltotal;
    JButton checkprice, bookpackage,back;

    BookPackage(String username) {
        this.username= username;
        setBounds(350, 200, 1100, 500);
        setLayout(null);



        JLabel text = new JLabel("BOOK PACKAGE");
        text.setBounds(100,10,200,30);
        text.setFont(new Font("Tahoma",Font.BOLD,20));
        getContentPane().setBackground(Color.WHITE);
        add(text);



        JLabel lblusername = new JLabel("Username");
        text.setFont(new Font("Tahoma",Font.PLAIN,16));
        lblusername.setBounds(40,70,100,20);
        add(lblusername);

        labelusername = new JLabel();
        text.setFont(new Font("Tahoma",Font.PLAIN,16));
        labelusername.setBounds(250,70,200,20);
        add(labelusername);


        JLabel bpackage = new JLabel("Select Package");
        text.setFont(new Font("Tahoma",Font.PLAIN,16));
        bpackage.setBounds(40,110,150,25);
        add(bpackage);

        cpackage = new Choice();
        cpackage.add("Gold Package");
        cpackage.add("Silver Package");
        cpackage.add("Bronze Package");
        cpackage.setBounds(250,110,200,20);
        add(cpackage);


        JLabel lblperson = new JLabel("Total Persons");
        text.setFont(new Font("Tahoma",Font.PLAIN,16));
        lblperson.setBounds(40,150,150,25);
        add(lblperson);

        tfperson = new JTextField();
        tfperson.setBounds(250,150,200,25);
        add(tfperson);

        JLabel blid= new JLabel("id");
        blid.setFont(new Font("Tahoma",Font.PLAIN,16));
        blid.setBounds(40,190,150,25);
        add(blid);

       labelid= new JLabel();
        labelid.setBounds(250,190,200,25);
        add(labelid);

        JLabel lblnumber = new JLabel("Number");
        lblnumber.setFont(new Font("Tahoma",Font.PLAIN,16));
        lblnumber.setBounds(40,230,150,25);
        add(lblnumber);

        labelnumber = new JLabel();
        labelnumber.setBounds(250,230,150,25);
        add(labelnumber);


        JLabel blphone= new JLabel("Phone");
        blphone.setFont(new Font("Tahoma",Font.PLAIN,16));
        blphone.setBounds(40,270,150,25);
        add(blphone);

        labelphone= new JLabel();
        labelphone.setBounds(250,270,200,25);
        add(labelphone);

        JLabel lbltotal = new JLabel("Total Price");
        lbltotal.setFont(new Font("Tahoma",Font.PLAIN,16));
        lbltotal.setBounds(40,310,150,25);
        add(lbltotal);

        labeltotal = new JLabel();
        labeltotal.setBounds(250,310,150,25);
        add(labeltotal);


        try {
            Conn conn =new Conn();
            String query = "select * from customer where username = '"+username+"'";
            ResultSet rs = conn.s.executeQuery(query);

            while (rs.next()){
                labelusername.setText(rs.getString("username"));
                labelid.setText(rs.getString("id"));
                labelnumber.setText(rs.getString("number"));
                labelphone.setText(rs.getString("phone"));
            }

        }catch (Exception e){

        }
        checkprice =new JButton("Check Price");
        checkprice.setBackground(Color.BLACK);
        checkprice.setForeground(Color.WHITE);
        checkprice.setBounds(60,380,120,25);
        checkprice.addActionListener(this);
        add(checkprice);

        bookpackage =new JButton("Book Package");
        bookpackage.setBackground(Color.BLACK);
        bookpackage.setForeground(Color.WHITE);
        bookpackage.setBounds(200,380,120,25);
        bookpackage.addActionListener(this);
        add(bookpackage);


        back =new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(340,380,120,25);
        back.addActionListener(this);
        add(back);

        ImageIcon i1= new ImageIcon(ClassLoader.getSystemResource("icons/bookpackage.jpg"));
        Image i2 = i1.getImage().getScaledInstance(500,300,Image.SCALE_DEFAULT);
        ImageIcon i3= new ImageIcon(i2);
        JLabel l12=new JLabel(i3);
        l12.setBounds(550,50,500,300);
        add(l12);



        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource()==checkprice){
            String pack =cpackage.getSelectedItem();
            int cost=0;
            if (pack.equals("Gold Package")){
                cost+=5000;
            } else if (pack.equals("Silver Package")) {
                cost+=3500;
            }else {
                cost += 2500;
            }
            int persons= Integer.parseInt(tfperson.getText());
            cost *=persons;
            labeltotal.setText( cost+" $");
            }else if (ae.getSource()==bookpackage){
            try {
                Conn c = new Conn();
                c.s.executeUpdate("insert into bookpackage values ('"+labelusername.getText()+"','"+cpackage.getSelectedItem()+"','"+tfperson.getText()+"','"+labelid.getText()+"','"+labelnumber.getText()+"','"+labelphone.getText()+"','"+labeltotal.getText()+"')");
                JOptionPane.showMessageDialog(null,"Package Booked Successfully");
                setVisible(false);
            }catch (Exception e){
                e.printStackTrace();
            }
        }else {

            setVisible(false);
        }
        }





    public static void main(String[] args) {
        new BookPackage("Gabi");
    }


}

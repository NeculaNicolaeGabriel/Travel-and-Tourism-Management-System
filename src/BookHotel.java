import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class BookHotel extends JFrame implements ActionListener {



    Choice chotel,cac, cffood;
    JTextField tfperson,tfdays;
    String username;
    JLabel labelusername,labelid,labelnumber,labelphone,labelprice;
    JButton checkprice, bookpackage,back;

    BookHotel(String username) {
        this.username= username;
        setBounds(350, 200, 1100, 600);
        setLayout(null);


        JLabel text = new JLabel("BOOK HOTEL");
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


        JLabel bpackage = new JLabel("Select Hotel");
        text.setFont(new Font("Tahoma",Font.PLAIN,16));
        bpackage.setBounds(40,110,150,25);
        add(bpackage);


        JLabel lblperson = new JLabel("Total Persons");
        text.setFont(new Font("Tahoma",Font.PLAIN,16));
        lblperson.setBounds(40,150,150,25);
        add(lblperson);

        tfperson = new JTextField();
        tfperson.setBounds(250,150,200,25);
        add(tfperson);

        chotel = new Choice();
        chotel.setBounds(250,110,200,20);
        add(chotel);

        try {
            Conn c=new Conn();
            ResultSet rs = c.s.executeQuery("select * from hotel");
            while(rs.next()){
                chotel.add(rs.getString("name"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }


        JLabel lbldays = new JLabel("No. of Day");
        lbldays.setFont(new Font("Tahoma",Font.PLAIN,16));
        lbldays.setBounds(40,190,150,25);
        add(lbldays);

        tfdays = new JTextField("1");
        tfdays.setBounds(250,190,200,25);
        add( tfdays);

        JLabel lblac = new JLabel("AC/Non-AC");
        lblac.setFont(new Font("Tahoma",Font.PLAIN,16));
        lblac.setBounds(40,230,150,25);
        add(lblac);

        cac=new Choice();
        cac.add("AC");
        cac.add("Non-AC");
        cac.setBounds(250,230,200,20);
        add(cac);


        JLabel lblfood = new JLabel("Food Included");
        lblfood.setFont(new Font("Tahoma",Font.PLAIN,16));
        lblfood.setBounds(40,270,150,25);
        add(lblfood);

        cffood=new Choice();
        cffood.add("Yes");
        cffood.add("No");
        cffood.setBounds(250,270,200,20);
        add(cffood);

        JLabel blid= new JLabel("id");
        blid.setFont(new Font("Tahoma",Font.PLAIN,16));
        blid.setBounds(40,310,150,25);
        add(blid);

        labelid= new JLabel();
        labelid.setBounds(250,310,200,25);
        add(labelid);


        JLabel lblnumber = new JLabel("Number");
        lblnumber.setFont(new Font("Tahoma",Font.PLAIN,16));
        lblnumber.setBounds(40,350,150,25);
        add(lblnumber);

        labelnumber = new JLabel();
        labelnumber.setBounds(250,350,150,25);
        add(labelnumber);


        JLabel blphone= new JLabel("Phone");
        blphone.setFont(new Font("Tahoma",Font.PLAIN,16));
        blphone.setBounds(40,392,150,25);
        add(blphone);

        labelphone= new JLabel();
        labelphone.setBounds(250,390,200,25);
        add(labelphone);

        JLabel lbltotal = new JLabel("Total Price");
        lbltotal.setFont(new Font("Tahoma",Font.PLAIN,16));
        lbltotal.setBounds(40,430,150,25);
        add(lbltotal);

        labelprice = new JLabel();
        labelprice.setBounds(250,430,150,25);
        add(labelprice);


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
        checkprice.setBounds(60,490,120,25);
        checkprice.addActionListener(this);
        add(checkprice);

        bookpackage =new JButton("Book Hotel");
        bookpackage.setBackground(Color.BLACK);
        bookpackage.setForeground(Color.WHITE);
        bookpackage.setBounds(200,490,120,25);
        bookpackage.addActionListener(this);
        add(bookpackage);


        back =new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(340,490,120,25);
        back.addActionListener(this);
        add(back);

        ImageIcon i1= new ImageIcon(ClassLoader.getSystemResource("icons/book.jpg"));
        Image i2 = i1.getImage().getScaledInstance(600,300,Image.SCALE_DEFAULT);
        ImageIcon i3= new ImageIcon(i2);
        JLabel l12=new JLabel(i3);
        l12.setBounds(550,50,600,300);
        add(l12);



        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource()==checkprice) {
            try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from hotel where name='" + chotel.getSelectedItem() + "'");
            while (rs.next()){
                int cost=Integer.parseInt(rs.getString("costperperson"));
                int food=Integer.parseInt(rs.getString("foodincluded"));
                int ac=Integer.parseInt(rs.getString("acrom"));

                int perons= Integer.parseInt(tfperson.getText());
                int days=Integer.parseInt(tfdays.getText());

                String acselect = cac.getSelectedItem();
                String foodselect= cffood.getSelectedItem();

                if (perons *days >0){
                    int total =0;
                    total += acselect.equals("AC")? ac:0;
                    total +=foodselect.equals("Yes")? food :0;
                    total+=cost;
                    total=total*perons*days;
                    labelprice.setText(total+" $");
                }else {
                    JOptionPane.showMessageDialog(null,"Please enter a valid number");
                }
            }
        }catch(Exception e){
                e.printStackTrace();

        }
        }else if (ae.getSource()==bookpackage){
            try {
                Conn c = new Conn();
                c.s.executeUpdate("insert into bookhotel values ('" + labelusername.getText() + "','" + chotel.getSelectedItem() + "','" + tfperson.getText() + "','" + tfdays.getText() + "','" + cac.getSelectedItem() + "','" + cffood.getSelectedItem().toString() + "','" + labelid.getText() + "','" + labelnumber.getText() + "','" + labelphone.getText() + "','" + labelprice.getText() + "')");

                JOptionPane.showMessageDialog(null,"Hotel Booked Successfully");
                setVisible(false);
            }catch (Exception e){
                e.printStackTrace();
            }
        }else {

            setVisible(false);
        }
    }






    public static void main(String[] args) {
        new BookHotel("Gabi");
    }
}

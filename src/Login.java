import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.xml.transform.Result;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {

    JButton login,singup,password;
    JTextField tfusername,tfpassword;
   Login() {
       setSize(900, 400);
       setLocation(350, 200);
       setLayout(null);

       getContentPane().setBackground(Color.WHITE);

       JPanel pl=new JPanel();
       pl.setBackground(new Color(131,193,233));
       pl.setBounds(0,0,400,400);
       pl.setLayout(null);
       add(pl);

       ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/login.png"));
       Image i2= i1.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT);
       ImageIcon i3 =new ImageIcon(i2);
       JLabel image = new JLabel(i3);
       image.setBounds(100,120,200,200);
       pl.add(image);

       JPanel p2 = new JPanel();
       p2.setLayout(null);
       p2.setBounds(400,30,450,300);
       add(p2);

       JLabel blusername = new JLabel("Username");
       blusername.setBounds(60,20,100,25);
       blusername.setFont(new Font("SAN SERIF",Font.PLAIN,20));

       tfusername = new JTextField();
       tfusername.setBounds(60,60,300,30);
       tfusername.setBorder(BorderFactory.createEmptyBorder());
       p2.add(tfusername);
       p2.add(blusername);


       JLabel blpassword = new JLabel("Password");
       blpassword.setBounds(60,110,100,25);
       blpassword.setFont(new Font("SAN SERIF",Font.PLAIN,20));
       p2.add(blpassword);

       tfpassword = new JTextField();
       tfpassword.setBounds(60,150,300,30);
       tfpassword.setBorder(BorderFactory.createEmptyBorder());
       p2.add(tfpassword);

       login = new JButton("Login");
       login.setBounds(60,200,130,30);
       login.setBackground(new Color(133,193,233));
       login.setForeground(Color.WHITE);
       login.setBorder(new LineBorder(new Color(133,193,233)));
       login.addActionListener(this);
       p2.add(login);


       singup = new JButton("Singup");
       singup.setBounds(230,200,133,30);
       singup.setBackground(new Color(133,193,233));
       singup.setForeground(Color.WHITE);
       singup.setBorder(new LineBorder(new Color(133,193,233)));
       singup.addActionListener(this);
       p2.add(singup);

       password = new JButton("Forget Password");
       password.setBounds(130,250,130,30);
       password.setBackground(new Color(133,193,233));
       password.setForeground(Color.WHITE);
       password.setBorder(new LineBorder(new Color(133,193,233)));
       password.addActionListener(this);
       p2.add(password);

       JLabel text = new JLabel("Trouble in login...");
       text.setBounds(300,250,100,20);
       text.setForeground(Color.RED);
       p2.add(text);




       setVisible(true);
   }


    @Override
    public void actionPerformed(ActionEvent ae) {
       if (ae.getSource()==login){
           try{
               String username = tfusername.getText();
               String password = tfpassword.getText();

               String query="select * from account where username = '"+username+"'AND password ='"+password+"'";
               Conn c = new Conn();
               ResultSet rs =c.s.executeQuery(query);

               if(rs.next()){
                   setVisible(false);
                   new Loading(username);
               }else {
                   JOptionPane.showMessageDialog(null,"Incorrect username or password");

               }
           }catch (Exception e) {
           e.printStackTrace();

           }
       } else if (ae.getSource()==singup) {
           setVisible(false);
           new Signup();
       }else {
           setVisible(false);
           new ForgetPassword();

       }

    }



    public static void main(String[] args) {
    new Login();
    }

}

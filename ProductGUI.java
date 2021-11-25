package Product_GUI;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.lang.*;

public class ProductGUI extends JFrame implements Serializable{

    //variables
    private int productID;
    private String productName;
    private double productPrice;
    private Product newProduct;

    //arraylist to hold product information
    ArrayList<Product> productList = new ArrayList<>();

    //add panels
    private JPanel a = new JPanel();
    private JPanel b = new JPanel();
    private JPanel c = new JPanel();
    private JPanel d = new JPanel();
    private JPanel e = new JPanel();


    //add text fields
    private JTextField aTextField = new JTextField("", 20);
    private JTextField bTextField = new JTextField("",20);
    private JTextField cTextField = new JTextField("", 20);

    //add labels for textfields
    private JLabel  aLabel = new JLabel("Product ID: ");
    private JLabel bLabel = new JLabel("Product Name: ");
    private JLabel cLabel = new JLabel("Product Price(Rands):  ");
    private JLabel dLabel = new JLabel("Product Display: ");

    //buttons
    private JButton submitButton = new JButton("Submit");
    private JButton searchButton = new JButton("Search");

    //display area
    private JTextArea productDisplay = new JTextArea(20, 15);

    public ProductGUI() {

        setTitle("Product GUI");
        setSize(700, 500);
        setVisible(true);
        setResizable(true);
        setLocationRelativeTo(null);

        setLayout(new FlowLayout());

        customHandler handler = new customHandler();

//adding panels
        this.add(a);
        getContentPane().add(a);
        this.add(b);
        getContentPane().add(b);
        this.add(c);
        getContentPane().add(c);
        this.add(d);
        getContentPane().add(d);
        this.add(e);
        getContentPane().add(e);

//adding components to the panels
        a.setLayout(new GridLayout(1,3));
        a.add(aLabel);
        a.add(aTextField);
        aLabel.setSize(20, 20);
        aTextField.setSize(150, 30);

        b.setLayout(new GridLayout(1,3));
        b.add(bLabel);
        b.add(bTextField);
        bLabel.setSize(20, 20);

        c.setLayout(new GridLayout(1,3));
        c.add(cLabel);
        c.add(cTextField);
        cLabel.setSize(20, 20);
        cTextField.setSize(150, 30);

        ButtonGroup bg = new ButtonGroup();
        bg.add(submitButton);
        bg.add(searchButton);


        d.setLayout(new GridLayout(1, 2));
        d.add(submitButton);
        d.add(searchButton);


        e.setLayout(new FlowLayout());
        e.add(dLabel);
        e.add(productDisplay);
        productDisplay.setEditable((false));
//productDisplay.

//add listeners for the button
        searchButton.addActionListener(handler);
        submitButton.addActionListener(handler);




    }

    public class customHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent change) {
// TODO Auto-generated method stub



            if(change.getSource() == submitButton){

                productID = Integer.parseInt(aTextField.getText());
                productName = bTextField.getText();
                productPrice = Double.parseDouble(cTextField.getText());

                newProduct = new Product(productID, productName, productPrice);


                System.out.println(newProduct.toString());

                try {
                    productList.add(newProduct);
                    File productFile = new File("newProduct.txt");

                    FileOutputStream fos = new FileOutputStream(productFile);

                    ObjectOutputStream out = new ObjectOutputStream(fos);
                    out.writeObject(productList);
                    out.close();
                }catch(FileNotFoundException ex) {

                    Logger.getLogger(ProductGUI.class.getName()).log(Level.SEVERE, null, ex);

                } catch(IOException ex) {
                    Logger.getLogger(ProductGUI.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                }

                aTextField.setText("");
                bTextField.setText("R");
                cTextField.setText("");

                try {
                    FileInputStream fileIn = new FileInputStream("newProduct.txt");
                    ObjectInputStream in = new ObjectInputStream(fileIn);


                    productList= (ArrayList<Product>) in.readObject();

                    for(int i = 0 ; i < productList.size(); i++){
                        if(i==0){
                            in.read();
                            productDisplay.setText(productList.get(i).toString());

                        }
                        else {
                            productDisplay.setText(productDisplay.getText() + productList.get(i));

                        }
                    }
                }catch(Exception e) {
                    e.printStackTrace();
                }
            }

            if(change.getSource() == searchButton) {

                try {


                    FileInputStream fileIn = new FileInputStream("newProduct.txt");
                    ObjectInputStream in = new ObjectInputStream(fileIn);

                    productList= (ArrayList<Product>) in.readObject();


                    JFrame f = new JFrame();


                    int  userInput = Integer.parseInt(JOptionPane.showInputDialog("Enter product id:", JOptionPane.INFORMATION_MESSAGE ));

                    if(userInput == productID) {
                        JOptionPane.showMessageDialog(f, productList);
                    }else {
                        JOptionPane.showMessageDialog(f, "Product does not exist");
                    }









                }catch(Exception e) {
                    e.printStackTrace();
                }

            }
        }

    }
}
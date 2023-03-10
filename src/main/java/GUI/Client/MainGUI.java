/*
 * Created by JFormDesigner on Fri Mar 10 17:10:28 ICT 2023
 */

package GUI.Client;

import javax.swing.border.*;

import Utils.Helper;
import com.formdev.flatlaf.ui.FlatDropShadowBorder;
import model.Account;

import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.*;

/**
 * @author Laffy
 */
public class MainGUI extends JFrame {
    private Account acc = Account.builder().id(1).username("Laffy").balance(12000).build();
    public MainGUI() {
        this.setUndecorated(true);
        initComponents();
        this.setSize(300,600);
        // frame location top:0, left: screen width - frame width
        this.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width - this.getWidth()+2, 0);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Already there
        FlatDropShadowBorder shadow = new FlatDropShadowBorder();
        panel2.setBorder(BorderFactory.createEmptyBorder(10,50,10,50));
        //set margin for panel2
        label7.setText(acc.getUsername());
        label7.putClientProperty( "FlatLaf.styleClass", "h2" );
         label8.setIcon(Helper.getIcon("/icons/supportbanner.png",280, 150));
         AtomicInteger sec = new AtomicInteger();
        Timer timer = new Timer(1000, e -> {
            sec.getAndIncrement();
            if (textField2.getText().contains(":")){
                textField2.setText(Helper.toHHMM(sec.get(), false));
            }else{
                textField2.setText(Helper.toHHMM(sec.get(), true));
            }
        });
        timer.start();

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        panel14 = new JPanel();
        label8 = new JLabel();
        panel1 = new JPanel();
        panel11 = new JPanel();
        panel2 = new JPanel();
        label1 = new JLabel();
        panel3 = new JPanel();
        textField1 = new JTextField();
        label2 = new JLabel();
        panel4 = new JPanel();
        textField2 = new JTextField();
        label3 = new JLabel();
        panel5 = new JPanel();
        textField3 = new JTextField();
        label4 = new JLabel();
        panel6 = new JPanel();
        textField4 = new JTextField();
        label5 = new JLabel();
        panel7 = new JPanel();
        textField5 = new JTextField();
        label6 = new JLabel();
        panel8 = new JPanel();
        textField6 = new JTextField();
        panel9 = new JPanel();
        panel10 = new JPanel();
        panel12 = new JPanel();
        label7 = new JLabel();
        panel13 = new JPanel();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        button4 = new JButton();
        button5 = new JButton();
        button6 = new JButton();

        //======== this ========
        setMinimumSize(new Dimension(14, 14));
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== panel14 ========
        {
            panel14.setBackground(new Color(0xebf8ff));
            panel14.setLayout(new FlowLayout());

            //---- label8 ----
            label8.setIcon(null);
            panel14.add(label8);
        }
        contentPane.add(panel14, BorderLayout.SOUTH);

        //======== panel1 ========
        {
            panel1.setBackground(new Color(0xedf2f7));
            panel1.setLayout(new BorderLayout());

            //======== panel11 ========
            {
                panel11.setBackground(new Color(0xedf2f7));
                panel11.setLayout(new FlowLayout());

                //======== panel2 ========
                {
                    panel2.setBackground(Color.white);
                    panel2.setBorder(new EmptyBorder(5, 5, 5, 5));
                    panel2.setLayout(new GridBagLayout());
                    ((GridBagLayout)panel2.getLayout()).columnWidths = new int[] {0, 0, 0};
                    ((GridBagLayout)panel2.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0};
                    ((GridBagLayout)panel2.getLayout()).columnWeights = new double[] {1.0, 1.0, 1.0E-4};
                    ((GridBagLayout)panel2.getLayout()).rowWeights = new double[] {1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0E-4};

                    //---- label1 ----
                    label1.setText("T\u1ed5ng th\u1eddi gian:");
                    panel2.add(label1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));

                    //======== panel3 ========
                    {
                        panel3.setBackground(Color.white);
                        panel3.setLayout(new FlowLayout(FlowLayout.RIGHT));

                        //---- textField1 ----
                        textField1.setMinimumSize(new Dimension(60, 22));
                        textField1.setPreferredSize(new Dimension(70, 22));
                        textField1.setFocusable(false);
                        textField1.setText("00:00");
                        panel3.add(textField1);
                    }
                    panel2.add(panel3, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));

                    //---- label2 ----
                    label2.setText("Th\u1eddi gian s\u1eed d\u1ee5ng:");
                    panel2.add(label2, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));

                    //======== panel4 ========
                    {
                        panel4.setBackground(Color.white);
                        panel4.setLayout(new FlowLayout(FlowLayout.RIGHT));

                        //---- textField2 ----
                        textField2.setMinimumSize(new Dimension(60, 22));
                        textField2.setPreferredSize(new Dimension(70, 22));
                        textField2.setFocusable(false);
                        textField2.setText("00:00");
                        panel4.add(textField2);
                    }
                    panel2.add(panel4, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));

                    //---- label3 ----
                    label3.setText("Th\u1eddi gian c\u00f2n l\u1ea1i:");
                    panel2.add(label3, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));

                    //======== panel5 ========
                    {
                        panel5.setBackground(Color.white);
                        panel5.setLayout(new FlowLayout(FlowLayout.RIGHT));

                        //---- textField3 ----
                        textField3.setMinimumSize(new Dimension(60, 22));
                        textField3.setPreferredSize(new Dimension(70, 22));
                        textField3.setFocusable(false);
                        textField3.setText("00:00");
                        panel5.add(textField3);
                    }
                    panel2.add(panel5, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));

                    //---- label4 ----
                    label4.setText("T\u1ed5ng chi ph\u00ed:");
                    panel2.add(label4, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));

                    //======== panel6 ========
                    {
                        panel6.setBackground(Color.white);
                        panel6.setLayout(new FlowLayout(FlowLayout.RIGHT));

                        //---- textField4 ----
                        textField4.setMinimumSize(new Dimension(60, 22));
                        textField4.setPreferredSize(new Dimension(70, 22));
                        textField4.setFocusable(false);
                        textField4.setText("00:00");
                        panel6.add(textField4);
                    }
                    panel2.add(panel6, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));

                    //---- label5 ----
                    label5.setText("Chi ph\u00ed d\u1ecbch v\u1ee5:");
                    panel2.add(label5, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));

                    //======== panel7 ========
                    {
                        panel7.setBackground(Color.white);
                        panel7.setLayout(new FlowLayout(FlowLayout.RIGHT));

                        //---- textField5 ----
                        textField5.setMinimumSize(new Dimension(60, 22));
                        textField5.setPreferredSize(new Dimension(70, 22));
                        textField5.setFocusable(false);
                        textField5.setText("00:00");
                        panel7.add(textField5);
                    }
                    panel2.add(panel7, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));

                    //---- label6 ----
                    label6.setText("S\u1ed1 d\u01b0 t\u00e0i kho\u1ea3n:");
                    panel2.add(label6, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));

                    //======== panel8 ========
                    {
                        panel8.setBackground(Color.white);
                        panel8.setLayout(new FlowLayout(FlowLayout.RIGHT));

                        //---- textField6 ----
                        textField6.setMinimumSize(new Dimension(60, 22));
                        textField6.setPreferredSize(new Dimension(70, 22));
                        textField6.setFocusable(false);
                        textField6.setText("00:00");
                        panel8.add(textField6);
                    }
                    panel2.add(panel8, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));
                }
                panel11.add(panel2);
            }
            panel1.add(panel11, BorderLayout.CENTER);

            //======== panel9 ========
            {
                panel9.setBackground(new Color(0xedf2f7));
                panel9.setPreferredSize(new Dimension(30, 10));
                panel9.setLayout(new FlowLayout());
            }
            panel1.add(panel9, BorderLayout.WEST);

            //======== panel10 ========
            {
                panel10.setBackground(new Color(0xedf2f7));
                panel10.setMinimumSize(new Dimension(30, 10));
                panel10.setPreferredSize(new Dimension(30, 10));
                panel10.setLayout(new FlowLayout());
            }
            panel1.add(panel10, BorderLayout.EAST);

            //======== panel12 ========
            {
                panel12.setMinimumSize(new Dimension(10, 15));
                panel12.setBackground(new Color(0xebf8ff));
                panel12.setBorder(new EmptyBorder(5, 5, 5, 5));
                panel12.setLayout(new BorderLayout());

                //---- label7 ----
                label7.setText("text");
                label7.setForeground(new Color(0x68d391));
                label7.setFont(new Font("Nunito SemiBold", Font.PLAIN, 14));
                panel12.add(label7, BorderLayout.WEST);
            }
            panel1.add(panel12, BorderLayout.NORTH);
        }
        contentPane.add(panel1, BorderLayout.NORTH);

        //======== panel13 ========
        {
            panel13.setBackground(new Color(0xebf8ff));
            panel13.setLayout(new GridLayout(2, 3));

            //---- button1 ----
            button1.setText("\ntext");
            button1.setIcon(new ImageIcon(getClass().getResource("/icons/message.png")));
            button1.setVerticalTextPosition(SwingConstants.BOTTOM);
            button1.setBackground(new Color(0x00ffffff, true));
            button1.setSelectedIcon(new ImageIcon(getClass().getResource("/icons/message.png")));
            button1.setHorizontalTextPosition(SwingConstants.CENTER);
            panel13.add(button1);

            //---- button2 ----
            button2.setText("text");
            button2.setHorizontalTextPosition(SwingConstants.CENTER);
            button2.setVerticalTextPosition(SwingConstants.BOTTOM);
            button2.setIcon(new ImageIcon(getClass().getResource("/icons/logout.png")));
            button2.setBackground(new Color(0x00ffffff, true));
            panel13.add(button2);

            //---- button3 ----
            button3.setText("text");
            button3.setHorizontalTextPosition(SwingConstants.CENTER);
            button3.setVerticalTextPosition(SwingConstants.BOTTOM);
            button3.setIcon(new ImageIcon(getClass().getResource("/icons/key.png")));
            button3.setBackground(new Color(0x00ffffff, true));
            panel13.add(button3);

            //---- button4 ----
            button4.setText("text");
            button4.setHorizontalTextPosition(SwingConstants.CENTER);
            button4.setVerticalTextPosition(SwingConstants.BOTTOM);
            button4.setIcon(new ImageIcon(getClass().getResource("/icons/food.png")));
            button4.setBackground(new Color(0x00ffffff, true));
            panel13.add(button4);

            //---- button5 ----
            button5.setText("text");
            button5.setHorizontalTextPosition(SwingConstants.CENTER);
            button5.setVerticalTextPosition(SwingConstants.BOTTOM);
            button5.setIcon(new ImageIcon(getClass().getResource("/icons/lock.png")));
            button5.setBackground(new Color(0x00ffffff, true));
            panel13.add(button5);

            //---- button6 ----
            button6.setText("text");
            button6.setHorizontalTextPosition(SwingConstants.CENTER);
            button6.setVerticalTextPosition(SwingConstants.BOTTOM);
            button6.setIcon(new ImageIcon(getClass().getResource("/icons/unity.png")));
            button6.setBackground(new Color(0x00ffffff, true));
            panel13.add(button6);
        }
        contentPane.add(panel13, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel panel14;
    private JLabel label8;
    private JPanel panel1;
    private JPanel panel11;
    private JPanel panel2;
    private JLabel label1;
    private JPanel panel3;
    private JTextField textField1;
    private JLabel label2;
    private JPanel panel4;
    private JTextField textField2;
    private JLabel label3;
    private JPanel panel5;
    private JTextField textField3;
    private JLabel label4;
    private JPanel panel6;
    private JTextField textField4;
    private JLabel label5;
    private JPanel panel7;
    private JTextField textField5;
    private JLabel label6;
    private JPanel panel8;
    private JTextField textField6;
    private JPanel panel9;
    private JPanel panel10;
    private JPanel panel12;
    private JLabel label7;
    private JPanel panel13;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
    public static void main(String[] args) {
        Helper.initUI();
        MainGUI mainGUI = new MainGUI();
    }
}
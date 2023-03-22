/*
 * Created by JFormDesigner on Wed Mar 22 21:45:12 ICT 2023
 */

package GUI.Server.Account;

import javax.swing.border.*;

import GUI.Blur;
import GUI.Server.MainUI;
import model.Account;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

/**
 * @author HuuHoang
 */
public class AccountDetailGUI extends JDialog {
    private Account account;
    private Mode mode;
   public enum Mode {
         EDIT, READ_ONLY,CREATE
    }
    public AccountDetailGUI(Window owner, Account account, Mode mode) {
        initComponents();
        reDesign();
        initEvent();
        setModal(true);
        setModalityType(ModalityType.APPLICATION_MODAL);
    }

    private void initEvent() {
       cancel.addActionListener(e->{
           dispose();
       });
    }

    public AccountDetailGUI(Window owner) {
        this(owner,Account.builder().username("").password("").build(), Mode.CREATE);
    }
    public AccountDetailGUI(Window owner, Account account) {
        this(owner,account, Mode.EDIT);
    }
    private void reDesign() {
    }




    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        panel1 = new JPanel();
        label1 = new JLabel();
        panel2 = new JPanel();
        label2 = new JLabel();
        textField1 = new JTextField();
        panel3 = new JPanel();
        label3 = new JLabel();
        textField2 = new JTextField();
        panel4 = new JPanel();
        label4 = new JLabel();
        textField3 = new JTextField();
        panel5 = new JPanel();
        label5 = new JLabel();
        textField4 = new JTextField();
        panel6 = new JPanel();
        cancel = new JButton();
        ok = new JButton();

        //======== this ========
        setPreferredSize(new Dimension(420, 450));
        var contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        //======== panel1 ========
        {
            panel1.setLayout(new FlowLayout());

            //---- label1 ----
            label1.setText("Ch\u1ec9nh s\u1eeda t\u00e0i kho\u1ea3n");
            label1.setFont(new Font("Nunito Medium", Font.BOLD, 20));
            panel1.add(label1);
        }
        contentPane.add(panel1);

        //======== panel2 ========
        {
            panel2.setBorder(new EmptyBorder(2, 10, 0, 10));
            panel2.setLayout(new FlowLayout(FlowLayout.LEFT));

            //---- label2 ----
            label2.setText("T\u00ean \u0111\u0103ng nh\u1eadp:");
            label2.setFont(new Font("Nunito Medium", Font.PLAIN, 14));
            panel2.add(label2);

            //---- textField1 ----
            textField1.setPreferredSize(new Dimension(370, 30));
            textField1.setFont(new Font("Nunito", Font.PLAIN, 14));
            panel2.add(textField1);
        }
        contentPane.add(panel2);

        //======== panel3 ========
        {
            panel3.setBorder(new EmptyBorder(2, 10, 0, 10));
            panel3.setLayout(new FlowLayout(FlowLayout.LEFT));

            //---- label3 ----
            label3.setText("T\u00ean \u0111\u0103ng nh\u1eadp:");
            label3.setFont(new Font("Nunito Medium", Font.PLAIN, 14));
            panel3.add(label3);

            //---- textField2 ----
            textField2.setPreferredSize(new Dimension(370, 30));
            textField2.setFont(new Font("Nunito", Font.PLAIN, 14));
            panel3.add(textField2);
        }
        contentPane.add(panel3);

        //======== panel4 ========
        {
            panel4.setBorder(new EmptyBorder(2, 10, 0, 10));
            panel4.setLayout(new FlowLayout(FlowLayout.LEFT));

            //---- label4 ----
            label4.setText("T\u00ean \u0111\u0103ng nh\u1eadp:");
            label4.setFont(new Font("Nunito Medium", Font.PLAIN, 14));
            panel4.add(label4);

            //---- textField3 ----
            textField3.setPreferredSize(new Dimension(370, 30));
            textField3.setFont(new Font("Nunito", Font.PLAIN, 14));
            panel4.add(textField3);
        }
        contentPane.add(panel4);

        //======== panel5 ========
        {
            panel5.setBorder(new EmptyBorder(2, 10, 0, 10));
            panel5.setLayout(new FlowLayout(FlowLayout.LEFT));

            //---- label5 ----
            label5.setText("T\u00ean \u0111\u0103ng nh\u1eadp:");
            label5.setFont(new Font("Nunito Medium", Font.PLAIN, 14));
            panel5.add(label5);

            //---- textField4 ----
            textField4.setPreferredSize(new Dimension(370, 30));
            textField4.setFont(new Font("Nunito", Font.PLAIN, 14));
            panel5.add(textField4);
        }
        contentPane.add(panel5);

        //======== panel6 ========
        {
            panel6.setBorder(new EmptyBorder(20, 10, 0, 10));
            panel6.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 5));

            //---- cancel ----
            cancel.setText("text");
            cancel.setFont(new Font("Nunito Medium", Font.PLAIN, 14));
            cancel.setMaximumSize(new Dimension(90, 35));
            cancel.setMinimumSize(new Dimension(90, 35));
            cancel.setPreferredSize(new Dimension(90, 40));
            cancel.setBackground(new Color(0xf7fafc));
            panel6.add(cancel);

            //---- ok ----
            ok.setText("text");
            ok.setFont(new Font("Nunito Medium", Font.PLAIN, 14));
            ok.setMaximumSize(new Dimension(90, 35));
            ok.setMinimumSize(new Dimension(90, 35));
            ok.setPreferredSize(new Dimension(90, 40));
            ok.setBackground(new Color(0x63b3ed));
            panel6.add(ok);
        }
        contentPane.add(panel6);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel panel1;
    private JLabel label1;
    private JPanel panel2;
    private JLabel label2;
    private JTextField textField1;
    private JPanel panel3;
    private JLabel label3;
    private JTextField textField2;
    private JPanel panel4;
    private JLabel label4;
    private JTextField textField3;
    private JPanel panel5;
    private JLabel label5;
    private JTextField textField4;
    private JPanel panel6;
    private JButton cancel;
    private JButton ok;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}

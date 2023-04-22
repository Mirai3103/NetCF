import Utils.Fonts;
import Utils.Helper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KhungSP extends JPanel {
    private JLabel label;
    private JButton button;

    public KhungSP(int idProduct,String imgIcon, String nameProduct,Double priceProduct) {
        //tao mot icon roi set icon do va JLabel
        JPanel panelh = new JPanel();
//        panelh.setPreferredSize(new Dimension(150-5,50));
        panelh.setLayout(new BorderLayout());
        label = new JLabel();
        label.setIcon(Helper.getIcon(imgIcon,300,200));
        panelh.add(label,BorderLayout.CENTER);
        JLabel name = new JLabel(nameProduct);
        name.setPreferredSize(new Dimension(45,20));
        name.setFont(Fonts.getFont(Font.PLAIN,18));
        JLabel price = new JLabel(priceProduct+"");
        price.setPreferredSize(new Dimension(70,20));
        price.setFont(Fonts.getFont(Font.PLAIN,18));
        JCheckBox checkBox = new JCheckBox("Mua hàng");
        checkBox.setFont(Fonts.getFont(Font.PLAIN,18));
        checkBox.setPreferredSize(new Dimension(90,20));
        checkBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        setPreferredSize(new Dimension(400,400));
        setLayout(new BorderLayout());
        add(panelh,BorderLayout.PAGE_START);
        add(name,BorderLayout.CENTER);
        add(price,BorderLayout.LINE_END);
        add(checkBox,BorderLayout.PAGE_END);
    }

}

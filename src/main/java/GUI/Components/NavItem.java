/*
 * Created by JFormDesigner on Sat Mar 11 16:06:11 ICT 2023
 */

package GUI.Components;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author Laffy
 */
public class NavItem extends JButton {
    private boolean isSelected = false;
    @Getter
    @Setter
    private String key;
    @Getter
    @Setter
    private JPanel contentPanel;
    @Setter
    @Getter
    private ImageIcon icon;
    public void setSelected(boolean selected) {
        isSelected = selected;
        if (isSelected) {
            this.setBackground(new Color(11, 197, 234));
            this.setForeground(Color.WHITE);
        } else {
            this.setBackground(Color.WHITE);
            this.setForeground(Color.BLACK);
        }
        // repaint();
        repaint();
    }
    public void toggleSelected() {
        setSelected(!isSelected);
        // repaint();
        repaint();
    }
    public boolean isSelected() {
        return isSelected;
    }
    public NavItem() {
        initComponents();
    }public NavItem(String text) {
        initComponents();
        setText(text);

    }

    private void thisMouseEntered(MouseEvent e) {
        //#0BC5EA
        this.setBackground(new Color(11, 197, 234));
        this.setForeground(Color.WHITE);
    }

    private void thisMouseExited(MouseEvent e) {
        this.setBackground(Color.WHITE);
        this.setForeground(Color.BLACK);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        setText("sfsdsd");
        //---- this ----
        setBorder(new EmptyBorder(20, 20, 20, 20));
        setHorizontalAlignment(SwingConstants.LEFT);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                thisMouseEntered(e);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                thisMouseExited(e);
            }
        });
        this.setPreferredSize(new Dimension(300, 50));
        this.setMinimumSize(new Dimension(300, 50));
        this.setBorder(new EmptyBorder(20, 20, 20, 20));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}

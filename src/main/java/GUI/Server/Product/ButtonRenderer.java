package GUI.Server.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class ButtonRenderer extends DefaultTableCellRenderer {
    private JButton button;
    public ButtonRenderer() {
        setOpaque(true);
        button = new JButton();
    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        button.setText((value == null) ? "" : value.toString());
        return button;
    }
}

package GUI.Server.Product;

import javax.swing.*;
import java.awt.*;

public class ButtonEditor extends DefaultCellEditor {
    private JButton button;
    private boolean isPushed;
    public ButtonEditor(JCheckBox checkBox) {
        super(checkBox);
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(e->fireEditingStopped());
    }
    @Override
    public Component getTableCellEditorComponent(JTable table,Object value, boolean isSelected, int row, int column) {
        if (isSelected) {
            button.setForeground(table.getSelectionForeground());
            button.setBackground(table.getSelectionBackground());
        } else {
            button.setForeground(table.getForeground());
            button.setBackground(UIManager.getColor("Button.background"));
        }
        button.setText((value == null) ? "" : value.toString());
        isPushed = true;
        return button;
    }

    @Override
    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }

    @Override
    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }
}

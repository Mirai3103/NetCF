package GUI.Components;

import Utils.Constants;
import lombok.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.ArrayList;
import java.util.List;

public class SideBar {

    private JPanel rootPanel;
    private JPanel contentPanel;
    private final List<NavItemElement>  items = new ArrayList<>();
    public SideBar(JPanel rootPanel, JPanel contentPanel) {
        this.rootPanel = rootPanel;
        this.contentPanel = contentPanel;
    }
    public void initComponent(List<Constants.Tab> tabs){
        tabs.forEach(tab -> {
            NavItem item = new NavItem();
            item.setKey(tab.getKey());
            item.setText(tab.getTitle());
            item.setIcon(tab.getIcon());
            item.setContentPanel(tab.getChildren() == null ? tab.getContentPanel() : null);
            var navItemElement = new NavItemElement(new ArrayList<>(), item);
            rootPanel.add(item);
            if (tab.getChildren() != null) {
                tab.getChildren().forEach(child -> {
                    NavItem childItem = new NavItem();
                    childItem.setBorder(new EmptyBorder(20, 50, 20, 20));
                    childItem.setKey(child.getKey());
                    childItem.setText(child.getTitle());
                    childItem.setIcon(child.getIcon());
                    childItem.setContentPanel(child.getContentPanel());
                    navItemElement.getChildren().add(childItem);
                    childItem.setVisible(false);
                    rootPanel.add(childItem);
                });
            }
            items.add(navItemElement);
        });
        initEvent();
    }
    public void initEvent(){
        items.forEach(item -> {
            item.getParent().addActionListener(e -> {
                items.forEach(NavItemElement::closeCollapse);
                if (!item.getParent().isSelected()) {
                    item.openCollapse();

                }
                if (item.getChildren().size() == 0) {
                    contentPanel.removeAll();
                    contentPanel.add(item.getParent().getContentPanel());
                    contentPanel.repaint();
                    contentPanel.revalidate();
                    item.getParent().setSelected(true);
                }

            });
            item.initChildEvent(this.contentPanel);
        });
    }
}

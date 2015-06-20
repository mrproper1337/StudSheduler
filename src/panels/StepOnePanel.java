package panels;
import java.awt.*;
import java.awt.event.*;
import tableModels.AudienceModel;
import tableModels.FacultyModel;
import tableModels.LecturerModel;
import javax.swing.*;

public class StepOnePanel extends JPanel {
    private boolean readyToListen = false;

    public StepOnePanel() {
        initComponents();
        setReadyToListen(true);
        table1.setModel(new LecturerModel());
    }

    public static void updateModel(){
        switch(comboBox1.getSelectedIndex()){
            case 0:
                table1.setModel(new LecturerModel());
                break;
            case 1:
                table1.setModel(new AudienceModel());
                break;
            case 2:
                table1.setModel(new FacultyModel());
                break;
        }
    }

    private void comboBox1ItemStateChanged(ItemEvent e) {
        if(isReadyToListen())
            updateModel();
    }


    private boolean isReadyToListen() {
        return readyToListen;
    }

    private void setReadyToListen(boolean readyToListen) {
        this.readyToListen = readyToListen;
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        comboBox1 = new JComboBox();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        label1 = new JLabel();

        //======== this ========
        setPreferredSize(new Dimension(460, 320));

        //---- comboBox1 ----
        comboBox1.setModel(new DefaultComboBoxModel(new String[] {
            "\u0412\u0438\u043a\u043b\u0430\u0434\u0430\u0447\u0456",
            "\u0410\u0443\u0434\u0438\u0442\u043e\u0440\u0456\u0457",
            "\u0412\u0456\u0434\u0434\u0456\u043b\u0435\u043d\u043d\u044f"
        }));
        comboBox1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                comboBox1ItemStateChanged(e);
            }
        });

        //======== scrollPane1 ========
        {

            //---- table1 ----
            table1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
            table1.setRowHeight(20);
            scrollPane1.setViewportView(table1);
        }

        //---- label1 ----
        label1.setText("\u041a\u0440\u043e\u043a 1");
        label1.setFont(new Font("Segoe UI", Font.BOLD, 14));

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup()
                        .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(comboBox1, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 281, Short.MAX_VALUE)
                            .addComponent(label1, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                    .addGap(12, 12, 12)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(comboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label1))
                    .addContainerGap())
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private static JComboBox comboBox1;
    private JScrollPane scrollPane1;
    private static JTable table1;
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

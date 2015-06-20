package panels;

import java.awt.event.*;

import pojo.Faculty;
import tableModels.CellUtilities;
import tableModels.ConnectHibernate;
import tableModels.GroupModel;
import tableModels.SubjectModel;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.GroupLayout;
/*
 * Created by JFormDesigner on Wed May 27 17:54:00 EEST 2015
 */



/**
 * @author Urka
 */
public class StepTwoPanel extends JPanel {

    private boolean readyToListen = false;

    public StepTwoPanel() {
        initComponents();
        setReadyToListen(true);
        table1.setModel(new GroupModel());
        initCombos(0);
    }

    public static void updateModel(){
        switch(comboBox1.getSelectedIndex()){
            case 0:
                table1.setModel(new GroupModel());
                break;
            case 1:
                table1.setModel(new SubjectModel());
                break;
        }
        initCombos(comboBox1.getSelectedIndex());
    }

    private void comboBox1ItemStateChanged(ItemEvent e) {
        if(isReadyToListen())
            updateModel();
    }

    public boolean isReadyToListen() {
        return readyToListen;
    }

    public void setReadyToListen(boolean readyToListen) {
        this.readyToListen = readyToListen;
    }

    private static void initCombos(int tableId){
        ArrayList<String> facultyNames,semesterNumbers;
        facultyNames = new ArrayList<String>();
        for(Object obj: ConnectHibernate.loadTable("from Faculty")){
            Faculty fac = (Faculty)obj;
            facultyNames.add(fac.getFacultyName());
        }
        semesterNumbers = new ArrayList<String>();
        for(int i = 1;i<=8;i++)
            semesterNumbers.add(String.valueOf(i));
        facultyNames.add("");
        semesterNumbers.add("");
        switch(tableId){
            case 0:
                CellUtilities.initInsertedCombos(table1, 1, facultyNames, 165);
                CellUtilities.initInsertedCombos(table1,2,semesterNumbers,40);
                break;
            case 1:
                CellUtilities.initInsertedCombos(StepTwoPanel.table1, 2, facultyNames, 165);
                CellUtilities.initInsertedCombos(StepTwoPanel.table1,3,semesterNumbers,40);
                break;
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        comboBox1 = new JComboBox();
        label1 = new JLabel();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();

        //======== this ========

        //---- comboBox1 ----
        comboBox1.setModel(new DefaultComboBoxModel(new String[] {
            "\u0413\u0440\u0443\u043f\u0438",
            "\u041f\u0440\u0435\u0434\u043c\u0435\u0442\u0438"
        }));
        comboBox1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                comboBox1ItemStateChanged(e);
            }
        });

        //---- label1 ----
        label1.setText("\u041a\u0440\u043e\u043a 2");
        label1.setFont(new Font("Segoe UI", Font.BOLD, 14));

        //======== scrollPane1 ========
        {

            //---- table1 ----
            table1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
            table1.setAutoCreateRowSorter(true);
            table1.setRowHeight(20);
            scrollPane1.setViewportView(table1);
        }

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup()
                        .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(comboBox1, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 262, Short.MAX_VALUE)
                            .addComponent(label1, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(comboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label1))
                    .addContainerGap())
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private static JComboBox comboBox1;
    private JLabel label1;
    private JScrollPane scrollPane1;
    public static JTable table1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

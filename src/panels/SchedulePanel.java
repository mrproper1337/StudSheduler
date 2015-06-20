/*
 * Created by JFormDesigner on Sun May 31 14:48:01 EEST 2015
 */

package panels;

import java.awt.event.*;

import pojo.Audience;
import pojo.Group;
import pojo.SLG;
import pojo.Subject;
import tableModels.CellUtilities;
import tableModels.ConnectHibernate;
import tableModels.ScheduleModel;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.GroupLayout;


/**
 * @author Urka
 */
public class SchedulePanel extends JPanel {

    public SchedulePanel() {
        initComponents();
        for(Object g: ConnectHibernate.loadTable("from Group")){
            String st = ((Group)g).getGroupName();
            comboBox1.addItem(st);
        }

        comboBox2.addItem("понеділок");
        comboBox2.addItem("вівторок");
        comboBox2.addItem("середа");
        comboBox2.addItem("четвер");
        comboBox2.addItem("п'ятниця");
        comboBox2.addItem("субота");

        setReadyToListen(true);

        table1.setModel(new ScheduleModel(((Group)(ConnectHibernate.loadTable("from Group").get(0))).getGroupId(),"понеділок"));
        initCombos();
    }

    private boolean readyToListen = false;

    public static void updateModel(){

        table1.setModel(new ScheduleModel(((Group) ConnectHibernate.loadTable("from Group").get(comboBox1.getSelectedIndex())).getGroupId(),
                comboBox2.getSelectedItem().toString()));
        initCombos();
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

    private static void initCombos(){
        ArrayList<String> subjectNames,audienceNumbers;

        subjectNames = new ArrayList<String>();
        for(SLG slg:ScheduleModel.slgList)
            subjectNames.add(slg.getSlgSubject().getSubjectName());
        subjectNames.add("");

        audienceNumbers = new ArrayList<String>();
        for(Object obj:ConnectHibernate.loadTable("from Audience")){
            Audience audience = (Audience) obj;
            audienceNumbers.add(String.valueOf(audience.getAudienceNumber()));
        }
        audienceNumbers.add("");

        CellUtilities.initInsertedCombos(table1, 1, subjectNames, 165);
        CellUtilities.initInsertedCombos(table1,2,audienceNumbers,60);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        comboBox1 = new JComboBox();
        label1 = new JLabel();
        comboBox2 = new JComboBox();

        //======== this ========

        //======== scrollPane1 ========
        {

            //---- table1 ----
            table1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
            table1.setAutoCreateRowSorter(true);
            table1.setRowHeight(20);
            scrollPane1.setViewportView(table1);
        }

        //---- comboBox1 ----
        comboBox1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                comboBox1ItemStateChanged(e);
            }
        });

        //---- label1 ----
        label1.setText("\u0420\u043e\u0437\u043a\u043b\u0430\u0434");
        label1.setFont(new Font("Segoe UI", Font.BOLD, 14));

        //---- comboBox2 ----
        comboBox2.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                comboBox1ItemStateChanged(e);
            }
        });

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup()
                        .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(comboBox1, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(comboBox2, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 143, Short.MAX_VALUE)
                            .addComponent(label1, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 311, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(label1)
                        .addComponent(comboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(comboBox2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap())
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JScrollPane scrollPane1;
    private static JTable table1;
    private static JComboBox comboBox1;
    private JLabel label1;
    private static JComboBox comboBox2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

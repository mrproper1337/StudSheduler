/*
 * Created by JFormDesigner on Sat May 30 23:23:25 EEST 2015
 */

package panels;

import pojo.Audience;
import pojo.Group;
import pojo.Lecturer;
import pojo.Subject;
import tableModels.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author Urka
 */
public class StepThreePanel extends JPanel {

    private boolean readyToListen = false;

    public StepThreePanel() {
        initComponents();
        setReadyToListen(true);
        table1.setModel(new SLGModel());
        initCombos(0);
    }

    public static void updateModel(){
        switch(comboBox1.getSelectedIndex()){
            case 0:
                table1.setModel(new SLGModel());
                break;
            case 1:
                table1.setModel(new LAModel());
                break;
            case 2:
                table1.setModel(new LTModel());
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
        ArrayList<String> subjectNames,lecturerNames,groupNames,audienceNumbers,timeDays,timeNumbers;

        subjectNames = new ArrayList<String>();
        for(Object obj:ConnectHibernate.loadTable("from Subject")){
            Subject subject = (Subject) obj;
            subjectNames.add(subject.getSubjectName());
        }
        subjectNames.add("");

        lecturerNames = new ArrayList<String>();
        for(Object obj:ConnectHibernate.loadTable("from Lecturer")){
            Lecturer lecturer = (Lecturer) obj;
            lecturerNames.add(lecturer.getLecturerName());
        }
        lecturerNames.add("");

        groupNames = new ArrayList<
                String>();
        for(Object obj:ConnectHibernate.loadTable("from Group")){
            Group group = (Group) obj;
            groupNames.add(group.getGroupName());
        }
        groupNames.add("");

        audienceNumbers = new ArrayList<String>();
        for(Object obj:ConnectHibernate.loadTable("from Audience")){
            Audience audience = (Audience) obj;
            audienceNumbers.add(String.valueOf(audience.getAudienceNumber()));
        }
        audienceNumbers.add("");

        timeDays = new ArrayList<String>();
        timeDays.add("понеділок");
        timeDays.add("вівторок");
        timeDays.add("середа");
        timeDays.add("четвер");
        timeDays.add("п'ятниця");
        timeDays.add("субота");
        timeDays.add("");

        timeNumbers = new ArrayList<String>();
        for(int i = 1;i < 6;i++)
            timeNumbers.add(String.valueOf(i));
        timeNumbers.add("");



        switch(tableId){
            case 0:
                CellUtilities.initInsertedCombos(table1, 0, subjectNames, 160);
                CellUtilities.initInsertedCombos(table1,1,lecturerNames,185);
                CellUtilities.initInsertedCombos(table1,2,groupNames,160);
                break;
            case 1:
                CellUtilities.initInsertedCombos(table1,0,lecturerNames,185);
                CellUtilities.initInsertedCombos(table1,1,audienceNumbers,100);
                break;
            case 2:
                CellUtilities.initInsertedCombos(table1,0,lecturerNames,185);
                CellUtilities.initInsertedCombos(table1,1,timeDays,160);
                CellUtilities.initInsertedCombos(table1,2,timeNumbers,40);
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
            "\u041f\u0440\u0435\u0434\u043c\u0435\u0442-\u0412\u0438\u043a\u043b\u0430\u0434\u0430\u0447-\u0413\u0440\u0443\u043f\u0430",
            "\u0412\u0438\u043a\u043b\u0430\u0434\u0430\u0447-\u0410\u0443\u0434\u0438\u0442\u043e\u0440\u0456\u044f",
            "\u0412\u0438\u043a\u043b\u0430\u0434\u0430\u0447-\u0427\u0430\u0441"
        }));
        comboBox1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                comboBox1ItemStateChanged(e);
            }
        });

        //---- label1 ----
        label1.setText("\u041a\u0440\u043e\u043a 3");
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
                        .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(comboBox1, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 281, Short.MAX_VALUE)
                            .addComponent(label1, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(label1)
                        .addComponent(comboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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

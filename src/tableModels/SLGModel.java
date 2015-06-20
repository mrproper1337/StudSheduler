package tableModels;

import panels.StepThreePanel;
import pojo.Group;
import pojo.Lecturer;
import pojo.SLG;
import pojo.Subject;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class SLGModel extends DefaultTableModel {
    static List<SLG> slgList = ConnectHibernate.loadTable("from SLG");

    public SLGModel(){
        slgList = ConnectHibernate.loadTable("from SLG");
    }

    @Override
    public int getRowCount() {
        return slgList.size()+1;
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int column) {
        switch (column){
            case 0:
                return "Предмет";
            case 1:
                return "Викладач";
            case 2:
                return "Група";
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return true;
    }

    @Override
    public Object getValueAt(int row, int column) {
        if(row == slgList.size())return "";
        switch (column){
            case 0:
                return slgList.get(row).getSlgSubject().getSubjectName();
            case 1:
                return slgList.get(row).getSlgLecturer().getLecturerName();
            case 2:
                return slgList.get(row).getSlgGroup().getGroupName();
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int row, int column) {
        switch(CellUtilities.getActionId(row == slgList.size(), aValue.toString().equals(""))){
            case 0:
                addRow(aValue,column);
                break;
            case 1:
                deleteRow(row);
                break;
            case 2:
                updateRow(aValue,row,column);
                break;
        }
    }

    private void addRow(Object aValue,int column){
        SLG obj = new SLG();
        switch (column){
            case 0:
                for(Object f:ConnectHibernate.loadTable("from Subject")){
                    Subject subject = (Subject)f;
                    if(subject.getSubjectName().equals(aValue.toString()))
                        obj.setSlgSubject(subject);
                }
                obj.setSlgLecturer((Lecturer) ConnectHibernate.loadTable("from Lecturer").get(0));
                obj.setSlgGroup((Group)ConnectHibernate.loadTable("from Group").get(0));
                break;
            case 1:
                for(Object f:ConnectHibernate.loadTable("from Lecturer")){
                    Lecturer lecturer = (Lecturer)f;
                    if(lecturer.getLecturerName().equals(aValue.toString()))
                        obj.setSlgLecturer(lecturer);
                }
                obj.setSlgSubject((Subject) ConnectHibernate.loadTable("from Subject").get(0));
                obj.setSlgGroup((Group)ConnectHibernate.loadTable("from Group").get(0));
                break;
            case 2:
                for(Object f:ConnectHibernate.loadTable("from Group")){
                    Group group = (Group)f;
                    if(group.getGroupName().equals(aValue.toString()))
                        obj.setSlgGroup(group);
                }
                obj.setSlgSubject((Subject) ConnectHibernate.loadTable("from Subject").get(0));
                obj.setSlgLecturer((Lecturer) ConnectHibernate.loadTable("from Lecturer").get(0));
                break;
        }
        ConnectHibernate.addToTable(obj);
        StepThreePanel.updateModel();
    }
    private void deleteRow(int row){
        SLG obj = slgList.get(row);
        ConnectHibernate.deleteFromTable(obj);
        StepThreePanel.updateModel();
    }
    private void updateRow(Object aValue, int row, int column){
        SLG obj = slgList.get(row);
        switch(column){
            case 0:
                for(Object f:ConnectHibernate.loadTable("from Subject")){
                    Subject subject = (Subject)f;
                    if(subject.getSubjectName().equals(aValue.toString()))
                        obj.setSlgSubject(subject);
                }
                break;
            case 1:
                for(Object f:ConnectHibernate.loadTable("from Lecturer")){
                    Lecturer lecturer = (Lecturer)f;
                    if(lecturer.getLecturerName().equals(aValue.toString()))
                        obj.setSlgLecturer(lecturer);
                }
                break;
            case 2:
                for(Object f:ConnectHibernate.loadTable("from Group")){
                    Group group = (Group)f;
                    if(group.getGroupName().equals(aValue.toString()))
                        obj.setSlgGroup(group);
                }
                break;
        }
        ConnectHibernate.updateInTable(obj);
        StepThreePanel.updateModel();
    }
}

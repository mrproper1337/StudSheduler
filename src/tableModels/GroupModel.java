package tableModels;

import panels.StepTwoPanel;
import pojo.Faculty;
import pojo.Group;
import pojo.Semester;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class GroupModel extends DefaultTableModel {
    static List<Group> groupsList = ConnectHibernate.loadTable("from Group");

    public GroupModel(){
        groupsList = ConnectHibernate.loadTable("from Group");
    }

    @Override
    public int getRowCount() {
        return groupsList.size()+1;
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0:return "Назва групи";
            case 1:return "Відділення";
            case 2:return "Поточний семестр";
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return true;
    }

    @Override
    public Object getValueAt(int row, int column) {
        if(row == groupsList.size())return "";
        switch(column){
            case 0:return groupsList.get(row).getGroupName();
            case 1:return groupsList.get(row).getGroupSemester().getSemesterFacultyId().getFacultyName();
            case 2:return String.valueOf(groupsList.get(row).getGroupSemester().getSemesterNumber());
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int row, int column) {
        switch(CellUtilities.getActionId(row == groupsList.size(), aValue.toString().equals(""))){
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
        if(column == 0){
            Group obj = new Group();
            obj.setGroupName(aValue.toString());
            obj.setGroupSemester((Semester)ConnectHibernate.
                    loadTable("from Semester").get(0));
            ConnectHibernate.addToTable(obj);
            StepTwoPanel.updateModel();
        }
    }
    private void deleteRow(int row){
        Group obj = groupsList.get(row);
        ConnectHibernate.deleteFromTable(obj);
        StepTwoPanel.updateModel();
    }
    private void updateRow(Object aValue, int row, int column){
        Group obj = groupsList.get(row);
        switch(column){
            case 0:
                obj.setGroupName(aValue.toString());
                break;
            case 1:
                int facId = 0;
                for(Object f:ConnectHibernate.loadTable("from Faculty")){
                    Faculty faculty = (Faculty)f;
                    if(faculty.getFacultyName().equals(aValue.toString()))
                        facId = faculty.getFacultyId();
                }
                Semester semId = (Semester)ConnectHibernate.loadTable("from Semester where " +
                        "semester_number = " +obj.getGroupSemester().getSemesterNumber()+
                        " and " +
                        "semester_faculty = " +facId+
                        "").get(0);
                obj.setGroupSemester(semId);
                break;
            case 2:
                List sl = ConnectHibernate.loadTable("from Semester where " +
                        "semester_number = " + Integer.parseInt(aValue.toString()) +
                        " and " +
                        "semester_faculty = " + obj.getGroupSemester().getSemesterFacultyId().getFacultyId() +
                        "");
                if(!sl.isEmpty())
                    obj.setGroupSemester((Semester)sl.get(0));
                break;
        }
        ConnectHibernate.updateInTable(obj);
        StepTwoPanel.updateModel();
    }
}

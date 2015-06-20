package tableModels;

import panels.StepTwoPanel;
import pojo.Faculty;
import pojo.Semester;
import pojo.Subject;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class SubjectModel extends DefaultTableModel {
    static List<Subject> subjectsList = ConnectHibernate.loadTable("from Subject");

    public SubjectModel(){
        subjectsList = ConnectHibernate.loadTable("from Subject");
    }

    @Override
    public int getRowCount() {
        return subjectsList.size()+1;
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int column) {
        switch (column){
            case 0:return "Назва предмету";
            case 1:return "Кількість годин";
            case 2:return "Відділення";
            case 3:return "Семестр";
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return true;
    }

    @Override
    public Object getValueAt(int row, int column) {
        if(row == subjectsList.size())return "";
        switch (column){
            case 0:return subjectsList.get(row).getSubjectName();
            case 1:return subjectsList.get(row).getSubjectHours();
            case 2:return subjectsList.get(row).getSubjectSemester().getSemesterFacultyId().getFacultyName();
            case 3:return String.valueOf(subjectsList.get(row).getSubjectSemester().getSemesterNumber());
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int row, int column) {
        switch(CellUtilities.getActionId(row == subjectsList.size(), aValue.toString().equals(""))){
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
        switch(column){
            case 0:
                Subject obj = new Subject();
                obj.setSubjectName(aValue.toString());
                obj.setSubjectHours(50);
                obj.setSubjectSemester((Semester) ConnectHibernate.
                        loadTable("from Semester").get(0));
                ConnectHibernate.addToTable(obj);
                StepTwoPanel.updateModel();
                break;
            case 1:
                obj = new Subject();
                obj.setSubjectName("Назва предмету");
                obj.setSubjectHours(Integer.parseInt(aValue.toString()));
                obj.setSubjectSemester((Semester)ConnectHibernate.
                        loadTable("from Semester").get(0));
                ConnectHibernate.addToTable(obj);
                StepTwoPanel.updateModel();
                break;
        }
    }
    private void deleteRow(int row){
        Subject obj = subjectsList.get(row);
        ConnectHibernate.deleteFromTable(obj);
        StepTwoPanel.updateModel();
    }
    private void updateRow(Object aValue, int row, int column){
        Subject obj = subjectsList.get(row);
        switch (column){
            case 0:
                obj.setSubjectName(aValue.toString());
                break;
            case 1:
                obj.setSubjectHours(Integer.parseInt(aValue.toString()));
                break;
            case 2:
                int facId = 0;
                for(Object f:ConnectHibernate.loadTable("from Faculty")){
                    Faculty faculty = (Faculty)f;
                    if(faculty.getFacultyName().equals(aValue.toString()))
                        facId = faculty.getFacultyId();
                }
                Semester semId = (Semester)ConnectHibernate.loadTable("from Semester where " +
                        "semester_number = " +obj.getSubjectSemester().getSemesterNumber()+
                        " and " +
                        "semester_faculty = " +facId+
                        "").get(0);
                obj.setSubjectSemester(semId);
                break;
            case 3:
                List sl = ConnectHibernate.loadTable("from Semester where " +
                        "semester_number = " + Integer.parseInt(aValue.toString()) +
                        " and " +
                        "semester_faculty = " + obj.getSubjectSemester().getSemesterFacultyId().getFacultyId() +
                        "");
                if(!sl.isEmpty())
                    obj.setSubjectSemester((Semester) sl.get(0));
                break;
        }
        ConnectHibernate.updateInTable(obj);
        StepTwoPanel.updateModel();
    }
}

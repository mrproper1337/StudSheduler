package tableModels;

import panels.StepOnePanel;
import pojo.Faculty;
import pojo.Semester;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class FacultyModel extends DefaultTableModel{
    static List<Faculty> facultiesList = ConnectHibernate.loadTable("from Faculty");

    public FacultyModel(){
        facultiesList = ConnectHibernate.loadTable("from Faculty");
    }

    @Override
    public int getRowCount() {
        return facultiesList.size()+1;
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public String getColumnName(int column) {
        if(column == 0)return "Назва відділення";
        return "Кількість семестрів";
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return true;
    }

    @Override
    public Object getValueAt(int row, int column) {
        if(facultiesList.size() == row)return "";
        if(column == 1)
            return facultiesList.get(row).getFacultySemesters();
        return facultiesList.get(row).getFacultyName();
    }

    @Override
    public void setValueAt(Object aValue, int row, int column) {
        Faculty obj;
        switch(CellUtilities.getActionId(row == facultiesList.size(), aValue.toString().equals(""))){
            case 0:
                obj = new Faculty();
                if(column == 0){
                    obj.setFacultyName(aValue.toString());
                    obj.setFacultySemesters(8);
                }
                else{
                    obj.setFacultyName("Назва відділення");
                    obj.setFacultySemesters(Integer.parseInt(aValue.toString()));
                }
                ConnectHibernate.addToTable(obj);
                StepOnePanel.updateModel();
                break;
            case 1:
                obj = facultiesList.get(row);
                ConnectHibernate.deleteFromTable(obj);
                StepOnePanel.updateModel();
                break;
            case 2:
                obj = facultiesList.get(row);
                if(column == 0)obj.setFacultyName(aValue.toString());
                else obj.setFacultySemesters(Integer.parseInt(aValue.toString()));
                ConnectHibernate.updateInTable(obj);
                break;
        }
        if(column == 0)
            genNewSemesters();
    }

    private void genNewSemesters(){
        CellUtilities.clearTable("semester");
        for(Faculty fac:facultiesList){
            for(int i = 1;i<=fac.getFacultySemesters();i++){
                Semester sem = new Semester();
                sem.setSemesterNumber(i);
                sem.setSemesterFacultyId(fac);
                ConnectHibernate.addToTable(sem);
            }
        }
    }
}

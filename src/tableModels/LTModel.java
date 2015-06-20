package tableModels;

import panels.StepThreePanel;
import pojo.LT;
import pojo.Lecturer;
import pojo.Time;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class LTModel extends DefaultTableModel {


    static List<LT> ltList = ConnectHibernate.loadTable("from LT");

    public LTModel(){
        ltList = ConnectHibernate.loadTable("from LT");
    }

    @Override
    public int getRowCount() {
        return ltList.size()+1;
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int column) {
        switch (column){
            case 0:
                return "Викладач";
            case 1:
                return "День";
            case 2:
                return "Пара";
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return true;
    }

    @Override
    public Object getValueAt(int row, int column) {
        if(row == ltList.size())return "";
        switch (column){
            case 0:
                return ltList.get(row).getLtLecturer().getLecturerName();
            case 1:
                return ltList.get(row).getLtTime().getTimeDay();
            case 2:
                return String.valueOf(ltList.get(row).getLtTime().getTimeNumber());
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int row, int column) {
        switch(CellUtilities.getActionId(row == ltList.size(), aValue.toString().equals(""))){
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
        LT obj = new LT();
        switch (column){
            case 0:
                for(Object f:ConnectHibernate.loadTable("from Lecturer")){
                    Lecturer lecturer = (Lecturer)f;
                    if(lecturer.getLecturerName().equals(aValue.toString()))
                        obj.setLtLecturer(lecturer);
                }
                obj.setLtTime((Time) ConnectHibernate.loadTable("from Time").get(0));
                break;
            case 1:
                for(Object f:ConnectHibernate.loadTable("from Time")){
                    Time time = (Time)f;
                    if(time.getTimeDay().equals(aValue.toString()))
                        obj.setLtTime(time);
                }
                obj.setLtLecturer((Lecturer) ConnectHibernate.loadTable("from Lecturer").get(0));
                break;
            case 2:
                for(Object f:ConnectHibernate.loadTable("from Time")){
                    Time time = (Time)f;
                    if(time.getTimeNumber() == Integer.parseInt(aValue.toString()))
                        obj.setLtTime(time);
                }
                obj.setLtLecturer((Lecturer) ConnectHibernate.loadTable("from Lecturer").get(0));
                break;
        }
        ConnectHibernate.addToTable(obj);
        StepThreePanel.updateModel();
    }
    private void deleteRow(int row){
        LT obj = ltList.get(row);
        ConnectHibernate.deleteFromTable(obj);
        StepThreePanel.updateModel();
    }
    private void updateRow(Object aValue, int row, int column){
        LT obj = ltList.get(row);
        switch(column){
            case 0:
                for(Object f:ConnectHibernate.loadTable("from Lecturer")){
                    Lecturer lecturer = (Lecturer)f;
                    if(lecturer.getLecturerName().equals(aValue.toString()))
                        obj.setLtLecturer(lecturer);
                }
                break;
            case 1:
                for(Object f:ConnectHibernate.loadTable("from Time")){
                    Time time = (Time)f;
                    if(time.getTimeDay().equals(aValue.toString())&&
                            time.getTimeNumber()==obj.getLtTime().getTimeNumber())
                        obj.setLtTime(time);
                }
                break;
            case 2:
                for(Object f:ConnectHibernate.loadTable("from Time")){
                    Time time = (Time)f;
                    if(time.getTimeDay().equals(obj.getLtTime().getTimeDay())&&
                            time.getTimeNumber()== Integer.parseInt(aValue.toString()))
                        obj.setLtTime(time);
                }
                break;
        }
        ConnectHibernate.updateInTable(obj);
        StepThreePanel.updateModel();
    }

}

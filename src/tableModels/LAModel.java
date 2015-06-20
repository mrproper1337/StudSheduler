package tableModels;

import panels.StepThreePanel;
import pojo.Audience;
import pojo.LA;
import pojo.Lecturer;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class LAModel extends DefaultTableModel {

    static List<LA> laList = ConnectHibernate.loadTable("from LA");

    public LAModel(){
        laList = ConnectHibernate.loadTable("from LA");
    }

    @Override
    public int getRowCount() {
        return laList.size()+1;
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public String getColumnName(int column) {
        switch (column){
            case 0:
                return "Викладач";
            case 1:
                return "Аудиторія";
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return true;
    }

    @Override
    public Object getValueAt(int row, int column) {
        if(row == laList.size())return "";
        switch (column){
            case 0:
                return laList.get(row).getLaLecturer().getLecturerName();
            case 1:
                return String.valueOf(laList.get(row).getLaAudience().getAudienceNumber());
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int row, int column) {
        switch(CellUtilities.getActionId(row == laList.size(), aValue.toString().equals(""))){
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
        LA obj = new LA();
        switch (column){
            case 0:
                for(Object f:ConnectHibernate.loadTable("from Lecturer")){
                    Lecturer lecturer = (Lecturer)f;
                    if(lecturer.getLecturerName().equals(aValue.toString()))
                        obj.setLaLecturer(lecturer);
                }
                obj.setLaAudience((Audience) ConnectHibernate.loadTable("from Audience").get(0));
                break;
            case 1:
                for(Object f:ConnectHibernate.loadTable("from Audience")){
                    Audience audience = (Audience)f;
                    if(audience.getAudienceNumber() == (Integer.parseInt(aValue.toString())))
                        obj.setLaAudience(audience);
                }
                obj.setLaLecturer((Lecturer) ConnectHibernate.loadTable("from Lecturer").get(0));
                break;
        }
        ConnectHibernate.addToTable(obj);
        StepThreePanel.updateModel();
    }
    private void deleteRow(int row){
        LA obj = laList.get(row);
        ConnectHibernate.deleteFromTable(obj);
        StepThreePanel.updateModel();
    }
    private void updateRow(Object aValue, int row, int column){
        LA obj = laList.get(row);
        switch(column){
            case 0:
                for(Object f:ConnectHibernate.loadTable("from Lecturer")){
                    Lecturer lecturer = (Lecturer)f;
                    if(lecturer.getLecturerName().equals(aValue.toString()))
                        obj.setLaLecturer(lecturer);
                }
                break;
            case 1:
                for(Object f:ConnectHibernate.loadTable("from Audience")){
                    Audience audience = (Audience)f;
                    if(audience.getAudienceNumber() == (Integer.parseInt(aValue.toString())))
                        obj.setLaAudience(audience);
                }
                break;
        }
        ConnectHibernate.updateInTable(obj);
        StepThreePanel.updateModel();
    }
}

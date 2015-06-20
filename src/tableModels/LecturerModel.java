package tableModels;

import panels.StepOnePanel;
import pojo.Lecturer;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class LecturerModel extends DefaultTableModel {
    static List<Lecturer> lecturersList = ConnectHibernate.loadTable("from Lecturer");

    public LecturerModel(){
        lecturersList = ConnectHibernate.loadTable("from Lecturer");
    }

    @Override
    public int getRowCount() {
        return lecturersList.size()+1;
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public String getColumnName(int column) {
        return "ПІБ Викладача";
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return true;
    }

    @Override
    public Object getValueAt(int row, int column) {
        if(row == lecturersList.size())
            return "";
        return lecturersList.get(row).getLecturerName();
    }

    @Override
    public void setValueAt(Object aValue, int row, int column) {
        Lecturer obj;
        switch(CellUtilities.getActionId(row == lecturersList.size(), aValue.toString().equals(""))){
            case 0:
                obj = new Lecturer();
                obj.setLecturerName(aValue.toString());
                ConnectHibernate.addToTable(obj);
                StepOnePanel.updateModel();
                break;
            case 1:
                obj = lecturersList.get(row);
                ConnectHibernate.deleteFromTable(obj);
                StepOnePanel.updateModel();
                break;
            case 2:
                obj = lecturersList.get(row);
                obj.setLecturerName(aValue.toString());
                ConnectHibernate.updateInTable(obj);
                break;
        }
    }
}

package tableModels;

import panels.StepOnePanel;
import pojo.Audience;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class AudienceModel extends DefaultTableModel {
    static List<Audience> audiencesList = ConnectHibernate.loadTable("from Audience");

    public AudienceModel(){
        audiencesList = ConnectHibernate.loadTable("from Audience");
    }

    @Override
    public int getRowCount() {
        return audiencesList.size()+1;
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0:return "Номер аудиторії";
            case 1:return "Номер корпусу";
            case 2:return "Поверх";
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return true;
    }

    @Override
    public Object getValueAt(int row, int column) {
        if(row == audiencesList.size())return "";
        Audience obj = audiencesList.get(row);
        switch(column){
            case 0:return obj.getAudienceNumber();
            case 1:return obj.getAudiencePavilion();
            case 2:return obj.getAudienceFloor();
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int row, int column) {
        Audience obj;
        switch(CellUtilities.getActionId(row == audiencesList.size(), aValue.toString().equals(""))){
            case 0:
                obj = new Audience();
                switch(column){
                    case 0:
                        obj.setAudienceNumber(Integer.parseInt(aValue.toString()));
                        obj.setAudiencePavilion(1);
                        obj.setAudienceFloor(1);
                        break;
                    case 1:
                        obj.setAudienceNumber(1);
                        obj.setAudiencePavilion(Integer.parseInt(aValue.toString()));
                        obj.setAudienceFloor(1);
                        break;
                    case 2:
                        obj.setAudienceNumber(1);
                        obj.setAudiencePavilion(1);
                        obj.setAudienceFloor(Integer.parseInt(aValue.toString()));
                        break;
                }
                ConnectHibernate.addToTable(obj);
                StepOnePanel.updateModel();
                break;
            case 1:
                obj = audiencesList.get(row);
                ConnectHibernate.deleteFromTable(obj);
                StepOnePanel.updateModel();
                break;
            case 2:
                obj = audiencesList.get(row);
                switch(column){
                    case 0:obj.setAudienceNumber(Integer.parseInt(aValue.toString()));
                        break;
                    case 1:obj.setAudiencePavilion(Integer.parseInt(aValue.toString()));
                        break;
                    case 2:obj.setAudienceFloor(Integer.parseInt(aValue.toString()));
                        break;
                }
                ConnectHibernate.updateInTable(obj);
                break;
        }
    }
}

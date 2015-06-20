package tableModels;

import panels.SchedulePanel;
import pojo.Audience;
import pojo.SLG;
import pojo.Schedule;
import pojo.Time;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ScheduleModel extends DefaultTableModel {
    static int groupId = 1;
    static String day = "понеділок";
    static List<Schedule> scheduleList = ConnectHibernate.loadTable("from Schedule where(" +
            "scheduleSlg = (from SLG where slgGroup.groupId = "+groupId+")" +
            " and " +
            "scheduleTime = (from Time where timeDay = '"+day+"')" +
            ")");
    public static List<SLG> slgList = ConnectHibernate.loadTable("from SLG where slg_group = "+groupId);
    List<Time> timeList;
    List<Audience> audienceList;


    public ScheduleModel(int groupId, String day){
        this.groupId = groupId;
        this.day = day;
        audienceList = ConnectHibernate.loadTable("from Audience");
        slgList = ConnectHibernate.loadTable("from SLG where slg_group = "+groupId);
        timeList = ConnectHibernate.loadTable("from Time where time_day = '"+day+"'");
        String sqq = "",tqq = "";
        int i = 0;
        for(SLG slg:slgList){
            if(i == 0)
                sqq +="schedule_slg = " + slg.getSlgId();
            sqq += " or schedule_slg = "+slg.getSlgId();
            i++;
        }
        i = 0;
        for(Time time:timeList){
            if(i == 0)
                tqq +="schedule_time = " + time.getTimeId();
            tqq += " or schedule_time = " + time.getTimeId();
            i++;
        }
        scheduleList = ConnectHibernate.loadTable("from Schedule where(" +
                "("+sqq+")" +
                " and " +
                "("+tqq+")" +
                ")");
    }

    @Override
    public int getRowCount() {
        return 5;
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int column) {
        switch (column){
            case 0:
                return "Пара";
            case 1:
                return "Предмет";
            case 2:
                return "Аудиторія";
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return column!=0;
    }

    @Override
    public Object getValueAt(int row, int column) {
        Schedule schedule = null;
        for(Schedule s:scheduleList){
            if(s.getScheduleTime().getTimeNumber()==(row+1))
                schedule = s;
        }
        switch (column){
            case 0:
                return row+1;
            case 1:
                if(schedule!=null)
                    return schedule.getScheduleSlg().getSlgSubject().getSubjectName();
                break;
            case 2:
                if(schedule!=null)
                    return String.valueOf(schedule.getScheduleAudience().getAudienceNumber());
                break;
        }
        return "";
    }

    @Override
    public void setValueAt(Object aValue, int row, int column) {
        int actionId = -1;
        Schedule schedule = null;
        for(Schedule s:scheduleList){
            if(s.getScheduleTime().getTimeNumber()==(row+1))
                schedule = s;
        }

        if(schedule == null && !aValue.equals(""))
            actionId = 0;
        if(schedule != null && aValue.equals(""))
            actionId = 1;
        if(schedule != null && !aValue.equals(""))
            actionId = 2;


        switch(actionId){
            case 0:
                addRow(aValue,row,column);
                break;
            case 1:
                deleteRow(schedule);
                break;
            case 2:
                updateRow(aValue,column,schedule);
                break;
        }
    }

    private void addRow(Object aValue,int row,int column){
        Schedule obj = new Schedule();
        switch (column){
            case 1:
                for(SLG slg:slgList){
                    if(slg.getSlgSubject().getSubjectName().equals(aValue.toString()))
                        obj.setScheduleSlg(slg);
                }
                for(Time time:timeList){
                    if(time.getTimeNumber() ==(row+1))
                        obj.setScheduleTime(time);
                }
                obj.setScheduleAudience(audienceList.get(0));
                break;
            case 2:
                for(Audience audience:audienceList){
                    if(audience.getAudienceNumber()==Integer.parseInt(aValue.toString()))
                        obj.setScheduleAudience(audience);
                }
                for(Time time:timeList){
                    if(time.getTimeNumber() ==(row+1))
                        obj.setScheduleTime(time);
                }
                obj.setScheduleSlg(slgList.get(0));
                break;

        }
        ConnectHibernate.addToTable(obj);
        SchedulePanel.updateModel();
    }
    private void deleteRow(Schedule s){
        ConnectHibernate.deleteFromTable(s);
        SchedulePanel.updateModel();
    }
    private void updateRow(Object aValue, int column,Schedule s){
        switch(column){
            case 1:
                for(SLG slg:slgList){
                    if(slg.getSlgSubject().getSubjectName().equals(aValue.toString()))
                        s.setScheduleSlg(slg);
                }
                break;
            case 2:
                for(Audience audience:audienceList){
                    if(audience.getAudienceNumber()==Integer.parseInt(aValue.toString()))
                        s.setScheduleAudience(audience);
                }
                break;
        }
        ConnectHibernate.updateInTable(s);
        SchedulePanel.updateModel();
    }
}

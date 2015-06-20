import pojo.Group;
import tableModels.ConnectHibernate;

public class Test {
    public static void main(String[] args) {
        String s = "П - 611";
        for(Object o:ConnectHibernate.loadTable("from Group where group_name = '"+s+"'")){
            System.out.println(((Group)o).getGroupName());
        }
    }
}

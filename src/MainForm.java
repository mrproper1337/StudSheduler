import java.awt.event.*;

import panels.SchedulePanel;
import panels.StepOnePanel;
import panels.StepThreePanel;
import panels.StepTwoPanel;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;
/*
 * Created by JFormDesigner on Wed May 27 18:42:13 EEST 2015
 */



/**
 * @author Urka
 */
public class MainForm extends JFrame {
    private int step;
    public MainForm() {
        initComponents();
        setContentPane(new StepOnePanel());
        step = 1;
    }

    public void changeStep(int step){
        step = step > 4? 4:step;
        switch(step){
            case 1:
                setContentPane(new StepOnePanel());
                break;
            case 2:
                setContentPane(new StepTwoPanel());
                break;
            case 3:
                setContentPane(new StepThreePanel());
                break;
            case 4:
                setContentPane(new SchedulePanel());
                break;
        }
        revalidate();
    }

    private void nextStep(ActionEvent e) {
        changeStep(++step);
    }

    private void newData(ActionEvent e) {
        step = 1;
        changeStep(step);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        menuBar1 = new JMenuBar();
        menu2 = new JMenu();
        menuItem4 = new JMenuItem();
        menuItem6 = new JMenuItem();

        //======== this ========
        setTitle("\u0420\u043e\u0437\u043a\u043b\u0430\u0434");
        Container contentPane = getContentPane();

        //======== menuBar1 ========
        {

            //======== menu2 ========
            {
                menu2.setText("\u0412\u0445\u0456\u0434\u043d\u0456 \u0434\u0430\u043d\u0456");

                //---- menuItem4 ----
                menuItem4.setText("\u041d\u0430\u0441\u0442\u0443\u043f\u043d\u0438\u0439 \u043a\u0440\u043e\u043a");
                menuItem4.setToolTipText("\u041f\u0440\u043e\u0434\u043e\u0432\u0436\u0438\u0442\u0438 \u0437\u0430\u043f\u043e\u0432\u043d\u0435\u043d\u043d\u044f");
                menuItem4.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        nextStep(e);
                    }
                });
                menu2.add(menuItem4);

                //---- menuItem6 ----
                menuItem6.setText("\u041d\u043e\u0432\u0456 \u0434\u0430\u043d\u0456");
                menuItem6.setToolTipText("\u0412\u0432\u0435\u0441\u0442\u0438 \u043d\u043e\u0432\u0456 \u0434\u0430\u043d\u0456");
                menuItem6.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        newData(e);
                    }
                });
                menu2.add(menuItem6);
            }
            menuBar1.add(menu2);
        }
        setJMenuBar(menuBar1);

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGap(0, 699, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGap(0, 421, Short.MAX_VALUE)
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JMenuBar menuBar1;
    private JMenu menu2;
    private JMenuItem menuItem4;
    private JMenuItem menuItem6;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    public static void main(String[] args) {
        MainForm form = new MainForm();
        form.setVisible(true);
        form.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //form.setResizable(false);
    }
}

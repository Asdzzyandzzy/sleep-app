package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 保持Printer类的基本结构，但调整布局和组件初始化
public abstract class Printer extends JFrame {
    JPanel panelMain;
    public static final int WIDTH = 500;
    public static final int HEIGHT = 300;

    private JLabel instructionsArea;
  //  private JTextField inputField;
  //  private JButton submitButton;

    // 在Printer类中
    protected JTextField inputField;
    protected JButton submitButton;


    private String s;
    private String inputText;

    public Printer() {
        super("Sleep App");
        panelMain = new JPanel(new BorderLayout()); // 使用BorderLayout
        getContentPane().add(panelMain);

        setMinimumSize(new Dimension(WIDTH, HEIGHT));

        // 初始化instructionsArea
        instructionsArea = new JLabel();
        panelMain.add(instructionsArea, BorderLayout.CENTER);

        // 创建一个子面板用于存放输入框和按钮
        JPanel southPanel = new JPanel(new FlowLayout()); // 使用FlowLayout让组件排列更自由
        inputField = new JTextField(20); // 指定输入框的初始大小
        submitButton = new JButton("Submit");

        southPanel.add(inputField);
        southPanel.add(submitButton);

        panelMain.add(southPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);

        inputText = "";

    }

    public void button() {
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 从 JTextField 获取文本
                inputText = inputField.getText();
                submitInput();
            }
        });
    }

    public void typeWord(String s) {
        instructionsArea.setText(s); // 直接设置文本，不需要每次都新建一个JLabel
    }

    public String submitInput() {
        return inputText;
    }


}

// 关于SleepApp类的代码，看起来主要问题在于界面的初始化可能未完全按照Swing的要求来做。
// 建议检查runSleep方法及其调用的任何地方，确保长时间运行的操作不是在事件调度线程（EDT）上执行的。
// 如果runSleep中有长时间运行的操作，请考虑使用SwingWorker或其他并发机制来处理。

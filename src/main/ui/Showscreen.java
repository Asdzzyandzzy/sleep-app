package ui;

import model.Sleep;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 仅需要从SleepApp接收必要的操作，而不是继承它
public class Showscreen extends Printer {

    Choose choose;

    public Showscreen() {
        typeWord("<html>Record sleep: press 'r'<br>"
                + "See all the sleep: type 'all'<br>"
                + "See the stat: press 's'<br>"
                + "Set Goal: press 'g'<br>"
                + "See the Goal: press 't'<br>"
                + "See the categorize summarize: press 'c'<br>"
                + "Clear all the data and restart: press 'R'<br>"
                + "save current data: type 'save'<br>"
                + "Reload the data: type 'load'<br>"
                + "Quit: press 'q'<br>"
                + "Type your command below and press Enter:<html>");

        System.out.println("show");
        choose = new Choose();
        button();
    }

    @Override
    public void button() {
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 从 JTextField 获取文本
                String inputText = inputField.getText();
                choose.chooseThing(inputText);
            }
        });
    }


}

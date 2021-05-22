/**
 *
 * @author nina
 */
package com.mycompany.hw;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleGui extends JFrame{
    private int state = 1;
    private int exit;
    private final int [][][] table = new int[][][] {{{3, 4}, {1, 0}}, 
                                                    {{6, 5}, {1, 0}}, 
                                                    {{6, 4}, {1, 0}},
                                                    {{2, 5}, {1, 0}},
                                                    {{6, 8}, {1, 0}},
                                                    {{7, 8}, {1, 0}},
                                                    {{ ,  }, { ,  }},
                                                    {{ ,  }, { ,  }}};
    private final String[] states = new String[] {"проснулась", "лежит на диване и смотрит YouTube", "гуляет вокруг дома",
                                                  "смотрит видеолекции", "делает домашнее задание", " ест домашнюю еду",
                                                  "Конец дня: Нина ложится спать", "Конец дня: Нина всю ночь готовится к РК"};
    private final String[] exits = new String[] {"Ее усталость растет", "Ее усталость уменьшается"};
    private final JButton button = new JButton("Начнем!");
    private final JButton button_2 = new JButton("Дальше");
    private final JButton button_3 = new JButton("Начать новый день");
    private final JLabel label_1 = new JLabel("Привет друг!!");
    private final JLabel label_2 = new JLabel("Это приложение - симулятор обычного дня Нины");
    private final JLabel label_3 = new JLabel("во время пандемии ужасной болезни Covid,");
    private final JLabel label_4 = new JLabel("если тебе интересно, скорее жми кнопку и не болей!!");
    private final JLabel label_5 = new JLabel("Какое у нее сейчас состояние?");
    private final JRadioButton radio_1 = new JRadioButton("Она хочет отдохнуть");
    private final JRadioButton radio_2 = new JRadioButton("Она хочет поработать");
    private final Container container = this.getContentPane();
    private final JLabel label_state = new JLabel("Нина "+states[0]);
    private final JLabel label_exit = new JLabel();
    
    private void updateUI(int state, int exit) {
        label_exit.setText(exits[exit]);
        if (state < 7){
            label_state.setText("Нина "+states[state-1]);
        } else {
             label_state.setText(states[state-1]);
             restart(); 
        }
        revalidate();
        repaint();
    }
    
    private void restart(){
        container.remove(radio_1);
        container.remove(radio_2);
        container.remove(label_5);
        container.remove(button_2);
        container.add(button_3);
        button_3.addActionListener(new Button3EventListener());

    }
    
    private void changeState(int signal){
        exit = table[state-1][1][signal];
        state = table [state-1][0][signal];
        updateUI(state, exit);
    }
    
    public SimpleGui(){
        super("День Нины во время пандемии");
        this.setBounds(500, 400, 500, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     
              
        container.setLayout(new GridLayout(6, 1, 1, 1));
        container.add(label_1);
        container.add(label_2);
        container.add(label_3);
        container.add(label_4);
        container.add(button);
        label_1.setHorizontalAlignment(JLabel.CENTER);
        label_2.setHorizontalAlignment(JLabel.CENTER);
        label_3.setHorizontalAlignment(JLabel.CENTER);
        label_4.setHorizontalAlignment(JLabel.CENTER);
        label_5.setHorizontalAlignment(JLabel.CENTER);
        label_state.setHorizontalAlignment(JLabel.CENTER);
        label_exit.setHorizontalAlignment(JLabel.CENTER);
        radio_1.setHorizontalAlignment(JLabel.CENTER);
        radio_2.setHorizontalAlignment(JLabel.CENTER);
        
        this.getContentPane().setBackground(Color.white);
        button.setBackground(Color.white);
        button_3.setBackground(Color.white);
        button_2.setBackground(Color.white);
        radio_1.setBackground(Color.white);
        radio_2.setBackground(Color.white);
        
        Font f = new Font("Serif", Font.BOLD, 15);
        label_1.setFont(f);
        label_2.setFont(f);
        label_3.setFont(f);
        label_4.setFont(f);
        label_5.setFont(f);
        label_exit.setFont(f);
        label_state.setFont(f);
        button.setFont(f);
        button_2.setFont(f);
        button_3.setFont(f);
        radio_1.setFont(f);
        radio_2.setFont(f);

        
        ButtonGroup group = new ButtonGroup();
        group.add(radio_1);
        group.add(radio_2);
        
        button.addActionListener(new ButtonEventListener());
        button_2.addActionListener(new Button2EventListener());
    }
    class ButtonEventListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            container.remove(label_1);
            container.remove(label_2);
            container.remove(label_3);
            container.remove(label_4);
            container.remove(button);
            container.add(label_state);
            container.add(label_exit);
            container.add(label_5);
            container.add(radio_1);
            radio_2.setSelected(true);
            container.add(radio_2);
            container.add(button_2);
            revalidate();
            repaint();
        }
        
    }
    class Button2EventListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
           if (radio_1.isSelected()) 
               changeState(0);
           else
               changeState(1);
        }
    }
    class Button3EventListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
           state = 1;
           label_exit.setText("");
           label_state.setText("Нина "+states[state-1]);
           container.remove(label_state);
           container.remove(label_exit);
           container.remove(button_3);
           container.add(label_1);
           container.add(label_2);
           container.add(label_3);
           container.add(label_4);
           container.add(button);
           revalidate();
           repaint();
        }
    }
}

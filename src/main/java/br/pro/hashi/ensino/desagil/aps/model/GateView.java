package br.pro.hashi.ensino.desagil.aps.model;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;


public class GateView extends JPanel implements ActionListener {

    private final Gate gate;

    private final JCheckBox inputCheck0;
    private final JCheckBox inputCheck1;
    private final JCheckBox outputCheck;

    Switch inputSwitch0 = new Switch();
    Switch inputSwitch1 = new Switch();

    public GateView(Gate gate) {
        this.gate = gate;
        int inputSize = gate.getInputSize();

        inputCheck0 = new JCheckBox();
        inputCheck0.setMnemonic(KeyEvent.VK_C);
        inputCheck0.setSelected(true);

        inputCheck1 = new JCheckBox();
        inputCheck1.setMnemonic(KeyEvent.VK_C);
        inputCheck1.setSelected(true);

        outputCheck = new JCheckBox();
        outputCheck.setMnemonic(KeyEvent.VK_C);
        outputCheck.setSelected(true);

        JLabel inputLabel = new JLabel("Input");
        JLabel outputLabel = new JLabel("Output");

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(inputLabel);
        add(inputCheck0);
        inputCheck0.addActionListener(this);
        gate.connect(0, inputSwitch0);

        if (inputSize == 2){
            add(inputCheck1);
            inputCheck1.addActionListener(this);
            gate.connect(1, inputSwitch1);
        }

        add(outputLabel);
        add(outputCheck);

        outputCheck.setEnabled(false);

        update();
    }

    private void update() {
        int inputSize = gate.getInputSize();

        if(inputCheck0.isSelected()){
            inputSwitch0.turnOn();
        }
        else{
            inputSwitch0.turnOff();
        }

        if (inputSize == 2) {
            if (inputCheck1.isSelected()) {
                inputSwitch1.turnOn();
            } else {
                inputSwitch1.turnOff();
            }
        }

        boolean returnRead = gate.read();
        outputCheck.setSelected(returnRead);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        update();
    }
}

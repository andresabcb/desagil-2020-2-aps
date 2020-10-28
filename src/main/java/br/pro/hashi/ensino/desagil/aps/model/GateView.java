package br.pro.hashi.ensino.desagil.aps.model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;


public class GateView extends FixedPanel implements ActionListener, MouseListener{

    private final Gate gate;

    private final JCheckBox inputCheck0;
    private final JCheckBox inputCheck1;
    //private final JCheckBox outputCheck;

    Switch inputSwitch0 = new Switch();
    Switch inputSwitch1 = new Switch();
    Light light = new Light(255,0,0);

    //private Color color;
    private Color true_color;
    private final Image image;

    public GateView(Gate gate) {
        super(200, 100);
        this.gate = gate;
        int inputSize = gate.getInputSize();

        inputCheck0 = new JCheckBox();
        inputCheck0.setMnemonic(KeyEvent.VK_C);
        inputCheck0.setSelected(true);

        inputCheck1 = new JCheckBox();
        inputCheck1.setMnemonic(KeyEvent.VK_C);
        inputCheck1.setSelected(true);

        JLabel inputLabel = new JLabel("Input");
        JLabel outputLabel = new JLabel("Output");

        add(inputLabel, 15, 5, 75, 25);

        if (inputSize == 1){
            add(inputCheck0, 20, 40, 25, 25);
            inputCheck0.addActionListener(this);
            gate.connect(0, inputSwitch0);
        }

        if (inputSize == 2){
            add(inputCheck0, 20, 30, 25, 25);
            inputCheck0.addActionListener(this);
            gate.connect(0, inputSwitch0);

            add(inputCheck1, 20, 50, 25, 25);
            inputCheck1.addActionListener(this);
            gate.connect(1, inputSwitch1);
        }

        true_color = Color.RED;

        String name = gate.toString() + ".png";
        URL url = getClass().getClassLoader().getResource(name);
        image = getToolkit().getImage(url);

        add(outputLabel, 140, 5, 75, 25);
        light.connect(0, gate);

        addMouseListener(this);

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
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        update();
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        int x = 158; //centro do círculo em x
        int y = 53; //centro do círculo em y
        int xmouse = event.getX();
        int ymouse = event.getY();

        // calculando se a distancia entre os dois pontos
        // está dentro do raio do círculo = 10
        int xquadrado = (x-xmouse)*(x-xmouse);
        int yquadrado = (y-ymouse)*(y-ymouse);
        double m = Math.sqrt(xquadrado+yquadrado);

        if (m<=10) {

            light.setColor(JColorChooser.showDialog(this, null, true_color));

            repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent event) {
    }

    @Override
    public void mouseReleased(MouseEvent event) {
    }

    @Override
    public void mouseEntered(MouseEvent event) {
    }

    @Override
    public void mouseExited(MouseEvent event) {
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        g.drawImage(image, 50, 30, 90, 45, this);

        g.setColor(light.getColor());
        g.fillOval(148, 43, 20, 20);

        getToolkit().sync();

        update();
    }
}

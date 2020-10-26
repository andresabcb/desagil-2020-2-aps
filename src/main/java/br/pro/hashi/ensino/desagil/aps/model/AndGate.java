package br.pro.hashi.ensino.desagil.aps.model;

public class AndGate extends Gate {
    private final NandGate[] nand;

    public AndGate() {
        super("AND", 2);

        // são dois nands
        nand = new NandGate[2];

        nand[0] = new NandGate();
        nand[1] = new NandGate();
    }

    @Override
    public boolean read() {
        return nand[1].read();
    }

    @Override
    public void connect(int inputIndex, Emitter emitter) {
        if (inputIndex < 0 || inputIndex > 1) {
            throw new IndexOutOfBoundsException(inputIndex);
        }

        // uma entrada de cada nand é definida em cada connect do Teste
        nand[0].connect(inputIndex, emitter);
        nand[1].connect(inputIndex, nand[0]);
    }

}

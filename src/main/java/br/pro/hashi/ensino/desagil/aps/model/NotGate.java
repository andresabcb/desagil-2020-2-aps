package br.pro.hashi.ensino.desagil.aps.model;

public class NotGate extends Gate {
    private final NandGate nand;

    public NotGate() {
        super("NOT", 1);
        nand = new NandGate();
    }

    @Override
    public void connect(int inputIndex, Emitter emitter) {
        if (inputIndex != 0) {
            throw new IndexOutOfBoundsException(inputIndex);
        }
        // inputIndex são as entradas
        nand.connect(0, emitter);
        nand.connect(1, emitter);
    }

    @Override
    public boolean read() {
        return nand.read();
    }
}

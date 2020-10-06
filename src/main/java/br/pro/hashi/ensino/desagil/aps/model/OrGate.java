package br.pro.hashi.ensino.desagil.aps.model;

public class OrGate extends Gate {
    private final NandGate[] nand;

    public OrGate() {
        super("OR", 2);

        // são 3 nands
        nand = new NandGate[3];

        nand[0] = new NandGate();
        nand[1] = new NandGate();
        nand[2] = new NandGate();
    }

    @Override
    public boolean read() {
        return nand[2].read();
    }

    @Override
    public void connect(int inputIndex, Emitter emitter) {
        if (inputIndex < 0 || inputIndex > 1) {
            throw new IndexOutOfBoundsException(inputIndex);
        }
        nand[inputIndex].connect(0, emitter); // as duplas de entradas são iguais
        nand[inputIndex].connect(1, emitter);

        nand[2].connect(inputIndex,nand[inputIndex]); // o último nand usa os anteriores nas entradas
    }
}

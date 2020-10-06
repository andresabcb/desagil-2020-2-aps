package br.pro.hashi.ensino.desagil.aps.model;

public class XorGate extends Gate {
    private final NandGate[] nand;

    public XorGate() {
        super("XOR",2);

        // são 4 nands
        nand = new NandGate[4];

        nand[0] = new NandGate();
        nand[1] = new NandGate();
        nand[2] = new NandGate();
        nand[3] = new NandGate();
    }

    @Override
    public boolean read() {
        return nand[3].read();
    }

    @Override
    public void connect(int inputIndex, Emitter emitter) {
        if (inputIndex < 0 || inputIndex > 1) {
            throw new IndexOutOfBoundsException(inputIndex);
        }
        nand[2].connect(inputIndex, emitter); // o 2 é o primeiro (da esquerda para a direita)
        nand[inputIndex].connect(0, emitter); // o 0 e o 1 são os do "meio"
        nand[inputIndex].connect(1, nand[2]); // que se adequam às duas situações
        nand[3].connect(inputIndex, nand[inputIndex]); // o último recebe os valores anteriores

    }

}

package codeGenerator;

import node.Program;

public class CodeGenerator {
    private Program program;

    public CodeGenerator() {
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public void generate() throws Exception {
        if (program == null) {
            throw new Exception("Program not set");
        }
        
        // 기계어 코드를 생성
        System.out.println("\n--------------- machine code ---------------");
        for (String line : program.generate()) {
            System.out.println(line);
        }
    }

    public void initialize() { }
    public void finish() { }
}

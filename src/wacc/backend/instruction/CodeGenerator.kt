package wacc.backend.instruction

class CodeGenerator {

//    private fun translateProgram(program: ProgramAST): List<Instruction> {
//        // Translate function definitions
//        val functionInstructions = mutableListOf<Instruction>()
//        for (function in program.funcList) {
//            functionInstructions.addAll(translateFunction(function))
//        }
//
//        val mainInstructions = mutableListOf<Instruction>()
//        mainInstructions.add(FunctionLabel("main"))
//        // AI: PUSH {lr}
//        mainInstructions.add(PushInstr(Register.LR))
//
//        // Visit main program and add to instruction list
//        mainInstructions.addAll(translateStatBlock(program.stats))
//
//        // AI: LDR r0, =0
//        mainInstructions.add(LoadInstr("0", Register.R0))
//        // AI: POP {pc}
//        mainInstructions.add(PopInstr(Register.PC))
//
//
//    }

//    private fun translateStatement(stat: StatAST): List<Instruction> {
//            SkipStatAST -> mutableListOf()
//            DeclareStatAST -> translateDeclare()
//            AssignStatAST -> translateAssign()
//            ReadStatAST -> translateRead()
//
//            ActionStat.FREE -> translateFree()
//            ActionStat.RETURN -> translateReturn()
//            ActionStat.EXIT -> translateExit()
//            ActionStat.PRINT -> translatePrint()
//            ActionStat.PRINTLN -> translatePrintLn()

//            IfStatAST -> translateIf()
//            WhileStatAST -> translateWhile)
//            BlockStatAST -> translateStatBlock()
//            MultiStatAST -> translateStatMulti()

//        }
}




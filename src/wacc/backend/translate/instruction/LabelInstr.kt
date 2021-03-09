package wacc.backend.translate.instruction

/**
 * Represents Label instructions
 *
 * @property name String representing the label
 */
abstract class LabelInstr(val name: String) : Instruction {
    override fun toArm(): String {
        return "$name:"
    }

    override fun toX86(): String {
        return "$name:"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is LabelInstr) return false

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }

    override fun toString(): String {
        return toArm()
    }
}

class Label(label: String) : LabelInstr(label)

class FunctionLabel(functionName: String) : LabelInstr("f_$functionName")


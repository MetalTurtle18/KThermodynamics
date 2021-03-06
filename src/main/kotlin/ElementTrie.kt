/**
 * An implementation of a trie but using Elements instead of Strings.
 */
class ElementTrie {
    private var root = ElementTrieNode()

    fun add(molecule: Molecule) {
        recursiveInsertion(molecule, molecule.elements, root)
    }

    private fun recursiveInsertion(molecule: Molecule, elements: Array<out Element>, current: ElementTrieNode) {
        if (elements.isEmpty()) return // If this is an empty molecule it's done
        // Create the new child if it didn't exist before
        var node = current.children[elements[0].number - 1]
        if (node == null)
            node = ElementTrieNode()
        node.isMolecule = elements.size == 1 || node.isMolecule
        if (node.isMolecule && elements.size == 1)
            node.molecule = molecule
        current.children[elements[0].number - 1] = node // Set the child
        recursiveInsertion(molecule, elements.sliceArray(0 until elements.size - 1), node) // Recurse
    }

    /**
     * Print out every molecule in the trie.
     *
     * @param transform A lambda function to transform the molecule string before printing it.
     */
    fun print(transform: (Molecule) -> String = { it.toString() }) {
        recursivePrint(root, transform)
    }

    private fun recursivePrint(current: ElementTrieNode, transform: (Molecule) -> String) {
        if (current.isMolecule) // If this is a molecule, print it
            println(transform(current.molecule!!))
        for (child in current.children) { // Then recursively print every child
            recursivePrint(child ?: continue, transform)
        }
    }

    // Searching does not work
    fun search(moleculeName: String): Molecule? {
        if (moleculeName.isEmpty()) return null
        val elements = mutableListOf<String>()
        var cur = ""
        for (c in moleculeName) {
            if (c.isDigit()) {
                for (i in 0..c.digitToInt())
                    elements += cur
                cur = ""
            } else if (c.isLowerCase()){
                cur += c
            } else {
                if (cur.isNotEmpty()) elements += cur
                cur = c.toString()
            }
        }
        elements += cur
        return recursiveSearch(elements.toTypedArray(), root)
    }

    private fun recursiveSearch(elements: Array<String>, current: ElementTrieNode): Molecule? {
        if (elements.isEmpty()) return null
        val child = current.children[(Element symbol elements[0]).number] ?: return null
        if (elements.size == 1)
            return if (child.isMolecule)
                child.molecule
            else
                null
        return recursiveSearch(elements.sliceArray(0 until elements.size - 1), child)

    }

}

/**
 * A node in the trie.
 */
class ElementTrieNode {
    val children = Array<ElementTrieNode?>(118) { null }
    var isMolecule = false
    var molecule: Molecule? = null
}

/**
 * A class representing a single atom/element
 */
class Element private constructor(val symbol: String, val number: Int) {
    /**
     * The companion object has every element type already made
     */
    companion object {
        val HYDROGEN = Element("H", 1)
        val HELIUM = Element("He", 2)
        val LITHIUM = Element("Li", 3)
        val BERYLLIUM = Element("Be", 4)
        val BORON = Element("B", 5)
        val CARBON = Element("C", 6)
        val NITROGEN = Element("N", 7)
        val OXYGEN = Element("O", 8)
        val FLUORINE = Element("F", 9)
        val NEON = Element("Ne", 10)
        val SODIUM = Element("Na", 11)
        val MAGNESIUM = Element("Mg", 12)
        val ALUMINUM = Element("Al", 13)
        val SILICON = Element("Si", 14)
        val PHOSPHORUS = Element("P", 15)
        val SULFUR = Element("S", 16)
        val CHLORINE = Element("Cl", 17)
        val ARGON = Element("Ar", 18)
        val POTASSIUM = Element("K", 19)
        val CALCIUM = Element("Ca", 20)
        val SCANDIUM = Element("Sc", 21)
        val TITANIUM = Element("Ti", 22)
        val VANADIUM = Element("V", 23)
        val CHROMIUM = Element("Cr", 24)
        val MANGANESE = Element("Mn", 25)
        val IRON = Element("Fe", 26)
        val COBALT = Element("Co", 27)
        val NICKEL = Element("Ni", 28)
        val COPPER = Element("Cu", 29)
        val ZINC = Element("Zn", 30)
        val GALLIUM = Element("Ga", 31)
        val GERMANIUM = Element("Ge", 32)
        val ARSENIC = Element("As", 33)
        val SELENIUM = Element("Se", 34)
        val BROMINE = Element("Br", 35)
        val KRYPTON = Element("Kr", 36)
        val RUBIDIUM = Element("Rb", 37)
        val STRONTIUM = Element("Sr", 38)
        val YTTRIUM = Element("Y", 39)
        val ZIRCONIUM = Element("Zr", 40)
        val NIOBIUM = Element("Nb", 41)
        val MOLYBDENUM = Element("Mo", 42)
        val TECHNETIUM = Element("Tc", 43)
        val RUTHENIUM = Element("Ru", 44)
        val RHODIUM = Element("Rh", 45)
        val PALLADIUM = Element("Pd", 46)
        val SILVER = Element("Ag", 47)
        val CADMIUM = Element("Cd", 48)
        val INDIUM = Element("In", 49)
        val TIN = Element("Sn", 50)
        val ANTIMONY = Element("Sb", 51)
        val TELLURIUM = Element("Te", 52)
        val IODINE = Element("I", 53)
        val XENON = Element("Xe", 54)
        val CESIUM = Element("Cs", 55)
        val BARIUM = Element("Ba", 56)
        val LANTHANUM = Element("La", 57)
        val CERIUM = Element("Ce", 58)
        val PRASEODYMIUM = Element("Pr", 59)
        val NEODYMIUM = Element("Nd", 60)
        val PROMETHIUM = Element("Pm", 61)
        val SAMARIUM = Element("Sm", 62)
        val EUROPIUM = Element("Eu", 63)
        val GADOLINIUM = Element("Gd", 64)
        val TERBIUM = Element("Tb", 65)
        val DYSPROSIUM = Element("Dy", 66)
        val HOLMIUM = Element("Ho", 67)
        val ERBIUM = Element("Er", 68)
        val THULIUM = Element("Tm", 69)
        val YTTERBIUM = Element("Yb", 70)
        val LUTETIUM = Element("Lu", 71)
        val HAFNIUM = Element("Hf", 72)
        val TANTALUM = Element("Ta", 73)
        val TUNGSTEN = Element("W", 74)
        val RHENIUM = Element("Re", 75)
        val OSMIUM = Element("Os", 76)
        val IRIDIUM = Element("Ir", 77)
        val PLATINUM = Element("Pt", 78)
        val GOLD = Element("Au", 79)
        val MERCURY = Element("Hg", 80)
        val THALLIUM = Element("Tl", 81)
        val LEAD = Element("Pb", 82)
        val BISMUTH = Element("Bi", 83)
        val POLONIUM = Element("Po", 84)
        val ASTATINE = Element("At", 85)
        val RADON = Element("Rn", 86)
        val FRANCIUM = Element("Fr", 87)
        val RADIUM = Element("Ra", 88)
        val ACTINIUM = Element("Ac", 89)
        val THORIUM = Element("Th", 90)
        val PROTACTINIUM = Element("Pa", 91)
        val URANIUM = Element("U", 92)
        val NEPTUNIUM = Element("Np", 93)
        val PLUTONIUM = Element("Pu", 94)
        val AMERICIUM = Element("Am", 95)
        val CURIUM = Element("Cm", 96)
        val BERKELIUM = Element("Bk", 97)
        val CALIFORNIUM = Element("Cf", 98)
        val EINSTEINIUM = Element("Es", 99)
        val FERMIUM = Element("Fm", 100)
        val MENDELEVIUM = Element("Md", 101)
        val NOBELIUM = Element("No", 102)
        val LAWRENCIUM = Element("Lr", 103)
        val RUTHERFORDIUM = Element("Rf", 104)
        val DUBNIUM = Element("Db", 105)
        val SEABORGIUM = Element("Sg", 106)
        val BOHRIUM = Element("Bh", 107)
        val HASSIUM = Element("Hs", 108)
        val MEITNERIUM = Element("Mt", 109)
        val DARMSTADTIUM = Element("Ds", 110)
        val ROENTGENIUM = Element("Rg", 111)
        val COPERNICIUM = Element("Cn", 112)
        val NIHONIUM = Element("Nh", 113)
        val FLEROVIUM = Element("Fl", 114)
        val MOSCOVIUM = Element("Mc", 115)
        val LIVERMORIUM = Element("Lv", 116)
        val TENNESSINE = Element("Ts", 117)
        val OGANESSON = Element("Og", 118)

        /**
         * A method to get an element by its atomic number
         *
         * (This doesn't seem to be needed, but it took a lot of work to make, so I won't remove it yet)
         */
        infix fun number(index: Int) = mapOf(
            1 to HYDROGEN,
            2 to HELIUM,
            3 to LITHIUM,
            4 to BERYLLIUM,
            5 to BORON,
            6 to CARBON,
            7 to NITROGEN,
            8 to OXYGEN,
            9 to FLUORINE,
            10 to NEON,
            11 to SODIUM,
            12 to MAGNESIUM,
            13 to ALUMINUM,
            14 to SILICON,
            15 to PHOSPHORUS,
            16 to SULFUR,
            17 to CHLORINE,
            18 to ARGON,
            19 to POTASSIUM,
            20 to CALCIUM,
            21 to SCANDIUM,
            22 to TITANIUM,
            23 to VANADIUM,
            24 to CHROMIUM,
            25 to MANGANESE,
            26 to IRON,
            27 to COBALT,
            28 to NICKEL,
            29 to COPPER,
            30 to ZINC,
            31 to GALLIUM,
            32 to GERMANIUM,
            33 to ARSENIC,
            34 to SELENIUM,
            35 to BROMINE,
            36 to KRYPTON,
            37 to RUBIDIUM,
            38 to STRONTIUM,
            39 to YTTRIUM,
            40 to ZIRCONIUM,
            41 to NIOBIUM,
            42 to MOLYBDENUM,
            43 to TECHNETIUM,
            44 to RUTHENIUM,
            45 to RHODIUM,
            46 to PALLADIUM,
            47 to SILVER,
            48 to CADMIUM,
            49 to INDIUM,
            50 to TIN,
            51 to ANTIMONY,
            52 to TELLURIUM,
            53 to IODINE,
            54 to XENON,
            55 to CESIUM,
            56 to BARIUM,
            57 to LANTHANUM,
            58 to CERIUM,
            59 to PRASEODYMIUM,
            60 to NEODYMIUM,
            61 to PROMETHIUM,
            62 to SAMARIUM,
            63 to EUROPIUM,
            64 to GADOLINIUM,
            65 to TERBIUM,
            66 to DYSPROSIUM,
            67 to HOLMIUM,
            68 to ERBIUM,
            69 to THULIUM,
            70 to YTTERBIUM,
            71 to LUTETIUM,
            72 to HAFNIUM,
            73 to TANTALUM,
            74 to TUNGSTEN,
            75 to RHENIUM,
            76 to OSMIUM,
            77 to IRIDIUM,
            78 to PLATINUM,
            79 to GOLD,
            80 to MERCURY,
            81 to THALLIUM,
            82 to LEAD,
            83 to BISMUTH,
            84 to POLONIUM,
            85 to ASTATINE,
            86 to RADON,
            87 to FRANCIUM,
            88 to RADIUM,
            89 to ACTINIUM,
            90 to THORIUM,
            91 to PROTACTINIUM,
            92 to URANIUM,
            93 to NEPTUNIUM,
            94 to PLUTONIUM,
            95 to AMERICIUM,
            96 to CURIUM,
            97 to BERKELIUM,
            98 to CALIFORNIUM,
            99 to EINSTEINIUM,
            100 to FERMIUM,
            101 to MENDELEVIUM,
            102 to NOBELIUM,
            103 to LAWRENCIUM,
            104 to RUTHERFORDIUM,
            105 to DUBNIUM,
            106 to SEABORGIUM,
            107 to BOHRIUM,
            108 to HASSIUM,
            109 to MEITNERIUM,
            110 to DARMSTADTIUM,
            111 to ROENTGENIUM,
            112 to COPERNICIUM,
            113 to NIHONIUM,
            114 to FLEROVIUM,
            115 to MOSCOVIUM,
            116 to LIVERMORIUM,
            117 to TENNESSINE,
            118 to OGANESSON
        )[index] ?: throw IllegalArgumentException("No element with index $index")

        /**
         * A method to get an element by its symbol
         */
        infix fun symbol(symbol: String) = mapOf(
            "H" to HYDROGEN,
            "He" to HELIUM,
            "Li" to LITHIUM,
            "Be" to BERYLLIUM,
            "B" to BORON,
            "C" to CARBON,
            "N" to NITROGEN,
            "O" to OXYGEN,
            "F" to FLUORINE,
            "Ne" to NEON,
            "Na" to SODIUM,
            "Mg" to MAGNESIUM,
            "Al" to ALUMINUM,
            "Si" to SILICON,
            "P" to PHOSPHORUS,
            "S" to SULFUR,
            "Cl" to CHLORINE,
            "Ar" to ARGON,
            "K" to POTASSIUM,
            "Ca" to CALCIUM,
            "Sc" to SCANDIUM,
            "Ti" to TITANIUM,
            "V" to VANADIUM,
            "Cr" to CHROMIUM,
            "Mn" to MANGANESE,
            "Fe" to IRON,
            "Co" to COPPER,
            "Ni" to NICKEL,
            "Cu" to ZINC,
            "Zn" to ZINC,
            "Ga" to GALLIUM,
            "Ge" to GERMANIUM,
            "As" to ARSENIC,
            "Se" to SELENIUM,
            "Br" to BROMINE,
            "Kr" to KRYPTON,
            "Rb" to RUBIDIUM,
            "Sr" to STRONTIUM,
            "Y" to YTTRIUM,
            "Zr" to ZIRCONIUM,
            "Nb" to NIOBIUM,
            "Mo" to MOLYBDENUM,
            "Tc" to TECHNETIUM,
            "Ru" to RUTHENIUM,
            "Rh" to RHODIUM,
            "Pd" to PALLADIUM,
            "Ag" to SILVER,
            "Cd" to CADMIUM,
            "In" to INDIUM,
            "Sn" to TIN,
            "Sb" to ANTIMONY,
            "Te" to TELLURIUM,
            "I" to IODINE,
            "Xe" to XENON,
            "Cs" to CESIUM,
            "Ba" to BARIUM,
            "La" to LANTHANUM,
            "Ce" to CERIUM,
            "Pr" to PRASEODYMIUM,
            "Nd" to NEODYMIUM,
            "Pm" to PROMETHIUM,
            "Sm" to SAMARIUM,
            "Eu" to EUROPIUM,
            "Gd" to GADOLINIUM,
            "Tb" to TERBIUM,
            "Dy" to DYSPROSIUM,
            "Ho" to HOLMIUM,
            "Er" to ERBIUM,
            "Tm" to THULIUM,
            "Yb" to YTTERBIUM,
            "Lu" to LUTETIUM,
            "Hf" to HAFNIUM,
            "Ta" to TANTALUM,
            "W" to TUNGSTEN,
            "Re" to RHENIUM,
            "Os" to OSMIUM,
            "Ir" to IRIDIUM,
            "Pt" to PLATINUM,
            "Au" to GOLD,
            "Hg" to MERCURY,
            "Tl" to THALLIUM,
            "Pb" to LEAD,
            "Bi" to BISMUTH,
            "Po" to POLONIUM,
            "At" to ASTATINE,
            "Rn" to RADON,
            "Fr" to FRANCIUM,
            "Ra" to RADIUM,
            "Ac" to ACTINIUM,
            "Th" to THORIUM,
            "Pa" to PROTACTINIUM,
            "U" to URANIUM,
            "Np" to NEPTUNIUM,
            "Pu" to PLUTONIUM,
            "Am" to AMERICIUM,
            "Cm" to CURIUM,
            "Bk" to BERKELIUM,
            "Cf" to CALIFORNIUM,
            "Es" to EINSTEINIUM,
            "Fm" to FERMIUM,
            "Md" to MENDELEVIUM,
            "No" to NOBELIUM,
            "Lr" to LAWRENCIUM,
            "Rf" to RUTHERFORDIUM,
            "Db" to DUBNIUM,
            "Sg" to SEABORGIUM,
            "Bh" to BOHRIUM,
            "Hs" to HASSIUM,
            "Mt" to MEITNERIUM,
            "Ds" to DARMSTADTIUM,
            "Rg" to ROENTGENIUM,
            "Cn" to COPERNICIUM,
            "Nh" to NIHONIUM,
            "Fl" to FLEROVIUM,
            "Mc" to MOSCOVIUM,
            "Lv" to LIVERMORIUM,
            "Ts" to TENNESSINE,
            "Og" to OGANESSON
        )[symbol] ?: throw IllegalArgumentException("No element with symbol $symbol")
    }
}

/**
 * A class representing a full molecule
 *
 * It is constructed with a vararg list of elements, then a lambda builder for the thermodynamic information
 */
class Molecule constructor(vararg val elements: Element, informationBuilder: Information.() -> Unit = { }) {
    val information = Information().apply(informationBuilder)

    /**
     * The companion object has some pre-made molecules
     */
    companion object {
        val Al = Molecule(Element.ALUMINUM) {
            solid {
                enthalpy = 0.0
                entropy = 28.3
            }

            gas {
                enthalpy = 324.4
                entropy = 164.54
            }
        }
        val Al2O3 = Molecule(Element.ALUMINUM, Element.ALUMINUM, Element.OXYGEN, Element.OXYGEN, Element.OXYGEN) {
            solid {
                enthalpy = -1676.0
                entropy = 50.92
            }
        }
    }

    /**
     * Convert the molecule to a string with proper formatting
     */
    override fun toString(): String {
        /**
         * Convert a given number to a string of that number as subscript
         */
        fun Int.sub(): String {
            val subscripts = mapOf(
                '0' to "???",
                '1' to "???",
                '2' to "???",
                '3' to "???",
                '4' to "???",
                '5' to "???",
                '6' to "???",
                '7' to "???",
                '8' to "???",
                '9' to "???"
            )
            if (this <= 1) return ""
            return this.toString().toCharArray().joinToString(separator = "") { subscripts[it]!! }
        }
        // This converts a molecule to a string with proper subscript formatting
        var result = ""
        val current = mutableListOf<String>()
        for (atom in elements) {
            if (current.size == 0)
                current += atom.symbol
            else {
                if (current[0] == atom.symbol)
                    current += atom.symbol
                else {
                    result += current[0] + current.size.sub()
                    current.clear()
                    current += atom.symbol
                }

            }
        }
        result += current[0] + current.size.sub()
        return result
    }

    /**
     * A class to store thermodynamic information about a molecule by state of matter
     */
    class Information {
        var solid: Properties? = null
            private set
        var liquid: Properties? = null
            private set
        var gas: Properties? = null
            private set
        var aqueous: Properties? = null
            private set

        // These are four builder methods for the thermodynamic properties

        fun solid(solid: Properties.() -> Unit) {
            this.solid = Properties().apply(solid)
        }

        fun liquid(liquid: Properties.() -> Unit) {
            this.liquid = Properties().apply(liquid)
        }

        fun gas(gas: Properties.() -> Unit) {
            this.gas = Properties().apply(gas)
        }

        fun aqueous(aqueous: Properties.() -> Unit) {
            this.aqueous = Properties().apply(aqueous)
        }

        override fun toString() = """
            |Information:
            |   Solid: ${solid ?: "none"}
            |   Liquid: ${liquid ?: "none"}
            |   Gas: ${gas ?: "none"}
            |   Aqueous: ${aqueous ?: "none"}
        """.trimMargin()
    }

    /**
     * A class to store thermodynamic properties of a certain state of matter of a molecule
     */
    class Properties {
        var enthalpy: Double? = null
        var entropy: Double? = null
        var otherName: String? = null

        override fun toString() =
            "${if (otherName == null) "" else "\"$otherName: \""}enthalpy = $enthalpy kJ/mol; entropy = $entropy J/K mol"
    }
}
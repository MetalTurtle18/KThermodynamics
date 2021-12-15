fun main() {
    // ****************************************
    // Test 1: Basic inserting
    // ****************************************
    val trie = ElementTrie()
    trie.add(Molecule.Al)
    trie.add(Molecule.Al2O3)
    println(
        """
        ****************************************
        Test 1: Basic inserting
        ****************************************
        Expected:
        
        Al:
   	        Information:
	            Solid: enthalpy = 0.0 kJ/mol; entropy = 28.3 J/K mol
	            Liquid: none
	            Gas: enthalpy = 324.4 kJ/mol; entropy = 164.54 J/K mol
	            Aqueous: none
        Al₂O₃:
   	        Information:
	            Solid: enthalpy = -1676.0 kJ/mol; entropy = 50.92 J/K mol
	            Liquid: none
	            Gas: none
	            Aqueous: none
        
        Got:
        
    """.trimIndent()
    )
    trie.print {
        """
            |$it:
            |   ${it.information.toString().replaceIndent("\t")}
        """.trimMargin()
    }

    // ****************************************
    // Test 2: Inserting custom molecules
    // ****************************************
    trie.add(
        Molecule(Element.HYDROGEN, Element.HYDROGEN, Element.OXYGEN) {
            liquid {
                enthalpy = -285.83
                entropy = 70.0
                otherName = "water"
            }
            gas {
                enthalpy = -241.82
                entropy = 188.8
                otherName = "steam"
            }
        }
    )
    println(
        """
        ****************************************
        Test 2: Inserting custom molecules
        ****************************************
        Expected:
        
        H₂O:
            Information:
                Solid: none
                Liquid: water: enthalpy = -285.83 kJ/mol; entropy = 70.0 J/K mol
                Gas: steam: enthalpy = -241.82 kJ/mol; entropy = 188.8 J/K mol
                Aqueous: none
        Al:
   	        Information:
	            Solid: enthalpy = 0.0 kJ/mol; entropy = 28.3 J/K mol
	            Liquid: none
	            Gas: enthalpy = 324.4 kJ/mol; entropy = 164.54 J/K mol
	            Aqueous: none
        Al₂O₃:
   	        Information:
	            Solid: enthalpy = -1676.0 kJ/mol; entropy = 50.92 J/K mol
	            Liquid: none
	            Gas: none
	            Aqueous: none

        Got:
    """.trimIndent()
    )
    trie.print {
        """
            |$it:
            |   ${it.information.toString().replaceIndent("\t")}
        """.trimMargin()
    }
}
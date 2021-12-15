fun main() {
    val trie = ElementTrie()
    println(trie.search("Al"))
    trie.add(Molecule.Al)
    println(trie.search("Al"))
}
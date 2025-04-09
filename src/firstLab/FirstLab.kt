package firstLab

class FirstLab {
    private val poems = mutableListOf(
        Poem("author1","Весна", "Весна приходит, снег уходит, цветы расцветают..."), //48
        Poem("author1","Утро", "Роса блестит на траве, солнце встречает день, а я пишу код..."), //61
        Poem("author1","Ночь", "Темная ночь, звезды горят, тихий ветер шумит..."), //47
    )

    private fun getSortedPoems(): List<Poem> {
        return poems.sortedBy { it.text.length }
    }

    fun displaySortedPoem(){
        val sortedPoems = getSortedPoems()
        for (poem in sortedPoems){
            println("Название: ${poem.title}, " +
                    "длинна текста стиха: ${poem.text.length}")
        }
    }
}
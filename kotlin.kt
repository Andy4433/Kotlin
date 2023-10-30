var alunos= arrayListOf<String>();
fun main(){

    println("Quantos alunos voce tem?")
    var y= readln().toInt()
    println("digite o nome do seus alunos")
    loops(y = y)
    nomes()
    println("digieta a nota dos seus alunos")
    criaMatriz(y=y)

}
fun loops(y:Int){
    var j =0
    while(j < y){
        var al= readln().toString()
        alunos.add(al)
        j++
    }
}
fun nomes(){
    for (i in alunos){
        println(i)
    }
}
fun apagar(){
    var no = readln().toString()
    alunos.remove(no)
}
fun escolha(){

    var es= readln().toString()
    es = es.uppercase()
    if (es.equals("S")){
        println("digite o nome do aluno: ")
        apagar()
    }else{
        println("Nao removendo nenhum aluno")
    }
}
fun addicionar(){
    var es= readln().toString()
    es = es.uppercase()
    if (es.equals("S")) {
        println("digite o nome do aluno: ")
        var al = readln().toString()
        alunos.add(al)
    }
}

fun criaMatriz(y: Int): List<List<Float>> {
    val matriz = mutableListOf<MutableList<Float>>()
    for (i in 0 until y) {
        matriz.add(mutableListOf<Float>())
    }
    for (i in 0 until y) {
        for (j in 0 until 3) {
            println("Insira a nota  do aluno ${alunos[i]} ${i + 1}, ${j + 1}:")
            val nota = readln().toFloat()
            matriz[i].add(nota)
        }
    }
    println(matriz)
    var i =0
    while (i<y){
        var matrizm=(matriz[i].sum())/3
        println("---------------------------------------------------------")
        println("A nota do aluno ${alunos[i]}: "+matrizm)
        println("---------------------------------------------------------")
        i++
    }
    var x=0
    while(x<y){
        println("---------------------------------------------------------")
        println("a maior nota do aluno ${alunos[x]}: "+matriz[x].max())
        println("a menor nota do aluno ${alunos[x]}: "+matriz[x].min())
        println("---------------------------------------------------------")
        x++
    }
    return matriz
}
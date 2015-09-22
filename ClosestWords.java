/* Labb 2 i DD1352 Algoritmer, datastrukturer och komplexitet    */
/* Se labbanvisning under kurswebben https://www.kth.se/social/course/DD1352 */
/* Ursprunglig författare: Viggo Kann KTH viggo@nada.kth.se      */
import java.util.LinkedList;
import java.util.List;

public class ClosestWords {
  LinkedList<String> closestWords = null;

  int closestDistance = -1;
  int[][] matrix;
  String prevWord = "";

  int Distance(String w1, String w2) {
	int matching = 0;
	for (int i = 0; i < Math.min(prevWord.length(), w2.length()); i++){
	    if (w1.charAt(i) == w2.charAt(i)) {
 		    matching++;
        }
    }
    for (int j = matching + 1; j <  w2.length() + 1; j++){
        matrix[0][j] = j;
    }
    for(int j = matching + 1; j < w2.length() +1; j++) {
        for(int i = matching + 1; i < matrix.length; i++){
            if (w1.charAt(i-1) == w2.charAt(j-1)){
                matrix[i][j] = matrix[i-1][j-1];
            } else {
                matrix[i][j] = Math.min(Math.min((matrix[i][j-1]+1),(matrix[i-1][j]+1)), (matrix[i-1][j-1]+1));
            }
        }
       // for (int i = 1; i < matrix.length; i++){
       //   System.out.print(matrix[i][j]);
       // }
       // System.out.println();
    }
    //System.out.println();
    return matrix[w1.length()][w2.length()];
    //return partDist(w1, w2, w1.length(), w2.length());
  }

  public ClosestWords(String w, List<String> wordList) {
    // Create a matrix based on the misspelled word
    matrix = new int[w.length() + 1][40];
    for (int i = 0; i < matrix.length; i++){
        matrix[i][0] = i;
    }

    for (String s : wordList) {
      int dist = Distance(w, s);
      // System.out.println("d(" + w + "," + s + ")=" + dist);
      if (dist < closestDistance || closestDistance == -1) {
        closestDistance = dist;
        closestWords = new LinkedList<String>();
        closestWords.add(s);
      }
      else if (dist == closestDistance)
        closestWords.add(s);
    }
  }

  int getMinDistance() {
    return closestDistance;
  }

  List<String> getClosestWords() {
    return closestWords;
  }
}

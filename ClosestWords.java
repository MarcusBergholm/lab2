/* Labb 2 i DD1352 Algoritmer, datastrukturer och komplexitet    */
/* Se labbanvisning under kurswebben https://www.kth.se/social/course/DD1352 */
/* Ursprunglig författare: Viggo Kann KTH viggo@nada.kth.se      */
import java.util.LinkedList;
import java.util.List;

public class ClosestWords {
  LinkedList<String> closestWords = null;

  int closestDistance = -1;

  int partDist(String w1, String w2, int w1len, int w2len) {
    if (w1len == 0)
      return w2len;
    if (w2len == 0)
      return w1len;
    int res = partDist(w1, w2, w1len - 1, w2len - 1) +
	(w1.charAt(w1len - 1) == w2.charAt(w2len - 1) ? 0 : 1);
    int addLetter = partDist(w1, w2, w1len - 1, w2len) + 1;
    if (addLetter < res)
      res = addLetter;
    int deleteLetter = partDist(w1, w2, w1len, w2len - 1) + 1;
    if (deleteLetter < res)
      res = deleteLetter;
    return res;
  }

  int Distance(String w1, String w2) {
    int[][] matrix = new int[w1.length()+1 ][w2.length()+1];
    for (int i = 0; i < matrix.length; i++){
        matrix[i][0] = i;
    }
    for (int j = 0; j <  matrix[0].length; j++){
        matrix[0][j] = j;
    }
    for(int j = 1; j < matrix[0].length; j++) {
        for(int i = 1; i < matrix.length; i++){
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

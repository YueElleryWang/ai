//Wang Yue
//2019.May.9
//CSCI 4511 final, MDP grid world

import java.io.*;
import java.util.*;

public class GridWorldMain{
  public static void main(String[] args) throws Exception{
    String filePath;
    if (args.length == 1)
      filePath = args[0];
    else
      filePath = "../input/i1.txt";
    GridWorld gw = readInput(filePath);
    if (gw == null)
      return;
    ValueIteration.printQStar(gw);
    ValueIteration.printVStar(gw);
    PolicyIteration.printVStar(gw);
  }

  //read from input file, reate GridWorld instance
  static GridWorld readInput(String fileName) throws Exception {
    int n,m;
    try{
      File file = new File(fileName);
      BufferedReader br = new BufferedReader(new FileReader(file));
      String st;
      String[] temp;
      st = br.readLine();
      while(st.charAt(0)=='#')
        st = br.readLine();
      temp = st.split(",",2);
      n = Integer.parseInt(temp[0]);
      m = Integer.parseInt(temp[1]);

      char[][] gridWorld = new char[n][m];
      int [][] gridWorldValue = new int[n][m];
      st = br.readLine();
      while(st.charAt(0)=='#')
        st = br.readLine();
      for (int i = 0; i < n; i++){
        temp = st.split(",",m);
        for (int j = 0; j<m; j++){
          gridWorld[i][j] = temp[j].charAt(0);
          if (!(temp[j].equals("S"))&&!(temp[j].equals("W")))
            gridWorldValue[i][j] = Integer.parseInt(temp[j]);
          else
            gridWorldValue[i][j] = 0;
        }
        st = br.readLine();
      }
      return new GridWorld(n,m,gridWorld,gridWorldValue);
    }
    catch(FileNotFoundException e){
      System.out.println("File does exist.");
      return null;
    }
  }
}

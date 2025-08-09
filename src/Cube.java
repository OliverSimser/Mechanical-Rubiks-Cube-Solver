import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class Cube {
  //Create an 3x3 array for each face of the cube
  public int[][] whiteFace = new int[3][3];
  public int[][] redFace = new int[3][3];
  public int[][] blueFace = new int[3][3];
  public int[][] orangeFace = new int[3][3];
  public int[][] greenFace = new int[3][3];
  public int[][] yellowFace = new int[3][3];

  public Cube(int [][] w, int [][] r, int [][] b, int [][] o, int [][] g, int [][] y) { //Construcotr for the cube class
    //Takes in the copies of the values for each face of the cube and assignes it to the arrays in the cube class
    copyFace(whiteFace, w);
    copyFace(redFace, r);
    copyFace(blueFace, b);
    copyFace(orangeFace, o);
    copyFace(greenFace, g);
    copyFace(yellowFace, y);
    System.out.println(ShowCube()); //Prints current state of the cube
    System.out.println(solve()); //Solves cube and prints instructions
    System.out.println(ShowCube()); //Prints the solved cube
  }

  //Method to copy values of an array to the cube array instance variables
  private void copyFace(int[][] face, int[][] source) {
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        face[i][j] = source[i][j];
      }
    }
  }

  // Method to show cubes state
  public String ShowCube() {
    String whiteFaceString = "      " + whiteFace[2][2] + " " + whiteFace[2][1] + " " + whiteFace[2][0] + " \n      "
        + whiteFace[1][2] + " " + whiteFace[1][1] + " " + whiteFace[1][0] + " \n      " + whiteFace[0][2] + " "
        + whiteFace[0][1] + " " + whiteFace[0][0] + "\n";
    StringBuilder GRBFaceString = new StringBuilder();
    for (int i = 0; i < greenFace.length; i++) {
      for (int j = 0; j < greenFace[i].length; j++) {
        GRBFaceString.append(greenFace[i][j]).append(" ");
      }
      for (int j = 0; j < redFace[i].length; j++) {
        GRBFaceString.append(redFace[i][j]).append(" ");
      }
      for (int j = 0; j < blueFace[i].length; j++) {
        GRBFaceString.append(blueFace[i][j]).append(" ");
      }
      GRBFaceString.append("\n");
    }
    StringBuilder yellowFaceString = new StringBuilder();
    for (int i = 0; i < yellowFace.length; i++) {
      yellowFaceString.append("      "); // Add six spaces at the start of each line
      for (int j = 0; j < yellowFace[i].length; j++) {
        yellowFaceString.append(yellowFace[i][j]).append(" ");
      }
      yellowFaceString.append("\n");
    }
    String orangeFaceString = "      " + orangeFace[2][2] + " " + orangeFace[2][1] + " " + orangeFace[2][0]
        + " \n      " + orangeFace[1][2] + " " + orangeFace[1][1] + " " + orangeFace[1][0] + " \n      "
        + orangeFace[0][2] + " " + orangeFace[0][1] + " " + orangeFace[0][0] + "\n";
    return "Cube:" + "\n" + "\n" + whiteFaceString + GRBFaceString.toString() + yellowFaceString.toString()
        + orangeFaceString + "\n" + "\n";
  }

  // Method to check if a face is solved
  public static boolean checkFace(int[][] face, int color) {
    for (int i = 0; i < face.length; i++) { //Goes through each element and checks if it is equal to the color passed in
      for (int j = 0; j < face[i].length; j++) {
        if (face[i][j] != color) {
          return false;
        }
      }
    }
    return true;
  }

  // Clockwise turns
  public void FaceTurnCW(int[][] a) { // method to change values of the array for any face of the cube being turned clockwise
    int duplicateCorner = a[0][0];
    a[0][0] = a[2][0]; 
    a[2][0] = a[2][2];
    a[2][2] = a[0][2];
    a[0][2] = duplicateCorner;

    int duplicateEdge = a[0][1];
    a[0][1] = a[1][0];
    a[1][0] = a[2][1];
    a[2][1] = a[1][2];
    a[1][2] = duplicateEdge;
  }

  public void FaceTurnCCW(int[][] a) { // method to change values of the array for any face of the cube being turned counter-clockwise
    int duplicateCorner = a[0][0];
    a[0][0] = a[0][2];
    a[0][2] = a[2][2];
    a[2][2] = a[2][0];
    a[2][0] = duplicateCorner;

    int duplicateEdge = a[0][1];
    a[0][1] = a[1][2];
    a[1][2] = a[2][1];
    a[2][1] = a[1][0];
    a[1][0] = duplicateEdge;
  }

  public void R() { // Method to change values of arrays to match a the right side of the cube being turned CW
    FaceTurnCW(blueFace);
    int duplicate, duplicate2, duplicate3;
    duplicate = redFace[0][2];
    duplicate2 = redFace[1][2];
    duplicate3 = redFace[2][2];
    redFace[0][2] = yellowFace[0][2];
    redFace[1][2] = yellowFace[1][2];
    redFace[2][2] = yellowFace[2][2];
    yellowFace[0][2] = orangeFace[2][0];
    yellowFace[1][2] = orangeFace[1][0];
    yellowFace[2][2] = orangeFace[0][0];
    orangeFace[2][0] = whiteFace[2][0];
    orangeFace[1][0] = whiteFace[1][0];
    orangeFace[0][0] = whiteFace[0][0];
    whiteFace[2][0] = duplicate;
    whiteFace[1][0] = duplicate2;
    whiteFace[0][0] = duplicate3;
  }

  public String R(String a) { //Method to add "R" to the string of instructions
    R();
    return a += "R, ";
  }

  public void L() { // Method to change values of arrays to match a the left side of the cube being turned CW
    FaceTurnCW(greenFace);
    int duplicate, duplicate2, duplicate3;
    duplicate = redFace[0][0];
    duplicate2 = redFace[1][0];
    duplicate3 = redFace[2][0];
    redFace[0][0] = whiteFace[2][2];
    redFace[1][0] = whiteFace[1][2];
    redFace[2][0] = whiteFace[0][2];
    whiteFace[2][2] = orangeFace[2][2];
    whiteFace[1][2] = orangeFace[1][2];
    whiteFace[0][2] = orangeFace[0][2];
    orangeFace[2][2] = yellowFace[0][0];
    orangeFace[1][2] = yellowFace[1][0];
    orangeFace[0][2] = yellowFace[2][0];
    yellowFace[0][0] = duplicate;
    yellowFace[1][0] = duplicate2;
    yellowFace[2][0] = duplicate3;
  }

  public String L(String a) { //Method to add "L" to the string of instructions
    L();
    return a += "L, ";
  }

  public void F() { // Method to change values of arrays to match a the front side of the cube being turned CW
    FaceTurnCW(redFace);
    int duplicate, duplicate2, duplicate3;
    duplicate = whiteFace[0][0];
    duplicate2 = whiteFace[0][1];
    duplicate3 = whiteFace[0][2];
    whiteFace[0][0] = greenFace[0][2];
    whiteFace[0][1] = greenFace[1][2];
    whiteFace[0][2] = greenFace[2][2];
    greenFace[0][2] = yellowFace[0][0];
    greenFace[1][2] = yellowFace[0][1];
    greenFace[2][2] = yellowFace[0][2];
    yellowFace[0][0] = blueFace[2][0];
    yellowFace[0][1] = blueFace[1][0];
    yellowFace[0][2] = blueFace[0][0];
    blueFace[2][0] = duplicate;
    blueFace[1][0] = duplicate2;
    blueFace[0][0] = duplicate3;
  }

  public String F(String a) {
    F();
    return a += "F, ";
  }

  public void B() { // Method to change values of arrays to match a back turn of the cube clockwise
    FaceTurnCW(orangeFace);
    int duplicate, duplicate2, duplicate3;
    duplicate = whiteFace[2][2];
    duplicate2 = whiteFace[2][1];
    duplicate3 = whiteFace[2][0];
    whiteFace[2][2] = blueFace[0][2];
    whiteFace[2][1] = blueFace[1][2];
    whiteFace[2][0] = blueFace[2][2];
    blueFace[0][2] = yellowFace[2][2];
    blueFace[1][2] = yellowFace[2][1];
    blueFace[2][2] = yellowFace[2][0];
    yellowFace[2][2] = greenFace[2][0];
    yellowFace[2][1] = greenFace[1][0];
    yellowFace[2][0] = greenFace[0][0];
    greenFace[2][0] = duplicate;
    greenFace[1][0] = duplicate2;
    greenFace[0][0] = duplicate3;
  }

  public String B(String a) {
    B();
    return a += "B, ";
  }

  public void U() { // Method to change values of arrays to match a up turn of the cube clockwise
    FaceTurnCW(whiteFace);
    int duplicate, duplicate2, duplicate3;
    duplicate = redFace[0][0];
    duplicate2 = redFace[0][1];
    duplicate3 = redFace[0][2];
    redFace[0][0] = blueFace[0][0];
    redFace[0][1] = blueFace[0][1];
    redFace[0][2] = blueFace[0][2];
    blueFace[0][0] = orangeFace[0][0];
    blueFace[0][1] = orangeFace[0][1];
    blueFace[0][2] = orangeFace[0][2];
    orangeFace[0][0] = greenFace[0][0];
    orangeFace[0][1] = greenFace[0][1];
    orangeFace[0][2] = greenFace[0][2];
    greenFace[0][0] = duplicate;
    greenFace[0][1] = duplicate2;
    greenFace[0][2] = duplicate3;
  }

  public String U(String a) {
    U();
    return a += "U, ";
  }

  public void D() { // Method to change values of arrays to match a down turn of the cube clockwise
    FaceTurnCW(yellowFace);
    int duplicate, duplicate2, duplicate3;
    duplicate = redFace[2][2];
    duplicate2 = redFace[2][1];
    duplicate3 = redFace[2][0];
    redFace[2][2] = greenFace[2][2];
    redFace[2][1] = greenFace[2][1];
    redFace[2][0] = greenFace[2][0];
    greenFace[2][2] = orangeFace[2][2];
    greenFace[2][1] = orangeFace[2][1];
    greenFace[2][0] = orangeFace[2][0];
    orangeFace[2][2] = blueFace[2][2];
    orangeFace[2][1] = blueFace[2][1];
    orangeFace[2][0] = blueFace[2][0];
    blueFace[2][2] = duplicate;
    blueFace[2][1] = duplicate2;
    blueFace[2][0] = duplicate3;
  }

  public String D(String a) {
    D();
    return a += "D, ";
  }

  // Counter-clockwise or prime turns
  public String Rp(String a) {
    R();
    R();
    R();
    return a += "R', ";
  }

  public String Lp(String a) {
    L();
    L();
    L();
    return a += "L', ";
  }

  public String Fp(String a) {
    F();
    F();
    F();
    return a += "F', ";
  }

  public String Bp(String a) {
    B();
    B();
    B();
    return a += "B', ";
  }

  public String Up(String a) {
    U();
    U();
    U();
    return a += "U', ";
  }

  public String Dp(String a) {
    D();
    D();
    D();
    return a += "D', ";
  }

  // Double turns
  public String R2(String a) {
    R();
    R();
    return a += "R2, ";
  }

  public String L2(String a) {
    L();
    L();
    return a += "L2, ";
  }

  public String F2(String a) {
    F();
    F();
    return a += "F2, ";
  }

  public String B2(String a) {
    B();
    B();
    return a += "B2, ";
  }

  public String U2(String a) {
    U();
    U();
    return a += "U2, ";
  }

  public String D2(String a) {
    D();
    D();
    return a += "D2, ";
  }

  public String solve() { //Method to return a string of instructions for user to solve the cube
    String InstructionsSolveString = "Have the Red side facing you and the White side facing upwards. Preform the following moves to solve the cube: ";
    String solveString = "";
    solveString += whiteCross(); //Adds white cross instructions
    solveString += whiteCorners(); //Adds white corners instructions
    solveString += F2L(); //Adds F2L instructions
    solveString += OLL(); //Adds OLL instructions
    solveString += PLL(); //Adds PLL instructions

    String optimizedSolveString = optimizedMoves(solveString); //Optimizes the moves
    
    String MRCSString = ""; //String of instructions for the MRCS
    MRCSString += optimizedSolveString;

    // Perform replaceAll operations sequentially on the original MRCSString
    MRCSString = MRCSString.replace("U'", "A");
    MRCSString = MRCSString.replace("U2", "0");
    MRCSString = MRCSString.replace("U", "a");
    MRCSString = MRCSString.replace("F'", "X");
    MRCSString = MRCSString.replace("F2", "1");
    MRCSString = MRCSString.replace("F", "b");
    MRCSString = MRCSString.replace("R'", "C");
    MRCSString = MRCSString.replace("R2", "2");
    MRCSString = MRCSString.replace("R", "c");
    MRCSString = MRCSString.replace("B'", "S");
    MRCSString = MRCSString.replace("B2", "3");
    MRCSString = MRCSString.replace("B", "d");
    MRCSString = MRCSString.replace("L'", "E");
    MRCSString = MRCSString.replace("L2", "4");
    MRCSString = MRCSString.replace("L", "e");
    MRCSString = MRCSString.replace("D'", "V");
    MRCSString = MRCSString.replace("D2", "5");
    MRCSString = MRCSString.replace("D", "f");
    MRCSString = MRCSString.replaceAll("[\\s,]", "");
    return InstructionsSolveString + optimizedSolveString + "\n" + "Arduino code to enter: " + MRCSString + "\n";
  }

  public String whiteCross() { //Method to solve the white cross of the cube and return the instructions
    String Add = "";
    while (whiteEdgeCheck(redFace)) { //Checks for white edges on each face and turns the cube to insert them on the yellow side safely
      if (redFace[1][2] == 0) { 
        Add = Rp(Add); //Inserts the white edge
        Add = Dp(Add);
      } else {
        Add = F(Add); //Moves white edge to correct spot before inserting it
      }
    }
    Add = D(Add);
    while (whiteEdgeCheck(blueFace)) {
      if (blueFace[1][2] == 0) {
        Add = Bp(Add);
        Add = Dp(Add);
      } else {
        if (yellowFace[1][2] == 0) { //Checks if yellow face is empty to be put in without removing previous white edges
          Add = Dp(Add);
          Add = R(Add);
          Add = D(Add);
        } else {
          Add = R(Add);
        }
      }
    }
    Add = D(Add);
    while (whiteEdgeCheck(orangeFace)) {
      if (orangeFace[1][2] == 0) {
        Add = Lp(Add);
        Add = Dp(Add);
      } else {
        if (yellowFace[2][1] == 0) {
          Add = Dp(Add);
          Add = B(Add);
          Add = D(Add);
        } else {
          Add = B(Add);
        }
      }
    }
    Add = D(Add);
    while (whiteEdgeCheck(greenFace)) {
      if (greenFace[1][2] == 0) {
        Add = Fp(Add);
        Add = Dp(Add);
      } else {
        if (yellowFace[0][1] == 0) {
          Add = Dp(Add);
          Add = L(Add);
          Add = D(Add);
        } else {
          Add = L(Add);
        }
      }
    }
    while (whiteEdgeCheck(whiteFace)) { //Moves any white edges from the top side down
      if (whiteFace[0][1] != 0 && whiteFace[2][1] != 0 && whiteFace[1][0] != 0 && whiteFace[1][2] != 0)
        break;
      if (whiteFace[0][1] == 0) {
        Add = F2(Add);
        Add = Dp(Add);
      } else {
        Add = U(Add);
      }
    }
    while (whiteEdgeCheck(redFace)) { //Re-checks all the side faces for white edges
      while (yellowFace[1][2] == 0) {
        Add = D(Add);
      }
      if (redFace[1][2] == 0) {
        Add = Rp(Add);
        Add = Dp(Add);
      } else if (redFace[2][1] == 0) {
        Add = F(Add);
      } else {
        Add = Dp(Add);
        Add = F(Add);
        Add = D(Add);
      }
    }
    while (whiteEdgeCheck(orangeFace)) {
      while (yellowFace[1][0] == 0) {
        Add = D(Add);
      }
      if (orangeFace[1][2] == 0) {
        Add = Lp(Add);
        Add = Dp(Add);
      } else if (orangeFace[2][1] == 0) {
        Add = B(Add);
      } else {
        Add = Dp(Add);
        Add = B(Add);
        Add = D(Add);
      }
    }
    while (whiteEdgeCheck(blueFace)) {
      while (yellowFace[2][1] == 0) {
        Add = D(Add);
      }
      if (blueFace[1][2] == 0) {
        Add = Bp(Add);
        Add = Dp(Add);
      } else if (blueFace[2][1] == 0) {
        Add = R(Add);
      } else {
        Add = Dp(Add);
        Add = R(Add);
        Add = D(Add);
      }
    }
    while (whiteEdgeCheck(greenFace)) {
      while (yellowFace[0][1] == 0) {
        Add = D(Add);
      }
      if (greenFace[1][2] == 0) {
        Add = Fp(Add);
      } else if (greenFace[2][1] == 0) {
        Add = L(Add);
      } else {
        Add = Dp(Add);
        Add = L(Add);
        Add = D(Add);
      }
    }
    while (whiteEdgeCheck(whiteFace)) { //Inserts white edges from top side down
      while (yellowFace[0][1] == 0)
        Add = D(Add);
      if (whiteFace[0][1] != 0 && whiteFace[2][1] != 0 && whiteFace[1][0] != 0 && whiteFace[1][2] != 0)
        break;
      if (whiteFace[0][1] == 0) {
        Add = F2(Add);
      } else {
        Add = U(Add);
      }
    } // Yellow Daisy is done
    while (redFace[2][1] != 1) { //Inserts white edges from yellow daisy to the white side correctly
      Add = D(Add);
    }
    Add = F2(Add);
    while (true) {
      if (blueFace[2][1] == 2 && yellowFace[1][2] == 0)
        break;
      Add = D(Add);
    }
    Add = R2(Add);
    while (true) {
      if (orangeFace[2][1] == 3 && yellowFace[2][1] == 0)
        break;
      Add = D(Add);
    }
    Add = B2(Add);
    while (true) {
      if (greenFace[2][1] == 4 && yellowFace[1][0] == 0)
        break;
      Add = D(Add);
    }
    Add = L2(Add);
    return Add;
  }

  public String whiteCorners() { //Method to solve the white corners of the cube and return the instructions
    String Add2 = "";
    if (blueFace[0][0] == 0) { //Insterts any white corners from the bottom layer into their coresponding spot
      Add2 = F(Add2);
      Add2 = D(Add2);
      Add2 = Fp(Add2);
      Add2 = Dp(Add2);
      Add2 = F(Add2);
      Add2 = D2(Add2);
      Add2 = Fp(Add2);
      Add2 = Dp(Add2);
      Add2 = F(Add2);
      Add2 = D(Add2);
      Add2 = Fp(Add2);
    }
    if (redFace[0][0] == 0) {
      Add2 = L(Add2);
      Add2 = D(Add2);
      Add2 = Lp(Add2);
      Add2 = Dp(Add2);
      Add2 = L(Add2);
      Add2 = D2(Add2);
      Add2 = Lp(Add2);
      Add2 = Dp(Add2);
      Add2 = L(Add2);
      Add2 = D(Add2);
      Add2 = Lp(Add2);
    }
    if (greenFace[0][0] == 0) {
      Add2 = B(Add2);
      Add2 = D(Add2);
      Add2 = Bp(Add2);
      Add2 = Dp(Add2);
      Add2 = B(Add2);
      Add2 = D2(Add2);
      Add2 = Bp(Add2);
      Add2 = Dp(Add2);
      Add2 = B(Add2);
      Add2 = D(Add2);
      Add2 = Bp(Add2);
    }
    if (orangeFace[0][0] == 0) {
      Add2 = R(Add2);
      Add2 = D(Add2);
      Add2 = Rp(Add2);
      Add2 = Dp(Add2);
      Add2 = R(Add2);
      Add2 = D2(Add2);
      Add2 = Rp(Add2);
      Add2 = Dp(Add2);
      Add2 = R(Add2);
      Add2 = D(Add2);
      Add2 = Rp(Add2);
    }
    if (blueFace[0][2] == 0) {
      Add2 = Bp(Add2);
      Add2 = Dp(Add2);
      Add2 = B(Add2);
      Add2 = D(Add2);
      Add2 = R(Add2);
      Add2 = D2(Add2);
      Add2 = Rp(Add2);
      Add2 = Dp(Add2);
      Add2 = R(Add2);
      Add2 = D(Add2);
      Add2 = Rp(Add2);
    }
    if (redFace[0][2] == 0) {
      Add2 = Rp(Add2);
      Add2 = Dp(Add2);
      Add2 = R(Add2);
      Add2 = D(Add2);
      Add2 = F(Add2);
      Add2 = D2(Add2);
      Add2 = Fp(Add2);
      Add2 = Dp(Add2);
      Add2 = F(Add2);
      Add2 = D(Add2);
      Add2 = Fp(Add2);
    }
    if (greenFace[0][2] == 0) {
      Add2 = Fp(Add2);
      Add2 = Dp(Add2);
      Add2 = F(Add2);
      Add2 = D(Add2);
      Add2 = L(Add2);
      Add2 = D2(Add2);
      Add2 = Lp(Add2);
      Add2 = Dp(Add2);
      Add2 = L(Add2);
      Add2 = D(Add2);
      Add2 = Lp(Add2);
    }
    if (orangeFace[0][2] == 0) {
      Add2 = Lp(Add2);
      Add2 = Dp(Add2);
      Add2 = L(Add2);
      Add2 = D(Add2);
      Add2 = B(Add2);
      Add2 = D2(Add2);
      Add2 = Bp(Add2);
      Add2 = Dp(Add2);
      Add2 = B(Add2);
      Add2 = D(Add2);
      Add2 = Bp(Add2);
    }
    while (whiteCornerCheck(yellowFace)) { //Inserts corners from yellow layer down to the white layer
      if (whiteFace[2][2] != 0) { //Makes sure it does not remove any white edges in the correct spot already
        while (yellowFace[2][0] != 0) {
          Add2 = D(Add2);
        }
        Add2 = B(Add2);
        Add2 = D2(Add2);
        Add2 = Bp(Add2);
        Add2 = Dp(Add2);
        Add2 = B(Add2);
        Add2 = D(Add2);
        Add2 = Bp(Add2);
        if (!whiteCornerCheck(yellowFace))
          break;
      }
      if (whiteFace[2][0] != 0) {
        while (yellowFace[2][2] != 0) {
          Add2 = D(Add2);
        }
        Add2 = R(Add2);
        Add2 = D2(Add2);
        Add2 = Rp(Add2);
        Add2 = Dp(Add2);
        Add2 = R(Add2);
        Add2 = D(Add2);
        Add2 = Rp(Add2);
        if (!whiteCornerCheck(yellowFace))
          break;
      }
      if (whiteFace[0][0] != 0) {
        while (yellowFace[0][2] != 0) {
          Add2 = D(Add2);
        }
        Add2 = F(Add2);
        Add2 = D2(Add2);
        Add2 = Fp(Add2);
        Add2 = Dp(Add2);
        Add2 = F(Add2);
        Add2 = D(Add2);
        Add2 = Fp(Add2);
        if (!whiteCornerCheck(yellowFace))
          break;
      }
      if (whiteFace[0][2] != 0) {
        while (yellowFace[0][0] != 0) {
          Add2 = D(Add2);
        }
        Add2 = L(Add2);
        Add2 = D2(Add2);
        Add2 = Lp(Add2);
        Add2 = Dp(Add2);
        Add2 = L(Add2);
        Add2 = D(Add2);
        Add2 = Lp(Add2);
        if (!whiteCornerCheck(yellowFace))
          break;
      }
    }
    while ((redFace[2][2] == 0 || blueFace[2][2] == 0 || orangeFace[2][2] == 0 || greenFace[2][2] == 0) == true
        || (redFace[2][0] == 0 || blueFace[2][0] == 0 || orangeFace[2][0] == 0 || greenFace[2][0] == 0) == true) {
      if (whiteFace[0][2] != 0
          && (redFace[2][2] == 0 || blueFace[2][2] == 0 || orangeFace[2][2] == 0 || greenFace[2][2] == 0) == true) { //Moves all white corners from the top layer of the sides to the bottom layer
        while (greenFace[2][2] != 0) {
          Add2 = D(Add2);
        }
        if (blueFace[2][2] == 0) { //Removes any possibility of having to check the yellow face again for white corners
          Add2 = Rp(Add2);
          Add2 = L(Add2);
          Add2 = D(Add2);
          Add2 = Lp(Add2);
          Add2 = R(Add2);
        } else {
          Add2 = L(Add2);
          Add2 = D(Add2);
          Add2 = Lp(Add2);
        }
      }

      if (whiteFace[0][0] != 0
          && (redFace[2][2] == 0 || blueFace[2][2] == 0 || orangeFace[2][2] == 0 || greenFace[2][2] == 0) == true) {
        while (redFace[2][2] != 0) {
          Add2 = D(Add2);
        }
        if (orangeFace[2][2] == 0) {
          Add2 = Bp(Add2);
          Add2 = F(Add2);
          Add2 = D(Add2);
          Add2 = Fp(Add2);
          Add2 = B(Add2);
        } else {
          Add2 = F(Add2);
          Add2 = D(Add2);
          Add2 = Fp(Add2);
        }
      }
      if (whiteFace[2][0] != 0
          && (redFace[2][2] == 0 || blueFace[2][2] == 0 || orangeFace[2][2] == 0 || greenFace[2][2] == 0) == true) {
        while (blueFace[2][2] != 0) {
          Add2 = D(Add2);
        }
        if (greenFace[2][2] == 0) {
          Add2 = Lp(Add2);
          Add2 = R(Add2);
          Add2 = D(Add2);
          Add2 = Rp(Add2);
          Add2 = L(Add2);
        } else {
          Add2 = R(Add2);
          Add2 = D(Add2);
          Add2 = Rp(Add2);
        }
      }
      if (whiteFace[2][2] != 0
          && (redFace[2][2] == 0 || blueFace[2][2] == 0 || orangeFace[2][2] == 0 || greenFace[2][2] == 0) == true) {
        while (orangeFace[2][2] != 0) {
          Add2 = D(Add2);
        }
        if (redFace[2][2] == 0) {
          Add2 = Fp(Add2);
          Add2 = B(Add2);
          Add2 = D(Add2);
          Add2 = Bp(Add2);
          Add2 = F(Add2);
        } else {
          Add2 = B(Add2);
          Add2 = D(Add2);
          Add2 = Bp(Add2);
        }
      }
      if (whiteFace[0][2] != 0
          && (redFace[2][0] == 0 || blueFace[2][0] == 0 || orangeFace[2][0] == 0 || greenFace[2][0] == 0) == true) {
        while (redFace[2][0] != 0) {
          Add2 = D(Add2);
        }
        if (orangeFace[2][2] == 0 || redFace[2][2] == 0) {
          Add2 = Fp(Add2);
          Add2 = Dp(Add2);
          Add2 = F(Add2);
        } else {
          Add2 = Lp(Add2);
          Add2 = F(Add2);
          Add2 = L(Add2);
          Add2 = Fp(Add2);
        }
      }
      if (whiteFace[0][0] != 0
          && (redFace[2][0] == 0 || blueFace[2][0] == 0 || orangeFace[2][0] == 0 || greenFace[2][0] == 0) == true) {
        while (blueFace[2][0] != 0) {
          Add2 = D(Add2);
        }
        if (greenFace[2][2] == 0 || blueFace[2][2] == 0) {
          Add2 = Rp(Add2);
          Add2 = Dp(Add2);
          Add2 = R(Add2);
        } else {
          Add2 = Fp(Add2);
          Add2 = R(Add2);
          Add2 = F(Add2);
          Add2 = Rp(Add2);
        }
      }
      if (whiteFace[2][0] != 0
          && (redFace[2][0] == 0 || blueFace[2][0] == 0 || orangeFace[2][0] == 0 || greenFace[2][0] == 0) == true) {
        while (orangeFace[2][0] != 0) {
          Add2 = D(Add2);
        }
        if (redFace[2][2] == 0 || orangeFace[2][2] == 0) {
          Add2 = Bp(Add2);
          Add2 = Dp(Add2);
          Add2 = B(Add2);
        } else {
          Add2 = Rp(Add2);
          Add2 = B(Add2);
          Add2 = R(Add2);
          Add2 = Bp(Add2);
        }
      }
      if (whiteFace[2][2] != 0
          && (redFace[2][0] == 0 || blueFace[2][0] == 0 || orangeFace[2][0] == 0 || greenFace[2][0] == 0) == true) {
        while (greenFace[2][0] != 0) {
          Add2 = D(Add2);
        }
        if (blueFace[2][2] == 0 || greenFace[2][2] == 0) {
          Add2 = Lp(Add2);
          Add2 = Dp(Add2);
          Add2 = L(Add2);
        } else {
          Add2 = Bp(Add2);
          Add2 = L(Add2);
          Add2 = B(Add2);
          Add2 = Lp(Add2);
        }
      }
    } // White corners in are, but possibly not in right order, next code changes the order to the correct spot
    if (redFace[0][0] != 1 && greenFace[0][2] != 4) {
      if (greenFace[0][0] == 1 && orangeFace[0][2] == 4) {
        Add2 = B(Add2);
        Add2 = D(Add2);
        Add2 = Bp(Add2);
        Add2 = Lp(Add2);
        Add2 = F(Add2);
        Add2 = L(Add2);
        Add2 = Fp(Add2);
        Add2 = Dp(Add2);
        Add2 = B(Add2);
        Add2 = D(Add2);
        Add2 = Bp(Add2);
      } else if (orangeFace[0][0] == 1 && blueFace[0][2] == 4) {
        Add2 = R(Add2);
        Add2 = D(Add2);
        Add2 = D(Add2);
        Add2 = Rp(Add2);
        Add2 = Lp(Add2);
        Add2 = F(Add2);
        Add2 = L(Add2);
        Add2 = Fp(Add2);
        Add2 = Dp(Add2);
        Add2 = Dp(Add2);
        Add2 = R(Add2);
        Add2 = D(Add2);
        Add2 = Rp(Add2);
      } else {
        Add2 = Rp(Add2);
        Add2 = Dp(Add2);
        Add2 = R(Add2);
        Add2 = L(Add2);
        Add2 = D(Add2);
        Add2 = Lp(Add2);
        Add2 = Rp(Add2);
        Add2 = Dp(Add2);
        Add2 = R(Add2);
      }
    } // red and green in correct spot
    if (greenFace[0][0] != 4 && orangeFace[0][2] != 3) {
      if (orangeFace[0][0] == 4 && blueFace[0][2] == 3) {
        Add2 = R(Add2);
        Add2 = D(Add2);
        Add2 = Rp(Add2);
        Add2 = Lp(Add2);
        Add2 = Dp(Add2);
        Add2 = L(Add2);
        Add2 = R(Add2);
        Add2 = D(Add2);
        Add2 = Rp(Add2);
      } else {
        Add2 = Rp(Add2);
        Add2 = D(Add2);
        Add2 = D(Add2);
        Add2 = R(Add2);
        Add2 = B(Add2);
        Add2 = D(Add2);
        Add2 = Bp(Add2);
        Add2 = D(Add2);
        Add2 = Rp(Add2);
        Add2 = Dp(Add2);
        Add2 = R(Add2);
      }
    } // orange and green in correct spot
    if (orangeFace[0][0] != 3 && blueFace[0][2] != 2) {
      Add2 = F(Add2);
      Add2 = D(Add2);
      Add2 = Fp(Add2);
      Add2 = Bp(Add2);
      Add2 = Dp(Add2);
      Add2 = B(Add2);
      Add2 = F(Add2);
      Add2 = D(Add2);
      Add2 = Fp(Add2);
    }
    return Add2;
  }

  public String F2L() { //Method to solve the first two layers of the cube and return the instructions
    String Add3 = "";
    int numCycle = 0;

    while (redFace[1][0] != 1 || redFace[1][2] != 1 || blueFace[1][0] != 2 || blueFace[1][2] != 2 ||
        orangeFace[1][0] != 3 || orangeFace[1][2] != 3 || greenFace[1][0] != 4 || greenFace[1][2] != 4) { //While F2L is not solved

      boolean check = false;
      if (numCycle > 2 && numCycle % 3 == 0) { //Only check for f2L edges on middle layer of side every three turns
        check = true;
      }

      String yellowEdge = yellowF2LEdge();

      if ((yellowEdge.contains("redtor")) || (check && (hasF2LEdge(redFace) == 1))
          || (check && (hasF2LEdge(redFace) == 3))) { //Moves edge down to the correct spot if condition is true and it needs to be moved
        Add3 = D(Add3);
        Add3 = L(Add3);
        Add3 = Dp(Add3);
        Add3 = Lp(Add3);
        Add3 = Dp(Add3);
        Add3 = Fp(Add3);
        Add3 = D(Add3);
        Add3 = F(Add3);
      }
      if ((yellowEdge.contains("redtol")) || (check && (hasF2LEdge(redFace) == 2))
          || (check && (hasF2LEdge(redFace) == 3))) {
        Add3 = Dp(Add3);
        Add3 = Rp(Add3);
        Add3 = D(Add3);
        Add3 = R(Add3);
        Add3 = D(Add3);
        Add3 = F(Add3);
        Add3 = Dp(Add3);
        Add3 = Fp(Add3);
      }
      if ((yellowEdge.contains("bluetor")) || (check && (hasF2LEdge(blueFace) == 1))
          || (check && (hasF2LEdge(blueFace) == 3))) {
        Add3 = D(Add3);
        Add3 = F(Add3);
        Add3 = Dp(Add3);
        Add3 = Fp(Add3);
        Add3 = Dp(Add3);
        Add3 = Rp(Add3);
        Add3 = D(Add3);
        Add3 = R(Add3);
      }
      if ((yellowEdge.contains("bluetol")) || (check && (hasF2LEdge(blueFace) == 2))
          || (check && (hasF2LEdge(blueFace) == 3))) {
        Add3 = Dp(Add3);
        Add3 = Bp(Add3);
        Add3 = D(Add3);
        Add3 = B(Add3);
        Add3 = D(Add3);
        Add3 = R(Add3);
        Add3 = Dp(Add3);
        Add3 = Rp(Add3);
      }
      if ((yellowEdge.contains("orangetor")) || (check && (hasF2LEdge(orangeFace) == 1))
          || (check && (hasF2LEdge(orangeFace) == 3))) {
        Add3 = D(Add3);
        Add3 = R(Add3);
        Add3 = Dp(Add3);
        Add3 = Rp(Add3);
        Add3 = Dp(Add3);
        Add3 = Bp(Add3);
        Add3 = D(Add3);
        Add3 = B(Add3);
      }
      if ((yellowEdge.contains("orangetol")) || (check && (hasF2LEdge(orangeFace) == 2))
          || (check && (hasF2LEdge(orangeFace) == 3))) {
        Add3 = Dp(Add3);
        Add3 = Lp(Add3);
        Add3 = D(Add3);
        Add3 = L(Add3);
        Add3 = D(Add3);
        Add3 = B(Add3);
        Add3 = Dp(Add3);
        Add3 = Bp(Add3);
      }
      if ((yellowEdge.contains("greentor")) || (check && (hasF2LEdge(greenFace) == 1))
          || (check && (hasF2LEdge(greenFace) == 3))) {
        Add3 = D(Add3);
        Add3 = B(Add3);
        Add3 = Dp(Add3);
        Add3 = Bp(Add3);
        Add3 = Dp(Add3);
        Add3 = Lp(Add3);
        Add3 = D(Add3);
        Add3 = L(Add3);
      }
      if ((yellowEdge.contains("greentol")) || (check && (hasF2LEdge(greenFace) == 2))
          || (check && (hasF2LEdge(greenFace) == 3))) {
        Add3 = Dp(Add3);
        Add3 = Fp(Add3);
        Add3 = D(Add3);
        Add3 = F(Add3);
        Add3 = D(Add3);
        Add3 = L(Add3);
        Add3 = Dp(Add3);
        Add3 = Lp(Add3);
      }

      Add3 = D(Add3);
      numCycle++;
    }

    return Add3;
  }

  public String OLL() { //Method to oreintation of the last layer of the cube and return the instructions
    String Add4 = "";

    while ((noYE() || adjacentYE() || oppositeYE())) { //While the yellow cross is not solved

      if (adjacentYE()) { //Orient yellow edges so algorithm works
        while (true) {
          if (yellowFace[0][1] != 5 && yellowFace[1][2] == 5 && yellowFace[2][1] == 5 && yellowFace[1][0] != 5)
            break;
          else
            Add4 = D(Add4);
        }
      } else if (oppositeYE()) { //Orient yellow edges so algorithm works
        while (yellowFace[1][2] != 5 && yellowFace[1][0] != 5) {
          Add4 = D(Add4);
        }
      }

      // Perform OLL algorithm
      Add4 = F(Add4);
      Add4 = L(Add4);
      Add4 = D(Add4);
      Add4 = Lp(Add4);
      Add4 = Dp(Add4);
      Add4 = Fp(Add4);
    } // Yellow cross is done

    if (yellowFace[0][0] != 5 && yellowFace[0][2] != 5 && yellowFace[2][2] != 5 && yellowFace[2][0] != 5){ //If case is just yellow cross
      while (true){
        if (redFace[2][0] != 5 && redFace[2][2] != 5) break;
        else Add4 = D(Add4); //Orient for algorithm
      }
      Add4 = L(Add4); Add4 = D(Add4); Add4 = Lp(Add4); Add4 = D(Add4); Add4 = L(Add4); Add4 = D2(Add4); Add4 = Lp(Add4);
    }
    if (houseCase()){ //If house case
      while (true){
        if (yellowFace[0][0] == 5 && yellowFace[2][0] == 5) break; //orient
        else Add4 = D(Add4); 
      }
       Add4 = R(Add4); Add4 = F(Add4); Add4 = Lp(Add4); Add4 = Fp(Add4); Add4 = Rp(Add4); Add4 = F(Add4); Add4 = L(Add4); Add4 = Fp(Add4);
    }
    while (crossCase()){ //If cross case
      while (true){
        if (redFace[2][0] == 5) break; //orient
        else Add4 = D(Add4); 
      }
      Add4 = Fp(Add4); Add4 = R(Add4); Add4 = F(Add4); Add4 = Lp(Add4); Add4 = Fp(Add4); Add4 = Rp(Add4); Add4 = F(Add4); Add4 = L(Add4);
    }
    if (fishCase()){ //If fish case
      while (true){
        if (yellowFace[0][2] == 5) break; //orient
        else Add4 = D(Add4);
      }
      if (redFace[2][0] == 5) {
        Add4 = L(Add4); Add4 = D(Add4); Add4 = Lp(Add4); Add4 = D(Add4); Add4 = L(Add4); Add4 = D2(Add4); Add4 = Lp(Add4);
      }
      else{
        Add4 = R(Add4); Add4 = D2(Add4); Add4 = Rp(Add4); Add4 = Dp(Add4); Add4 = R(Add4); Add4 = Dp(Add4); Add4 = Rp(Add4);
      }
    }
      return Add4;
  }

  public String PLL () { //Method to finish last layer of the cube and return the instructions
    String Add5 = "";
    for (int i = 0; i < 2; i++){ //If worst case needs to be done twice
    if ((redFace[2][0] != redFace[2][2] && blueFace[2][0] != blueFace[2][2] && orangeFace[2][0] != orangeFace[2][2] && greenFace[2][0] != greenFace[2][2]) || twoCorner()){
      if (twoCorner()){
        i++;
        while (true){
          if (orangeFace[2][2] == orangeFace[2][0]) break;
          else Add5 = D(Add5);
        }
      }
      Add5 = Lp(Add5); Add5 = F(Add5); Add5 = Lp(Add5); Add5 = B2(Add5); Add5 = L(Add5); Add5 = Fp(Add5); Add5 = Lp(Add5); Add5 = B2(Add5); Add5 = L2(Add5);
    }
    }
    if(ETL() || ETR()){ //If ETL or ETR cases are true
      while (true){
      if (orangeFace[2][2] == orangeFace[2][1] && orangeFace[2][1] == orangeFace[2][0]){
        break; //orient
      }
      else Add5 = D(Add5);
      }
      if (ETL()){ //solve for etl
        Add5 = R2(Add5); Add5 = L2(Add5); Add5 = Up(Add5); Add5 = L(Add5); Add5 = Rp(Add5); Add5 = F2(Add5); Add5 = R(Add5); Add5 = Lp(Add5); Add5 = Up(Add5); Add5 = R2(Add5); Add5 = L2(Add5);
      }
      if (ETR()){ //solve for etr
        Add5 = R2(Add5); Add5 = L2(Add5); Add5 = U(Add5); Add5 = L(Add5); Add5 = Rp(Add5); Add5 = F2(Add5); Add5 = R(Add5); Add5 = Lp(Add5); Add5 = U(Add5); Add5 = R2(Add5); Add5 = L2(Add5);
      }
      }
    if (adjacentES() != 0){ //If this pll case is true
      if (adjacentES() == 2){
        Add5 = D(Add5); //orient
      }
      Add5 = R(Add5); Add5 = Lp(Add5); Add5 = F(Add5); Add5 = R2(Add5); Add5 = L2(Add5); Add5 = B(Add5); Add5 = R2(Add5); Add5 = L2(Add5); Add5 = F(Add5); Add5 = R(Add5); Add5 = Lp(Add5); Add5 = U2(Add5); Add5 = R2(Add5); Add5 = L2(Add5); Add5 = Dp(Add5); //solve
    }
    if (middleES()){ //If this pll case is true
      Add5 = R2(Add5); Add5 = L2(Add5); Add5 = U(Add5); Add5 = R2(Add5); Add5 = L2(Add5); Add5 = D2(Add5); Add5 = R2(Add5); Add5 = L2(Add5); Add5 = U(Add5); Add5 = R2(Add5); Add5 = L2(Add5); //solve
    }
    while (!cubeSolved()) Add5 = D(Add5); //If cube is one move away from being solved, solve it
    return Add5;
  }

  // Method to check if cube is solved
  public boolean cubeSolved() { //Checks each face
    if (checkFace(whiteFace, 0) && checkFace(redFace, 1) && checkFace(blueFace, 2)
        && checkFace(orangeFace, 3) && checkFace(greenFace, 4) && checkFace(yellowFace, 5)) {
      return true;
    } else {
      return false;
    }
  }

  public boolean twoCorner() { //Checks if pll case is true
    if ((greenFace[2][0] == greenFace[2][2] && redFace[2][0] != redFace[2][2] && blueFace[2][0] != blueFace[2][2] && orangeFace[2][0] != orangeFace[2][2]) || (redFace[2][0] == redFace[2][2] && greenFace[2][0] != greenFace[2][2] && blueFace[2][0] != blueFace[2][2] && orangeFace[2][0] != orangeFace[2][2]) || (blueFace[2][0] == blueFace[2][2] && greenFace[2][0] != greenFace[2][2] && redFace[2][0] != redFace[2][2] && orangeFace[2][0] != orangeFace[2][2]) || (orangeFace[2][0] == orangeFace[2][2] && greenFace[2][0] != greenFace[2][2] && redFace[2][0] != redFace[2][2] && blueFace[2][0] != blueFace[2][2])){
      return true;
    }
    else{
      return false;
    }
  }

  public boolean ETL() { //Checks if pll case is true
    if ((greenFace[2][0] == greenFace[2][1] && greenFace[2][1] == greenFace[2][2] && redFace[2][1] == blueFace[2][2] && blueFace[2][1] == orangeFace[2][2]) || (orangeFace[2][0] == orangeFace[2][1] && orangeFace[2][1] == orangeFace[2][2] && greenFace[2][1] == redFace[2][2] && redFace[2][1] == blueFace[2][2]) || (blueFace[2][0] == blueFace[2][1] && blueFace[2][1] == blueFace[2][2] && orangeFace[2][1] == greenFace[2][2] && greenFace[2][1] == redFace[2][2]) || (redFace[2][0] == redFace[2][1] && redFace[2][1] == redFace[2][2] && blueFace[2][1] == orangeFace[2][2] && orangeFace[2][1] == greenFace[2][2])){
      return true;
    }
    else{
      return false;
    }
  }

  public boolean ETR() { //Checks if pll case is true
    if ((greenFace[2][0] == greenFace[2][1] && greenFace[2][1] == greenFace[2][2] && orangeFace[2][1] == blueFace[2][2] && blueFace[2][1] == redFace[2][2]) || (orangeFace[2][0] == orangeFace[2][1] && orangeFace[2][1] == orangeFace[2][2] && blueFace[2][1] == redFace[2][2] && redFace[2][1] == greenFace[2][2]) || (blueFace[2][0] == blueFace[2][1] && blueFace[2][1] == blueFace[2][2] && redFace[2][1] == greenFace[2][2] && greenFace[2][1] == orangeFace[2][2]) || (redFace[2][0] == redFace[2][1] && redFace[2][1] == redFace[2][2] && greenFace[2][1] == orangeFace[2][2] && orangeFace[2][1] == blueFace[2][2])){
      return true;
    }
    else{
      return false;
    }
  }

  public int adjacentES() { //Checks if pll case is true
    if ((orangeFace[2][0] == orangeFace[2][2] && orangeFace[2][2] == greenFace[2][1]) && (greenFace[2][0] == greenFace[2][2] && greenFace[2][2] == orangeFace[2][1])){
      return 1; //Return one if orientation is good
    }
      else if ((orangeFace[2][0] == orangeFace[2][2] && orangeFace[2][2] == blueFace[2][1]) && (blueFace[2][0] == blueFace[2][2] && blueFace[2][2] == orangeFace[2][1])){
      return 2; //Return two if it needs to be oriented correctly
    }
    else{
      return 0;
    }
  }

  public boolean middleES() { //Checks if pll case is true
    if (redFace[2][0] == redFace[2][2] && redFace[2][1] == orangeFace[2][2] && orangeFace[2][2] == orangeFace[2][0] && orangeFace[2][1] == redFace[2][2]){
      return true;
    }
    else{
      return false;
    }
  }

  public boolean noYE() { //Checks if oll case is true
    if (yellowFace[0][1] != 5 && yellowFace[1][2] != 5 && yellowFace[2][1] != 5 && yellowFace[1][0] != 5)
      return true;
    else
      return false;
  }

  public boolean adjacentYE() { //Checks if oll case is true
    if ((yellowFace[0][1] != 5 && yellowFace[1][2] == 5 && yellowFace[2][1] == 5 && yellowFace[1][0] != 5)
        || (yellowFace[0][1] != 5 && yellowFace[1][2] != 5 && yellowFace[2][1] == 5 && yellowFace[1][0] == 5)
        || (yellowFace[0][1] == 5 && yellowFace[1][2] != 5 && yellowFace[2][1] != 5 && yellowFace[1][0] == 5)
        || (yellowFace[0][1] == 5 && yellowFace[1][2] == 5 && yellowFace[2][1] != 5 && yellowFace[1][0] != 5))
      return true;
    else
      return false;
  }

  public boolean oppositeYE() { //Checks if oll case is true
    if ((yellowFace[0][1] == 5 && yellowFace[1][2] != 5 && yellowFace[2][1] == 5 && yellowFace[1][0] != 5)
        || (yellowFace[0][1] != 5 && yellowFace[1][2] == 5 && yellowFace[2][1] != 5 && yellowFace[1][0] == 5))
      return true;
    else
      return false;
  }

  public boolean fishCase() { //Checks if oll case is true
    if ((yellowFace[0][0] == 5 && yellowFace[0][2] != 5 && yellowFace[2][2] != 5 && yellowFace[2][0] != 5) || (yellowFace[0][0] != 5 && yellowFace[0][2] == 5 && yellowFace[2][2] != 5 && yellowFace[2][0] != 5) || (yellowFace[0][0] != 5 && yellowFace[0][2] != 5 && yellowFace[2][2] == 5 && yellowFace[2][0] != 5) || (yellowFace[0][0] != 5 && yellowFace[0][2] != 5 && yellowFace[2][2] != 5 && yellowFace[2][0] == 5))
      return true;
    else
      return false;
  }
  
  public boolean houseCase() { //Checks if oll case is true
    if ((yellowFace[0][0] == 5 && yellowFace[0][2] == 5 && yellowFace[2][2] != 5 && yellowFace[2][0] != 5) || (yellowFace[0][0] != 5 && yellowFace[0][2] == 5 && yellowFace[2][2] == 5 && yellowFace[2][0] != 5) || (yellowFace[0][0] != 5 && yellowFace[0][2] != 5 && yellowFace[2][2] == 5 && yellowFace[2][0] == 5) || (yellowFace[0][0] == 5 && yellowFace[0][2] != 5 && yellowFace[2][2] != 5 && yellowFace[2][0] == 5))
      return true;
    else
      return false;
  }

  public boolean crossCase() { //Checks if oll case is true
    if ((yellowFace[0][0] == 5 && yellowFace[0][2] != 5 && yellowFace[2][2] == 5 && yellowFace[2][0] != 5) || (yellowFace[0][0] != 5 && yellowFace[0][2] == 5 && yellowFace[2][2] != 5 && yellowFace[2][0] == 5))
      return true;
    else
      return false;
  }

  public String yellowF2LEdge() { //Method to determine what case the F2L method is in, and what needs to be done
    String returnString = "";
    if ((yellowFace[0][1] == 4 && redFace[2][1] == 1)) {
      returnString += "redtor";
    }
    if ((yellowFace[0][1] == 2 && redFace[2][1] == 1)) {
      returnString += "redtol";
    }
    if ((yellowFace[1][2] == 1 && blueFace[2][1] == 2)) {
      returnString += "bluetor";
    }
    if ((yellowFace[1][2] == 3 && blueFace[2][1] == 2)) {
      returnString += "bluetol";
    }
    if ((yellowFace[2][1] == 2 && orangeFace[2][1] == 3)) {
      returnString += "orangetor";
    }
    if ((yellowFace[2][1] == 4 && orangeFace[2][1] == 3)) {
      returnString += "orangetol";
    }
    if ((yellowFace[1][0] == 3 && greenFace[2][1] == 4)) {
      returnString += "greentor";
    }
    if ((yellowFace[1][0] == 1 && greenFace[2][1] == 4)) {
      returnString += "greentol";
    }
    return returnString;
  }

  public int hasF2LEdge(int[][] array) { //Checks if side faces contain a F2L edge incorrectly palced that needs to be moved back to the top layor
    int intReturn = 0;
    if (array == redFace) {
      if ((redFace[1][0] != 1 || greenFace[1][2] != 4) && (greenFace[1][2] != 5 && redFace[1][0] != 5))
        intReturn++; //Returns 1 if only right side is incorrectly placed
      if ((redFace[1][2] != 1 || blueFace[1][0] != 2) && (blueFace[1][0] != 5 && redFace[1][2] != 5))
        intReturn += 2; //Returns 2 if only left side is incorrectly placed
      //Returns 3 if both sides are incorrectly placed
    }
    if (array == blueFace) {
      if ((blueFace[1][0] != 2 || redFace[1][2] != 1) && (redFace[1][2] != 5 && blueFace[1][0] != 5))
        intReturn++;
      if ((blueFace[1][2] != 2 || orangeFace[1][0] != 3) && (orangeFace[1][0] != 5 && blueFace[1][2] != 5))
        intReturn += 2;
    }
    if (array == orangeFace) {
      if ((orangeFace[1][0] != 3 || blueFace[1][2] != 2) && (blueFace[1][2] != 5 && orangeFace[1][0] != 5))
        intReturn++;
      if ((orangeFace[1][2] != 3 || greenFace[1][0] != 4) && (greenFace[1][0] != 5 && orangeFace[1][2] != 5))
        intReturn += 2;
    }
    if (array == greenFace) {
      if ((greenFace[1][0] != 4 || orangeFace[1][2] != 3) && (orangeFace[1][2] != 5 && greenFace[1][0] != 5))
        intReturn++;
      if ((greenFace[1][2] != 4 || redFace[1][0] != 1) && (redFace[1][0] != 5 && greenFace[1][2] != 5))
        intReturn += 2;
    }
    return intReturn;
  }

  public boolean whiteEdgeCheck(int[][] array) { //Checks if a face contains a white edge
    if (array[0][1] == 0 || array[1][2] == 0 || array[2][1] == 0 || array[1][0] == 0) {
      return true;
    } else {
      return false;
    }
  }
 
  public boolean whiteCornerCheck(int[][] array) { // Checks if a face contains a white corner
    if (array[2][0] == 0 || array[2][2] == 0 || array[0][2] == 0 || array[0][0] == 0) {
      return true;
    } else {
      return false;
    }
  }

  public static String optimizedMoves (String rawMoves) { //Static method (stays in the class) to get ride of any unnecessary moves
    String[] moves = rawMoves.split(","); //Create array of strings for each induvidual move using , as a delimiter
    List<String> cleanedMoves = new ArrayList<>(); //Use a list instead of array to add and remove elements easily
    for (String move : moves) { //Go through each move and make a new string (move)
        move = move.trim();
        if (!move.isEmpty()) cleanedMoves.add(move); //moves are added to list
    }
    List<String> optimized = new ArrayList<>(); //New list for final optimized moves
    int i = 0;
    while (i < cleanedMoves.size()) { //Go through all elements of raw list
      String currentFace = getFace(cleanedMoves.get(i)); //Gets letter of move, method below
      List<String> group = new ArrayList<>(); //New list to store all consecutive moves of the same face
      while (i < cleanedMoves.size() && getFace(cleanedMoves.get(i)).equals(currentFace)) { //Keeps collecting moves as long as they're on the same face as currentFace
        group.add(cleanedMoves.get(i));
        i++;
      }
      int net = netRotation(group); //Method below that treates D as +1, D' as -1, etc, calculates the net rotation of the moves in the group, ex D D D, 3, becomes D'
      if (net != 0) {
        optimized.add(convertToMove(currentFace, net));
      }
    }
      // else: all moves cancel out
    return String.join(", ", optimized) + ",";
  }

  private static String getFace(String move) { //Method to get the first letter of the move (face of cube)
    return move.substring(0, 1); //Returns first charecter of move, move string is constantly changing to the new move, 0 is starteding index, 1 is ending index (exclusive)
  }

  public static int netRotation(List<String> moves) { //Method to calculate the net rotation of the moves in the group
    int total = 0; //counter to keep track of total rotation effect
    for (String move : moves) { //goes through all moves in list
      if (move.endsWith("2")) total += 2;
          else if (move.endsWith("'")) total -= 1;
          else total += 1;
      }
    return ((total % 4) + 4) % 4; //First %4 is to handle big numbers, gives remainder so same result, second +4 is to handle negative numbers
    }

  public static String convertToMove(String face, int net) { //Makes final string that the net moves would equate to
      switch (net) {
          case 1: return face;
          case 2: return face + "2";
          case 3: return face + "'";
          default: return ""; // net = 0 means no move
      }
  }

  public String shuffle() { //Method to shuffle the cube
    String shuffle = "";
    Random rand = new Random();
    for (int i = 0; i < 3; i++) { //Sets cube to solved
      for (int j = 0; j < 3; j++) {
        whiteFace[i][j] = 0;
        redFace[i][j] = 1;
        blueFace[i][j] = 2;
        orangeFace[i][j] = 3;
        greenFace[i][j] = 4;
        yellowFace[i][j] = 5;
      }
    }
    for (int i = 0; i < 20; i++) { // 20 random moves will shuffle the cube to its most mixed state
      int randomNumber = rand.nextInt(18) + 1;
      switch (randomNumber) {
        case 1:
          shuffle = R(shuffle);
          break;
        case 2:
          shuffle = Rp(shuffle);
          break;
        case 3:
          shuffle = R2(shuffle);
          break;
        case 4:
          shuffle = L(shuffle);
          break;
        case 5:
          shuffle = Lp(shuffle);
          break;
        case 6:
          shuffle = L2(shuffle);
          break;
        case 7:
          shuffle = F(shuffle);
          break;
        case 8:
          shuffle = Fp(shuffle);
          break;
        case 9:
          shuffle = F2(shuffle);
          break;
        case 10:
          shuffle = B(shuffle);
          break;
        case 11:
          shuffle = Bp(shuffle);
          break;
        case 12:
          shuffle = B2(shuffle);
          break;
        case 13:
          shuffle = U(shuffle);
          break;
        case 14:
          shuffle = Up(shuffle);
          break;
        case 15:
          shuffle = U2(shuffle);
          break;
        case 16:
          shuffle = D(shuffle);
          break;
        case 17:
          shuffle = Dp(shuffle);
          break;
        case 18:
          shuffle = D2(shuffle);
          break;
      }
    }
    return "Shuffling moves: " + shuffle;
  }
}
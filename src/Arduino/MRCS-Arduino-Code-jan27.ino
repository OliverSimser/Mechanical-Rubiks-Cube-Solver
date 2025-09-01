const int stepsPerRevolution = 200;
int stepDelay = 500;
int moveDelay = 1;

String instructions = "A4feAe0baba4A1F1432";

//with R facing you and White Upwards: a is U, A is Up, 0 is U2, b is F, B is Fp, 1 is F2, c is R, C is Rp, 2 is R2, d is B, D is Bp, 3 is B2, e is L, E is Lp, 4 is L2, f is D, F is Dp, 5 is D2
//white = [0]
// red = [1]
// blue = [2]
// orange = [3]
// green = [4]
// yellow = [5]

int dirPins[] = { 2, 4, 6, 8, 10, 12 };
int stepPins[] = { 3, 5, 7, 9, 11, 13 };

void decode(String instructions) {
  for (int i = 0; i < instructions.length(); i++) {

    if (i != instructions.length() - 1) {
      if (isOpposite(instructions[i], instructions[i + 1])) {
        Serial.print("a");
        doubleRotate(decode(instructions[i]), decode(instructions[i + 1]));
        i++;
      }
    }

    switch (instructions[i]) {
      case 'a':
        rotateCW(0);
        break;
      case 'b':
        rotateCW(1);
        break;
      case 'c':
        rotateCW(2);
        break;
      case 'd':
        rotateCW(3);
        break;
      case 'e':
        rotateCW(4);
        break;
      case 'f':
        rotateCW(5);
        break;
      case 'A':
        rotateCCW(0);
        break;
      case 'B':
        rotateCCW(1);
        break;
      case 'C':
        rotateCCW(2);
        break;
      case 'D':
        rotateCCW(3);
        break;
      case 'E':
        rotateCCW(4);
        break;
      case 'F':
        rotateCCW(5);
        break;
      case '0':
        rotate2(0);
        break;
      case '1':
        rotate2(1);
        break;
      case '2':
        rotate2(2);
        break;
      case '3':
        rotate2(3);
        break;
      case '4':
        rotate2(4);
        break;
      case '5':
        rotate2(5);
        break;
    }
  }
}


int decode(char c) {
  switch (c) {
    case 'a':
      return 00;
      break;
    case 'b':
      return 10;
      break;
    case 'c':
      return 20;
      break;
    case 'd':
      return 30;
      break;
    case 'e':
      return 40;
      break;
    case 'f':
      return 50;
      break;
    case 'A':
      return 01;
      break;
    case 'B':
      return 11;
      break;
    case 'C':
      return 21;
      break;
    case 'D':
      return 31;
      break;
    case 'E':
      return 41;
      break;
    case 'F':
      return 51;
      break;
    case '0':
      return 02;
      break;
    case '1':
      return 12;
      break;
    case '2':
      return 22;
      break;
    case '3':
      return 32;
      break;
    case '4':
      return 42;
      break;
    case '5':
      return 52;
      break;
  }
}

boolean isOpposite(char c1, char c2) {
  switch (c1) {
    case 'a':
      return c2 == 'f' || c2 == 'F' || c2 == '5';
      break;
    case 'b':
      return c2 == 'd' || c2 == 'D' || c2 == '3';
      break;
    case 'c':
      return c2 == 'e' || c2 == 'E' || c2 == '4';
      break;
    case 'd':
      return c2 == 'b' || c2 == 'B' || c2 == '1';
      break;
    case 'e':
      return c2 == 'c' || c2 == 'C' || c2 == '2';
      break;
    case 'f':
      return c2 == 'a' || c2 == 'A' || c2 == '0';
      break;
    case 'A':
      return c2 == 'f' || c2 == 'F' || c2 == '5';
      break;
    case 'B':
      return c2 == 'd' || c2 == 'D' || c2 == '3';
      break;
    case 'C':
      return c2 == 'e' || c2 == 'E' || c2 == '4';
      break;
    case 'D':
      return c2 == 'b' || c2 == 'B' || c2 == '1';
      break;
    case 'E':
      return c2 == 'c' || c2 == 'C' || c2 == '2';
      break;
    case 'F':
      return c2 == 'a' || c2 == 'A' || c2 == '0';
      break;
    case '0':
      return c2 == 'f' || c2 == 'F' || c2 == '5';
      break;
    case '1':
      return c2 == 'd' || c2 == 'D' || c2 == '3';
      break;
    case '2':
      return c2 == 'e' || c2 == 'E' || c2 == '4';
      break;
    case '3':
      return c2 == 'b' || c2 == 'B' || c2 == '1';
      break;
    case '4':
      return c2 == 'c' || c2 == 'C' || c2 == '2';
      break;
    case '5':
      return c2 == 'a' || c2 == 'A' || c2 == '0';
      break;
  }
}

void doubleRotate(int move1, int move2) {




  int motor1 = move1 / 10;
  int motor2 = move2 / 10;
  int moveType1 = move1 % 10;
  int moveType2 = move2 % 10;
  int moveLength1 = 50;
  int moveLength2 = 50;


  Serial.print("/n");
  Serial.print(moveType1);
  Serial.print(moveType2);

  digitalWrite(dirPins[motor1], LOW);  // CW
  digitalWrite(dirPins[motor2], LOW);  // CW

  if (moveType1 == 1) {
    digitalWrite(dirPins[motor1], HIGH);  // CCW
  } else if (moveType1 == 2) {
    moveLength1 = 100;
  }

  if (moveType2 == 1) {
    digitalWrite(dirPins[motor2], HIGH);  // CCW
  } else if (moveType2 == 2) {
    moveLength2 = 100;
  }

  int divisor = 4;

  if (moveLength1 > moveLength2) {
    divisor = stepsPerRevolution / moveLength1;
  } else {
    divisor = stepsPerRevolution / moveLength2;
  }

    Serial.print("C");
    Serial.print(moveLength1);
    Serial.print(moveLength2);

  // Spin motor
  for (int x = 0; x < stepsPerRevolution / divisor; x++) {  // 90 degree turn


    if (x < moveLength1) {
      digitalWrite(stepPins[motor1], HIGH);
      Serial.print("A");
    }
    if (x < moveLength2) {
      digitalWrite(stepPins[motor2], HIGH);
      Serial.print("B");
    }


    delayMicroseconds(stepDelay);


    if (x < moveLength1) {
      digitalWrite(stepPins[motor1], LOW);
    }
    if (x < moveLength2) {
      digitalWrite(stepPins[motor2], LOW);
    }


    delayMicroseconds(stepDelay);
  }



  delay(moveDelay);  // Wait a second
}

void rotateCW(int motor) {


  digitalWrite(dirPins[motor], LOW);  // CW

  // Spin motor
  for (int x = 0; x < stepsPerRevolution / 4; x++) {  // 90 degree turn
    digitalWrite(stepPins[motor], HIGH);
    delayMicroseconds(stepDelay);
    digitalWrite(stepPins[motor], LOW);
    delayMicroseconds(stepDelay);
  }

  delay(moveDelay);  // Wait a second
}

void rotateCCW(int motor) {

  digitalWrite(dirPins[motor], HIGH);

  // Spin motor
  for (int x = 0; x < stepsPerRevolution / 4; x++) {  // 90 degree turn
    digitalWrite(stepPins[motor], HIGH);
    delayMicroseconds(stepDelay);
    digitalWrite(stepPins[motor], LOW);
    delayMicroseconds(stepDelay);
  }

  delay(moveDelay);  // Wait a second
}


void rotate2(int motor) {

  digitalWrite(dirPins[motor], LOW);

  // Spin motor
  for (int x = 0; x < stepsPerRevolution / 2; x++) {  // 180 degree turn
    digitalWrite(stepPins[motor], HIGH);
    delayMicroseconds(stepDelay);
    digitalWrite(stepPins[motor], LOW);
    delayMicroseconds(stepDelay);
  }

  delay(moveDelay * 2);  // Wait a second
}


void stepCW(int motor, int steps) {
  digitalWrite(dirPins[motor], LOW);

  // Spin motor
  for (int x = 0; x < steps; x++) {  // 180 degree turn
    digitalWrite(stepPins[motor], HIGH);
    delayMicroseconds(stepDelay);
    digitalWrite(stepPins[motor], LOW);
    delayMicroseconds(stepDelay);
  }

  delay(moveDelay * (steps / 50));  // Wait a second
}

void stepCCW(int motor, int steps) {
  digitalWrite(dirPins[motor], HIGH);

  // Spin motor
  for (int x = 0; x < steps; x++) {  // 180 degree turn
    digitalWrite(stepPins[motor], HIGH);
    delayMicroseconds(stepDelay);
    digitalWrite(stepPins[motor], LOW);
    delayMicroseconds(stepDelay);
  }

  delay(moveDelay * (steps / 50));  // Wait a second
}

void setup() {


  delay(4000);

  Serial.begin(9600);



  // Serial.print(instructions);

  for (int i = 2; i < 14; i++) {
    pinMode(i, OUTPUT);
  }

  // stepCCW(0,600);
  stepCCW(5, 3);
  //stepCCW(3, 2);
  delay(1000);
  decode(instructions);
}


void loop() {
}

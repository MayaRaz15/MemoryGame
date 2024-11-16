	import java.util.Scanner;

	public class Assignment1 
	{

	public static Scanner sc = new Scanner(System.in);
	
	public static double[][] Board;
	public static boolean[][] BooleanBoard;
	public static boolean isE;
	public static int Rows = 0, Columns = 0;
	public static boolean PlayAgain = true;
	public static double[] ArrayWithValidCards;
	public static int turns;
	public static boolean AllMatched;

	 public static void main(String[] args) 
	 {
	  while(PlayAgain)
	  {
	   DefineBoard(); 
	   GameStart();
//	   GameFinished();
	   PlayAgain = PlayAgain();
	  }//while
	 }//main

	// public static int Input(String s)
	 {
	  
	 }
		
	 private static boolean PlayAgain() //function that checks the player's answer and starts another game or finishes the game.
	 {
	  System.out.println("Would you like to start a new game?");
	  char PlayersAnswer = GetChar(sc.nextLine());
	  if ( PlayersAnswer == 'y' || PlayersAnswer == 'Y')
	   return true;
	  if ( PlayersAnswer == 'n' || PlayersAnswer == 'N')
	  {
	   System.out.println("Goodbye!");
	   return false;
	  }//if
	  else
	  {
	   System.out.println("wrong input");
	   return PlayAgain();
	  }//else
	 }//PlayAgain

//	 public static boolean GameFinished() 
//	 {
//	  while (!isE || !AllMatched) 
//	  {
//	  if (AllMatched)
//	  {
//		  System.out.println("Good Job!");
//		  PrintBoard(Board);
//		  System.out.println("Memory Champ!");
//		  System.out.println(turns + "Number of Turns.");
//		  
//	  }//if
//	  if (isE)
//	  	{
//		 System.out.println("Thank you for playing Fatma Memory Game!"); 
//	  	}
//	  }
//	  return PlayAgain();
//	 }//FinishGame
//	 
	 public static void AllMatchedCheck(Boolean[][] Board)
	 {
		 int matched = 0;
		 for (int i = 0; i < Rows; i++) 
		 {
			 for (int j = 0; j < Columns; j++) 
			 {
				 if (BooleanBoard[i][j])
					 matched +=1;
			 }
		 }
		 if (matched == Rows * Columns)
			 AllMatched = true;
	 }
	 
	 public static void GameStart()
	 {
	  System.out.println("Excellent lets Play!");
//	  while(!GameFinished())
	  {
	   int FirstLocation = GetFirstLocation(Rows, Columns,sc);
	   int SecondLocation = GetSecondLocation(Rows, Columns,sc);
//	   IsMatch(FirstLocation, SecondLocation);
	   PrintBoard(Board);
	  }//while
	 }//GameStart
		
	 public static void IsMatch(int firstLocation, int secondLocation, int Rows, int Columns) 
	 {
//			if (firstLocation == -1 || secondLocation == -1)
//			{
//				System.out.println("someting went wrong, let's try to play again.");
//				GameFinished();
//			}
//			PrintBoard(Board);
//			if (firstLocation < 1000 && 99 < firstLocation)
//			{
//				if (Rows/10 == 0) 
//				{
//				if (Board[firstLocation/100][firstLocation%100] == Board[secondLocation/100][secondLocation%100])
//				{
//						
//					}
//				}
//			}
			
	}

	public static int GetSecondLocation(int rows2, int columns2, Scanner sc2) 
	 {
			System.out.println("Please enter the location of second card: ");
			int CardLocation = GetInt(sc.nextLine());
			if (CardLocationDoubleDigits(CardLocation) && CardLocationOnBoardTwoDigits (CardLocation, Rows, Columns))
			{
				return CardLocation;
			}//if valid card location
			
			else if (CardLocationThreeDigits(CardLocation) && CardLocationOnBoardThreeDigits (CardLocation, Rows, Columns))
			{
				return CardLocation;
			}
			System.out.println("Card location NOT valid.");
			return GetSecondLocation(rows2, columns2, sc2);	
	}
	
	public static boolean CardLocationOnBoardThreeDigits(int CardLocation, int Rows, int Columns) 
	{
		if (Rows < 10)
		{
			if (CardLocation/100 <= Rows && CardLocation%100 <= Columns) 
			{
				return true;
			}
		}
		if (Columns < 10)
		{
			if (CardLocation/10 <= Rows && CardLocation%10 >= Columns) 
			{
				return true;
			}
		}
		System.out.println("Card location NOT valid.");
		return false;
	}

	public static int GetFirstLocation(int Rows, int Columns, Scanner sc) 
	 {
		System.out.println("Please enter the location of first card: ");
		int CardLocation = GetInt(sc.nextLine());
		if (CardLocationDoubleDigits(CardLocation) && CardLocationOnBoardTwoDigits (CardLocation, Rows, Columns))
		{
			return CardLocation;
		}//if valid card location
		
		else if (CardLocationThreeDigits(CardLocation) && CardLocationOnBoardThreeDigits (CardLocation, Rows, Columns))
		{
			return CardLocation;
		}
		System.out.println("Card location NOT valid.");
		return GetSecondLocation(Rows, Columns, sc);
	 }
	
	public static boolean CardLocationDoubleDigits (int cardLocation)
	{
		return (cardLocation >= 10 && cardLocation <= 99);
	}
	
	public static boolean CardLocationOnBoardTwoDigits (int CardLocation, int Rows, int Columns)
	{
		return (CardLocation/10 <= Rows && CardLocation%10 <= Columns);
	}
	
	public static boolean CardLocationThreeDigits (int CardLocation)
	{
		return (99 < CardLocation && CardLocation <1000);
	}
	
	public static void DefineBoard() //function that defines the board
	 {
	  DefineBoardDimention();//change - add function that receives Strings
	  CreateNumbersForCards();//one dimension array
	  CrateBoardWithNumbers(ArrayWithValidCards);//creates the board with the wanted numbers
	  PrintBoard(Board);
	 }//DefineBoard
		
	 public static void CrateBoardWithNumbers(double[] array) 
	 {
	  Board = new double[Rows][Columns];// a new board has been created 
	  BooleanBoard = new boolean[Rows][Columns];
	  boolean[] BooleanSupportArray = new boolean[array.length];
	  for ( int i = 0 ; i <= Rows-1 ; i++) 
	  {
		  for ( int j = 0 ; j <= Columns-1 ; j++)
		  {
			    Board[i][j] = RandomNumberPlug(array, BooleanSupportArray);
		  }
	 }
	}//CrateBoardWithNumbers
	
	 public static double RandomNumberPlug(double[] arr, boolean[] barr) 
	 {//inserts the numbers from the array of numbers (NumbersForGame) randomly to the new Board
	  int placement = RandomNumber(arr.length);
	  while(barr[placement])
	   placement = RandomNumber(arr.length);
	  barr[placement] = true;
	  return arr[placement];
	 }//RandomNumberPlug
	 public static int RandomNumber(int arraylength)
	 {
	  return (int)(Math.random()*arraylength);//return random numbers from to to given number.
	 }//RandomNumber

	 public static double[] CreateArrayWithValidCards(double min, double mult)//fix this function
	 {
	  int MinimunMultiplier = (int)((min/mult)+1);
	  ArrayWithValidCards = new double[Rows*Columns];
	  for ( int i = 0 ; i < ArrayWithValidCards.length ;)
	  {
	   ArrayWithValidCards[i] = MinimunMultiplier*mult;
	   ArrayWithValidCards[i+1] = MinimunMultiplier*mult;
	   i+=2;
	   MinimunMultiplier++;
	  }//for
	  return ArrayWithValidCards;
	 }//CreateArrayWithValidCards

	 public static double[] CreateNumbersForCards()//receives minimum number and multiplier number so it can create an array of cards 
	 {
	  System.out.print("Please define the minimum number: ");
	  double Minumum = GetDouble(sc.nextLine());//change function that receives Strings
	  System.out.print("Please define the multiple: ");
	  double Multiple = GetDouble(sc.nextLine());
	  return CreateArrayWithValidCards(Minumum , Multiple);
	 }//CreateNumbersForCards

	 public static void DefineBoardDimention() // defines the size the the board
	 {
	  boolean ValidBoard = false;
	  while (!ValidBoard)
	  {
	  System.out.print("Please enter the number of rows of the board: ");
	  Rows = GetInt(sc.nextLine());//change to something that receives Strings
	  System.out.print("Please enter the number of columns of the board: ");
	  Columns = GetInt(sc.nextLine());
	  ValidBoard = (BoardSizeValid(Rows, Columns) && BoardEvenValid(Rows, Columns));
	  }//while
	 }//DefineBoardDimention
	 
	 public static boolean BoardSizeValid(int Rows, int Columns) //checks if the number of cells exceeds 20.
	 {
	  if ((Rows*Columns) > 20)
	   {
	   System.out.println("ERROR: the number of cells is too big.");
	   return false;
	   }//if
	  return true;
	 }//BoardSizeValid
	 
	 public static boolean BoardEvenValid(int Rows, int Columns) //checks if there is an even number of cells
	 {
	  if ((Rows*Columns)%2 == 1)
	  {
	   System.out.println("ERROR: the number of cells is odd.");
	   return false;
	  }
	  return true;
	 }
	 
	 public static void PrintBoard(double[][] array)
	 {
		 for ( int i = 0; i < array.length ; i++)
		 {
			 for ( int j = 0 ; j < array[i].length ; j++)
			 {
				 System.out.print(array[i][j] + "\t");
			 }
			 System.out.println();
		 }
	 }

	 public static int GetInt(String s) //function that recieves user's number.
	 {
	  if (s.indexOf('e') != -1)
	   isE = true;
	  if (s.indexOf('.') != -1)
	  {
	   System.out.println("this is not an integer. try again.");
	   String news = sc.nextLine();
	   GetInt(news);
	  }
	  return str2int(s); 
	 }
		
	 public static char GetChar(String s) 
	 {
	  if (s.indexOf('e') != -1)
	   isE = true;
	  if (s.length() > 1) 
	  {
		  System.out.println("this is not a valid char. try again.");
		   String news = sc.nextLine();
		   GetChar(news);
	  }
	  return s.charAt(0);
	 }
	 public static double GetDouble (String s)
	 {
		 if (s.indexOf('e') != -1)
			   isE = true;
		 if (s.indexOf('.') == -1) 
		 {
			 return (double) str2int(s);
		 }
		 return Double.parseDouble(s);
	 }
	 
	 public static int str2int(String s) //converting a string to an integer
	 {
		 boolean negative = false; 
			if (s.charAt(0) == '-') { 
				s = s.substring(1);
				negative = true;
			}
			int answer = 0;
			for (int i = 0; i < s.length(); i++) { 

				answer = answer * 10 + (s.charAt(i) - '0');
			}

			if (negative) 
				return answer * -1;
			return answer; 
	 }
	}//asignment_1
	

import java.util.Scanner;

public class Assignment1 
{
	public static Scanner sc = new Scanner(System.in);
	public static double[][] board;
	public static boolean[][] booleanBoard;
	public static boolean isE;
	public static int rows = 0, columns = 0;
	public static boolean playAgain = true;
	public static double[] arrayWithValidCards;
	public static int turns;
	public static boolean allMatched;
		
	public static void main(String[] args) 
	{
		while(playAgain)
		{
			DefineBoard();	
			PrintBoard(board,booleanBoard);
			GameStart();
			GameFinished();
			playAgain = PlayAgain();
		}//while
	}//main
		
	public static void IsMatch(double[] location1, double[] location2)
	{
	   if (card1!=card2)
	   {
		booleanBoard[location][location]=false;
		booleanBoard[location][location]=false;
		PrintBoard(board);
	   }
	}//IsMatch
	
	public static boolean PlayAgain() //function that checks the player's answer and starts another game or finishes the game.
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
//		 while (!isE || !AllMatched) 
//		 {
//			 if (AllMatched)
//			 {
//				System.out.println("Good Job!");
//				PrintBoard(Board);
//				System.out.println("Memory Champ!");
//				System.out.println(turns + "Number of Turns.");
//			 }//if
//			 if (isE)
//				 System.out.println("Thank you for playing Fatma Memory Game!"); 
//		 }
//		 return PlayAgain();
//	 }//GameFinished
	 
	 public static void AllMatchedCheck(Boolean[][] Board)
	 {
		 int matched = 0;
		 for (int i = 0; i < rows; i++) 
		 {
			 for (int j = 0; j < columns; j++) 
			 {
				 if (booleanBoard[i][j])
					 matched +=1;
			 }
		 }
		 if (matched == rows * columns)
			 allMatched = true;
	 }//AllMatchedCheck
	 
	public static void GameStart()
	{
		System.out.println("Excellent lets Play!");
		while(!GameFinished())
		{
			int FirstLocation = GetFirstLocation(rows, columns,sc);
			int SecondLocation = GetSecondLocation(rows, columns,sc);
			booleanBoard[getfirstlocation][getfirstlocation]=true;
			booleanBoard[getsecondllocation][getsecondllocation]=true;
			PrintBoard(board,booleanBoard);
			IsMatch();
		}//while
	}//GameStart
		
	 public static void IsMatch(int firstLocation, int secondLocation, int rows, int columns) 
	 {
//		 if (firstLocation == -1 || secondLocation == -1)
//		 {
//			 System.out.println("someting went wrong, let's try to play again.");
//			 GameFinished();
//		 }
//		 PrintBoard(board);
//		 if (firstLocation < 1000 && 99 < firstLocation)
//		 {
//			 if (rows/10 == 0) 
//			 {
//				 if (board[firstLocation/100][firstLocation%100] == board[secondLocation/100][secondLocation%100])
//				 {
//						
//				 }//if
//			 }//if
//		 }//if
	}//IsMatch

	public static int GetSecondLocation(int rows2, int columns2, Scanner sc2) 
	{
		System.out.println("Please enter the location of second card: ");
		int CardLocation = GetInt(sc.nextLine());
		if (CardLocationDoubleDigits(CardLocation) && CardLocationOnBoardTwoDigits (CardLocation, rows, Columns))
		{
			return CardLocation;
		}//if valid card location
		else if (CardLocationThreeDigits(CardLocation) && CardLocationOnBoardThreeDigits (CardLocation, rows, Columns))
		{
			return CardLocation;
		}//else if
		System.out.println("Card location NOT valid.");
		return GetSecondLocation(rows2, columns2, sc2);	
	}//GetSecondLocation
	
	public static boolean CardLocationOnBoardThreeDigits(int CardLocation, int Rows, int Columns) 
	{
		if (Rows < 10)
			if (CardLocation/100 <= Rows && CardLocation%100 <= Columns) 
				return true;
		if (Columns < 10)
			if (CardLocation/10 <= Rows && CardLocation%10 >= Columns) 
				return true;
		System.out.println("Card location NOT valid.");
		return false;
	}//CardLocationOnBoardThreeDigits

	public static int GetFirstLocation(int Rows, int Columns, Scanner sc) 
	{
		System.out.println("Please enter the location of first card: ");
		int CardLocation = GetInt(sc.nextLine());
		if (CardLocationDoubleDigits(CardLocation) && CardLocationOnBoardTwoDigits (CardLocation, Rows, Columns))
			return CardLocation;		//if valid card location
		else if (CardLocationThreeDigits(CardLocation) && CardLocationOnBoardThreeDigits (CardLocation, Rows, Columns))
			return CardLocation;
		System.out.println("Card location NOT valid.");
		return GetSecondLocation(Rows, Columns, sc);
	}//GetFirstLocation
	
	public static boolean CardLocationDoubleDigits (int cardLocation)
	{
		return (cardLocation >= 10 && cardLocation <= 99);
	}//CardLocationDoubleDigits
	
	public static boolean CardLocationOnBoardTwoDigits (int CardLocation, int Rows, int Columns)
	{
		return (CardLocation/10 <= Rows && CardLocation%10 <= Columns);
	}//CardLocationOnBoardTwoDigits
	
	public static boolean CardLocationThreeDigits (int CardLocation)
	{
		return (99 < CardLocation && CardLocation <1000);
	}//CardLocationThreeDigits 
	
	public static void DefineBoard() //function that defines the board
	{
		DefineBoardDimention();//change - add function that receives Strings
		CreateNumbersForCards();//one dimension array
		CrateBoardWithNumbers(arrayWithValidCards);//creates the board with the wanted numbers
	}//DefineBoard
		
	public static void CrateBoardWithNumbers(double[] array) 
	{
		board = new double[rows][columns];// a new board has been created 
		booleanBoard = new boolean[rows][columns];
		boolean[] BooleanSupportArray = new boolean[array.length];
		for ( int i = 0 ; i <= rows-1 ; i++)
			for ( int j = 0 ; j <= columns-1 ; j++)
				board[i][j] = RandomNumberPlug(array, BooleanSupportArray);
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
		arrayWithValidCards = new double[rows*columns];
		for ( int i = 0 ; i < arrayWithValidCards.length ;)
		{
			arrayWithValidCards[i] = MinimunMultiplier*mult;
			arrayWithValidCards[i+1] = MinimunMultiplier*mult;
			i+=2;
			MinimunMultiplier++;
		}//for
		return arrayWithValidCards;
	}//CreateArrayWithValidCards

	public static double[] CreateNumbersForCards()//receives minimum number and multiplier number so it can create an array of cards 
	{
		System.out.print("Please define the minimum number: ");
		double Minumum = GetDouble(sc.nextLine());
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
		rows = GetInt(sc.nextLine());
		System.out.print("Please enter the number of columns of the board: ");
		columns = GetInt(sc.nextLine());
		ValidBoard = (BoardSizeValid(rows, columns) && BoardEvenValid(rows, columns));
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
	}//BoardEvenValid
	 
	public static void PrintBoard(double[][] array ,boolean[][] bArr)
	{
		for ( int i = 0; i <= array.length-1 ; i++)
		{
			for ( int j  = 0 ;  j <= array[1].length-1 ; j++)
			{
				if (!bArr[i][j])
					System.out.print("#" + "\t");
				else
					System.out.print(array[i][j] + "\t");
			}//for
			System.out.println();
		}//for
	}//PrintBoard

	 public static int GetInt(String s) //function that recieves user's number.
	 {
		 if (s.indexOf('e') != -1)
			 isE = true;
		 if (s.indexOf('.') != -1)
		 {
			 System.out.println("this is not an integer. try again.");
			 String newS = sc.nextLine();
			 GetInt(newS);
		 }//if
		 return str2int(s); 
	 }//GetInt
		
	 public static char GetChar(String s) 
	 {
		 if (s.indexOf('e') != -1)
			 isE = true;
		 if (s.length() > 1) 
		 {
			 System.out.println("this is not a valid char. try again.");
			 String newS = sc.nextLine();
			 GetChar(newS);
		 }//if
		 return s.charAt(0);
	 }//GetChar
		
	 public static double GetDouble (String s)
	 {
		 if (s.indexOf('e') != -1)
			   isE = true;
		 if (s.indexOf('.') == -1) 
			 return (double) str2int(s);
		 return Double.parseDouble(s);
	 }//GetDouble

	 public static int str2int(String s) //converting a string to an integer
	 {
		 boolean negative = false; 
			if (s.charAt(0) == '-')
			{ 
				s = s.substring(1);
				negative = true;
			}//if
			int answer = 0;
			for (int i = 0; i < s.length(); i++)
				answer = answer * 10 + (s.charAt(i) - '0');
			if (negative)
				return answer * -1;
			return answer; 
	 }//str2int
	 
	public static void Printarray(double[] array)
	{
		for ( int i = 0; i <= array.length-1 ; i++)
			System.out.print(array[i] + "\t");
	}//Printarray
}//asignment_1

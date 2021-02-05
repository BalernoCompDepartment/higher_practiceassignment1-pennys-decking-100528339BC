
import java.util.Scanner;
import java.io.*;

class Deck{
  String name;
  double length;
  double width;
  double cost;
}
class Main {
  public static void main(String[] args) {
   Scanner scan = new Scanner(System.in);
   Deck [] DecksList = new Deck[6];
   String NameOfFile = "Decks.csv";

   DecksList = GetDecksFromFile(NameOfFile);
   displayOptions();
   executeOptions(DecksList);

   
   


   

  }
  public static Deck[] GetDecksFromFile(String fileName){

   Scanner scan = new Scanner(System.in);
   Scanner fileScanner = null;
   int index = 0;
   Deck [] tempDeckList  = new Deck[6];

   try {
     fileScanner = new Scanner (new BufferedReader (new FileReader (fileName)));
     fileScanner.useDelimiter("[\\r\\n,]+");
      while(fileScanner.hasNext()){
        tempDeckList[index] = new Deck();
        tempDeckList[index].name = fileScanner.next();
        tempDeckList[index].length = fileScanner.nextDouble();
        tempDeckList[index].width = fileScanner.nextDouble();
        tempDeckList[index].cost = fileScanner.nextDouble();
        index = index + 1;
      }
    }catch(FileNotFoundException error){
     System.out.println("file not found :(");
     }finally{
      if(fileScanner != null){
        fileScanner.close();
      }
     }
     return tempDeckList;
  }

  public static void displayOptions(){
    System.out.println("Option 1 => Display Cheapest Deck");
    System.out.println("Option 2 => Choose Minimum Length (inclusive)");
    System.out.println("Option 3 => Choose Minimum Area (non inclusive)");
    System.out.println("Option 4 => Close");
  }

  public static void executeOptions(Deck [] tempDecksList){
    Scanner scan = new Scanner(System.in);
    int optionNumber = 0;
    boolean option4 = false;

    while(!option4){
      optionNumber = getValidOption();
      if(optionNumber == 1){
        option1(tempDecksList);
      }else if(optionNumber == 2){
        option2(tempDecksList);
      }else if(optionNumber == 3){
        option3(tempDecksList);
      }else{
        option4 = true;
      }
      
    }
  }

  public static int getValidOption(){
    Scanner scan = new Scanner(System.in);
    int number = 0;

    do{
      System.out.println("please enter either 1, 2, 3 or 4");
      number = scan.nextInt();
    }while(number < 1 || number > 4);
    
    return number;
  }
  
  public static void option1(Deck[] DeckList){
    double cheapest = DeckList[0].cost;
    int cheapestPos = 0;
    for(int i = 1; i < DeckList.length; i++){
      if(DeckList[i].cost < cheapest){
        cheapest = DeckList[i].cost;
        cheapestPos = i;
      }
    }
   System.out.println("The Cheapest Deck is " + DeckList[cheapestPos]);
  }

  public static void option2(Deck[] DeckList){
    Scanner scan = new Scanner(System.in);

    System.out.println("Enter the minimum Length");
    double minLength = scan.nextDouble();

    for(int i = 0; i < DeckList.length; i++){
      if (DeckList[i].length >= minLength){
        System.out.println(""+ DeckList[i]);
      }
    }
  }

  public static void option3(Deck[] DeckList){
    Scanner scan = new Scanner(System.in);

    System.out.println("Enter Area");
    double area = scan.nextDouble();
    int counter = 0;

    for(int i = 0; i < DeckList.length; i++){
      if(DeckList[i].length*DeckList[i].width > area){
        counter = counter + 1;
      }
    }
    System.out.println("There are "+counter+" decks greater than this are");
  }
}
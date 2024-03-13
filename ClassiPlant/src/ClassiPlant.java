import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

public class ClassiPlant {

    private static final Scanner console = new Scanner(System.in);
    static final Database database = new Database();
    private static UserInfo userInfo = null;

    public static void main(String[] args) {

        intro();
        int menuChoice = menu();
        do {
            if (menuChoice == 1) { //list of all names
                ArrayList<PlantName> allPName = database.getAllNames();
                getNames(allPName);
            } else if (menuChoice == 2) { //search by name
                getPlantInfo();
            } else if (menuChoice == 3) { //classify plant
                userInfo = new UserInfo();
                getUserInfo();
                ArrayList<PlantName> pName = database.getPlant(userInfo);
                if (pName != null) {
                    System.out.println(" The top three matches for your plant are: ");
                    pName.toString();
                } else {
                    System.out.println("""
                            We could not find a match for your plant.
                            Please make sure all of your information is correct and try again.
                            Otherwise, your plant may not be a native species to Pierce County
                            """);
                }
            }
            menuChoice = menu();
        } while (menuChoice != 4);
    }

    static void intro() {
        flowerArt();
        System.out.print("""
                Welcome to ClassiPlant
                I am a plant classification and identification tool
                that can help you identify plants native to Pierce County, WA

                Please note that some information may vary and not always match descriptions exactly.
                Any dangers or edibility information is also a suggestion and to be followed up with
                additional research we are not responsible for any illness or reaction from interactions
                with plant species.

                We hope our tool encourages you to go outside and appreciate nature by observing it's
                characteristics carefully.
                
                Go touch grass.
                """);
    }

    /**
     * prints menu and gets response
     * @return menuChoice
     */
    static int menu() {
        int menuChoice = 0;
        boolean valid = false;
        while (!valid) {
            System.out.println("""
                    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                    Select a search method:
                    (1) See a list of names of all plants native to Pierce County
                    (2) Get information about a specific plant
                    (3) Identify a plant by providing information
                    (4) Quit
                    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                    """);
            try {
                menuChoice = console.nextInt();
                if (menuChoice >= 1 && menuChoice <= 4) {
                    valid = true;
                } else {
                    System.out.println("Please choose a valid option 1, 2, or 3");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number");
                console.next();
            }
        }
        return menuChoice;
    }

    /**
     * prints all names
     * @param thePName ArrayList of PlantName objects
     */
    static void getNames(final ArrayList<PlantName> thePName) {
        for (int i = 0; i < thePName.size(); i++) {
            System.out.println((i + 1) + ") " + thePName.get(i).toString());
        }
    }

    static void getPlantInfo() {
        System.out.println("Enter the common name of the plant you want information on: ");
        String plantName = console.next();
        PlantInfo plantInfo = database.getPlantInfo(plantName);
        if (plantInfo != null) {
            System.out.println(plantInfo.toString());
        }
    }

    /**
     * prompts user for information about plant
     */
    static void getUserInfo() {
        System.out.println("""
                Looking around, find a plant you'd like to identify
                What seems to be the average height of the plant? (in inches)
                """);
        int plantHeight = console.nextInt();
        userInfo.addHeightInfo(plantHeight);
        System.out.println("If your plant is a tree, what is the bark type? " +
                "If it is not a tree, please skip this question by hitting enter");
        System.out.println("What is the bark type of the tree?");
        String barkType = console.next();
        userInfo.addTreeInfo(barkType);
        System.out.println("Are there any fruits? Please enter yes or no");
        String fruits = console.next();
        if (fruits.equals("yes") || fruits.equals("y")) {
            fruitInfo();
        }
        System.out.println("Are there any flowers? Please enter yes or no");
        String flowers = console.next();
        if (flowers.equals("yes") || flowers.equals("y")) {
            flowerInfo();
        }
        leafInfo();
    }

    static void fruitInfo() {
        System.out.println("What is the color of the fruit?");
        String fruitColor = console.next();
        System.out.println("What is the shape of the fruit?");
        String fruitShape = console.next();
        System.out.println("Approximately, how large is the fruit?");
        String fruitSize = console.next();
        userInfo.addFruitInfo(fruitColor, fruitShape, Double.parseDouble(fruitSize));
    }

    static void flowerInfo() {
        System.out.println("How many petals does the flower have?");
        int petalNum = console.nextInt();
        System.out.println("What is the shape of the flower?");
        String flowerShape = console.next();
        System.out.println("What is the color of the flower?");
        String flowerColor = console.next();
        userInfo.addFlowerInfo(petalNum, flowerShape, flowerColor);
    }

    static void leafInfo() {
        System.out.println("What is the shape of the leaves?");
        String leafShape = console.next();
        System.out.println("What are the margins of the leaves?");
        String leafMargin = console.next();
        System.out.println("What is the texture of the leaves?");
        String leafTexture = console.next();
        System.out.println("About how long (in inches) are the leaves?");
        String leafLength = console.next();
        userInfo.addLeafInfo(leafShape, leafMargin, leafTexture, Integer.parseInt(leafLength));
    }

    /**
     * prints ASCII art of a flower
     */
    static void flowerArt() {
        System.out.println("""
                                    _
                                  _(_)_                          wWWWw   _
                      @@@@       (_)@(_)   vVVVv     _     @@@@  (___) _(_)_
                     @@()@@ wWWWw  (_)\\    (___)   _(_)_  @@()@@   Y  (_)@(_)
                      @@@@  (___)     `|/    Y    (_)@(_)  @@@@   \\|/   (_)\\
                       /      Y       \\|    \\|/    /(_)    \\|      |/      |
                    \\ |     \\ |/       | / \\ | /  \\|/       |/    \\|      \\|/
                   \\\\|//   \\\\|///  \\\\\\|//\\\\\\|/// \\|///  \\\\\\|//  \\\\|//  \\\\\\|//\s
                ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
                """);
    }
}

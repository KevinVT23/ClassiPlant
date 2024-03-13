public class PlantInfo {

    final private String myCommonName;
    final private String myScientificName;

    final private String myFlower;
    final private String myFlowerSeason;
    final private int myPetalNum;
    final private String myFlowerShape;
    final private String myFlowerColor;

    final private String myFruit;
    final private String myFruitSeason;
    final private String myFruitColor;
    final private String myFruitShape;
    final private double myFruitSize;

    final private String myLeafShape;
    final private String myLeafMargins;
    final private String myFoliage;
    final private String myLeafTexture;
    final private double myLeafLength;

    final private String myEdibility;
    final private String myDangers;

    PlantInfo(final String theCommonName, final String theScientificName, final String theFlower, final String theFlowerSeason,
              final int thePetalNum, final String theFlowerShape, final String theFlowerColor, final String theFruit,
              final String theFruitSeason, final String theFruitColor, final String theFruitShape, final double theFruitSize,
              final String theLeafShape, final String theLeafMargins, final String theFoliage, final String theLeafTexture,
              final double theLeafLength, final String theEdibility, final String theDangers) {
        myCommonName = theCommonName;
        myScientificName = theScientificName;

        myFlower = theFlower;
        myFlowerSeason = theFlowerSeason;
        myPetalNum = thePetalNum;
        myFlowerShape = theFlowerShape;
        myFlowerColor = theFlowerColor;

        myFruit = theFruit;
        myFruitSeason = theFruitSeason;
        myFruitColor = theFruitColor;
        myFruitShape = theFruitShape;
        myFruitSize = theFruitSize;

        myLeafShape = theLeafShape;
        myLeafMargins = theLeafMargins;
        myFoliage = theFoliage;
        myLeafTexture = theLeafTexture;
        myLeafLength = theLeafLength;

        myEdibility = theEdibility;
        myDangers = theDangers;
    }

    @Override
    public String toString() {
        String info = """
            Common Name: %s
            Scientific Name: %s
            
            Flower: %s
            Bloom Season: %s 
            Number of Petals: %d
            Shape of Flower: %s
            Color of Flower: %s
            
            Fruit: %s
            Season: %s
            Color of Fruit: %s
            Shape of Fruit: %s
            Size of Fruit: %.2f
            
            Leaf Shape: %s
            Margins: %s
            Foliage Types: %s
            Leaf Texture: %s
            Leaf Length: %.2f
            
            Edibility: %s
            Dangers: %s
            """;
        return String.format(info, myCommonName, myScientificName, myFlower,
                myFlowerSeason, myPetalNum, myFlowerShape, myFlowerColor, myFruit,
                myFruitSeason, myFruitColor, myFruitShape, myFruitSize,
                myLeafShape, myLeafMargins, myFoliage, myLeafTexture, myLeafLength,
                myEdibility, myDangers);
    }

}

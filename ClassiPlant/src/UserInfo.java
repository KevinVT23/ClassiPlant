public class UserInfo {
    private int myHeight;

    private String myFruitColor;
    private String myFruitShape;
    private double myFruitSize;

    private int myPetalNum;
    private String myFlowerShape;
    private String myFlowerColor;

    private String myBarkType;

    private String myLeafShape;
    private String myLeafMargins;
    private String myLeafTexture;
    private int myLeafLength;

    void addHeightInfo(final int theHeight) {
        myHeight = theHeight;
    }

    void addTreeInfo(final String theBarkType) {
        myBarkType = theBarkType;
    }

    void addFruitInfo(final String theFruitColor, final String theFruitShape,
                      final double theFruitSize) {
        myFruitColor = theFruitColor;
        myFruitShape = theFruitShape;
        myFruitSize = theFruitSize;
    }

    void addFlowerInfo(final int thePetalNum, final String theFlowerShape,
                       final String theFlowerColor) {
        myPetalNum = thePetalNum;
        myFlowerShape = theFlowerShape;
        myFlowerColor = theFlowerColor;
    }

    void addLeafInfo(final String theLeafShape, final String theLeafMargins, final String theLeafTexture,
                             final int theLeafLength) {
        myLeafShape = theLeafShape;
        myLeafMargins = theLeafMargins;
        myLeafTexture = theLeafTexture;
        myLeafLength = theLeafLength;
    }

    int getHeight() {
        return myHeight;
    }

    String getFruitColor() {
        return myFruitColor;
    }

    String getFruitShape() {
        return myFruitShape;
    }

    double getFruitSize() {
        return myFruitSize;
    }

    int getPetalNum() {
        return myPetalNum;
    }

    String getFlowerShape() {
        return myFlowerShape;
    }

    String getFlowerColor() {
        return myFlowerColor;
    }

    String getBarkType() {
        return myBarkType;
    }

    String getLeafShape() {
        return myLeafShape;
    }

    String getLeafMargins() {
        return myLeafMargins;
    }

    String getLeafTexture() {
        return myLeafTexture;
    }

    int getLeafLength() {
        return myLeafLength;
    }

}


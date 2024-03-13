public class PlantName {

    final private String myCommonName;
    final private String myScientificName;

    PlantName(final String theCommonName, final String theScientificName) {
        myCommonName = theCommonName;
        myScientificName = theScientificName;
    }

    String getMyCommonName() {
        return myCommonName;
    }

    String getMyScientificName() {
        return myScientificName;
    }

    @Override
    public String toString() {
        return myCommonName + " (" + myScientificName + ")";
    }
}

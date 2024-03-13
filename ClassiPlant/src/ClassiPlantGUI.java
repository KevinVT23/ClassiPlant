import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ClassiPlantGUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(ClassiPlantGUI::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("ClassiPlant");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("ClassiPlant", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(titleLabel, BorderLayout.NORTH);

        JTextArea introText = new JTextArea("""

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
        introText.setWrapStyleWord(true);
        introText.setLineWrap(true);
        introText.setOpaque(false);
        introText.setEditable(false);
        introText.setFocusable(false);
        frame.add(introText, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10)); // Changed to GridLayout with 3 rows and 1 column

        JButton button1 = new JButton("List of all plants");
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                plantNameListSubWindow();
            }
        });
        buttonPanel.add(button1);

        JButton button2 = new JButton("Information on a plant");
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                plantInfoSubWindow();
            }
        });
        buttonPanel.add(button2);

        JButton button3 = new JButton("Identify a plant");
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                classifyPlantSubWindow();
            }
        });
        buttonPanel.add(button3);

        frame.add(buttonPanel, BorderLayout.SOUTH); // Changed to SOUTH

        frame.setVisible(true);
    }


    private static void plantNameListSubWindow() {
        JFrame subFrame = new JFrame("All Plant Names");
        subFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        subFrame.setSize(500, 900);
        ArrayList<PlantName> allPName = ClassiPlant.database.getAllNames();
        JTextArea textArea = new JTextArea();
        for (int i = 0; i < allPName.size(); i++) {
            textArea.append((i + 1) + ") " + allPName.get(i).toString() + "\n");
        }
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        subFrame.add(scrollPane);
        subFrame.setVisible(true);
    }

    private static void plantInfoSubWindow() {
        JFrame subFrame = new JFrame("Plant Information");
        subFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        subFrame.setSize(500, 900);
        JPanel panel = new JPanel();
        JLabel promptLabel = new JLabel("Enter the common name of the plant:");
        JTextField plantNameField = new JTextField(10);
        JButton goButton = new JButton("Go");
        JTextArea plantInfoTextArea = new JTextArea();
        plantInfoTextArea.setEditable(false);
        goButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String enteredPlantName = plantNameField.getText();
                PlantInfo plantInfo = ClassiPlant.database.getPlantInfo(enteredPlantName);

                if (plantInfo != null) {
                    plantInfoTextArea.setText(plantInfo.toString());
                } else {
                    plantInfoTextArea.setText("Plant not found it is also possible that the plant you are trying " +
                            "to gather information about is not a native plant to Pierce County");
                }
            }
        });
        panel.add(promptLabel);
        panel.add(Box.createVerticalStrut(10)); // Add vertical spacing
        panel.add(plantNameField);
        panel.add(Box.createVerticalStrut(10)); // Add vertical spacing
        panel.add(goButton);
        subFrame.add(panel, BorderLayout.NORTH);
        subFrame.add(new JScrollPane(plantInfoTextArea), BorderLayout.CENTER);
        subFrame.setVisible(true);
    }

    private static void classifyPlantSubWindow() {
        JFrame subFrame = new JFrame("Plant Information");
        subFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        subFrame.setSize(500, 900);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel heightLabel = new JLabel("Height of the plant (in inches):");
        JTextField heightField = new JTextField();
        JLabel barkTypeLabel = new JLabel("Bark Type (if tree):");

        JLabel fruitsLabel = new JLabel("Are there any fruits?");
        JRadioButton yesFruitsRadioButton = new JRadioButton("Yes");
        JRadioButton noFruitsRadioButton = new JRadioButton("No");
        ButtonGroup fruitsButtonGroup = new ButtonGroup();
        fruitsButtonGroup.add(yesFruitsRadioButton);
        fruitsButtonGroup.add(noFruitsRadioButton);
        JLabel fruitColorLabel = new JLabel("What is the color of the fruit?");
        JLabel fruitShapeLabel = new JLabel("What is the shape of the fruit?");
        JLabel fruitSizeLabel = new JLabel("Approximately, how large is the fruit?");
        JTextField fruitSizeField = new JTextField();

        JLabel flowersLabel = new JLabel("Are there any flowers?");
        JRadioButton yesFlowersRadioButton = new JRadioButton("Yes");
        JRadioButton noFlowersRadioButton = new JRadioButton("No");
        ButtonGroup flowersButtonGroup = new ButtonGroup();
        flowersButtonGroup.add(yesFlowersRadioButton);
        flowersButtonGroup.add(noFlowersRadioButton);
        JLabel flowerPetalNumLabel = new JLabel("How many petals does the flower have?");
        JTextField flowerPetalNumField = new JTextField();
        JLabel flowerColorLabel = new JLabel("What is the color of the flower?");
        JLabel flowerShapeLabel = new JLabel("What is the shape of the flower?");

        JLabel leafShapeLabel = new JLabel("What is the shape of the leaf?");
        JLabel leafMarginLabel = new JLabel("What is the margin of the leaf?");
        JLabel leafTextureLabel = new JLabel("What is the texture of the leaf?");
        JLabel leafLengthLabel = new JLabel("What is the length of the leaf?");
        JTextField leafLengthField = new JTextField();

        JButton flowerShapePictureButton = new JButton("Flower Shape Info");
        JButton leafShapePictureButton = new JButton("Leaf Shape Info");
        JButton leafMarginPictureButton = new JButton("Leaf Margin Info");
        flowerShapePictureButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showPictureWindow("flower_shapes.jpg");
            }
        });

        leafShapePictureButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showPictureWindow("leaf_shapes.jpg");
            }
        });

        leafMarginPictureButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showPictureWindow("leaf_margins.jpg");
            }
        });

        String[] barkTypeOptions = {" ","Smooth", "Rough", "Hairy"};
        JComboBox<String> barkTypeComboBox = new JComboBox<>(barkTypeOptions);
        String[] fruitColorOptions = {" ", "Red", "Blue", "Purple", "Green", "Brown", "Orange", "Black"};
        JComboBox<String> fruitColorComboBox = new JComboBox<>(fruitColorOptions);
        String[] fruitsShapeOptions = {" ", "Round", "Oval", "Cluster"};
        JComboBox<String> fruitShapeComboBox = new JComboBox<>(fruitsShapeOptions);
        String[] flowerColorOptions = {" ", "White", "Pink", "Yellow", "Green", "Red", "Orange", "Purple"};
        JComboBox<String> flowerColorComboBox = new JComboBox<>(flowerColorOptions);
        String[] flowerShapeOptions = {" ", "Round", "Cup", "Tubular", "Umbrella", "Oval", "flat", "Urn", "Catkin",
                "Star", "Simple", "Pointed", "Cluster", "Bell", "Long"};
        JComboBox<String> flowerShapeComboBox = new JComboBox<>(flowerShapeOptions);
        String[] leafShapeOptions = {" ", "Linear", "Lanceolate", "Elliptical", "Truncate", "Oval", "Palmate", "Pinnate",
                "Simple", "Cordate", "Lobed"};
        JComboBox<String> leafShapeComboBox = new JComboBox<>(leafShapeOptions);
        String[] leafMarginOptions = {" ", "Entire", "Serrated", "Toothed", "Wavy"};
        JComboBox<String> leafMarginComboBox = new JComboBox<>(leafMarginOptions);
        String[] leafTextureOptions = {" ", "Smooth", "Hairy", "Rough", "Waxy"};
        JComboBox<String> leafTextureComboBox = new JComboBox<>(leafTextureOptions);

        JButton submitButton = new JButton("Submit");
        JTextArea identificationResult = new JTextArea();
        identificationResult.setEditable(false);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UserInfo userInfo = new UserInfo();
                try {
                    // Collect user input from UI elements
                    int height = Integer.parseInt(heightField.getText());
                    String selectedBarkType = (String) barkTypeComboBox.getSelectedItem();
                    userInfo.addHeightInfo(height);
                    userInfo.addTreeInfo(selectedBarkType);
                    // additional information if fruits yes
                    double fruitSize = 0; // Default value for fruit size
                    if (yesFruitsRadioButton.isSelected()) {
                        String fruitSizeInput = fruitSizeField.getText();
                        String selectedFruitColor = (String) fruitColorComboBox.getSelectedItem();
                        String selectedFruitShape = (String) fruitShapeComboBox.getSelectedItem();
                        if (!fruitSizeInput.isEmpty()) {
                            fruitSize = Double.parseDouble(fruitSizeInput);
                        } else {
                            identificationResult.setText("Please enter valid value for fruit size.");
                            return;
                        }
                        userInfo.addFruitInfo(selectedFruitColor, selectedFruitShape, fruitSize);
                    }
                    // additional information if flowers yes
                    int flowerPetalNum = 0; // Default value for flower petal number
                    if (yesFlowersRadioButton.isSelected()) {
                        String flowerPetalNumInput = flowerPetalNumField.getText();
                        String selectedFlowerColor = (String) flowerColorComboBox.getSelectedItem();
                        String selectedFlowerShape = (String) flowerShapeComboBox.getSelectedItem();
                        if (!flowerPetalNumInput.isEmpty()) {
                            flowerPetalNum = Integer.parseInt(flowerPetalNumInput);
                        } else {
                            identificationResult.setText("Please enter valid integer value for flower petal number.");
                            return; // Exit the method if flower petal number is empty
                        }
                        userInfo.addFlowerInfo(flowerPetalNum, selectedFlowerShape, selectedFlowerColor);
                    }
                    String selectedLeafShape = (String) leafShapeComboBox.getSelectedItem();
                    String selectedLeafMargin = (String) leafMarginComboBox.getSelectedItem();
                    String selectedLeafTexture = (String) leafTextureComboBox.getSelectedItem();
                    int leafLength = Integer.parseInt(leafLengthField.getText());
                    userInfo.addLeafInfo(selectedLeafShape, selectedLeafMargin, selectedLeafTexture, leafLength);

                    // Query the database to identify the plant
                    ArrayList<PlantName> classifiedPlants = ClassiPlant.database.getPlant(userInfo);

                    if (classifiedPlants != null) {
                        StringBuilder result = new StringBuilder("Top three matches:\n");
                        for (PlantName plantName : classifiedPlants) {
                            result.append(plantName.toString()).append("\n");
                        }
                        identificationResult.setText(result.toString());
                    } else {
                        identificationResult.setText("""
                                No match found. Please try again with different values.
                                Check your information carefully, it is also possible that
                                the plant you are trying to identify is not a native plant
                                to Pierce County, WA
                                """);
                    }
                } catch (NumberFormatException ex) {
                    identificationResult.setText("Please enter valid values for numeric fields.");
                }
            }
        });

        panel.add(heightLabel);
        panel.add(heightField);
        panel.add(barkTypeLabel);
        panel.add(barkTypeComboBox);

        panel.add(fruitsLabel);
        panel.add(yesFruitsRadioButton);
        panel.add(noFruitsRadioButton);
        JPanel fruitsPanel = new JPanel();
        fruitsPanel.setLayout(new BoxLayout(fruitsPanel, BoxLayout.Y_AXIS));
        fruitsPanel.add(fruitColorLabel);
        fruitsPanel.add(fruitColorComboBox);
        fruitsPanel.add(fruitShapeLabel);
        fruitsPanel.add(fruitShapeComboBox);
        fruitsPanel.add(fruitSizeLabel);
        fruitsPanel.add(fruitSizeField);
        fruitsPanel.add(Box.createVerticalStrut(10));

        JPanel flowersPanel = new JPanel();
        flowersPanel.setLayout(new BoxLayout(flowersPanel, BoxLayout.Y_AXIS));
        flowersPanel.add(flowerPetalNumLabel);
        flowersPanel.add(flowerPetalNumField);
        flowersPanel.add(flowerColorLabel);
        flowersPanel.add(flowerColorComboBox);
        flowersPanel.add(flowerShapeLabel);
        flowersPanel.add(flowerShapePictureButton);
        flowersPanel.add(flowerShapeComboBox);
        flowersPanel.add(Box.createVerticalStrut(10));

        yesFruitsRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fruitsPanel.setVisible(true);
                flowersPanel.setVisible(false);
                subFrame.pack();
            }
        });
        noFruitsRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fruitsPanel.setVisible(false);
                flowersPanel.setVisible(false);
                subFrame.pack();
            }
        });
        panel.add(fruitsPanel);
        panel.add(flowersLabel);

        panel.add(yesFlowersRadioButton);
        panel.add(noFlowersRadioButton);
        yesFlowersRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                flowersPanel.setVisible(true);
                subFrame.pack();
            }
        });
        noFlowersRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                flowersPanel.setVisible(false);
                subFrame.pack();
            }
        });
        panel.add(flowersPanel);
        panel.add(leafShapeLabel);
        panel.add(leafShapePictureButton);
        panel.add(leafShapeComboBox);
        panel.add(leafMarginLabel);
        panel.add(leafMarginPictureButton);
        panel.add(leafMarginComboBox);
        panel.add(leafTextureLabel);
        panel.add(leafTextureComboBox);
        panel.add(leafLengthLabel);
        panel.add(leafLengthField);
        panel.add(submitButton);
        subFrame.add(panel, BorderLayout.NORTH);
        subFrame.add(new JScrollPane(identificationResult), BorderLayout.CENTER);
        subFrame.setVisible(true);
    }

    private static void showPictureWindow(String pictureFileName) {
        JFrame pictureFrame = new JFrame("Picture");
        pictureFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pictureFrame.setSize(600, 600);

        ImageIcon originalIcon = new ImageIcon(pictureFileName);
        Image originalImage = originalIcon.getImage();

        // Calculate the scaled width and height to fit the window
        int maxWidth = 600;
        int maxHeight = 600;
        int originalWidth = originalImage.getWidth(null);
        int originalHeight = originalImage.getHeight(null);

        double scaleFactor = Math.min((double) maxWidth / originalWidth, (double) maxHeight / originalHeight);

        int scaledWidth = (int) (originalWidth * scaleFactor);
        int scaledHeight = (int) (originalHeight * scaleFactor);

        Image scaledImage = originalImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JLabel pictureLabel = new JLabel(scaledIcon);

        pictureFrame.add(pictureLabel);
        pictureFrame.pack(); // Resize the frame to fit the picture
        pictureFrame.setVisible(true);
    }

}


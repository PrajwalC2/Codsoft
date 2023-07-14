package application;
	
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class Main extends Application {
	private TextArea textArea;
    private Label wordCountLabel;
    private Label uniqueWordsLabel;
    private ListView<String> wordFrequencyListView;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Word Counter");

        BorderPane root = new BorderPane();

        // Text area for user input
        textArea = new TextArea();
        textArea.setPrefHeight(200);

        // Button to open file
        Button fileButton = new Button("Open File");
        fileButton.setOnAction(e -> openFile(primaryStage));

        // Button to count words
        Button countButton = new Button("Count Words");
        countButton.setOnAction(e -> countWords());

        // Label for word count
        wordCountLabel = new Label();

        // Label for unique words count
        uniqueWordsLabel = new Label();

        // List view for word frequency
        wordFrequencyListView = new ListView<>();

        VBox vBox = new VBox(10);
        vBox.setPadding(new Insets(10));
        vBox.getChildren().addAll(textArea, fileButton, countButton, wordCountLabel, uniqueWordsLabel, wordFrequencyListView);

        root.setCenter(vBox);

        Scene scene = new Scene(root, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void openFile(Stage primaryStage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        File file = fileChooser.showOpenDialog(primaryStage);

        if (file != null) {
            try {
                Scanner fileScanner = new Scanner(file);
                StringBuilder stringBuilder = new StringBuilder();
                while (fileScanner.hasNextLine()) {
                    stringBuilder.append(fileScanner.nextLine()).append(" ");
                }
                textArea.setText(stringBuilder.toString());
                fileScanner.close();
            } catch (FileNotFoundException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("File not found.");
                alert.showAndWait();
            }
        }
    }

    private void countWords() {
        String inputText = textArea.getText();

        // Split the string into an array of words
        String[] words = inputText.split("[\\s\\p{Punct}]+");

        // Initialize a counter variable to keep track of the number of words
        int wordCount = 0;

        // Create a HashMap to store word frequencies
        Map<String, Integer> wordFrequencies = new HashMap<>();

        // Iterate through the array of words and increment the counter for each word encountered
        for (String word : words) {
            // Ignore common words or stop words
            if (isStopWord(word)) {
                continue;
            }

            wordCount++;

            // Update word frequency
            wordFrequencies.put(word, wordFrequencies.getOrDefault(word, 0) + 1);
        }

        // Update labels
        wordCountLabel.setText("Total number of words: " + wordCount);
        uniqueWordsLabel.setText("Number of unique words: " + wordFrequencies.size());

        // Update word frequency list view
        List<String> frequencyList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : wordFrequencies.entrySet()) {
            frequencyList.add(entry.getKey() + ": " + entry.getValue());
        }
        wordFrequencyListView.getItems().setAll(frequencyList);
    }

    private boolean isStopWord(String word) {
        // List of common stop words
        String[] stopWords = {"the", "and", "is", "in", "a", "to", "of"};

        // Check if the word is a stop word
        return Arrays.asList(stopWords).contains(word.toLowerCase());
    }
}

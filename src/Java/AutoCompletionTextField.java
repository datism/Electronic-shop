package Java;

import javafx.geometry.Side;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.*;
import java.util.stream.Collectors;

public class AutoCompletionTextField extends TextField {
    //Local variables
    //entries to autocomplete
    private Set<String> nameEntries;
    private Set<String> manuEntries;
    //popup GUI
    private final ContextMenu nameEntriesPopup;
    private final ContextMenu manuEntriesPopup;

    public AutoCompletionTextField() {
        super();
        this.nameEntries = new TreeSet<>();
        this.manuEntries = new TreeSet<>();
        this.nameEntriesPopup = new ContextMenu();
        this.manuEntriesPopup = new ContextMenu();
        manuEntriesPopup.setUserData("getHangSanXuat");
        nameEntriesPopup.setUserData("getTen");
        setListener();
    }

    private void setListener() {
        //Add "suggestions" by changing text
        textProperty().addListener((observable, oldValue, newValue) -> {
            String enteredText = getText();
            //always hide suggestion if nothing has been entered (only "spacebars" are dissalowed in TextFieldWithLengthLimit)
            if (enteredText == null || enteredText.isEmpty()) {
                nameEntriesPopup.hide();
                manuEntriesPopup.hide();
            } else {
                //filter all possible suggestions depends on "Text", case insensitive
                List<String> filteredNameEntries = nameEntries.stream()
                        .filter(e -> e.toLowerCase().contains(enteredText.toLowerCase()))
                        .collect(Collectors.toList());
                //some suggestions are found
                if (!filteredNameEntries.isEmpty()) {
//                    filteredNameEntries.add(-1, "Ten: ");
                    //build popup - list of "CustomMenuItem"
                    populatePopup(filteredNameEntries, enteredText, nameEntriesPopup);
                    if (!nameEntriesPopup.isShowing()) { //optional
                        nameEntriesPopup.show(AutoCompletionTextField.this, Side.BOTTOM, 0, 0); //position of popup
                    }
                    //no suggestions -> hide
                } else {
                    nameEntriesPopup.hide();
                }

                List<String> filteredManuEntries = manuEntries.stream()
                        .filter(e -> e.toLowerCase().contains(enteredText.toLowerCase()))
                        .collect(Collectors.toList());
                if (!filteredManuEntries.isEmpty()) {
                    //build popup - list of "CustomMenuItem"
                    populatePopup(filteredManuEntries, enteredText, manuEntriesPopup);
                    if (!manuEntriesPopup.isShowing()) { //optional

                        manuEntriesPopup.show(AutoCompletionTextField.this, Side.BOTTOM, 200, 0); //position of popup
                    }
                    //no suggestions -> hide
                } else {
                    manuEntriesPopup.hide();
                }

            }
        });

        //Hide always by focus-in (optional) and out
        focusedProperty().addListener((observableValue, oldValue, newValue) -> {
            nameEntriesPopup.hide();
            manuEntriesPopup.hide();
        });
    }

    private void populatePopup(List<String> searchResult, String searchReauest, ContextMenu menu) {
        //List of "suggestions"
        List<CustomMenuItem> menuItems = new LinkedList<>();
        //List size - 10 or founded suggestions count
        int maxEntries = 10;
        int count = Math.min(searchResult.size(), maxEntries);
        //Build list as set of labels
        for (int i = 0; i < count; i++) {
            final String result = searchResult.get(i);
            //label with graphic (text flow) to highlight founded subtext in suggestions
            Label entryLabel = new Label();
            entryLabel.setGraphic(buildTextFlow(result, searchReauest));
            entryLabel.setPrefHeight(10);
            CustomMenuItem item = new CustomMenuItem(entryLabel, true);
            menuItems.add(item);

            //if any suggestion is select set it into text and close popup
            item.setOnAction(actionEvent -> {
                setText(result);
                setUserData(menu.getUserData());
                positionCaret(result.length());
                menu.hide();
            });
        }
        //"Refresh" context menu
        menu.getItems().clear();
        menu.getItems().addAll(menuItems);
    }

    //Format
    private TextFlow buildTextFlow(String text, String filter) {
        int filterIndex = text.toLowerCase().indexOf(filter.toLowerCase());
        Text textBefore = new Text(text.substring(0, filterIndex));
        Text textAfter = new Text(text.substring(filterIndex + filter.length()));
        Text textFilter = new Text(text.substring(filterIndex, filterIndex + filter.length())); //instead of "filter" to keep "case"
        textFilter.setFill(Color.ORANGE);
        textFilter.setFont(Font.font("Helvetica", FontWeight.BOLD, 12));
        return new TextFlow(textBefore, textFilter, textAfter);
    }

    //source
    public void setNameEntries(Set<String> nameEntries) {
        this.nameEntries = nameEntries;
    }

    public void setManuEntries(Set<String> manuEntries) {
        this.manuEntries = manuEntries;
    }

}
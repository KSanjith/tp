package ymfc;

import java.io.FileNotFoundException;
import java.util.logging.Logger;
import java.util.logging.Level;

import ymfc.parser.Parser;
import ymfc.commands.Command;
import ymfc.exception.InvalidArgumentException;
import ymfc.exception.InvalidCommandException;
import ymfc.recipelist.RecipeList;
import ymfc.storage.Storage;
import ymfc.ui.Ui;

public class YMFC {
    private static final String saveFilePath = "./data/recipes.txt";
    private static Logger logger = Logger.getLogger(YMFC.class.getName());

    /**
     * Main entry-point for the java.ymfc.YMFC application.
     */
    public static void main(String[] args) {
        assert false : "failure testing";
        Ui ui = new Ui(System.in);
        RecipeList recipeList = new RecipeList();
        Storage storage = new Storage(saveFilePath);
        boolean saidBye = false;

        logger.log(Level.FINE, "Starting YMFC");
        ui.greet();

        try {
            storage.loadRecipes(recipeList, ui, storage);
            logger.log(Level.INFO, "Save file found");
        } catch (FileNotFoundException e) {
            logger.log(Level.INFO, "No save file found");
        }


        String userInput;
        while (!saidBye) {
            userInput = ui.readCommand();

            try {
                Command command = Parser.parseCommand(userInput);
                command.execute(recipeList, ui, storage);

                if (command.isBye()) {
                    saidBye = true;
                    logger.log(Level.INFO, "User said bye");
                }
            } catch (InvalidArgumentException | InvalidCommandException e) {
                System.out.println(e.getMessage());
            }


        }

        logger.log(Level.FINE, "Ending YMFC");

    }

}

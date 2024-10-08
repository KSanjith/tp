package YMFC.commands;

import YMFC.Recipe;
import YMFC.RecipeList;
import YMFC.Ui;

public abstract class Command {
    protected static boolean isBye;

    public Command() {
        isBye = false;
    }

    public void setBye() {
        Command.isBye = true;
    }

    public boolean isBye() {
        return isBye;
    }


    public void execute(RecipeList recipes, Ui ui) {

    };

    public void addNewRecipe(RecipeList recipes, Recipe newRecipe, Ui ui) {
        recipes.addRecipe(newRecipe);
        ui.printAddedRecipe(newRecipe.toString(), recipes.getCounter());
    }
}

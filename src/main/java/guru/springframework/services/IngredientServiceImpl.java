package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.convertors.IngredientToIngredientCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {
    private final RecipeRepository recipeRepository;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;

    public IngredientServiceImpl(RecipeRepository recipeRepository, IngredientToIngredientCommand ingredientToIngredientCommand) {
        this.recipeRepository = recipeRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        Optional<Recipe> detachedRecipe = recipeRepository.findById(recipeId);
        if (!detachedRecipe.isPresent()) {

        }
        Recipe foundRecipe = detachedRecipe.get();
        Optional<IngredientCommand> foundIngredientOptional = foundRecipe.getIngredients()
                .stream().filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(ingredientToIngredientCommand::convert).findFirst();
        if (!foundIngredientOptional.isPresent()) {
            log.error("Ingredient not found");
        }
        return foundIngredientOptional.get();
    }
}

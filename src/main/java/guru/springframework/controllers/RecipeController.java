package guru.springframework.controllers;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("recipe/{id}/show")
    public String recipeById(@PathVariable String id, Model model) {
        log.debug("Getting Index page");
        model.addAttribute("recipe", recipeService.findRecipeById(Long.valueOf(id)));
        return "recipe/show";
    }
    @GetMapping
    @RequestMapping("recipe/new")
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new RecipeCommand());
        return "recipe/recipeForm";
    }

    @GetMapping
    @RequestMapping("recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findRecipeById(Long.valueOf(id)));
        return "recipe/recipeForm";
    }

    @PostMapping
    @RequestMapping("recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);
        return "redirect:/recipe/" + savedCommand.getId() + "/show";
    }
    @GetMapping
    @RequestMapping("recipe/{id}/delete")
    public String deleteRecipe(@PathVariable String id) {
       recipeService.deleteById(Long.valueOf(id));
        return "redirect:/";
    }
}

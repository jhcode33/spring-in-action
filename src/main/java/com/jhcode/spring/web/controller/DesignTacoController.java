package com.jhcode.spring.web.controller;

import com.jhcode.spring.domain.Ingredient;
import com.jhcode.spring.domain.Order;
import com.jhcode.spring.domain.Taco;
import com.jhcode.spring.repository.IngredientRepository;
import com.jhcode.spring.repository.TacoRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import static com.jhcode.spring.domain.Ingredient.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private final IngredientRepository ingredientRepo;
    private final TacoRepository tacoRepository;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepo, TacoRepository tacoRepository){
        this.ingredientRepo = ingredientRepo;
        this.tacoRepository = tacoRepository;
    }

    @GetMapping
    public String showDesignForm(Model model){
        List<Ingredient> ingredients = new ArrayList<>();
        /*  // Iterable 인터페이스의 메소드
            default void forEach(Consumer<? super T> action) {
                Objects.requireNonNull(action);
                    for (T t : this) {      // findAll() 메소드로 반환 받은 Iterable<Ingredient> 객체
                        action.accept(t);   // i -> ingredients.add(i)로 정의한 메소드. List 안에 객체를 넣음
                    }
                }
         */

        // consumer의 accept 메소드 구현
        ingredientRepo.findAll().forEach(ingredient -> ingredients.add(ingredient));

        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }

        model.addAttribute("taco", new Taco());

        return "design";
    }

    private Object filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter((pred) -> pred.getType().equals((type))) // Predicate의 test 메소드 구현
                .collect(Collectors.toList());
    }

    @ModelAttribute(name = "order")
    public Order order(){
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco(){
        return new Taco();
    }

    @PostMapping
    public String processDesign(@Valid Taco design, Errors errors, @ModelAttribute Order order){
        if (errors.hasErrors()){
            return "design";
        }

        Taco saved = tacoRepository.save(design);
        order.addDesign(saved);

        return "redirect:/orders/current";
    }


}

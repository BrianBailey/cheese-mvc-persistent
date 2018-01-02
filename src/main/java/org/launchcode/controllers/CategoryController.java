package org.launchcode.controllers;

import org.launchcode.models.Category;
import org.launchcode.models.data.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

// this was created
@Controller
@RequestMapping(value ="category")
public class CategoryController {

    //  not sure why this wasnt pushed properly for final
    // autowired was added part of dependency injection
    @Autowired
    private CategoryDao categoryDao;

    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("categories", categoryDao.findAll());
        model.addAttribute("title", "My Categories");


        return "category/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddForm(Model model) {
        model.addAttribute("title", "Add Category");
        // this passes into view with the key "category"
        model.addAttribute(new Category());

        return "category/add";
    }

    // this was created
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddForm(Model model,
                                 @ModelAttribute @Valid Category category,
                                 Errors errors) {

        if(errors.hasErrors()){
            return "category/add";
        }

        categoryDao.save(category);
        return ("redirect:");

    }


}
package org.perscholas.service;

import org.perscholas.dao.ICategoryRepo;
import org.perscholas.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// makes this class as service component
@Service
public class CategoryService {

    @Autowired
    ICategoryRepo categoryRepo;


    // methods retrieves list of category
    public List<Category> getAllCategory(){
        return categoryRepo.findAll();
    }

    // methods saves category, takes category objects and saves.
    public void addCategory(Category category){
        categoryRepo.save(category);

    }
    // delete book category by id
    public void deleteCategoryById(int id){
        categoryRepo.deleteById(id);

    }
    // returns optional type, because category id admin looks for may or may not exist.
    public Optional<Category> getCategoryById(int id){
        return categoryRepo.findById(id);
    }


}

package mx.edu.utez.firstapp.controllers.Category;


import mx.edu.utez.firstapp.controllers.Category.dtos.CategoryDTO;
import mx.edu.utez.firstapp.controllers.Person.dtos.PersonDTO;
import mx.edu.utez.firstapp.models.Person.Person;
import mx.edu.utez.firstapp.models.category.Category;
import mx.edu.utez.firstapp.services.Category.CategoryServices;
import mx.edu.utez.firstapp.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api-firstapp/category")
@CrossOrigin(origins = {"*"})
public class CategoryController {
    @Autowired
    private CategoryServices service;

    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<Category>>> getAll() {
        return new ResponseEntity<>(
                this.service.getAll(),
                HttpStatus.OK
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Category>> getOne(@PathVariable("id") Long id) {
        return new ResponseEntity<>(
                this.service.getOne(id),
                HttpStatus.OK
        );
    }
    @PostMapping("/")
    public ResponseEntity<CustomResponse<Category>> saveCategory(@RequestBody CategoryDTO category, @Valid BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(
                    null,
                    HttpStatus.BAD_REQUEST
            );
        }
        return new ResponseEntity<>(
                this.service.insert(category.castToCategory()),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/")
    public ResponseEntity<CustomResponse<Category>> updateCategory(@RequestBody CategoryDTO category, @Valid BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(
                    null,
                    HttpStatus.BAD_REQUEST
            );
        }
        return new ResponseEntity<>(
                this.service.update(category.castToCategory()),
                HttpStatus.CREATED
        );
    }

    @PatchMapping("/")
    public ResponseEntity<CustomResponse<Integer>> enableOrDisable(
            @RequestBody CategoryDTO categoryDto) {
        return new ResponseEntity<>(
                this.service.changeStatus(categoryDto.changeStatus()),
                HttpStatus.OK
        );
    }
}

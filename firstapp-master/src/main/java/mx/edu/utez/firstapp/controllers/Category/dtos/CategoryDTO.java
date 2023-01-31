package mx.edu.utez.firstapp.controllers.Category.dtos;

import lombok.*;
import mx.edu.utez.firstapp.models.category.Category;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CategoryDTO {

    private Long id;
    @NonNull
    @NotBlank
    @Length(min=1, max = 150)
    private String name;
    private Boolean status;

    public Category castToCategory(){
        return new Category(
                getId(),
                getName(),
                getStatus(),
                null
        );
    }

    public Category changeStatus(){
        return new Category(
                getId(),
                null,
                getStatus(),
                null
        );
    }

}

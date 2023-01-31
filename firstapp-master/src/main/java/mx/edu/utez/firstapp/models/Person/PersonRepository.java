package mx.edu.utez.firstapp.models.Person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

//este es un dao completo, ya vienen integrados sus metodos
@Repository
public interface PersonRepository  extends JpaRepository<Person, Long> { //reciben dos parametros, la clase y el id

    boolean findById(long id);
    Optional<Person> findByCurp(String curp);
    List<Person> findAllByGenero(String genero);
    @Modifying
    @Query(
            value="UPDATE people SET status= :status WHERE id=:id",
            nativeQuery = true
    )
    int updateStatusById(
            @Param("status") Boolean status,
            @Param("id") Long id);
     // @Query(value = "SELEC*FROM PEOPLE", nativeQuery = true )
 //   Person buscarTodos();

}

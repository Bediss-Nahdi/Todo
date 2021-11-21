package fr.bediss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.bediss.entities.Todo;


@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {

}

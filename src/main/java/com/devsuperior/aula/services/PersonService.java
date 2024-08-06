package com.devsuperior.aula.services;
import com.devsuperior.aula.dto.PersonDTO;
import com.devsuperior.aula.dto.PersonDepartmentDTO;
import com.devsuperior.aula.entities.Department;
import com.devsuperior.aula.entities.Person;
import com.devsuperior.aula.repositories.DepartmentRepository;
import com.devsuperior.aula.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Transactional
    public PersonDepartmentDTO insert (PersonDepartmentDTO dto) {
        //create a entity person in database for received dto
        Person entity = new Person();
        entity.setName(dto.getName());
        entity.setSalary(dto.getSalary());
        Department dept = departmentRepository.getReferenceById(dto.getDepartment().getId());
        //create entity department because is an entity associated
        //this command go create a department in database
        // Department dept = new Department();
        // dept.setId(dto.getDepartment().getId());

        //go to associate entity with dept
        entity.setDepartment(dept);

        //go to save on database
        entity = repository.save(entity);

        return new PersonDepartmentDTO(entity);
    }

    //overload, methods with the same name but with different parameters
    @Transactional
    public PersonDTO insert (PersonDTO dto) {
        //create a entity person in database for received dto
        Person entity = new Person();
        entity.setName(dto.getName());
        entity.setSalary(dto.getSalary());
        Department dept = departmentRepository.getReferenceById(dto.getDepartmentId());
        //create entity department because is an entity associated
        //this command go create a department in database
        //we can do it this way too
        //Department dept = new Department();
        //dept.setId(dto.getDepartmentId());

        //go to associate entity with dept
        entity.setDepartment(dept);

        //go to save on database
        entity = repository.save(entity);

        return new PersonDTO(entity);
    }
}

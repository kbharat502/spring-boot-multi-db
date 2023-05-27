package example.test.springbootmultidb.contacts.db.repo;

import example.test.springbootmultidb.contacts.db.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}

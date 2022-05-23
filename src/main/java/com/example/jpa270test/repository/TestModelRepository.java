package com.example.jpa270test.repository;

import com.example.jpa270test.model.TestModel;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.transaction.annotation.Transactional;

public interface TestModelRepository extends JpaRepository<TestModel, Long> {

  @Modifying
  @Transactional
  @Query(nativeQuery = true,
  value = """
          create function countByUuid(one uuid) \s
          returns int \s
          language plpgsql \s
          as \s
          $$ \s
          declare \s
           c integer; \s
          begin \s
             select count(*)  \s
             into c \s
             from test_model \s
             where first = one; \s
             return c; \s
          end; \s
          $$;\s
      """)
  void createFuncionWithOneArgument();

  @Procedure("countByUuid")
  void countUuid(UUID one);

}

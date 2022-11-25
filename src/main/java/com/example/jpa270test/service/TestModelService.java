package com.example.jpa270test.service;

import com.example.jpa270test.model.TestModel;
import com.example.jpa270test.repository.TestModelRepository;
import java.util.UUID;
import java.util.logging.Logger;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

@Service
@Lazy(false)
public class TestModelService {

  private final TestModelRepository repository;
  private final PlatformTransactionManager txManager;

  @Autowired
  public TestModelService(TestModelRepository repository,
      @Qualifier("transactionManager") PlatformTransactionManager txManager) {
    this.repository = repository;
    this.txManager = txManager;
  }

  @PostConstruct
  void init() {
    repository.createFuncionWithOneArgument();
    TestModel model = new TestModel();
    model.setFirst(UUID.randomUUID());
    repository.saveAndFlush(model);
    TransactionTemplate tmpl = new TransactionTemplate(txManager);
    var logger = Logger.getLogger(TestModelService.class.getName());
    tmpl.execute(new TransactionCallbackWithoutResult() {
      @Override
      protected void doInTransactionWithoutResult(TransactionStatus status) {
        logger.info("Calling procedure with null UUID");
        repository.countUuid(null);
      }
    });
    logger.info("It worked.");
  }
}

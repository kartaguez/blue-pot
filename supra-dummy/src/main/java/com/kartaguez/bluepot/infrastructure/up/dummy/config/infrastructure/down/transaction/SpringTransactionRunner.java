package com.kartaguez.bluepot.infrastructure.up.dummy.config.infrastructure.down.transaction;

import java.util.function.Supplier;

import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import com.kartaguez.bluepot.application.down.transaction.TransactionRunner;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SpringTransactionRunner implements TransactionRunner {

    private PlatformTransactionManager tm;

    @Override
    public <T> T inTransaction(Supplier<T> work) {
        if (null != this.tm) {
            return new TransactionTemplate(tm).execute(status -> work.get());
        } else {
            return null;
        }
    }

}

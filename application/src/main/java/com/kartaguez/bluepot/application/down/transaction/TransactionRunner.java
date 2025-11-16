package com.kartaguez.bluepot.application.down.transaction;

import java.util.function.Supplier;

public interface TransactionRunner {

    <T> T inTransaction(Supplier<T> work);

}

package com.bcp.challenge.exchangerate.repository;

import com.bcp.challenge.exchangerate.model.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Integer> {

    @Query("select c.exchangerate from Currency c where c.origincurrency = :origincurrency" +
            " and c.destinationcurrency = :destinationcurrency")
    Optional<Float> findExchangeRate(@Param("origincurrency") String originCurrency,
                                   @Param("destinationcurrency") String destinationCurrency);


    @Transactional
    @Modifying
    @Query("update Currency c set c.exchangerate = :exchangerate" +
            " where c.origincurrency = :origincurrency" +
            " and c.destinationcurrency = :destinationcurrency")
    Integer updateExchangeRate(@Param("exchangerate") Float exchangeRate,
                               @Param("origincurrency") String originCurrency,
                               @Param("destinationcurrency") String destinationCurrency);


    @Transactional
    @Modifying
    @Query(value = "insert into Currency (origincurrency, destinationcurrency, exchangerate) " +
            "values ( :origincurrency , :destinationcurrency , :exchangerate )", nativeQuery = true)
    Integer insertExchangeRate(@Param("origincurrency") String originCurrency,
                               @Param("destinationcurrency") String destinationCurrency,
                               @Param("exchangerate") Float exchangeRate);

}

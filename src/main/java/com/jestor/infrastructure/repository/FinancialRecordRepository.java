package com.jestor.infrastructure.repository;

import com.jestor.domain.model.financialrecord.FinancialRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Repository
@RedisHash
public interface FinancialRecordRepository extends JpaRepository<FinancialRecord, Long> {

    @Query(
            value = "SELECT fc.id, fc.value, fc.description, fc.date, fc.category_id, fc.user_id, c.type FROM financial_record fc LEFT JOIN category c ON fc.category_id = c.id LEFT JOIN _user u ON fc.user_id = u.id WHERE c.type = :type AND u.email = :email AND MONTH(fc.date) = :month AND YEAR(fc.date) = :year ORDER BY fc.date DESC, fc.id DESC;",
            nativeQuery = true
    )
    List<FinancialRecord> getFinancialRecords(@Param("email") String email, @Param("type") String type, @Param("month") Integer month, @Param("year") Integer year);

    @Query(
            value = "SELECT fc.id, fc.value, fc.description, fc.date, fc.category_id, fc.user_id, c.type FROM financial_record fc LEFT JOIN category c ON fc.category_id = c.id LEFT JOIN _user u ON fc.user_id = u.id WHERE u.email = :email ORDER BY fc.date DESC, fc.id DESC;",
            nativeQuery = true
    )
    List<FinancialRecord> getAllFinancialRecords(@Param("email") String email);

    @Transactional
    @Modifying
    @Query(
            value = "INSERT INTO financial_record (value, description, date, category_id, user_id) VALUES (:value, :description, STR_TO_DATE(:date, '%d/%m/%Y'), :categoryId, (SELECT u.id FROM _user u WHERE u.email = :email))",
            nativeQuery = true
    )
    void createFinancialRecord(
            @Param("value") BigDecimal value,
            @Param("description") String description,
            @Param("date") String date,
            @Param("categoryId") Integer categoryId,
            @Param("email") String email
    );
}
package com.jestor.infrastructure.repository;

import com.jestor.domain.model.FinancialRecord;
import com.jestor.domain.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FinancialRecordRepository extends JpaRepository<FinancialRecord, Long> {

    List<FinancialRecord> findAllByUser(User user);

    @Query(
            name = "FinancialRecord.findAllEntradasByUser",
            value = "SELECT fc.id, fc.value, fc.description, fc.date, fc.category_id, fc.user_id, c.type FROM financial_record fc LEFT JOIN category c ON fc.category_id = c.id LEFT JOIN _user u ON fc.user_id = u.id WHERE c.type = :type AND u.email = :email ORDER BY fc.date DESC, fc.id DESC",
            nativeQuery = true
    )
    List<FinancialRecord> getFinancialRecords(@Param("email") String email,
                                              @Param("type") String type);
}
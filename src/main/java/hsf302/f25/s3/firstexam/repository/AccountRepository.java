package hsf302.f25.s3.firstexam.repository;

import hsf302.f25.s3.firstexam.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {
    Account findByPhone(String phone);
}

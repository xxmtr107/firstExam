package hsf302.f25.s3.firstexam.service;

import hsf302.f25.s3.firstexam.entity.Account;
import hsf302.f25.s3.firstexam.repository.AccountRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public void deleteById(Integer id) {
        accountRepository.deleteById(id);
    }

    public Account findById(Integer id) {
        return accountRepository.findById(id).orElse(null);
    }

    public void save(Account account) {
        accountRepository.save(account);
    }


}

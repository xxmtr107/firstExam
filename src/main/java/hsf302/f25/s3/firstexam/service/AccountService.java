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

    public Account authenticate(String phone,String password){
        Account account = accountRepository.findByPhone(phone);
        if(account !=null && account.getPassword().equals(password)){
            return account;
        }
        return null;
    }

    public boolean isAdminOrStaff(Account account){
        return account != null && (account.getRoleId().equals(1) || account.getRoleId().equals(2));
    }

}

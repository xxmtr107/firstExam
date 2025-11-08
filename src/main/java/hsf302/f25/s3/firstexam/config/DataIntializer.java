package hsf302.f25.s3.firstexam.config;

import hsf302.f25.s3.firstexam.entity.Account;
import hsf302.f25.s3.firstexam.entity.Category;
import hsf302.f25.s3.firstexam.entity.Product;
import hsf302.f25.s3.firstexam.repository.AccountRepository;
import hsf302.f25.s3.firstexam.repository.CategoryRepository;
import hsf302.f25.s3.firstexam.repository.ProductRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DataIntializer implements CommandLineRunner {
    AccountRepository accountRepository;
    CategoryRepository categoryRepository;
    ProductRepository productRepository;
    @Override
    public void run(String... args) throws Exception {
        // --- Accounts ---
        accountRepository.save(Account.builder()
                .phone("0905111111")
                .password("@1")
                .roleId(1)
                .build());
        accountRepository.save(Account.builder()
                .phone("0905222222")
                .password("@1")
                .roleId(2)
                .build());
        accountRepository.save(Account.builder()
                .phone("0905333333")
                .password("@1")
                .roleId(3)
                .build());
        // --- Categories ---
        categoryRepository.save(Category.builder()
                .cateName("HeadPhone")
                .status("active")
                .build());
        categoryRepository.save(Category.builder()
                .cateName("Cameras")
                .status("active")
                .build());
        categoryRepository.save(Category.builder()
                .cateName("TVs")
                .status("active")
                .build());
        // --- Products ---
        productRepository.save(Product.builder()
                .productName("Alpha 1 II - Full-frame Mirrorless")
                .price(6000)
                .stock(3)
                .cateId(categoryRepository.findById(2).orElse(null))
                .build());
        productRepository.save(Product.builder()
                .productName("Alpha 7C II – Full-frame")
                .price(2000)
                .stock(5)
                .cateId(categoryRepository.findById(2).orElse(null))
                .build());
        productRepository.save(Product.builder()
                .productName("BRAVIA 8 OLED 4K HDR TV")
                .price(2500)
                .stock(10)
                .cateId(categoryRepository.findById(3).orElse(null))
                .build());
        productRepository.save(Product.builder()
                .productName("LinkBuds Fit Truly Wireless Noise Canceling")
                .price(180)
                .stock(15)
                .cateId(categoryRepository.findById(1).orElse(null))
                .build());
    }
}

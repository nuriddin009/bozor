package uz.market.bozor.component;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uz.market.bozor.entity.User;
import uz.market.bozor.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {


    private final UserRepository userRepository;


    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            userRepository.save(User.builder()
                    .email("nuriddin@gmail.com")
                    .firstname("Nuriddin")
                    .lastname("Inoyatov")
                    .phoneNumber("+998999686653")
                    .password("p2ssword")
                    .build());
        }

    }
}

package uz.market.bozor.component;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.market.bozor.entity.Role;
import uz.market.bozor.entity.User;
import uz.market.bozor.entity.constants.Language;
import uz.market.bozor.entity.constants.RoleName;
import uz.market.bozor.entity.constants.Status;
import uz.market.bozor.repository.RoleRepository;
import uz.market.bozor.repository.UserRepository;

import java.util.HashSet;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;


    @Override
    public void run(String... args) {

        if (roleRepository.count() == 0) {
            roleRepository.saveAll(List.of(
                    new Role(RoleName.ROLE_SUPER_ADMIN),
                    new Role(RoleName.ROLE_STORE),
                    new Role(RoleName.ROLE_USER)
            ));
        }

        if (userRepository.count() == 0) {
            userRepository.save(User.builder()
                    .email("inoyatovnuriddin007@gmail.com")
                    .firstname("Nuriddin")
                    .lastname("Inoyatov")
                    .phoneNumber("+998999686653")
                    .password(passwordEncoder.encode("root123"))
                    .status(Status.ACTIVE)
                    .roles(new HashSet<>(roleRepository.findAll()))
                    .language(Language.uz)
                    .build());
        }


    }
}

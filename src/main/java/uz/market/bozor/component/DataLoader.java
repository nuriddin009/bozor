package uz.market.bozor.component;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.market.bozor.entity.*;
import uz.market.bozor.entity.constants.DAY;
import uz.market.bozor.entity.constants.PrivilegeName;
import uz.market.bozor.entity.constants.RoleName;
import uz.market.bozor.entity.constants.Status;
import uz.market.bozor.repository.*;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;
    private final PrivilegeRepository privilegeRepository;
    private final TimingRepository timingRepository;
    private final StoreRepository storeRepository;

    @Override
    public void run(String... args) {

        if (userRepository.count() == 0) {

            List<Role> roles = roleRepository.saveAll(
                    List.of(
                            new Role(RoleName.ROLE_STORE),
                            new Role(RoleName.ROLE_SUPER_ADMIN)
                    )
            );
            List<Privilege> privileges = privilegeRepository.saveAll(
                    List.of(
                            new Privilege(PrivilegeName.HOME),
                            new Privilege(PrivilegeName.ACCOUNTS),
                            new Privilege(PrivilegeName.ORDERS),
                            new Privilege(PrivilegeName.STOCKS)
                    )
            );

            userRepository.save(User.builder()
                    .email("nuriddin@gmail.com")
                    .firstname("Nuriddin")
                    .lastname("Inoyatov")
                    .phoneNumber("+998999686653")
                    .status(Status.ACTIVE)
                    .password(passwordEncoder.encode("password"))
                    .roles(new HashSet<>(roles))
                    .privileges(new HashSet<>(privileges))
                    .build());
        }

        if (timingRepository.count() == 0) {
            Store store = storeRepository.save(Store.builder()
//                    .bankAccount(860_019_015L)
                    .bankAccountDetails("Nuriddin Inoyatov")
                    .name("Lux brand")
                    .email("luxbrand@gmail.com")
                    .build());
            for (DAY day : DAY.values()) {
                timingRepository.save(Timing.builder()
                        .day(day)
                        .openingTime(LocalTime.of(9, 0, 0))
                        .closingTime(LocalTime.of(10, 0, 0))
                        .store(store)
                        .build());
            }
        }




    }
}

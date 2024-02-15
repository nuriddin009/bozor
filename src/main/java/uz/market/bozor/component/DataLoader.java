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
    private final RegionRepository regionRepository;
    private final DistrictRepository districtRepository;
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

        if (regionRepository.count() == 0) {

            regionRepository.saveAll(
                    List.of(
                            new Region(1, "Qoraqalpog‘iston Respublikasi", "Қорақалпоғистон Республикаси", "Республика Каракалпакстан"),
                            new Region(2, "Andijon viloyati", "Андижон вилояти", "Андижанская область"),
                            new Region(3, "Buxoro viloyati", "Бухоро вилояти", "Бухарская область"),
                            new Region(4, "Jizzax viloyati", "Жиззах вилояти", "Джизакская область"),
                            new Region(5, "Qashqadaryo viloyati", "Қашқадарё вилояти", "Кашкадарьинская область"),
                            new Region(6, "Navoiy viloyati", "Навоий вилояти", "Навоийская область"),
                            new Region(7, "Namangan viloyati", "Наманган вилояти", "Наманганская область"),
                            new Region(8, "Samarqand viloyati", "Самарқанд вилояти", "Самаркандская область"),
                            new Region(9, "Surxandaryo viloyati", "Сурхандарё вилояти", "Сурхандарьинская область"),
                            new Region(10, "Sirdaryo viloyati", "Сирдарё вилояти", "Сырдарьинская область"),
                            new Region(11, "Toshkent viloyati", "Тошкент вилояти", "Ташкентская область"),
                            new Region(12, "Farg‘ona viloyati", "Фарғона вилояти", "Ферганская область"),
                            new Region(13, "Xorazm viloyati", "Хоразм вилояти", "Хорезмская область"),
                            new Region(14, "Toshkent shahri", "Тошкент шаҳри", "Город Ташкент")
                    )
            );
        }

        if (districtRepository.count() == 0) {
            Region one = regionRepository.getReferenceById(1);
            Region two = regionRepository.getReferenceById(2);
            Region three = regionRepository.getReferenceById(3);
            Region four = regionRepository.getReferenceById(4);
            Region five = regionRepository.getReferenceById(5);
            Region six = regionRepository.getReferenceById(6);
            Region seven = regionRepository.getReferenceById(7);
            Region eight = regionRepository.getReferenceById(8);
            Region nine = regionRepository.getReferenceById(9);
            Region ten = regionRepository.getReferenceById(10);
            Region eleven = regionRepository.getReferenceById(11);
            Region twelve = regionRepository.getReferenceById(12);
            Region thirteen = regionRepository.getReferenceById(13);
            Region fourteen = regionRepository.getReferenceById(14);


            districtRepository.saveAll(
                    List.of(
                            new District(15, one, "Amudaryo tumani", "Амударё тумани", "Амударьинский район"),
                            new District(16, one, "Beruniy tumani", "Беруний тумани", "Берунийский район"),
                            new District(17, one, "Kegayli tumani", "Кегайли тумани", "Кегейлийский район"),
                            new District(18, one, "Qonliko‘l tumani", "Қонликўл тумани", "Канлыкульский район"),
                            new District(19, one, "Qorao‘zak tumani", "Қораўзак тумани", "Караузякский район"),
                            new District(20, one, "Qo‘ng‘irot tumani", "Қўнғирот тумани", "Кунградский район"),
                            new District(21, one, "Mo‘ynoq tumani", "Мўйноқ тумани", "Муйнакский район"),
                            new District(22, one, "Nukus tumani", "Нукус тумани", "Нукусский район"),
                            new District(23, one, "Nukus shahri", "Нукус шаҳри", "город Нукус"),
                            new District(24, one, "Taxtako‘pir tumani", "Тахтакўпир тумани", "Тахтакупырский район"),
                            new District(25, one, "To‘rtko‘l tumani", "Тўрткўл тумани", "Турткульский район"),
                            new District(26, one, "Xo‘jayli tumani", "Хўжайли тумани", "Ходжейлийский район"),
                            new District(27, one, "Chimboy tumani", "Чимбой тумани", "Чимбайский район"),
                            new District(28, one, "Shumanay tumani", "Шуманай тумани", "Шуманайский район"),
                            new District(29, one, "Ellikqal‘a tumani", "Элликқалъа тумани", "Элликкалинский район"),
                            new District(30, two, "Andijon shahri", "Андижон шаҳри", "город Андижан"),
                            new District(31, two, "Andijon tumani", "Андижон тумани", "Андижанский район"),
                            new District(32, two, "Asaka tumani", "Асака тумани", "Асакинский район"),
                            new District(33, two, "Baliqchi tumani", "Балиқчи тумани", "Балыкчинский район"),
                            new District(34, two, "Buloqboshi tumani", "Булоқбоши тумани", "Булакбашинский район"),
                            new District(35, two, "Bo‘z tumani", "Бўз тумани", "Бозский район"),
                            new District(36, two, "Jalaquduq tumani", "Жалақудуқ тумани", "Джалакудукский район"),
                            new District(37, two, "Izbosgan tumani", "Избосган тумани", "Избасканский район"),
                            new District(38, two, "Qorasuv shahri", "Қорасув шаҳри", "город Карасув"),
                            new District(39, two, "Qo‘rg‘ontepa tumani", "Қўрғонтепа тумани", "Кургантепинский район"),
                            new District(40, two, "Marhamat tumani", "Марҳамат тумани", "Мархаматский район"),
                            new District(41, two, "Oltinko‘l tumani", "Олтинкўл тумани", "Алтынкульский район"),
                            new District(42, two, "Paxtaobod tumani", "Пахтаобод тумани", "Пахтаабадский район"),
                            new District(43, two, "Ulug‘nor tumani", "Улуғнор тумани", "Улугнорский район"),
                            new District(44, two, "Xonabod tumani", "Хонабод тумани", "город Ханабад"),
                            new District(45, two, "Xo‘jaobod tumani", "Хўжаобод тумани", "Ходжаабадский район"),
                            new District(46, two, "Shahrixon tumani", "Шаҳрихон тумани", "Шахриханский район"),
                            new District(47, three, "Buxoro shahri", "Бухоро шаҳри", "город Бухара"),
                            new District(48, three, "Buxoro tumani", "Бухоро тумани", "Бухарский район"),
                            new District(49, three, "Vobkent tumani", "Вобкент тумани", "Вабкентский район"),
                            new District(50, three, "G‘ijduvon tumani", "Ғиждувон тумани", "Гиждуванский район"),
                            new District(51, three, "Jondor tumani", "Жондор тумани", "Жондорский район"),
                            new District(52, three, "Kogon tumani", "Когон тумани", "Каганский район"),
                            new District(53, three, "Kogon shahri", "Когон шаҳри", "город Каган"),
                            new District(54, three, "Qorako‘l tumani", "Қоракўл тумани", "Каракульский район"),
                            new District(55, three, "Qorovulbozor tumani", "Қоровулбозор тумани", "Караулбазарский район"),
                            new District(56, three, "Olot tumani", "Олот тумани", "Алатский район"),
                            new District(57, three, "Peshku tumani", "Пешку тумани", "Пешкунский район"),
                            new District(58, three, "Romitan tumani", "Ромитан тумани", "Ромитанский район"),
                            new District(59, three, "Shofirkon tumani", "Шофиркон тумани", "Шафирканский район"),
                            new District(60, four, "Arnasoy tumani", "Арнасой тумани", "Арнасайский район"),
                            new District(61, four, "Baxmal tumani", "Бахмал тумани", "Бахмальский район"),
                            new District(62, four, "G‘allaorol tumani", "Ғаллаорол тумани", "Галляаральский район"),
                            new District(63, four, "Do‘stlik tumani", "Дўстлик тумани", "Дустликский район"),
                            new District(64, four, "Sh.Rashidov tumani", "Ш.Рашидов тумани", "Шараф-Рашидовский район"),
                            new District(65, four, "Jizzax shahri", "Жиззах шаҳри", "город Джизак"),
                            new District(66, four, "Zarbdor tumani", "Зарбдор тумани", "Зарбдарский район"),
                            new District(67, four, "Zafarobod tumani", "Зафаробод тумани", "Зафарабадский район"),
                            new District(68, four, "Zomin tumani", "Зомин тумани", "Зааминский район"),
                            new District(69, four, "Mirzacho‘l tumani", "Мирзачўл тумани", "Мирзачульский район"),
                            new District(70, four, "Paxtakor tumani", "Пахтакор тумани", "Пахтакорский район"),
                            new District(71, four, "Forish tumani", "Фориш тумани", "Фаришский район"),
                            new District(72, four, "Yangiobod tumani", "Янгиобод тумани", "Янгиабадский район"),
                            new District(73, five, "G‘uzor tumani", "Ғузор тумани", "Гузарский район"),
                            new District(74, five, "Dehqonobod tumani", "Деҳқонобод тумани", "Дехканабадский район"),
                            new District(75, five, "Qamashi tumani", "Қамаши тумани", "Камашинский район"),
                            new District(76, five, "Qarshi tumani", "Қарши тумани", "Каршинский район"),
                            new District(77, five, "Qarshi shahri", "Қарши шаҳри", "город Карши"),
                            new District(78, five, "Kasbi tumani", "Касби тумани", "Касбийский район"),
                            new District(79, five, "Kitob tumani", "Китоб тумани", "Китабский район"),
                            new District(80, five, "Koson tumani", "Косон тумани", "Касанский район"),
                            new District(81, five, "Mirishkor tumani", "Миришкор тумани", "Миришкорский район"),
                            new District(82, five, "Muborak tumani", "Муборак тумани", "Мубарекский район"),
                            new District(83, five, "Nishon tumani", "Нишон тумани", "Нишанский район"),
                            new District(84, five, "Chiroqchi tumani", "Чироқчи тумани", "Чиракчинский район"),
                            new District(85, five, "Shahrisabz tumani", "Шаҳрисабз тумани", "Шахрисабзский район"),
                            new District(86, five, "Yakkabog‘ tumani", "Яккабоғ тумани", "Яккабагский район"),
                            new District(87, six, "Zarafshon shahri", "Зарафшон шаҳри", "город Зарафшан"),
                            new District(88, six, "Karmana tumani", "Кармана тумани", "Карманинский район"),
                            new District(89, six, "Qiziltepa tumani", "Қизилтепа тумани", "Кызылтепинский район"),
                            new District(90, six, "Konimex tumani", "Конимех тумани", "Канимехский район"),
                            new District(91, six, "Navbahor tumani", "Навбаҳор тумани", "Навбахорский район"),
                            new District(92, six, "Navoiy shahri", "Навоий шаҳри", "город Навои"),
                            new District(93, six, "Nurota tumani", "Нурота тумани", "Нуратинский район"),
                            new District(94, six, "Tomdi tumani", "Томди тумани", "Тамдынский район"),
                            new District(95, six, "Uchquduq tumani", "Учқудуқ тумани", "Учкудукский район"),
                            new District(96, six, "Xatirchi tumani", "Хатирчи тумани", "Хатырчинский район"),
                            new District(97, seven, "Kosonsoy tumani", "Косонсой тумани", "Касансайский район"),
                            new District(98, seven, "Mingbuloq tumani", "Мингбулоқ тумани", "Мингбулакский район"),
                            new District(99, seven, "Namangan tumani", "Наманган тумани", "Наманганский район"),
                            new District(100, seven, "Namangan shahri", "Наманган шаҳри", "город Наманган"),
                            new District(101, seven, "Norin tumani", "Норин тумани", "Нарынский район"),
                            new District(102, seven, "Pop tumani", "Поп тумани", "Папский район"),
                            new District(103, seven, "To‘raqo‘rg‘on tumani", "Тўрақўрғон тумани", "Туракурганский район"),
                            new District(104, seven, "Uychi tumani", "Уйчи тумани", "Уйчинский район"),
                            new District(105, seven, "Uchqo‘rg‘on tumani", "Учқўрғон тумани", "Учкурганский район"),
                            new District(106, seven, "Chortoq tumani", "Чортоқ тумани", "Чартакский район"),
                            new District(107, seven, "Chust tumani", "Чуст тумани", "Чустский район"),
                            new District(108, seven, "Yangiqo‘rg‘on tumani", "Янгиқўрғон тумани", "Янгикурганский район"),
                            new District(109, eight, "Bulung‘ur tumani", "Булунғур тумани", "Булунгурский район"),
                            new District(110, eight, "Jomboy tumani", "Жомбой тумани", "Джамбайский район"),
                            new District(111, eight, "Ishtixon tumani", "Иштихон тумани", "Иштыханский район"),
                            new District(112, eight, "Kattaqo‘rg‘on tumani", "Каттақўрғон тумани", "Каттакурганский район"),
                            new District(113, eight, "Kattaqo‘rg‘on shahri", "Каттақўрғон шаҳри", "город Каттакурган"),
                            new District(114, eight, "Qo‘shrabot tumani", "Қўшработ тумани", "Кошрабадский район"),
                            new District(115, eight, "Narpay tumani", "Нарпай тумани", "Нарпайский район"),
                            new District(116, eight, "Nurabod tumani", "Нурабод тумани", "Нурабадский район"),
                            new District(117, eight, "Oqdaryo tumani", "Оқдарё тумани", "Акдарьинский район"),
                            new District(118, eight, "Payariq tumani", "Паяриқ тумани", "Пайарыкский район"),
                            new District(119, eight, "Pastarg‘om tumani", "Пастарғом тумани", "Пастдаргомский район"),
                            new District(120, eight, "Paxtachi tumani", "Пахтачи тумани", "Пахтачийский район"),
                            new District(121, eight, "Samarqand tumani", "Самарқанд тумани", "Самаркандский район"),
                            new District(122, eight, "Samarqand shahri", "Самарқанд шаҳри", "город Самарканд"),
                            new District(123, eight, "Toyloq tumani", "Тойлоқ тумани", "Тайлакский район"),
                            new District(124, eight, "Urgut tumani", "Ургут тумани", "Ургутский район"),
                            new District(125, nine, "Angor tumani", "Ангор тумани", "Ангорский район"),
                            new District(126, nine, "Boysun tumani", "Бойсун тумани", "Байсунский район"),
                            new District(127, nine, "Denov tumani", "Денов тумани", "Денауский район"),
                            new District(128, nine, "Jarqo‘rg‘on tumani", "Жарқўрғон тумани", "Джаркурганский район"),
                            new District(129, nine, "Qiziriq tumani", "Қизириқ тумани", "Кизирикский район"),
                            new District(130, nine, "Qo‘mqo‘rg‘on tumani", "Қўмқўрғон тумани", "Кумкурганский район"),
                            new District(131, nine, "Muzrabot tumani", "Музработ тумани", "Музрабадский район"),
                            new District(132, nine, "Oltinsoy tumani", "Олтинсой тумани", "Алтынсайский район"),
                            new District(133, nine, "Sariosiyo tumani", "Сариосиё тумани", "Сариасийский район"),
                            new District(134, nine, "Termiz tumani", "Термиз тумани", "Термезский район"),
                            new District(135, nine, "Termiz shahri", "Термиз шаҳри", "город Термез"),
                            new District(136, nine, "Uzun tumani", "Узун тумани", "Узунский район"),
                            new District(137, nine, "Sherobod tumani", "Шеробод тумани", "Шерабадский район"),
                            new District(138, nine, "Sho‘rchi tumani", "Шўрчи тумани", "Шурчинский район"),
                            new District(139, ten, "Boyovut tumani", "Боёвут тумани", "Баяутский район"),
                            new District(140, ten, "Guliston tumani", "Гулистон тумани", "Гулистанский район"),
                            new District(141, ten, "Guliston shahri", "Гулистон шаҳри", "город Гулистан"),
                            new District(142, ten, "Mirzaobod tumani", "Мирзаобод тумани", "Мирзаабадский район"),
                            new District(143, ten, "Oqoltin tumani", "Оқолтин тумани", "Акалтынский район"),
                            new District(144, ten, "Sayxunobod tumani", "Сайхунобод тумани", "Сайхунабадский район"),
                            new District(145, ten, "Sardoba tumani", "Сардоба тумани", "Сардобский район"),
                            new District(146, ten, "Sirdaryo tumani", "Сирдарё тумани", "Сырдарьинский район"),
                            new District(147, ten, "Xavos tumani", "Хавос тумани", "Хавастский район"),
                            new District(148, ten, "Shirin shahri", "Ширин шаҳри", "город Ширин"),
                            new District(149, ten, "Yangiyer shahri", "Янгиер шаҳри", "город Янгиер"),
                            new District(150, eleven, "Angiren shahri", "Ангирен шаҳри", "город Ангрен"),
                            new District(151, eleven, "Bekabod tumani", "Бекабод тумани", "Бекабадский район"),
                            new District(152, eleven, "Bekabod shahri", "Бекабод шаҳри", "город Бекабад"),
                            new District(153, eleven, "Bo‘ka tumani", "Бўка тумани", "Букинский район"),
                            new District(154, eleven, "Bo‘stonliq tumani", "Бўстонлиқ тумани", "Бостанлыкский район"),
                            new District(155, eleven, "Zangiota tumani", "Зангиота тумани", "Зангиатинский район"),
                            new District(156, eleven, "Qibray tumani", "Қибрай тумани", "Кибрайский район"),
                            new District(157, eleven, "Quyichirchiq tumani", "Қуйичирчиқ тумани", "Куйичирчикский район"),
                            new District(158, eleven, "Oqqo‘rg‘on tumani", "Оққўрғон тумани", "Аккурганский район"),
                            new District(159, eleven, "Olmaliq shahri", "Олмалиқ шаҳри", "город Алмалык"),
                            new District(160, eleven, "Ohangaron tumani", "Оҳангарон тумани", "Ахангаранский район"),
                            new District(161, eleven, "Parkent tumani", "Паркент тумани", "Паркентский район"),
                            new District(162, eleven, "Piskent tumani", "Пискент тумани", "Пскентский район"),
                            new District(163, eleven, "O‘rtachirchiq tumani", "Ўртачирчиқ тумани", "Уртачирчикский район"),
                            new District(164, eleven, "Chinoz tumani", "Чиноз тумани", "Чиназский район"),
                            new District(165, eleven, "Chirchiq shahri", "Чирчиқ шаҳри", "город Чирчик"),
                            new District(166, eleven, "Yuqorichirchiq tumani", "Юқоричирчиқ тумани", "Юкоричирчикский район"),
                            new District(167, eleven, "Yangiyo‘l tumani", "Янгийўл тумани", "Янгиюльский район"),
                            new District(168, twelve, "Beshariq tumani", "Бешариқ тумани", "Бешарыкский район"),
                            new District(169, twelve, "Bog‘dod tumani", "Боғдод тумани", "Багдадский район"),
                            new District(170, twelve, "Buvayda tumani", "Бувайда тумани", "Бувайдинский район"),
                            new District(171, twelve, "Dang‘ara tumani", "Данғара тумани", "Дангаринский район"),
                            new District(172, twelve, "Yozyovon tumani", "Ёзёвон тумани", "Язъяванский район"),
                            new District(173, twelve, "Quva tumani", "Қува тумани", "Кувинский район"),
                            new District(174, twelve, "Quvasoy shahri", "Қувасой шаҳри", "город Кувасай"),
                            new District(175, twelve, "Qo‘qon shahri", "Қўқон шаҳри", "город Коканд"),
                            new District(176, twelve, "Qo‘shtepa tumani", "Қўштепа тумани", "Куштепинский район"),
                            new District(177, twelve, "Marg‘ilon shahri", "Марғилон шаҳри", "город Маргилан"),
                            new District(178, twelve, "Oltiariq tumani", "Олтиариқ тумани", "Алтыарыкский район"),
                            new District(179, twelve, "Rishton tumani", "Риштон тумани", "Риштанский район"),
                            new District(180, twelve, "So‘x tumani", "Сўх тумани", "Сохский район"),
                            new District(181, twelve, "Toshloq tumani", "Тошлоқ тумани", "Ташлакский район"),
                            new District(182, twelve, "Uchko‘prik tumani", "Учкўприк тумани", "Учкуприкский район"),
                            new District(183, twelve, "O‘zbekiston tumani", "Ўзбекистон тумани", "Узбекистанский район"),
                            new District(184, twelve, "Farg‘ona tumani", "Фарғона тумани", "Ферганский район"),
                            new District(185, twelve, "Farg‘ona shahri", "Фарғона шаҳри", "город Фергана"),
                            new District(186, twelve, "Furqat tumani", "Фурқат тумани", "Фуркатский район"),
                            new District(187, thirteen, "Bog‘ot tumani", "Боғот тумани", "Багатский район"),
                            new District(188, thirteen, "Gurlan tumani", "Гурлан тумани", "Гурленский район"),
                            new District(189, thirteen, "Qo‘shko‘pir tumani", "Қўшкўпир тумани", "Кошкупырский район"),
                            new District(190, thirteen, "Urganch tumani", "Урганч тумани", "Ургенчский район"),
                            new District(191, thirteen, "Urganch shahri", "Урганч шаҳри", "город Ургенч"),
                            new District(192, thirteen, "Xiva tumani", "Хива тумани", "Хивинский район"),
                            new District(193, thirteen, "Xazarasp tumani", "Хазарасп тумани", "Хазараспский район"),
                            new District(194, thirteen, "Xonqa tumani", "Хонқа тумани", "Ханкинский район"),
                            new District(195, thirteen, "Shavot tumani", "Шавот тумани", "Шаватский район"),
                            new District(196, thirteen, "Yangiariq tumani", "Янгиариқ тумани", "Янгиарыкский район"),
                            new District(197, thirteen, "Yangibozor tumani", "Янгибозор тумани", "Янгибазарский район"),
                            new District(198, fourteen, "Bektimer tumani", "Бектимер тумани", "Бектемирский район"),
                            new District(199, fourteen, "Mirzo Ulug\"bek tumani", "Мирзо-Улугбекский район", "Мирзо-Улугбекский район"),
                            new District(200, fourteen, "Mirobod tumani", "Миробод тумани", "Мирабадский район"),
                            new District(201, fourteen, "Olmazor tumani", "Олмазор тумани", "Алмазарский район"),
                            new District(202, fourteen, "Sirg\"ali tumani", "Сиргали тумани", "Сергелийский район"),
                            new District(203, fourteen, "Uchtepa tumani", "Учтепа тумани", "Учтепинский район"),
                            new District(204, fourteen, "Yashnobod tumani", "Яшнобод тумани", "Яшнободский район"),
                            new District(205, fourteen, "Chilonzor tumani", "Чилонзор тумани", "Чиланзарский район"),
                            new District(206, fourteen, "Shayxontohur tumani", "Шайхонтоҳур тумани", "Шайхантахурский район"),
                            new District(207, fourteen, "Yunusobod tumani", "Юнусобод тумани", "Юнусабадский район"),
                            new District(208, fourteen, "Yakkasaroy tumani", "Яккасарой тумани", "Яккасарайский район"),
                            new District(209, one, "Taxiatosh shahri", "Тахиатош шаҳри", "Тахиаташский район"),
                            new District(210, two, "Asaka shahri", "Асака шаҳри", "Асакинский район"),
                            new District(211, nine, "Bandixon tumani", "Бандихон тумани", "Бандиханский район"),
                            new District(212, eleven, "Ohangaron shahri", "Оҳангарон шаҳри", "город Ахангаранский"),
                            new District(213, eleven, "Yangiyo‘l shahri", "Янгийўл шаҳри", "город Янгиюль"),
                            new District(215, eleven, "Toshkent tumani", "Тошкент тумани", "Ташкентский район"),
                            new District(216, thirteen, "Xiva shahri", "Хива шаҳри", "город Хива"),
                            new District(217, thirteen, "Do\"stlik shahri", "Дўстлик шаҳри\r\nДўстлик шаҳри", "город Дўстлик\r\nДўстлик"),
                            new District(218, fourteen, "Yangihayot tumani", "Янгиҳаёт тумани", "Янгихаётский район"),
                            new District(219, thirteen, "Tuproqqala tumani", "Тупроққалъа тумани", "Тироккальский район"),
                            new District(220, seven, "Davlatobod tumani", "Давлатобод тумани", "Давлатабадский район \r\n"),
                            new District(221, six, "G‘ozg‘on shahri", "Ғозғон шаҳри", "Город Гозган"),
                            new District(222, one, "Bo‘zatov tumani", "Бўзатов тумани", "Бозатовский район"),
                            new District(224, five, "Shahrisabz shahri", "Шаҳрисабз шаҳри", "Город Шахрисабз"),
                            new District(225, five, "Ko‘kdala tumani", "Кўкдала тумани", "Кукдалинский район")
                    )
            );
        }


    }
}

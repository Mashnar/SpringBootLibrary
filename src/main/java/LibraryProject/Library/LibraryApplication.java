package LibraryProject.Library;

import LibraryProject.Library.DB.CRUD.BooksRepository;
import LibraryProject.Library.Services.UserService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import LibraryProject.Library.DB.*;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableAutoConfiguration
public class LibraryApplication {
@Autowired
	UserService userService;
@Autowired
	BooksRepository booksRepository;
	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}



	@Bean
	InitializingBean sendDatabase() {
		return () -> {
			User user = userService.findUserByEmail("admin@wp.pl");
			if (user == null)
			{
				//dodaje admina
				User user_1 = new User();
				user_1.setLast_name("admin");
				user_1.setFirst_name("admin");
				user_1.setEmail("admin@wp.pl");
				user_1.setPassword("admin");
				userService.saveAdmin(user_1);
				//dodaje ksiazki do bazy
				booksRepository.save( new Books("Metro","Dmitry Glukhovsky","Książka opowiada o ludziach, którzy przeżyli wojnę atomową. Niemal cała akcja rozgrywa się w moskiewskim metrze (największym na świecie schronie przeciwatomowym, mogącym pełnić funkcję podziemnego miasta), na którego stacjach i w przejściach mieszkają ludzie. Dzięki sprawnym działaniom obrony cywilnej sieć metra udało się uchronić przed skażeniem promieniotwórczym – niemal na wszystkich stacjach zamknięto hermetyczne wrota, aktywowano filtry przeciwradiacyjne i oczyszczające wodę. Wszystko wskazuje na to, że w chwili zagłady Moskwy w metrze mogło się schronić około 70 000 osób. Po 20 latach od czasu wojny atomowej, metro zamieszkiwane jest przez 50 000 ludzi. Jedynie połowa stacji jest zaludniona – niektóre zostały porzucone, wiele zostało strawionych przez pożary bądź odciętych w wyniku zawalenia się tuneli.",false,0));
				booksRepository.save(new Books("Ślepnąc od świateł","Jakub Żulczyk","Zawsze chodzi wyłącznie o pieniądze. O nic innego. Ktoś może powiedzieć ci, że to niska pobudka. To nieprawda - oświadcza bohater powieści Jakuba Żulczyka. Ten młody człowiek przyjechał z Olsztyna do Warszawy, gdzie prawie skończył ASP. By uniknąć powielania egzystencjalnych schematów swoich rówieśników – przyszłych meneli, ludzi mogących w najlepszym razie otrzeć się o warstwy klasy średniej, niepoprawnych idealistów – dokonał życiowego wyboru według własnych upodobań: Zawsze lubiłem ważyć i liczyć.",false,0));
				booksRepository.save(new Books("Żmijowisko","Wojciech Chmielarz","Mistrzowski thriller Wojciecha Chmielarza. Ostatnie wspólne wakacje... Tragedia, która niszczy. Czas, który nie przynosi pocieszenia. Koniec, od którego nie ma ucieczki.\n" +
						"Grupa trzydziestolatków, przyjaciół ze studiów, co roku wyjeżdża wspólnie ze swoimi rodzinami na wakacje. Tym razem trafiają do zagubionej wśród jezior i lasów agroturystyki w niewielkiej wsi Żmijowisko.",false,0));




			}




		};
	}
}


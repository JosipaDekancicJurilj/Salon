package ba.sum.fpmoz.salon.controller;

import ba.sum.fpmoz.salon.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import ba.sum.fpmoz.salon.model.User;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    public UserRepository userRepo;

    @GetMapping("/login")
    public String showLoginForm(Model model){
        model.addAttribute("user", new User());
        return "login";
    }

    @GetMapping("/register")
    public String register (Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("success", false);
        return "register";
    }

    @PostMapping("/register_user")
    public String register_user (@Valid User user, BindingResult result, Model model) {
        // Provjeri je li korisnik postoji u bazi, ako postoji u polje email dodati grešku
        if (userRepo.findByEmail(user.getEmail()) != null) {
            result.addError(new FieldError("user", "email", "Korisnik je već registriran s ovom email adresom, molimo pokušajte koristiti drugu."));
        }
        // Provjeri jesu li lozinke odgovarajuće, ako nisu u polje password repeat i password dodati grešku
        if (!user.getPassword().equals(user.getPasswordRepeat())) {
            result.addError(new FieldError("user", "passwordRepeat", "Lozinke se moraju podudarati"));
            result.addError(new FieldError("user", "password", "Lozinke se moraju podudarati"));
        }

        // prikaži greške ukoliko postoje
        boolean errors = result.hasErrors();
        if (errors) {
            model.addAttribute("user", user); // podaci koji su uredu šalju se formi na prikaz
            model.addAttribute("success", false); // sakrij poruku da je sve ok
            return "register";
        }

        // enkodiraj lozinku i postavi ulogu guest za nove korisnike
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole("guest");
        userRepo.save(user);

        // resetiraj formu i prikaži poruku za uspješnu registraciju
        model.addAttribute("user", new User());
        model.addAttribute("success", true);
        return "register";
    }
}

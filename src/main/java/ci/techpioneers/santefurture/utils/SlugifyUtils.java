package ci.techpioneers.santefurture.utils;

import com.github.slugify.Slugify;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SlugifyUtils {

    public SlugifyUtils() {
    }

    // Méthode pour génerer un slud qui retourne le clé et la valeur
    public static String generate(String Input){
        String value = String.format("%s , %s",Input, UUID.randomUUID());
        final Slugify slg = Slugify.builder().underscoreSeparator(true).build();
        return slg.slugify(value);
    }
}

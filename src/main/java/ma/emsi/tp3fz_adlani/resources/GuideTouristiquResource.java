package ma.emsi.tp3fz_adlani.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

@Path("/guide")

public class GuideTouristiquResource {
    @GET
    @Path("/lieu/{ville_ou_pays}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLieuxTouristiques(@PathParam("ville_ou_pays") String lieu) {

        // Convertir le paramètre en minuscules pour faciliter la comparaison
        String lieuNormalise = lieu.toLowerCase();

        // Déterminer les lieux touristiques en fonction du paramètre
        List<String> lieuxTouristiques;

        switch (lieuNormalise) {
            case "paris":
                lieuxTouristiques = Arrays.asList("Tour Eiffel", "Louvre");
                break;
            case "maroc":
                lieuxTouristiques = Arrays.asList("Jamaa El Fna", "Hassan II");
                break;
            case "rome":
                lieuxTouristiques = Arrays.asList("Colisée", "Fontaine de Trevi");
                break;
            case "japon":
                lieuxTouristiques = Arrays.asList("Mont Fuji", "Temple Kinkaku-ji");
                break;
            case "new york":
                lieuxTouristiques = Arrays.asList("Statue de la Liberté", "Central Park");
                break;
            default:
                lieuxTouristiques = Arrays.asList("Place principale", "Musée local");
                break;
        }

        // Retourner la réponse JSON avec statut 200
        return Response.ok(lieuxTouristiques).build();
    }
}

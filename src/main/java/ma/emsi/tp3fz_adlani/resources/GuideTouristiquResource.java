package ma.emsi.tp3fz_adlani.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ma.emsi.tp3fz_adlani.llm.ServiceLLM;

@Path("/guide")
public class GuideTouristiquResource {

    @Inject
    private ServiceLLM serviceLLM;

    @GET
    @Path("/lieu/{ville_ou_pays}/{nombre_endroits}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInfosTouristiques(
            @PathParam("ville_ou_pays") String lieu,
            @PathParam("nombre_endroits") int nombreEndroits) {

        try {
            // Validation du nombre d'endroits
            if (nombreEndroits < 1 || nombreEndroits > 10) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("{\"erreur\": \"Le nombre d'endroits doit être entre 1 et 10\"}")
                        .build();
            }

            // Utiliser le service LLM pour obtenir les informations touristiques
            String reponseLLM = serviceLLM.obtenirInfosTouristiques(lieu, nombreEndroits);

            // Retourner la réponse JSON directement
            return Response.ok(reponseLLM).build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"erreur\": \"Service temporairement indisponible\"}")
                    .build();
        }
    }

    // Endpoint conservé pour la compatibilité (2 endroits par défaut)
    @GET
    @Path("/lieu/{ville_ou_pays}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInfosTouristiques(@PathParam("ville_ou_pays") String lieu) {
        try {
            // Utiliser le service LLM pour obtenir les informations touristiques (2 endroits par défaut)
            String reponseLLM = serviceLLM.obtenirInfosTouristiques(lieu);

            // Retourner la réponse JSON directement
            return Response.ok(reponseLLM).build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"erreur\": \"Service temporairement indisponible\"}")
                    .build();
        }
    }
}
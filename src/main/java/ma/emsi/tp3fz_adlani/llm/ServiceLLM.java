package ma.emsi.tp3fz_adlani.llm;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import dev.langchain4j.service.AiServices;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ServiceLLM {

    private final GuideTouristique guide;

    public ServiceLLM() {
        String apiKey = System.getenv("Gemini-API-Key");

        if (apiKey == null || apiKey.trim().isEmpty()) {
            throw new IllegalStateException("La clé API Gemini n'est pas configurée. " +
                    "Veuillez définir la variable d'environnement 'Gemini-API-Key'");
        }

        ChatModel modele = GoogleAiGeminiChatModel.builder()
                .apiKey(apiKey)
                .modelName("gemini-2.5-flash")
                .temperature(0.3)
                .build();

        this.guide = AiServices.create(GuideTouristique.class, modele);
    }

    public String obtenirInfosTouristiques(String lieu) {
        return guide.obtenirInfosTouristiques(lieu);
    }
}

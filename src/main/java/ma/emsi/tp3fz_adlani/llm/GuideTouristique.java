package ma.emsi.tp3fz_adlani.llm;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

public interface GuideTouristique {
    @SystemMessage("""
        Tu es un guide touristique expert. Pour chaque lieu demandé, tu dois indiquer :
        - Le prix moyen d'un repas dans la devise locale
        
        Réponds EXCLUSIVEMENT au format JSON suivant :
        {
          "ville_ou_pays": "nom de la ville ou du pays",
          "prix_moyen_repas": "<prix> <devise du pays>"
        }
        """)
}
package com.skaldia.service;

import com.skaldia.client.OpenAIClient;
import com.skaldia.dto.NpcDto;
import com.skaldia.model.Npc;
import com.skaldia.model.Scenario;
import com.skaldia.repository.NpcRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.transaction.Transactional;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class NpcService {

    @Inject
    NpcRepository npcRepository;

    @Inject
    OpenAIClient openAIClient;

    public List<Npc> getAllNpcs() {
        return npcRepository.listAll();
    }

    public Npc getNpc(Long id) {
        return npcRepository.findById(id);
    }

    @Transactional
    public void createNpc(NpcDto npcDto) {
        // TODO faire la gestion du scénario
        Scenario scenario = new Scenario();
        scenario.setLocation("Boston");
        scenario.setPeriod("1920");
        scenario.setSummary("Dans les années 1920, les personnages joueurs (PJ) sont membres d'une agence d'investigation privée à Boston. Un jour, ils reçoivent la visite d'une vieille dame, Mme Lestrade, qui leur demande d'enquêter sur la disparition mystérieuse de son fils, un archéologue renommé qui a récemment découvert un artefact ancien lié aux cultes obscurs.\n" +
                "\n" +
                "Acte 1 : La Disparition\n" +
                "\n" +
                "Les PJ commencent par enquêter dans l'entourage du fils disparu, interrogeant collègues et proches.\n" +
                "Ils découvrent que l'artéfact trouvé est une statuette étrange d'un dieu oublié, que le fils de Mme Lestrade avait l'intention d'étudier.\n" +
                "Ils trouvent également un journal personnel faisant mention de rêves étranges et de visites à une bibliothèque occulte.\n" +
                "Acte 2 : L'Ombre grandissante\n" +
                "\n" +
                "Les PJ suivent la piste à la bibliothèque occulte, où ils découvrent des informations sur un culte ancien dédié au réveil d'une entité endormie, ainsi que des indices sur l'endroit où le fils de Mme Lestrade pourrait être retenu.\n" +
                "Ils entendent parler d'un manoir abandonné à la périphérie de la ville, réputé hanté, et décident d'y enquêter.\n" +
                "Acte 3 : Le Manoir de l'Horreur\n" +
                "\n" +
                "Dans le manoir, les PJ découvrent des indices sur des rituels sombres et des rencontres avec des créatures cauchemardesques.\n" +
                "Ils trouvent le fils de Mme Lestrade, enfermé et sous l'emprise d'un sortilège, prêt à être sacrifié dans un rituel visant à réveiller l'entité endormie.\n" +
                "Ils doivent alors stopper le rituel, sauver le fils, et affronter les membres du culte ainsi que les forces obscures à l’œuvre.\n" +
                "Épilogue :\n" +
                "\n" +
                "Les PJ parviennent à sauver le fils et à disperser le culte, mais à quel prix ? Leurs actions ont-elles réellement stoppé la menace ou simplement retardé l'inévitable ?\n" +
                "Ils doivent faire face aux conséquences de leurs découvertes et aux secrets terrifiants de l'inconnu qu'ils ont dévoilés.\n" +
                "Dans cette trame, il y a beaucoup de place pour l'exploration, l'investigation, et des rencontres effrayantes typiques de l'univers de l'Appel de Cthulhu. Le Maître de Jeu (MJ) peut évidemment ajouter, modifier ou retirer des éléments pour mieux adapter l'histoire à son groupe de joueurs.");


        Npc npc = new Npc();

        npc.setFirstName(npcDto.getFirstName());
        npc.setLastName(npcDto.getLastName());
        npc.setAge(npcDto.getAge());
        npc.setOccupation(npcDto.getOccupation());

        String responseString = openAIClient.generateText("Je veux un JSON qui décrit " +
                        npc.getFirstName() + " " + npc.getLastName() +
                        " qui est " +
                        npc.getOccupation() +
                        " et qui a " +
                        npc.getAge() +
                        " ans, vivant à " +
                        scenario.getLocation() +
                        " en " +
                        scenario.getPeriod() +
                        ". Ce personnage est un PNJ que les joueurs pourrait rencontrer." +
                "Le background doit être étoffé et s'inscrit dans le contexte suivant :" +
                        scenario.getSummary() +
                " Je veux le resultat sous forme de JSON exposant" +
                "- le passé exhaustif du personnage, ce qui l'a amené à se trouver dans la position où il est au début de du récit  et un lien direct avec l'intrigue en 500 mots" +
                "- 3 adjectifs" +
                "- Son objectif" +
                "- Un indice sur l'affaire" +
                "- Comment avoir l'indice" +
                "- 3 phrases typique que le personnage pourrait utiliser" +
                "- la description physique en 3 phrases : première impression, détails importants, expression. Sois concis." +
                "- la description psychologique" +
                "- un numéro de dossier aléatoire en 12 caractères" +
                "- une date de naissance au format jj/MM/aaaa" +
                "N'affiche QUE le JSON sous cette forme " +
                "{" +
                "\"background\": \"\"," +
                "  \"adjectifs\": [\"\", \"\", \"\"]," +
                " \"objectif\": \"\"," +
                "\"indice\":\"\"," +
                " \"condition\":\"\"," +
                " \"phrases_typiques\": [" +
                " \"\"," +
                "  \"\"," +
                "   \"\"" +
                "  ]," +
                "  \"description_physique\": \"\"," +
                " \"description_psychologique\": \"\"," +
                "  \"numero_dossier\": \"\"," +
                "  \"date_naissance\": \"\"" +
                "}"
                );

        try (JsonReader jsonReader = Json.createReader(new StringReader(responseString))) {
            JsonObject responseJson = jsonReader.readObject();
            String resultString = responseJson.getJsonArray("choices").getJsonObject(0).getJsonObject("message").getString("content");
            JsonReader jsonReaderResult = Json.createReader(new StringReader(resultString));
            JsonObject resultJson = jsonReaderResult.readObject();
            System.out.println(resultJson);
            String background = resultJson.getString("background");

            JsonArray jsonAdjectifs = resultJson.getJsonArray("adjectifs");
            List<String> adjectifs = new ArrayList<>();
            for (int i = 0; i < jsonAdjectifs.size(); i++) {
                adjectifs.add(jsonAdjectifs.getString(i));
            }

            String objectif = resultJson.getString("objectif");
            String indice = resultJson.getString("indice");
            String condition = resultJson.getString("condition");

            JsonArray jsonPhrasesTypiques = resultJson.getJsonArray("phrases_typiques");
            List<String> phrasesTypiques = new ArrayList<>();
            for (int i = 0; i < jsonPhrasesTypiques.size(); i++) {
                phrasesTypiques.add(jsonPhrasesTypiques.getString(i));
            }

            String descriptionPhysique = resultJson.getString("description_physique");
            String descriptionPsychologique = resultJson.getString("description_psychologique");
            String numeroDossier = resultJson.getString("numero_dossier");
            String dateNaissance = resultJson.getString("date_naissance");


            npc.setBackground(background);
            npc.setAdjectives(adjectifs);
            npc.setBirthDate(dateNaissance);
            npc.setPhysicalDescription(descriptionPhysique);
            npc.setPsychologicalDescription(descriptionPsychologique);
            npc.setFileId(numeroDossier);

            npc.setPortrait("");
            while(npc.getPortrait().isBlank()) {
                String responseStringImg = openAIClient.generatePortrait("Damaged black and white photo portrait, realistic : "+descriptionPhysique);
                JsonReader jsonReaderImg = Json.createReader(new StringReader(responseStringImg));
                JsonObject responseJsonImg = jsonReaderImg.readObject();
                System.out.println(responseJsonImg);

                JsonArray dataArray = responseJsonImg.getJsonArray("data");
                System.out.println("6");
                if (dataArray != null && !dataArray.isEmpty()) {
                    System.out.println("5");
                    String imgUrl = dataArray.getJsonObject(0).getString("url");
                    System.out.println("4");
                    npc.setPortrait(imgUrl);
                    System.out.println("3");
                }
                System.out.println("2");
            }
            System.out.println("1");
        }

        // TODO set les autres champs via des appel d'API GenAI
        System.out.println("0");
        npcRepository.persist(npc);

    }
}


package org.CECarlemany.Acceptance;

import org.CECarlemany.ExcursionCenter;
import org.CECarlemany.Expedition.Expedition;
import org.CECarlemany.Expedition.ExpeditionCatalogue;
import org.CECarlemany.Expedition.InMemoryExpeditionCatalogue;
import org.CECarlemany.Expeditionary.Alpinist;
import org.CECarlemany.Expeditionary.Expeditionary;
import org.CECarlemany.Expeditionary.ExpeditionaryCatalogue;
import org.CECarlemany.Expeditionary.InMemoryExpeditionaryCatalogue;
import org.CECarlemany.Mountain.InMemoryMountainCatalogue;
import org.CECarlemany.Mountain.Mountain;
import org.CECarlemany.Mountain.MountainCatalogue;
import org.CECarlemany.Mountain.MountainDifficulty;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

class ExcursionCenterAcceptanceTest {

    @Test
    public void should_display_expeditionary_expeditions () {
        // Given
        String mountainID = UUID.randomUUID().toString();
        String mountainName = "Montseny";
        Integer mountainHeight = 1700;
        Mountain newMountain = new Mountain(mountainID, mountainName, mountainHeight, MountainDifficulty.MEDIUM);
        MountainCatalogue mountainCatalogue = new InMemoryMountainCatalogue(List.of(newMountain));

        String expeditionaryID = UUID.randomUUID().toString();
        String expeditionaryName = "Joan";
        Expeditionary newExpeditionary = new Alpinist(expeditionaryID, expeditionaryName);
        ExpeditionaryCatalogue expeditionaryCatalogue = new InMemoryExpeditionaryCatalogue(List.of(newExpeditionary));

        String expeditionID = UUID.randomUUID().toString();
        String expeditionName = "Excursió al Montseny";
        LocalDateTime expeditionDate = LocalDateTime.of(2021, 5, 1, 10, 0);
        Expedition newExpedition = new Expedition(expeditionID, expeditionName, expeditionDate, mountainID, new ArrayList<String>(List.of(expeditionaryID)));
        ExpeditionCatalogue expeditionCatalogue = new InMemoryExpeditionCatalogue(List.of(newExpedition));

        ExcursionCenter excursionCenter = new ExcursionCenter(mountainCatalogue, expeditionaryCatalogue, expeditionCatalogue);

        // When
        List<Expedition> foundExpeditions = excursionCenter.retrieveExpeditionsFromExpeditionaryID(expeditionaryID);

        // Then
        assertThat(foundExpeditions).hasSize(List.of(newExpedition).size());
        assertThat(foundExpeditions).contains(newExpedition);
    }

    @Test
    void should_sign_up_expeditioners_in_expedition() {
        MountainCatalogue mountainCatalogue = new InMemoryMountainCatalogue(List.of());

        String firstExpeditionaryID = UUID.randomUUID().toString();
        Expeditionary firstExpeditionary = new Alpinist(firstExpeditionaryID, "Joan");
        String secondExpeditionaryID = UUID.randomUUID().toString();
        Expeditionary secondExpeditionary = new Alpinist(secondExpeditionaryID, "Pedro");
        ExpeditionaryCatalogue expeditionaryCatalogue = new InMemoryExpeditionaryCatalogue(List.of(firstExpeditionary, secondExpeditionary));

        String expeditionID = UUID.randomUUID().toString();
        String expeditionName = "Excursió al Montseny";
        LocalDateTime expeditionDate = LocalDateTime.now();
        ArrayList<String> expeditioners = new ArrayList<>(List.of(firstExpeditionaryID));
        Expedition newExpedition = new Expedition(expeditionID, expeditionName, expeditionDate, null, expeditioners);
        ExpeditionCatalogue expeditionCatalogue = new InMemoryExpeditionCatalogue(List.of(newExpedition));

        ExcursionCenter excursionCenter = new ExcursionCenter(mountainCatalogue, expeditionaryCatalogue, expeditionCatalogue);

        Expedition expedition = excursionCenter.signUpExpeditionaryIntoExpedition(expeditionID, secondExpeditionaryID);

        assertThat(expeditioners).contains(firstExpeditionaryID, secondExpeditionaryID);
    }

}
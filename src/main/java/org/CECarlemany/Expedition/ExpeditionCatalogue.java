package org.CECarlemany.Expedition;

import java.util.List;

public interface ExpeditionCatalogue {
    List<Expedition> retrieveExpeditionsFromExpeditionaryID(String expeditionaryId);

    void addExpedition(Expedition newExpedition);

    List<Expedition> retrieveExpeditions();

    Expedition retrieveExpeditionByID(String expeditionID);
}

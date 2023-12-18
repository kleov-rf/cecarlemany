package org.CECarlemany.Expedition;

import org.CECarlemany.Expedition.Expedition;
import org.CECarlemany.Expedition.ExpeditionCatalogue;

import java.util.List;

public class InMemoryExpeditionCatalogue implements ExpeditionCatalogue {
    private final List<Expedition> expeditions;

    public InMemoryExpeditionCatalogue(List<Expedition> expeditions) {
        this.expeditions = expeditions;
    }
}
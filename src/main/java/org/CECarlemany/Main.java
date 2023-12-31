package org.CECarlemany;

import org.CECarlemany.Expedition.Expedition;
import org.CECarlemany.Expedition.ExpeditionConsolePrinter;
import org.CECarlemany.Expeditionary.Expeditionary;
import org.CECarlemany.Expeditionary.ExpeditionaryConsolePrinter;
import org.CECarlemany.Expeditionary.ExpeditionaryType;
import org.CECarlemany.Mountain.InMemoryMountainCatalogue;
import org.CECarlemany.Mountain.Mountain;
import org.CECarlemany.Mountain.MountainConsolePrinter;
import org.CECarlemany.Mountain.MountainDifficulty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ExcursionCenter excursionCenter = new ExcursionCenter();

        Map<Integer, String> mainMenuOptions = new HashMap<>() {{
            put(1, "Get expeditions of an expeditionary");
            put(2, "Create expeditionary");
            put(3, "List expeditioners");
            put(4, "Find expeditionary by ID");
            put(5, "Create mountain");
            put(6, "List mountains");
            put(7, "Find mountain by ID");
            put(8, "Create expedition");
            put(9, "List expeditions");
            put(10, "Find expedition by ID");
            put(11, "Sign up expeditionary into expedition");
            put(0, "Exit program");
        }};

        Scanner consoleLineScanner = new Scanner(System.in).useDelimiter("\n");

        do {
            for (Map.Entry<Integer, String> entry : mainMenuOptions.entrySet()) {
                System.out.printf("%d - %s\n", entry.getKey(), entry.getValue());
            }

            int selectedOption = consoleLineScanner.nextInt();

            switch (selectedOption) {
                case 1 -> getExpeditionsByExpeditionaryIDMenu(consoleLineScanner, excursionCenter);
                case 2 -> createExpeditionaryMenu(consoleLineScanner, excursionCenter);
                case 3 -> getAllExpeditionersMenu(excursionCenter);
                case 4 -> getExpeditionaryByIDMenu(consoleLineScanner, excursionCenter);
                case 5 -> createMountainMenu(consoleLineScanner, excursionCenter);
                case 6 -> getAllMountainsMenu(excursionCenter);
                case 7 -> getMountainByIDMenu(consoleLineScanner, excursionCenter);
                case 8 -> createExpeditionMenu(consoleLineScanner, excursionCenter);
                case 9 -> getAllExpeditionsMenu(excursionCenter);
                case 10 -> getExpeditionByIDMenu(consoleLineScanner, excursionCenter);
                case 11 -> signUpExpeditionaryIntoExpeditionMenu(consoleLineScanner, excursionCenter);
                case 0 -> System.exit(0);
                default -> System.out.println("Option not available");
            }
        } while (true);
    }

    private static void signUpExpeditionaryIntoExpeditionMenu(Scanner consoleLineScanner, ExcursionCenter excursionCenter) {
        System.out.println("Enter expedition ID:");
        String expeditionID = consoleLineScanner.next();

        System.out.println("Enter expeditionary ID:");
        String expeditionaryID = consoleLineScanner.next();

        Expedition updatedExpedition = excursionCenter.signUpExpeditionaryIntoExpedition(expeditionID, expeditionaryID);

        ExpeditionConsolePrinter expeditionConsolePrinter = new ExpeditionConsolePrinter(updatedExpedition);
        expeditionConsolePrinter.print();
    }

    private static void getExpeditionByIDMenu(Scanner consoleLineScanner, ExcursionCenter excursionCenter) {
        System.out.println("Enter expedition ID:");
        String expeditionID = consoleLineScanner.next();

        Expedition expedition = excursionCenter.retrieveExpeditionByID(expeditionID);
        ExpeditionConsolePrinter expeditionConsolePrinter = new ExpeditionConsolePrinter(expedition);
        expeditionConsolePrinter.print();
    }

    private static void getAllExpeditionsMenu(ExcursionCenter excursionCenter) {
        List<Expedition> expeditions = excursionCenter.retrieveExpeditions();
        for (Expedition expedition : expeditions) {
            new ExpeditionConsolePrinter(expedition).print();
        }
    }

    private static void getMountainByIDMenu(Scanner consoleLineScanner, ExcursionCenter excursionCenter) {
        System.out.println("Enter mountain ID:");
        String mountainID = consoleLineScanner.next();

        Mountain mountain = excursionCenter.retrieveMountainByID(mountainID);
        MountainConsolePrinter mountainConsolePrinter = new MountainConsolePrinter(mountain);
        mountainConsolePrinter.print();
    }

    private static void getExpeditionaryByIDMenu(Scanner consoleLineScanner, ExcursionCenter excursionCenter) {
        System.out.println("Enter expeditionary ID:");
        String expeditionaryID = consoleLineScanner.next();

        Expeditionary expeditionary = excursionCenter.retrieveExpeditionaryByID(expeditionaryID);
        ExpeditionaryConsolePrinter expeditionaryConsolePrinter = new ExpeditionaryConsolePrinter(expeditionary);
        expeditionaryConsolePrinter.print();
    }

    private static void createExpeditionMenu(Scanner consoleLineScanner, ExcursionCenter excursionCenter) {
        System.out.println("Enter expedition ID:");
        String expeditionID = consoleLineScanner.next();

        System.out.println("Enter expedition name:");
        String expeditionName = consoleLineScanner.next();

        System.out.println("Enter expedition date:");
        String expeditionDate = consoleLineScanner.next();

        System.out.println("Enter expedition mountain ID:");
        String expeditionMountainID = consoleLineScanner.next();

        System.out.println("Enter expedition expeditionary ID:");
        String expeditionExpeditionaryID = consoleLineScanner.next();

        excursionCenter.createExpedition(expeditionID, expeditionName, expeditionDate, expeditionMountainID, expeditionExpeditionaryID);
    }

    private static void getAllMountainsMenu(ExcursionCenter excursionCenter) {
        List<Mountain> mountains = excursionCenter.retrieveMountains();
        for (Mountain mountain : mountains) {
            MountainConsolePrinter mountainConsolePrinter = new MountainConsolePrinter(mountain);
            mountainConsolePrinter.print();
        }
    }

    private static void createMountainMenu(Scanner consoleLineScanner, ExcursionCenter excursionCenter) {
        System.out.println("Enter mountain ID:");
        String mountainID = consoleLineScanner.next();

        System.out.println("Enter mountain name:");
        String mountainName = consoleLineScanner.next();

        System.out.println("Enter mountain height:");
        Integer mountainHeight = consoleLineScanner.nextInt();

        Map<Integer, String> mountainDifficultyMenuOptions = new HashMap<>() {{
            put(1, MountainDifficulty.LOW.name());
            put(2, MountainDifficulty.MEDIUM.name());
            put(3, MountainDifficulty.HIGH.name());
        }};
        System.out.println("Select Mountain difficulty:");
        for (Map.Entry<Integer, String> entry : mountainDifficultyMenuOptions.entrySet()) {
            System.out.printf("%d - %s\n", entry.getKey(), entry.getValue());
        }

        int selectedOption = consoleLineScanner.nextInt();
        String mountainDifficulty = mountainDifficultyMenuOptions.get(selectedOption);

        excursionCenter.createMountain(mountainID, mountainName, mountainHeight, mountainDifficulty);
    }

    private static void getAllExpeditionersMenu(ExcursionCenter excursionCenter) {
        List<Expeditionary> expeditioners = excursionCenter.retrieveExpeditioners();
        for (Expeditionary expeditionary : expeditioners) {
            ExpeditionaryConsolePrinter expeditionaryConsolePrinter = new ExpeditionaryConsolePrinter(expeditionary);
            expeditionaryConsolePrinter.print();
        }
    }

    private static void createExpeditionaryMenu(Scanner consoleLineScanner, ExcursionCenter excursionCenter) {
        Map<Integer, String> expeditionaryTypeMenuOptions = new HashMap<>() {{
            put(1, ExpeditionaryType.ALPINIST.name());
            put(2, ExpeditionaryType.MEDIC.name());
        }};
        System.out.println("Select Expeditionary type:");
        for (Map.Entry<Integer, String> entry : expeditionaryTypeMenuOptions.entrySet()) {
            System.out.printf("%d - %s\n", entry.getKey(), entry.getValue());
        }

        int selectedOption = consoleLineScanner.nextInt();
        String expeditionaryType = expeditionaryTypeMenuOptions.get(selectedOption);

        System.out.println("Enter expeditionary ID:");
        String expeditionaryID = consoleLineScanner.next();

        System.out.println("Enter expeditionary name:");
        String expeditionaryName = consoleLineScanner.next();

        excursionCenter.createExpeditionary(expeditionaryType, expeditionaryID, expeditionaryName);
    }

    private static void getExpeditionsByExpeditionaryIDMenu(Scanner consoleLineScanner, ExcursionCenter excursionCenter) {
        System.out.println("Enter expeditionary ID:");
        String expeditionaryID = consoleLineScanner.next();

        List<Expedition> foundExpeditions = excursionCenter.retrieveExpeditionsFromExpeditionaryID(expeditionaryID);
        for (Expedition expedition : foundExpeditions) {
            new ExpeditionConsolePrinter(expedition).print();
        }
    }
}
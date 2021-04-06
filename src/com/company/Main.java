package com.company;

import java.util.Random;

public class Main {
    public static int bossHealth = 900;
    public static int bossDamage = 50;
    public static String bossDefenceType = "";
    public static int[] heroesHealth = {300, 250, 200, 200, 800, 300, 300, 300};
    public static int[] heroesDamages = {15, 20, 25, 0, 5, 10, 20, 30};
    public static String[] heroesAttackType = {"Logan", "Cyclops", "Professor X", "Medic", "Golem", "Lucky", "Berserk", "Thor"};


    public static void main(String[] args) {
        System.out.println("Начало игры");
        printStatistics();
        while (!gameOver()) {
            round();

        }

    }

    public static void medic() {
        int healing = 50;
        int indexMedic = 0;
        boolean medicDed = true;
        for (String str : heroesAttackType) {
            if (str == "Medic") {
                break;
            }
            indexMedic++;
        }
        if (heroesHealth[indexMedic] > 0) {
            medicDed = false;
            for (int i = 0; i < heroesHealth.length; i++) {
                if (heroesHealth[i] <= 100 && heroesHealth[i] > 0) {
                    heroesHealth[i] = heroesHealth[i] + healing;
                    System.out.println("исцеление " + heroesAttackType[i] + " " + healing);
                    break;
                }
            }
        } else if (heroesHealth[indexMedic] <= 0) {
            medicDed = true;
            System.out.println("Medic погиб");
        }
    }

    public static void bossChangesDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length);// 0,1,2,3
        bossDefenceType = heroesAttackType[randomIndex];
        System.out.println("Boss choose " + bossDefenceType);
    }

    public static void round() {
        bossChangesDefence();
        if (bossHealth > 0) {
            int indexThor = 0;
            boolean thorDed = true;
            for (String thor : heroesAttackType) {
                if (thor == "Thor") {
                    thorDed = false;
                    break;
                }
                indexThor++;
            }
            Random random = new Random();
            boolean rand = random.nextBoolean();
            if (rand) {
                System.out.println("THOR оглушил БОССА");
            }else {
                bossHits();
                methodGolem();
                methodBerserk();
                methodLucky();
                System.out.println("THOR НЕоглушил БОССА");
            }
        }
        heroesHits();
        medic();
        printStatistics();
    }

    public static boolean gameOver() {
        if (bossHealth <= 0) {
            System.out.println("ПОБЕДА!!!");
            return true;
        }
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }

        }
        if (allHeroesDead) {
            System.out.println("ПОРАЖЕНИЕ");
        }
        return allHeroesDead;
    }

    public static void bossHits() {

                for (int i = 0; i < heroesHealth.length; i++) {
                    if (heroesHealth[i] > 0) {
                        if (heroesHealth[i] - bossDamage < 0) {
                            heroesHealth[i] = 0;
                        } else {
                            heroesHealth[i] = heroesHealth[i] - bossDamage; // жизнь героя - сила удара босса = остаток жизни героя
                        }
                    }
                }

    }

    public static void heroesHits() {
        for (int i = 0; i < heroesDamages.length; i++) {
            if (bossHealth > 0 && heroesHealth[i] > 0) {
                Random random = new Random();
                int coefficient = random.nextInt(5) + 2;
                if (heroesAttackType[i] == bossDefenceType) {
                    System.out.println("Критический УРОН " + heroesDamages[i] * coefficient);
                    if (bossHealth - heroesDamages[i] * coefficient < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamages[i] * coefficient;
                    }
                } else {
                    if (bossHealth - heroesDamages[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamages[i];
                    }
                }
            }
        }
    }

    public static void printStatistics() {
        System.out.println("______________");
        System.out.println("Жизнь босса: " + bossHealth);
        for (int i = 0; i < heroesAttackType.length; i++) {
            System.out.println(heroesAttackType[i] + " -жизнь: " + heroesHealth[i]);
        }

    }

    public static void methodBerserk() {
        int indexBerserk = 0;
        boolean berserkDed = true;
        for (String metBerserk : heroesAttackType) {
            if (metBerserk == "Berserk") {
                break;
            }
            indexBerserk++;
        }
        if (heroesHealth[indexBerserk] > 0) {
            berserkDed = false;
            heroesHealth[indexBerserk] = heroesHealth[indexBerserk] + bossDamage / 2;
            heroesDamages[indexBerserk] = bossDamage / 2 + heroesDamages[indexBerserk];
        } else if (heroesHealth[indexBerserk] <= 0) {
            berserkDed = true;
            System.out.println("Berserk погиб");
        }

    }

    public static void methodLucky() {
        int indexLucky = 0;
        boolean luckyDead = true;
        for (String metLucky : heroesAttackType) {
            if (metLucky == "Lucky") {
                break;
            }
            indexLucky++;
        }

        if (heroesHealth[indexLucky] > 0) {
            luckyDead = false;
            Random random = new Random();
            boolean rand = random.nextBoolean();
            if (rand) {
                heroesHealth[indexLucky] += bossDamage;
                System.out.println("Lucky уклонился");
            }
            if (!rand) {
                System.out.println("Lucky НЕуклонился");
            }
        } else if (heroesHealth[indexLucky] <= 0) {
            luckyDead = true;
            System.out.println("lucky погиб");
        }
    }

    public static void methodGolem() {
        int indexGolem = 0;
        boolean golemDed = true;
        for (String golem : heroesAttackType) {
            if (golem == "Golem") {
                break;
            }
            indexGolem++;
        }
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[indexGolem] > 0) {
                golemDed = false;
                heroesHealth[indexGolem] -= bossDamage * 20 / 100;
                heroesHealth[i] += bossDamage * 20 / 100;
            } else if (heroesHealth[indexGolem] <= 0) {
                golemDed = true;
                System.out.println("Golem погиб ");
                break;
            }
        }
    }
}


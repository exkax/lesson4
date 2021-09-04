package com.company;

import java.util.Random;

public class Main {

    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefence = "";
    public static int[] heroesHealth = {260, 250, 240, 200};
    public static int[] heroesDamage = {20, 25, 15};
    public static String[] heroesAtackType = {"physical", "magical", "kinetic", "Medic"};
    public static int health = 20;

    public static void main(String[] args) {
        // создание рпг игры
        printstatistics();
        while (!isGameFinished()) {
            round();
        }
    }

    public static void changeBossDefence() {
        Random r = new Random();
        int randomIndex = r.nextInt(heroesAtackType.length);//0,1,2
        bossDefence = heroesAtackType[randomIndex];
        System.out.println("Boss choose " + bossDefence);
    }


    public static void round() {
        changeBossDefence();
        heroesHit();
        if (bossHealth > 0) {
            bossHits();
        }
        medicsHelp();
        printstatistics();
    }


    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesAtackType[i] == bossDefence) {
                    Random r = new Random();
                    int coeff = r.nextInt(6) + 2;
                    System.out.println("Critical Damage: " +
                            heroesDamage[i] * coeff);
                    if (bossHealth - heroesDamage[i] * coeff < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coeff;
                    }
                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }
            }
        }
    }

    public static void bossHits() {
        Random r = new Random();
        int chance = r.nextInt(3);// 0,1,2
        double coeff = Math.random(); //какое то число от 0 до 1 например 0.378
        if (chance == 0) {
            System.out.println("Boss became weaker " + (int) (bossDamage * coeff));
        }
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (chance == 0) {
                    if (heroesHealth[i] - (int) (bossDamage * coeff) < 0) {
                        heroesHealth[i] = 0;
                    } else {
                        heroesHealth[i] = heroesHealth[i] - (int) (bossDamage * coeff);

                    }
                } else {
                    if (heroesHealth[i] - bossDamage < 0) {
                        heroesHealth[i] = 0;
                    } else {
                        heroesHealth[i] = heroesHealth[i] - bossDamage;
                    }

                }
            }
        }
    }


    public static void printstatistics() {
        System.out.println("____________________");
        System.out.println("Boss health " + bossHealth);
        for (int i = 0; i < heroesAtackType.length; i++) {

            System.out.println(heroesAtackType[i]
                    + " health:" + heroesHealth[i]);

        }
        System.out.println("____________________");
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
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
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

    public static void medicsHelp() {
        Random r = new Random();
        int chance = r.nextInt(heroesDamage.length); // 0, 1, 2
        if (heroesHealth[chance] > 0 && heroesHealth[chance] < 100 && heroesHealth[3] > 0) {
            heroesHealth[chance] += health;
            System.out.println("Medic healed " + heroesAtackType[chance]);

        }
    }
}

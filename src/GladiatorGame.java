import java.util.*;

import java.util.LinkedList;



class Item {
    String name;
    int price;
    int durability;

    public Item(String name, int price, int durability) {
        this.name = name;
        this.price = price;
        this.durability = durability;
    }

    public void increaseDurability() {
        if (durability < 3) { // Assuming max durability is 3
            durability++;
        }
    }

    public void degrade() {
        if (durability > 0) {
            durability--;
        }
    }

    public boolean isBroken() {
        return durability == 0;
    }
}

class Enemy {
    String name;
    int difficulty;

    public Enemy(String name, int difficulty) {
        this.name = name;
        this.difficulty = difficulty;
    }
}

class Gladiator {
    String name;
    int gold;
    Item armor;
    Item weapon;
    int consecutiveLosses;
    Enemy currentEnemy;

    public Gladiator(String name, int gold, Item armor, Item weapon) {
        this.name = name;
        this.gold = gold;
        this.armor = armor;
        this.weapon = weapon;
        this.consecutiveLosses = 0;
        this.currentEnemy = null;
    }

    public void buyItem(Item item) {
        if (gold >= item.price) {
            gold -= item.price;
            if (item.name.equals("Armor")) {
                armor = item;
            } else if (item.name.equals("Weapon")) {
                weapon = item;
            }
            item.increaseDurability(); // Increase durability when purchased
            System.out.println(item.name + " purchased!");
        } else {
            System.out.println("Insufficient gold!");
        }
    }

    public void fight() {
        if (currentEnemy == null) {
            System.out.println("No enemy to fight!");
            return;
        }

        Random random = new Random();
        int chance = random.nextInt(100);

        if (!armor.isBroken() || !weapon.isBroken()) {
            if (chance < (80 - currentEnemy.difficulty)) { // Adjusted chance based on enemy difficulty
                System.out.println("You win the fight against " + currentEnemy.name + "!");
                gold += 10;
                consecutiveLosses = 0;
                currentEnemy = null; // Move on to the next enemy
            } else {
                System.out.println("You lose the fight against " + currentEnemy.name + "!");
                gold -= 10;
                consecutiveLosses++;
                armor.degrade();
                weapon.degrade();
            }
        } else {
            System.out.println("Your armor or weapon is broken! You need to buy new ones.");
        }
    }
}

class GladiatorGame {
    String win = "";

   public void gladgame() {
        Scanner scanner = new Scanner(System.in);

        Item armor = new Item("Armor", 5, 3);
        Item weapon = new Item("Weapon", 10, 3);

        Gladiator gladiator = new Gladiator("Maximus", 20, armor, weapon);

        LinkedList<Enemy> enemies = new LinkedList<>();
        enemies.add(new Enemy("Enemy 1", 5)); // Add enemies with different difficulties
        enemies.add(new Enemy("Enemy 2", 10));
        enemies.add(new Enemy("Enemy 3", 15));

        int currentDay = 0;

        while (currentDay <= 10) {
            System.out.println("Day " + currentDay);
            System.out.println("Gold: " + gladiator.gold);
            System.out.println("Armor Durability: " + gladiator.armor.durability);
            System.out.println("Weapon Durability: " + gladiator.weapon.durability);
            System.out.println("Current Enemy: " + (gladiator.currentEnemy != null ? gladiator.currentEnemy.name : "None"));
            System.out.println("1. Buy Armor (" + armor.price + " gold)");
            System.out.println("2. Buy Weapon (" + weapon.price + " gold)");
            System.out.println("3. Fight");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    gladiator.buyItem(armor);
                    currentDay--;
                    break;
                case 2:
                    gladiator.buyItem(weapon);
                    currentDay--;
                    break;
                case 3:
                    gladiator.fight();
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }

            if (gladiator.gold <= 0) {
                System.out.println("You ran out of gold! Game over.");
                break;
            }

            if (gladiator.consecutiveLosses >= 3) {
                System.out.println("You lost 3 fights in a row! Game over.");
                break;
            }

            if (gladiator.currentEnemy == null) {
                if (!enemies.isEmpty()) {
                    gladiator.currentEnemy = enemies.poll(); // Move to the next enemy
                } else {
                    System.out.println("You have defeated all enemies! You are victorious!");
                    win = "Player Won";
                    break;
                }
            }

            if (gladiator.consecutiveLosses == 0) {
                currentDay++; // Only increase the day if the gladiator wins a fight
            }
        }

        System.out.println("Game over.");
        scanner.close();
    }

public String winning() {
    return win;
}
}

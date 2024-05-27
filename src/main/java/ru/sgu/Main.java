package main.java.ru.sgu;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

interface Fireable {
    void fire();
    void reload();
}

abstract class AbstractWeapon implements Comparable<AbstractWeapon> {
    protected String model;
    protected int ammoCapacity;
    protected int currentAmmo;
    protected int serialnumber;

    public AbstractWeapon(String model, int ammoCapacity) {
        this.model = model;
        this.ammoCapacity = ammoCapacity;
        this.currentAmmo = ammoCapacity;
        this.serialnumber = ThreadLocalRandom.current().nextInt(3000, 5001);

    }

    public abstract String getNameOfWeapon();

    public void loadAmmo(int ammo) {
        if (ammo + currentAmmo > ammoCapacity) {
            currentAmmo = ammoCapacity;
        } else {
            currentAmmo += ammo;
        }
    }

    // Переопределение метода equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        AbstractWeapon that = (AbstractWeapon) obj;
        return ammoCapacity == that.ammoCapacity &&
                currentAmmo == that.currentAmmo &&
                Objects.equals(model, that.model);
    }

    // Переопределение метода hashCode
    @Override
    public int hashCode() {
        return Objects.hash(model, ammoCapacity, currentAmmo, serialnumber);
    }

    // Переопределение метода compareTo
    @Override
    public int compareTo(AbstractWeapon other) {
        return Integer.compare(this.ammoCapacity, other.ammoCapacity);
    }

    // Переопределение метода toString
    @Override
    public String toString() {
        return "[Model: " + model + ", Ammo Capacity: " + ammoCapacity + ", Current Ammo: " +
                currentAmmo + ", Serial Number: " + serialnumber +"]";
    }

    @Override
    public AbstractWeapon clone() {
        try {
            return (AbstractWeapon) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // Can't happen
        }
    }

    public AbstractWeapon shallowCopy() {
        try {
            return (AbstractWeapon) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // Can't happen
        }
    }

    // Deep copy method
    public AbstractWeapon deepCopy() {
        try {
            AbstractWeapon clone = (AbstractWeapon) super.clone();
            clone.model = new String(this.model);
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}

class Pistol extends AbstractWeapon implements Fireable {
    public Pistol(String model, int ammoCapacity) {
        super(model, ammoCapacity);
    }

    @Override
    public String getNameOfWeapon() {
        return "Pistol " + model;
    }

    @Override
    public void fire() {
        if (currentAmmo > 0) {
            currentAmmo--;
            System.out.println("Pistol fired. Remaining ammo: " + currentAmmo);
        } else {
            System.out.println("Out of ammo! Reload the pistol.");
        }
    }

    @Override
    public void reload() {
        loadAmmo(ammoCapacity);
        System.out.println("Pistol reloaded. Current ammo: " + currentAmmo);
    }

    public AbstractWeapon shallowCopy() {
        return (AbstractWeapon) super.clone();
    }

}

class Rifle extends AbstractWeapon implements Fireable {
    public Rifle(String model, int ammoCapacity) {
        super(model, ammoCapacity);
    }

    @Override
    public String getNameOfWeapon() {
        return "Rifle " + model;
    }

    @Override
    public void fire() {
        if (currentAmmo > 0) {
            currentAmmo--;
            System.out.println("Rifle fired. Remaining ammo: " + currentAmmo);
        } else {
            System.out.println("Out of ammo! Reload the rifle.");
        }
    }

    @Override
    public void reload() {
        loadAmmo(ammoCapacity);
        System.out.println("Rifle reloaded. Current ammo: " + currentAmmo);
    }
}


public class Main {
    public static void main(String[] args) {
        Pistol pistol = new Pistol("Glock 19", 15);
        Rifle rifle = new Rifle("AK-47", 30);

        pistol.fire();
        pistol.fire();
        pistol.reload();

        rifle.fire();
        rifle.reload();

        System.out.println(pistol);
        System.out.println(rifle);

        Pistol anotherPistol = new Pistol("Glock 19", 15);
        System.out.println(pistol +" is equal to " + anotherPistol + " " + pistol.equals(anotherPistol));
        System.out.println("Pistol hash code: " + pistol.hashCode());
        System.out.println("Another pistol hash code: " + anotherPistol.hashCode());


    }
}
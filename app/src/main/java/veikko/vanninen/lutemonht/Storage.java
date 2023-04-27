package veikko.vanninen.lutemonht;

import android.content.Context;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Storage {
    private ArrayList<Lutemon> lutemons = new ArrayList<>();
    private static Storage storage = null;
    private Storage() {
    }

    // Skeleton for storage class
    public static Storage getInstance() {
        if (storage == null) {
            storage = new Storage();
        }
        return storage;
    }

    // Method for listing all existing Lutemons
    public ArrayList<Lutemon> getLutemonsToList() {
        return lutemons;
    }

    //Method for getting lutemons to battlefield
    public ArrayList<Lutemon> getLutemonsToBattlefield() {
        return lutemons;
    }

    //Method for getting specific lutemon
    public Lutemon getLutemon (int id) {
        return lutemons.get(id);
    }

    // Method for adding new Lutemon
    public void addLutemon(Lutemon lutemon) {
        lutemons.add(lutemon);
    }

    // Method for deleting Lutemons
    public void deleteLutemon (int id) {
        int i = 0;
        for (Lutemon lutemon : lutemons) {
            if (lutemon.getId() == id) {
                break;
            }
        i++;
        }
        lutemons.remove(i);
    }

    // Method for saving lutemons to a file.
    public boolean saveLutemons (Context context) {
        try {
            ObjectOutputStream lutemonWriter = new ObjectOutputStream(context.openFileOutput("lutemons.data", Context.MODE_PRIVATE));
            lutemonWriter.writeObject(lutemons);
            lutemonWriter.close();
            return true;
        } catch (IOException e) {
            System.out.println("Could not save Lutemons.");
            return false;
        }

    }

    // Method for loading Lutemons from a file.
    public boolean loadLutemons (Context context) {
        try {
            ObjectInputStream lutemonReader = new ObjectInputStream(context.openFileInput("lutemons.data"));
            lutemons = (ArrayList<Lutemon>) lutemonReader.readObject();
            lutemonReader.close();
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("Could not load Lutemons.");
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            System.out.println("Could not load Lutemons.");
            return false;
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load Lutemons.");
            e.printStackTrace();
            return false;
        }
    }
}

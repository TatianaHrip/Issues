package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class FileOpenManagerTest {
    private FileOpenManager fileOpenManager = new FileOpenManager();

    @BeforeEach
    public void before() {
        fileOpenManager.save("doc", "Word");
        fileOpenManager.save("str", "Str");
    }

    @Test
    public void registerNewApp() {
        Map<String, String> mapT = new HashMap<>();
        mapT.put("ipg", "AddSee");
        mapT.put("doc", "Word");
        mapT.put("str", "Str");
        Map expected = mapT;
        Map actual = fileOpenManager.registerNewApp("ipg", "AddSee");
        assertEquals(expected, actual);
    }

    @Test
    public void getApplicationToOpenFile() {
        String expected = "Word";
        String actual = String.valueOf(fileOpenManager.getApplicationToOpenFile("doc"));
        assertEquals(expected, actual);
    }

    @Test
    public void removeTheBindingApplications() {
        Map<String, String> mapT = new HashMap<>();
        mapT.put("doc", "Word");
        Map expected = mapT;
        Map actual = fileOpenManager.removeTheBindingApplications("str");
        assertEquals(expected, actual);
    }

    @Test
    public void getListAllRegisteredExtensions() {
        Set<String> expected = Set.of("str", "doc");
        Set actual = fileOpenManager.getListAllRegisteredExtensions();
        assertEquals(expected, actual);
    }


    @Test
    public void getListAllApp() {
        List<String> expected = List.of("Str", "Word");
        ArrayList actual = fileOpenManager.getListAllApp();
        assertEquals(expected, actual);
    }

}

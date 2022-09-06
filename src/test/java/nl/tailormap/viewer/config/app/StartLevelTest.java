/*
 * Copyright (C) 2015-2021 B3Partners B.V.
 */
package nl.tailormap.viewer.config.app;

import nl.tailormap.viewer.util.TestUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Meine Toonen meinetoonen@b3partners.nl
 */
public class StartLevelTest extends TestUtil {

    @Test
    public void persistLevel() {
        StartLevel sl = new StartLevel();
        sl.setSelectedIndex(16);
        persistEntityTest(sl, StartLevel.class);

        entityManager.refresh(sl);
        StartLevel test = entityManager.find(StartLevel.class, sl.getId());
        assertNotNull(test);

        assertEquals(Integer.valueOf(16), test.getSelectedIndex());
        assertEquals(6, entityManager.createQuery("FROM Level").getResultList().size());
    }

    @Test
    public void deleteStartLevel() {
        Application app = entityManager.find(Application.class, applicationId);

        Level level = entityManager.find(Level.class, 5L);

        StartLevel sl = new StartLevel();
        sl.setLevel(level);
        sl.setApplication(app);
        sl.setSelectedIndex(16);
        persistEntityTest(sl, StartLevel.class);

        Level levelExists = entityManager.find(Level.class, 5L);
        Application appExists = entityManager.find(Application.class, applicationId);

        assertNotNull(levelExists);
        assertNotNull(appExists);
        assertEquals(6, entityManager.createQuery("FROM Level").getResultList().size());
    }

}

/*
 * Copyright (C) 2015-2021 B3Partners B.V.
 */
package nl.tailormap.viewer.config.app;

import nl.tailormap.viewer.util.TestUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author Meine Toonen meinetoonen@b3partners.nl
 */
class StartLayerTest extends TestUtil {

    private static final Log log = LogFactory.getLog(StartLayerTest.class);

    @Test
    void persistLayer() {
        StartLayer sl = new StartLayer();
        sl.setChecked(true);
        sl.setSelectedIndex(16);
        persistEntityTest(sl, StartLayer.class);

        entityManager.refresh(sl);

        StartLayer test = entityManager.find(StartLayer.class, sl.getId());
        assertNotNull(test, "StartLayer not found");
        assertEquals(Integer.valueOf(16), test.getSelectedIndex(), "Selected index not found");
        assertEquals(6, entityManager.createQuery("FROM Level").getResultList().size(), "Levels not found");
    }

    @Test
    void deleteLayer() {
        Application app = entityManager.find(Application.class, 1L);

        ApplicationLayer appLayer = entityManager.find(ApplicationLayer.class, 2L);

        StartLayer sl = new StartLayer();
        sl.setChecked(true);
        sl.setApplicationLayer(appLayer);
        sl.setApplication(app);
        sl.setSelectedIndex(16);
        persistEntityTest(sl, StartLayer.class);

        entityManager.flush();
        ApplicationLayer appLayerExists = entityManager.find(ApplicationLayer.class, 2L);
        Application appExists = entityManager.find(Application.class, applicationId);

        assertNotNull(appLayerExists, "ApplicationLayer not found");
        assertNotNull(appExists, "Application not found");
        assertEquals(6, entityManager.createQuery("FROM Level").getResultList().size(), "Levels not found");

    }

    @Test
    void deleteApplayer() {
        initData(true);
        assertNotNull(testAppLayer, "ApplicationLayer not found");
        assertNotNull(testStartLayer, "StartLayer not found");
        long lid = testAppLayer.getId();
        ApplicationLayer appLayer = entityManager.find(ApplicationLayer.class, lid);
        StartLayer startLayer = entityManager.find(StartLayer.class, testStartLayer.getId());
        assertNotNull(startLayer, "StartLayer not found");

        testLevel.getLayers().remove(appLayer);
        app.getStartLayers().removeAll(appLayer.getStartLayers().values());
        entityManager.remove(appLayer);
        try {
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            log.error("Fout bij verwijderen", e);
            assert (false);
        }
        entityManager.getTransaction().begin();

        ApplicationLayer appLayerNull = entityManager.find(ApplicationLayer.class, lid);
        StartLayer startLayerNull = entityManager.find(StartLayer.class, testStartLayer.getId());
        assertNull(appLayerNull, "AppLayer not deleted");
        assertNull(startLayerNull, "Startlayer not deleted");
    }


    @Test
    void deleteApplication() {
        initData(true);
        assertNotNull(testAppLayer,"AppLayer not found");
        assertNotNull(app,"App not found");
        assertNotNull(testStartLayer,"StartLayer not found");
        long lid = testAppLayer.getId();

        entityManager.remove(app);
        entityManager.getTransaction().commit();
        entityManager.getTransaction().begin();

        ApplicationLayer shouldBeNull = entityManager.find(ApplicationLayer.class, lid);
        StartLayer shouldBeNullAsWell = entityManager.find(StartLayer.class, testStartLayer.getId());
        Application appShouldBeNull = entityManager.find(Application.class, app.getId());
        assertNull(shouldBeNull, "AppLayer should be null");
        assertNull(shouldBeNullAsWell, "StartLayer should be null");
        assertNull(appShouldBeNull, "Application should be null");
    }
}

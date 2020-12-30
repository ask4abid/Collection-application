package com.finca.ccw.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.finca.ccw.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class CollectionTrackingTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CollectionTracking.class);
        CollectionTracking collectionTracking1 = new CollectionTracking();
        collectionTracking1.setId(1L);
        CollectionTracking collectionTracking2 = new CollectionTracking();
        collectionTracking2.setId(collectionTracking1.getId());
        assertThat(collectionTracking1).isEqualTo(collectionTracking2);
        collectionTracking2.setId(2L);
        assertThat(collectionTracking1).isNotEqualTo(collectionTracking2);
        collectionTracking1.setId(null);
        assertThat(collectionTracking1).isNotEqualTo(collectionTracking2);
    }
}

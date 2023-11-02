package com.ahlesunnat.asws.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.ahlesunnat.asws.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DemoTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Demo.class);
        Demo demo1 = new Demo();
        demo1.setId(1L);
        Demo demo2 = new Demo();
        demo2.setId(demo1.getId());
        assertThat(demo1).isEqualTo(demo2);
        demo2.setId(2L);
        assertThat(demo1).isNotEqualTo(demo2);
        demo1.setId(null);
        assertThat(demo1).isNotEqualTo(demo2);
    }
}

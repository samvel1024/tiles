package com.balitech.tiled;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by Samvel Abrahamyanon 11/13/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class PlayerTest {

    @Mock
    private Player player;

    @InjectMocks
    private World world = new World();

    @Test
    public void test(){
        Assert.assertNotNull(world.getPlayer());
    }

}

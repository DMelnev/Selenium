package Lesson4;

import org.junit.Assert;
import org.junit.Test;

public class MainTest_v4 {

    @Test
    public void rectangleSquare() {
        Assert.assertEquals(0, Main.rectangleSquare(0, 4, 5), 0.001);
        Assert.assertEquals(0, Main.rectangleSquare(3, 0, 5), 0.001);
        Assert.assertEquals(0, Main.rectangleSquare(3, 4, 0), 0.001);
        Assert.assertEquals(0, Main.rectangleSquare(-1, 4, 5), 0.001);
        Assert.assertEquals(6, Main.rectangleSquare(3, 4, 5), 0.001);
        Assert.assertEquals(600000000, Main.rectangleSquare(30000,40000, 50000), 0.001);
    }

}
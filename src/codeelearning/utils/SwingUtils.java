/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package codeelearning.utils;

import java.awt.image.BufferedImage;
import javafx.scene.image.Image;
import javafx.scene.paint.ColorBuilder;

/**
 *
 * @author Ramzi
 */
public class SwingUtils {

    public static java.awt.Color toAWTColor(javafx.scene.paint.Color fxColor)
    {
        return new java.awt.Color((float)fxColor.getRed(), (float)fxColor.getGreen(), (float)fxColor.getBlue(), (float)fxColor.getOpacity());
    }

    public static javafx.scene.paint.Color fromAWTColor(java.awt.Color awtColor)
    {
        return ColorBuilder.create()
                            .red(awtColor.getRed() / 255.0)
                            .green(awtColor.getGreen() / 255.0)
                            .blue(awtColor.getBlue() / 255.0).build();
    }

    // There is a problem with this implementation: transparent pixels on the BufferedImage aren't converted to transparent pixels on the fxImage.
    public static javafx.scene.image.Image convertToFxImage(java.awt.image.BufferedImage awtImage) {
    	if (Image.impl_isExternalFormatSupported(BufferedImage.class)) {
    		return javafx.scene.image.Image.impl_fromExternalImage(awtImage);
    	} else {
    		return null;
    	}
    }

    public static java.awt.image.BufferedImage convertToAwtImage(javafx.scene.image.Image fxImage) {
    	if (Image.impl_isExternalFormatSupported(BufferedImage.class)) {
    		java.awt.image.BufferedImage awtImage = new BufferedImage((int)fxImage.getWidth(), (int)fxImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
        	return (BufferedImage)fxImage.impl_toExternalImage(awtImage);
    	} else {
    		return null;
    	}
    }
}

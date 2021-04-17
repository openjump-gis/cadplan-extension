package com.cadplan.icon;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.swing.ImageIcon;

import com.vividsolutions.jump.workbench.Logger;
import org.saig.jump.lang.I18N;

public class IconLoader {
   public static final ImageIcon DEFAULT_UNKNOW_ICON =
       new ImageIcon(IconLoader.class.getResource("default_icon.png"));

   public static ImageIcon icon(String filename) {
      return icon(filename, true);
   }

   public static ImageIcon icon(String filename, boolean useDefaultForNull) {
      URL urlIcon = IconLoader.class.getResource(filename);
      if (urlIcon == null) {
         if (useDefaultForNull) {
            Logger.warn(I18N.getMessage("com.vividsolutions.com.cadplan.jump.workbench.com.cadplan.jump.ui.images.IconLoader.The-icon-{0}-has-not-been-found-default-icon-will-be-used", new Object[]{filename}));
            return DEFAULT_UNKNOW_ICON;
         } else {
            return null;
         }
      } else {
         return new ImageIcon(urlIcon);
      }
   }

   public static ImageIcon icon(URL url) {
      if (url == null) {
         Logger.warn(I18N.getMessage("com.vividsolutions.com.cadplan.jump.workbench.com.cadplan.jump.ui.images.IconLoader.The-icon-{0}-has-not-been-found-default-icon-will-be-used", new Object[]{url}));
         return DEFAULT_UNKNOW_ICON;
      } else {
         return new ImageIcon(url);
      }
   }

   public static BufferedImage image(String filename) {
      ImageIcon icon = icon(IconLoader.class.getResource(resolveFile(filename)));
      Image image = icon.getImage();
      BufferedImage bufImg = new BufferedImage(image.getWidth(null), image.getHeight(null), 2);
      Graphics2D bGr = bufImg.createGraphics();
      bGr.drawImage(image, 0, 0, null);
      bGr.dispose();
      return bufImg;
   }

   protected static String resolveFile(String filename) {
      String[] var4;
      int var3 = (var4 = new String[]{"", "famfam/", "fugue/"}).length;

      for(int var2 = 0; var2 < var3; ++var2) {
         String path = var4[var2];
         if (IconLoader.class.getResource(path + filename) != null) {
            return path + filename;
         }
      }

      return filename;
   }
}

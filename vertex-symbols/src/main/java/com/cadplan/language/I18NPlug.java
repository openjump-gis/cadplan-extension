package com.cadplan.language;

import com.vividsolutions.jump.I18N;
import java.io.File;
import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.ResourceBundle;

public class I18NPlug {

   private static ResourceBundle bundle =
       ResourceBundle.getBundle("com.cadplan.jump.language.VertexSymbolsPlugin");

   //private static File path = new File("com/cadplan/jump/language/VertexSymbolsPlugin");

   //public static String getI18N(String key) {
   //   return getMessage(key);
   //}

   public static String getI18N(String key) {
      return bundle.getString(key);
   }

   public static String getI18N(String label, Object... objects) {
      final MessageFormat mformat = new MessageFormat(bundle.getString(label));
      return mformat.format(objects);
   }

   //public static String getMessage(String label, Object... objects) {
   //   return I18N.getMessage(path, label, objects);
   //}
}

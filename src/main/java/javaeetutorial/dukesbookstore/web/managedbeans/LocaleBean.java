package javaeetutorial.dukesbookstore.web.managedbeans;

import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * Managed bean that retrieves current locale, used on each page.
 */
@Named
@SessionScoped
public class LocaleBean extends AbstractBean
{
   private static final long serialVersionUID = 1L;
   private static Logger LOGGER = Logger.getLogger("dukesbookstore.web.LocalBean");
   private Locale locale = context().getViewRoot().getLocale();

   public LocaleBean()
   {}

   public String getLanguage()
   {
      Locale newlocale = null;
      LOGGER.log(Level.INFO, "Entering LocaleBean.getLanguage");
      String lang = locale.getLanguage();
      Map<String, Object> map = context().getExternalContext().getSessionMap();
      if (map.containsKey("locale")) {
         newlocale = (Locale) map.get("locale");
      }
      if (!(newlocale == null)) {
         String newlang = newlocale.getLanguage();
         if (!newlang.equals(lang)) {
            return newlang;
         }
      }
      return lang;
   }

   public Locale getLocale()
   {
      LOGGER.log(Level.FINER, "Entering LocaleBean.getLocale");
      return locale;
   }

   public void setLanguage(String language)
   {
      LOGGER.log(Level.FINER, "Entering LocaleBean.setLanguage");
      locale = new Locale(language);
      context().getViewRoot().setLocale(locale);
   }

   public void setLocale(Locale locale)
   {
      LOGGER.log(Level.FINER, "Entering LocaleBean.setLocale");
      this.locale = locale;
   }
}

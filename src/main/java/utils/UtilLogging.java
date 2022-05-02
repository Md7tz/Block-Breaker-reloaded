package utils;

import java.util.Date;
import java.util.logging.*;

public class UtilLogging {
  public static Logger LOGGER = Logger.getLogger(UtilLogging.class.getName());
  static {
    ConsoleHandler handler = new ConsoleHandler();
    handler.setFormatter(new SimpleFormatter() {
      private static final String format = "[%1$tF %1$tT] [%2$s] %3$s %n";
      @Override
      public String formatMessage(LogRecord record) {
        return String.format(format,
            new Date(record.getMillis()),
            record.getLevel().getLocalizedName(),
            record.getMessage() 
        );
      }
    });
    LOGGER.setUseParentHandlers(false);
    LOGGER.addHandler(handler);
  }
}
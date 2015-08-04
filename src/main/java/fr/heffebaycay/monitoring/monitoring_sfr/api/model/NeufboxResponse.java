package fr.heffebaycay.monitoring.monitoring_sfr.api.model;


import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class NeufboxResponse {

    private static final Logger logger = LoggerFactory.getLogger(NeufboxResponse.class);

    public static <T> T deserialize(Class<? extends T> type, String content) {
        Serializer serializer = new Persister();

        try {
            T result = serializer.read(type, content);
            return result;
        } catch (Exception e) {
            logger.error("Failed to deserialize object: {}", content);
            return null;
        }
    }

}

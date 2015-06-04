package armyc2.c2sd.renderer.utilities;

public class SettingsChangedEvent {

    static public final String EventType_CacheSizeChanged = "CACHE_CHANGED";
    static public final String EventType_FontChanged = "FONT_CHANGED";
    
    private String _EventType = null;
    public SettingsChangedEvent(String eventType)
    {
        if(eventType != null && eventType.equals("") == false)
        {
            _EventType = eventType;
        }
    }
    
    public String getEventType()
    {
        return _EventType;
    }

}

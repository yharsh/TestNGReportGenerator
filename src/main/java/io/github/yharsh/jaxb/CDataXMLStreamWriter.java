package io.github.yharsh.jaxb;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.util.regex.Pattern;

/**
 * Created by YHarsh on 2/26/2017.
 */
public class CDataXMLStreamWriter extends DelegatingXMLStreamWriter
{
    private static final Pattern XML_CHARS = Pattern.compile("[&<>]");
    
    public CDataXMLStreamWriter(XMLStreamWriter del)
    {
        super(del);
    }
    
    @Override
    public void writeCharacters(String text) throws XMLStreamException
    {
        boolean useCData = XML_CHARS.matcher(text).find();
        if(useCData)
        {
            super.writeCData(text);
        }
        else
        {
            super.writeCharacters(text);
        }
    }
}